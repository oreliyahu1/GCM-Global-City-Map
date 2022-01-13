package entities;
import java.io.IOException;
import entities.AbstractSettings;

/**
 * The Class Settings.
 * Client settings class
 * Extends AbstractSettings
 */
public class Settings extends AbstractSettings{

	/** The port. */
	private int port;
	
	/** The ip. */
	private String ip;
	
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
	public Settings(int port, String ip) {
		this.port = port;
		this.ip = ip;
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
		return super.save("client");
	}

	/**
	 * Open.
	 *
	 * @return true, if successful
	 */
	public boolean open() {
		if(super.open("client")) return true;
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
		} catch (IOException e) {
			System.out.println("Settings error: " + e.toString());
		}
	}

}
