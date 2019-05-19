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

/**
 * Insert new Portfolio data into a database given a username, portfolioname, and an ArrayList of symbols.
 * 
 * @author Michael I Angerman
 */
public class PortfolioAdd {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(Portfolio.class);

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public void process(String username, String portfolioname, ArrayList symbols) throws Exception {
		
		try {
			tx = session.beginTransaction();
			long begin = System.currentTimeMillis();

			Portfolio p = new Portfolio();
			p.setUsername(username);
			p.setPortfolioname(portfolioname);
			p.setSymbols(symbols);
			session.save(p);

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
    
    public static void create(String username, String portfolioname, ArrayList symbols) {
	    	PortfolioAdd ih = new PortfolioAdd();
		try {
			ih.init();
			ih.process(username,portfolioname,symbols);
			ih.close();
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
    }
/*    
	public static void main(String[] args) throws Exception {
		
		PortfolioAdd ih = new PortfolioAdd();
		ih.init();
		
		ArrayList ala = new ArrayList();
		ala.add("sebl");
		ala.add("sunw");
		ala.add("eclg");
		
		ArrayList alb = new ArrayList();
		alb.add("ibm");
		alb.add("hp");
		alb.add("dell");
		
		ArrayList alc = new ArrayList();
		alc.add("f");
		alc.add("gm");
		alc.add("toy");
		
		ArrayList ald = new ArrayList();
		ald.add("wmt");
		ald.add("ge");
		ald.add("csco");
		
		ArrayList ale = new ArrayList();
		ale.add("adbe");
		ale.add("akam");
		ale.add("prtl");
		
		ih.process("iris","p101",ala);
		ih.process("iris","p102",alb);
		ih.process("iris","p103",alc);
		
		ih.process("larry","p104a",ald);
		ih.process("larry","p104b",ale);
		
		ih.close();
	}
*/

	public static void main(String[] args) throws Exception {
		
		PortfolioAdd ih = new PortfolioAdd();
		ih.init();
		ArrayList al = new ArrayList();
		al.add("slr");
		al.add("sanm");
		al.add("jbl");
		al.add("flex");
		al.add("cls");
		ih.process("michael","ems",al);
		ih.close();
	}

}
