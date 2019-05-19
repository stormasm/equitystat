/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang.math.NumberUtils;

import com.arcadian.util.EtsNumberFormat;
import com.arcadian.util.MillBill;

/**
Data switch between the Key statistics from the data provider and the standard Zrato database representation.
*/

public abstract class KsSwitch {
	
	public static final String marketcapx = "Market Cap (intraday):";
	public static final String marketcapy = "MarketCap";
	
	public static final String enterprisevaluex = "Enterprise Value (";
	public static final String enterprisevaluey = "EnterpriseValue";
	
	public static final String trailingpex = "Trailing P/E (ttm, intraday):";
	public static final String trailingpey = "TrailingPE";
	
	public static final String forwardpex = "Forward P/E";
	public static final String forwardpey = "ForwardPE";
	
	public static final String pegratiox = "PEG Ratio (5 yr expected):";
	public static final String pegratioy = "PegRatio";
	
	public static final String pricesalesx = "Price/Sales (ttm):";
	public static final String pricesalesy = "PriceSales";
	
	public static final String pricebookx = "Price/Book (mrq):";
	public static final String pricebooky = "PriceBook";
	
	public static final String evrevenuex = "Enterprise Value/Revenue (ttm):";
	public static final String evrevenuey = "EvRevenue";
	
	public static final String evebitdax = "Enterprise Value/EBITDA (ttm):";
	public static final String evebitday = "EvEbitda";
	
	public static final String fiscalyearendsx = "Fiscal Year Ends:";
	public static final String fiscalyearendsy = "FiscalYearEnds";
	
	public static final String mostrecentqtrx = "Most Recent Quarter (mrq):";
	public static final String mostrecentqtry = "MostRecentQuarter";
	
	public static final String profitmarginx = "Profit Margin (ttm):";
	public static final String profitmarginy = "ProfitMargin";
	
	public static final String operatingmarginx = "Operating Margin (ttm):";
	public static final String operatingmarginy = "OperatingMargin";
	
	public static final String returnonassetsx = "Return on Assets (ttm):";
	public static final String returnonassetsy = "ReturnOnAssets";
	
	public static final String returnonequityx = "Return on Equity (ttm):";
	public static final String returnonequityy = "ReturnOnEquity";
	
	public static final String revenuex = "Revenue (ttm):";
	public static final String revenuey = "Revenue";
	
	public static final String revenuesharex = "Revenue Per Share (ttm):";
	public static final String revenuesharey = "RevenueShare";
	
	public static final String revenuegrowthx = "Qtrly Revenue Growth (yoy):";
	public static final String revenuegrowthy = "RevenueGrowth";
	
	public static final String grossprofitx = "Gross Profit (ttm):";
	public static final String grossprofity = "GrossProfit";
	
	public static final String ebitdax = "EBITDA (ttm):";
	public static final String ebitday = "Ebitda";
	
	public static final String netincomex = "Net Income Avl to Common (ttm):";
	public static final String netincomey = "NetIncome";
	
	public static final String epsx = "Diluted EPS (ttm):";
	public static final String epsy = "EpsTtm";
	
	public static final String epsqx = "Diluted EPS (mrq):";
	public static final String epsqy = "EpsMrq";
	
	public static final String earningsgrowthx = "Qtrly Earnings Growth (yoy):";
	public static final String earningsgrowthy = "EarningsGrowth";
	
	public static final String cashx = "Total Cash (mrq):";
	public static final String cashy = "Cash";
	
	public static final String cashsharex = "Total Cash Per Share (mrq):";
	public static final String cashsharey = "CashShare";
	
	public static final String debtx = "Total Debt (mrq):";
	public static final String debty = "Debt";
	
	public static final String debtequityx = "Total Debt/Equity (mrq):";
	public static final String debtequityy = "DebtEquity";
	
	public static final String currentratiox = "Current Ratio (mrq):";
	public static final String currentratioy = "CurrentRatio";
	
	public static final String bookvaluex = "Book Value Per Share (mrq):";
	public static final String bookvaluey = "BookValueShare";
	
	public static final String cashflowx = "From Operations (ttm):";
	public static final String cashflowy = "CashFlow";
	
	public static final String freecashflowx = "Free Cashflow (ttm):";
	public static final String freecashflowy = "FreeCashFlow";
	
	public static final String betax = "Beta:";
        public static final String betay = "Beta";

	public static final String fifty2weekchgx = "52-Week Change:";
	public static final String fifty2weekchgy = "52WeekChange";
	
