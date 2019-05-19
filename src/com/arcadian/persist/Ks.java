/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 * Key Statistics
 * @author Michael I Angerman
 */
 
public class Ks extends Persistent {
        private String symbol;
	// valuation measures
	private double marketcap, enterprisevalue, trailingpe, forwardpe, pegratio, pricesales, pricebook,evrevenue, evebitda;
	
	// fiscal year
	private String fiscalyearends, mostrecentqtr;
	
	// profitability
	private double profitmargin, operatingmargin;
	
	// management effectiveness
	private double returnonassets, returnonequity;
	
	// income statement
	private double revenue, revenueshare, qtrrevenuegrowth,grossprofit,ebitda,netincome,eps,qtrearningsgrowth;
	
	// balance sheet
	private double cash, cashshare, debt, debtequity, currentratio, bookvalueshare;
	
	// cash flow
	private double operationscashflow, freecashflow;
	
	// stock price history
	double beta, x52weekchange, x52weekchangesp500, x52weekhigh, x52weeklow, x50dayma, x200dayma;
	
	// share statistics
	double avgvol3month, avgvol10day, sharesoutstanding, xfloat, percentinsiders, percentinstitutions, shareshort;
	double shortratio, shortpercentoffloat, shareshortpriormonth;
	
	// dividends
	double dividend, dividendyield;
	String dividenddate, exdividenddate, lastsplitfactor, lastsplitdate;
	
        public String getSymbol() {
               return symbol;
        }
	
