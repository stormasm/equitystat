/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.persist.SymbolName;

/**
 * Used to read all of the symbols out of the database from the symbolname table,
 * all of these symbols are stored in a single HashMap.
 *
 * @author Michael I Angerman
 */
 
public class SymbolNameHelper {
	
	public static final String q01 = "select sn from SymbolName sn";
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	public static HashMap hm = null;
	
	public void init() throws Exception {

		Configuration cfg = new Configuration()
			.addClass(SymbolName.class);
		
		// cfg.setProperty("hibernate.show_sql", "true");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
		
		process();
	}
	
	/*
	*       Given a portfolio name get the symbols associated with it
	*/
	
	public void process() { 
	       
	       hm = new HashMap();
	       
		try {
			tx = session.beginTransaction();

			List snl = session.createQuery(q01).list();
			// System.out.println("Number of symbols = " + snl.size());
			
			Iterator iter = snl.iterator();
			while ( iter.hasNext() ) {
				SymbolName sn = (SymbolName) iter.next();
				
				String symbol = sn.getSymbol();
				String name = sn.getName();
				hm.put(symbol,name);
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (tx!=null) tx.rollback();
			// throw e;
		}
	}
	
	public HashMap getHashMap() {
		return hm;	
	}
	
	public static void main(String[] args) throws Exception {

		SymbolNameHelper snh = new SymbolNameHelper();
		snh.init();
		HashMap hm = snh.getHashMap();
	
		for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
			String key = (String) i.next();
			String value = (String) hm.get(key);
			System.out.println(key + " " + value);
		}	
		
		snh.factory.close();
	}
}
