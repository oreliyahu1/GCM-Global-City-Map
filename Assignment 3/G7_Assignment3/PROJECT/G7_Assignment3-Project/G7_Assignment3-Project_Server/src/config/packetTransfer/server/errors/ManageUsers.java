/*
 * this class defines errors for ManageUsers actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageUsers.
 * Static class
 * Consists of variable's for packet.
 */
public final class ManageUsers {

	/** The Constant WINDOW_ManageUsers. */
	//public static final String WINDOW = "ManageUsers";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_ALL_USERS. */
	public static final String SUB_ACTION_ERROR_GET_ALL_USERS = "Action failed";
	
	/** The Constant SUB_ACTION_GET_PAYMENT_METHOD. */
	public static final String SUB_ACTION_ERROR_GET_PAYMENT_METHOD= "Owner of credit card doesn't exist as a user in the system";
	
	/** The Constant SUB_ACTION_GET_ACCOUNT_DETAILS. */
	public static final String SUB_ACTION_ERROR_GET_ACCOUNT_DETAILS = "Username doesn't exist";
	
	/** The Constant SUB_ACTION_ERROR_GET_ALL_USER_PURCHASES. */
	public static final String SUB_ACTION_ERROR_GET_ALL_USER_PURCHASES ="Get all user purchases failed";
	
	/* ======================== ADD ============================= */

	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_ACCOUNT_DETAILS. */
	public static final String SUB_ACTION_ERROR_UPDATE_ACCOUNT_DETAILS = "User already exists";
	
	/** The Constant SUB_ACTION_UPDATE_ACCOUNT_DETAILS. */
	public static final String SUB_ACTION_ERROR_UPDATE_ACCOUNT_PASSWORD = "password too short";
	
	/** The Constant SUB_ACTION_UPDATE_PAYMENT_METHOD. */
	public static final String SUB_ACTION_ERROR_UPDATE_PAYMENT_METHOD= "Details of credit card are incorrect";
	
	/** The Constant SUB_ACTION_UPDATE_PERMISSION. */
	public static final String SUB_ACTION_ERROR_UPDATE_PERMISSION = "Action failed";
	
	/* ======================== DELETE ============================= */



	

	

	
	
	/**
	 * Override public contractor to make it static.
	 */
	private ManageUsers() {}
}
