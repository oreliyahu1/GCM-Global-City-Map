/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the CreditCard entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditCard. Static class This class consist of CreditCard queries
 * table on db scheme.
 */
public final class CreditCard {
	/**
	 * Override public contractor to make it static.
	 */
	private CreditCard() {
	}

	/** The Constant MySQL_QUERY_GET_PAYMENT_DETAILS. */
	/* ======================== GET ============================= */
	public static final String MySQL_QUERY_GET_PAYMENT_DETAILS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE `%s`=?",
			config.database.tables.CreditCard.TABLE_NAME,
			config.database.tables.columns.CreditCard.OWNER);

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_CREDITCARD. */
	public static final String MySQL_QUERY_ADD_CREDITCARD = String.format(
			"INSERT INTO `%s`(" + "`%s`,`%s`,`%s`,`%s`) values(?,?,?,?)", config.database.tables.CreditCard.TABLE_NAME,
			config.database.tables.columns.CreditCard.OWNER, config.database.tables.columns.CreditCard.CARDNUMBER,
			config.database.tables.columns.CreditCard.CVV, config.database.tables.columns.CreditCard.EXPIRY);

	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_PAYMENT_METHOD. */
	public static final String MySQL_QUERY_UPDATE_PAYMENT_METHOD = String.format("UPDATE `%s` "
			+ "SET `%s`=?,`%s`=?,`%s`=? "
			+ "WHERE `%s`=?",
			config.database.tables.CreditCard.TABLE_NAME,
			config.database.tables.columns.CreditCard.CARDNUMBER,config.database.tables.columns.CreditCard.CVV, config.database.tables.columns.CreditCard.EXPIRY,
			config.database.tables.columns.CreditCard.OWNER);

	/* ======================== DELETE ============================= */

}
