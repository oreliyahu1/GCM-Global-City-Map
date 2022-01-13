/*
 * this class defines Activity table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Activity.
 * Static class
 * This class consist of columns types of Activity table on db scheme. 
 */
public final class Activity {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "activity";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.Activity.ID, "int auto_increment");
		 
		 put(config.database.tables.columns.Activity.PURCHASE_ID, "int");
		 
		 put(config.database.tables.columns.Activity.SHORTCOUNTRY, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.Activity.SHORTCOUNTRY));
		 put(config.database.tables.columns.Activity.CITY, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.Activity.CITY));
		 put(config.database.tables.columns.Activity.MAP, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.Activity.MAP));
		 put(config.database.tables.columns.Activity.COLLECTION_VERSION, config.database.tables.Map.DB_TABLE.get(config.database.tables.columns.Activity.COLLECTION_VERSION));
		 
		 put(config.database.tables.columns.Activity.USERNAME, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.Activity.USERNAME));
		 put(config.database.tables.columns.Activity.DATE, "datetime");
		 put(config.database.tables.columns.Activity.TYPE, String.format("enum('%s','%s')", entities.Activity.type.DOWNLOAD,entities.Activity.type.VIEW));
	     
	   //define primary and references keys
	     put("", String.format("primary key(`%s`),"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete cascade on update cascade,"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete cascade on update cascade,"
	    		+ "foreign key(`%s`,`%s`,`%s`,`%s`) references `%s`(`%s`,`%s`,`%s`,`%s`) on delete no action on update cascade",
	    		 config.database.tables.columns.Activity.ID,
	    		 config.database.tables.columns.Activity.PURCHASE_ID, config.database.tables.Purchase.TABLE_NAME, config.database.tables.columns.Purchase.ID,
	    		 config.database.tables.columns.Activity.USERNAME, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME,
	    		 config.database.tables.columns.Activity.SHORTCOUNTRY, config.database.tables.columns.Activity.CITY, config.database.tables.columns.Activity.MAP, config.database.tables.columns.Activity.COLLECTION_VERSION,
	    		 config.database.tables.Map.TABLE_NAME,
	    		 config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.NAME, config.database.tables.columns.Map.COLLECTION_VERSION	 
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Activity() {}
	
}
