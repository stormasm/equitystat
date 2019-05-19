/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.sector;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import com.arcadian.itext.ks.KsTableFactory;
import com.arcadian.itext.misc.AgiTextHashMaps;
import com.arcadian.itext.misc.AgiTextStrings;
import com.arcadian.portfolio.PortfolioHelper;
import com.arcadian.sql.CriteriaIndustry;
import com.arcadian.sql.CriteriaKs;
import com.arcadian.sql.SymbolNameHelper;

/**
The organization and architecture of the Sector Worksheet document
*/

public class Sector {

	public static void process(String reportname) throws Exception {
		
			// Initialize the Title and Commentary HashMaps
			HashMap titlehm = AgiTextHashMaps.getTitleHashMap();
			HashMap commentaryhm = AgiTextHashMaps.getCommentaryHashMap();
			
			SymbolNameHelper snh = new SymbolNameHelper();
			snh.init();
		
			// Set up the filename that will be written to the disk
			String filename = reportname.concat(".pdf");
		
			// In this case, the portfolio name and the file name are the same
			ArrayList limit = PortfolioHelper.getPortfolio(reportname);		
		
			// Instantiate the document
			Document document = new Document(PageSize.A4, 25, 25, 35, 25);
			
			// Tie the document and the filename together
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

			// Get the title for this document
			String documentitle = (String) titlehm.get(reportname);
			
			// Get the fonts for the titles on each page and the disclaimers on each page
			Font head = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL, Color.blue);
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
			String commentary = (String) commentaryhm.get("techall");
			// Keep here to generate commentary per individual reports
			// String commentary = (String) commentaryhm.get(reportname);
			Paragraph t3 = new Paragraph(commentary);
			t3.setFirstLineIndent(100.0f);
			document.add(t3);
			// ----------------------  end commentary at the start of the document
			
	                document.newPage();
		
			//------------------------   begin marketcap
			ArrayList mcap = CriteriaKs.getDataDesc(limit, "marketcap");
			Paragraph t4 = new Paragraph(AgiTextStrings.marketcapcommentary);
			t4.setFirstLineIndent(100.0f);
			t4.setSpacingAfter(20.0f);
			document.add(t4);
			document.add(KsTableFactory.getMarketCap(mcap));
			//------------------------   end marketcap
			
	                document.newPage();
			
	    	    	//------------------------   begin marketcap - enterprise value
	    		Paragraph t5 = new Paragraph(AgiTextStrings.enterprisevaluecommentary);
			t5.setFirstLineIndent(100.0f);
			t5.setSpacingAfter(20.0f);
			document.add(t5);
			document.add(KsTableFactory.getMarketCapEv(mcap));
		       //------------------------   end marketcap - enterprise value
		       
		       
		       document.newPage();
			
	    	    	//------------------------   begin pe
			ArrayList pe = CriteriaKs.getDataDesc(limit, "forwardpe");
	    		Paragraph t100 = new Paragraph(AgiTextStrings.pecommentary);
			t100.setFirstLineIndent(100.0f);
			t100.setSpacingAfter(20.0f);
			document.add(t100);
			document.add(KsTableFactory.getPE(pe));
		       //------------------------   end pe
		       
	    		document.newPage();
			
			//------------------------   begin symbol, company name table
			ArrayList snal = new ArrayList();
			snal = CriteriaKs.getDataAsc(limit, "symbol");
			Paragraph t6 = new Paragraph(AgiTextStrings.symboltablecommentary);
			t6.setFirstLineIndent(100.0f);
			t6.setSpacingAfter(20.0f);
			document.add(t6);
			document.add(KsTableFactory.getSymbolName(snal));
			//------------------------   end symbol, company name table
	    	
			document.newPage();
		
			//------------------------   begin company name, industry
			ArrayList indal = CriteriaIndustry.getData(limit,"sector");
			Paragraph t7 = new Paragraph(AgiTextStrings.industrycommentary);
			t7.setFirstLineIndent(100.0f);
			t7.setSpacingAfter(20.0f);
			document.add(t7);
			document.add(KsTableFactory.getIndustry(indal));
			//------------------------   end company name, industry

			document.close();
			System.out.println("The " + filename + " report was generated"); 
	}
}
