/*
 * this class defines PlaceOfInterest table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterestMap.
 * Static class
 * This class consist of columns types of PlaceOfInterestMap table on db scheme. 
 */
public final class PlaceOfInterestMap {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "poimap";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY));
		 put(config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.PlaceOfInterestMap.CITY));
		 put(config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION));
		 put(config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.PlaceOfInterestMap.NAME));
		 put(config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.PlaceOfInterestMap.MAP));
		 
		 put(config.database.tables.columns.PlaceOfInterestMap.LOCATION_X, "double");
		 put(config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y, "double");
	     
	   //multiple define primary and references keys
	     put("", String.format("primary key(`%s`,`%s`,`%s`,`%s`,`%s`),"
	    		+ "foreign key(`%s`,`%s`,`%s`,`%s`) references `%s`(`%s`,`%s`,`%s`,`%s`) on delete cascade on update cascade,"
	    		+ "foreign key(`%s`,`%s`,`%s`,`%s`) references `%s`(`%s`,`%s`,`%s`,`%s`) on delete cascade on update cascade",
	    		 config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
	    		 config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
	    		 config.database.tables.Map.TABLE_NAME,
	    		 config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
	    		 config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
	    		 config.database.tables.PlaceOfInterest.TABLE_NAME,
	    		 config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private PlaceOfInterestMap() {}
	
}
