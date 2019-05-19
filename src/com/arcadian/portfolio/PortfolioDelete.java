/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.portfolio;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.arcadian.persist.Portfolio;
import com.arcadian.sql.CriteriaPortfolio;

/**
 * Delete a set of portfolios based on either the username or the username and the portfolioname.
 * 
 * @author Michael I Angerman
 */
public class PortfolioDelete {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(Portfolio.class);

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public void process(ArrayList pal) throws Exception {
		
		try {
			tx = session.beginTransaction();
			long begin = System.currentTimeMillis();

			for (int i = 0; i < pal.size(); i++) {
				Portfolio p = (Portfolio) pal.get(i);
				System.out.println(p.getPortfolioname());
				session.delete(p);	
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
    
    /*
	public static void main(String[] args) throws Exception {
		
		PortfolioDelete ih = new PortfolioDelete();
		ih.init();
		
		ArrayList uname1 = new ArrayList();
		uname1.add("iris");
		
		ArrayList uname2 = new ArrayList();
		uname2.add("larry");
		
		ArrayList pnamex = new ArrayList();
		pnamex.add("p102");
		
		ArrayList pnamey = new ArrayList();
		pnamey.add("p104a");

		// ArrayList pal = CriteriaPortfolio.getUserName(uname);		
		ArrayList pal1 = CriteriaPortfolio.getPortfolioName(uname1,pnamex);
		ih.process(pal1);
		
		ArrayList pal2 = CriteriaPortfolio.getPortfolioName(uname2,pnamey);
		ih.process(pal2);
		
		ih.close();
	}
	*/
	
	public static void main(String[] args) throws Exception {
		
		PortfolioDelete ih = new PortfolioDelete();
		ih.init();
		
		ArrayList uname = new ArrayList();
		uname.add("michael");
		
		ArrayList pal = CriteriaPortfolio.getUserName(uname);		
		ih.process(pal);
		
		ih.close();
	}
	
	/*
	public static void main(String[] args) throws Exception {
		
		PortfolioDelete ih = new PortfolioDelete();
		ih.init();
		
		ArrayList uname = new ArrayList();
		uname.add("michael");
		
		ArrayList pname = new ArrayList();
		pname.add("softsmall");
		
		ArrayList pal = CriteriaPortfolio.getPortfolioName(uname,pname);
		ih.process(pal);
		
		ih.close();
	}
	*/
}
