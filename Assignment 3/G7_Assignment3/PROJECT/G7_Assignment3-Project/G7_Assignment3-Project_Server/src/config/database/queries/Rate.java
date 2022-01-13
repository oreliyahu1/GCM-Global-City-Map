/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Rate entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Rate. Static class This class consist of Rate queries table on db
 * scheme.
 */
public final class Rate {
	/**
	 * Override public contractor to make it static.
	 */
	private Rate() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_RATES. */
	public static final String MySQL_QUERY_GET_ALL_RATES = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "ORDER BY `%s`",
			config.database.tables.Rate.TABLE_NAME, config.database.tables.columns.Rate.PRICE);

	/** The Constant MySQL_QUERY_GET_RATES_BY_TYPE. */
	public static final String MySQL_QUERY_GET_RATES_BY_TYPE = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s` = ? "
			+ "ORDER BY `%s`",
			config.database.tables.Rate.TABLE_NAME, config.database.tables.columns.Rate.TYPE,
			config.database.tables.columns.Rate.PRICE);

	/** The Constant MySQL_QUERY_GET_RATES. */
	public static final String MySQL_QUERY_GET_RATES = String.format("SELECT `*` FROM `%s`", config.database.tables.Rate.TABLE_NAME);

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_RATE. */
	public static final String MySQL_QUERY_ADD_RATE = String.format("INSERT INTO `%s`(`%s`,`%s`,`%s`) values(?,?,?) "
			+ "ON DUPLICATE KEY UPDATE `%s`=VALUES(`%s`),`%s`=VALUES(`%s`),`%s`=VALUES(`%s`) ",
			config.database.tables.Rate.TABLE_NAME,
			config.database.tables.columns.Rate.PRICE,config.database.tables.columns.Rate.NUMBEROFDAYS,config.database.tables.columns.Rate.TYPE,
			config.database.tables.columns.Rate.PRICE,config.database.tables.columns.Rate.PRICE,
			config.database.tables.columns.Rate.NUMBEROFDAYS,config.database.tables.columns.Rate.NUMBEROFDAYS,
			config.database.tables.columns.Rate.TYPE,config.database.tables.columns.Rate.TYPE);
	
	/* ======================== UPDATE ============================= */

/** The Constant MySQL_QUERY_UPDATE_RATE. */
	public static final String MySQL_QUERY_UPDATE_RATE = String.format("UPDATE `%s` "
			+ "SET `%s`=?,`%s`=?,`%s`=? "
			+ "WHERE `%s`=? AND `%s`=? ",
			config.database.tables.Rate.TABLE_NAME,
			config.database.tables.columns.Rate.PRICE, config.database.tables.columns.Rate.NUMBEROFDAYS, config.database.tables.columns.Rate.TYPE,
			config.database.tables.columns.Rate.TYPE,
			config.database.tables.columns.Rate.NUMBEROFDAYS);
	/* ======================== DELETE ============================= */

	/** The Constant MySQL_QUERY_DELETE_RATE. */
	public static final String MySQL_QUERY_DELETE_RATE = String.format("DELETE FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=? ", config.database.tables.Rate.TABLE_NAME,
			config.database.tables.columns.Rate.PRICE, config.database.tables.columns.Rate.NUMBEROFDAYS);
	
	
	


}
