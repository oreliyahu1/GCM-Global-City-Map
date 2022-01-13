/*
 * this class defines City table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class City.
 * Static class
 * This class consist of columns types of city table on db scheme. 
 */
public final class City {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "city";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.Country.DB_TABLE.get(config.database.tables.columns.City.SHORTCOUNTRY));
	     put(config.database.tables.columns.City.NAME, "varchar(50)");
	     put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, "varchar(32)");
	     put(config.database.tables.columns.City.DESCRIPTION, "varchar(300)");
	     
	   //multiple define primary and references keys
	     put("", String.format("primary key(`%s`,`%s`),"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete cascade on update cascade",
	    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME,
	    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private City() {}
	
}
