/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 * Sector
 * @author Michael I Angerman
 */
 
public class Sector extends Persistent {
        private String sector, indname, indcode;
	
	public String getSector() {
		return sector;	
	}
	
	public String getIndname() {
		return indname;	
	}
	
	public String getIndcode() {
		return indcode;	
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
		System.out.println(sector + " " + indname + " " + indcode);
	}
}
