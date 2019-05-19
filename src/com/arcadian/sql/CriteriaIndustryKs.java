/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.sql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;

import com.arcadian.persist.IndustryKs;
import com.arcadian.portfolio.PortfolioHelper;

/**
 * Get IndustryKs data from the Database given an ArrayList of symbols.
 * 
 * @author Michael I Angerman
 */

public class CriteriaIndustryKs {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(IndustryKs.class);

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public ArrayList process(ArrayList limit, Criterion se, String orderby) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(IndustryKs.class)
			.add( Restrictions.in("symbol", limit))
			.add(se)
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
    
    public static ArrayList getData(ArrayList al, Criterion se, String orderby) throws Exception {
		
		CriteriaIndustryKs ih = new CriteriaIndustryKs();
		ih.init();
		ArrayList back = ih.process(al,se,orderby);
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

		ArrayList alsector = ph.getSector("Technology");
		ArrayList alindcode = ph.getIndcode("821");
		
		Criterion c1 = Restrictions.gt("marketcap", new Double(80000.0));
		Criterion c2 = Restrictions.between("marketcap", new Double(10000.0),  new Double(20000.0));
		
		ArrayList back = getData(alindcode,c2,"marketcap");
		
		for (int i = 0; i < back.size(); i++) {
			IndustryKs ind = (IndustryKs) back.get(i);
			System.out.println(ind.getSymbol() + " " + ind.getMarketcap());
		}
		
		ph.close();
	}
}
