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
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.persist.Industry;
import com.arcadian.reader.KsReader;

/**
 * Get Industry data from the Database given an ArrayList of symbols.
 * 
 * @author Michael I Angerman
 */
public class CriteriaIndustry {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(Industry.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public ArrayList process(ArrayList limit, String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Industry.class)
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
	
    
    public void close() throws Exception {
	session.close();	
    }
    
    /**
    Pass in an ArrayList of symbols you want to get back
    Pass in the database column as a String that you want to order by
    */
    
    public static ArrayList getData(ArrayList al, String orderby) throws Exception {
		
		CriteriaIndustry ih = new CriteriaIndustry();
		ih.init();
		ArrayList back = ih.process(al,orderby);
		ih.close();
		return back;
	}
    
    
	public static void main(String[] args) throws Exception {

		PortfolioHelper ph = new PortfolioHelper();
		
		try {
			ph.init();
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
		ArrayList limit = ph.getPortfolio("techg02");		
		
		ArrayList back = getData(limit,	"sector");
		for (int i = 0; i < back.size(); i++) {
			Industry ind = (Industry) back.get(i);
			System.out.println(ind.getSymbol() + " " + ind.getSector() + " " + ind.getIndname());
		}
	}
}
