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
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.persist.Ks;

import com.arcadian.ant.AntProperty;
import com.arcadian.persist.Ks;
import com.arcadian.reader.KsReader;

/**
 * Insert new Ks data into Database given a symbol.
 * 
 * @author Michael I Angerman
 */
public class InsertKs {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	KsReader ksr = null;
		
	public void init() throws Exception {
		String dira = AntProperty.etsdata();
		String dirb = dira.concat("\\finance\\ks\\predb\\");
		ksr = new KsReader();
		ksr.init(dirb);
		
		Configuration cfg = new Configuration()
			.addClass(Ks.class);
			// .setProperty(Environment.HBM2DDL_AUTO, "create");
			
		//cfg.setProperty("hibernate.show_sql", "true");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public void process() throws Exception {
		
		Ks ks = null;
		int count = 0;
		
		try {
			tx = session.beginTransaction();
		
		while ((ks = ksr.getNextKs()) != null) {
			long begin = System.currentTimeMillis();
			// ks.print();
			session.save(ks);
			
			String symbol = ks.getSymbol();
			
			long end = System.currentTimeMillis();
			System.out.println(symbol + " " + count + " " + " " + (end - begin)/1000);
			count = count + 1;
		}
		tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
    
    public void close() throws Exception {
	session.close();	
    }
    
	public static void main(String[] args) throws Exception {
		
		InsertKs ih = new InsertKs();
		ih.init();
		ih.process();
		ih.close();
	}
}
