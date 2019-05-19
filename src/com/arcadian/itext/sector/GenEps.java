/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.sector;

/**
The driver class for the EPS Worksheet document based on the number of portfolios.
*/

public class GenEps {

	public static void main(String[] args) {

		String[] sector = {"techg00", "techg01", "techg02", "techg03", "techg04", "techg05"};
		// String[] sector = {"techg00"};
		
		for (int i = 0; i < sector.length; i++) {
			String reportname = sector[i];
			
			try {
				Eps.process(reportname);
			}
			catch (Exception e) {
				e.printStackTrace();	
			}
		}
	}
}
