/**
  * Copyright 2003 - 2005 Arcadian Group LLC. All rights reserved. 
  * Use is subject to license terms found in this distribution. 
*/
package com.arcadian.ant;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Property;

/**
Read environment variables from your operating system.  This is helpful if you want to read files
from a particular location on disk. Simply set an environment variable, and then you can read
and write files to the file system. 
 */
 
public class AntProperty
{
	private static LocalEnv env_;
	
/**
get the environment variable based on the String that gets passed in.
*/
	
	public static String getEnv(String var) {
		String ret = getLocalEnv().getProperty(var);
		return(ret);
	}
	
	private synchronized static LocalEnv getLocalEnv() {
		if (env_ == null) {
			env_ = new LocalEnv();
			env_.init();
		}
		
		return env_;
	}

/**
get the environment variable ETSDATA_HOME
*/
	
	public static String etsdata() {
		return getEnv("ETSDATA_HOME");
	}
	
	
/**
get the environment variable WEBSVGDATA_HOME
*/
	
	public static String websvgdata() {
		return getEnv("WEBSVGDATA_HOME");
	}

	
/**
get the environment variable ETSLIB_HOME
*/	
	
	public static String etslib() {
		return getEnv("ETSLIB_HOME");
	}
	
   /**
     * For standalone testing
     */
    public static void main(String[] args)
    {
		if (args != null && args.length > 0) {
			for (int i=0;i< args.length;i++) {
				System.out.println(getEnv(args[i]));
			}
		} else {
			System.out.println(getEnv("USERNAME"));
			System.out.println(etsdata());
			System.out.println(etslib());
		}
    }
    
private static class LocalEnv extends Property {
	protected String prefix_ = "iris.";
	
	public LocalEnv() {
		Project project = new Project();
		project.init();
		setProject(project);
	}
	
	public void init() {
		setEnvironment(prefix_);
		execute();
	}
	
	public String getProperty(String prop) {
		return getProject().getProperty(prefix_ + prop);
	}
};

};

