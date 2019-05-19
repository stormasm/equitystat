/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.symbol;

import java.util.ArrayList;
import java.util.Stack;

/**
This stores an ArrayList of SymbolArrayLists
*/

public class SymbolArrayListContainer {

	public Stack stack = null;
	public ArrayList top = null;

    public void init() {
        top = new ArrayList();
	stack = new Stack();
    }

    public void addSymbol(String x) {
	    SymbolArrayList sal = new SymbolArrayList();
	    sal.setSymbol(x);
	    stack.push(sal);
    }

    public void addParam(String x) {
	    SymbolArrayList sal = (SymbolArrayList) stack.pop();
	    sal.addParam(x);
	    stack.push(sal);
    }
    
    public ArrayList getArrayList() {
	while(!stack.empty()) {
		SymbolArrayList sal = (SymbolArrayList) stack.pop();
		top.add(sal);
	}
	return top;
    }

    public static void main(String[] args) {
	    	SymbolArrayListContainer salc = new SymbolArrayListContainer();
		salc.init();
		salc.addSymbol("Cars");
		salc.addParam("Ford");
		salc.addParam("GM");
		salc.addSymbol("Software");
		salc.addParam("Microsoft");
		salc.addParam("Oracle");
		salc.addParam("Siebel");
		ArrayList al = salc.getArrayList();
		
		for (int i = 0; i < al.size(); i++) {
			SymbolArrayList sal = (SymbolArrayList) al.get(i);
			sal.print();
		}
    }
}
