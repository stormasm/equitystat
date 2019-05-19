/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.apache.xerces.parsers.DOMParser;

import com.arcadian.ant.AntProperty;
import com.arcadian.finance.symbol.SymbolHm;
// import com.arcadian.persist.Snl;


/**
This reads in a set of XML files which represent the indices
located in the index directory.

Each file is SymbolHm, and if there are 5 files, 
there are 5 SymbolHm's stored in a Vector.

Each SymbolHm has the username and a
HashMap of keys representing the portfolio name
followed by a value which is an ArrayList of symbols
in that particular portfolio. 
*/

public class PortfolioIndexReader {

	
	// This stores a set of SymbolHm's, one for each index file
    	Vector vec = null;
	
	// This is for the symbol name pairs which gets passed off to 
	// a class which will update the symbolname table
	HashMap hm = null;
	
	File[] pathnames = null;
	
	public void init(String dir) {
		File dira = new File(dir);
		pathnames = dira.listFiles();
		vec = new Vector();
		hm = new HashMap();
	}
	
	public void process() throws Exception{
		for (int i = 0; i < pathnames.length; i++) {
			
			String namext = pathnames[i].getName();
			int val = namext.indexOf(".");
			String name = namext.substring(0,val);
			System.out.println(name);
			
			SymbolHm shm = getData(pathnames[i].getCanonicalPath(), name);
			vec.add(shm);
		}	
	}
	
    public SymbolHm getData(String filenameread, String indexname) {

	    SymbolHm shm = new SymbolHm();
	    shm.setSymbol("admin");
	    
	    ArrayList al = new ArrayList();
	    
	InputSource inputsrc = new InputSource(filenameread);    
	    
        DOMParser parser = null;

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
          System.err.println("PortfolioIndexReader: Unable to instantiate parser"); 
        }
        }
	
            // parse file
            try {
                parser.parse(inputsrc);
                Document doc = parser.getDocument();
                Element e = doc.getDocumentElement();

                Node na = null, nb = null, nc = null;
                Element ea = null, eb = null;
                NodeList nl = e.getChildNodes();
                int len = nl.getLength();
                // System.out.println("Len -> " + len);

                for (int k = 0; k < len; k++) {
                    ea = (Element) nl.item(k);
		    NamedNodeMap nnm = ea.getAttributes();
		    na = nnm.getNamedItem("symbol");
		    nb = nnm.getNamedItem("name");
		    nc = nnm.getNamedItem("lasttrade");
		    String symbol_uc = na.getNodeValue();
		    String name = nb.getNodeValue();
		    String symbol = symbol_uc.toLowerCase();
		    String prices = nc.getNodeValue();
		    // System.out.println(symbol + " " + name + " " + prices);
		    
		    // first do the HashMap
		    // prevents symbol duplicates
		    
		    if (!hm.containsKey(symbol)) {
			    hm.put(symbol,name);
		    }
		    
		    // second do the ArrayList
		    al.add(symbol);
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
	    
	    shm.addArrayList(indexname,al);
	    return shm;
    }
    
    /*
    *     This is a HashMap of symbol name pairs
    */
    
    public HashMap getHashMap() {
		return hm;
    }
    
    /*
    *    This is a vector of SymbolHm's
    */
    
    public Vector getVector() {
	    return vec;
    }
    
    public static void main(String[] args) throws Exception {
        PortfolioIndexReader sr = new PortfolioIndexReader();
	
	String dira = AntProperty.etsdata();
        String dirb = dira.concat("\\finance\\cp\\db\\");

	sr.init(dirb);
	sr.process();
	
	Vector v = sr.getVector();
    }
} 
