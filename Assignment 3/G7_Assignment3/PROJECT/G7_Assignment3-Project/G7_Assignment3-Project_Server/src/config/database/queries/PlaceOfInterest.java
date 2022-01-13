/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the PlaceOfInterest entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterest. Static class This class consist of PlaceOfInterest
 * queries table on db scheme.
 */
public final class PlaceOfInterest {
	/**
	 * Override public contractor to make it static.
	 */
	private PlaceOfInterest() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_POIS_BY_CITY. */
	public static final String MySQL_QUERY_GET_POIS_BY_CITY = String.format(
			"SELECT `%s`.`*` " + "FROM `%s`" + "WHERE `%s`.`%s`=? AND `%s`.`%s`=? AND `%s`.`%s`=? "
					+ "ORDER BY `%s`.`%s`",
			config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
			config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
			config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.NAME);

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_POI_TO_CITY. */
		public static final String MySQL_QUERY_ADD_POI_TO_CITY = String.format(
				"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) " + "values(?,?,?,?,?,?,?)",
				config.database.tables.PlaceOfInterest.TABLE_NAME,
				config.database.tables.columns.PlaceOfInterest.ACCESSIBLE,
				config.database.tables.columns.PlaceOfInterest.CITY,
				config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
				config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
				config.database.tables.columns.PlaceOfInterest.DESCRIPTION,
				config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.columns.PlaceOfInterest.TYPE);
		
		/** The Constant MySQL_QUERY_ADD_POIS_NEW_COLLECTION_VERSION. */
		public static final String MySQL_QUERY_ADD_POIS_NEW_COLLECTION_VERSION = String.format(
				"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) "
				+ "SELECT DISTINCT `%s`,`%s`,? as `%s`,`%s`,`%s`,`%s`,`%s` "
				+ "FROM `%s` "
				+ "WHERE `%s`=? AND `%s`=? AND `%s`=? "
				+ "AND EXISTS("
				+ "(SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
				+ "FROM `%s` "
				+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=`%s`.`%s`) "
				+ "UNION DISTINCT "
				+ "(SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
				+ "FROM `%s` "
				+ "WHERE `poimap`.`short`=`poi`.`short` AND `poimap`.`city`=`poi`.`city` AND `poimap`.`collectionVer`=`poi`.`collectionVer` AND `poimap`.`poi`=`poi`.`poi`))",
				config.database.tables.PlaceOfInterest.TABLE_NAME,
				config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION, config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.columns.PlaceOfInterest.DESCRIPTION, config.database.tables.columns.PlaceOfInterest.TYPE,config.database.tables.columns.PlaceOfInterest.ACCESSIBLE,
				config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,config.database.tables.columns.PlaceOfInterest.CITY,config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,config.database.tables.columns.PlaceOfInterest.NAME,config.database.tables.columns.PlaceOfInterest.DESCRIPTION,config.database.tables.columns.PlaceOfInterest.TYPE,config.database.tables.columns.PlaceOfInterest.ACCESSIBLE,
				config.database.tables.PlaceOfInterest.TABLE_NAME,config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,config.database.tables.columns.PlaceOfInterest.CITY,config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
				config.database.tables.Tour.TABLE_NAME,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.SHORTCOUNTRY,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.CITY,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.CITY,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.COLLECTION_VERSION,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
				config.database.tables.Tour.TABLE_NAME, config.database.tables.columns.Tour.POI,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.NAME,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.SHORTCOUNTRY,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.CITY,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.CITY,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.COLLECTION_VERSION,
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
				config.database.tables.PlaceOfInterestMap.TABLE_NAME, config.database.tables.columns.PlaceOfInterestMap.NAME);
	/* ======================== UPDATE ============================= */

		
		/** The Constant MySQL_QUERY_UPDATE_POI_OF_CITY. */
	public static final String MySQL_QUERY_UPDATE_POI_OF_CITY = String.format(
				"UPDATE `%s` " + "SET `%s`=?, `%s`=?, `%s`=?, `%s`=?  " + "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=? ",
				config.database.tables.PlaceOfInterest.TABLE_NAME, config.database.tables.columns.PlaceOfInterest.NAME,
				config.database.tables.columns.PlaceOfInterest.ACCESSIBLE,
				config.database.tables.columns.PlaceOfInterest.DESCRIPTION,
				config.database.tables.columns.PlaceOfInterest.TYPE,

				config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
				config.database.tables.columns.PlaceOfInterest.CITY,
				config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
				config.database.tables.columns.PlaceOfInterest.NAME);
	/* ======================== DELETE ============================= */
		
		/** The Constant MySQL_QUERY_DELETE_POI_FROM_CITY. */
	public static final String MySQL_QUERY_DELETE_POI_FROM_CITY = String.format("DELETE FROM `%s` "
				+ "WHERE `%s`=? AND `%s`=? AND `%s`=? AND `%s`=? ",
				config.database.tables.PlaceOfInterest.TABLE_NAME,
				config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
				config.database.tables.columns.PlaceOfInterest.CITY,
				config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
				config.database.tables.columns.PlaceOfInterest.NAME);

	/** The Constant MySQL_QUERY_DELETE_POIS_FROM_CITY. */
public static final String MySQL_QUERY_DELETE_POIS_FROM_CITY = String.format("DELETE FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.PlaceOfInterest.TABLE_NAME,
			config.database.tables.columns.PlaceOfInterest.CITY,
			config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY,
			config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION);		


}
