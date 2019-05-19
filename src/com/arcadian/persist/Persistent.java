/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.persist;

/**
 *  This is the core class for this package, all of the classes in this package extend this class.
 *
 * @author Michael I Angerman
 */
 
public class Persistent {
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long l) {
		id = l;
	}
}
