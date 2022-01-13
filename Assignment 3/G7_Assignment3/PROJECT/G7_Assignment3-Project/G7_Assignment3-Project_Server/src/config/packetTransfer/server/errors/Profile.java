/*
 * this class defines errors for Profile actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Profile.
 * Static class
 * Consists of variable's for packet.
 */
public final class Profile {

	/** The Constant WINDOW_ECITCITY. */
	//public static final String WINDOW = "CATALOG";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_PAYMENT_METHOD. */
	public static final String SUB_ACTION_ERROR_GET_PAYMENT_METHOD= "Owner of credit card doesn't exist as a user in the system";
	
	/** The Constant SUB_ACTION_GET_ACCOUNT_DETAILS. */
	public static final String SUB_ACTION_ERROR_GET_ACCOUNT_DETAILS = "Username doesn't exist";
	
	/** The Constant SUB_ACTION_GET_ALL_PURCHASES. */
	public static final String SUB_ACTION_ERROR_GET_ALL_PURCHASES = "Get all Username doesn't exist error ";
	
	/* ======================== ADD ============================= */

	/* ======================== UPDATE ============================= */
	
	/** The Constant SUB_ACTION_UPDATE_ACCOUNT_DETAILS. */
	public static final String SUB_ACTION_ERROR_UPDATE_ACCOUNT_DETAILS = "User already exists";
	
	/** The Constant SUB_ACTION_UPDATE_ACCOUNT_DETAILS. */
	public static final String SUB_ACTION_ERROR_UPDATE_ACCOUNT_PASSWORD = "password too short";
	
	/** The Constant SUB_ACTION_UPDATE_PAYMENT_METHOD. */
	public static final String SUB_ACTION_ERROR_UPDATE_PAYMENT_METHOD= "Details of credit card are incorrect";
	
	
	
	/* ======================== DELETE ============================= */



	

	

	
	
	/**
	 * Override public contractor to make it static.
	 */
	private Profile() {}
}
