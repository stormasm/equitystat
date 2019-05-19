/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.symbol;

import java.util.ArrayList;

/**
A SymbolArrayList which has a symbol and an ArrayList
*/

public class SymbolArrayList {

    public String symbol = "";
    public ArrayList al = null;

    public SymbolArrayList() {
        init();
    }

    public void init() {
        al = new ArrayList();
    }

    public void setSymbol(String x) {
        symbol = x;
    }

    public String getSymbol() {
        return symbol;
    }

    public void addParam(String value) {
        al.add(value);
    }
    
    public ArrayList getArrayList() {
	return al;    
    }
    
    public void print() {
	System.out.println(symbol);
	for (int i = 0; i < al.size(); i++) {
		String x = (String) al.get(i);
		System.out.println("         " + x);
	}
    }
}
