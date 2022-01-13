package entities;
import java.io.IOException;
import entities.AbstractSettings;

/**
 * The Class Settings.
 * Server settings class
 * Extends AbstractSettings
 */
public class Settings extends AbstractSettings{

	/** The port. */
	private int port;
	
	/** The ip. */
	private String ip;
	
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
	 */
	public Settings(int port, String ip, Database db) {
		this.port = port;
		this.ip = ip;
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
	 * Save.
	 *
	 * @return true, if successful
	 */
	public boolean save() {
		return super.save("server");
	}

	/**
	 * Open.
	 *
	 * @return true, if successful
	 */
	public boolean open() {
		if(super.open("server")) return true;
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
			Settings jsonSettings = AbstractSettings.toObject(Settings.class, json);
			this.ip = jsonSettings.getIp();
			this.port = jsonSettings.getPort();
			this.db = jsonSettings.getDb();
		} catch (IOException e) {
			System.out.println("Settings error: " + e.toString());
		}
	}

}
