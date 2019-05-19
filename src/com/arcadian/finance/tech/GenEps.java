/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.tech;

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

import com.arcadian.util.EtsNumberFormat;
import com.arcadian.persist.Eps;
import com.arcadian.persist.Ks;
import com.arcadian.sql.CriteriaKs;

/**
 * Generate out the Eps data given the Ks.
 *  This will then be used by the reporting engine for more analysis.
 * 
 * @author Michael I Angerman
 */

public class GenEps {
	
	public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;
	
	ArrayList ksdata = null;
	
	private String symbol;
	private double trailingpe, epswrite, price;
	private double forwardpe, futureeps, futureprice; 
	private double pctincrease;

	public void init() throws Exception {
		
		Configuration cfg = new Configuration()
			.addClass(Eps.class)
			.addClass(Ks.class);

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
		
		ksdata = CriteriaKs.getAllDataDesc("marketcap");
		
		/*		
		for (int i = 0; i < ksdata.size(); i++) {
			Ks ks = (Ks) ksdata.get(i);
			System.out.println(ks.getSymbol() + " " + ks.getMarketcap());
		}
		*/
	}
	
	public void process() throws Exception {
		
		for (int i = 0; i < ksdata.size(); i++) {
			Ks ks = (Ks) ksdata.get(i);
			// System.out.println(ks.getSymbol() + " " + ks.getMarketcap());
			
			Eps eps = new Eps();
			
			String symbol = (String) ks.getSymbol();
			
			/**
			Step One, calculate the current stock price
			*/
			
			trailingpe = (double) ks.getTrailingpe();
			epswrite = (double) ks.getEps();
			price = trailingpe * epswrite;
			
			/**
			Step Two, calculate the the future eps
			*/
			forwardpe = (double) ks.getForwardpe();
			futureeps = price / forwardpe;
			
			/**
			Step Three, calculate the future price
			*/
			
			futureprice = trailingpe * futureeps;
			
			/**
			Step Four, calculate the pctincrease
			*/
			
			double pctincreasetmp1 = futureprice / price;
			pctincrease = pctincreasetmp1 - 1.0;
			
			eps.setSymbol(symbol);
			eps.setTrailingpe(trailingpe);
			eps.setEps(EtsNumberFormat.twoDecimalPlaces(epswrite));
			
			
			eps.setPrice(EtsNumberFormat.twoDecimalPlaces(price));
			eps.setForwardpe(forwardpe);
			eps.setFutureeps(EtsNumberFormat.twoDecimalPlaces(futureeps));
			eps.setFutureprice(EtsNumberFormat.twoDecimalPlaces(futureprice));
			eps.setPctincrease(EtsNumberFormat.threeDecimalPlaces(pctincrease));
			
			session.save(eps);
		}
	}
	
	public void close() throws Exception {
		session.close();	
	}
    
	public static void main(String[] args) throws Exception {
		GenEps ge = new GenEps();
		ge.init();
		ge.process();
        }		
}
