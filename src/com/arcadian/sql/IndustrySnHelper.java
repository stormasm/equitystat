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

import com.arcadian.persist.Industry;

/**
 *   IndustrySymbolNameHelper used to read all of the data out of the of the database from the Industry table
 *  and pick off the symbol and name from the Industry data structure 
 *  and store all of these symbols and names in a single HashMap.
 *
 *  Currently used by the SymbolNameWriter to write all of the symbols
 *  that get stored in the Industry database table to the symbolname table.
 *
 * @author Michael I Angerman
 */
 
public class IndustrySnHelper {
	
	public static final String q01 = "select ind from Industry ind";
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	public void init() throws Exception {

		Configuration cfg = new Configuration()
			.addClass(Industry.class);

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	/*
	*       Given an Industry get the symbol and name and store it in a HashMap
	*/
	
	public HashMap getHashMap() { 
	       
	       HashMap hm = new HashMap();
	       
		try {
			tx = session.beginTransaction();

			List snl = session.createQuery(q01).list();
			// System.out.println("Number of symbols = " + snl.size());
			
			Iterator iter = snl.iterator();
			while ( iter.hasNext() ) {
				Industry ix = (Industry) iter.next();
				
				String symbol = ix.getSymbol();
				String name = ix.getName();
				hm.put(symbol,name);
			}
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}
	
	public static void main(String[] args) throws Exception {

		IndustrySnHelper isnh = new IndustrySnHelper();
		isnh.init();
		HashMap hm = isnh.getHashMap();
	
		for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
			String key = (String) i.next();
			String value = (String) hm.get(key);
			System.out.println(key + " " + value);
		}	
		
		isnh.factory.close();
	}
}
