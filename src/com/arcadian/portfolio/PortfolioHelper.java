/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.portfolio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.persist.Ks;
import com.arcadian.persist.Industry;
import com.arcadian.persist.IndustryKs;
import com.arcadian.persist.Portfolio;

import com.arcadian.util.CombineArrayLists;
import com.arcadian.util.IntersectArrayLists;

/**
 * Used to get symbols based on certain criteria
 *
 * @author Michael I Angerman
 */
 
public class PortfolioHelper {
	
	public static final String q01 = "select port from Portfolio port where port.portfolioname like 'xxportfolionamexx'";
	public static final String q02 = "select port from Portfolio port where port.username like 'xxusernamexx'";
	public static final String q03 = "select ind from Industry ind where ind.indcode like 'xxindcodexx'";
	public static final String q04 = "select ind from Industry ind where ind.sector like 'xxsectorxx'";
	public static final String q06 = "select port from Portfolio port";
	public static final String q07 = "select ks from Ks ks";
	
	// These are custom queries
	public static final String q05 = "select indks from IndustryKs indks where indks.marketcap > 20000";

	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	public void init() throws Exception {

		Configuration cfg = new Configuration()
			.addClass(Ks.class)
			.addClass(Industry.class)
			.addClass(IndustryKs.class)
			.addClass(Portfolio.class);
		
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	/*
	*       Get all of the symbols in all of the portfolios making sure there are no duplicates
	*       This function will be used to drive going out to the internet and getting the data.
	*/
	
	public ArrayList getAllSymbols() {
	
		Vector vec = new Vector();
		ArrayList usernames = getAllUserNames();
			
		for (int i = 0; i < usernames.size(); i++) {
			String name = (String) usernames.get(i);
			ArrayList al = new ArrayList();
			al = getUsername(name);
			vec.add(al);
		}
		
		ArrayList back = CombineArrayLists.combine(vec);
		return back;
	}
	
	/*
	*       Get all of the usernames in all of the portfolios making sure there are no duplicates
	*/
	
	public ArrayList getAllUserNames() { 
	       
	       ArrayList al = new ArrayList();
	       HashSet hs = new HashSet();
	       
	       List usernames = session.createQuery(q06).list();
	       
	       System.out.println("Number of rows in portfoliotable = " + usernames.size());
			
		Iterator iter = usernames.iterator();
		int count = 0;
		while ( iter.hasNext() ) {
			Portfolio p = (Portfolio) iter.next();
			String name = (String) p.getUsername();
			// add this symbol name to the set if it is not already there
			boolean val = hs.add(name);
			// if symbol was not there a TRUE is returned, then add to ArrayList
			if (val) {
				al.add(name);	
			}
		}
		return al;
	}
	
	
	/*
	*
	*    This is used by a program which goes out onto the internet to get Ks data
	*    for a particular ArrayList of symbols.
	*   
	*    It looks at all of the symbols in all of the portfolios and determines if there
	*    are any symbols in all of these portfolios that are not in the Ks table.
	*
	*    If there are, it goes out and get those symbols
	*
	*/
	
	public static ArrayList intersectPortfolioSymbolsWithKsSymbols() {
		PortfolioHelper ph = new PortfolioHelper();
		
		try {
			ph.init();
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
		ArrayList portSymbols = ph.getAllSymbols();
		ArrayList ksSymbols = ph.getAllSymbolsKs();
		ArrayList back = IntersectArrayLists.intersect(portSymbols,ksSymbols);
		ph.close();
		return back;
	}
	
	
	/*
	*       Get all of the symbols in the Ks table
	*/
	
	public ArrayList getAllSymbolsKs() { 
	       
	       ArrayList al = new ArrayList();
	       
	       List ksa = session.createQuery(q07).list();
	       
	       System.out.println("Number of symbols in the ks table = " + ksa.size());
		
		for (int i = 0; i < ksa.size(); i++) {
			Ks ks = (Ks) ksa.get(i);
			al.add(ks.getSymbol());	
		}
		
		return al;
	}
	
	/*
	*       Given a portfolio name get the symbols associated with it
	*/
	
	public static ArrayList getPortfolio(String portfolioname) { 
	       
	       ArrayList al = new ArrayList();
	       
	       PortfolioHelper ph = new PortfolioHelper();
		
		try {
			ph.init();
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
	       
		try {
			tx = session.beginTransaction();

			String s01 = q01.replaceAll("xxportfolionamexx",portfolioname);		
			
			List indcodes = session.createQuery(s01).list();
			System.out.println("Number of portfolios = " + indcodes.size());
			
			Iterator iter = indcodes.iterator();
			int count = 0;
			while ( iter.hasNext() ) {
				Portfolio p = (Portfolio) iter.next();
				List plist = (List) p.getSymbols();
				
				int plistnum = plist.size();
				
				System.out.println("Number of symbols in portfolio " + plistnum);
				
				Iterator piter = plist.iterator();
				
				while(piter.hasNext()) {
					String x = (String) piter.next();
					al.add(x);
				}
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (tx!=null) tx.rollback();
			// throw e;
		}
		ph.close();
		
		return al;
	}
	
	/*
	*       Given a user name get the symbols associated with it
	*/
	
	public ArrayList getUsername(String username) { 
	       
	       ArrayList al = new ArrayList();
	       
		try {
			tx = session.beginTransaction();

			String s01 = q02.replaceAll("xxusernamexx",username);		
			
			List indcodes = session.createQuery(s01).list();
			System.out.println("Number of portfolios = " + indcodes.size());
			
			Iterator iter = indcodes.iterator();
			int count = 0;
			while ( iter.hasNext() ) {
				Portfolio p = (Portfolio) iter.next();
				List plist = (List) p.getSymbols();
				
				int plistnum = plist.size();
				
				System.out.println("Number of symbols in portfolio " + plistnum);
				
				Iterator piter = plist.iterator();
				
				while(piter.hasNext()) {
					String x = (String) piter.next();
					al.add(x);
				}
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (tx!=null) tx.rollback();
			// throw e;
		}
		return al;
	}
	
	/*
	*       Given an indcode get the symbols associated with it
	*/
	
	public ArrayList getIndcode(String indcode) { 
	       
	       ArrayList al = new ArrayList();
	       
		try {
			tx = session.beginTransaction();

			String s01 = q03.replaceAll("xxindcodexx", indcode);		
			
			List indcodes = session.createQuery(s01).list();
			System.out.println("Number of symbols = " + indcodes.size());
			
			Iterator iter = indcodes.iterator();
			int count = 0;
			while ( iter.hasNext() ) {
				Industry p = (Industry) iter.next();
				String s = p.getSymbol();
				al.add(s);
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (tx!=null) tx.rollback();
			// throw e;
		}
		return al;
	}
	
	/*
	*       Given a sector get the symbols associated with it
	*/
	
	public ArrayList getSector(String sector) { 
	       
	       ArrayList al = new ArrayList();
	       
		try {
			tx = session.beginTransaction();

			String s01 = q04.replaceAll("xxsectorxx", sector);		
			
			List indcodes = session.createQuery(s01).list();
			System.out.println("Number of symbols = " + indcodes.size());
			
			Iterator iter = indcodes.iterator();
			int count = 0;
			while ( iter.hasNext() ) {
				Industry p = (Industry) iter.next();
				String s = p.getSymbol();
				al.add(s);
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (tx!=null) tx.rollback();
			// throw e;
		}
		return al;
	}
	
	/*
	*       Given a custom query, get the symbols associated with it
	*/
	
	public ArrayList getCustomQuery() { 
	       
	       ArrayList al = new ArrayList();
	       
		try {
			tx = session.beginTransaction();

			List indcodes = session.createQuery(q05).list();
			System.out.println("Number of symbols = " + indcodes.size());
			
			Iterator iter = indcodes.iterator();
			int count = 0;
			while ( iter.hasNext() ) {
				IndustryKs p = (IndustryKs) iter.next();
				String s = p.getSymbol();
				al.add(s);
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (tx!=null) tx.rollback();
			// throw e;
		}
		return al;
	}
	
	public static void close() {
		session.close();	
	}
	
	/*
	public static void main(String[] args) throws Exception {

		PortfolioHelper ph = new PortfolioHelper();
		ph.init();
		
		// ArrayList al = ph.getPortfolio("gspc");
		// ArrayList al = ph.getIndcode("electu");
		// ArrayList al = ph.getSector("Technology");
		// ArrayList altgt = ph.getCustomQuery();
		
		ArrayList al = ph.getAllSymbols();
		
		for (int q = 0; q < al.size(); q++) {
			System.out.println(al.get(q));	
		}
		
		System.out.println("Total number of unique symbols = " + al.size());
		
		ArrayList alwin = FilterArrayLists.filter(alsrc,altgt);		
		
		for (int i = 0; i < alwin.size(); i++) {
			System.out.println(alwin.get(i));	
		}
		
		ph.close();
	}
	*/

/*	
	public static void main(String[] args) throws Exception {
		ArrayList al = PortfolioHelper.intersectPortfolioSymbolsWithKsSymbols();
			
		for (int i = 0; i < al.size(); i++) {
			String symbol = (String) al.get(i);
			System.out.println(symbol);	
		}
	}
*/

/*
	public static void main(String[] args) throws Exception {
		ArrayList al = PortfolioHelper.getPortfolio("softbig");
			
		for (int i = 0; i < al.size(); i++) {
			String symbol = (String) al.get(i);
			System.out.println(symbol);	
		}
	}
*/

	public static void main(String[] args) throws Exception {
		
		PortfolioHelper ph = new PortfolioHelper();
		ph.init();
		
		ArrayList al = ph.getSector("Technology");
		
		for (int q = 0; q < al.size(); q++) {
			System.out.println(al.get(q));	
		}
		
		System.out.println("Total number of unique companies = " + al.size());
		
		/*
		ArrayList alwin = FilterArrayLists.filter(alsrc,altgt);		
		
		for (int i = 0; i < alwin.size(); i++) {
			System.out.println(alwin.get(i));	
		}
		*/
		ph.close();
	}
}
