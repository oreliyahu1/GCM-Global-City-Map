/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the VersionRequest entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class VersionRequest. Static class This class consist of VersionRequest
 * queries table on db scheme.
 */
public final class VersionRequest {
	/**
	 * Override public contractor to make it static.
	 */
	private VersionRequest() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_VERSION_REQUESTS. */
	public static final String MySQL_QUERY_GET_ALL_VERSION_REQUESTS = String.format("SELECT `%s`.`*`,`%s`.`%s` "
			+ "FROM `%s`,`%s` "
			+ "WHERE `%s`.`%s`=`%s`.`%s` "
			+ "ORDER BY `%s`.`%s` ",
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.columns.Country.NAME,
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.VersionRequest.TABLE_NAME,config.database.tables.columns.VersionRequest.SHORTCOUNTRY,
			config.database.tables.Country.TABLE_NAME,config.database.tables.columns.Country.SHORT,
			config.database.tables.VersionRequest.TABLE_NAME,config.database.tables.columns.VersionRequest.DATE);

	/** The Constant MySQL_QUERY_GET_USER_VERSION_REQUEST_RESPONSE. */
	public static final String MySQL_QUERY_GET_USER_VERSION_REQUEST_RESPONSE = String.format("SELECT `%s`.`*`,`%s`.`%s` "
			+ "FROM `%s`,`%s` "
			+ "WHERE `%s`.`%s`=`%s`.`%s` AND `%s`.`%s`=? "
			+ "ORDER BY `%s`.`%s` ",
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.columns.Country.NAME,
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.VersionRequest.TABLE_NAME,config.database.tables.columns.VersionRequest.SHORTCOUNTRY,
			config.database.tables.Country.TABLE_NAME,config.database.tables.columns.Country.SHORT,
			config.database.tables.VersionRequest.TABLE_NAME, config.database.tables.columns.VersionRequest.USERNAME_REQUEST,
			config.database.tables.VersionRequest.TABLE_NAME,config.database.tables.columns.VersionRequest.DATE);

	/** The Constant MySQL_QUERY_GET_NEW_TODAY_VERSION_REQUESTS. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_VERSION_REQUESTS = String.format("SELECT `%s`.`*`,`%s`.`%s` "
			+ "FROM `%s`,`%s` "
			+ "WHERE `%s`.`%s`=`%s`.`%s` AND DATE(`%s`.`%s`)=? ",
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.columns.Country.NAME,
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.Country.TABLE_NAME,
			config.database.tables.VersionRequest.TABLE_NAME,config.database.tables.columns.VersionRequest.SHORTCOUNTRY,
			config.database.tables.Country.TABLE_NAME,config.database.tables.columns.Country.SHORT,
			config.database.tables.VersionRequest.TABLE_NAME, config.database.tables.columns.VersionRequest.DATE);
	
	
	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_VERSION_REQUEST. */
	public static final String MySQL_QUERY_ADD_VERSION_REQUEST = String.format(
			"INSERT INTO `%s`(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) "
			+ "SELECT NOW(),?,?,?,?,'%s' "
			+ "FROM `%s` "
			+ "WHERE (`%s`=? AND `%s`=? AND `%s`<>'%s') "
			+ "HAVING COUNT(*) = 0",
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.columns.VersionRequest.DATE,
			config.database.tables.columns.VersionRequest.SHORTCOUNTRY,
			config.database.tables.columns.VersionRequest.CITY,
			config.database.tables.columns.VersionRequest.COLLECTION_VERSION,
			config.database.tables.columns.VersionRequest.USERNAME_REQUEST,
			config.database.tables.columns.VersionRequest.STATUS,
			entities.AbstractRequest.STATUS.WAITING.toString(),
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.columns.VersionRequest.SHORTCOUNTRY,
			config.database.tables.columns.VersionRequest.CITY,
			config.database.tables.columns.VersionRequest.STATUS,
			entities.AbstractRequest.STATUS.REJECT.toString());

	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_APPROVAL_VERSION_REQUEST. */
	public static final String MySQL_QUERY_UPDATE_APPROVAL_VERSION_REQUEST = String.format("UPDATE `%s` "
			+ "SET `%s`=? "
			+ "WHERE `%s`=?", config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.columns.VersionRequest.STATUS,
			config.database.tables.columns.VersionRequest.ID);
	/* ======================== DELETE ============================= */

	/** The Constant MySQL_QUERY_DELETE_VERSION_REQUEST. */
	public static final String MySQL_QUERY_DELETE_VERSION_REQUEST = String.format("DELETE FROM `%s` "
			+ "WHERE `%s`=? ",
			config.database.tables.VersionRequest.TABLE_NAME,
			config.database.tables.columns.VersionRequest.ID);
	
	/** The Constant MySQL_QUERY_ADD_CREDITCARD. */
	public static final String MySQL_QUERY_ADD_CREDITCARD = String.format(
			"INSERT INTO `%s`(" + "`%s`,`%s`,`%s`,`%s`) values(?,?,?,?)", config.database.tables.CreditCard.TABLE_NAME,
			config.database.tables.columns.CreditCard.OWNER, config.database.tables.columns.CreditCard.CARDNUMBER,
			config.database.tables.columns.CreditCard.CVV, config.database.tables.columns.CreditCard.EXPIRY);


}
