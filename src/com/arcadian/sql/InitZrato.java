/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.persist.Eps;
import com.arcadian.persist.Hp;
import com.arcadian.persist.Industry;
import com.arcadian.persist.IndustryKs;
import com.arcadian.persist.Ks;
import com.arcadian.persist.Ma;
import com.arcadian.persist.Portfolio;
import com.arcadian.persist.Sector;
import com.arcadian.persist.SymbolName;

/**
 * Initialize the Zrato Database.
 *
 * @author Michael I Angerman
 */
 
public class InitZrato {

	private SessionFactory factory;
	
	public void init() throws Exception {

		Session s = factory.openSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public static void main(String[] args) throws Exception {

		InitZrato ix = new InitZrato();

		Configuration cfg = new Configuration()
			.addClass(Eps.class)
			.addClass(Hp.class)
			.addClass(Industry.class)
			.addClass(IndustryKs.class)
			.addClass(Ks.class)
			.addClass(Ma.class)
			.addClass(Portfolio.class)
			.addClass(Sector.class)
			.addClass(SymbolName.class)
			.setProperty(Environment.HBM2DDL_AUTO, "create");
		
		// cfg.setProperty("hibernate.show_sql", "true");

		ix.factory = cfg.buildSessionFactory();
		ix.init();
		ix.factory.close();
	}
}
