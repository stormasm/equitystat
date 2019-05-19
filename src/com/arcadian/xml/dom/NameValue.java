/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.xml.dom;

import com.arcadian.io.FileUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.SerializerFactory;
import org.apache.xml.serialize.XMLSerializer;

import java.io.IOException;
import java.io.StringWriter;

/**
Creates an XML document with name value pairs.
*/

public class NameValue {

    Document doc = null;
    Element root = null;
    Element pair = null;

    // Attributes for pair
    Attr name = null;
    Attr value = null;

    String xsym = "";

    public NameValue() {}

    public void init(String symbol) {
        doc = new DocumentImpl();
        // Create Root Element
        root = doc.createElement(symbol);   
        doc.appendChild(root);
        xsym = new String(symbol);
    }

    public void name(String x) {

        // Create Top Level Elements

        // --------------------------------------------------------
        //
        // go back to old way 
        //
        // -------------------------------------------------------
        // pair = doc.createElement("pair");

        // --------------------------------------------------------
        //
        // this is the new way
        //
        // -------------------------------------------------------
        pair = doc.createElement(xsym);

        // Create Attributes for pair

        name = doc.createAttribute("name");
        value = doc.createAttribute("value");

        // Set Attributes for valuation

        pair.setAttributeNode(name);
        pair.setAttributeNode(value);

        // Append the elements to root document
        root.appendChild(pair);

        // Get rid of the white space before storing
        String xtrim = x.trim();
        pair.setAttribute("name",xtrim);
    }

    public void value(String x) {
        pair.setAttribute("value",x);
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

        FileUtil.store(stringOut.toString(), filename);
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


    public static void main( String[] argv ) {
        NameValue nv = new NameValue();
        nv.init("top");
        nv.name("michael");
        nv.value("brownhair");
        nv.name("iris");
        nv.value("greenhair");
        nv.name("michele");
        nv.value("redhair");
        nv.store("outfile.xml");
    }
}

