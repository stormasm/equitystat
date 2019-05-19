/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *    Functions which read and write files
 */

public class FileUtil {

/**
Given a filename, return a char[] of the contents
*/	
	
    public static char[] readFile(String filename) {

       long fl;
       int filelength;

       int numofchars;
       FileReader fr = null;

       File f = new File(filename);
       fl = f.length();
       filelength = (int) fl;

       char[] data = new char[filelength];

       try {
           fr = new FileReader(f);
       }
       catch(FileNotFoundException e) {
           e.printStackTrace();
       }

       try {
           BufferedReader br = new BufferedReader(fr);
           numofchars = br.read(data,0,filelength);
       }
       catch(IOException e) {
           e.printStackTrace();
       }

       return(data);
    }

/**
Store the char[] given a filename
*/
    
    public static void store(char[] data, String filename) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        File f = new File(filename);

        try {
            fw = new FileWriter(f);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        bw = new BufferedWriter(fw);

        try {
            bw.write(data,0,data.length);
            bw.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
/**
Store a String given a filename
*/
    
    public static void store(String data, String filename) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        File f = new File(filename);

        try {
            fw = new FileWriter(f);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        bw = new BufferedWriter(fw);

        try {
            bw.write(data,0,data.length());
            bw.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
Store a BufferedImage given a filename
*/

    public static void storeJpegImage(BufferedImage bi, String filename) {

        File f = new File(filename);

        try {
            ImageIO.setUseCache(false);
            ImageIO.write(bi,"jpeg",f);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

