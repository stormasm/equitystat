/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import com.lowagie.text.pdf.PdfPTable;
import com.arcadian.finance.symbol.SymbolList;

/**
Static methods used as helper classes in the Moving Average Worksheet document.
*/

public abstract class MaTableFactory {

	public static String[] macolumns = {"Symbol", "Date", "Direction", "Moving Average", "Close", "Days"};
	
	public static PdfPTable getMaTable(SymbolList sl) {
			Column5Ma c5s = new Column5Ma();
			c5s.init(sl,macolumns);
			PdfPTable back = c5s.processMovingAverage();
			return back;
	}
	
	public static PdfPTable getMaTutorial() {
			Column1Paragraph c1p = new Column1Paragraph();
			c1p.init();
			PdfPTable back = c1p.getMaTutorial();
			return back;
	}
}
