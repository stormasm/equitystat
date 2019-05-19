/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.io;

import java.io.File;
import com.arcadian.ant.AntProperty;

/**
Static methods which pre-define a directory. To get a particular file from the directory just concat the associated file by passing in a
filename to the statically defined directory.
*/

public abstract class DirName {

/**
Get the named file from a directory called c:\etsdata\finance\cp
*/	
	
    public static String getCp(String name) {
	String dira = AntProperty.etsdata();
	String dirb = dira.concat(File.separator);
	String dirc = dirb.concat("finance");
	String dird = dirc.concat(File.separator);
	String dire = dird.concat("cp");
	String dirf = dire.concat(File.separator);
	String dirg = dirf.concat(name);
	return dirg;
    }

/**
Get the named file from a directory called c:\etsdata\finance\hp
*/	
    
    public static String getHp(String name) {
	String dira = AntProperty.etsdata();
	String dirb = dira.concat(File.separator);
	String dirc = dirb.concat("finance");
	String dird = dirc.concat(File.separator);
	String dire = dird.concat("hp");
	String dirf = dire.concat(File.separator);
	String dirg = dirf.concat(name);
	return dirg;
    }

/**
Get the named file from a directory called c:\etsdata\finance\ks
*/	
    
    public static String getKs(String name) {
	String dira = AntProperty.etsdata();
	String dirb = dira.concat(File.separator);
	String dirc = dirb.concat("finance");
	String dird = dirc.concat(File.separator);
	String dire = dird.concat("ks");
	String dirf = dire.concat(File.separator);
	String dirg = dirf.concat(name);
	return dirg;
    }

/**
Get the named file from a directory called c:\etsdata\finance\log
*/	

    
   public static String getFinanceLog(String name) {
	String dira = AntProperty.etsdata();
	String dirb = dira.concat(File.separator);
	String dirc = dirb.concat("finance");
	String dird = dirc.concat(File.separator);
	String dire = dird.concat("log");
	String dirf = dire.concat(File.separator);
	String dirg = dirf.concat(name);
	return dirg;
    }

/**
Get the named file from a directory called c:\etsdata\db
*/	

    
    public static String getDb(String name) {
	String dira = AntProperty.etsdata();
	String dirb = dira.concat(File.separator);
	String dirc = dirb.concat("db");
	String dird = dirc.concat(File.separator);
	String dire = dird.concat(name);
	return dire;
    }

/**
Get the named file from a directory called c:\etsdata\report
*/	
    
    public static String getReport(String name) {
	String dira = AntProperty.etsdata();
	String dirb = dira.concat(File.separator);
	String dirc = dirb.concat("report");
	String dird = dirc.concat(File.separator);
	String dire = dird.concat(name);
	return dire;
    }
}

