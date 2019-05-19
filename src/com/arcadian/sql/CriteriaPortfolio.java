/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;

import com.arcadian.persist.Portfolio;

/**
 * Get a set of Portfolios.
 *
 * This class manipulates the portfolio table in different ways and 
 * passes back data associated with only the portfolio table.
 * 
 * @author Michael I Angerman
 */
public class CriteriaPortfolio {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(Portfolio.class);

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	/**
	*
	*    Given an ArrayList of usernames, pass back the list of portfolios associated with that particular ArrayList
	*
	*/
	
	public ArrayList processUserName(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Portfolio.class)
			.add( Restrictions.in("username", limit))
			.list();
			
			al = (ArrayList) ksx;
			
			long end = System.currentTimeMillis();
			// System.out.println((end - begin)/1000);
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return al;
	}
	
	
	/**
	*
	*    Given an ArrayList of usernames, 
	*    Given an ArrayList of portfolionames
	*    pass back the list of portfolios associated with both particular ArrayLists
	*
	*/
	
	public ArrayList processPortfolioName(ArrayList username, ArrayList portfolioname) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Portfolio.class)
			.add( Restrictions.in("username", username))
			.add( Restrictions.in("portfolioname", portfolioname))
			.list();
			
			al = (ArrayList) ksx;
			
			long end = System.currentTimeMillis();
			// System.out.println((end - begin)/1000);
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return al;
	}
    
    public void close() throws Exception {
	session.close();	
    }
    
    /**
    Pass in an ArrayList of usernames of portfolios you want to get back
    */
    
    public static ArrayList getUserName(ArrayList al) throws Exception {
		
		CriteriaPortfolio ih = new CriteriaPortfolio();
		ih.init();
		ArrayList back = ih.processUserName(al);
		ih.close();
		return back;
	}
	
	/**
	Pass in an ArrayList of usernames and portfolio names you want to get back
	*/
	
	public static ArrayList getPortfolioName(ArrayList uname, ArrayList pname) throws Exception {
		
		CriteriaPortfolio ih = new CriteriaPortfolio();
		ih.init();
		ArrayList back = ih.processPortfolioName(uname,pname);
		ih.close();
		return back;
	}
    
	public static void main(String[] args) throws Exception {
		
		/*
		ArrayList limit = new ArrayList();
		limit.add("iris");
		ArrayList back = getUserName(limit);
		*/
		
		ArrayList uname = new ArrayList();
		uname.add("iris");
		
		ArrayList pname = new ArrayList();
		pname.add("p101");
		
		ArrayList back = getPortfolioName(uname, pname);
		
		for (int i = 0; i < back.size(); i++) {
			Portfolio p = (Portfolio) back.get(i);
			System.out.println(p.getPortfolioname());
		}
	}
}
