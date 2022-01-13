/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Map entity 
 */
package config.database.queries;


// TODO: Auto-generated Javadoc
/**
 * The Class Map. Static class This class consist of Map queries table on db
 * scheme.
 */

public final class Map {
	/**
	 * Override public contractor to make it static.
	 */
	private Map() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_CITIES_EDIT_COLLECTION_VERSIONS. */
public static final String MySQL_QUERY_GET_CITIES_EDIT_COLLECTION_VERSIONS = 
		String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
				+ "FROM `%s` "
				+ "WHERE `%s`.`%s`='%s' "
				+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s` ",
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.City.NAME,
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
		config.database.tables.Map.TABLE_NAME,
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
		config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.City.NAME,
		config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION);

	/** The Constant MySQL_QUERY_GET_MAPS_TOTAL_POIS. */
	public static final String MySQL_QUERY_GET_MAPS_TOTAL_POIS = String.format(
			"SELECT `%s`.`*`, count(`%s`.`%s`) as `%s` " + "FROM `%s`, `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`",
			config.database.tables.Map.TABLE_NAME, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.NAME, config.database.tables.columns.Map.TOTAL_POIS,
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

	// ----------------
	
	
	/** The Constant MySQL_QUERY_GET_CITY_MAPS_BY_CITY. */
	public static final String MySQL_QUERY_GET_CITY_MAPS_BY_CITY = String.format(
			"SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`, (%s) as `%s` "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? "
					
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.NAME,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.MAP_VERSION,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.DESCRIPTION,
			
			String.format("SELECT COUNT(*) "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
					config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP,
					config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.NAME),
			config.database.tables.columns.Map.TOTAL_POIS,
			config.database.tables.Map.TABLE_NAME,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.NAME);

	// ------------------
	/** The Constant MySQL_QUERY_GET_ALL_CITY_MAPS. */
	public static final String MySQL_QUERY_GET_ALL_CITY_MAPS = String.format(
			"SELECT `*`" + "FROM `%s` " + "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.columns.Map.NAME);
	
	//-----------
	
	public static final String MYSQL_QUERY_GET_SEARCH_BY_TEXT_MAP = String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`, `%s`.`%s` , "
			+ " (%s) as `%s`, (%s) as `%s` "
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
			, config.database.tables.Map.TABLE_NAME,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.DESCRIPTION,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.CITY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION),
	config.database.tables.columns.City.TOTAL_MAPS,
	
	String.format("SELECT COUNT(*) "
			+ "FROM `%s` "
			+ "WHERE `%s`.`%s` LIKE ? AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
			, config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.DESCRIPTION,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.NAME ,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP,
			config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.SHORTCOUNTRY ,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			
			
			
			
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP),
	config.database.tables.columns.City.TOTAL_POIS,
	
	
	config.database.tables.City.TABLE_NAME,
	config.database.tables.Map.TABLE_NAME,
	config.database.tables.Country.TABLE_NAME,
	config.database.tables.Map.TABLE_NAME, config.database.tables.columns.Map.DESCRIPTION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.MAPSCOLLECTIONVERSION,
	config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY,
	config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.columns.City.TOTAL_MAPS,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.NAME,
	config.database.tables.City.TABLE_NAME, config.database.tables.columns.City.SHORTCOUNTRY);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_EXTERNAL_MAP_TO_CITY. */
	public static final String MySQL_QUERY_ADD_EXTERNAL_MAP_TO_CITY = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) " + 
			"SELECT `%s`,`%s`,'%s' as `%s`,`%s`,`%s`,`%s`,`%s` " + 
			"FROM `%s` " + 
			"WHERE `%s`=? AND `%s`=? AND `%s`=? " ,
			config.database.tables.Map.TABLE_NAME, 
			config.database.tables.columns.Map.SHORTCOUNTRY,config.database.tables.columns.Map.CITY,config.database.tables.columns.Map.COLLECTION_VERSION,config.database.tables.columns.Map.NAME,config.database.tables.columns.Map.MAP_VERSION,config.database.tables.columns.Map.DESCRIPTION,config.database.tables.columns.Map.IMAGE,
			config.database.tables.columns.Map.SHORTCOUNTRY,config.database.tables.columns.Map.CITY,config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,config.database.tables.columns.Map.COLLECTION_VERSION,config.database.tables.columns.Map.NAME,config.database.tables.columns.Map.MAP_VERSION,config.database.tables.columns.Map.DESCRIPTION,config.database.tables.columns.Map.IMAGE,
			config.database.tables.ExternalMap.TABLE_NAME, 
			config.database.tables.columns.ExternalMap.SHORTCOUNTRY,
			config.database.tables.columns.ExternalMap.CITY,
			config.database.tables.columns.ExternalMap.NAME);
	
	
	// ------------------
	
	/** The Constant MySQL_QUERY_ADD_MAPS_NEW_COLLECTION_VERSION. */
	public static final  String MySQL_QUERY_ADD_MAPS_NEW_COLLECTION_VERSION = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) "
			+ "SELECT `%s`,`%s`,? as `%s`,`%s`,`%s`,`%s`,`%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? AND EXISTS ("
			+ "SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s`)",
			config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.COLLECTION_VERSION,config.database.tables.columns.Map.NAME, config.database.tables.columns.Map.MAP_VERSION, config.database.tables.columns.Map.DESCRIPTION, config.database.tables.columns.Map.IMAGE,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.COLLECTION_VERSION,config.database.tables.columns.Map.NAME, config.database.tables.columns.Map.MAP_VERSION, config.database.tables.columns.Map.DESCRIPTION, config.database.tables.columns.Map.IMAGE,
			config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.SHORTCOUNTRY, config.database.tables.columns.Map.CITY, config.database.tables.columns.Map.COLLECTION_VERSION,
			
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.Map.TABLE_NAME,config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.Map.TABLE_NAME,config.database.tables.columns.Map.CITY,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.Map.TABLE_NAME,config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.MAP,
			config.database.tables.Map.TABLE_NAME,config.database.tables.columns.Map.NAME);
			
	 
	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_MAP. */
	public static final String MySQL_QUERY_UPDATE_MAP = String.format("UPDATE `%s` "
	+ "SET `%s`=?,`%s`=?,`%s`=?,`%s`=? "
	+ "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=?" ,
	config.database.tables.Map.TABLE_NAME,
	config.database.tables.columns.Map.NAME, config.database.tables.columns.Map.MAP_VERSION,
	config.database.tables.columns.Map.DESCRIPTION, config.database.tables.columns.Map.IMAGE, 
	
	config.database.tables.columns.Map.SHORTCOUNTRY,
	config.database.tables.columns.Map.CITY,
	config.database.tables.columns.Map.COLLECTION_VERSION,
	config.database.tables.columns.Map.NAME);
	
	
	/* ======================== DELETE ============================= */
	
	/** The Constant MySQL_QUERY_DELETE_MAP. */
	public static final String MySQL_QUERY_DELETE_MAP = String.format("DELETE FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY,
			config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.columns.Map.COLLECTION_VERSION,
			config.database.tables.columns.Map.NAME);
	
	/** The Constant MySQL_QUERY_DELETE_COLLECTION_VERSION. */
	public static final String MySQL_QUERY_DELETE_COLLECTION_VERSION = String.format("DELETE FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.Map.TABLE_NAME,
			config.database.tables.columns.Map.CITY,
			config.database.tables.columns.Map.SHORTCOUNTRY,
			config.database.tables.columns.Map.COLLECTION_VERSION);
	

}
