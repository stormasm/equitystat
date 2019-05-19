/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
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
import com.arcadian.finance.symbol.SymbolArrayList;
import com.arcadian.xml.sax.SaxWriter;

/**
This reads in a set of Csv files which represent the portfolios
located in the csv directory.

Each file is SymbolArrayList, and if there are 5 files, 
there are 5 SymbolArrayList's stored in a Vector.

Each SymbolArrayList has the username and a
HashMap of keys representing the portfolio name
followed by a value which is an ArrayList of symbols
in that particular portfolio. 
*/

public class PortfolioReaderCsv {

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

	public SymbolArrayList getNextSymbolArrayList() throws Exception{

		if (count < total) {			
			String s1 = pathnames[count].getName();
			int index = s1.indexOf(".csv");
			String s2 = s1.substring(0,index);
			// System.out.println(s2);
			SymbolArrayList shm = getSymbolArrayList(pathnames[count].getCanonicalPath(),s2);
			count = count + 1;
			return shm;
		}
		return null;
	}


    public SymbolArrayList getSymbolArrayList(String filenameread, String symbol) {

	    System.out.println(filenameread);
	    
	    FileReader fr = null;
	    
	SymbolArrayList shm = new SymbolArrayList();
	shm.setSymbol(symbol);
	
	try {
		fr = new FileReader(filenameread);
	
	BufferedReader br = new BufferedReader(fr);
	LineNumberReader lnr = new LineNumberReader(br);
	
	String x = null;
	
	// Read 3 lines first before actually getting the real data
	lnr.readLine();
	lnr.readLine();
	lnr.readLine();
	
	while ((x = lnr.readLine()) != null) {
		String y = getSymbol(x);
		
		// convert to lowercase
		String y1 = y.toLowerCase();
		
		if (y1.compareTo("skip") != 0) {
			shm.addParam(y1);
		}
	}
	
	}
	catch (Exception e) {
		e.printStackTrace();	
	}
	
        return shm;
    }
    
    public String getSymbol(String input) {
	    
	String y = null;    
	int x = input.indexOf(",");
	
	try {
		y = input.substring(0,x);
	}
	catch (StringIndexOutOfBoundsException e) {
		// System.out.println(input);
		return ("skip");
	}
	
	return y;
    }
    
    public void process() {
	    SymbolArrayList shm = null;
	    try {
		    while ((shm = getNextSymbolArrayList()) != null) {
			    // shm.printArrayList();
			    vec.add(shm);
		    }
	    }
	    catch(Exception e) {
		e.printStackTrace();    
	    }
    }
   
    public static void main(String[] args) throws Exception {
	
        PortfolioReaderCsv pr = new PortfolioReaderCsv();
	String dira = AntProperty.etsdata();
        String dirb = dira.concat("\\csv\\");
	pr.init(dirb);
	pr.process();
	Vector v = pr.getVector();
	
	for (int i = 0; i < v.size(); i++) {
		SymbolArrayList shm = (SymbolArrayList) v.get(i);
		shm.print();
	}
	
	System.out.println("Done Reading Portfolios");
    }
}
