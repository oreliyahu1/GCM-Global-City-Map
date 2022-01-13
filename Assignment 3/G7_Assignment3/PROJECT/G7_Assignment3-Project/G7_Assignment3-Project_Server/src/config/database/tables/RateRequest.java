/*
 * this class defines RateRequest table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class RateRequest.
 * Static class
 * This class consist of columns types of RateRequest table on db scheme. 
 */
public final class RateRequest {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "raterequest";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.RateRequest.ID, "int auto_increment");
		 
		 put(config.database.tables.columns.RateRequest.TYPE, config.database.tables.Rate.DB_TABLE.get(config.database.tables.columns.RateRequest.TYPE));
		 put(config.database.tables.columns.RateRequest.NUMBEROFDAYS, config.database.tables.Rate.DB_TABLE.get(config.database.tables.columns.RateRequest.NUMBEROFDAYS));
		 put(config.database.tables.columns.RateRequest.PRICE, config.database.tables.Rate.DB_TABLE.get(config.database.tables.columns.RateRequest.PRICE));
		 
		 put(config.database.tables.columns.RateRequest.USERNAME_REQUEST, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.RateRequest.USERNAME_REQUEST));
		 put(config.database.tables.columns.RateRequest.STATUS, String.format("enum('%s','%s','%s')", entities.AbstractRequest.STATUS.WAITING,entities.AbstractRequest.STATUS.APPROVE,entities.AbstractRequest.STATUS.REJECT));
		 put(config.database.tables.columns.RateRequest.DATE, "datetime");
		 
	     
	   //define primary and references keys
	     put("", String.format("primary key(`%s`),"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete cascade on update cascade",
	    		 config.database.tables.columns.RateRequest.ID,
	    		 config.database.tables.columns.RateRequest.USERNAME_REQUEST, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private RateRequest() {}
	
}
