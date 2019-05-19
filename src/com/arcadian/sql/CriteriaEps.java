/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import com.arcadian.persist.Eps;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.reader.KsReader;

/**
 * Get Eps data from the Database given an ArrayList of symbols.
 * 
 * @author Michael I Angerman
 */

public class CriteriaEps {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration().addClass(Eps.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	/**
	*
	*        This is for Descending Order
	*
	*/
	
	public ArrayList processLimitDesc(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Eps.class)
			.add( Restrictions.in("symbol", limit))
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
	
	public ArrayList processLimitAsc(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Eps.class)
			.add( Restrictions.in("symbol", limit))
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
	*        This is for Descending Order
	*
	*/
	
	public ArrayList processOrderDesc(ArrayList limit, String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Eps.class)
			.add( Restrictions.in("symbol", limit))
			.addOrder(Order.desc(orderby))
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
	*        This is for Ascending Order
	*
	*/
	
	public ArrayList processOrderAsc(ArrayList limit, String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Eps.class)
			.add( Restrictions.in("symbol", limit))
			.addOrder(Order.asc(orderby))
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
	*        This gets all of the data for Descending Order
	*
	*/
	
	public ArrayList processAllDesc(String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Eps.class)
			.addOrder(Order.desc(orderby))
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
	*        This gets all of the data for Ascending Order
	*
	*/
	
	public ArrayList processAllAsc(String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Eps.class)
			.addOrder(Order.asc(orderby))
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
    Pass in an ArrayList of symbols you want to get back
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getLimitDataDesc(ArrayList al) throws Exception {
		
		CriteriaEps ih = new CriteriaEps();
		ih.init();
		ArrayList back = ih.processLimitDesc(al);
		ih.close();
		return back;
	}
	
	/**
    Pass in an ArrayList of symbols you want to get back
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getLimitDataAsc(ArrayList al) throws Exception {
		
		CriteriaEps ih = new CriteriaEps();
		ih.init();
		ArrayList back = ih.processLimitAsc(al);
		ih.close();
		return back;
	}
	
    /**
    Pass in an ArrayList of symbols you want to get back
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getOrderDataDesc(ArrayList al, String orderby) throws Exception {
		
		CriteriaEps ih = new CriteriaEps();
		ih.init();
		ArrayList back = ih.processOrderDesc(al,orderby);
		ih.close();
		return back;
	}
	
     /**
    Pass in an ArrayList of symbols you want to get back
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getOrderDataAsc(ArrayList al, String orderby) throws Exception {
		
		CriteriaEps ih = new CriteriaEps();
		ih.init();
		ArrayList back = ih.processOrderAsc(al,orderby);
		ih.close();
		return back;
	}
	
     /**
     This function gets all of the data in the Ks table.
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getAllDataDesc(String orderby) throws Exception {
		
		CriteriaEps ih = new CriteriaEps();
		ih.init();
		ArrayList back = ih.processAllDesc(orderby);
		ih.close();
		return back;
	}
	
    /**
    This function gets all of the data in the Ks table
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getAllDataAsc(String orderby) throws Exception {
		
		CriteriaEps ih = new CriteriaEps();
		ih.init();
		ArrayList back = ih.processAllAsc(orderby);
		ih.close();
		return back;
	}
	
	public static void main(String[] args) throws Exception {
		
		ArrayList limit = PortfolioHelper.getPortfolio("techg01");
		
		ArrayList ksymbols = CriteriaKs.getSymbolDataAsc(limit,"marketcap");
		
		ArrayList back = CriteriaEps.getLimitDataAsc(ksymbols);
		
		for (int i = 0; i < back.size(); i++) {
			Eps eps = (Eps) back.get(i);
			System.out.println(eps.getSymbol() + " " + eps.getPctincrease());
		}
	}
	
	/*
	public static void main(String[] args) throws Exception {
		
		ArrayList limit = new ArrayList();
		limit.add("msft");
		limit.add("intc");
		limit.add("csco");
		
		ArrayList back = CriteriaEps.getDataDesc(limit,"pctincrease");
		
		for (int i = 0; i < back.size(); i++) {
			Eps eps = (Eps) back.get(i);
			System.out.println(eps.getSymbol() + " " + eps.getPctincrease());
		}
	}
	*/
	
	/*
	public static void main(String[] args) throws Exception {
		
		// ArrayList back = getAllDataAsc("marketcap");
		ArrayList back = getAllDataDesc("marketcap");
		
		for (int i = 0; i < back.size(); i++) {
			Ks ks = (Ks) back.get(i);
			System.out.println(ks.getSymbol() + " " + ks.getMarketcap());
		}
	}
	*/
}
