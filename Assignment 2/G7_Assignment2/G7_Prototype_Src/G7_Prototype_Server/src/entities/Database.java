package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class Database.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Database {

	/** The db URL. */
	private String dbURL;
	
	/** The db hostname. */
	private String dbHostname;
	
	/** The db port. */
	private int dbPort;
	
	/** The db schema. */
	private String dbSchema;
	
	/** The db username. */
	private String dbUsername;
	
	/** The db password. */
	private String dbPassword;
	
	/** The db server timezone. */
	private String dbServerTimezone;
	
	/**
	 * Instantiates a new Database
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Database() {}
	
	/**
	 * Instantiates a new data base.
	 *
	 * @param dbURL the db URL
	 * @param dbHostname the db hostname
	 * @param dbPort the db port
	 * @param dbSchema the db schema
	 * @param dbUsername the db username
	 * @param dbPassword the db password
	 * @param dbServerTimezone the db server timezone
	 */
	public Database(String dbURL, String dbHostname, int dbPort, String dbSchema, String dbUsername, String dbPassword,
			String dbServerTimezone) {
		this.dbURL = dbURL;
		this.dbHostname = dbHostname;
		this.dbPort = dbPort;
		this.dbSchema = dbSchema;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbServerTimezone = dbServerTimezone;
	}
	
	/**
	 * Gets the db URL.
	 *
	 * @return the db URL
	 */
	public String getDbURL() {
		return dbURL;
	}
	
	/**
	 * Sets the db URL.
	 *
	 * @param dbURL the new db URL
	 */
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	
	/**
	 * Gets the db hostname.
	 *
	 * @return the db hostname
	 */
	public String getDbHostname() {
		return dbHostname;
	}
	
	/**
	 * Sets the db hostname.
	 *
	 * @param dbHostname the new db hostname
	 */
	public void setDbHostname(String dbHostname) {
		this.dbHostname = dbHostname;
	}
	
	/**
	 * Gets the db port.
	 *
	 * @return the db port
	 */
	public int getDbPort() {
		return dbPort;
	}
	
	/**
	 * Sets the db port.
	 *
	 * @param dbPort the new db port
	 */
	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}
	
	/**
	 * Gets the db schema.
	 *
	 * @return the db schema
	 */
	public String getDbSchema() {
		return dbSchema;
	}
	
	/**
	 * Sets the db schema.
	 *
	 * @param dbSchema the new db schema
	 */
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}
	
	/**
	 * Gets the db username.
	 *
	 * @return the db username
	 */
	public String getDbUsername() {
		return dbUsername;
	}
	
	/**
	 * Sets the db username.
	 *
	 * @param dbUsername the new db username
	 */
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	
	/**
	 * Gets the db password.
	 *
	 * @return the db password
	 */
	public String getDbPassword() {
		return dbPassword;
	}
	
	/**
	 * Sets the db password.
	 *
	 * @param dbPassword the new db password
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	/**
	 * Gets the db server timezone.
	 *
	 * @return the db server timezone
	 */
	public String getDbServerTimezone() {
		return dbServerTimezone;
	}
	
	/**
	 * Sets the db server timezone.
	 *
	 * @param dbServerTimezone the new db server timezone
	 */
	public void setDbServerTimezone(String dbServerTimezone) {
		this.dbServerTimezone = dbServerTimezone;
	}
	
	/**
	 * Gets the full URL.
	 * Create full url for jdbc
	 * @return the full URL
	 */
	public String getFullURL() {
		return String.format("%s%s:%d/%s?serverTimezone=%s",getDbURL(),getDbHostname(),getDbPort(),getDbSchema(),getDbServerTimezone());
	}
	
	/**
	 * Gets the short URL.
	 * Create short url for jdbc 
	 * @return the short URL
	 */
	public String getShortURL() {
		return String.format("%s%s:%d/?serverTimezone=%s",getDbURL(),getDbHostname(),getDbPort(),getDbServerTimezone());
	}
}
