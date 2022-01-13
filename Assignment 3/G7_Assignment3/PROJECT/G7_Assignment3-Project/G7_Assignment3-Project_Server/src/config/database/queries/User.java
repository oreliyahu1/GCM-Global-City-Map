/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the User entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class User. Static class This class consist of User queries table on db
 * scheme.
 */
public final class User {
	/**
	 * Override public contractor to make it static.
	 */
	private User() {
	}

	/* ======================== GET ============================= */
	
	/** The Constant MySQL_QUERY_GET_USER_DETAILS. */
	public static final String MySQL_QUERY_GET_USER_DETAILS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s`=`?`",
			config.database.tables.User.TABLE_NAME,
			config.database.tables.columns.User.USERNAME);

	/** The Constant MySQL_QUERY_GET_ALL_USERS. */
	public static final String MySQL_QUERY_GET_ALL_USERS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "ORDER BY `%s`,`%s` ",
			config.database.tables.User.TABLE_NAME,
			config.database.tables.columns.User.FIRSTNAME, config.database.tables.columns.User.LASTNAME);

	/** The Constant MySQL_QUERY_GET_LOGIN. */
	public static final String MySQL_QUERY_GET_LOGIN = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s`=? AND `%s`=?", config.database.tables.User.TABLE_NAME,
			config.database.tables.columns.User.USERNAME, config.database.tables.columns.User.PASSWORD);

	/** The Constant MySQL_QUERY_GET_NEW_TODAY_USERS. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_USERS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE DATE(`%s`)=? ",
			config.database.tables.User.TABLE_NAME,
			config.database.tables.columns.User.CREATED);
	
	/** The Constant MySQL_QUERY_GET_USER_BY_PERMISSION. */
	public static final String MySQL_QUERY_GET_USER_BY_PERMISSION = String.format("SELECT `%s`.`%s` "
			+ "FROM `%s` "
			+ "WHERE `%s`&?<>0",
			config.database.tables.User.TABLE_NAME,config.database.tables.columns.User.USERNAME,
			config.database.tables.User.TABLE_NAME,
			config.database.tables.columns.User.PREMISSION);
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_USER. */
	public static final String MySQL_QUERY_ADD_USER = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) values(?,?,?,?,?,?,?,NOW())",
			config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME,
			config.database.tables.columns.User.FIRSTNAME, config.database.tables.columns.User.LASTNAME,
			config.database.tables.columns.User.EMAIL, config.database.tables.columns.User.PASSWORD,
			config.database.tables.columns.User.PHONENUMBER, config.database.tables.columns.User.PREMISSION,
			config.database.tables.columns.User.CREATED);

	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_ACCOUNT_DETAILS. */
	public static final String MySQL_QUERY_UPDATE_ACCOUNT_DETAILS = String.format("UPDATE `%s` "
			+ "SET `%s`=?, `%s`=?, `%s`=?, `%s`=?  "
			+ "WHERE `%s`=?",
			config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.FIRSTNAME,
			config.database.tables.columns.User.LASTNAME, config.database.tables.columns.User.PHONENUMBER,
			config.database.tables.columns.User.EMAIL, config.database.tables.columns.User.USERNAME);

	/** The Constant MySQL_QUERY_UPDATE_ACCOUNT_PASSWORD. */
	public static final String MySQL_QUERY_UPDATE_ACCOUNT_PASSWORD = String.format("UPDATE `%s` "
			+ "SET `%s`=? "
			+ "WHERE `%s`=?", config.database.tables.User.TABLE_NAME,
			config.database.tables.columns.User.PASSWORD, config.database.tables.columns.User.USERNAME);

	/** The Constant MySQL_QUERY_SET_PERMISSION. */
	public static final String MySQL_QUERY_SET_PERMISSION = String.format("UPDATE `%s` "
			+ "SET `%s`=? "
			+ "WHERE `%s`=?",
			config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.PREMISSION,
			config.database.tables.columns.User.USERNAME);

	/* ======================== DELETE ============================= */

}
