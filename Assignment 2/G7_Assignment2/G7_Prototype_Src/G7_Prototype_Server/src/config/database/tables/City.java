package config.database.tables;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class City.
 * Static class
 * This class consist of columns types of city table on db scheme. 
 */
public final class City {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "city";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
	     put(config.database.tables.columns.City.NAME, "varchar(50) primary key");
	     put(config.database.tables.columns.City.NO_MAPS, "int");
	     put(config.database.tables.columns.City.NO_POI, "int");
	     put(config.database.tables.columns.City.NO_TOURS, "int");
	     put(config.database.tables.columns.City.VERSION, "varchar(30)");
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private City() {}
	
}
