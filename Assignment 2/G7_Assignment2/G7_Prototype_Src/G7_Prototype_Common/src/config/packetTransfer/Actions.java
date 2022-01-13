package config.packetTransfer;

/**
 * The Class Actions.
 * Static class
 * Consists of variable's for packet.
 */
public final class Actions {

	/**
	 * The Enum ACTION_TYPE.
	 */
	public static enum ACTION_TYPE { 
									 /** The update. */
									 UPDATE, 
									 /** The delete. */
									 DELETE, 
									 /** The add. */
									 ADD, 
									 /** The get. */
									 GET, 
									 /** The response success. */
									 RESPONSE_SUCCESS, 
									 /** The response error. */
									 RESPONSE_ERROR
									 };
 
	/**
	 * Override public contractor to make it static.
	 */
	private Actions() {}
	
}
