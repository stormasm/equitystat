/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.filefilter.PrefixFileFilter;


/**
 *    Functions which read filenames from a directory
 */

public class FileNameReader {

/**
Given a directory name, pass back an ArrayList of files
*/
	
    public static ArrayList getArrayList(String dirname) {

       int numoffiles;
       ArrayList al = new ArrayList();

       File f = new File(dirname);
       
       String[] x = f.list();
       
       for (int i = 0; i < x.length; i++) {
	 	al.add(x[i]);      
       }
       return(al);
    }

/**
Given a directory name, pass back a String[] of files
*/
    
    public static String[] getStringArray(String dirname) {
       File f = new File(dirname);
       String[] x = f.list();
       return(x);
    }

/**
Given a directory name, pass back a String[] of of the Canonical Paths.
*/
    
    public static String[] getFullPathStringArray(String dirname) throws Exception {
       File f = new File(dirname);
       File[] x = f.listFiles();
       String[] xs = new String[x.length];
       
       for (int i = 0; i < x.length; i++) {
	 xs[i] = x[i].getCanonicalPath();      
       }
       return(xs);
    }

/**
Given a directory name, pass back a String[] of prefixes given the suffix.
*/
    
    public static String[] getPrefixStringArray(String dirname, String suffix) {
	    File f = new File(dirname);
	    String[] x1 = f.list();
	    String[] x2 = new String[x1.length];
	    
	    for (int i = 0; i < x1.length; i++) {
	    
	    	int index = x1[i].indexOf(suffix);
		// deal with the dot in the suffix
		x2[i] = x1[i].substring(0,index - 1);
            }
	    return(x2);
    }
    
   public static void main(String[] args) throws Exception {
	
	String xdir = DirName.getKs("robot");
	FileNameReader fnr = new FileNameReader();
	
	/*
        ArrayList pal = FileNameReader.getArrayList(xdir);
	for (int i = 0; i < pal.size(); i++) {
		String x = (String) pal.get(i);
		System.out.println(x);
	}
	*/
	
	/*
	String[] dir1 = fnr.getStringArray(xdir);
	String[] dir2 = fnr.getPrefixStringArray(xdir,"htm");
	for (int i = 0; i < dir2.length; i++) {
		String x1 = (String) dir1[i];
		String x2 = (String) dir2[i];
		System.out.println(x1 + " " + x2);
	}
	*/
	
	String[] dir1 = fnr.getFullPathStringArray(xdir);
	for (int i = 0; i < dir1.length; i++) {
		String x1 = (String) dir1[i];
		System.out.println(x1);
	}
   }
}

