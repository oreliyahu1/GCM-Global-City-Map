package entities;
import java.io.IOException;
import entities.AbstractJsonToFile;

// TODO: Auto-generated Javadoc
/**
 * The Class Settings.
 * Server settings class
 * Extends AbstractSettings
 */
public class Settings extends AbstractJsonToFile{

	/** The port. */
	private int port;
	
	/** The ip. */
	private String ip;
	
	/** The max kb log buffer. */
	private int maxKBLogBuffer;
	
	/** The db. */
	private Database db;
	
	/**
	 * Instantiates a new Settings
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Settings() {}
	
	/**
	 * Instantiates a new settings.
	 *
	 * @param port the port
	 * @param ip the ip
	 * @param maxKBLogBuffer the max KB log buffer
	 * @param db the db
	 */
	public Settings(int port, String ip, int maxKBLogBuffer, Database db) {
		this.port = port;
		this.ip = ip;
		this.maxKBLogBuffer=maxKBLogBuffer;
		this.db = db;
	}

	/**
	 * Gets the db.
	 *
	 * @return the db
	 */
	public Database getDb() {
		return db;
	}

	/**
	 * Sets the db.
	 *
	 * @param db the new db
	 */
	public void setDb(Database db) {
		this.db = db;
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	/**
	 * Gets the max KB log buffer.
	 *
	 * @return the max KB log buffer
	 */
	public int getMaxKBLogBuffer() {
		return maxKBLogBuffer;
	}

	/**
	 * Sets the max KB log buffer.
	 *
	 * @param maxKBLogBuffer the new max KB log buffer
	 */
	public void setMaxKBLogBuffer(int maxKBLogBuffer) {
		this.maxKBLogBuffer = maxKBLogBuffer;
	}

	/**
	 * Save.
	 *
	 * @return true, if successful
	 */
	public boolean save() {
		return super.save("server.config");
	}

	/**
	 * Open.
	 *
	 * @return true, if successful
	 */
	public boolean open() {
		if(super.open("server.config")) return true;
		System.out.println("Settings: Default settings loaded");
		return false;
	}

	/* (non-Javadoc)
	 * @see entities.AbstractSettings#update(java.lang.String)
	 * update the loaded file to class parameters
	 */
	@Override
	protected void update(String json) {
		try {
			Settings jsonSettings = AbstractJsonToFile.toObject(Settings.class, json);
			//this.ip = jsonSettings.getIp(); //its can make errors
			this.port = jsonSettings.getPort();
			this.db = jsonSettings.getDb();
			this.maxKBLogBuffer = jsonSettings.getMaxKBLogBuffer();
		} catch (IOException e) {
			System.out.println("Settings error: " + e.toString());
		}
	}

}
