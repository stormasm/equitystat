/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

import java.util.Date;

/**
Moving Average.
*/

public class Ma extends Persistent {

     String symbol = "";
     Date date = null;
     
     /**
     Number of days in Moving average, usually 50, 100, 200
     */
     
     int days = 0;
     double adjclose = 0.0;
     
     /**
     Moving Average
     */
     
     double ma = 0.0;

     /** 
     	true is up, false is down
	true is when ac > ma, in other words when the adjusted close moves above the moving average, this is bullish
	false is when ma > ac, in other words the adjusted close falls below the moving average, this is bearish
     */
     
     boolean direction = true;  

     public String getSymbol() {
	return symbol;     
     }
     
     public void setSymbol(String s) {
	symbol = s;     
     }
     
     public Date getDate() {
	return date;     
     }
     
     public void setDate(Date d) {
	date = d;     
     }
     
     public double getAdjclose() {
	return adjclose;
     }
     
     public void setAdjclose(double ac) {
	adjclose = ac;     
     }
     
     public void setMa(double m) {
		ma = m;     
     }
     
     public double getMa() {
		return ma;     
     }
     
     /**
     Number of days in Moving average, usually 50, 100, 200
     */
     
     public int getDays() {
		return days;     
     }
     
     /**
     Number of days in Moving average, usually 50, 100, 200
     */
     
     public void setDays(int d) {
		days = d;     
     }
     
     public String getDirectionupdown() {
	     // If the the adjclose surpasses the moving average this is a bullish sign up
	     if (adjclose > ma) {
		direction = true;
		return "up";
	     }
	     direction = false;
	     return "down";
     }
     
     public boolean getDirection() {
	     return direction;
     }

     public void setDirection(boolean val) {
		direction = val;     
     }
     
     public String getDirectionString() {
	     
	     String up = "up";
	     String down = "down";
	     
	     if (direction) {
		return up;
	     }
	     return down;
     }
     
/*     
     public void setDirection(String dir) {
	     if (dir.compareTo("up") == 0) {
		direction = true;
	     }
	     else {
		if(dir.compareTo("down") == 0) {
			direction = false;
		}
	     }
     }
*/  
     public void print() {
	System.out.println(symbol + " " + date +  "," + days + " adjclose = " + adjclose + " ma = " + ma + " " + getDirectionupdown());      
     }
}

