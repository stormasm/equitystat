/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.FieldPosition;

/**
Get the Dates based on a specific format defined in the method. 
*/

public abstract class CalendarUtil {

  public  static String getYyyyMmDd() {
	  
	  Date date = new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  StringBuffer sb1 = new StringBuffer();
	  FieldPosition fp = new FieldPosition(0);
	  StringBuffer sb = sdf.format(date,sb1,fp);
          String ret = sb.toString(); 
	  return ret;
  }
}
