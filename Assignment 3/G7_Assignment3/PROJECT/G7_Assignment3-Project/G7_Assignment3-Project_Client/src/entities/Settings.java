package entities;
import java.io.IOException;
import entities.AbstractJsonToFile;

/**
 * The Class Settings.
 * Client settings class
 * Extends AbstractSettings
 */
public class Settings extends AbstractJsonToFile{

	/** The port. */
	private int port;
	
	/** The ip. */
	private String ip;
	
	/** The max kb log buffer. */
	private int maxKBLogBuffer;
	
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
	public Settings(int port, String ip, int maxKBLogBuffer) {
		this.port = port;
		this.ip = ip;
		this.maxKBLogBuffer = maxKBLogBuffer;
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
		return super.save("client.config");
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
	 * Open.
	 *
	 * @return true, if successful
	 */
	public boolean open() {
		if(super.open("client.config")) return true;
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
			this.ip = jsonSettings.getIp();
			this.port = jsonSettings.getPort();
			this.maxKBLogBuffer = jsonSettings.getMaxKBLogBuffer();
		} catch (IOException e) {
			System.out.println("Settings error: " + e.toString());
		}
	}

}
