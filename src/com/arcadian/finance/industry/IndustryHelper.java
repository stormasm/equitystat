/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.finance.industry;

import java.util.ArrayList;
import com.arcadian.finance.symbol.SymbolArrayList;
import com.arcadian.finance.symbol.SymbolArrayListContainer;
import com.arcadian.persist.Industry;
import com.arcadian.persist.Sector;
import com.arcadian.sql.CriteriaIndustry;
import com.arcadian.sql.CriteriaSector;
import com.arcadian.util.FilterArrayLists;

/**
 * Used to get industries based on certain criteria
 *
 * @author Michael I Angerman
 */
 
public class IndustryHelper {

/**
Pass in two ArrayLists

The first ArrayList is an ArrayList with only one sector for now,
later we will add functionality to do multiple sectors.

The second ArrayList is an ArrayList of industries you want removed
The method returns all the industries in the sectors you passed in
minus the industries in the second ArrayList
*/

	public static ArrayList removeIndustryNamesFromSector(ArrayList sector, ArrayList two) {
		
		ArrayList industries = null;
		
		try {
			industries = CriteriaSector.getIndNames(sector,"sector");
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
		/**
		Given two ArrayLists with Strings in them, 
		remove all of the Strings in the Target that
		are located in the Source.
		*/
		
		ArrayList back = FilterArrayLists.remove(two,industries);
		return back;		
	}
	
	
/**
Pass in two ArrayLists

The first ArrayList is an ArrayList with only one sector for now,
later we will add functionality to do multiple sectors.

The second ArrayList is an ArrayList of industry codes you want removed
The method returns all the industry codes in the sector you passed in
minus the industry codes in the second ArrayList
*/

	public static ArrayList removeIndustryCodesFromSector(ArrayList sector, ArrayList two) {
		
		ArrayList industries = null;
		
		try {
			industries = CriteriaSector.getIndCodes(sector,"sector");
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
		/**
		Given two ArrayLists with Strings in them, 
		remove all of the Strings in the Target that
		are located in the Source.
		*/
		
		ArrayList back = FilterArrayLists.remove(two,industries);
		return back;		
	}
	
	/**
	*
	*   Given an ArrayList of symbols
	*   Pass back an ArrayList of SymbolArrayLists
	*   Where the symbol is the Industry Name
	*   and the ArrayList contains the list of company symbols
	*
	*/
	
	public static ArrayList getIndustryPlusSymbolList(ArrayList input) {
	
		    	SymbolArrayListContainer salc = new SymbolArrayListContainer();
			salc.init();
			ArrayList indal = null;
			
			// Get an ArrayList of industries given an ArrayList of symbols
			
			try {
				indal = CriteriaIndustry.getData(input,"sector");
			}
			catch (Exception e) {
				e.printStackTrace();	
			}
			
			Industry ind = (Industry) indal.get(0);
			String lastindustry = (String) ind.getIndname();
			String companysymbol = (String) ind.getSymbol();
			salc.addSymbol(lastindustry);
			salc.addParam(companysymbol);
			
			for (int i = 1; i < indal.size(); i++) {
				
				ind = (Industry) indal.get(i);
				String industry = (String) ind.getIndname();
				String symbol = (String) ind.getSymbol();
				
				if (industry.compareTo(lastindustry) != 0) {
					
					salc.addSymbol(industry);
					salc.addParam(symbol);
				}
				else {
					salc.addParam(symbol);	
				}
				lastindustry = industry;
			}
			
			ArrayList back100 = salc.getArrayList();
			ArrayList back200 = reverseArrayList(back100);
			return back200;
	}
	
	/**
	This function is specific to reversing ArrayLists's that have SymbolArrayList's inside of them ONLY !!
	*/
	
	public static ArrayList reverseArrayList(ArrayList input) {
		int size = input.size();

		ArrayList back = new ArrayList();

		for (int i = size - 1; i >= 0; i--) {
			SymbolArrayList sal = (SymbolArrayList) input.get(i);
			back.add(sal);
		}
		
		return back;
	}
	
	/*
	public static void main(String[] args) throws Exception {
		
		ArrayList sector = new ArrayList();
		sector.add("Technology");
		
		ArrayList limit = new ArrayList();
		limit.add("DiversifiedCommunicationServices");
		limit.add("LongDistanceCarriers");
		limit.add("TelecomServices-Domestic");
		limit.add("TelecomServices-Foreign");
		limit.add("WirelessCommunications");

		ArrayList back = IndustryHelper.removeIndustryNamesFromSector(sector,limit);
		
		System.out.println("Size of new array is " + back.size());
		
		for (int i = 0; i < back.size(); i++) {
			System.out.println(back.get(i));
		}
		
		ArrayList limitx = new ArrayList();
		limitx.add("846");
		limitx.add("843");
		limitx.add("844");
		limitx.add("845");
		limitx.add("840");

		ArrayList back1 = IndustryHelper.removeIndustryCodesFromSector(sector,limitx);
		
		System.out.println("Size of new array is " + back1.size());
		
		for (int i = 0; i < back1.size(); i++) {
			System.out.println(back1.get(i));
		}
	}
	*/
	public static void main(String[] args) throws Exception {
		
			ArrayList input = new ArrayList();
			input.add("msft");
			input.add("orcl");
			input.add("avci");
			input.add("bbox");
			input.add("brkt");
			ArrayList al = IndustryHelper.getIndustryPlusSymbolList(input);
			
			for (int i = 0; i < al.size(); i++) {
				SymbolArrayList sal = (SymbolArrayList) al.get(i);
				sal.print();
			}
	}
}
