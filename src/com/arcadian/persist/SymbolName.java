/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 * SymbolName
 * @author Michael I Angerman
 */
 
public class SymbolName extends Persistent {
	private String symbol;
	private String name;
	
	public String getName() {
               return name;
        }
	
	public void setName(String s) {
            name = s;
        }
	
	public String getSymbol() {
               return symbol;
        }
	
	public void setSymbol(String s) {
            symbol = s;
        }
	
	public void print() {
		System.out.println("------------------------ " + symbol + "  " + name + "    ----------------------------------");
	}
}
