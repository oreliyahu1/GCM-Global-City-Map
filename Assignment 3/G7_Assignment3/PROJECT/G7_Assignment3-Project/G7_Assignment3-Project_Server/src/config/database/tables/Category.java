/*
 * this class defines Category table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Category.
 * Static class
 * This class consist of columns types of Category table on db scheme. 
 */
public final class Category {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "category";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
	     put(config.database.tables.columns.Category.NAME, "varchar(50)");
	     
		   //define primary
	     put("", String.format("primary key(`%s`)", config.database.tables.columns.Category.NAME));
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Category() {}
	
}