	public static final String fifty2weekchgsp500x = "SP500 52-Week Change:";
	public static final String fifty2weekchgsp500y = "52WeekChangeSp500";
	
	public static final String fifty2weekhighx = "52-Week High";
	public static final String fifty2weekhighy = "52WeekHigh";
	
	public static final String fifty2weeklowx = "52-Week Low";
	public static final String fifty2weeklowy = "52WeekLow";
	
	public static final String fiftydaymvavgx = "50-Day Moving Average:";
	public static final String fiftydaymvavgy = "50DayMovingAvg";
	
	public static final String two100daymvavgx = "200-Day Moving Average:"; 
	public static final String two100daymvavgy = "200DayMovingAverage";
	
	public static final String avgvol3monthx = "Average Volume (3 month):";
	public static final String avgvol3monthy = "AvgVolume3month";
	
	public static final String avgvol10dayx = "Average Volume (10 day):";
	public static final String avgvol10dayy = "AvgVolume10day";
	
	public static final String sharesoutstandingx = "Shares Outstanding:";
	public static final String sharesoutstandingy = "SharesOutstanding";
	
	public static final String floatx = "Float:";
	public static final String floaty = "Float";
	
	public static final String pctinsidersx = "% Held by Insiders:";
	public static final String pctinsidersy = "PctInsiders";
	
	public static final String pctinstx = "% Held by Institutions:";
	public static final String pctinsty = "PctInstitutions";
	
	public static final String shareshortx = "Shares Short (as of";
	public static final String shareshorty = "SharesShort";
	
	public static final String shortratiox = "Short Ratio (as of";
	public static final String shortratioy = "ShortRatio";
	
	public static final String shortpctfloatx = "Short % of Float (as of";
	public static final String shortpctfloaty = "ShortPctFloat";
	
	public static final String shortpriormonthx = "Shares Short (prior month):";
	public static final String shortpriormonthy = "SharesShortPriorMonth";
	
	public static final String dividendx = "Dividend (ttm):";
	public static final String dividendy = "Dividend";
	
	public static final String dividendyieldx = "Dividend Yield (ttm):";
	public static final String dividendyieldy = "DividendYield";
	
	public static final String dividenddatex = "Dividend Date:";
	public static final String dividenddatey = "DividendDate";
	
	public static final String exdividenddatex = "Ex-Dividend Date:";
	public static final String exdividenddatey = "ExDividendDate";
	
	public static final String lastsplitfactorx = "Last Split Factor (new per old):";
	public static final String lastsplitfactory = "LastSplitFactor";
	
	public static final String lastsplitdatex = "Last Split Date:";
	public static final String lastsplitdatey = "LastSplitDate";
	
	/**
	Number is the number attribute from the XML file
	*/
	