	public void setSymbol(String s) {
            symbol = s;
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
	
	public double getEnterprisevalue() {
		return enterprisevalue;	
	}
	
	public void setEnterprisevalue(double d) {
		enterprisevalue = d;
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
	
	public double getPegratio() {
		return pegratio;	
	}
	
	public void setPegratio(double d) {
		pegratio = d;
	}
	
	public double getPricesales() {
		return pricesales;
	}
	
	public void setPricesales(double d) {
		pricesales = d;
	}
	
	public double getPricebook() {
		return pricebook;	
	}
	
	public void setPricebook(double d) {
		pricebook = d;
	}
	
	public double getEvrevenue() {
		return evrevenue;	
	}
	
	public void setEvrevenue(double d) {
		evrevenue = d;
	}
	
	public double getEvebitda() {
		return evebitda;	
	}
	
	public void setEvebitda(double d) {
		evebitda = d;
	}
	
	/**
	        Fiscal Year
	*/
	
	public String getFiscalyearends() {
		return fiscalyearends;
	}
	
	public void setFiscalyearends(String s) {
		fiscalyearends = s;	
	}
	
	public String getMostrecentqtr() {
		return mostrecentqtr;	
	}
	
	public void setMostrecentqtr(String s) {
		mostrecentqtr = s;	
	}
	
	/**
		Profitability
	*/
	
	public double getProfitmargin() {
		return profitmargin;	
	}
	
	public void setProfitmargin(double d) {
		profitmargin = d;
	}
	
	public double getOperatingmargin() {
		return operatingmargin;	
	}
	
	public void setOperatingmargin(double d) {
		operatingmargin = d;
	}
	
	/**
		Management Effectiveness
	*/
	
	public double getReturnonassets() {
		return returnonassets;	
	}
	
	public void setReturnonassets(double d) {
		returnonassets = d;
	}
	
	public double getReturnonequity() {
		return returnonequity;	
	}
	
	public void setReturnonequity(double d) {
		returnonequity = d;
	}
	
	/**
		Income Statement
	*/
	
	public double getRevenue() {
		return revenue;
	}
	
	public void setRevenue(double d) {
		revenue = d;
	}
	
	public double getRevenueshare() {
		return revenueshare;
	}
	
	public void setRevenueshare(double d) {
		revenueshare =d ;
	}
	
	public double getQtrrevenuegrowth() {
		return qtrrevenuegrowth;
	}
	
	public void setQtrrevenuegrowth(double d) {
		qtrrevenuegrowth = d;
	}
	
	public double getGrossprofit() {
		return grossprofit;
	}
	
	public void setGrossprofit(double d) {
		grossprofit = d;
	}
	
	public double getEbitda() {
		return ebitda;
	}
	
	public void setEbitda(double d) {
		ebitda = d;
	}
	
	public double getNetincome() {
		return netincome;
	}
	
	public void setNetincome(double d) {
		netincome = d;
	}
	
	public double getEps() {
		return eps;
	}
	
	public void setEps(double d) {
		eps = d;
	}
	
	public double getQtrearningsgrowth() {
		return qtrearningsgrowth;
	}
	
	public void setQtrearningsgrowth(double d) {
		qtrearningsgrowth = d;
	}
	
	/**
		Balance Sheet
	*/
	
	public double getCash() {
		return cash;
	}
	
	public void setCash(double d) {
		cash = d;
	}
	
	public double getCashshare() {
		return cashshare;
	}
	
	public void setCashshare(double d) {
		cashshare = d;
	}

	public double getDebt() {
		return debt;
	}
	
	public void setDebt(double d) {
		debt = d;
	}

	public double getDebtequity() {
		return debtequity;
	}
	
	public void setDebtequity(double d) {
		debtequity = d;
	}
	
	public double getCurrentratio() {
		return currentratio;
	}
	
	public void setCurrentratio(double d) {
		currentratio = d;
	}

	public double getBookvalueshare() {
		return bookvalueshare;
	}
	
	public void setBookvalueshare(double d) {
		bookvalueshare = d;
	}
	
	/**
		Cash Flow Statement
	*/
	
	public double getOperationscashflow() {
		return operationscashflow;
	}
	
	public void setOperationscashflow(double d) {
		operationscashflow = d;
	}
	
	public double getFreecashflow() {
		return freecashflow;
	}
	
	public void setFreecashflow(double d) {
		freecashflow = d;
	}
	
	/**
		Stock Price History
	*/
	
	public double getBeta() {
		return beta;
	}
	
	public void setBeta(double d) {
		beta = d;
	}
	
	public double getX52weekchange() {
		return x52weekchange;
	}
	
	public void setX52weekchange(double d) {
		x52weekchange = d;
	}
	
	public double getX52weekchangesp500() {
		return x52weekchangesp500;
	}
	
	public void setX52weekchangesp500(double d) {
		x52weekchangesp500 = d;
	}
	
	public double getX52weekhigh() {
		return x52weekhigh;
	}
	
	public void setX52weekhigh(double d) {
		x52weekhigh = d;
	}
	
	public double getX52weeklow() {
		return x52weeklow;
	}
	
	public void setX52weeklow(double d) {
		x52weeklow = d;
	}
	
	public double getX50dayma() {
		return x50dayma;
	}
	
	public void setX50dayma(double d) {
		x50dayma = d;
	}
	
	public double getX200dayma() {
		return x200dayma;
	}
	
	public void setX200dayma(double d) {
		x200dayma = d;
	}
	
	/**
		Share Statistics
	*/
	
	public double getAvgvol3month() {
		return avgvol3month;
	}
	
	public void setAvgvol3month(double d) {
		avgvol3month = d;
	}
	
	public double getAvgvol10day() {
		return avgvol10day;
	}
	
	public void setAvgvol10day(double d) {
		avgvol10day = d;
	}
	
	public double getSharesoutstanding() {
		return sharesoutstanding;
	}
	
	public void setSharesoutstanding(double d) {
		sharesoutstanding = d;
	}
	
	public double getXfloat() {
		return xfloat;
	}
	
	public void setXfloat(double d) {
		xfloat = d;
	}
	
	public double getPercentinsiders() {
		return percentinsiders;
	}
	
	public void setPercentinsiders(double d) {
		percentinsiders = d;
	}
	
	public double getPercentinstitutions() {
		return percentinstitutions;
	}
	
	public void setPercentinstitutions(double d) {
		percentinstitutions = d;
	}
	
	public double getShareshort() {
		return shareshort;
	}
	
	public void setShareshort(double d) {
		shareshort = d;
	}
	
	public double getShortratio() {
		return shortratio;
	}
	
	public void setShortratio(double d) {
		shortratio = d;
	}
	
	public double getShortpercentoffloat() {
		return shortpercentoffloat;
	}
	
	public void setShortpercentoffloat(double d) {
		shortpercentoffloat = d;
	}
	
	public double getShareshortpriormonth() {
		return shareshortpriormonth;
	}
	
	public void setShareshortpriormonth(double d) {
		shareshortpriormonth = d;
	}
	
	/**
		Dividends
	*/
	
	public double getDividend() {
		return dividend;
	}
	
	public void setDividend(double d) {
		dividend = d;
	}
	
	public double getDividendyield() {
		return dividendyield;
	}
	
	public void setDividendyield(double d) {
		dividendyield = d;
	}
	
	public String getDividenddate() {
		return dividenddate;
	}
	
	public void setDividenddate(String s) {
		dividenddate = s;
	}
	
	public String getExdividenddate() {
		return exdividenddate;
	}
	
	public void setExdividenddate(String s) {
		exdividenddate = s;
	}
	
	public String getLastsplitfactor() {
		return lastsplitfactor;
	}
	
	public void setLastsplitfactor(String s) {
		lastsplitfactor = s;
	}
	
	public String getLastsplitdate() {
		return lastsplitdate;
	}
	
	public void setLastsplitdate(String s) {
		lastsplitdate = s;
	}
	
	public void print() {
		System.out.println("----------------------------- " + symbol + " ----------------------------------");
		System.out.println("marketcap = " + marketcap + " pricesales " + pricesales + " pricebook " + pricebook);
		System.out.println("revenue " + revenue + " revenueshare " + revenueshare + " grossprofit " + grossprofit);
		System.out.println("cash " + cash + " cashshare " + cashshare + " debt " + debt);
		System.out.println("debtequity " + debtequity + " currentratio " + currentratio + " bookvalueshare " + bookvalueshare);
		System.out.println("freecashflow " + freecashflow + " beta " + beta + " sharesoutstanding " + sharesoutstanding);
	}
}
