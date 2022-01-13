/*
 * this class defines errors for Home actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Home.
 * Static class
 * Consists of variable's for packet.
 */
public final class Home {

	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_LOGIN. */
	public static final String SUB_ACTION_ERROR_GET_LOGIN = "Username / password is incorrect";

	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_REGISTER. */
	public static final String SUB_ACTION_ERROR_ADD_REGISTER = "User already exists";
	
	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_LOGOUT. */
	public static final String SUB_ACTION_ERROR_UPDATE_LOGOUT = "LOGOUT";
	
	/* ======================== DELETE ============================= */
	

	
	
	/**
	 * Override public contractor to make it static.
	 */
	private Home() {}
}