	public static String processColumnName(String input, String number) {
		
		if(input.compareTo(marketcapx) == 0)
			return marketcapy;
		
		if(input.startsWith(enterprisevaluex))
			return enterprisevaluey;
		
		if(input.compareTo(trailingpex) == 0)
			return trailingpey;
		
		if(input.startsWith(forwardpex))
			return forwardpey;
		
		if(input.compareTo(pegratiox) == 0)
			return pegratioy;
	
		if(input.compareTo(pricesalesx) == 0)
			return pricesalesy;
		
		if(input.compareTo(pricebookx) == 0)
			return pricebooky;
		
		if(input.compareTo(evrevenuex) == 0)
			return evrevenuey;
		
		if(input.compareTo(evebitdax) == 0)
			return evebitday;
	
		if(input.compareTo(fiscalyearendsx) == 0)
			return fiscalyearendsy;

		if(input.compareTo(mostrecentqtrx) == 0)
			return mostrecentqtry;
	
		if(input.compareTo(profitmarginx) == 0)
			return profitmarginy;
		
		if(input.compareTo(operatingmarginx) == 0)
			return operatingmarginy;
		
		if(input.compareTo(returnonassetsx) == 0)
			return returnonassetsy;
		// 10
		if(input.compareTo(returnonequityx) == 0)
			return returnonequityy;
		
		if(input.compareTo(revenuex) == 0)
			return revenuey;
		
		if(input.compareTo(revenuesharex) == 0)
			return revenuesharey;
		
		if(input.compareTo(revenuegrowthx) == 0)
			return revenuegrowthy;
		
		if(input.compareTo(grossprofitx) == 0)
			return grossprofity;
		
		if(input.compareTo(ebitdax) == 0)
			return ebitday;
		
		if(input.compareTo(netincomex) == 0)
			return netincomey;
		
		if(input.compareTo(epsx) == 0)
			return epsy;
		
		if (input.compareTo(epsqx) == 0)
			return epsqy;
		
		if (input.compareTo(earningsgrowthx) == 0)
			return earningsgrowthy;
		// 20
		
		if (input.compareTo(cashx) == 0)
			return cashy;
		
		if (input.compareTo(cashsharex) == 0)
			return cashsharey;
		
		if (input.compareTo(debtx) == 0)
			return debty;
		
		if(input.compareTo(debtequityx) == 0)
			return debtequityy;
		
		if(input.compareTo(currentratiox) == 0)
			return currentratioy;
		// 25
		if(input.compareTo(bookvaluex) == 0)
			return bookvaluey;
		
		if(input.compareTo(cashflowx) == 0)
			return cashflowy;
		
		if(input.compareTo(freecashflowx) == 0)
			return freecashflowy;
		
		if(input.compareTo(betax) == 0)
			return betay;
			
		if(input.compareTo(fifty2weekchgx) == 0)
			return fifty2weekchgy;
		
		// 30
			
		if(input.compareTo(fifty2weekchgsp500x) == 0)
			return fifty2weekchgsp500y;
		
		if(input.startsWith(fifty2weekhighx))
			return fifty2weekhighy;
			
		if(input.startsWith(fifty2weeklowx))
			return fifty2weeklowy;
		
		if(input.compareTo(fiftydaymvavgx) == 0)
			return fiftydaymvavgy;
			
		if(input.compareTo(two100daymvavgx) == 0)
			return two100daymvavgy;
	
		if(input.compareTo(avgvol3monthx) == 0)
			return avgvol3monthy;
		
		if(input.compareTo(avgvol10dayx) == 0)
			return avgvol10dayy;
		
		if(input.compareTo(sharesoutstandingx) == 0)
			return sharesoutstandingy;
	
		if(input.compareTo(floatx) == 0)
			return floaty;
	
		if(input.compareTo(pctinsidersx) == 0)
			return pctinsidersy;
		//40
		if(input.compareTo(pctinstx) == 0)
			return pctinsty;
			
		if(input.compareTo(shareshortx) == 0)
			return shareshorty;
		
		if(input.startsWith(shortratiox))
			return shortratioy;
		
		if(input.startsWith(shareshortx))
			return shareshorty;
		
		if(input.startsWith(shortpctfloatx))
			return shortpctfloaty;
		
		if(input.compareTo(shortpriormonthx) == 0)
			return shortpriormonthy;
				
		if(input.compareTo(dividendx) == 0)
			return dividendy;
				
		if(input.compareTo(dividendyieldx) == 0)
			return dividendyieldy;
		
		if(input.compareTo(dividenddatex) == 0)
			return dividenddatey;
		
		if(input.compareTo(exdividenddatex) == 0)
			return exdividenddatey;

		if(input.compareTo(lastsplitfactorx) == 0)
			return lastsplitfactory;

		if(input.compareTo(lastsplitdatex) == 0)
			return lastsplitdatey;

		return "zzxx123";
	}
	
	public static String processColumnValue(String tvalue) {
		int index = 0;
		
		String value = tvalue.trim();
		String back = "";
		
		if (value.compareTo("N/A") == 0) {
			Double valueFix = new Double(-6.96969);
			return valueFix.toString();
		}
		
		if((index = value.indexOf("B")) > 0) {
			back = MillBill.convertToMill(value);
			return back;
		}
		
		if((index = value.indexOf("M")) > 0) {
			
			// Deal with the case where dates have M's in them
			
			String valk0 = value.substring(0,index);
			
			if (NumberUtils.isNumber(valk0) == false) {
				return value;	
			}
			
			back = MillBill.convertToMill(value);
			return back;
		}
		
		if((index = value.indexOf("K")) > 0) {
			back = MillBill.convertToMill(value);
			return back;
		}
		
		if((index = value.indexOf("%")) > 0) {
			String valq0 = value.substring(0,index);
			// System.out.println(index + " " + value + " " + valq0);
			Double valq1 = new Double(valq0);
			double valq2 = valq1.doubleValue();
			double valq3 = valq2 / 100.0;
			double valq4 = EtsNumberFormat.threeDecimalPlaces(valq3);
			
			return String.valueOf(valq4);
		}
		
		if((index = value.indexOf(",")) > 0) {
			back = EtsNumberFormat.removeCommas(value);
			return back;
		}

		if (NumberUtils.isNumber(value) == false) {
			// System.out.println(value + " is NOT a number");
			return value;	
		}
		
		Double val = new Double(value);
		return val.toString();
	}
}
