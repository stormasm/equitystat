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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.ant.AntProperty;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.persist.Sector;
import com.arcadian.reader.KsReader;

/**
 * Get Sector data from the Database given an ArrayList of sectors
 * 
 * @author Michael I Angerman
 */
public class CriteriaSector {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(Sector.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public ArrayList process(ArrayList limit, String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Sector.class)
			.add( Restrictions.in("sector", limit))
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
	
	public static HashMap getIndustryCodesHashMap() throws Exception {
		
		List sectorlist = null;
		HashMap hm = new HashMap();
		
		CriteriaSector ih = new CriteriaSector();
		ih.init();
		
		try {
			tx = session.beginTransaction();
			long begin = System.currentTimeMillis();
			sectorlist = session.createCriteria(Sector.class).list();
			long end = System.currentTimeMillis();
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
		for (int i = 0; i < sectorlist.size(); i++) {
			Sector s = (Sector) sectorlist.get(i);
			String indcode = s.getIndcode();
			String indname = s.getIndname();
			hm.put(indcode,indname);
		}
		
		ih.close();
		return hm;
	}
    
    public void close() throws Exception {
	session.close();	
    }
    
    /**
    Pass in an ArrayList of symbols you want to get back
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getIndNames(ArrayList al, String orderby) throws Exception {
		
		CriteriaSector ih = new CriteriaSector();
		ih.init();
		ArrayList back = ih.process(al,orderby);
		ih.close();
		
		ArrayList back1 = new ArrayList();
		
		for (int i = 0; i < back.size(); i++) {
			Sector s = (Sector) back.get(i);
			back1.add(s.getIndname());
		}
		
		return back1;
	}
	
	public static ArrayList getIndCodes(ArrayList al, String orderby) throws Exception {
		
		CriteriaSector ih = new CriteriaSector();
		ih.init();
		ArrayList back = ih.process(al,orderby);
		ih.close();
		
		ArrayList back1 = new ArrayList();
		
		for (int i = 0; i < back.size(); i++) {
			Sector s = (Sector) back.get(i);
			back1.add(s.getIndcode());
		}
		
		return back1;
	}
/*    
	public static void main(String[] args) throws Exception {

		ArrayList limit = new ArrayList();
		limit.add("Technology");
		
		ArrayList back = getIndNames(limit,"sector");
		System.out.println("Size of new Array is " + back.size());
		for (int i = 0; i < back.size(); i++) {
			System.out.println(back.get(i));
		}
		
		ArrayList back1 = getIndCodes(limit,"sector");
		System.out.println("Size of new Array is " + back.size());
		for (int i = 0; i < back1.size(); i++) {
			System.out.println(back1.get(i));
		}
	}
*/

	public static void main(String[] args) throws Exception {

		HashMap hm = getIndustryCodesHashMap();
		
		for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
			String key = (String) i.next();
			String value = (String) hm.get(key);
			System.out.println(key + " " + value);
		}
		
		System.out.println("Number of Total Industry Codes = " + hm.size());
	}
}
