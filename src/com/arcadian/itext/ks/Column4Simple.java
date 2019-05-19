/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;

import com.arcadian.persist.Industry;
import com.arcadian.persist.Ks;
import com.arcadian.sql.SymbolNameHelper;
import com.arcadian.util.EtsNumberFormat;

/**
Generate out a four column PdfPTable within a document.
*/

public class Column4Simple {

	HashMap snhm = null;
	ArrayList al = null;
	PdfPTable datatable = null;
	
	Color darkgray = new Color(230,230,230);
	Color litegray = new Color(254,254,254);
	
	public void init(ArrayList input, String[] columns) {
		SymbolNameHelper snh = new SymbolNameHelper();
		snhm = snh.getHashMap();
		
		al = input;
		datatable = new PdfPTable(4);
	
		int headerwidths[] = {40, 20, 20, 20};  // percentage
			
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
		datatable.addCell(columns[2]);
		datatable.addCell(columns[3]);
		
		datatable.setHeaderRows(1);  
		// this is the end of the table header
		datatable.getDefaultCell().setBorderWidth(1);
	}
	
	public PdfPTable getPETable() {			
			
			for (int i = 0; i < al.size(); i++) {
				
				Ks ks = (Ks) al.get(i);
				
				double d1 = ks.getForwardpe();
				double d2 = ks.getTrailingpe();
				
				double d3 = d2 - d1;
				
				String s1 = Double.toString(d1);
				String s2 = Double.toString(d2);
				String s3 = Double.toString(d3);
				
				String s3a = EtsNumberFormat.threeDecimalPlaces(s3);
				
				String companyname = (String) snhm.get(ks.getSymbol());
				
				if (d1 > d2) {
					datatable.getDefaultCell().setBackgroundColor(Color.green);
				}
				else {
					if (i % 2 == 1) {
						datatable.getDefaultCell().setBackgroundColor(litegray);
					}
					else {
						datatable.getDefaultCell().setBackgroundColor(darkgray);
					}
				}
				
				datatable.addCell(companyname);
				datatable.addCell(s1);
				datatable.addCell(s2);
				datatable.addCell(s3a);
			}
			return datatable;
	}
}
