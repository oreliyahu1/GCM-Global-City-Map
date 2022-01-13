package config.packetTransfer.server;

/**
 * The Class ServerActions.
 * Static class
 * Server comments for packet
 */
public final class ServerActions {
	
	/** The Constant SERVER_RESPONSE_ERROR_UNEXPECTED. */
	//response errors
	public static final String SERVER_RESPONSE_ERROR_UNEXPECTED = "Unexpected response";
	
	/** The Constant SERVER_RESPONSE_ERROR_NOACTION. */
	public static final String SERVER_RESPONSE_ERROR_NOACTION = "Action not detected";
	
	/** The Constant SERVER_RESPONSE_ERROR_EXISTS. */
	public static final String SERVER_RESPONSE_ERROR_EXISTS = "The item is already exists";
	
	/** The Constant SERVER_RESPONSE_ERROR_UNEXISTS. */
	public static final String SERVER_RESPONSE_ERROR_UNEXISTS = "The item not exists";
	
	/** The Constant SERVER_RESPONSE_ERROR_UPDATE. */
	public static final String SERVER_RESPONSE_ERROR_UPDATE = "Update failed";
	
	/**
	 * Override public contractor to make it static.
	 */
	private ServerActions() {}
}
