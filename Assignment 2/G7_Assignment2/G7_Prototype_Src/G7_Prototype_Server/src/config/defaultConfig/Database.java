package config.defaultConfig;

/**
 * The Class Database.
 * Static class
 * Default mysql db settings
 */
public final class Database {
	
	/** The Constant DB_DEFAULT_URL. */
	public static final String DB_DEFAULT_URL = "jdbc:mysql://";
	
	/** The Constant DB_DEFAULT_HOSTNAME. */
	public static final String DB_DEFAULT_HOSTNAME = "127.0.0.1";
	
	/** The Constant DB_DEFAULT_PORT. */
	public static final int DB_DEFAULT_PORT = 3306;
	
	/** The Constant DB_DEFAULT_SCHEMA. */
	public static final String DB_DEFAULT_SCHEMA = "GCM";
	
	/** The Constant DB_DEFAULT_USERNAME. */
	public static final String DB_DEFAULT_USERNAME = "root";
	
	/** The Constant DB_DEFAULT_PASSWORD. */
	public static final String DB_DEFAULT_PASSWORD = "Aa123456"; //Aa123456
	
	/** The Constant DB_DEFAULT_SERVERTIMEZONE. */
	public static final String DB_DEFAULT_SERVERTIMEZONE = "IST";
	
	/**
	 * Override public contractor to make it static.
	 */
	private Database() {}
}
