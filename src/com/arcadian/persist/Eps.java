/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 * Earnings Per Share
 * @author Michael I Angerman
 */
 
public class Eps extends Persistent {
        private String symbol;
	private double trailingpe, eps, price;
	private double forwardpe, futureeps, futureprice; 
	private double pctincrease;
	
        public String getSymbol() {
               return symbol;
        }
	
	public void setSymbol(String s) {
            symbol = s;
        }
	
	public double getTrailingpe() {
		return trailingpe;	
	}
	
	public void setTrailingpe(double d) {
		trailingpe = d;
	}

	public double getForwardpe() {
		return forwardpe;	
	}
	
	public void setForwardpe(double d) {
		forwardpe = d;
	}
	
	public double getPrice() {
		return price;	
	}
	
	public void setPrice(double d) {
		price = d;
	}
	
	public double getFutureprice() {
		return futureprice;	
	}
	
	public void setFutureprice(double d) {
		futureprice = d;
	}
	
	public double getEps() {
		return eps;
	}
	
	public void setEps(double d) {
		eps = d;
	}
	
	public double getFutureeps() {
		return futureeps;
	}
	
	public void setFutureeps(double d) {
		futureeps = d;
	}
	
	public double getPctincrease() {
		return pctincrease;
	}
	
	public void setPctincrease(double d) {
		pctincrease = d;
	}
	
	public void print() {
		System.out.println("----------------------------- " + symbol + " ----------------------------------");
		System.out.println("trailingpe = " + trailingpe + " eps " + eps + " price " + price);
		System.out.println("forwardpe " + forwardpe + " futureeps " + futureeps + " futureprice " + futureprice);
		System.out.println("pctincrease " + pctincrease);
	}
}
