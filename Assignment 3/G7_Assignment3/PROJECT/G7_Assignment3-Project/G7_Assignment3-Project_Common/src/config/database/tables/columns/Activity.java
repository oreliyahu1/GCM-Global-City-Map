
package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class Activity.
 * Static class
 * This class consist of column value's of Activity table on db scheme. 
 */
public final class Activity {
	
	/** The Constant ID. */
	public static final String ID = "id";
	
	/** The Constant PURCHASE_ID. */
	public static final String PURCHASE_ID = "purID";
	
	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.Map.SHORTCOUNTRY; //References to map
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.Map.CITY; //References to map
	
	/** The Constant COLLECTION_VERSION. */
	public static final String COLLECTION_VERSION = config.database.tables.columns.Map.COLLECTION_VERSION; //References to map
	
	/** The Constant MAP. */
	public static final String MAP = config.database.tables.columns.Map.NAME; //References to map
	
	/** The Constant USERNAME. */
	public static final String USERNAME = config.database.tables.columns.User.USERNAME;//References to user
	
	/** The Constant DATE. */
	public static final String DATE = "date";
	
	/** The Constant TYPE. */
	public static final String TYPE = "type";
	
	/**
	 * Override public contractor to make it static.
	 */
	private Activity() {}
	
}
