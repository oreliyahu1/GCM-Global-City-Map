/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Purchase entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase. Static class This class consist of Purchase queries table
 * on db scheme.
 */
public final class Purchase {
	/**
	 * Override public contractor to make it static.
	 */
	private Purchase() {
	}

	/* ======================== GET ============================= */

	/** The Constant MYSQL_QUERY_GET_ALL_USER_PURCHASE. */
	public static final String MYSQL_QUERY_GET_ALL_USER_PURCHASE =String.format("SELECT `*` " 
		       +"FROM `%s`  " 
			   + "WHERE `%s`=? "
			   + "ORDER BY `%s` DESC ",
			   config.database.tables.Purchase.TABLE_NAME,
			   config.database.tables.columns.Purchase.USER,
			   config.database.tables.columns.Purchase.ID);
	
	
	//-----------

	/** The Constant MySQL_QUERY_GET_PURCHASES_SUBSCRIPTION_EXPIRES_IN_X_DAYS. */
	public static final String MySQL_QUERY_GET_PURCHASES_SUBSCRIPTION_EXPIRES_IN_X_DAYS = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE (ADDDATE(DATE(`%s`),INTERVAL `%s` DAY)=ADDDATE(DATE(NOW()),INTERVAL %s DAY)) "
			+ "AND `%s`='%s'",
			config.database.tables.Purchase.TABLE_NAME,
			config.database.tables.columns.Purchase.DATE,config.database.tables.columns.Purchase.NUMBEROFDAYS,
			config.GCM.SERVER_SUBSCRIPTION_REMINDER_DAYS,
			config.database.tables.columns.Purchase.TYPE, entities.Rate.type.SUBSCRIPTION.toString()
	);
	
	/** The Constant MySQL_QUERY_GET_PURCHASES_BY_COUNTRY. */
	public static final String MySQL_QUERY_GET_PURCHASES_BY_COUNTRY = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE (ADDDATE(DATE(`%s`),INTERVAL `%s` DAY))>=NOW() AND `%s`='%s' "
			+ "AND `%s`=? AND `%s`=? ",
			config.database.tables.Purchase.TABLE_NAME,
			config.database.tables.columns.Purchase.DATE,config.database.tables.columns.Purchase.NUMBEROFDAYS,
			config.database.tables.columns.Purchase.TYPE, entities.Rate.type.SUBSCRIPTION.toString(),
			config.database.tables.columns.Purchase.SHORTCOUNTRY, config.database.tables.columns.Purchase.USER
	);

	/** The Constant MYSQL_QUERY_GET_UNEXPIRED_PURCHASES. */
	public static final String MYSQL_QUERY_GET_UNEXPIRED_PURCHASES =String.format("SELECT `*` " 
		       +"FROM `%s`  " 
			   + "WHERE (ADDDATE(DATE(`%s`),INTERVAL `%s` DAY)>=NOW()) AND `%s`='%s' AND "
			   + "`%s`=? AND `%s`=? " ,
			   config.database.tables.Purchase.TABLE_NAME,
			   config.database.tables.columns.Purchase.DATE,
			   config.database.tables.columns.Purchase.NUMBEROFDAYS,
			   config.database.tables.columns.Purchase.TYPE, entities.Rate.type.SUBSCRIPTION.toString(),
			   config.database.tables.columns.Purchase.CITY,
			   config.database.tables.columns.Purchase.SHORTCOUNTRY);
	
	//-----------------
	
	/** The Constant MYSQL_QUERY_GET_USER_UNEXPIRED_PURCHASES. */
	public static final String MYSQL_QUERY_GET_USER_UNEXPIRED_PURCHASES =String.format("SELECT `*` " 
		       +"FROM `%s`  " 
			   + "WHERE `%s`='%s' AND `%s`=? AND "
			   + "`%s`=? AND `%s`=? AND (ADDDATE(DATE(`%s`),INTERVAL `%s` DAY)>=NOW()) " ,
			   config.database.tables.Purchase.TABLE_NAME,
			   config.database.tables.columns.Purchase.TYPE, entities.Rate.type.SUBSCRIPTION.toString(),
			   config.database.tables.columns.Purchase.CITY,
			   config.database.tables.columns.Purchase.SHORTCOUNTRY,
			   config.database.tables.columns.Purchase.USER,
			   config.database.tables.columns.Purchase.DATE,
			   config.database.tables.columns.Purchase.NUMBEROFDAYS);
	
	
	
	//---------
	
	/** The Constant MYSQL_QUERY_GET_ALL_USER_CITY_PURCHASES. */
	public static final String MYSQL_QUERY_GET_ALL_USER_CITY_PURCHASES =  String.format("SELECT `*` "
			+ "FROM `%s`  "
			+ "WHERE `%s`=?  AND `%s`=? AND `%s`=? "
			+ "ORDER BY `%s` DESC",
			config.database.tables.Purchase.TABLE_NAME,
			config.database.tables.columns.Purchase.USER,
			config.database.tables.columns.Purchase.CITY,
			config.database.tables.columns.Purchase.SHORTCOUNTRY,
			config.database.tables.columns.Purchase.ID
			);
			
	

	
	
	//--------
	
	
	/** The Constant MySQL_QUERY_GET_NEW_TODAY_PURCHASES. */
	public static final String MySQL_QUERY_GET_NEW_TODAY_PURCHASES = String.format("SELECT `*` "
			+ "FROM `%s` "
			+ "WHERE DATE(`%s`)=? ",
			config.database.tables.Purchase.TABLE_NAME,
			config.database.tables.columns.Purchase.DATE);
	
	/** The Constant MYSQL_QUERY_GET_USER_LAST_PURCHASE. */
	public static final String MYSQL_QUERY_GET_USER_LAST_PURCHASE =String.format("SELECT `*` " 
		       +"FROM `%s`  " 
			   + "WHERE `%s`=? "
			   + "ORDER BY `%s` DESC "
			   + "LIMIT 1" ,
			   config.database.tables.Purchase.TABLE_NAME,
			   config.database.tables.columns.Purchase.USER,
			   config.database.tables.columns.Purchase.ID);
	

	/* ======================== ADD ============================= */

	/** The Constant MySQL_QUERY_ADD_PURCHASE. */
	public static final String MySQL_QUERY_ADD_PURCHASE = String.format(
			"INSERT INTO `%s` (`%s`, `%s`, `%s`,`%s`, `%s`, `%s`,`%s`, `%s`, `%s`) " + "values(NOW(),?,?,?,?,?,?,?,?)",
			config.database.tables.Purchase.TABLE_NAME, config.database.tables.columns.Purchase.DATE,
			config.database.tables.columns.Purchase.RENEWAL, config.database.tables.columns.Purchase.CITY,
			config.database.tables.columns.Purchase.PRICE, config.database.tables.columns.Purchase.NUMBEROFDAYS,
			config.database.tables.columns.Purchase.SHORTCOUNTRY,
			config.database.tables.columns.Purchase.COLLECTION_VERSION, config.database.tables.columns.Purchase.TYPE,
			config.database.tables.columns.Purchase.USER);
	/* ======================== UPDATE ============================= */

	/** The Constant MySQL_QUERY_UPDATE_PURCHASE_DOWNLOAD. */
	public static final String MySQL_QUERY_UPDATE_PURCHASE_DOWNLOAD = String.format("UPDATE `%s` "
	+ "SET `%s`=1 "
	+ "WHERE `%s`=?" ,
	config.database.tables.Purchase.TABLE_NAME,
	config.database.tables.columns.Purchase.DOWNLOADED, config.database.tables.columns.Purchase.ID);
	
	/* ======================== DELETE ============================= */

}
