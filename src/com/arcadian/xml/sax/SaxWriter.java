/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.xml.sax;

import java.lang.reflect.Method;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.arcadian.xml.sax.helpers.AttributesImpl;

/**
Writes out an XML file based on the SAX Api.
*/

public class SaxWriter extends DefaultHandler  implements LexicalHandler {

    /** Lexical handler property id (http://xml.org/sax/properties/lexical-handler). */
    protected static final String LEXICAL_HANDLER_PROPERTY_ID = "http://xml.org/sax/properties/lexical-handler";

    /** Default parser name. */
    protected static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";

    /** Default canonical output (false). */
    protected static final boolean DEFAULT_CANONICAL = false;

    /** Print writer. */
    protected PrintWriter fOut;

    /** Canonical output. */
    protected boolean fCanonical;

    /** Element depth. */
    protected int fElementDepth;
    
    /** Document locator. */
    protected Locator fLocator;
    
    /** Processing XML 1.1 document. */
    protected boolean fXML11 = true;
    
    /** In CDATA section. */
    protected boolean fInCDATA;

    /** Sets whether output is canonical. */
    public void setCanonical(boolean canonical) {
        fCanonical = canonical;
    } // setCanonical(boolean)
   
    /** Sets the output stream for printing. */
    public void setOutput(OutputStream stream, String encoding)
        throws UnsupportedEncodingException {

	FileWriter writer = null;
	
	try {
		writer = new FileWriter(new File("look.xml"));
	}
	catch(IOException e) {
		e.printStackTrace();	
	}
        fOut = new PrintWriter(writer);
    } 

    /** Sets the output writer. */
    public void setOutput(java.io.Writer writer) {

	    
	    
        fOut = writer instanceof PrintWriter
             ? (PrintWriter)writer : new PrintWriter(writer);
    } 

    //
    // ContentHandler methods
    //
    
    /** Set Document Locator. */
    public void setDocumentLocator(Locator locator) {
    	fLocator = locator;
    } // setDocumentLocator(Locator)
    	
    /** Start document. */
    public void startDocument() throws SAXException {

        fElementDepth = 0;
        fXML11 = false;
        fInCDATA = false;
        
    } // startDocument()

    /** Processing instruction. */
    public void processingInstruction(String target, String data)
        throws SAXException {

        if (fElementDepth > 0) {
            fOut.print("<?");
            fOut.print(target);
            if (data != null && data.length() > 0) {
                fOut.print(' ');
                fOut.print(data);
            }
            fOut.print("?>");
            fOut.flush();
        }

    } // processingInstruction(String,String)

