/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the RateRequest entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class RateRequest. Static class This class consist of RateRequest queries
 * table on db scheme.
 */
public final class RateRequest {
	/**
	 * Override public contractor to make it static.
	 */
	private RateRequest() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_RATE_REQUESTS. */
	public static final String MySQL_QUERY_GET_ALL_RATE_REQUESTS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "ORDER BY `%s`", config.database.tables.RateRequest.TABLE_NAME,
			config.database.tables.columns.RateRequest.DATE);

	/** The Constant MySQL_QUERY_GET_USER_RATE_REQUEST_RESPONSE. */
	public static final String MySQL_QUERY_GET_USER_RATE_REQUEST_RESPONSE = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s`= ? "
			+ "ORDER BY `%s`",
			config.database.tables.RateRequest.TABLE_NAME, config.database.tables.columns.RateRequest.USERNAME_REQUEST,
			config.database.tables.columns.RateRequest.DATE);

	/** The Constant MySQL_QUERY_GET_NEW_TODAY_RATE_REQUESTS. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_RATE_REQUESTS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE DATE(`%s`)=? ",
			config.database.tables.RateRequest.TABLE_NAME,
			config.database.tables.columns.RateRequest.DATE);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_RATE_REQUEST. */
	// date, price, days, id, type, username, status
	public static final String MySQL_QUERY_ADD_RATE_REQUEST = String.format(
			"INSERT INTO `%s`(`%s`, `%s`, `%s`, `%s`, `%s`, `%s`) " + "values(NOW(),?,?,?,?,?)",
			config.database.tables.RateRequest.TABLE_NAME, config.database.tables.columns.RateRequest.DATE,
			config.database.tables.columns.RateRequest.PRICE, config.database.tables.columns.RateRequest.NUMBEROFDAYS,
			config.database.tables.columns.RateRequest.TYPE,
			config.database.tables.columns.RateRequest.USERNAME_REQUEST,
			config.database.tables.columns.RateRequest.STATUS);
	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_APPROVAL_RATE_REQUEST. */
	public static final String MySQL_QUERY_UPDATE_APPROVAL_RATE_REQUEST = String.format("UPDATE `%s` "
			+ "SET `%s`=? "
			+ "WHERE `%s`=?", config.database.tables.RateRequest.TABLE_NAME,
			config.database.tables.columns.RateRequest.STATUS,
			config.database.tables.columns.RateRequest.ID);
	
	

	/* ======================== DELETE ============================= */

	/** The Constant MySQL_QUERY_DELETE_RATE_REQUEST. */
	public static final String MySQL_QUERY_DELETE_RATE_REQUEST = String.format("DELETE "
			+ "FROM `%s` "
			+ "WHERE `%s` = ? ", config.database.tables.RateRequest.TABLE_NAME,
			config.database.tables.columns.RateRequest.ID);

}
