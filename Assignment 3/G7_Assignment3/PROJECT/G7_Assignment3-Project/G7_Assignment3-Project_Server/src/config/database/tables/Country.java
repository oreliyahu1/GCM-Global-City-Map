/*
 * this class defines Country table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Country.
 * Static class
 * This class consist of columns types of country table on db scheme. 
 */
public final class Country {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "country";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
	     put(config.database.tables.columns.Country.NAME, "varchar(50)");
	     put(config.database.tables.columns.Country.CAPITAL, "varchar(50)");
	     put(config.database.tables.columns.Country.SHORT, "varchar(6)");
	     put(config.database.tables.columns.Country.POPUALTION, "bigint");
	     put(config.database.tables.columns.Country.TOTAL_AREA, "int");
	     
		   //define primary
	     put("", String.format("primary key(`%s`)", config.database.tables.columns.Country.SHORT));
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Country() {}
	
}
