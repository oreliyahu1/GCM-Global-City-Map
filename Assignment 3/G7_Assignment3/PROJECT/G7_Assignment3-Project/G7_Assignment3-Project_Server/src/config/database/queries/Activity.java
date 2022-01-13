/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Activity entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Activity. Static class This class consist of Activity queries table
 * on db scheme.
 */
public final class Activity {
	/**
	 * Override public contractor to make it static.
	 */
	private Activity() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_ACTIVITIES_BY_USERNAME. */
	/*
	 * SELECT * FROM activity WHERE username='haia' ORDER BY date DESC
	 */
	public static final String MySQL_QUERY_GET_ALL_ACTIVITIES_BY_USERNAME = String.format(
			"SELECT `%s`.`*`,`%s`.`%s` " + "FROM `%s`,`%s` " + "WHERE `%s`.`%s`=? AND `%s`.`%s`=`%s`.`%s` "
					+ "ORDER BY `%s`.`%s` DESC",
			config.database.tables.Activity.TABLE_NAME, config.database.tables.Country.TABLE_NAME,
			config.database.tables.columns.Country.NAME, config.database.tables.Activity.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,

			config.database.tables.Activity.TABLE_NAME, config.database.tables.columns.Activity.USERNAME,
			config.database.tables.Activity.TABLE_NAME, config.database.tables.columns.Activity.SHORTCOUNTRY,
			config.database.tables.Country.TABLE_NAME, config.database.tables.columns.Country.SHORT,

			config.database.tables.Activity.TABLE_NAME, config.database.tables.columns.Activity.DATE);

	/** The Constant MySQL_QUERY_GET_NEW_TODAY_ACTIVITIES. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_ACTIVITIES = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE DATE(`%s`)=? ",
			config.database.tables.Activity.TABLE_NAME,
			config.database.tables.columns.Activity.DATE);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_ACTIVITY. */
	public static final String MySQL_QUERY_ADD_ACTIVITY = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) values(NOW(),?,?,?,?,?,?,?)",
			config.database.tables.Activity.TABLE_NAME, config.database.tables.columns.Activity.DATE,
			config.database.tables.columns.Activity.CITY, config.database.tables.columns.Activity.PURCHASE_ID,
			config.database.tables.columns.Activity.SHORTCOUNTRY,
			config.database.tables.columns.Activity.COLLECTION_VERSION, config.database.tables.columns.Activity.TYPE,
			config.database.tables.columns.Activity.MAP, config.database.tables.columns.Activity.USERNAME);
	/* ======================== UPDATE ============================= */

	/* ======================== DELETE ============================= */

}
