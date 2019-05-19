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

import com.arcadian.persist.Hp;

/**
 * Initialize one table the Zrato Database.
 *
 * @author Michael I Angerman
 */
 
public class InitZratoTable {

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

		InitZratoTable ix = new InitZratoTable();

		Configuration cfg = new Configuration()
			.addClass(Hp.class)
			.setProperty(Environment.HBM2DDL_AUTO, "create");

		ix.factory = cfg.buildSessionFactory();
		ix.init();
		ix.factory.close();
	}
}
