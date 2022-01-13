/*
 * this class defines DailyReport table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyReport.
 * Static class
 * This class consist of columns types of DailyReport table on db scheme. 
 */
public final class DailyReport {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "dailyreport";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
		 put(config.database.tables.columns.DailyReport.DATE, "date");
		 put(config.database.tables.columns.DailyReport.REPORT, "mediumblob");
	     
	   //define primary key
	     put("", String.format("primary key(`%s`)", config.database.tables.columns.DailyReport.DATE));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private DailyReport() {}
	
}
