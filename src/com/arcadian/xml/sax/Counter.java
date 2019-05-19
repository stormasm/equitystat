/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.xml.sax;

import java.io.PrintWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
Counts the specific number of tags in an XML Document.
*/

public class Counter extends DefaultHandler {

    /** Default parser name. */
    protected static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";

    /** Number of elements. */
    protected long fElements;

    /** Number of attributes. */
    protected long fAttributes;

    /** Number of characters. */
    protected long fCharacters;

    /** Number of ignorable whitespace characters. */
    protected long fIgnorableWhitespace;

    /** Number of characters of tags. */
    protected long fTagCharacters;

    /** Number of other content characters for the "tagginess" calculation. */
    protected long fOtherCharacters;

    /** Prints the results. */
    public void printResults(PrintWriter out) {
        out.print(" (");
        out.print(fElements);
        out.print(" elems, ");
        out.print(fAttributes);
        out.print(" attrs, ");
        out.print(fIgnorableWhitespace);
        out.print(" spaces, ");
        out.print(fCharacters);
        out.print(" chars)");
        out.println();
        out.flush();
    } 

    public void startDocument() throws SAXException {
        fElements = 0;
        fAttributes = 0;
        fCharacters = 0;
        fIgnorableWhitespace = 0;
        fTagCharacters = 0;
    } 

    public void startElement(String uri, String local, String raw,
                             Attributes attrs) throws SAXException {

        fElements++;
        fTagCharacters++; // open angle bracket
        fTagCharacters += raw.length();
        if (attrs != null) {
            int attrCount = attrs.getLength();
            fAttributes += attrCount;
            for (int i = 0; i < attrCount; i++) {
                fTagCharacters++; // space
                fTagCharacters += attrs.getQName(i).length();
                fTagCharacters++; // '='
                fTagCharacters++; // open quote
                fOtherCharacters += attrs.getValue(i).length();
                fTagCharacters++; // close quote
            }
        }
        fTagCharacters++; // close angle bracket

    } // startElement(String,String,StringAttributes)

    /** Characters. */
    public void characters(char ch[], int start, int length)
        throws SAXException {

        fCharacters += length;

    } // characters(char[],int,int);

    /** Ignorable whitespace. */
    public void ignorableWhitespace(char ch[], int start, int length)
        throws SAXException {

        fIgnorableWhitespace += length;

    } // ignorableWhitespace(char[],int,int);

    /** Processing instruction. */
    public void processingInstruction(String target, String data)
        throws SAXException {
        fTagCharacters += 2; // "<?"
        fTagCharacters += target.length();
        if (data != null && data.length() > 0) {
            fTagCharacters++; // space
            fOtherCharacters += data.length();
        }
        fTagCharacters += 2; // "?>"
    } // processingInstruction(String,String)

    /** Warning. */
    public void warning(SAXParseException ex) throws SAXException {
        printError("Warning", ex);
    } // warning(SAXParseException)

    /** Error. */
    public void error(SAXParseException ex) throws SAXException {
        printError("Error", ex);
    } // error(SAXParseException)

    /** Fatal error. */
    public void fatalError(SAXParseException ex) throws SAXException {
        printError("Fatal Error", ex);
        //throw ex;
    } // fatalError(SAXParseException)

    /** Prints the error message. */
    protected void printError(String type, SAXParseException ex) {

        System.err.print("[");
        System.err.print(type);
        System.err.print("] ");
        if (ex== null) {
            System.out.println("!!!");
        }
        String systemId = ex.getSystemId();
        if (systemId != null) {
            int index = systemId.lastIndexOf('/');
            if (index != -1)
                systemId = systemId.substring(index + 1);
            System.err.print(systemId);
        }
        System.err.print(':');
        System.err.print(ex.getLineNumber());
        System.err.print(':');
        System.err.print(ex.getColumnNumber());
        System.err.print(": ");
        System.err.print(ex.getMessage());
        System.err.println();
        System.err.flush();
    }

    public static void main(String argv[]) {

	    if (argv.length == 0) {
		    System.out.println("No arguments");
		    System.exit(1);
	    }

            Counter counter = new Counter();
            PrintWriter out = new PrintWriter(System.out);
            XMLReader parser = null;

            // create parser
            try {
                  parser = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
            }
            catch (Exception e) {
                  System.err.println("error: Unable to instantiate parser ("+DEFAULT_PARSER_NAME+")");
            }

            // parse file
            parser.setContentHandler(counter);
            parser.setErrorHandler(counter);
            try {
                parser.parse(argv[0]);
                counter.printResults(out);
            }
            catch (SAXParseException e) {
                // ignore
            }
            catch (Exception e) {
                System.err.println("error: Parse error occurred - "+e.getMessage());
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
}
