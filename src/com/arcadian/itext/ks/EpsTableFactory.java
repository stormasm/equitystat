/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.ks;

import java.util.ArrayList;
import com.lowagie.text.pdf.PdfPTable;

/**
Static methods used as helper classes in the EPS Worksheet document.
*/

public abstract class EpsTableFactory {

	public static String[] epscolumns = {"", "EPS", "P/E", "Price", "% Increase"};
	
	public static PdfPTable getFutureEps(ArrayList symbolist) {
			Column5Eps c5s = new Column5Eps();
			c5s.init(symbolist,epscolumns);
			PdfPTable back = c5s.process();
			return back;
	}
	
	public static PdfPTable getEpsTutorial() {
			Column1Paragraph c1p = new Column1Paragraph();
			c1p.init();
			PdfPTable back = c1p.getEpsTutorial();
			return back;
	}
}
