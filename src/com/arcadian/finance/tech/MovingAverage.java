/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.tech;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.arcadian.finance.util.HpIterator;
import com.arcadian.persist.Hp;
import com.arcadian.persist.Ma;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.sql.CriteriaHp;
import com.arcadian.util.EtsNumberFormat;
import com.arcadian.util.MathUtil;

/**
Use a moving average to iterate through the different parameters in Hp (Volume, Adjclose).
*/

public class MovingAverage {

     Hp[] hp = null;
     Date date = null;
     double adjclose = 0.0;
     int numofdays = 0;
     int srcPos = 0;
     String symbol = null;
     
     // assume moving average > adjclose
     boolean magtac = true;  
     
     public SessionFactory factory = null;
	public static Session session = null;
	public static Transaction tx=null;

	public void init() throws Exception {
		Configuration cfg = new Configuration().addClass(Ma.class);
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
     
     
     public Date getDate() {
	return date;     
     }
     
     public double getAdjclose() {
	return adjclose;
     }
     
     public int getSrcPos() {
	return srcPos;     
     }
     
     public String getSymbol() {
	return symbol;     
     }
     
     public void setSymbol(String s) {
	     symbol = s;
     }
     
     public int getNumberOfDays() {
	return numofdays;     
     }
     
     public void setNumberOfDays(int x) {
	numofdays = x;
     }
     
     
     public Hp[] getHpArray() {
	return hp;     
     }
     
     public void setHpArray(Hp[] d) {
	hp = d;
     }
     
     private double[] getAdjcloseArray() {
	double[] src = new double[numofdays];
	int count = 0;
	int size = srcPos + numofdays;
	for (int i=srcPos; i < size; i++) {
		src[count++] = hp[i].getAdjclose();
	}
	
	date = hp[size - 1].getDate();
	adjclose = hp[size - 1].getAdjclose();
	return src;
     }
     
     public double[] getNextAdjcloseArray() {
	     
	     int currentValue = srcPos + numofdays;
	     if (currentValue > hp.length) return null;
	     
	     double[] src = getAdjcloseArray();
	     srcPos = srcPos + 1;
	     return src;
     }
     
     /**
     return a true if magtac changes from one state to the other
     */
     
     public boolean magtacTest(double ma, double ac) {
	     boolean magtac2 = true;
	     if (ac > ma) magtac2 = false;
		     
	     if (magtac2 != magtac) {
		     // System.out.println(ma + " " + ac + " true");
		     
		     if (magtac == true) {
			     magtac = false;
			     return true;
		     }
		     magtac = true;
		     return true;
	     }
	     // System.out.println(ma + " " + ac + " false");
	     return false;
     }
     
     
    public Ma[] getMaArray() {
	Vector vec = new Vector();
	
	double[] ac = null;
	HpIterator hpi = new HpIterator();
	hpi.setHpArray(getHpArray());
	hpi.setNumberOfDays(getNumberOfDays());
	    
	while ((ac = hpi.getNextAdjCloseArray()) != null) {
		    double movingAverage1 = MathUtil.average(ac);
		    double movingAverage = EtsNumberFormat.twoDecimalPlaces(movingAverage1);
			
		    boolean t1 = magtacTest(movingAverage,hpi.getAdjClose());
		    
		    // System.out.println(t1 + " " + hpi.getDate() + " " + movingAverage + " " + hpi.getAdjClose());
			
		    if (t1) {
		    	Ma dacmad = new Ma();
			dacmad.setDate(hpi.getDate());
			dacmad.setDays(getNumberOfDays());
			dacmad.setAdjclose(hpi.getAdjClose());
			
			double back = EtsNumberFormat.twoDecimalPlaces(movingAverage);
			dacmad.setMa(back);
			dacmad.setSymbol(getSymbol());
			vec.add(dacmad);
		    }
	}    
	    
	int numofdacmads = vec.size();
	Ma[] dma = new Ma[numofdacmads];
	    
	for (int i = 0; i < numofdacmads; i++) {
		dma[i] = (Ma) vec.get(i);    
	}
	
	return dma;
    }
    
    // Put data into the database
    
    public static void main(String[] args) throws Exception {

	    PortfolioHelper ph = new PortfolioHelper();
	    ph.init();
	    ArrayList al = ph.getPortfolio("techg00");
	    
	    MovingAverage ma = new MovingAverage();
	    ma.init();
	    
	    // For each symbol in the database do the following...
	    for (int i = 0; i < al.size(); i++) {
		    String symbol = (String) al.get(i);
		    
		    System.out.println(symbol);
		    
		    ArrayList altmp = null;
		    altmp = new ArrayList();
		    altmp.add(symbol);
		    Hp hp[] = CriteriaHp.getDataAsc(altmp);
		    
		    ma.setHpArray(hp);
		    ma.setSymbol(symbol);
		    
		    // number of days
		    int nod = Integer.parseInt(args[0]); 
		    ma.setNumberOfDays(nod);
		    
		    Ma[] dma = ma.getMaArray();
		    
		    for (int k = 0; k < dma.length; k++) {
			    dma[k].print();
			    session.save(dma[k]);
		    }
	    }
    }
}
