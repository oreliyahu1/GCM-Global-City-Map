/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Message entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Message. Static class This class consist of Message queries table
 * on db scheme.
 */
public final class Message {
	/**
	 * Override public contractor to make it static.
	 */
	private Message() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_NOTIFICATIONS. */
	public static final String MySQL_QUERY_GET_ALL_NOTIFICATIONS = String.format(
			"SELECT `*`" + "FROM `%s` " + "WHERE `%s`=? " + "ORDER BY `%s` DESC", config.database.tables.Message.TABLE_NAME,
			config.database.tables.columns.Message.TO, config.database.tables.columns.Message.DATE);

	/** The Constant MySQL_QUERY_GET_NEW_NOTIFICATIONS. */
	public static final String MySQL_QUERY_GET_NEW_NOTIFICATIONS = String.format(
			"SELECT `*`" + "FROM `%s` " + "WHERE `%s`=? AND `%s`=0 " + "ORDER BY `%s` DESC ",
			config.database.tables.Message.TABLE_NAME, config.database.tables.columns.Message.TO,
			config.database.tables.columns.Message.IS_READ, config.database.tables.columns.Message.DATE);

	/** The Constant MySQL_QUERY_GET_NEW_TODAY_NOTIFICATIONS. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_NOTIFICATIONS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE DATE(`%s`)=? ",
			config.database.tables.Message.TABLE_NAME,
			config.database.tables.columns.Message.DATE);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_NOTIFICATION. */
	public static final String MySQL_QUERY_ADD_NOTIFICATION = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) " + "values(?,?,?,?,0,NOW())",
			config.database.tables.Message.TABLE_NAME, config.database.tables.columns.Message.FROM,
			config.database.tables.columns.Message.TO, config.database.tables.columns.Message.SUBJECT,
			config.database.tables.columns.Message.MESSAGE, config.database.tables.columns.Message.IS_READ,
			config.database.tables.columns.Message.DATE);

	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_READ_NOTIFICATION. */
	public static final String MySQL_QUERY_UPDATE_READ_NOTIFICATION = String.format(
			"UPDATE `%s`" + "SET `%s`=1 " + "WHERE `%s`=?", config.database.tables.Message.TABLE_NAME,
			config.database.tables.columns.Message.IS_READ, config.database.tables.columns.Message.ID);

	/* ======================== DELETE ============================= */

	/** The Constant MySQL_QUERY_DELETE_NOTIFICATION. */
	public static final String MySQL_QUERY_DELETE_NOTIFICATION = String.format(
			"DELETE " + "FROM `%s` " + "WHERE `%s`=?", config.database.tables.Message.TABLE_NAME,
			config.database.tables.columns.Message.ID);

	/** The Constant MySQL_QUERY_DELETE_ALL_NOTIFICATIONS. */
	public static final String MySQL_QUERY_DELETE_ALL_NOTIFICATIONS = String.format(
			"DELETE " + "FROM `%s` " + "WHERE `%s`=?", config.database.tables.Message.TABLE_NAME,
			config.database.tables.columns.Message.TO);

}
