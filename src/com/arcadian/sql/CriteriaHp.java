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
import com.arcadian.persist.Hp;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.reader.KsReader;

/**
 * Get Hp data from the Database given a particular symbol.
 * 
 * @author Michael I Angerman
 */

public class CriteriaHp {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration().addClass(Hp.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	/**
	*
	*        This is for Descending Order
	*
	*/
	
	public ArrayList processDesc(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Hp.class)
			.add( Restrictions.in("symbol", limit))
			.addOrder(Order.desc("date"))
			.list();
			
			al = (ArrayList) ksx;
			
			long end = System.currentTimeMillis();
			tx.commit();	
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return al;
	}
	
	/**
	*
	*        This is for Ascending Order
	*
	*/
	
	public ArrayList processAsc(ArrayList limit) throws Exception {
		
		ArrayList al = null;
		
		try {
			tx = session.beginTransaction();
		
			long begin = System.currentTimeMillis();
		
			List ksx = session.createCriteria(Hp.class)
			.add( Restrictions.in("symbol", limit))
			.addOrder(Order.asc("date"))
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
    */
    
    public static ArrayList getDataDescArrayList(ArrayList al) throws Exception {
		
		CriteriaHp ih = new CriteriaHp();
		ih.init();
		ArrayList back = ih.processDesc(al);
		ih.close();
		return back;
	}
	
     /**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static ArrayList getDataAscArrayList(ArrayList al) throws Exception {
		
		CriteriaHp ih = new CriteriaHp();
		ih.init();
		ArrayList back = ih.processAsc(al);
		ih.close();
		return back;
	}
	
	/**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static Hp[] getDataDesc(ArrayList al) throws Exception {
		
		CriteriaHp ih = new CriteriaHp();
		ih.init();
		ArrayList back = ih.processDesc(al);
		ih.close();
		
		int size = back.size();
		Hp[] hp = new Hp[size];
		
		for (int i = 0; i < size; i++) {
			hp[i] = (Hp) back.get(i);	
		}
		
		return hp;
	}
	
     /**
    Pass in an ArrayList of symbols you want to get back
    */
    
    public static Hp[] getDataAsc(ArrayList al) throws Exception {
		
		CriteriaHp ih = new CriteriaHp();
		ih.init();
		ArrayList back = ih.processAsc(al);
		ih.close();
		
		int size = back.size();
		Hp[] hp = new Hp[size];
		
		for (int i = 0; i < size; i++) {
			hp[i] = (Hp) back.get(i);	
		}
		
		return hp;
	}
	
	public static void main(String[] args) throws Exception {
		
		ArrayList al = new ArrayList();
		al.add("yhoo");
		
		Hp hp[] = CriteriaHp.getDataAsc(al);
		
		for (int i = 0; i < hp.length; i++) {
			System.out.println(hp[i].getSymbol() + " " + hp[i].getDate() + " " + hp[i].getAdjclose());
		}
	}
	
	/*
	public static void main(String[] args) throws Exception {
		
		ArrayList al = new ArrayList();
		al.add("yhoo");
		
		ArrayList back = CriteriaHp.getDataAscArrayList(al);
		
		for (int i = 0; i < back.size(); i++) {
			Hp hp = (Hp) back.get(i);
			System.out.println(hp.getSymbol() + " " + hp.getDate() + " " + hp.getAdjclose());
		}
	}
	*/
	
}
