/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

/**
Remove whitespace based on Character.isWhiteSpace().
*/

public class RemoveWhiteSpace {
    
    public static String simple(String s) {
	    char[] x = s.toCharArray();
	    char[] y = new char[x.length];
	    int count = 0;
	    
	    for (int i = 0; i < x.length; i++) {
		boolean v = Character.isWhitespace(x[i]);
		
		if (!v) {
			y[count++] = x[i];	
		}
	    }
	    
	    char[] z = new char[count];
	    
	    System.arraycopy(y,0,z,0,count);
	    
	    String back = new String(z);
	    return back;
    }
    
    public static char[] simple(char[] x) {
	    char[] y = new char[x.length];
	    int count = 0;
	    
	    for (int i = 0; i < x.length; i++) {
		boolean v = Character.isWhitespace(x[i]);
		
		if (!v) {
			y[count++] = x[i];	
		}
	    }
	    
	    char[] z = new char[count];
	    
	    System.arraycopy(y,0,z,0,count);
	    
	    return z;
    }
    
    public static void main(String[] args) {
	    
	    String t1 = "j  h  n  x";
	    String t2 = RemoveWhiteSpace.simple(t1);
	    System.out.println(t2);
	    
	    String t3 = "y  r   u   ";
	    char[] x = t3.toCharArray();
	    char[] y = simple(x);
	    
	    String t4 = new String(y);
	    System.out.println(t4);
    }
}

