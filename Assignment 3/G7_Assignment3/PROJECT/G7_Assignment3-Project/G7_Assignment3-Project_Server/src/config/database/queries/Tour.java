/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Tour entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Tour Static Class This class consist of Tour queries table on db
 * scheme.
 */
public final class Tour {
	/**
	 * Override public contractor to make it static.
	 */
	private Tour() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_RECOMMENDED_TOURS_BY_CITY. */
	public static final String MySQL_QUERY_GET_RECOMMENDED_TOURS_BY_CITY = String.format("SELECT `%s`.`*`, count(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=1 "
			+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
			+ "ORDER BY `%s`.`%s`",
			config.database.tables.Tour.TABLE_NAME, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.POI, config.database.tables.columns.Country.TOTAL_POIS,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SECTIME,
			config.database.tables.columns.Tour.TOTAL_DURATION,

			config.database.tables.Tour.TABLE_NAME, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.CITY, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.COLLECTION_VERSION, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.RECOMMENDED,

			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.NAME,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.NAME);
	
	/** The Constant MySQL_QUERY_GET_POISTOUR_BY_TOUR. */
	public static final String MySQL_QUERY_GET_POISTOUR_BY_TOUR = String.format("SELECT `%s`.`*`, `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
					+ "FROM `%s`,`%s` "
					+ "WHERE `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? "
					+ "AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.NAME,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.DESCRIPTION,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.RECOMMENDED,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.SECTIME,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.INDEX,
			config.database.tables.PlaceOfInterest.TABLE_NAME,config.database.tables.Tour.TABLE_NAME,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.CITY,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.NAME,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterest.TABLE_NAME,config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.CITY,
			config.database.tables.PlaceOfInterest.TABLE_NAME,config.database.tables.columns.PlaceOfInterest.CITY,
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterest.TABLE_NAME,config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,	
			config.database.tables.Tour.TABLE_NAME,config.database.tables.columns.Tour.POI,
			config.database.tables.PlaceOfInterest.TABLE_NAME,config.database.tables.columns.PlaceOfInterest.NAME,
			config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.INDEX);

/** The Constant MySQL_QUERY_GET_TOURS_BY_CITY. */
//--------------
	public static final String MySQL_QUERY_GET_TOURS_BY_CITY = String.format("SELECT `%s`.`*`, count(`%s`.`%s`) as `%s`, sum(`%s`.`%s`) as `%s` "
					+ "FROM `%s` "
					+ "WHERE `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? "
					+ "GROUP BY `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s` " + "ORDER BY `%s`.`%s`",
			config.database.tables.Tour.TABLE_NAME, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.POI, config.database.tables.columns.Country.TOTAL_POIS,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SECTIME,
			config.database.tables.columns.Tour.TOTAL_DURATION,

			config.database.tables.Tour.TABLE_NAME, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.CITY, config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.COLLECTION_VERSION,

			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.NAME,
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.NAME);

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_TOUR_PARTIAL. */
	public static final String MySQL_QUERY_ADD_TOUR_PARTIAL = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) values",
			config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
			config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.columns.Tour.DESCRIPTION, config.database.tables.columns.Tour.INDEX,
			config.database.tables.columns.Tour.POI, config.database.tables.columns.Tour.NAME,
			config.database.tables.columns.Tour.SECTIME, config.database.tables.columns.Tour.RECOMMENDED);
	//-----------------

	/** The Constant MySQL_QUERY_ADD_TOURS_NEW_COLLECTION_VERSION. */
	public static final String MySQL_QUERY_ADD_TOURS_NEW_COLLECTION_VERSION = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) "
			+ "SELECT `%s`,`%s`,? as `%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.columns.Tour.CITY, config.database.tables.columns.Tour.COLLECTION_VERSION,config.database.tables.columns.Tour.DESCRIPTION, config.database.tables.columns.Tour.INDEX, config.database.tables.columns.Tour.POI, config.database.tables.columns.Tour.NAME,config.database.tables.columns.Tour.SECTIME, config.database.tables.columns.Tour.RECOMMENDED,
			config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.columns.Tour.CITY, config.database.tables.columns.Tour.COLLECTION_VERSION,config.database.tables.columns.Tour.DESCRIPTION, config.database.tables.columns.Tour.INDEX, config.database.tables.columns.Tour.POI, config.database.tables.columns.Tour.NAME,config.database.tables.columns.Tour.SECTIME, config.database.tables.columns.Tour.RECOMMENDED,
			config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.SHORTCOUNTRY,config.database.tables.columns.Tour.CITY,config.database.tables.columns.Tour.COLLECTION_VERSION);
			
		
	 
	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_TOUR_WITHOUT_POIS. */
	public static final String MySQL_QUERY_UPDATE_TOUR_WITHOUT_POIS = String.format("UPDATE `%s` "
			+"SET `%s`=?,`%s`=?,`%s`=? "
			+"WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.NAME,
			config.database.tables.columns.Tour.DESCRIPTION,
			config.database.tables.columns.Tour.RECOMMENDED,
			
			config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.columns.Tour.CITY,			
			config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.columns.Tour.NAME);
	

	/* ======================== DELETE ============================= */
	
	/** The Constant MySQL_QUERY_DELETE_TOUR_FROM_CITY. */
	public static final String MySQL_QUERY_DELETE_TOUR_FROM_CITY=String.format("DELETE FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=?",
			config.database.tables.Tour.TABLE_NAME,
			config.database.tables.columns.Tour.CITY,
			config.database.tables.columns.Tour.SHORTCOUNTRY,
			config.database.tables.columns.Tour.COLLECTION_VERSION,
			config.database.tables.columns.Tour.NAME);
}
