/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.sector;

/**
The driver class for the Moving Average (MA) Report.
*/

public class GenMa {

	public static void main(String[] args) {

		String[] sector = {"techg00"};

		// 
		//   Currently no data exists for portfolios other than techg00
		//   You can request additional data from us if you like.
		//
		//   String[] sector = {"techg00", "techg01", "techg02", "techg03", "techg04", "techg05"};
		
		for (int i = 0; i < sector.length; i++) {
			String reportname = sector[i];
			
			try {
				Ma.process(reportname);
			}
			catch (Exception e) {
				e.printStackTrace();	
			}
		}
	}
}
