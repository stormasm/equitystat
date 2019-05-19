/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.writer;

import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.ant.AntProperty;
import com.arcadian.sql.IndustrySnHelper;
import com.arcadian.persist.SymbolName;
import com.arcadian.reader.PortfolioIndexReader;
import com.arcadian.reader.SymbolNameReader;

/**
 * Insert new SymbolName data into a database given a HashMap with symbol,name pair.
 * 
 * @author Michael I Angerman
 */
public class SymbolNameWriter {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(SymbolName.class);
			// .setProperty(Environment.HBM2DDL_AUTO, "create");
			
		//cfg.setProperty("hibernate.show_sql", "true");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();

	}
	
	public void process(HashMap hm) throws Exception {
		
		try {
			tx = session.beginTransaction();
			long begin = System.currentTimeMillis();
				for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
					String key = (String) i.next();
					String value = (String) hm.get(key);
					
					SymbolName sn = new SymbolName();
					sn.setSymbol(key);
					sn.setName(value);
					session.save(sn);
					
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
*
*  This reads all of the symbols out of the Index Files
*  and stores the data in the symbolname database.
*
*/
    
	public static void getSymbolDataFromIndexFiles() throws Exception {
		
		SymbolNameWriter snw = new SymbolNameWriter();
		snw.init();
		
		// Set up to get the data from the XML Index Files
		String dira = AntProperty.etsdata();
		String dirb = dira.concat("\\finance\\cp\\db\\");
		
		PortfolioIndexReader pir = new PortfolioIndexReader();
		pir.init(dirb);
		pir.process();
		HashMap hm = pir.getHashMap();
		
		snw.process(hm);
		snw.close();
	}

/**
*
*  This reads all of the symbol out of the  Industry table
*  and stores the data in the symbolname database.
*
*/
	
	public static void getSymbolDataFromIndustryTable() throws Exception {
		
		SymbolNameWriter snw = new SymbolNameWriter();
		snw.init();
		
		// Set up to get the data from the Industry table in the database
		IndustrySnHelper isnh = new IndustrySnHelper();
		isnh.init();
		HashMap hm = isnh.getHashMap();
		
		snw.process(hm);
		snw.close();
	}

/**
*
*  This reads all of the symbol files out of the  \etsdata\symbolname directory
*  and stores the data in the database.
*
*/
	
	public static void getSymbolDataFromXmlFile() throws Exception {
		
		SymbolNameWriter snw = new SymbolNameWriter();
		snw.init();
	
	        SymbolNameReader snr = new SymbolNameReader();
		String dira = AntProperty.etsdata();
		String dirb = dira.concat("\\symbolname\\");
		snr.init(dirb);
		snr.process();
	
		// Get the Hashmap from the SymbolNameReader
		HashMap hm = snr.getHashMap();
		snw.process(hm);
		snw.close();
	}
	
	
	/**
*
*  This reads all of the symbol files out of the  \etsdata\symbolname directory
*  and stores the data in the database.
*
*/
	
	public static void main(String[] args) throws Exception {
		
		SymbolNameWriter.getSymbolDataFromIndustryTable(); 
		// SymbolNameWriter.getSymbolDataFromXmlFile();
		// SymbolNameWriter.getSymbolDataFromIndexFiles();
	}
}
