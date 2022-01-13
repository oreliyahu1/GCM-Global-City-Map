/*
 * this class is part of queries package.
 * it defines all the queries that can be done with the Category entity 
 */
package config.database.queries;

// TODO: Auto-generated Javadoc
/**
 * The Class Category. Static class This class consist of Category queries table
 * on db scheme.
 */
public final class Category {
	/**
	 * Override public contractor to make it static.
	 */
	private Category() {
	}

	/* ======================== GET ============================= */

	/** The Constant MySQL_QUERY_GET_ALL_CATEGORIES. */
	public static final String MySQL_QUERY_GET_ALL_CATEGORIES = String.format("SELECT `*` FROM `%s` ",
			config.database.tables.Category.TABLE_NAME);
	
	/** The Constant MySQL_QUERY_ADD_CATEGORY. */
	/* ======================== ADD ============================= */
	public static final String MySQL_QUERY_ADD_CATEGORY = String.format("INSERT INTO `%s`(`%s`) values(?)",
			config.database.tables.Category.TABLE_NAME, config.database.tables.columns.Category.NAME);
	/* ======================== UPDATE ============================= */
	
	/** The Constant MySQL_QUERY_UPDATE_CATEGORY. */
	public static final String MySQL_QUERY_UPDATE_CATEGORY = String.format("UPDATE `%s`  " 
			+ "SET `%s`=? " 
			+ "WHERE `%s`=? " ,
			config.database.tables.Category.TABLE_NAME, config.database.tables.columns.Category.NAME,
			config.database.tables.columns.Category.NAME);

	/* ======================== DELETE ============================= */
	
	/** The Constant MySQL_QUERY_DELETE_CATEGORY. */
	public static final String MySQL_QUERY_DELETE_CATEGORY = String.format("DELETE FROM `%s` WHERE `%s`= ? ",
			config.database.tables.Category.TABLE_NAME, config.database.tables.columns.Category.NAME);
}
