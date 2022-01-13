package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class VersionRequest.
 * Static class
 * This class consist of column value's of VersionRequest table on db scheme. 
 */
public final class VersionRequest {

	/** The Constant ID. */
	public static final String ID = config.database.tables.columns.Request.ID;
	
	/** The Constant USERNAME_REQUEST. */
	public static final String USERNAME_REQUEST = config.database.tables.columns.Request.USERNAME_REQUEST;
	
	/** The Constant DATE. */
	public static final String DATE = config.database.tables.columns.Request.DATE;
	
	/** The Constant STATUS. */
	public static final String STATUS = config.database.tables.columns.Request.STATUS;
	
	
	
	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.City.SHORTCOUNTRY; //References to city
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.City.NAME; //References to city
	
	/** The Constant COLLECTION_VERSION. */
	public static final String COLLECTION_VERSION = config.database.tables.columns.City.MAPSCOLLECTIONVERSION;
	

	
	/**
	 * Override public contractor to make it static.
	 */
	private VersionRequest() {}
	
}
