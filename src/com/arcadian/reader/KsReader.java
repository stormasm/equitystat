/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.apache.commons.lang.CharSetUtils;
import org.apache.xerces.parsers.DOMParser;

import com.arcadian.ant.AntProperty;
import com.arcadian.finance.symbol.SymbolHm;
import com.arcadian.util.EtsNumberFormat;
import com.arcadian.persist.Ks;

/**
This reads in a set of XML files which represent the Key Statistics
located in the ks directory.
*/

public class KsReader {

	String[] filenames = null;
	File[] pathnames = null;
	SymbolHm[] shma = null;
	int count = 0;
	int total = 0;
	
	public void init(String dir) {
		File dira = new File(dir);
		filenames = dira.list();
		pathnames = dira.listFiles();
		total = pathnames.length;
	}
	
	public Ks getNextKs() throws Exception{

		if (count < total) {			
			String s1 = pathnames[count].getName();
			int index = s1.indexOf(".xml");
			String s2 = s1.substring(0,index);
			
			SymbolHm shm = getSymbolHm(pathnames[count].getCanonicalPath(),s2);
			
			Ks ks = getKs(shm);
			count = count + 1;
			return ks;
		}
		return null;
	}
	
    public SymbolHm getSymbolHm(String filenameread, String symbol) {

	SymbolHm shm = null;
	HashMap hm = null;
	InputSource inputsrc = new InputSource(filenameread);    
	    
        DOMParser parser = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	Date hpdatex = null;
	
        if (parser == null) {

        // create parser
        try {
          parser = new DOMParser();
		parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
		parser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
		// Errors errors = new Errors();
		// parser.setErrorHandler(errors);
        }
        catch (Exception e) {
          System.err.println("SniReader: Unable to instantiate parser"); 
        }
        }
            // parse file
            try {
                parser.parse(inputsrc);
                Document doc = parser.getDocument();
                Element e = doc.getDocumentElement();

                Node na = null, nb = null, nc = null, nd = null, ne = null, nf = null, ng = null;
                Element ea = null, eb = null;
                NodeList nl = e.getChildNodes();
                int len = nl.getLength();
                // System.out.println("Len -> " + len);
		
		shm = new SymbolHm();
		shm.setSymbol(symbol);
		
                for (int k = 0; k < len; k++) {
                    ea = (Element) nl.item(k);
		    NamedNodeMap nnm = ea.getAttributes();
		    na = nnm.getNamedItem("name");
		    nb = nnm.getNamedItem("value");
		    nc = nnm.getNamedItem("number");
		    String nvx = na.getNodeValue();
		    String nvya = nb.getNodeValue();
		    String nvz = nc.getNodeValue();
		    
		    String nx = KsSwitch.processColumnName(nvx,nvz);
		    
		    // System.out.println("XX -> " + nx);
		    
		    String nvyb = "", nvyc = "";
		    
		    if(nvx.compareTo("zzxx123") != 0) {
			    nvyb = KsSwitch.processColumnValue(nvya);     
			    // deal with doubles that have more than two decimal places
			    // nvyc = EtsNumberFormat.twoDecimalPlaces(nvyb);				    
			    }
		    
		    
		    // System.out.println(nx + " " + nvyb);
		    
		    /*
		    System.out.println(nvx + " " + nvya);
		    System.out.println(nx + " " + nvyb);
		    System.out.println();
		    */
		    shm.addParam(nx,nvyb);
		} // end k
	    }
            catch (SAXParseException e) {
                // ignore
            }
            catch (Exception e) {
                System.err.println("error: Parse error occurred - " + 
                                   e.getMessage());
                Exception se = e;
                if (e instanceof SAXException) {
                    se = ((SAXException)e).getException();
                }
                if (se != null)
                  se.printStackTrace(System.err);
                else
                  e.printStackTrace(System.err);
            }
	    return shm;
    }
    
    /**
    Convert String to Double
    */
    public static double cstd (String input) {
	    
	    if (input.compareTo("-6.96969") == 0) {
		    double dy = Double.parseDouble(input);
		    return (dy);
	    }
	    
	    String x = EtsNumberFormat.threeDecimalPlaces(input);
	    double dx = Double.parseDouble(x);
	    return (dx);
    }
    
    
    
