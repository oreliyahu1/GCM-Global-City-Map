/*
 * this class defines VersionRequest table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class VersionRequest.
 * Static class
 * This class consist of columns types of VersionRequest table on db scheme. 
 */
public final class VersionRequest {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "versionrequest";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.VersionRequest.ID, "int auto_increment");
		 put(config.database.tables.columns.VersionRequest.SHORTCOUNTRY, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.VersionRequest.SHORTCOUNTRY));
		 put(config.database.tables.columns.VersionRequest.CITY, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.VersionRequest.CITY));
		 put(config.database.tables.columns.VersionRequest.COLLECTION_VERSION, config.database.tables.City.DB_TABLE.get(config.database.tables.columns.VersionRequest.COLLECTION_VERSION));
		 put(config.database.tables.columns.VersionRequest.USERNAME_REQUEST, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.VersionRequest.USERNAME_REQUEST));
		 put(config.database.tables.columns.VersionRequest.STATUS, String.format("enum('%s','%s','%s')", entities.AbstractRequest.STATUS.WAITING,entities.AbstractRequest.STATUS.APPROVE,entities.AbstractRequest.STATUS.REJECT));
		 put(config.database.tables.columns.VersionRequest.DATE, "datetime");
		 
	     
	   //define primary and references keys
	     put("", String.format("primary key(`%s`),"
	    		+ "foreign key(`%s`,`%s`) references `%s`(`%s`,`%s`) on delete cascade on update cascade,"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete cascade on update cascade",
	    		 config.database.tables.columns.VersionRequest.ID,
	    		 config.database.tables.columns.VersionRequest.SHORTCOUNTRY, config.database.tables.columns.VersionRequest.CITY,
	    		 config.database.tables.City.TABLE_NAME,
	    		 config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME,
	    		 config.database.tables.columns.VersionRequest.USERNAME_REQUEST, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private VersionRequest() {}
	
}
