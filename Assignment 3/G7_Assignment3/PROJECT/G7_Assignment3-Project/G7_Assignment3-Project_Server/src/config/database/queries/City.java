/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the City entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class City. Static class This class consist of City queries table on db
 * scheme.
 */
public final class City {
	/**
	 * Override public contractor to make it static.
	 */
	private City() {
	}

	/* ======================== GET ============================= */
			
	/** The Constant MySQL_QUERY_GET_ALL_CITIES. */
	public static final String MySQL_QUERY_GET_ALL_CITIES = String.format(
			"SELECT `*` " 
	      + "FROM `%s` "
		  + "ORDER BY `%s` " ,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME);
	
	/** The Constant MySQL_QUERY_GET_CITIES_EDIT_COLLECTION_VERSIONS. */
	public static final String MySQL_QUERY_GET_CITIES_EDIT_COLLECTION_VERSIONS = 
			String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`, %s as `%s` "
					+ "FROM `%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s` ",
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.DESCRIPTION,
			config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
			config.database.tables.City.TABLE_NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION);
	
	/** The Constant MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY_EDIT. */
	public static final String MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY_EDIT = 
			String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`,"
					+ " (%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
					+ "FROM (%s) as `%s` "
					+ "WHERE `%s`.`%s`=? "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.DESCRIPTION,
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
			
