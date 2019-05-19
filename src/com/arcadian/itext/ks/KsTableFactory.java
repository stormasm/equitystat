/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.util.ArrayList;
import com.lowagie.text.pdf.PdfPTable;

/**
Static methods used as helper classes in the Sector document.
*/

public abstract class KsTableFactory {

	public static String[] industrycolumns = {"Company Name", "Industry"};
	public static String[] mcapcolumns = {"Company", "Market Capitalization (Billions)"};
	public static String[] mcapevcolumns = {"Company Name","Market Cap","Enterprise Value"};
	public static String[] pecolumns = {"Company Name","Forward PE","Trailing PE", "Difference"};
	public static String[] symbolnamecolumns = {"Symbol", "Company Name"};
	
	public static PdfPTable getMarketCap(ArrayList al) {
			Column2Simple c2s = new Column2Simple();
			c2s.init(al,mcapcolumns);
			PdfPTable back = c2s.getMarketCapTable();
			return back;
	}
	
	public static PdfPTable getMarketCapEv(ArrayList al) {
			Column3Simple c3s = new Column3Simple();
			c3s.init(al,mcapevcolumns);
			PdfPTable back = c3s.getMarketCapEvTable();
			return back;
	}
	
	public static PdfPTable getPE(ArrayList al) {
			Column4Simple c4s = new Column4Simple();
			c4s.init(al,pecolumns);
			PdfPTable back = c4s.getPETable();
			return back;
	}
	
	public static PdfPTable getSymbolName(ArrayList al) {
			Column2Simple c2s = new Column2Simple();
			c2s.init(al,symbolnamecolumns);
			PdfPTable back = c2s.getSymbolNameTable();
			return back;
	}
	
	public static PdfPTable getIndustry(ArrayList al) {
			Column2Simple c2s = new Column2Simple();
			c2s.init(al,industrycolumns);
			PdfPTable back = c2s.getIndustryTable();
			return back;
	}
}
