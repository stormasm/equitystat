/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.symbol;

import java.util.List;

/**
A SymbolList which has a symbol and a List
*/

public class SymbolList {

    public String symbol = "";
    public List list = null;

    public void setSymbol(String x) {
        symbol = x;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setList(List value) {
	    list = value;
    }
    
    public List getList() {
	return list;    
    }
}
