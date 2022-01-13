/*
 * this class defines Rate table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Rate.
 * Static class
 * This class consist of columns types of Rate table on db scheme. 
 */
public final class Rate {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "rate";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
	     put(config.database.tables.columns.Rate.TYPE, String.format("enum('%s','%s')", entities.Rate.type.ONETIME,entities.Rate.type.SUBSCRIPTION));
	     put(config.database.tables.columns.Rate.NUMBEROFDAYS, "smallint");
	     put(config.database.tables.columns.Rate.PRICE, "double");
	     
	   //multiple define primary and references keys
	     put("", String.format("primary key(`%s`,`%s`)",
	    		 config.database.tables.columns.Rate.TYPE, config.database.tables.columns.Rate.NUMBEROFDAYS
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Rate() {}
	
}
