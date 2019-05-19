/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 * Industry Key Statistics
 *
 * @author Michael I Angerman
 */
 
public class IndustryKs extends Persistent {
        private String symbol;
	
	// this does NOT get stored in the database table with other variables
	// this is here as a convenience for passing back data in the Industry Browser
	private String name;
	
	// valuation measures
	private double marketcap, trailingpe, pricebook;
	// management effectiveness
	private double roe;
	// balance sheet
	private double debtequity;
	// dividends
	double divyield;
	// extra parameters (not in Ks)
	double pricechange, qtrrevgrowth, qtrepsgrowth;
	
        public String getSymbol() {
               return symbol;
        }
	
	public void setSymbol(String s) {
            symbol = s;
        }
	
	public String getName() {
               return name;
        }
	
	public void setName(String s) {
            name = s;
        }
	
	/**
		Valuation Measures
	*/
		
	public double getMarketcap() {
		return marketcap;	
	}
	
	public void setMarketcap(double d) {
		marketcap = d;
	}
	
	public double getTrailingpe() {
		return trailingpe;
	}
	
	public void setTrailingpe(double d) {
		trailingpe = d;
	}
	
	public double getPricebook() {
		return pricebook;	
	}
	
	public void setPricebook(double d) {
		pricebook = d;
	}
		
	/**
		Management Effectiveness
	*/
	
	public double getRoe() {
		return roe;
	}
	
	public void setRoe(double d) {
		roe = d;
	}
	
	/**
		Balance Sheet
	*/
	
	public double getDebtequity() {
		return debtequity;
	}
	
	public void setDebtequity(double d) {
		debtequity = d;
	}
	
	/**
		Dividends
	*/
	
	public double getDivyield() {
		return divyield;
	}
	
	public void setDivyield(double d) {
		divyield = d;
	}
		
	/**
		Extra Parameters
	*/
	
	public double getPricechange() {
		return pricechange;
	}
	
	public void setPricechange(double d) {
		pricechange = d;
	}
	
	public double getQtrrevgrowth() {
		return qtrrevgrowth;
	}
	
	public void setQtrrevgrowth(double d) {
		qtrrevgrowth = d;
	}
	
	public double getQtrepsgrowth() {
		return qtrepsgrowth;
	}
	
	public void setQtrepsgrowth(double d) {
		qtrepsgrowth = d;
        }
	
	public void print() {
		System.out.println("---- " + symbol + " " + name + " -------");
		System.out.println("pricechange " + pricechange);
		System.out.println("marketcap = " + marketcap + " trailingpe " + trailingpe);
		System.out.println("roe " + roe + " divyield " + divyield + " debtequity " + debtequity + " pricebook " + pricebook);
		System.out.println("qtrrevgrowth " + qtrrevgrowth + " qtrepsgrowth " + qtrepsgrowth);
	}
}
