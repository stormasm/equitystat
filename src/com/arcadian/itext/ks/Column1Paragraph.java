/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

import com.arcadian.itext.misc.EpsTextStrings;
import com.arcadian.itext.misc.MaTextStrings;
import com.arcadian.persist.Industry;
import com.arcadian.persist.Ks;
import com.arcadian.sql.SymbolNameHelper;
import com.arcadian.util.EtsNumberFormat;

/**
Generate out a set of paragraphs within a document.
*/
public class Column1Paragraph {

	PdfPTable datatable = null;
	
	public void init() {
		
		Color litegray = new Color(200,200,200);
		
		datatable = new PdfPTable(1);
		
		datatable.setWidthPercentage(80); // percentage
		datatable.getDefaultCell().setPadding(3);
		datatable.getDefaultCell().setBorderWidth(1);
		datatable.getDefaultCell().setBorderColor(litegray);
		datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	}
	
	public PdfPTable getEpsTutorial() {			
		Paragraph p1 = new Paragraph(EpsTextStrings.p1);
		Paragraph p2 = new Paragraph(EpsTextStrings.p2);
		Paragraph p3 = new Paragraph(EpsTextStrings.p3);
		datatable.addCell(p1);
		datatable.addCell(p2);
		datatable.addCell(p3);
		datatable.setSpacingAfter(20.0f);
		return datatable;
	}
	
	public PdfPTable getMaTutorial() {			
		Paragraph p1 = new Paragraph(MaTextStrings.p1);
		Paragraph p2 = new Paragraph(MaTextStrings.p2);
		Paragraph p3 = new Paragraph(MaTextStrings.p3);
		datatable.addCell(p1);
		datatable.addCell(p2);
		datatable.addCell(p3);
		datatable.setSpacingAfter(20.0f);
		return datatable;
	}
}