			MySQL_QUERY_GET_CITIES_EDIT_COLLECTION_VERSIONS, config.database.tables.City.TABLE_NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME);
	
	/** The Constant MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY. */
	public static final String MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY = 
			String.format("SELECT `%s`.`*`,"
					+ " (%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=? AND `%s`.`%s`<>'%s' "
					+ "ORDER BY `%s`.`%s`",
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
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
			config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME);
	
	/** The Constant MySQL_QUERY_GET_NEW_TODAY_COUNTRY_CITIES. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_COUNTRY_CITIES = 
			String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`,"
					+ " (%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
					+ "FROM `%s`,`%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`<>'%s' "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,

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
			
			String.format("SELECT COUNT(*) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					, config.database.tables.Tour.TABLE_NAME,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
					config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
					config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
			config.database.tables.columns.City.TOTAL_TOURS,
			
			config.database.tables.Country.TABLE_NAME,config.database.tables.City.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
			config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME);
//---------------
/** The Constant MySQL_QUERY_GET_TOTAL_POIS_CITIES. */
	public static final String MySQL_QUERY_GET_TOTAL_POIS_CITIES = String.format(
			"SELECT `%s`.`%s`, `%s`.`%s`, `%s`.`%s`, count(`%s`.`%s`) as `%s` " + "FROM `%s`, `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`",
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.columns.Country.TOTAL_POIS, config.database.tables.Map.TABLE_NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.COLLECTION_VERSION, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.NAME, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.COLLECTION_VERSION);
	
	/** The Constant MySQL_QUERY_GET_TOTAL_POIS_CITIES_EDIT. */
	//-----------------
	public static final String MySQL_QUERY_GET_TOTAL_POIS_CITIES_EDIT = String.format(
			"SELECT `%s`.`%s`, `%s`.`%s`, `%s`.`%s`, count(`%s`.`%s`) as `%s` " + "FROM `%s` " + "LEFT JOIN `%s` "
					+ "ON `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`",
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.columns.Country.TOTAL_POIS, config.database.tables.Map.TABLE_NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.COLLECTION_VERSION, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.NAME, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.COLLECTION_VERSION);
	
	//------------------

	/** The Constant MySQL_QUERY_GET_COLLECTION_VERSION_HISTORY. */
	public static final String MySQL_QUERY_GET_COLLECTION_VERSION_HISTORY = String.format(
			"SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s` " + "FROM `%s` " + "WHERE `%s`.`%s`=? AND `%s`.`%s`=? "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s` " + "ORDER BY `%s`.`%s` DESC",
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,

			config.database.tables.Map.TABLE_NAME, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY,

			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,

			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION);


//--------------
	/** The Constant MySQL_QUERY_GET_LAST_COLLECTION_VERSION. */
	public static final String MySQL_QUERY_GET_LAST_COLLECTION_VERSION = String.format(
			"SELECT `%s`.`*` " + "FROM `%s` " + "WHERE `%s`.`%s`=? AND `%s`.`%s`=? ",
			config.database.tables.City.TABLE_NAME, config.database.tables.City.TABLE_NAME,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME);
	
	//----------------
	
	
	public static final String MYSQL_QUERY_GET_SEARCH_BY_TEXT_CITY = String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`, `%s`.`%s` , "
			+ " (%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
			+ "FROM `%s` ,`%s`  "
			+ "WHERE `%s`.`%s` LIKE ? AND `%s`.`%s`<>'%s' AND `%s`.`%s`=`%s`.`%s`"
			+ "ORDER BY `%s`.`%s` , `%s`.`%s` ",
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.DESCRIPTION,
	

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
	
	String.format("SELECT COUNT(*) "
			+ "FROM `%s` "
			+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
			, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
	config.database.tables.columns.City.TOTAL_TOURS,
	
	config.database.tables.City.TABLE_NAME,
	config.database.tables.Country.TABLE_NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.DESCRIPTION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT);
	
	//-------------
	
	/** The Constant MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY. */
	public static final String MySQL_QUERY_GET_SEARCH_BY_CITY_NAME = String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`, `%s`.`%s` , "
			+ " (%s) as `%s`, (%s) as `%s`, (%s) as `%s` "
			+ "FROM `%s` ,`%s`  "
			+ "WHERE `%s`.`%s` LIKE ? AND `%s`.`%s`<>'%s' AND `%s`.`%s`=`%s`.`%s`"
			+ "ORDER BY `%s`.`%s`",
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.DESCRIPTION,
	

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
	
	String.format("SELECT COUNT(*) "
			+ "FROM `%s` "
			+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
			, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
	config.database.tables.columns.City.TOTAL_TOURS,
	
	config.database.tables.City.TABLE_NAME,
	config.database.tables.Country.TABLE_NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME);
	
	/** The Constant MySQL_QUERY_ADD_CITY_TO_COUNTRY. */	
	/* ======================== ADD ============================= */
	public static final String MySQL_QUERY_ADD_CITY_TO_COUNTRY = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`) values(?,?,?,?)", config.database.tables.City.TABLE_NAME,
			config.database.tables.columns.City.SHORTCOUNTRY, config.database.tables.columns.City.NAME,
			config.database.tables.columns.City.MAPSCOLLECTIONVERSION, config.database.tables.columns.City.DESCRIPTION);
	
	/** The Constant MySQL_QUERY_UPDATE_CITY. */
	/* ======================== UPDATE ============================= */
	public static final String MySQL_QUERY_UPDATE_CITY = String.format("UPDATE `%s` "
			+ "SET `%s`=?,`%s`=? " 
			+ "WHERE `%s`=? AND `%s`=?",
			config.database.tables.City.TABLE_NAME,
			config.database.tables.columns.City.NAME,
			config.database.tables.columns.City.DESCRIPTION,
			config.database.tables.columns.City.NAME,
			config.database.tables.columns.City.SHORTCOUNTRY);

	/** The Constant MySQL_QUERY_UPDATE_VERSION_CITY. */
	public static final String MySQL_QUERY_UPDATE_VERSION_CITY = String.format("UPDATE `%s` "
			+ "SET `%s`=? " 
			+ "WHERE `%s`=? AND `%s`=?",
			config.database.tables.City.TABLE_NAME,
			config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
			config.database.tables.columns.City.NAME,
			config.database.tables.columns.City.SHORTCOUNTRY);
	
	/* ======================== DELETE ============================= */

}
