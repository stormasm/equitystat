/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.misc;

import java.util.HashMap;

/**
Maps Strings to specific report names.
*/

public class AgiTextHashMaps {

	public static HashMap title = new HashMap();
	public static HashMap commentary = new HashMap();
	
	public static void initTitle() {
		title.put("techg00","Technology Sector with Market Caps greater than 20 Billion");
		title.put("techg01","Technology Sector with Market Caps between 10 and 20 Billion");
		title.put("techg02","Technology Sector with Market Caps between 5 and 10 Billion");
		title.put("techg03","Technology Sector with Market Caps between 2.5 and 5 Billion");
		title.put("techg04","Technology Sector with Market Caps between 1 and 2.5 Billion");
		title.put("techg05","Technology Sector with Market Caps between 500 Million and 1 Billion");
		
		title.put("Epstechg00","EPS Worksheet for Technology Sector with Market Caps greater than 20 Billion");
		title.put("Epstechg01","EPS Worksheet for Technology Sector with Market Caps between 10 and 20 Billion");
		title.put("Epstechg02","EPS Worksheet for Technology Sector with Market Caps between 5 and 10 Billion");
		title.put("Epstechg03","EPS Worksheet for Technology Sector with Market Caps between 2.5 and 5 Billion");
		title.put("Epstechg04","EPS Worksheet for Technology Sector with Market Caps between 1 and 2.5 Billion");
		title.put("Epstechg05","EPS Worksheet for Technology Sector with Market Caps between 500 Million and 1 Billion");
		
		title.put("Matechg00","Moving Averages for Technology Sector with Market Caps greater than 20 Billion");
	}
	
	public static void initCommentary() {
		commentary.put("techg00", AgiTextStrings.commentarytechg00);
		commentary.put("techg01", AgiTextStrings.commentarytechg01);
		commentary.put("techg02", AgiTextStrings.commentarytechg02);
		commentary.put("techg03", AgiTextStrings.commentarytechg03);
		commentary.put("techg04", AgiTextStrings.commentarytechg04);
		commentary.put("techg05", AgiTextStrings.commentarytechg05);
		
		commentary.put("Epstechg00", EpsTextStrings.commentaryepstechg00);
		commentary.put("Epstechg01", EpsTextStrings.commentaryepstechg01);
		commentary.put("Epstechg02", EpsTextStrings.commentaryepstechg02);
		commentary.put("Epstechg03", EpsTextStrings.commentaryepstechg03);
		commentary.put("Epstechg04", EpsTextStrings.commentaryepstechg04);
		commentary.put("Epstechg05", EpsTextStrings.commentaryepstechg05);
	}
	
	public static HashMap getTitleHashMap() {
		initTitle();
		return title;	
	}
	
	public static HashMap getCommentaryHashMap() {
		initCommentary();
		return commentary;	
	}
}
