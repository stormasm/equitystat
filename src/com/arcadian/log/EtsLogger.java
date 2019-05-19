/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.log;

import java.io.IOException;

import org.apache.log4j.Category;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Logger;

import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.helpers.LogLog;

import com.arcadian.io.DirName;

/**
Simple logging class which writes out messages to the specified directory.
*/

public class EtsLogger extends Logger {

  // It's enough to instantiate a factory once and for all.
  private static EtsLoggerFactory myFactory = new EtsLoggerFactory();

  public EtsLogger(String name) {
    super(name);
  }

  public static void init(String name, Level lev) {

	  	 Logger root = Logger.getRootLogger();
		
		 root.setLevel((Level) Level.INFO); 
		 Layout layoutroot = new PatternLayout("%p [%t] %c (%F:%L) - %m%n");
		 String filenameroot = DirName.getFinanceLog("root.txt");
		 try {
			 root.addAppender(new FileAppender(layoutroot,filenameroot,true));
		 }
		 catch (IOException e) {
			 LogLog.error("Can't Initialize root in EtsLogger",e);      
		 }
	  
      Logger log = getLogger(name);
      log.setLevel((Level) lev); 
      Layout layout = new PatternLayout("%p [%t] %c (%F:%L) - %m%n");
      String filenamewrite = DirName.getFinanceLog("test1.txt");
      try {
	      log.addAppender(new FileAppender(layout,filenamewrite,true));
      }
      catch (IOException e) {
		LogLog.error("Can't Initialize EtsLogger",e);      
      }
  }
  
  public static void init(String name, Level lev,String filename) {
  
  		 Logger root = Logger.getRootLogger();
		
		 root.setLevel((Level) Level.INFO); 
		 Layout layoutroot = new PatternLayout("%p [%t] %c (%F:%L) - %m%n");
		 String filenameroot = DirName.getFinanceLog("root.txt");
		 try {
			 root.addAppender(new FileAppender(layoutroot,filenameroot,true));
		 }
		 catch (IOException e) {
			 LogLog.error("Can't Initialize root in EtsLogger",e);      
		 }
  
      Logger log = getLogger(name);
      log.setLevel((Level) lev); 
      Layout layout = new PatternLayout("%p [%t] %c (%F:%L) - %m%n");
      String filenamewrite = DirName.getFinanceLog(filename);
      try {
	      log.addAppender(new FileAppender(layout,filenamewrite,true));
      }
      catch (IOException e) {
		LogLog.error("Can't Initialize EtsLogger",e);      
      }
  }

  /**
     This method overrides {@link Logger#getInstance} by supplying
     its own factory type as a parameter.
  */
  public static Category getInstance(String name, Level lev) {
	  init(name,lev);
	  return Logger.getLogger(name, myFactory); 
  }
  
    /**
     This method overrides {@link Logger#getInstance} by supplying
     its own factory type as a parameter.
  */
  public static Category getInstance(String name, Level lev, String filename) {
	  init(name,lev,filename);
	  return Logger.getLogger(name, myFactory); 
  }

  
  /**
     This method overrides {@link Logger#getLogger} by supplying
     its own factory type as a parameter.
  */
  public  static  Logger getLogger(String name) {
    return Logger.getLogger(name, myFactory); 
  }
  

private static class EtsLoggerFactory implements LoggerFactory {

  public  Logger makeNewLoggerInstance(String name) {
    return new EtsLogger(name);
  }
}
}
