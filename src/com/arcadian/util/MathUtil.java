/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

/**
Some simple Math utilities including calculating averages
*/

public abstract class MathUtil {

/**
Given a double array return the average  
*/	
	
    public static double average(double[] x) {

	    int sizei = x.length;
	    double size = (double) sizei;
	    double sum = 0.0;
	    
	    for (int i = 0; i < size; i++) {
		    sum = sum + x[i];
	    }
	    
	    double val = sum / size;
	    return val;
    }
    
/**
Given an integer array return the average
*/	
	
    public static int average(int[] x) {

	    /**
	    A long type is required to sum up the volume.  If you only use an int
	    then when you sum up companies with large volumes like csco, msft,
	    orcl, sunw, then the int overflows and you get a negative result.
	    */
	    
	    long size = x.length;
	    long sum = 0;
	    
	    for (int i = 0; i < size; i++) {
		    sum = sum + x[i];
	    }
	    
	    long valx = sum / size;
	    int val = (int) valx;
	    return val;
    }
}
