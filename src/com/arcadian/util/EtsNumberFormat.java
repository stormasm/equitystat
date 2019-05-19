/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
Some common routines that get applied to java.text.DecimalFormats.
*/

public abstract class EtsNumberFormat {
    
    public static String removeCommas(String x) 
    { 
        DecimalFormat df = new DecimalFormat();
	Number n = null;
	try { 
		n = df.parse(x);
	}
	catch (ParseException pe) {
		pe.printStackTrace();	
	}
	
	double d = n.doubleValue();
	String s = String.valueOf(d);
	// System.out.println(s);
	return s;
    }
    
    // return a String with 2 places after the decimal

    public static String twoDecimalPlaces(String x) {
        DecimalFormat df = new DecimalFormat("##.##");
        Double d = new Double(x);
        double dval = d.doubleValue();
        df.setMinimumFractionDigits(2);
        String modified = df.format(dval);
        return modified;
    }
    
    public static double twoDecimalPlaces(double x) {
        DecimalFormat df = new DecimalFormat("##.##");
        Double d = new Double(x);
        double dval = d.doubleValue();
        df.setMinimumFractionDigits(2);
        String modified = df.format(dval);
	double dback = Double.parseDouble(modified);
        return dback;
    }
    
    public static double threeDecimalPlaces(double x) {
        DecimalFormat df = new DecimalFormat("##.###");
        Double d = new Double(x);
        double dval = d.doubleValue();
        df.setMinimumFractionDigits(2);
        String modified = df.format(dval);
	double dback = Double.parseDouble(modified);
        return dback;
    }

    // return a String with 3 places after the decimal

    public static String threeDecimalPlaces(String x) {

        DecimalFormat df = new DecimalFormat("##.###");
        Double d = new Double(x);
        double dval = d.doubleValue();
        df.setMinimumFractionDigits(3);
        String modified = df.format(dval);
        return modified;
    }
    
    public static String decimalToPercent(String x) {
        DecimalFormat df = new DecimalFormat("##.#");
        Double d = new Double(x);
        double dval = d.doubleValue() * 100.0;
        df.setMinimumFractionDigits(1);
        String modified = df.format(dval);
	String modified1 = modified.concat("%");
        return modified1;
    }
    
    public static void main(String[] args) {
	    // EtsNumberFormat.removeCommas("1,234.56");
	    
	    String t = EtsNumberFormat.decimalToPercent(".65932");
	    System.out.println(t);
    }
}
