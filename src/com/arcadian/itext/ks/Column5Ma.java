/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.awt.Color;
// import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import com.arcadian.finance.symbol.SymbolList;
import com.arcadian.persist.Industry;
import com.arcadian.persist.Ma;
import com.arcadian.sql.SymbolNameHelper;
import com.arcadian.util.EtsNumberFormat;


/**
Generate out a five column PdfPTable within a document.
*/

public class Column5Ma {

	HashMap snhm = null;
	SymbolList sl = null;
	PdfPTable datatable = null;
	String[] columns = null;
	
	public void init(SymbolList input, String[] columns) {
		
		sl = input;
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

		public PdfPTable initMiddle() {
		
			PdfPTable datatablemiddle = new PdfPTable(6);
	
			int headerwidths[] = {18, 18, 18, 18, 18, 10};  // percentage
			
			try {
				datatablemiddle.setWidths(headerwidths);
			}
			catch(Exception e) {
				e.printStackTrace();	
			}
		
			datatablemiddle.setWidthPercentage(100); // percentage
			datatablemiddle.getDefaultCell().setPadding(6);
			datatablemiddle.getDefaultCell().setBorderWidth(1);
			datatablemiddle.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
			datatablemiddle.addCell(columns[0]);
			datatablemiddle.addCell(columns[1]);
			datatablemiddle.addCell(columns[2]);
			datatablemiddle.addCell(columns[3]);
			datatablemiddle.addCell(columns[4]);
			datatablemiddle.addCell(columns[5]);
		
			datatablemiddle.setHeaderRows(1);  
			// this is the end of the table header
			datatablemiddle.getDefaultCell().setBorderWidth(1);
		
			return datatablemiddle;
	}
	
	public PdfPTable initBottom() {
		
			PdfPTable datatablebottom = new PdfPTable(6);
	
			int headerwidths[] = {18, 18, 18, 18, 18, 10};  // percentage
			
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
			datatablebottom.addCell(columns[5]);
		
			datatablebottom.setHeaderRows(1);  
			// this is the end of the table header
			datatablebottom.getDefaultCell().setBorderWidth(1);
		
			return datatablebottom;
	}
		
	public PdfPTable processMovingAverage() {
		
			String symbol = sl.getSymbol();
			List list = sl.getList();
	
			PdfPTable datatabletop = initTop(symbol);
			PdfPTable datatablemiddle = initMiddle();
			PdfPTable datatablebottom = initBottom();
			
			datatable.addCell(datatabletop);
		
			/**
			Get last 7 entries
			*/
			
			int calcall = list.size();
			int recent7 = 0;
			
			if (calcall >= 7) {
				recent7 = list.size() - 7;
			}
			else {
				recent7 = list.size();	
			}
		
			for (int k = recent7; k < list.size(); k++) {
				Ma ma = (Ma) list.get(k);
			
				// symbol
				datatablemiddle.addCell(symbol);
			
				// date
				Date datex = ma.getDate();
				String date = datex.toString();
				datatablemiddle.addCell(date);
				
				// direction
				String up = ma.getDirectionString();
				datatablemiddle.addCell(up);
				
				// moving average
				double maval = ma.getMa();
				String mavals = Double.toString(maval);
				datatablemiddle.addCell(mavals);
				
				// adjusted close
				double ac = ma.getAdjclose();
				String acs = Double.toString(ac);
				datatablemiddle.addCell(acs);
				
				// number of days
				int dx = ma.getDays();
				String dxs = Integer.toString(dx);
				datatablemiddle.addCell(dxs);
			}
			
			datatable.addCell(datatablemiddle);
		
		
			for (int k = 0; k < list.size(); k++) {
				Ma ma = (Ma) list.get(k);
			
				// symbol
				datatablebottom.addCell(symbol);
			
				// date
				Date datex = ma.getDate();
				String date = datex.toString();
				datatablebottom.addCell(date);
				
				// direction
				String up = ma.getDirectionString();
				datatablebottom.addCell(up);
				
				// moving average
				double maval = ma.getMa();
				String mavals = Double.toString(maval);
				datatablebottom.addCell(mavals);
				
				// adjusted close
				double ac = ma.getAdjclose();
				String acs = Double.toString(ac);
				datatablebottom.addCell(acs);
				
				// number of days
				int dx = ma.getDays();
				String dxs = Integer.toString(dx);
				datatablebottom.addCell(dxs);
			}
			
			datatable.addCell(datatablebottom);
			
		return datatable;
	}

}
