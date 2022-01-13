/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the ExternalMap entity 
 */
package config.database.queries;


// TODO: Auto-generated Javadoc
/**
 * The Class ExternalMap. Static class This class consist of ExternalMap queries table on db
 * scheme.
 */


public final class ExternalMap {
	/**
	 * Override public contractor to make it static.
	 */
	private ExternalMap() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_CITY_EXTERNAL_MAPS. */
	public static final String MySQL_QUERY_GET_ALL_CITY_EXTERNAL_MAPS = String.format("SELECT `%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s`,`%s`.`%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? ",
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.SHORTCOUNTRY,
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.CITY,
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.NAME,
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.MAP_VERSION,
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.DESCRIPTION,
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.SHORTCOUNTRY,
			config.database.tables.columns.ExternalMap.CITY);
		
	/** The Constant MySQL_QUERY_GET_ALL_CITY_EXTERNAL_FULL_MAP. */
	public static final String MySQL_QUERY_GET_ALL_CITY_EXTERNAL_FULL_MAP = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? AND `%s`=? ",
			config.database.tables.ExternalMap.TABLE_NAME, config.database.tables.columns.ExternalMap.SHORTCOUNTRY,
			config.database.tables.columns.ExternalMap.CITY,config.database.tables.columns.Map.NAME);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_EXTERNAL_MAP. */
	//short, city, collectionVer, map, mapVer, description, image
	public static final String MySQL_QUERY_ADD_EXTERNAL_MAP = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) values(?,?,?,?,?,?)",
			config.database.tables.ExternalMap.TABLE_NAME, 
			config.database.tables.columns.ExternalMap.SHORTCOUNTRY,
			config.database.tables.columns.ExternalMap.CITY,
			config.database.tables.columns.ExternalMap.NAME,
			config.database.tables.columns.ExternalMap.MAP_VERSION,
			config.database.tables.columns.ExternalMap.DESCRIPTION,
			config.database.tables.columns.ExternalMap.IMAGE);
	
	
	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_EXTERNAL_MAP. */
	public static final String MySQL_QUERY_UPDATE_EXTERNAL_MAP = String.format("UPDATE `%s` "
	+ "SET `%s`=?,`%s`=?,`%s`=?,`%s`=? "
	+ "WHERE `%s`=? AND `%s`=? AND `%s`=?" ,
	config.database.tables.ExternalMap.TABLE_NAME,
	config.database.tables.columns.ExternalMap.NAME, config.database.tables.columns.ExternalMap.MAP_VERSION,
	config.database.tables.columns.ExternalMap.DESCRIPTION, config.database.tables.columns.ExternalMap.IMAGE,
	config.database.tables.columns.ExternalMap.SHORTCOUNTRY,
	config.database.tables.columns.ExternalMap.CITY,
	config.database.tables.columns.ExternalMap.NAME);
	
	
	/* ======================== DELETE ============================= */
	
}
