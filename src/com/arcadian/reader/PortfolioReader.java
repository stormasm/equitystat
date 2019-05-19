/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.arcadian.finance.symbol.SymbolHm;
import com.arcadian.xml.sax.SaxWriter;

/**
This reads in a set of XML files which represent the portfolios
located in the portfolio directory.

Each file is SymbolHm, and if there are 5 files, 
there are 5 SymbolHm's stored in a Vector.

Each SymbolHm has the username and a
HashMap of keys representing the portfolio name
followed by a value which is an ArrayList of symbols
in that particular portfolio. 
*/

public class PortfolioReader {

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
		vec = new Vector();
	}
	
	public Vector getVector() {
		return vec;	
	}

	public SymbolHm getNextSymbolHm() throws Exception{

		if (count < total) {			
			String s1 = pathnames[count].getName();
			int index = s1.indexOf(".xml");
			String s2 = s1.substring(0,index);
			// System.out.println(s2);
			SymbolHm shm = getSymbolHm(pathnames[count].getCanonicalPath(),s2);
			count = count + 1;
			return shm;
		}
		return null;
	}


    public SymbolHm getSymbolHm(String filenameread, String symbol) {

	SymbolHm shm = new SymbolHm();
	shm.setSymbol(symbol);
	
	// This way will also work if you don't want to pre-process with the SaxWriter
	// InputSource inputsrc = new InputSource(filenameread);
	InputSource inputsrc = SaxWriter.getInputSource(filenameread);
	//
	//  Uncomment the next two lines if you need to debug and see
	//  what the file looks like
	//
	// SaxWriter.save(filenameread);
	// System.exit(1);
	    
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
		
		for (int k = 0; k < len; k++) {
			
		    String portfolioname = "";	
                    na = (Node) nl.item(k);
		    // Print out the symbol here for debugging purposes
		    String qq = na.getNodeName();
		    
		    if (na.hasAttributes()) {
			    Element ex = (Element) na;
			    portfolioname = ex.getAttribute("name");
			    // System.out.println(portfolioname);
		    }
		    
		    NodeList nla = na.getChildNodes();
		    int lena = nla.getLength();
		    // System.out.println("lena -> " + lena);
		    
		    
		    ArrayList al = new ArrayList();
		    for (int j = 0; j < lena; j++) {
			nb = (Node) nla.item(j);
			/*
			if (nb.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Got an element node");	
			}
			*/
			
			NodeList nlb = nb.getChildNodes();
			// int lenb = nlb.getLength();
			// System.out.println("lenb -> " + lenb);
			
			nc = (Node) nlb.item(0);
			String sym = (String) nc.getNodeValue();
			// System.out.println(sym);
			al.add(sym);
			
		    }
		    shm.addArrayList(portfolioname,al);
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
    
    public void process() {
	    SymbolHm shm = null;
	    try {
		    while ((shm = getNextSymbolHm()) != null) {
			    // shm.printArrayList();
			    vec.add(shm);
		    }
	    }
	    catch(Exception e) {
		e.printStackTrace();    
	    }
    }
   
    public static void main(String[] args) throws Exception {
	
        PortfolioReader pr = new PortfolioReader();
	String dira = AntProperty.etsdata();
        String dirb = dira.concat("\\portfolio\\");
	pr.init(dirb);
	pr.process();
	Vector v = pr.getVector();
	
	for (int i = 0; i < v.size(); i++) {
		SymbolHm shm = (SymbolHm) v.get(i);
		shm.printArrayList();
	}
	
	System.out.println("Done Reading Portfolios");
    }
}
