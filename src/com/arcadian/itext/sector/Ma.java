/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.sector;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import com.arcadian.finance.symbol.SymbolList;
import com.arcadian.itext.ks.MaTableFactory;
import com.arcadian.itext.misc.AgiTextHashMaps;
import com.arcadian.itext.misc.AgiTextStrings;
import com.arcadian.itext.misc.MaTextStrings;
import com.arcadian.persist.Ks;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.sql.CriteriaMa;
import com.arcadian.sql.CriteriaIndustry;
import com.arcadian.sql.CriteriaKs;
import com.arcadian.sql.SymbolNameHelper;

/**
The organization and architecture of the Moving Average Report.
*/

public class Ma {

	public static void process(String reportname) throws Exception {
			
			SymbolNameHelper snh = new SymbolNameHelper();
			snh.init();
			HashMap snhm = snh.getHashMap();
		
			// Set up the filename that will be written to the disk
			
			String filename1 = "Ma".concat(reportname);
			String filename = filename1.concat(".pdf");
			
			// Initialize the Title and Commentary HashMaps
			HashMap titlehm = AgiTextHashMaps.getTitleHashMap();
			HashMap commentaryhm = AgiTextHashMaps.getCommentaryHashMap();
		
			// In this case, the portfolio name and the file name are the same
			ArrayList limit = PortfolioHelper.getPortfolio(reportname);		
		
			// Instantiate the document
			Document document = new Document(PageSize.A4, 25, 25, 35, 25);
			
			// Tie the document and the filename together
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

			// Get the title for this document
			String documentitle = (String) titlehm.get(filename1);
			
			// Get the fonts for the titles on each page and the disclaimers on each page
			Font head = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, Color.blue);
			Font disclaimfont = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, Color.gray);
			
			// Set up the Header
			Paragraph one = new Paragraph(documentitle,head);
			HeaderFooter top = new HeaderFooter(one, false);
			top.setAlignment(Element.ALIGN_CENTER);
			document.setHeader(top);
			
			// Set up the footer
			Phrase two = new Phrase(AgiTextStrings.disclaimer,disclaimfont);
			HeaderFooter bottom = new HeaderFooter(two,false);
			document.setFooter(bottom);
			
			/**
				Do all of the stuff above prior to opening the document and adding stuff to it
			*/
			
			document.open();
			
			// ----------------------  begin commentary at the start of the document
			
			// Keep this here if you want to do custom commentary per report
			// Note this is a slightly different model as you are using a HashMap
			// to get at the data
			// String commentary = (String) commentaryhm.get(filename1);

			Paragraph px = new Paragraph(MaTextStrings.blankline);
			Paragraph p0 = new Paragraph(MaTextStrings.commentarymap0);
			Paragraph p1 = new Paragraph(MaTextStrings.commentarymap1);
			Paragraph p2 = new Paragraph(MaTextStrings.commentarymap2);
			Paragraph p3 = new Paragraph(MaTextStrings.commentarymap3);
			Paragraph p4 = new Paragraph(MaTextStrings.commentarymap4);
			
			document.add(p0);
			document.add(px);
			document.add(p1);
			document.add(px);
			document.add(p2);
			document.add(px);
			document.add(p3);
			document.add(px);
			document.add(p4);
			
			// ----------------------  end commentary at the start of the document
			
	                document.newPage();
		
			//------------------------   begin moving average
			
			/**
			This returns a Vector of SymbolLists.
			Each SymbolList contains a symbol and an List of Ma's
			*/
			Vector vecma = CriteriaMa.getDataAscVector(limit);
			
			// This is the first page description
			document.add(MaTableFactory.getMaTutorial());
			document.newPage();
			
			// This is the table set of Ma's.
			for (int i = 0; i < vecma.size(); i++) {
				SymbolList sl = (SymbolList) vecma.get(i);
				String symbol = sl.getSymbol();
				document.add(MaTableFactory.getMaTable(sl));
				document.newPage();
			}
			
			//------------------------   end moving average
	                
			document.close();
			System.out.println("The " + filename + " report was generated"); 
	}
}
