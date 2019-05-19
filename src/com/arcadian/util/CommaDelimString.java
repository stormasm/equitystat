/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

import java.util.ArrayList;

/**
Take a comma delimited String and create an ArrayList. 
*/

public abstract class CommaDelimString {

  public  static ArrayList getArrayList(String input) {

	  ArrayList al = new ArrayList();
	  String[] sa = input.split(",");
	  
	  for (int i = 0; i < sa.length; i++) {
		  String tmp1 = sa[i];
		  String tmp2 = tmp1.trim();
		  al.add(tmp2);
	  }
	  return al;
  }

public static void main(String[] args) {

	String t01 = new String("aapl, sebl, sunw, msft");
	
	ArrayList al = CommaDelimString.getArrayList(t01);
	
	for (int i = 0; i < al.size(); i++) {
		System.out.print(al.get(i));
		System.out.print(" " );
	}
    }
}
