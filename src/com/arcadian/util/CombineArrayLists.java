/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
Given a set of ArrayLists with Strings in them, combine them all into one ArrayList
and remove the duplicate Strings, and then return one ArrayList
*/

public abstract class CombineArrayLists {
    
    public static ArrayList combine(Vector input) {
	    
	    HashMap hm = new HashMap();
	    ArrayList back = new ArrayList();
	    
	    ArrayList[] al = new ArrayList[input.size()];
	    
	    for (int i = 0; i < input.size(); i++) {
		    al[i] = (ArrayList) input.get(i);
		    for (int j = 0; j < al[i].size(); j++) {
			String x = (String) al[i].get(j);
			
			if (hm.containsKey(x) == false) {
				hm.put(x,"hit");	
			}
		    }
	    }
	    
	    Set s = hm.keySet();
	    for (Iterator k = hm.keySet().iterator(); k.hasNext();) {
		    String key = (String) k.next();
		    back.add(key);
	    }
	    return back;
    }
    
    public static void main(String[] args) {
	    
	    ArrayList al00 = new ArrayList();
	    ArrayList al01 = new ArrayList();
	    ArrayList al02 = new ArrayList();
	 
	    al00.add("aapl");
	    al00.add("sebl");
	    al01.add("sunw");
	    al01.add("vitr");
	    al01.add("aapl");
	    al02.add("intc");
	    al02.add("sebl");
	    
	    Vector vec = new Vector();
	    vec.add(al00);
	    vec.add(al01);
	    vec.add(al02);
	    
	    ArrayList al = combine(vec);
	    
	    for (int i = 0; i < al.size(); i++) {
		String x = (String) al.get(i);
		System.out.println(x);
	    }
    }
}