    public Ks getKs(SymbolHm shm) {
    
    
		String symbol = shm.getSymbol();
		HashMap hm = shm.getHashMap();

		String marketcap = (String) hm.get("MarketCap");
		String enterprisevalue = (String) hm.get("EnterpriseValue");
		String trailingpe = (String) hm.get("TrailingPE");
		String forwardpe = (String) hm.get("ForwardPE");
		String pegratio = (String) hm.get("PegRatio");
		String pricesales = (String) hm.get("PriceSales");
		String pricebook = (String) hm.get("PriceBook");
		String evrevenue = (String) hm.get("EvRevenue");
		String evebitda = (String) hm.get("EvEbitda");
		String fiscalyearends = (String) hm.get("FiscalYearEnds");
		String mostrecentqtr = (String) hm.get("MostRecentQuarter");
		String profitmargin = (String) hm.get("ProfitMargin");
		String operatingmargin = (String) hm.get("OperatingMargin");
		String returnonassets = (String) hm.get("ReturnOnAssets");
		String returnonequity = (String) hm.get("ReturnOnEquity");
		String revenue = (String) hm.get("Revenue");
		String revenueshare = (String) hm.get("RevenueShare");
		String revenuegrowth = (String) hm.get("RevenueGrowth");
		String grossprofit = (String) hm.get("GrossProfit");
		String ebitda = (String) hm.get("Ebitda");
		String netincome = (String) hm.get("NetIncome");
		String eps = (String) hm.get("EpsTtm");
		String earningsgrowth = (String) hm.get("EarningsGrowth");
		String cash = (String) hm.get("Cash");
		String cashshare = (String) hm.get("CashShare");
		String debt = (String) hm.get("Debt");
		String debtequity = (String) hm.get("DebtEquity");
		String currentratio = (String) hm.get("CurrentRatio");
		String bookvalue = (String) hm.get("BookValueShare");
		String cashflow = (String) hm.get("CashFlow");
		String freecashflow = (String) hm.get("FreeCashFlow");
        	String beta = (String) hm.get("Beta");
		String x52weekchange = (String) hm.get("52WeekChange");
		String x52weekchangesp500 = (String) hm.get("52WeekChangeSp500");
		String x52weekhigh = (String) hm.get("52WeekHigh");
		String x52weeklow = (String) hm.get("52WeekLow");
		String x50dayma = (String) hm.get("50DayMovingAvg");
		String x200dayma = (String) hm.get("200DayMovingAverage");
		String avgvol3month = (String) hm.get("AvgVolume3month");
		String avgvol10day = (String) hm.get("AvgVolume10day");
		String sharesoutstanding = (String) hm.get("SharesOutstanding");
		String xfloat = (String) hm.get("Float");
		String pctinsiders = (String) hm.get("PctInsiders");
		String pctinst = (String) hm.get("PctInstitutions");
		String shareshort = (String) hm.get("SharesShort");
		String shortratio = (String) hm.get("ShortRatio");
		String shortpctfloat = (String) hm.get("ShortPctFloat");
		String shortpriormonth = (String) hm.get("SharesShortPriorMonth");
		String dividend = (String) hm.get("Dividend");
		String dividendyield = (String) hm.get("DividendYield");
		String dividenddate = (String) hm.get("DividendDate");
		String exdividenddate = (String) hm.get("ExDividendDate");
		String lastsplitfactor = (String) hm.get("LastSplitFactor");
		String lastsplitdate = (String) hm.get("LastSplitDate");

		Ks ks = new Ks();
		ks.setSymbol(symbol);
		
		ks.setMarketcap(cstd(marketcap));
		ks.setEnterprisevalue(cstd(enterprisevalue));
		ks.setTrailingpe(cstd(trailingpe));
		ks.setForwardpe(cstd(forwardpe));
		ks.setPegratio(cstd(pegratio));
		ks.setPricesales(cstd(pricesales));
		ks.setPricebook(cstd(pricebook));
		ks.setEvrevenue(cstd(evrevenue));
		ks.setEvebitda(cstd(evebitda));
		
		ks.setFiscalyearends(fiscalyearends);
		ks.setMostrecentqtr(mostrecentqtr);
		
		ks.setProfitmargin(cstd(profitmargin));
		ks.setOperatingmargin(cstd(operatingmargin));
		ks.setReturnonassets(cstd(returnonassets));
		ks.setReturnonequity(cstd(returnonequity));
		ks.setRevenue(cstd(revenue));
		ks.setRevenueshare(cstd(revenueshare));
		ks.setQtrrevenuegrowth(cstd(revenuegrowth));
		ks.setGrossprofit(cstd(grossprofit));
		
		
		ks.setEbitda(cstd(ebitda));
		ks.setNetincome(cstd(netincome));
		ks.setEps(cstd(eps));
		ks.setQtrearningsgrowth(cstd(earningsgrowth));
		ks.setCash(cstd(cash));
		ks.setCashshare(cstd(cashshare));
		ks.setDebt(cstd(debt));
		ks.setDebtequity(cstd(debtequity));
		ks.setCurrentratio(cstd(currentratio));
		ks.setBookvalueshare(cstd(bookvalue));
		
		ks.setOperationscashflow(cstd(cashflow));
		ks.setFreecashflow(cstd(freecashflow));
        	ks.setBeta(cstd(beta));
		ks.setX52weekchange(cstd(x52weekchange));
		ks.setX52weekchangesp500(cstd(x52weekchangesp500));
		ks.setX52weekhigh(cstd(x52weekhigh));
		ks.setX52weeklow(cstd(x52weeklow));
		ks.setX50dayma(cstd(x50dayma));
		ks.setX200dayma(cstd(x200dayma));
		
		ks.setAvgvol3month(cstd(avgvol3month));
		ks.setAvgvol10day(cstd(avgvol10day));
		
		ks.setSharesoutstanding(cstd(sharesoutstanding));
		ks.setXfloat(cstd(xfloat));
		ks.setPercentinsiders(cstd(pctinsiders));
		ks.setPercentinstitutions(cstd(pctinst));
		ks.setShareshort(cstd(shareshort));
		ks.setShortratio(cstd(shortratio));
		ks.setShortpercentoffloat(cstd(shortpctfloat));
		ks.setShareshortpriormonth(cstd(shortpriormonth));
		ks.setDividend(cstd(dividend));
		ks.setDividendyield(cstd(dividendyield));
		
		ks.setDividenddate(dividenddate);
		ks.setExdividenddate(exdividenddate);
		ks.setLastsplitfactor(lastsplitfactor);
		ks.setLastsplitdate(lastsplitdate);
		
		return ks;
    }
    
    public static void main(String[] args) throws Exception {
        KsReader ksr = new KsReader();
	
	String dira = AntProperty.etsdata();
        String dirb = dira.concat("\\finance\\ks\\predb\\");

	ksr.init(dirb);
	
	Ks ks = null;
	
	while ((ks = ksr.getNextKs()) != null) {
			ks.print();
	}
	System.out.println("Done");
    }
}
