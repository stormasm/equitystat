/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.symbol;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.SerializerFactory;
import org.apache.xml.serialize.XMLSerializer;

import com.arcadian.io.FileUtil;
import com.arcadian.sql.SymbolNameHelper;
import com.arcadian.util.AsciiFilter;

/**
*
*   Reads all of the symbols out of the database and writes them to an XML file.
*   This is a way to persist symbol information to one file and then later
*   use this file to re-build the symbol table in the database.
*
*/

public class SymbolXml {

    Document doc = null;
    Element root = null;
    Element component = null;

    // Attributes for component
    Attr symbol = null;
    Attr name = null;
    Attr lasttrade = null;

    String xsym = "";

    public void init(String toptag, String childtag) {
        doc = new DocumentImpl();
        // Create Root Element
        root = doc.createElement(toptag);   
        doc.appendChild(root);
        xsym = new String(childtag);
    }

    public void process(HashMap hm) {

	for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
		component = doc.createElement(xsym);
		String key = (String) i.next();
		String value = (String) hm.get(key);
		component.setAttribute("symbol",key);
		component.setAttribute("name",value);
		root.appendChild(component);
	}	
    }

    public void store(String filename) {

        // Serialize DOM
        OutputFormat format  = new OutputFormat(doc);  

        // Writer will be a String
        StringWriter  stringOut = new StringWriter();   
        XMLSerializer serial = new XMLSerializer(stringOut,format);

        // As a DOM Serializer

        try {
            serial.asDOMSerializer();
            serial.serialize(doc.getDocumentElement());
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        // Spit out DOM as a String
        // System.out.println( "STRXML = " + stringOut.toString() );
	
	
	String q1 = stringOut.toString();
	char[] beforefilter = q1.toCharArray();
	char[] afterfilter = AsciiFilter.filter(beforefilter,128);	
        FileUtil.store(afterfilter, filename);
    }
    
      public char[] getCharArray() {

        // Serialize DOM
        OutputFormat format  = new OutputFormat(doc);  

        // Writer will be a String
        StringWriter  stringOut = new StringWriter();   
        XMLSerializer serial = new XMLSerializer(stringOut,format);

        // As a DOM Serializer

        try {
            serial.asDOMSerializer();
            serial.serialize(doc.getDocumentElement());
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        // Spit out DOM as a String
        // System.out.println( "STRXML = " + stringOut.toString() ); 

        String x = stringOut.toString();
	char[] y = x.toCharArray();
	return y;
    }

/*
    public static void main( String[] argv ) {
        SymbolXml nv = new SymbolXml();
        nv.init("sn","component");
	
	HashMap hm = new HashMap();
	hm.put("eclg","ECollege");
	hm.put("hp","Hewlett Packard");
	hm.put("xlnx","Xilinx");
	hm.put("vitr","Vitria");
	
        nv.process(hm);
        nv.store("outfile.xml");
    }
*/

/**
*
*   Read all of the symbols out of the database and write them to an XML file
*
*/

    public static void main( String[] argv ) {
        SymbolXml nv = new SymbolXml();
        nv.init("sn","component");

		//
		//  Read all of the symbols out of the symbolname table in the database
		// 
		SymbolNameHelper snh = new SymbolNameHelper();
		try {
			snh.init();
		}
		catch (Exception e) {
			e.printStackTrace();	
		}

		HashMap hm = snh.getHashMap();
/*	
		for (Iterator i = hm.keySet().iterator(); i.hasNext(); ) {
			String key = (String) i.next();
			String value = (String) hm.get(key);
			System.out.println(key + " " + value);
		}	
*/		
		snh.factory.close();
	
        nv.process(hm);
        nv.store("outfile.xml");
    }
}

