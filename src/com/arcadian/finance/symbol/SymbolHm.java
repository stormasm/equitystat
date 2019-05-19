/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
A SymbolHashMap which has a symbol and HashMap to store symbol-value pairs.
The key in the HashMap is a String but,
the value in the HashMap can either be a String or an ArrayList.

Functions to support both String's as values and ArrayList's as values are supported
in this diverse class. 
*/

public class SymbolHm {

    public String symbol = "";
    HashMap hm = null;

    public SymbolHm() {
        init();
    }

    public void init() {
        hm = new HashMap();
    }

    public void setSymbol(String x) {
        symbol = x;
    }

    public String getSymbol() {
        return symbol;
    }

    public void addArrayList(String key, ArrayList value) {
	hm.put(key,value);    
    }
    
    public void addParam(String key, String value) {
        hm.put(key,value);
    }

    public String getParam(String key) {
        String x = (String) hm.get(key);
        return x;
    }
    
    public HashMap getHashMap() {
	return hm;    
    }

    public void print() {
        Set s = hm.keySet();
        System.out.println("--------------- " + symbol + " ----------------");
        for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            String value = (String) hm.get(key);
            System.out.println(key + " " + value);
        }
    }
    
    public void printArrayList() {
        Set s = hm.keySet();
        System.out.println("--------------- " + symbol + " ----------------");
        for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            ArrayList value = (ArrayList) hm.get(key);
            System.out.println(key);
	    for (int j = 0; j < value.size(); j++) {
		System.out.println("     " + value.get(j));    
	    }
        }
    }
}
