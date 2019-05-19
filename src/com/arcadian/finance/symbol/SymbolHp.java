/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.symbol;

import com.arcadian.persist.Hp;

/**
Contains a symbol and an array of Hp's
*/

public class SymbolHp {

    public String symbol = "";
    Hp[] hpa = null;

    public void setSymbol(String x) {
        symbol = x;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setHpa(Hp[] hp) {
        hpa = hp;
    }

    public Hp[] getHpa() {
        return hpa;
    }
    
    public void print() {
        System.out.println("--------------- " + symbol + " ----------------");
        for (int i = 0; i < hpa.length; i++) {
		hpa[i].dumpData();
        }
    }
}

