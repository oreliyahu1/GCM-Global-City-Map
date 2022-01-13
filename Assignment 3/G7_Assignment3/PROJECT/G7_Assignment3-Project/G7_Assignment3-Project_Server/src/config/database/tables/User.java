/*
 * this class defines User table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 * Static class
 * This class consist of columns types of user table on db scheme. 
 */
public final class User {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "accounts";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
	     put(config.database.tables.columns.User.FIRSTNAME, "varchar(50)");
	     put(config.database.tables.columns.User.LASTNAME, "varchar(50)");
	     put(config.database.tables.columns.User.PHONENUMBER, "varchar(20)");
	     put(config.database.tables.columns.User.EMAIL, "varchar(100)");
	     put(config.database.tables.columns.User.USERNAME, "varchar(32)");
	     put(config.database.tables.columns.User.PASSWORD, "varchar(32)");
	     put(config.database.tables.columns.User.PREMISSION, "bigint");
	     put(config.database.tables.columns.User.CREATED, "datetime");
	     
		   //define primary and references keys
	     put("", String.format("primary key(`%s`)",
	    		 config.database.tables.columns.User.USERNAME
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private User() {}
	
}
