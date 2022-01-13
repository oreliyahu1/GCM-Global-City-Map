/*
 * this class defines Purchase table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 * Static class
 * This class consist of columns types of Purchase table on db scheme. 
 */
public final class Purchase {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "purchase";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		   put(config.database.tables.columns.Purchase.ID, "int auto_increment");
		   put(config.database.tables.columns.Purchase.TYPE, config.database.tables.Rate.DB_TABLE.get(config.database.tables.columns.Purchase.TYPE));
		   put(config.database.tables.columns.Purchase.PRICE, config.database.tables.Rate.DB_TABLE.get(config.database.tables.columns.Purchase.PRICE));
		   put(config.database.tables.columns.Purchase.NUMBEROFDAYS, config.database.tables.Rate.DB_TABLE.get(config.database.tables.columns.Purchase.NUMBEROFDAYS));
		   put(config.database.tables.columns.Purchase.USER, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.Purchase.USER));
		   put(config.database.tables.columns.Purchase.DATE, "datetime");
		   put(config.database.tables.columns.Purchase.RENEWAL, "int");
		   put(config.database.tables.columns.Purchase.DOWNLOADED, "bool");
		   
		   put(config.database.tables.columns.Purchase.SHORTCOUNTRY, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.Purchase.SHORTCOUNTRY));
		   put(config.database.tables.columns.Purchase.CITY, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.Purchase.CITY));
		   put(config.database.tables.columns.Purchase.COLLECTION_VERSION, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.Purchase.COLLECTION_VERSION));
	     
		   //define primary and references keys
		     put("", String.format("primary key(`%s`),"
		    		+ "foreign key(`%s`) references `%s`(`%s`) on delete set null on update cascade,"
		    		+ "foreign key(`%s`,`%s`) references `%s`(`%s`,`%s`) on delete no action on update cascade",
		    		 config.database.tables.columns.Purchase.ID,
		    		 config.database.tables.columns.Purchase.USER, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME,
		    		 config.database.tables.columns.Purchase.SHORTCOUNTRY, config.database.tables.columns.Purchase.CITY,
		    		 config.database.tables.City.TABLE_NAME,
		    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME	 
		     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Purchase() {}
	
}
