/*
 * this class defines Map table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 * Static class
 * This class consist of columns types of Map table on db scheme. 
 */
public final class Map {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "map";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.ExternalMap.DB_TABLE.get(config.database.tables.columns.ExternalMap.SHORTCOUNTRY));
		 put(config.database.tables.columns.Map.CITY, config.database.tables.ExternalMap.DB_TABLE.get(config.database.tables.columns.ExternalMap.CITY));
		 put(config.database.tables.columns.Map.NAME, config.database.tables.ExternalMap.DB_TABLE.get(config.database.tables.columns.ExternalMap.NAME));
	     put(config.database.tables.columns.Map.IMAGE, config.database.tables.ExternalMap.DB_TABLE.get(config.database.tables.columns.ExternalMap.IMAGE));
	     put(config.database.tables.columns.Map.MAP_VERSION, config.database.tables.ExternalMap.DB_TABLE.get(config.database.tables.columns.ExternalMap.MAP_VERSION));
	     put(config.database.tables.columns.Map.COLLECTION_VERSION, "varchar(32)");
	     put(config.database.tables.columns.Map.DESCRIPTION, config.database.tables.ExternalMap.DB_TABLE.get(config.database.tables.columns.ExternalMap.DESCRIPTION));
	     
	   //multiple define primary and references keys
	     put("", String.format("primary key(`%s`,`%s`,`%s`,`%s`),"
	    		+ "foreign key(`%s`,`%s`) references `%s`(`%s`,`%s`) on delete cascade on update cascade",
	    		 config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
	    		 config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY,
	    		 config.database.tables.City.TABLE_NAME,
	    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Map() {}
	
}
