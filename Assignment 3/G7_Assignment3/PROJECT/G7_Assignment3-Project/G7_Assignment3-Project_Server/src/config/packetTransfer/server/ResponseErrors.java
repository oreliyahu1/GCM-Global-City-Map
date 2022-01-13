package config.packetTransfer.server;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseErrors.
 */
public class ResponseErrors {
	/**
	 * Override public contractor to make it static.
	 */
	private ResponseErrors() {}
	
	/** The Constant SERVER_RESPONSE_FATAL_ERROR. */
	public static final String SERVER_RESPONSE_FATAL_ERROR = "Action not detected!!!";
	
	
	
	
	
	
	/** The Constant SERVER_RESPONSE_ERROR_UNEXPECTED. */
	public static final String SERVER_RESPONSE_ERROR_UNEXPECTED = "Unexpected response";
	
	/** The Constant SERVER_RESPONSE_LOGIN_FAILED. */
	public static final String SERVER_RESPONSE_LOGIN_FAILED = "Username or password incorrect";
	

	
	/** The Constant SERVER_RESPONSE_ERROR_EXISTS. */
	public static final String SERVER_RESPONSE_ERROR_EXISTS = "The item is already exists";
	
	/** The Constant SERVER_RESPONSE_ERROR_UNEXISTS. */
	public static final String SERVER_RESPONSE_ERROR_UNEXISTS = "The item not exists";
	
	/** The Constant SERVER_RESPONSE_ERROR_UPDATE. */
	public static final String SERVER_RESPONSE_ERROR_UPDATE = "Update failed";
	
	/** The Constant SERVER_RESPONSE_TOKEN_FAILED. */
	public static final String SERVER_RESPONSE_TOKEN_FAILED = "Connection has been made from another computer";
}
