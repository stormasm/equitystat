/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.util;

import java.util.Date;

import com.arcadian.finance.symbol.SymbolHp;
import com.arcadian.persist.Hp;

/**
Some Utility functions surrounding Volume and Dates in the historical prices
*/

public abstract class SymbolHpUtil {

/**
Match a SymbolHp based on the volume and a window of values.  
*/	
	
    public static SymbolHp reduceVolume (SymbolHp shpold, int volume, int window) {
	    
	    // this is the index of the matching volume
	    int matchindex = -10101;
	    
	    // this is the low side of the window
	    int loindex = 0;
	    
	    // this is the high side of the window
	    int hiindex = 0;
	    
	    int windowsize = 2 * window + 1;
	    Hp[] hp = new Hp[windowsize];
	    
	    SymbolHp shp = new SymbolHp();
	    shp.setSymbol(shpold.getSymbol());
		
            Hp[] hpold = shpold.getHpa();
	    
	    for (int i = 0; i < hpold.length; i++) {
			int volcheck = hpold[i].getVolume();
			if(volcheck == volume) {
				// System.out.println("Match on number " + i);
				matchindex = i;
				break;
			}
	    }
	    
	    if (matchindex == -10101) {
		System.out.println("SymbolHpUtil NO match was found");
		return null;
	    }
	    
	    loindex = matchindex - window;
	    hiindex = matchindex + window + 1;       // slanted on this side by one
	    
	    // System.out.println("loindex = " + loindex + " hiindex = " + hiindex);
	    
	    if (loindex < 0) loindex = 0;
	    if (hiindex > hpold.length) hiindex = hpold.length;
	    
	    System.arraycopy(hpold,loindex,hp,0,windowsize);
	    
	    shp.setHpa(hp);
	    
	    return shp;
    }
    
/**
Match a SymbolHp based on the volume and return the date  
*/	
	
    public static Date getDate(SymbolHp shpold, int volume) {
	    
            Hp[] hpold = shpold.getHpa();
	    
	    for (int i = 0; i < hpold.length; i++) {
			int volcheck = hpold[i].getVolume();
			if(volcheck == volume) {
				return hpold[i].getDate();
			}
	    }
	    
	    System.out.println("SymbolHpUtil:getDate() NO match was found");
	    return null;
    }
    
/**
Match a SymbolHp based on the volume and return the Hp  
*/	
	
    public static Hp getHp(SymbolHp shpold, int volume) {
	    
            Hp[] hpold = shpold.getHpa();
	    
	    for (int i = 0; i < hpold.length; i++) {
			int volcheck = hpold[i].getVolume();
			if(volcheck == volume) {
				return hpold[i];
			}
	    }
	    
	    System.out.println("SymbolHpUtil:getHp() NO match was found");
	    return null;
    }
}

