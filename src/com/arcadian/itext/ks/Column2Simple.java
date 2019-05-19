/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.util.ArrayList;
import java.util.HashMap;

import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;

import com.arcadian.persist.Industry;
import com.arcadian.persist.Ks;
import com.arcadian.sql.SymbolNameHelper;
import com.arcadian.util.EtsNumberFormat;

/**
Generate out a two column PdfPTable within a document.
*/

public class Column2Simple {

	HashMap snhm = null;
	ArrayList al = null;
	PdfPTable datatable = null;
	
	public void init(ArrayList input, String[] columns) {
		SymbolNameHelper snh = new SymbolNameHelper();
		snhm = snh.getHashMap();
		
		al = input;
		datatable = new PdfPTable(2);
	
		int headerwidths[] = {50, 50};  // percentage
			
		try {
			datatable.setWidths(headerwidths);
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		
		datatable.setWidthPercentage(100); // percentage
		datatable.getDefaultCell().setPadding(3);
		datatable.getDefaultCell().setBorderWidth(2);
		datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
		datatable.addCell(columns[0]);
		datatable.addCell(columns[1]);
		
		datatable.setHeaderRows(1);  
		// this is the end of the table header
		datatable.getDefaultCell().setBorderWidth(1);
	}
	
	public PdfPTable getMarketCapTable() {			
			
			for (int i = 0; i < al.size(); i++) {
				
				Ks ks = (Ks) al.get(i);
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				
				double d1 = ks.getMarketcap();
				double d2 = d1/1000.0;
				
				String s =  Double.toString(d2);
				String s1 = EtsNumberFormat.threeDecimalPlaces(s);
				
				String companyname = (String) snhm.get(ks.getSymbol());
				
				datatable.addCell(companyname);
				datatable.addCell(s1);
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				}
			}
			return datatable;
	}
	
	public PdfPTable getSymbolNameTable() {			
			
			for (int i = 0; i < al.size(); i++) {
				
				Ks ks = (Ks) al.get(i);
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				
				String symbol = ks.getSymbol();
				String cname = (String) snhm.get(ks.getSymbol());
				
				datatable.addCell(symbol);
				datatable.addCell(cname);
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				}
			}
			return datatable;
	}
	
	public PdfPTable getIndustryTable() {
		
			for (int i = 0; i < al.size(); i++) {
			
				Industry ind = (Industry) al.get(i);
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				
				String cname101 = (String) snhm.get(ind.getSymbol());
				String industry = (String) ind.getIndname();
				
				datatable.addCell(cname101);
				datatable.addCell(industry);
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				}
			}
			return datatable;
	}
}

