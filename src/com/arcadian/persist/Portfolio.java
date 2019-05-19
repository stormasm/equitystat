/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

import java.util.List;

/**
 * Portfolio
 * @author Michael I Angerman
 */
 
public class Portfolio extends Persistent {
	private String username;
	private String portfolioname;
        private List symbols;
	
        public List getSymbols() {
               return symbols;
        }
	
	public void setSymbols(List s) {
            symbols = s;
        }
	
	public String getUsername() {
               return username;
        }
	
	public void setUsername(String s) {
            username = s;
        }
	
	public String getPortfolioname() {
               return portfolioname;
        }
	
	public void setPortfolioname(String s) {
            portfolioname = s;
        }
	
	public void print() {
		System.out.println("------------------------ " + username + " - " + portfolioname + "    ----------------------------------");
		for (int i = 0; i < symbols.size(); i++) {
			System.out.println(symbols.get(i));
		}
	}
}
