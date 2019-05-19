/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.writer;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.arcadian.ant.AntProperty;
import com.arcadian.finance.symbol.SymbolArrayList;
import com.arcadian.persist.Portfolio;
import com.arcadian.reader.PortfolioReaderCsv;

/**
 * Insert new Portfolio data into a database given a username, portfolioname, and a symbol
 * based on Csv input.
 * 
 * @author Michael I Angerman
 */
 
public class PortfolioWriterCsv {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	PortfolioReaderCsv pr = null;
	Vector vec = null;
	
	public void init() throws Exception {
		
		String dira = AntProperty.etsdata();
		String dirb = dira.concat("\\csv\\");
		
		pr = new PortfolioReaderCsv();
		pr.init(dirb);
		pr.process();
		vec = pr.getVector();

		Configuration cfg = new Configuration()
			.addClass(Portfolio.class);
			// .setProperty(Environment.HBM2DDL_AUTO, "create");
			
		//cfg.setProperty("hibernate.show_sql", "true");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();

	}
	
	public void process(String username) throws Exception {
		
		try {
			tx = session.beginTransaction();
			long begin = System.currentTimeMillis();
			for (int k = 0; k < vec.size(); k++) {
				SymbolArrayList shm = (SymbolArrayList) vec.get(k);
				
				ArrayList al = shm.getArrayList();
				String pname = shm.getSymbol();
					
				Portfolio p = new Portfolio();
				p.setUsername(username);
				p.setPortfolioname(pname);
				p.setSymbols(al);
				session.save(p);
					
			}

			long end = System.currentTimeMillis();
			tx.commit();
			System.out.println((end - begin)/1000);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
    public void close() throws Exception {
	session.close();	
    }
    
    /**
    *               Username is hardcoded for now to "michael", will probably fix later
    *
    */
    
	public static void main(String[] args) throws Exception {
		
		PortfolioWriterCsv ih = new PortfolioWriterCsv();
		ih.init();
		ih.process("michael");
		ih.close();
	}
}
