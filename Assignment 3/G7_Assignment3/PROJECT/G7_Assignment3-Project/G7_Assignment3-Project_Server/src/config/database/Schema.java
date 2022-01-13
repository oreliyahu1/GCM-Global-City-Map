package config.database;

import java.util.ArrayList;
import java.util.Map;
import javafx.util.Pair;

// TODO: Auto-generated Javadoc
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
	////put in arraylist the name of all the tables in the system
	public static final ArrayList<Pair<String, Map<String, String>>> TABLES = new ArrayList<Pair<String, Map<String, String>>>(){
		{
			add(new Pair<String, Map<String, String>>(config.database.tables.User.TABLE_NAME, config.database.tables.User.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.CreditCard.TABLE_NAME, config.database.tables.CreditCard.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Message.TABLE_NAME, config.database.tables.Message.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Rate.TABLE_NAME, config.database.tables.Rate.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Country.TABLE_NAME, config.database.tables.Country.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.City.TABLE_NAME, config.database.tables.City.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.ExternalMap.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Map.TABLE_NAME, config.database.tables.Map.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Category.TABLE_NAME, config.database.tables.Category.DB_TABLE));	
			add(new Pair<String, Map<String, String>>(config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.PlaceOfInterest.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.PlaceOfInterestMap.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Tour.TABLE_NAME, config.database.tables.Tour.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.VersionRequest.TABLE_NAME, config.database.tables.VersionRequest.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Purchase.TABLE_NAME, config.database.tables.Purchase.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.RateRequest.TABLE_NAME, config.database.tables.RateRequest.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.Activity.TABLE_NAME, config.database.tables.Activity.DB_TABLE));
			add(new Pair<String, Map<String, String>>(config.database.tables.DailyReport.TABLE_NAME, config.database.tables.DailyReport.DB_TABLE));
		}
	};
	
	
	/**
	 * Creates the table query.
	 *
	 * @return the array list
	 */
	//create tables query according to the tables names in TABLE
	public static ArrayList<String> createTableQuery() {
		ArrayList<String> colms = new ArrayList<String>();
		ArrayList<String> endQuery = new ArrayList<String>(); 
		ArrayList<String> newTablesQuery = new ArrayList<String>(); 
		for(Pair<String, Map<String, String>> table : TABLES)
		{
			String tableName = table.getKey();
			Map<String, String> map = table.getValue();
			for (String col : map.keySet()) {
				if(!col.equals(""))
					colms.add(String.format("`%s` %s", col, map.get(col)));
				else
					endQuery.add(map.get(col));
			}
			colms.addAll(endQuery);
			newTablesQuery.add(String.format("CREATE TABLE `%s`(%s)", tableName, String.join(", ", colms)));
			colms.clear();
			endQuery.clear();
		}
		return newTablesQuery;
	}
	
	/**
	 * Override public contractor to make it static.
	 */
	private Schema() {}
	
}
