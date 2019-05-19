/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.itext.misc;

/**
Static Strings used in the eps documents
*/

public abstract class EpsTextStrings {
			
	public static final String p1 = "The Trailing EPS (Earnings Per Share) is based on the previous 12 months of Earnings.  The Trailing P/E (Price Earnings Ratio) is the Current Price divided by the Trailing EPS.";
	public static final String p2 = "The Forward P/E is calculated using the Current Price and dividing it by the Forward EPS Estimate.  This Estimate is an average of all of the analysts who follow the particular company.  In the case of Microsoft, the number of analysts following the company is around 30.  So, if you average 30 different Earnings opinions you come up with the Forward EPS Estimate.";  
	public static final String p3 = "The Future Price is calculated by multiplying the Forward EPS Estimate by the Trailing P/E.  The % Increase is simply the Future Price divided by the Current Price. The reason for using the Trailing P/E to calculate the Future Price is because we are assuming that the company will continue to grow at its current rate.  If for some reason, the growth rate of the particular company slows down, then the Future Price will be reflected in a revised Forward EPS Estimate. ";
	
	public static final String commentaryepsp0 = "Earnings is the critical piece of information in evaluating the financial health of a corporation.  For that reason, we have chosen to come up with a simple earnings worksheet that outlines the relationship between the core concepts when evaluating a company.  This report outlines in detail how the future prices of corporations are calculated based on information we have today."; 
	public static final String commentaryepsp1 = "The core concept in this fundamental analysis of corporations is the Earnings Estimate.  Based on many different financial metrics, Wall Street analysts come up with a model to evaluate the future earnings of corporations.  The accuracy of this estimate is usually a function of the number of analysts following a particular corporation.  For the larger companies, there are many analysts who track a company and therefore the estimate is averaged over many more opinions.  Small companies have the disadvantage that not as many analysts follow them and therefore the estimates are not always as predictable.";
	public static final String commentaryepsp2 = "When reviewing the data below, keep in mind that there are two major concepts outlined.  The first concept is how well a corporation has done in the past.  This is easily measured by looking back over the past year and calculating the EPS (Earnings Per Share).  At the end of each quarter, companies report their earnings in their quarterly SEC filings called a 10Q.  By going back through these reports, one can add up the EPS for the past four quarters.  Most financial web sites also list this data so it is readily available.  Given this EPS number, one can easily then calculate the Trailng P/E (Price Earnings Ratio) by dividing the stock price by the EPS.  The Trailing P/E will be used in determining the future stock price.";
	public static final String commentaryepsp3 = "The second crucial concept is using the Earnings Estimate to calculate a stock price in the future.   The calculation is simple, just multiply the Earnings Estimate by the Trailng P/E calculated above.  Earnings Estimates usually have time periods associated with them, so check the data to find out the time frame of the Earnings Estimate.";
	public static final String commentaryepsp4 = " It is also possible to calculate future stock prices using a number different than the Trailing P/E.  The Trailng P/E should only be used if one has confidence that the company will continue to grow at the current rate of growth.  Sometimes, people will use numbers smaller than the Trailing P/E because they believe the corporation over time will grow more slowly. If for some reason, one believes the company is going to grow even faster than the Trailing P/E depicts, then one might in certain (rare) circumstances use a number greater than the Trailing P/E.";    

	public static final String commentaryepstechg00 = "Insert custom commentary for report Epstechg00.";
	public static final String commentaryepstechg01 = "Insert custom commentary for report Epstechg01.";
	public static final String commentaryepstechg02 = "Insert custom commentary for report Epstechg02.";
	public static final String commentaryepstechg03 = "Insert custom commentary for report Epstechg03.";
	public static final String commentaryepstechg04 = "Insert custom commentary for report Epstechg04.";
	public static final String commentaryepstechg05 = "Insert custom commentary for report Epstechg05.";

	public static final String blankline = "                                                                                                                                                                                                                                                                ";	
}
