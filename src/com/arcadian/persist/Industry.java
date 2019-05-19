/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 * Industry
 * @author Michael I Angerman
 */
 
public class Industry extends Persistent {
        private String symbol, name, sector, indname, indcode;
	
        public String getSymbol() {
               return symbol;
        }
	
	public String getName() {
               return name;
        }
	
	public String getSector() {
		return sector;	
	}
	
	public String getIndname() {
		return indname;	
	}
	
	public String getIndcode() {
		return indcode;	
	}
	
        public void setSymbol(String s) {
            symbol = s;
        }
	
	public void setName(String s) {
            name = s;
        }
	
	public void setSector(String s) {
	     sector = s;	
	}
	
	public void setIndname(String s) {
	     indname = s;	
	}
	
	public void setIndcode(String s) {
	     indcode = s;	
	}
	
	public void print() {
		System.out.println(sector + " " + indname + " " + symbol + " " + name + " " + indcode);
	}
	
	public void printSector() {
		System.out.println(sector + " " + indname + " " + indcode);
	}
}
