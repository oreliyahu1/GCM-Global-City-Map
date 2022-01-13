/*
 * this class defines PlaceOfInterest table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterest.
 * Static class
 * This class consist of columns types of PlaceOfInterest table on db scheme. 
 */
public final class PlaceOfInterest {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "poi";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY));
		 put(config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.PlaceOfInterest.CITY));
		 put(config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION));
		 put(config.database.tables.columns.PlaceOfInterest.NAME, "varchar(50)");
		 
		 put(config.database.tables.columns.PlaceOfInterest.TYPE, config.database.tables.Category.DB_TABLE.get(config.database.tables.columns.PlaceOfInterest.TYPE));
		 put(config.database.tables.columns.PlaceOfInterest.DESCRIPTION, "varchar(300)");
		 put(config.database.tables.columns.PlaceOfInterest.ACCESSIBLE, "bool");
	     
	   //multiple define primary and references keys
	     put("", String.format("primary key(`%s`,`%s`,`%s`,`%s`),"
	    		+ "foreign key(`%s`,`%s`) references `%s`(`%s`,`%s`) on delete cascade on update cascade,"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete set null on update cascade",
	    		 config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
	    		 config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterest.CITY,
	    		 config.database.tables.City.TABLE_NAME,
	    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME,
	    		 config.database.tables.columns.PlaceOfInterest.TYPE, config.database.tables.Category.TABLE_NAME, config.database.tables.columns.Category.NAME	    		 
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private PlaceOfInterest() {}
	
}
