/*
 * this class defines ExternalMap table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ExternalMap.
 * Static class
 * This class consist of columns types of ExternalMap table on db scheme. 
 */
public final class ExternalMap {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "externalmap";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.ExternalMap.SHORTCOUNTRY, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.ExternalMap.SHORTCOUNTRY));
		 put(config.database.tables.columns.ExternalMap.CITY, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.ExternalMap.CITY));
		 put(config.database.tables.columns.ExternalMap.NAME, "varchar(50)");
	     put(config.database.tables.columns.ExternalMap.IMAGE, "mediumblob"); //blob 16kb, mediumblob 16mb, longblob 4gb
	     put(config.database.tables.columns.ExternalMap.MAP_VERSION, "varchar(32)");
	     put(config.database.tables.columns.ExternalMap.DESCRIPTION, "varchar(300)");
	     
	   //multiple define primary and references keys
	     put("", String.format("primary key(`%s`,`%s`,`%s`),"
	     		+ "foreign key(`%s`,`%s`) references `%s`(`%s`,`%s`) on delete no action on update cascade",
	    		 config.database.tables.columns.ExternalMap.SHORTCOUNTRY, config.database.tables.columns.ExternalMap.CITY, config.database.tables.columns.ExternalMap.NAME,
	    		 config.database.tables.columns.ExternalMap.SHORTCOUNTRY, config.database.tables.columns.ExternalMap.CITY,
	    		 config.database.tables.City.TABLE_NAME,
	    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private ExternalMap() {}	
}
