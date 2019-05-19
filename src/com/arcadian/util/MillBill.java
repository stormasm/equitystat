/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

import org.apache.commons.lang.math.NumberUtils;

/**
Convert data between Millions and Billions and to Millions and Billions.
*/

public abstract class MillBill {
    
    public static String convertToBill(String tvalue) {
	String x = convertToBill_nt(tvalue);
	String y = EtsNumberFormat.threeDecimalPlaces(x);
	return y;
    }
    
    public static String convertToMill(String tvalue) {
	String x = convertToMill_nt(tvalue);
	String y = EtsNumberFormat.threeDecimalPlaces(x);
	return y;
    }
    
    	public static String convertToBill_nt(String tvalue) {
		int index = 0;
		
		String value = tvalue.trim();
		
		
		if((index = value.indexOf("B")) > 0) {
			String valx0 = value.substring(0,index);
			// System.out.println(index + " " + value + " " + valx0);
			Double valx1 = new Double(valx0);
			return valx1.toString();
		}
		
		if((index = value.indexOf("M")) > 0) {
			String valk0 = value.substring(0,index);
			
			if (NumberUtils.isNumber(valk0) == false) {
				return value;	
			}
			
			// System.out.println(index + " " + value + " " + valk0);
			Double valk1 = new Double(valk0);
			double valk2 = valk1.doubleValue();
			double valk3 = valk2 / 1000.0;
			return String.valueOf(valk3);
		}
		
		if((index = value.indexOf("K")) > 0) {
			String valk0 = value.substring(0,index);
			// System.out.println(index + " " + value + " " + valk0);
			Double valk1 = new Double(valk0);
			double valk2 = valk1.doubleValue();
			double valk3 = valk2 / 1000000.0;
			return String.valueOf(valk3);
		}
		
		// System.out.println(value);		
		if (NumberUtils.isNumber(value) == false) {
			// System.out.println(value + " is NOT a number");
			return value;	
		}
		
		Double val = new Double(value);
		return val.toString();
	}
	
	public static String convertToMill_nt(String tvalue) {
		int index = 0;
		
		String value = tvalue.trim();
		
		if((index = value.indexOf("B")) > 0) {
			String valx0 = value.substring(0,index);
			
			if (NumberUtils.isNumber(valx0) == false) {
				return value;	
			}
			
			Double valx1 = new Double(valx0);
			double valx2 = valx1.doubleValue();
			double valx3 = valx2 * 1000.0;
			return String.valueOf(valx3);
		}
		
		if((index = value.indexOf("M")) > 0) {
			String valk0 = value.substring(0,index);
			
			if (NumberUtils.isNumber(valk0) == false) {
				return value;	
			}
			
			// System.out.println(index + " " + value + " " + valk0);
			Double valk1 = new Double(valk0);
			double valk2 = valk1.doubleValue();
			return String.valueOf(valk2);
		}
		
		if((index = value.indexOf("K")) > 0) {
			String valk0 = value.substring(0,index);
			// System.out.println(index + " " + value + " " + valk0);
			Double valk1 = new Double(valk0);
			double valk2 = valk1.doubleValue();
			double valk3 = valk2 / 1000.0;
			return String.valueOf(valk3);
		}
		
		// System.out.println(value);		
		if (NumberUtils.isNumber(value) == false) {
			// System.out.println(value + " is NOT a number");
			return value;	
		}
		
		Double val = new Double(value);
		return val.toString();
	}
	
    
    public static void main(String[] args) {
	    String x1 = "12.34B", x2 = "122.34M", x3 = "969696.34K";
	    
	    String y1x = MillBill.convertToBill_nt(x1);
	    String y2x = MillBill.convertToBill_nt(x2);
	    String y3x = MillBill.convertToBill_nt(x3);

	    String y1 = EtsNumberFormat.threeDecimalPlaces(y1x);
	    String y2 = EtsNumberFormat.threeDecimalPlaces(y2x);
	    String y3 = EtsNumberFormat.threeDecimalPlaces(y3x);

	    System.out.println(x1 + " " + y1);
	    System.out.println(x2 + " " + y2);
	    System.out.println(x3 + " " + y3);

	    String q1x = MillBill.convertToMill_nt(x1);
	    String q2x = MillBill.convertToMill_nt(x2);
	    String q3x = MillBill.convertToMill_nt(x3);
	    
	    String q1 = EtsNumberFormat.threeDecimalPlaces(q1x);
	    String q2 = EtsNumberFormat.threeDecimalPlaces(q2x);
	    String q3 = EtsNumberFormat.threeDecimalPlaces(q3x);
	    
	    System.out.println(x1 + " " + q1);
	    System.out.println(x2 + " " + q2);
	    System.out.println(x3 + " " + q3);
	    
	    String a1x = MillBill.convertToBill(x1);
	    String a2x = MillBill.convertToBill(x2);
	    String a3x = MillBill.convertToBill(x3);
	    
	    System.out.println();
	    System.out.println();
	    
	    System.out.println(x1 + " " + a1x);
	    System.out.println(x2 + " " + a2x);
	    System.out.println(x3 + " " + a3x);
	    
	    String a4x = MillBill.convertToMill(x1);
	    String a5x = MillBill.convertToMill(x2);
	    String a6x = MillBill.convertToMill(x3);
	    
	    System.out.println(x1 + " " + a4x);
	    System.out.println(x2 + " " + a5x);
	    System.out.println(x3 + " " + a6x);
    }
}

