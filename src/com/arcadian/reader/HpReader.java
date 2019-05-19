/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

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
import com.arcadian.finance.symbol.SymbolHp;
import com.arcadian.persist.Hp;

/**
This reads in a set of XML files which represent the Historical Prices
located in the hp directory.
*/

public class HpReader {

	Vector vec = null;
	String[] filenames = null;
	File[] pathnames = null;
	int count = 0;
	int total = 0;
	
	public void init(String dir) {
		File dira = new File(dir);
		filenames = dira.list();
		pathnames = dira.listFiles();
		total = pathnames.length;
	}
	
	public SymbolHp getNextSymbolHp() throws Exception{

		if (count < total) {			
			String s1 = pathnames[count].getName();
			int index = s1.indexOf(".xml");
			String s2 = s1.substring(0,index);
			SymbolHp shp = getSymbolHp(pathnames[count].getCanonicalPath(),s2);
			count = count + 1;
			return shp;
		}
		return null;
	}
	
    public SymbolHp getSymbolHp(String filenameread, String symbol) {

	SymbolHp shp = null;
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
		
		shp = new SymbolHp();
		shp.setSymbol(symbol);
		Hp[] hpa = new Hp[len];
		
                for (int k = 0; k < len; k++) {
                    ea = (Element) nl.item(k);
		    // Print out the symbol here for debugging purposes
		    // String qq = ea.getNodeName();
		    // System.out.println(qq);
		    
		    // NodeList nla = ea.getChildNodes();
		    // int lena = nla.getLength();
		    
			    Hp hp = new Hp();
			    // eb = (Element) nl.item(m);
			    NamedNodeMap nnm = ea.getAttributes();
			    na = nnm.getNamedItem("volume");
			    nb = nnm.getNamedItem("high");
			    nc = nnm.getNamedItem("date");
			    nd = nnm.getNamedItem("open");
			    ne = nnm.getNamedItem("low");
			    nf = nnm.getNamedItem("adjclose");
			    ng = nnm.getNamedItem("close");
			    
			    String volumewithcommas = na.getNodeValue();
			    String volume = CharSetUtils.delete(volumewithcommas,",");			    
			    
			    String volxa = volume.trim();
			    int vol = Integer.parseInt(volxa);
			    hp.setVolume(vol);
			    
			    String high = nb.getNodeValue();
			    double hi = Double.parseDouble(high);
			    hp.setHigh(hi);
			    
			    String date = nc.getNodeValue();
			    
			    try {
				    hpdatex = sdf.parse(date);
			    }
			    catch (ParseException ex) {
				    ex.printStackTrace();
			    }
			    hp.setDate(hpdatex);
			    
			    String open = nd.getNodeValue();
			    double opend = Double.parseDouble(open);
			    hp.setOpen(opend);
			    
			    String low = ne.getNodeValue();
			    double lowd = Double.parseDouble(low);
			    hp.setLow(lowd);
			    
			    String adjclose = nf.getNodeValue();
			    double adjclosed = Double.parseDouble(adjclose);
			    hp.setAdjclose(adjclosed);
			    
			    String close = ng.getNodeValue();
			    double closed = Double.parseDouble(close);
			    hp.setClose(closed);
			    
			    hpa[k] = hp;
			    shp.setHpa(hpa);
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
	    return shp;
    }
    
    public static void main(String[] args) throws Exception {
        HpReader hpr = new HpReader();
	
	String dira = AntProperty.etsdata();
        String dirb = dira.concat("\\finance\\hp\\db\\");

	hpr.init(dirb);
	
	SymbolHp shp = null;
	
	while ((shp = hpr.getNextSymbolHp()) != null) {
			System.out.println("--------------" + shp.getSymbol() + "--------------------");
			/*
			Hp[] hp = shp.getHpa();
			for (int m = 0; m < hp.length; m++) {
				System.out.println(hp[m].getHigh());	
			}
			*/
	}
	System.out.println("Done");
    }
}	 
