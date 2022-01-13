package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 * Static class
 * This class consist of column value's of Purchase table on db scheme. 
 */
public final class Purchase {

	/** The Constant ID. */
	public static final String ID = "id";
	
	/** The Constant TYPE. */
	public static final String TYPE = config.database.tables.columns.Rate.TYPE; //References to Rate
	
	/** The Constant PRICE. */
	public static final String PRICE = config.database.tables.columns.Rate.PRICE; //References to Rate
	
	/** The Constant NUMBEROFDAYS. */
	public static final String NUMBEROFDAYS = config.database.tables.columns.Rate.NUMBEROFDAYS; //References to Rate
	
	/** The Constant USER. */
	public static final String USER = config.database.tables.columns.User.USERNAME;
	
	/** The Constant DATE. */
	public static final String DATE = "date";
	
	/** The Constant RENEWAL. */
	public static final String RENEWAL = "renewal";
	
	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.City.SHORTCOUNTRY; //References to city
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.City.NAME; //References to city
	
	/** The Constant COLLECTION_VERSION. */
	public static final String COLLECTION_VERSION = config.database.tables.columns.City.MAPSCOLLECTIONVERSION; 
	
	/** The Constant DOWNLOADED. */
	public static final String DOWNLOADED = "downloaded";
	
	/**
	 * Override public contractor to make it static.
	 */
	private Purchase() {}
	
}
