/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Country entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Country. Static class This class consist of Country queries table
 * on db scheme.
 */
public final class Country {
	/**
	 * Override public contractor to make it static.
	 */
	private Country() {
	}

	/* ======================== GET ============================= */
	
	/** The Constant MySQL_QUERY_GET_ALL_COUNTRIES. */
	public static final String MySQL_QUERY_GET_ALL_COUNTRIES = String.format("SELECT `*` " 
		      + "FROM `%s` "
			  + "ORDER BY `%s` " ,
				config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME);
	
	/** The Constant MySQL_QUERY_GET_COUNTRIES_EDIT. */
	public static final String MySQL_QUERY_GET_COUNTRIES_EDIT = 
			String.format("SELECT `%s`.`*`, count(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s` "
					+ "FROM `%s` "
					+ "LEFT JOIN (SELECT `%s`.`*`, "
					+ "(%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
					+ "FROM (%s) as `%s`) as `%s` "
					+ "ON `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s` "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_MAPS, config.database.tables.columns.Country.TOTAL_CITIES,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_MAPS, config.database.tables.columns.Country.TOTAL_MAPS,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_POIS, config.database.tables.columns.Country.TOTAL_POIS,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_TOURS, config.database.tables.columns.Country.TOTAL_TOURS,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.City.TABLE_NAME,
			
			
			String.format("SELECT COUNT(*) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					, config.database.tables.Map.TABLE_NAME,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
			config.database.tables.columns.City.TOTAL_MAPS,
			
			String.format("SELECT COUNT(*) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP),
			config.database.tables.columns.City.TOTAL_POIS,
			
			String.format("SELECT COUNT(DISTINCT `%s`.`%s`) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`"
					, 
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.NAME,
					config.database.tables.Tour.TABLE_NAME,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION),
			
			config.database.tables.columns.City.TOTAL_TOURS,
			config.database.queries.City.MySQL_QUERY_GET_CITIES_EDIT_COLLECTION_VERSIONS, 
			config.database.tables.City.TABLE_NAME,config.database.tables.City.TABLE_NAME,	
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,	
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME,
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME);

	/** The Constant MySQL_QUERY_GET_COUNTRIES. */
	public static final String MySQL_QUERY_GET_COUNTRIES = 
			String.format("SELECT `%s`.`*`, count(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s` "
					+ "FROM `%s`, (SELECT `%s`.`*`, "
					+ "(%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`<>'%s' ) as `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s` "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_MAPS, config.database.tables.columns.Country.TOTAL_CITIES,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_MAPS, config.database.tables.columns.Country.TOTAL_MAPS,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_POIS, config.database.tables.columns.Country.TOTAL_POIS,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.Country.TOTAL_TOURS, config.database.tables.columns.Country.TOTAL_TOURS,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.City.TABLE_NAME,
			
			
			String.format("SELECT COUNT(*) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					, config.database.tables.Map.TABLE_NAME,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
			config.database.tables.columns.City.TOTAL_MAPS,
			
			String.format("SELECT COUNT(*) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP),
			config.database.tables.columns.City.TOTAL_POIS,
			
			String.format("SELECT COUNT(DISTINCT `%s`.`%s`) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`"
					, 
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.NAME,
					config.database.tables.Tour.TABLE_NAME,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION),
			
			config.database.tables.columns.City.TOTAL_TOURS,
			
			config.database.tables.City.TABLE_NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION, config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
			config.database.tables.City.TABLE_NAME,	
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,	
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME,
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME);
	

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_COUNTRY. */
	public static final String MySQL_QUERY_ADD_COUNTRY = String.format(
			"INSERT INTO `%s`(" + "`%s`,`%s`,`%s`,`%s`,`%s`) values(?,?,?,?,?)",
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
			config.database.tables.columns.Country.NAME, config.database.tables.columns.Country.CAPITAL,
			config.database.tables.columns.Country.POPUALTION, config.database.tables.columns.Country.TOTAL_AREA);
	
	//------------

	/** The Constant MySQL_QUERY_ADD_COUNTRY_EDIT. */
	public static final String MySQL_QUERY_ADD_COUNTRY_EDIT = String.format(
			"INSERT INTO `%s`(" + "`%s`,`%s`,`%s`,`%s`,`%s`) values(?,?,?,?,?)",
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
			config.database.tables.columns.Country.NAME, config.database.tables.columns.Country.CAPITAL,
			config.database.tables.columns.Country.POPUALTION, config.database.tables.columns.Country.TOTAL_AREA);


	/* ======================== UPDATE ============================= */

	/* ======================== DELETE ============================= */

}
