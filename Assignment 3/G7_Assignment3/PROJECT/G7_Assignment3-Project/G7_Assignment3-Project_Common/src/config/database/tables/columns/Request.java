package config.database.tables.columns;

// TODO: Auto-generated Javadoc
//this is not DB table!!!!!!!!!!

/**
 * The Class Request.
 * Static class
 * This class consist of column value's of Request table on db scheme. 
 */
public final class Request {
	
	/** The Constant ID. */
	public static final String ID = "id";
	
	/** The Constant USERNAME_REQUEST. */
	public static final String USERNAME_REQUEST = config.database.tables.columns.User.USERNAME;//References to user
	
	/** The Constant DATE. */
	public static final String DATE = "date";
	
	/** The Constant STATUS. */
	public static final String STATUS = "status";
	
	/**
	 * Override public contractor to make it static.
	 */
	private Request() {}
	
}
