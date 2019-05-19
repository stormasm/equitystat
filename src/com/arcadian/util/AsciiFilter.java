/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.util;

// import com.arcadian.ant.AntProperty;
// import com.arcadian.io.FileUtil;


/**
Given a char[] filter out those characters that are not in a specific range.
*/

public class AsciiFilter {

    public static char[] filter(char[] input, int stoprange) {
	    
	    int count = 0;
	    
	    // this is the temporary character array that characters will be written into
	    char[] tmp = new char[input.length];
	    
	    for (int i = 0; i < input.length; i++) {
		int cp = Character.codePointAt(input,i);
		
		// add the character to the new array if it less than the stoprange
		
		if (cp < stoprange) {
			tmp[count++] = input[i];	
		}
	    }
	    
	    char[] back = new char[count];
	    
	    System.arraycopy(tmp,0,back,0,count);
	    
	    return back;
    }
	
   /* 
    public static void main(String[] args) {
		
		String dira = AntProperty.etsdata();
		String fb = dira.concat("\\test\\aapl.txt");
		String fc = dira.concat("\\test\\aaplnew.txt");
		char[] ca = FileUtil.readFile(fb);
		
		for (int j = 0; j < ca.length; j++) {
			int ga = Character.codePointAt(ca,j);
			String s = Character.toString(ca[j]);
			System.out.println(s + " " + ga);
		}
		
		char[]  look = AsciiFilter.filter(ca,128);
		
		FileUtil.store(look,fc);
    }
    */
}

