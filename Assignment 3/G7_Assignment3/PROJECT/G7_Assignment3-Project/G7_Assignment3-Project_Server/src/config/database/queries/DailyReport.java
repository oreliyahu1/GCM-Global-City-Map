/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the DailyReport entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyReport. Static class This class consist of DailyReport queries
 * table on db scheme.
 */
public final class DailyReport {
	/**
	 * Override public contractor to make it static.
	 */
	private DailyReport() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_DB_DATE. */
	public static final String MySQL_QUERY_GET_DB_DATE = "SELECT CURDATE()";
	
	/** The Constant MySQL_QUERY_GET_REPORTS. */
	public static final String MySQL_QUERY_GET_REPORTS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s`>=? AND `%s`<=?",
			config.database.tables.DailyReport.TABLE_NAME,
			config.database.tables.columns.DailyReport.DATE,
			config.database.tables.columns.DailyReport.DATE);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_RATE_REQUEST. */
	public static final String MySQL_QUERY_ADD_RATE_REQUEST = String.format(
			"INSERT INTO `%s`(`%s`,`%s`) values(?,?)",
			config.database.tables.DailyReport.TABLE_NAME,
			config.database.tables.columns.DailyReport.DATE,
			config.database.tables.columns.DailyReport.REPORT);
	
	/* ======================== UPDATE ============================= */


	/* ======================== DELETE ============================= */

	/** The Constant MySQL_QUERY_DELETE_RATE_REQUEST. */
	public static final String MySQL_QUERY_DELETE_RATE_REQUEST = String.format(
			"DELETE " + "FROM `%s` " + "WHERE `%s`=?", config.database.tables.RateRequest.TABLE_NAME,
			config.database.tables.columns.RateRequest.ID);

}
