/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

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

import com.arcadian.persist.Hp;

import com.arcadian.ant.AntProperty;
import com.arcadian.finance.symbol.SymbolHp;
import com.arcadian.reader.HpReader;
import com.arcadian.persist.Hp;
// import com.arcadian.sql.util.SymbolNameUtil;

/**
 * Insert new Hp data into Database given a symbol.
 * 
 * @author Michael I Angerman
 */
public class InsertHp {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	HpReader hpr = null;
	
	public void init() throws Exception {
		String dira = AntProperty.etsdata();
		String dirb = dira.concat("\\finance\\hp\\db\\");
		hpr = new HpReader();
		hpr.init(dirb);
		
		Configuration cfg = new Configuration().addClass(Hp.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();

	}
	
	public void process() throws Exception {
		
		SymbolHp shp = null;
		
		// HashMap hm = SymbolNameUtil.getMergedHm();
		
		HashMap hm = new HashMap();
		hm.put("aapl", "Apple Computer");
		hm.put("adbe","Adobe");
		hm.put("dell","Dell Computer");
		hm.put("ebay","EBay");
		hm.put("yhoo","Yahoo");
		
		while ((shp = hpr.getNextSymbolHp()) != null) {
			String symbolx = shp.getSymbol();
			String namex = (String) hm.get(symbolx);
			Hp[] hp = shp.getHpa();
			long begin = System.currentTimeMillis();
			
			try {
				tx = session.beginTransaction();
			
			for (int m = 0; m < hp.length; m++) {
				hp[m].setSymbol(symbolx);
				session.save(hp[m]);
			}
			long end = System.currentTimeMillis();
			tx.commit();
			System.out.println(symbolx + " " + (end - begin)/1000);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			}
	}
	
	private String getDate(Date date) {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuffer sb1 = new StringBuffer();
		FieldPosition fp = new FieldPosition(0);
		
		StringBuffer sb = sdf.format(date,sb1,fp);
		String modate = sb.toString(); 
		return modate;	
	}
	
    public void close() throws Exception {
	session.close();	
    }
    
	public static void main(String[] args) throws Exception {
		
		InsertHp ih = new InsertHp();
		ih.init();
		ih.process();
		ih.close();
	}
}
