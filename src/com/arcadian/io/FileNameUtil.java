/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.io;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *    Functions which read arrays of Strings and do things with them.
 */

public abstract class FileNameUtil {

/**
Given a String[] of filenames, return an ArrayList of the unique Strings
In this case the String[] has underscores after the name like so...
sunw_0
sunw_1
This parses out the _x and stores just one unique name per HashMap

This function works with other "markers", not the market in this case is the underscore
*/	
	
    public static ArrayList getSymbolsFromFileNames(String[] filename, String marker) {

	    ArrayList al = new ArrayList();
	    HashSet hs = new HashSet();
	    
	    for (int i = 0; i < filename.length; i++) {
		    
		    int x1 = filename[i].indexOf(marker);
		    String x2 = filename[i].substring(0,x1);
		    
		    if(hs.add(x2)) {
			al.add(x2);    
		    }
	    }
	    
	    return al;
    }
  
/**
This reads a set of files out of a directory structure and returns the number of file names
with the appropriate prefix and suffix....

The filenames always end in a integer, so it is easy to test if that particular file exists.
*/
  
    public static int getNumberOfFiles(String directory, String filenameprefix, String filenamesuffix) {
		    
		    int count = 0;
		    boolean flag = true;
		    
		    while (flag) {
			String cnt = Integer.toString(count);
			String fn = buildFileName(directory,filenameprefix, filenamesuffix,cnt);
			
			File f = new File(fn);
			
			if (f.exists()) {
				count = count + 1;	
			}
			else {
				flag = false;	
			}
		    }
		    
		    return(count);	    
    }
    
    
/**
This reads a set of files out of a directory structure and returns the file names
with the appropriate prefix and suffix....

The filenames always end in a integer, so it is easy to test if that particular file exists.
*/
  
    public static ArrayList getArrayListOfFiles(String directory, String filenameprefix, String filenamesuffix) {
		    
		    ArrayList back = new ArrayList();
		    int count = 0;
		    boolean flag = true;
		    
		    while (flag) {
			String cnt = Integer.toString(count);
			String fn = buildFileName(directory,filenameprefix, filenamesuffix,cnt);
			
			File f = new File(fn);
			
			if (f.exists()) {
				back.add(fn);
				count = count + 1;	
			}
			else {
				flag = false;	
			}
		    }
		    
		    return back;
    }
    
    public static String buildFileName(String directory, String filenameprefix, String filenamesuffix, String value) {
		
		String name = directory + File.separator + filenameprefix + filenamesuffix + value + ".xml";
		return name;
    }
    
    public static void main(String[] args) throws Exception {
	String dir = DirName.getHp("predb");
	String prefix = "sunw";
	String suffix = "_";
	
	int x = getNumberOfFiles(dir,prefix,suffix);
	System.out.println(x);
	
	ArrayList al = getArrayListOfFiles(dir,prefix,suffix);
	
	for (int i = 0; i < al.size(); i++) {
		System.out.println(al.get(i));	
	}
	
    }
}
