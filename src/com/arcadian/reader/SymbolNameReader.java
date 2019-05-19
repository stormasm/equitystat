/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.reader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
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
// import com.arcadian.persist.Snl;
import com.arcadian.xml.sax.SaxWriter;

/**
This reads in a set of XML files which represent the symbol name files
located in the symbol name directory.
*/

public class SymbolNameReader {

    	HashMap hm = null;
	File[] pathnames = null;
	
	public void init(String dir) {
		File dira = new File(dir);
		pathnames = dira.listFiles();
		hm = new HashMap();
	}
	
	public void process() throws Exception{
		for (int i = 0; i < pathnames.length; i++) {
			
			String namext = pathnames[i].getName();
			int val = namext.indexOf(".");
			String name = namext.substring(0,val);
			// System.out.println(name);
			
			getData(pathnames[i].getCanonicalPath(), name);
		}	
	}
	
    public void getData(String filenameread, String list) {

	InputSource inputsrc = SaxWriter.getInputSource(filenameread);    
	    
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
          System.err.println("SnlReader: Unable to instantiate parser"); 
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
		    String symbol_uc = na.getNodeValue();
		    String name = nb.getNodeValue();
		    String symbol = symbol_uc.toLowerCase();
		    hm.put(symbol,name);
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
    }
    
    public HashMap getHashMap() {
	return hm;    
    }
    
    public static void main(String[] args) throws Exception {
        SymbolNameReader snr = new SymbolNameReader();
	
	String dira = AntProperty.etsdata();
        String dirb = dira.concat("\\symbolname\\");

	snr.init(dirb);
	snr.process();
	
	HashMap hm = snr.getHashMap();
	
	for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
		String key = (String) i.next();
		String value = (String) hm.get(key);
		System.out.println(key + " " + value);
	}	
    }
} 
