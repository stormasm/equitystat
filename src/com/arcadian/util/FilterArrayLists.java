/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
An abstract class which filters String data stored in ArrayLists.
This abstract class only has static methods.
*/

public abstract class FilterArrayLists {

	
/**
Given two ArrayLists with Strings in them, 
keep all of the Strings in the Target that
are located in the Source.
*/
	
    public static ArrayList filter(ArrayList src, ArrayList tgt) {
	    
	    HashMap hm = new HashMap();
	    ArrayList back = new ArrayList();
	    
	    for (int i = 0; i < src.size(); i++) {
		hm.put(src.get(i),"hit");    
	    }
	
	    for (int j = 0; j < tgt.size(); j++) {
		    String test = (String) tgt.get(j);
	             
		     if (hm.containsKey(test) == true) {
			back.add(test);     
		     }
	    }
	
	    return back;
    }
    
/**
Given two ArrayLists with Strings in them, 
remove all of the Strings in the Target that
are located in the Source.
*/

    public static ArrayList remove(ArrayList src, ArrayList tgt) {
	    
	    HashMap hm = new HashMap();
	    ArrayList back = new ArrayList();
	    
	    for (int i = 0; i < src.size(); i++) {
		hm.put(src.get(i),"hit");    
	    }
	
	    for (int j = 0; j < tgt.size(); j++) {
		    String test = (String) tgt.get(j);
	             
		     if (hm.containsKey(test) == false) {
			back.add(test);     
		     }
	    }
	
	    return back;
    }
    
    public static void main(String[] args) {
	    
	    ArrayList al00 = new ArrayList();
	    ArrayList al01 = new ArrayList();
	 
	    al00.add("aapl");
	    al00.add("sebl");
	    al00.add("sunw");
	    
	    al01.add("ford");
	    al01.add("sebl");
	    al01.add("wmt");
	    al01.add("mcd");
	    al01.add("aapl");
	    al01.add("ibm");
	    
	    System.out.println("-------------------  filter test ---------------------");
	    
	    ArrayList alx = filter(al00,al01);
	    
	    for (int i = 0; i < alx.size(); i++) {
		String x = (String) alx.get(i);
		System.out.println(x);
	    }
	    
	    System.out.println("-------------------  remove test ---------------------");
	    
	    ArrayList aly = remove(al00,al01);
	    
	    for (int i = 0; i < aly.size(); i++) {
		String y = (String) aly.get(i);
		System.out.println(y);
	    }
    }
}
