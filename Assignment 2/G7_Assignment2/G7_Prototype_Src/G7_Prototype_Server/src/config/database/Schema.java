package config.database;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Schema.
 * Static class
 * Contains all the tables for scheme
 */
public final class Schema {
	
	/** The Constant DB_SCHEMA_NAME. */
	public static final String DB_SCHEMA_NAME = "GCM";
	
	/** The Constant TABLES. */
	@SuppressWarnings("serial")
	public static final Map<String,Map<String,String>> TABLES = new HashMap<String,Map<String,String>>()
	{
	   {
		   put(config.database.tables.City.TABLE_NAME , config.database.tables.City.DB_TABLE);
	   }
	};
	
	/**
	 * Override public contractor to make it static.
	 */
	private Schema() {}
	
}
