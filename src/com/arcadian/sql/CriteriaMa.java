/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.ant.AntProperty;
import com.arcadian.finance.symbol.SymbolList;
import com.arcadian.persist.Ma;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.reader.KsReader;

/**
 * Get Ma data from the Database given a particular symbol.
 * 
 * @author Michael I Angerman
 */

public class CriteriaMa {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration().addClass(Ma.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	/**
	*
	*        This is for Descending Order
	*
	*/
	
	public ArrayList processDesc(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Ma.class)
			.add( Restrictions.in("symbol", limit))
			.addOrder(Order.desc("symbol"))
			.list();
			
			al = (ArrayList) ksx;
			
			long end = System.currentTimeMillis();
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return al;
	}
	
	/**
	*
	*        This is for Ascending Order
	*
	*/
	
	public ArrayList processAsc(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Ma.class)
				.add( Restrictions.in("symbol", limit))
				.addOrder(Order.asc("symbol"))
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
	
	public SymbolList processSymbolAsc(String symbol) throws Exception {
		
		ArrayList limit = new ArrayList();
		limit.add(symbol);
		
		SymbolList sl = new SymbolList();
		sl.setSymbol(symbol);
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Ma.class)
				.add( Restrictions.in("symbol", limit))
				.addOrder(Order.asc("date"))
				.list();
		
			/** DEBUGGING ONLY
			for (int q = 0; q < ksx.size(); q++) {
				Ma ma = (Ma) ksx.get(q);
				ma.print();
			}
			*/
			
			sl.setList(ksx);
			
			long end = System.currentTimeMillis();
			// System.out.println((end - begin)/1000);
			tx.commit();
			
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return sl;
	}
    
    public void close() throws Exception {
	session.close();	
    }
	
    /**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static ArrayList getDataDescArrayList(ArrayList al) throws Exception {
		
		CriteriaMa ih = new CriteriaMa();
		ih.init();
		ArrayList back = ih.processDesc(al);
		ih.close();
		return back;
	}
	
     /**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static ArrayList getDataAscArrayList(ArrayList al) throws Exception {
		
		CriteriaMa ih = new CriteriaMa();
		ih.init();
		ArrayList back = ih.processAsc(al);
		ih.close();
		return back;
	}
	
    /**
    Pass in an ArrayList of symbols and get back a Vector of Symbol ArrayLists
    */
    
    public static Vector getDataAscVector(ArrayList al) throws Exception {
		
		Vector vec = new Vector();
		CriteriaMa ih = new CriteriaMa();
		ih.init();
		
		for (int i = 0; i < al.size(); i++) {
			String symbol = (String) al.get(i);	
			SymbolList sl = ih.processSymbolAsc(symbol);
			
			List l = sl.getList();
			int size = l.size();
			// System.out.println(symbol + " " + l.size()); 	
			
			if (size > 0) {
				vec.add(sl);
			}
		}
		
		ih.close();
		return vec;
	}
	
    /**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static Ma[] getDataDesc(ArrayList al) throws Exception {
		
		CriteriaMa ih = new CriteriaMa();
		ih.init();
		ArrayList back = ih.processDesc(al);
		ih.close();
		
		int size = back.size();
		Ma[] ma = new Ma[size];
		
		for (int i = 0; i < size; i++) {
			ma[i] = (Ma) back.get(i);	
		}
		
		return ma;
	}
	
     /**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static Ma[] getDataAsc(ArrayList al) throws Exception {
		
		CriteriaMa ih = new CriteriaMa();
		ih.init();
		ArrayList back = ih.processAsc(al);
		ih.close();
		
		int size = back.size();
		Ma[] ma = new Ma[size];
		
		for (int i = 0; i < size; i++) {
			ma[i] = (Ma) back.get(i);	
		}
		
		return ma;
	}

	public static void main(String[] args) throws Exception {
		
		ArrayList limit = new ArrayList();
		limit.add("yhoo");
		limit.add("amat");
		limit.add("msft");
		limit.add("ebay");
		limit.add("ibm");
		
		Vector vec = CriteriaMa.getDataAscVector(limit);
		
		for (int i = 0; i < vec.size(); i++) {
			SymbolList sl = (SymbolList) vec.get(i);
			System.out.println("-----------------------  " + sl.getSymbol() + " ---------------------------------");
			List l = sl.getList();
			
			for (int j = 0;  j < l.size(); j++) {
				Ma ma = (Ma) l.get(j);
				ma.print();
			}
		}
	}
	
/*	
	public static void main(String[] args) throws Exception {
		
		ArrayList al = new ArrayList();
		al.add("yhoo");
		al.add("amat");
		al.add("msft");
		al.add("ebay");
		
		ArrayList back = CriteriaMa.getDataAscArrayList(al);
		
		for (int i = 0; i < back.size(); i++) {
			Ma ma = (Ma) back.get(i);
			ma.print();
		}
	}
*/	
	/*
	public static void main(String[] args) throws Exception {
		
		ArrayList al = new ArrayList();
		al.add("yhoo");
		al.add("amat");
		al.add("msft");
		al.add("ebay");
		
		Ma ma[] = CriteriaMa.getDataAsc(al);
		
		for (int i = 0; i < ma.length; i++) {
			// System.out.println(ma[i].getSymbol() + " " + ma[i].getDate() + " " + ma[i].getAdjclose());
			ma[i].print();
		}
	}
	*/
	
	/*
	public static void main(String[] args) throws Exception {
		
		ArrayList al = new ArrayList();
		al.add("yhoo");
		
		ArrayList back = CriteriaMa.getDataAscArrayList(al);
		
		for (int i = 0; i < back.size(); i++) {
			Ma ma = (Ma) back.get(i);
			System.out.println(ma.getSymbol() + " " + ma.getDate() + " " + ma.getAdjclose());
		}
	}
	*/
}
