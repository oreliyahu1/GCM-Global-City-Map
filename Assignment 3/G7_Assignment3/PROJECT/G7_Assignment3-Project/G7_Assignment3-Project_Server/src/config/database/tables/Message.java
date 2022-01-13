/*
 * this class defines Message table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 * Static class
 * This class consist of columns types of Message table on db scheme. 
 */
public final class Message {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "message";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.Message.ID, "int auto_increment");
		 put(config.database.tables.columns.Message.FROM, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.User.USERNAME));
		 put(config.database.tables.columns.Message.TO, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.User.USERNAME));
		 put(config.database.tables.columns.Message.MESSAGE, "text");
		 put(config.database.tables.columns.Message.SUBJECT, "varchar(300)");
		 put(config.database.tables.columns.Message.DATE, "datetime");
		 put(config.database.tables.columns.Message.IS_READ, "bool");
	     
	   //define primary and references keys
	     put("", String.format("primary key(`%s`),"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete no action on update cascade,"
	    		+ "foreign key(`%s`) references `%s`(`%s`) on delete no action on update cascade",
	    		 config.database.tables.columns.Message.ID,
	    		 config.database.tables.columns.Message.FROM, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME,
	    		 config.database.tables.columns.Message.TO, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME
	     		));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Message() {}
	
}
