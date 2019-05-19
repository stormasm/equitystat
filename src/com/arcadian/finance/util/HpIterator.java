/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.util;

import java.util.ArrayList;
import java.util.Date;

import com.arcadian.persist.Hp;
import com.arcadian.sql.CriteriaHp;
import com.arcadian.util.EtsNumberFormat;
import com.arcadian.util.MathUtil;

/**
Iterate through the parameters in Hp
*/

public class HpIterator {

     Hp[] hp = null;
     Date date = null;
     double adjclose = 0.0;
     int numofdays = 0;
     int srcPos = 0;

     public Date getDate() {
	return date;     
     }
     
     public double getAdjClose() {
	return adjclose;
     }
     
     public int getSrcPos() {
	return srcPos;     
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
     
     private double[] getAdjCloseArray() {
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
     
     public double[] getNextAdjCloseArray() {
	     
	     int currentValue = srcPos + numofdays;
	     if (currentValue > hp.length) return null;
	     
	     double[] src = getAdjCloseArray();
	     srcPos = srcPos + 1;
	     return src;
     }
     
    public static void main(String[] args) throws Exception {
	    
	    ArrayList al = new ArrayList();
	    al.add("yhoo");
	    
	    Hp hp[] = CriteriaHp.getDataAsc(al);
	    
	    double[] ac = null;
	    HpIterator hpi = new HpIterator();
	    hpi.setHpArray(hp);
	    hpi.setNumberOfDays(50);
	    
	    while ((ac = hpi.getNextAdjCloseArray()) != null) {
		    double movingAverage = MathUtil.average(ac);
		    String mas = Double.toString(movingAverage);
		    String mas1 = EtsNumberFormat.twoDecimalPlaces(mas);
		    int tmpcv = hpi.srcPos + hpi.numofdays;
		    System.out.println(tmpcv + " " + hpi.getDate() + " " + mas1 + " " + hpi.getAdjClose());
	    }
    }
}
