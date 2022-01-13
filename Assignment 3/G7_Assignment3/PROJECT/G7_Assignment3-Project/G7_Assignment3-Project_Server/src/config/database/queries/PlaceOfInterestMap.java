/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the PlaceOfInterestMap entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterestMap. Static class This class consist of
 * PlaceOfInterestMap queries table on db scheme.
 */
public final class PlaceOfInterestMap {
	/**
	 * Override public contractor to make it static.
	 */
	private PlaceOfInterestMap() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_POIS_BY_MAP. */
	public static final String MySQL_QUERY_GET_POIS_BY_MAP = String.format(
			"SELECT `%s`.`*`, `%s`.`%s`,`%s`.`%s`,`%s`.`%s` " + "FROM `%s`,`%s` "
					+ "WHERE `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? "
					+ "AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.DESCRIPTION,
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.ACCESSIBLE,
			config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.TYPE,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP,

			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.NAME);

	// ----------------

	/** The Constant MySQL_QUERY_GET_TOTAL_POIS_MAPS. */
	public static final String MySQL_QUERY_GET_TOTAL_POIS_MAPS = String.format(
			"SELECT `%s`.`*`, count(`%s`.`%s`) as `%s` " + "FROM `%s` "
					+ "LEFT JOIN `%s` ON `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`",
			config.database.tables.Map.TABLE_NAME, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.columns.Country.TOTAL_POIS,
			config.database.tables.Map.TABLE_NAME, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.COLLECTION_VERSION, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION, config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.NAME, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.MAP,

			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.NAME);
	
	
	//------------------

	/** The Constant MYSQL_QUERY_GET_SEARCH_BY_POI. */
	public static final String MYSQL_QUERY_GET_SEARCH_BY_POI = String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`, `%s`.`%s` , "
			+ " (%s) as `%s` "
			+ "FROM `%s` , `%s` , `%s` "
			+ "WHERE `%s`.`%s` LIKE ? AND `%s`.`%s`<>'%s' AND `%s`.`%s`=`%s`.`%s` "
			+ "GROUP BY  `%s`.`%s`  " 
			+ " HAVING (`%s`)>0 "
			+ "ORDER BY `%s`.`%s` AND `%s`.`%s` ",
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.DESCRIPTION,
	

	String.format("SELECT COUNT(*) "
			+ "FROM `%s` "
			+ "WHERE `%s`.`%s` LIKE ? AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
			, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
	config.database.tables.columns.City.TOTAL_MAPS,
	
	
	
	config.database.tables.City.TABLE_NAME,
	config.database.tables.PlaceOfInterestMap.TABLE_NAME,
	config.database.tables.Country.TABLE_NAME,
	config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.columns.City.TOTAL_MAPS,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY		
	);
	

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_POI_TO_MAP. */
	public static final String MySQL_QUERY_ADD_POI_TO_MAP = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) " + "values(?,?,?,?,?,?,?)",
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.columns.PlaceOfInterestMap.NAME,
			config.database.tables.columns.PlaceOfInterestMap.MAP,
			config.database.tables.columns.PlaceOfInterestMap.LOCATION_X,
			config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y);
	//---------------

	/** The Constant MySQL_QUERY_ADD_POISMAP_NEW_COLLECTION_VERSION. */
	public static final String MySQL_QUERY_ADD_POISMAP_NEW_COLLECTION_VERSION = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) "
			+ "SELECT `%s`,`%s`,? as `%s`,`%s`,`%s`,`%s`,`%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.columns.PlaceOfInterestMap.LOCATION_X, config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterestMap.CITY, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.columns.PlaceOfInterestMap.MAP, config.database.tables.columns.PlaceOfInterestMap.LOCATION_X, config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,config.database.tables.columns.PlaceOfInterestMap.CITY,config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION);
			
	/** The Constant MySQL_QUERY_UPDATE_POI_TO_MAP_EDIT. */
	/* ======================== UPDATE ============================= */
	public static final String MySQL_QUERY_UPDATE_POI_TO_MAP_EDIT = String.format("UPDATE `%s`\r\n" + 
			"SET `%s`=? , `%s`=?\r\n" + 
			"WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=? AND  `%s`=? ;\r\n" , 
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.LOCATION_X,
			config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y,
			config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.columns.PlaceOfInterestMap.NAME,
			config.database.tables.columns.PlaceOfInterestMap.MAP);
		
	
	

	/* ======================== DELETE ============================= */

	/** The Constant MySQL_QUERY_DELETE_POI_FROM_MAP. */
	public static final String MySQL_QUERY_DELETE_POI_FROM_MAP = String.format(
			"DELETE FROM `%s` " + "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=?  AND `%s`=? ",
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.columns.PlaceOfInterestMap.NAME,
			config.database.tables.columns.PlaceOfInterestMap.MAP);
	


}
