/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import com.arcadian.persist.Industry;
import com.arcadian.persist.Eps;
import com.arcadian.persist.Ma;
import com.arcadian.sql.SymbolNameHelper;
import com.arcadian.util.EtsNumberFormat;

/**
Generate out a five column PdfPTable within a document.
*/

public class Column5Eps {

	HashMap snhm = null;
	ArrayList al = null;
	PdfPTable datatable = null;
	String[] columns = null;
	
	public void init(ArrayList input, String[] columns) {
		
		al = input;
		this.columns = columns; 
		
		SymbolNameHelper snh = new SymbolNameHelper();
		snhm = snh.getHashMap();
		
		datatable = new PdfPTable(1);
		
	}
		public PdfPTable initTop(String symbol) {
		
			PdfPTable datatabletop = new PdfPTable(1);
		
			String cname = (String) snhm.get(symbol);
	
			int headerwidths[] = {100};  // percentage
			
			try {
				datatabletop.setWidths(headerwidths);
			}
			catch(Exception e) {
				e.printStackTrace();	
			}
		
			datatabletop.setWidthPercentage(100); // percentage
			datatabletop.getDefaultCell().setPadding(10);
			datatabletop.getDefaultCell().setBorderWidth(3);
			datatabletop.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatabletop.getDefaultCell().setGrayFill(0.9f);
			datatabletop.addCell(cname);
			
			return datatabletop;
		}

		public PdfPTable initBottom() {
		
			PdfPTable datatablebottom = new PdfPTable(5);
	
			int headerwidths[] = {20, 20, 20, 20, 20};  // percentage
			
			try {
				datatablebottom.setWidths(headerwidths);
			}
			catch(Exception e) {
				e.printStackTrace();	
			}
		
			datatablebottom.setWidthPercentage(100); // percentage
			datatablebottom.getDefaultCell().setPadding(6);
			datatablebottom.getDefaultCell().setBorderWidth(1);
			datatablebottom.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
			datatablebottom.addCell(columns[0]);
			datatablebottom.addCell(columns[1]);
			datatablebottom.addCell(columns[2]);
			datatablebottom.addCell(columns[3]);
			datatablebottom.addCell(columns[4]);
		
			datatablebottom.setHeaderRows(1);  
			// this is the end of the table header
			datatablebottom.getDefaultCell().setBorderWidth(1);
		
			return datatablebottom;
	}
		
	public PdfPTable process() {
		
		for(int i = 0; i < al.size(); i++) {
		
			Eps eps = (Eps) al.get(i);
			
			double epsd = eps.getEps();
			String epsx = Double.toString(epsd);
			
			double trailingped = eps.getTrailingpe();
			String trailingpe = Double.toString(trailingped);
			
			double priced = eps.getPrice();
			String price = Double.toString(priced);
			
			double futureepsd = eps.getFutureeps();
			String futureeps = Double.toString(futureepsd);
			
			double forwardped = eps.getForwardpe();
			String forwardpe = Double.toString(forwardped);
			
			double futurepriced = eps.getFutureprice();
			String futureprice = Double.toString(futurepriced);
			
			double pctincreased = eps.getPctincrease();
			String pctincrease = Double.toString(pctincreased);
		
			PdfPTable datatabletop = initTop(eps.getSymbol());
			PdfPTable datatablebottom = initBottom();
		
			datatablebottom.addCell("Trailing");
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(epsx));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(trailingpe));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(price));
			datatablebottom.addCell("");
				
			datatablebottom.addCell("Forward");
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(futureeps));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(forwardpe));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(price));
			datatablebottom.addCell("");
			
			datatablebottom.addCell("Future");
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(futureeps));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(trailingpe));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(futureprice));
			datatablebottom.addCell(EtsNumberFormat.decimalToPercent(pctincrease));
			
			datatable.addCell(datatabletop);
			datatable.addCell(datatablebottom);
			datatable.setSpacingAfter(10.0f);
		}
		return datatable;
	}
	
	public PdfPTable processMovingAverage() {
		
		for(int i = 0; i < al.size(); i++) {
		
			Ma ma = (Ma) al.get(i);
			
			Date datex = ma.getDate();
			String date = datex.toString();
			
			PdfPTable datatabletop = initTop(ma.getSymbol());
			PdfPTable datatablebottom = initBottom();
		
			/*
			datatablebottom.addCell("Trailing");
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(epsx));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(trailingpe));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(price));
			datatablebottom.addCell("");
				
			datatablebottom.addCell("Forward");
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(futureeps));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(forwardpe));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(price));
			datatablebottom.addCell("");
			
			datatablebottom.addCell("Future");
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(futureeps));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(trailingpe));
			datatablebottom.addCell(EtsNumberFormat.twoDecimalPlaces(futureprice));
			datatablebottom.addCell(EtsNumberFormat.decimalToPercent(pctincrease));
			
			datatable.addCell(datatabletop);
			datatable.addCell(datatablebottom);
			*/
			datatable.setSpacingAfter(10.0f);
		}
		return datatable;
	}

}
