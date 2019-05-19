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
Given two ArrayLists a Src and Tgt with Strings in them, return an ArrayList of Strings
that are in the Src and not in the Tgt.  If all of the Strings in Src are in the Target then
return an ArrayList with no items in it.
<ul>
<li>
At this point one can assume that there are NO duplicates in the src or tgt,
and if there are the program will exit and list the duplicates.
</li>
</li>
</ul>
*/

public abstract class IntersectArrayLists {

	public static ArrayList intersect(ArrayList src, ArrayList tgt) {
		
		ArrayList back = new ArrayList();
		
		int numsrc = src.size();
		int numtgt = tgt.size();
	
		if(duplicates(src)) {
			System.out.println("There are duplicates in the src ArrayList");
			System.exit(501);
		}
		
		if(duplicates(tgt)) {
			System.out.println("There are duplicates in the tgt ArrayList");
			System.exit(502);
		}
		
		System.out.println("Src has " + numsrc + " Strings with no duplicates");
		System.out.println("Tgt has " + numtgt + " Strings with no duplicates");
		
		if (numsrc == numtgt) {
			boolean val1 = same(src,tgt,1,back);	
			boolean val2 = same(tgt,src,2,back);
			
			if(val1 & val2) {
				System.out.println("There is an exact match between the src and tgt ArrayLists");	
			}
		}
		
		if (numsrc > numtgt) {
			diff(src,tgt,1,back);	
		}
		
		if (numtgt > numsrc) {
			diff(tgt,src,2,back);
		}
		
		return back;
	}


	/**
		Return a false if there are no duplicate Strings in this ArrayList,
		Return a true if there are duplicate Strings and print out which Strings are duplicated
	*/
	
	public static boolean duplicates(ArrayList src) {
		
		HashMap hm = new HashMap();
		HashMap hmdup = new HashMap();
		
		// This flag gets set to true if a duplicate is found
		boolean flag = false;
		
	    	for (int i = 0; i < src.size(); i++) {
			String x = (String) src.get(i);
			
			if (hm.containsKey(x) == false) {
				hm.put(x,"hit");	
			}
			else {
				flag = true;
				hmdup.put(x,"dup");
		    }
		}
		
		if (flag == false) {
			return false;	
		}
		else {
			Set s = hmdup.keySet();
			for (Iterator k = hmdup.keySet().iterator(); k.hasNext();) {
				String key = (String) k.next();
				System.out.println(key);
			}	
		}
		return true;
	}
	
	/**
		The number of Strings in the ArrayLists are the same, 
		and there are no duplicates in either ArrayList
		return a TRUE if their is a match across the set.
	*/
	
	public static boolean same(ArrayList src, ArrayList tgt, int iteration,ArrayList back) {
		HashMap hmsrc = new HashMap();
		HashMap hmtgt = new HashMap();
		boolean flag = true;
		
	    	// put all of the Strings from the src ArrayList into a HashMap
	    	for (int i = 0; i < src.size(); i++) {
			String x = (String) src.get(i);
			hmsrc.put(x,"hit");	
		}
		
		// put all of the Strings from the tgt ArrayList into a HashMap
	    	for (int i = 0; i < tgt.size(); i++) {
			String y = (String) tgt.get(i);
			hmtgt.put(y,"hit");	
		}
	    
	    	Set setsrc = hmsrc.keySet();
		for (Iterator k = hmsrc.keySet().iterator(); k.hasNext();) {
		    String key = (String) k.next();
		    
		    if (hmtgt.containsKey(key) == false) {
			    flag = false;
			    if (iteration == 1) {
				    System.out.println(key + " is not in the target");
				    back.add(key);
			    } 
		    }
		}
		
		if (flag) return true;
		return false;
	}
	
	/**
		The number of Strings in the src ArrayList is greater than, 
		the number of Strings in the tgt ArrayList
	*/
	
	public static void diff(ArrayList src, ArrayList tgt, int iteration, ArrayList back) {
		
		HashMap hmsrc = new HashMap();
		HashMap hmtgt = new HashMap();
		
	    	// put all of the Strings from the src ArrayList into a HashMap
	    	for (int i = 0; i < src.size(); i++) {
			String x = (String) src.get(i);
			hmsrc.put(x,"hit");	
		}
		
		// put all of the Strings from the tgt ArrayList into a HashMap
	    	for (int i = 0; i < tgt.size(); i++) {
			String y = (String) tgt.get(i);
			hmtgt.put(y,"hit");	
		}
	    
	    	Set setsrc = hmsrc.keySet();
		for (Iterator k = hmsrc.keySet().iterator(); k.hasNext();) {
		    String key = (String) k.next();
		    
		    if (hmtgt.containsKey(key) == false) {
			    if (iteration == 1) {
				    System.out.println(key + " is not in the target");
				    back.add(key);
			    } 
		    }
		}
		
	    	Set settgt = hmtgt.keySet();
		for (Iterator m = hmtgt.keySet().iterator(); m.hasNext();) {
		    String key = (String) m.next();
		    
		    if (hmsrc.containsKey(key) == false) {
			    if (iteration == 2) {
				    System.out.println(key + " is not in the target");
				    back.add(key);
			    } 
		    }
		}
	}
	
    public static void main(String[] args) {
	    
	    ArrayList src = new ArrayList();
	    ArrayList tgt = new ArrayList();

	    src.add("sebl");
	    src.add("sunw");
	    src.add("aapl");	    
//	    src.add("intc");
//	    src.add("ibm");
	    
	    tgt.add("sebl");
	    tgt.add("sunw");
	    tgt.add("aapl");
	    tgt.add("intc");
	    tgt.add("ibm");
	
	    ArrayList al = intersect(src,tgt);
	    
	    if (al.size() == 0) {
		System.out.println("All Strings in the Src are in the Tgt");    
	    }
	    else {
		    System.out.println("The following Strings in the Src are NOT in the Tgt");
		    for (int i = 0; i < al.size(); i++) {
			    String x = (String) al.get(i);
			    System.out.println(al.get(i));    
		    }
	    }
    }
}