    /** Start element. */
    public void startElement(String uri, String local, String raw,
                             Attributes attrs) throws SAXException {

        // Root Element
        if (fElementDepth == 0) {
            if (fLocator != null) {
                fXML11 = "1.1".equals(getVersion());
                fLocator = null;
            }
        }
        
        fElementDepth++;
        fOut.print('<');
        fOut.print(raw);
        if (attrs != null) {
            attrs = sortAttributes(attrs);
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                fOut.print(' ');
                fOut.print(attrs.getQName(i));
                fOut.print("=\"");
                normalizeAndPrint(attrs.getValue(i), true);
                fOut.print('"');
            }
        }
        fOut.print('>');
        fOut.flush();

    } // startElement(String,String,String,Attributes)

    /** Characters. */
    public void characters(char ch[], int start, int length)
        throws SAXException {

        if (!fInCDATA) {
            normalizeAndPrint(ch, start, length, false);
        }
	
        fOut.flush();
    } 

    /** Ignorable whitespace. */
    public void ignorableWhitespace(char ch[], int start, int length)
        throws SAXException {

        characters(ch, start, length);
        fOut.flush();

    } // ignorableWhitespace(char[],int,int);

    /** End element. */
    public void endElement(String uri, String local, String raw)
        throws SAXException {

        fElementDepth--;
        fOut.print("</");
        fOut.print(raw);
        fOut.print('>');
        fOut.flush();

    } // endElement(String)

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
        throw ex;
    } // fatalError(SAXParseException)

    //
    // LexicalHandler methods
    //

    /** Start DTD. */
    public void startDTD(String name, String publicId, String systemId)
        throws SAXException {
    } // startDTD(String,String,String)

    /** End DTD. */
    public void endDTD() throws SAXException {
    } // endDTD()

    /** Start entity. */
    public void startEntity(String name) throws SAXException {
    } // startEntity(String)

    /** End entity. */
    public void endEntity(String name) throws SAXException {
    } // endEntity(String)

    /** Start CDATA section. */
    public void startCDATA() throws SAXException {
        if (!fCanonical) {
            fOut.print("<![CDATA[");
            fInCDATA = true;
        }
    } // startCDATA()

    /** End CDATA section. */
    public void endCDATA() throws SAXException {
        if (!fCanonical) {
            fInCDATA = false;
            fOut.print("]]>");
        }
    } // endCDATA()

    /** Comment. */
    public void comment(char ch[], int start, int length) throws SAXException {
        if (!fCanonical && fElementDepth > 0) {
            fOut.print("<!--");
            for (int i = 0; i < length; ++i) {
                fOut.print(ch[start+i]);
            }
            fOut.print("-->");
            fOut.flush();
        }
    } // comment(char[],int,int)

    //
    // Protected methods
    //

    /** Returns a sorted list of attributes. */
    protected Attributes sortAttributes(Attributes attrs) {

        AttributesImpl attributes = new AttributesImpl();

        int len = (attrs != null) ? attrs.getLength() : 0;
        for (int i = 0; i < len; i++) {
            String name = attrs.getQName(i);
            int count = attributes.getLength();
            int j = 0;
            while (j < count) {
                if (name.compareTo(attributes.getQName(j)) < 0) {
                    break;
                }
                j++;
            }
            attributes.insertAttributeAt(j, name, attrs.getType(i),
                                         attrs.getValue(i));
        }

        return attributes;

    } // sortAttributes(AttributeList):AttributeList

    /** Normalizes and prints the given string. */
    protected void normalizeAndPrint(String s, boolean isAttValue) {

        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            normalizeAndPrint(c, isAttValue);
        }

    } // normalizeAndPrint(String,boolean)

    /** Normalizes and prints the given array of characters. */
    protected void normalizeAndPrint(char[] ch, int offset, int length, boolean isAttValue) {
        for (int i = 0; i < length; i++) {
            normalizeAndPrint(ch[offset + i], isAttValue);
        }
    } // normalizeAndPrint(char[],int,int,boolean)

    /** Normalizes and print the given character. */
    protected void normalizeAndPrint(char c, boolean isAttValue) {

        switch (c) {
	    // The space character gets swallowed up and doesn't get output	
	    case ' ':{
		break;    
	    }
            case '<': {
                fOut.print("&lt;");
                break;
            }
            case '>': {
                fOut.print("&gt;");
                break;
            }
            case '&': {
                fOut.print("&amp;");
                break;
            }
            case '"': {
                // A '"' that appears in character data 
                // does not need to be escaped.
                if (isAttValue) {
                    fOut.print("&quot;");
                }
                else {
                    fOut.print("\"");
                }
                break;
            }
            case '\r': {
		    
            	// If CR is part of the document's content, it
            	// must not be printed as a literal otherwise
            	// it would be normalized to LF when the document
            	// is reparsed.
            	// fOut.print("&#xD;");
            	break;
            }
            case '\n': {
		    /*
                if (fCanonical) {
                    fOut.print("&#xA;");
                    break;
                }
		*/
		break;
                // else, default print char
            }
            default: {
           	// In XML 1.1, control chars in the ranges [#x1-#x1F, #x7F-#x9F] must be escaped.
            	//
            	// Escape space characters that would be normalized to #x20 in attribute values
            	// when the document is reparsed.
            	//
            	// Escape NEL (0x85) and LSEP (0x2028) that appear in content 
            	// if the document is XML 1.1, since they would be normalized to LF 
            	// when the document is reparsed.
            	if (fXML11 && ((c >= 0x01 && c <= 0x1F && c != 0x09 && c != 0x0A) 
            	    || (c >= 0x7F && c <= 0x9F) || c == 0x2028)
            	    || isAttValue && (c == 0x09 || c == 0x0A)) {
            	    fOut.print("&#x");
            	    fOut.print(Integer.toHexString(c).toUpperCase());
            	    fOut.print(";");
                }
                else {
                    fOut.print(c);
                }        
            }
        }
    } // normalizeAndPrint(char,boolean)

    /** Prints the error message. */
    protected void printError(String type, SAXParseException ex) {

        System.err.print("[");
        System.err.print(type);
        System.err.print("] ");
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

    } // printError(String,SAXParseException)

    /** Extracts the XML version from the Locator. */
    protected String getVersion() {
        if (fLocator == null) {
            return null;
        }
        String version = null;
        Method getXMLVersion = null;
        try {
            getXMLVersion = fLocator.getClass().getMethod("getXMLVersion", new Class[]{});
            // If Locator implements Locator2, this method will exist.
            if (getXMLVersion != null) {
		// This is what this method used to be before I changed it
		// because the compiler was complaining...  
		// mia june 22, 2005
                // version = (String) getXMLVersion.invoke(fLocator, null);
		version = (String) getXMLVersion.invoke(fLocator);
            }
        } 
        catch (Exception e) { 
            // Either this locator object doesn't have 
            // this method, or we're on an old JDK.
        }
        return version;
    } // getVersion()

    //
    // Main
    //

    public static void save(String filename) {
	
	// variables
        SaxWriter writer = null;
        XMLReader parser = null;

        // create parser
        try {
                 parser = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
        }
        catch (Exception e) {
                System.err.println("error: Unable to instantiate parser ("+DEFAULT_PARSER_NAME+")");
                e.printStackTrace(System.err);
        }

            // setup writer
            if (writer == null) {
                writer = new SaxWriter();
                try {
                    writer.setOutput(System.out, "UTF8");
                }
                catch (UnsupportedEncodingException e) {
                    System.err.println("error: Unable to set output. Exiting.");
                    System.exit(1);
                }
            }

            // set parser
            parser.setContentHandler(writer);
            parser.setErrorHandler(writer);
            try {
                parser.setProperty(LEXICAL_HANDLER_PROPERTY_ID, writer);
            }
            catch (SAXException e) {
                // ignore
            }

            // parse file
            writer.setCanonical(DEFAULT_CANONICAL);
            try {
                parser.parse(filename);
            }
            catch (SAXParseException e) {
                // ignore
            }
            catch (Exception e) {
                System.err.println("error: Parse error occurred - "+e.getMessage());
                if (e instanceof SAXException) {
                    Exception nested = ((SAXException)e).getException();
                    if (nested != null) {
                        e = nested;
                    } 
                }
                e.printStackTrace(System.err);
            }
    }
    
    public static InputSource getInputSource(String filename) {
	
	// variables
        SaxWriter writer = null;
        XMLReader parser = null;

	CharArrayWriter caw = new CharArrayWriter();
	
        // create parser
        try {
                 parser = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
        }
        catch (Exception e) {
                System.err.println("error: Unable to instantiate parser ("+DEFAULT_PARSER_NAME+")");
                e.printStackTrace(System.err);
        }

            // setup writer
            if (writer == null) {
                writer = new SaxWriter();
                try {
                    writer.setOutput(caw);
                }
                catch (Exception e) {
			e.printStackTrace();
                }
            }

            // set parser
            parser.setContentHandler(writer);
            parser.setErrorHandler(writer);
            try {
                parser.setProperty(LEXICAL_HANDLER_PROPERTY_ID, writer);
            }
            catch (SAXException e) {
                // ignore
            }

            // parse file
            writer.setCanonical(DEFAULT_CANONICAL);
            try {
                parser.parse(filename);
            }
            catch (SAXParseException e) {
                // ignore
            }
            catch (Exception e) {
                System.err.println("error: Parse error occurred - "+e.getMessage());
                if (e instanceof SAXException) {
                    Exception nested = ((SAXException)e).getException();
                    if (nested != null) {
                        e = nested;
                    } 
                }
                e.printStackTrace(System.err);
            }
	    
	    char[] cx = caw.toCharArray();
	    CharArrayReader car = new CharArrayReader(cx);
	    InputSource is = new InputSource(car);
	    return is;
    }
    
    /*
    public static void main(String argv[]) {
	String filename = argv[0];
	save(filename);
    }
    */
    
    public static void main(String argv[]) {
	String filename = argv[0];
	InputSource is = getInputSource(filename);
    }
}  
