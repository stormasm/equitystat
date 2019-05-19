/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

import java.util.Date;

/**
 * Historical Price
 * @author Michael I Angerman
  Open, Volume
 */
public class Hp extends Persistent {
	private Date date;
        private String symbol;
	private double open, high, low, close, adjclose;
	private int volume;
	
	public Date getDate() {
		return date;	
	}

        public String getSymbol() {
               return symbol;
        }
	
	public double getOpen() {
		return open;
	}
	
	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getClose() {
		return close;
	}

	public int getVolume() {
		return volume;
	}
	
	public double getAdjclose() {
		return adjclose;
	}

	public void setDate(Date d) {
		date = d;	
	}

        public void setSymbol(String s) {
            symbol = s;
        }
	
	public void setOpen(double f) {
		open = f;
	}
	
	public void setHigh(double f) {
		high = f;
	}

	public void setLow(double f) {
		low = f;
	}

	public void setClose(double f) {
		close = f;
	}

	public void setVolume(int i) {
		volume = i;
	}
	
	public void setAdjclose(double f) {
		adjclose = f;
	}
	
	public void dumpData() {
		System.out.println(symbol + " " + date.toString() + " " + open + " " + high + " " + low + " " + close + " " + volume + " " + adjclose);
	}
}
