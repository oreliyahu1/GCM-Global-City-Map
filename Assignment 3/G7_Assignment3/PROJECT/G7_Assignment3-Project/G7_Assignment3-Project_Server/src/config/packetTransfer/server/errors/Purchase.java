/*
 * this class defines errors for Purchase actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 * Static class
 * Consists of variable's for packet.
 */
public final class Purchase {

	/** The Constant WINDOW_ECITCITY. */
	//public static final String WINDOW = "Purchase";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_ERROR_GET_RATES. */
	public static final String SUB_ACTION_ERROR_GET_RATES = "Action failed";
	
	/** The Constant SUB_ACTION_ERROR_GET_PURCHASES_EXPIRES_IN_3DAYS. */
	public static final String SUB_ACTION_ERROR_GET_PURCHASES_EXPIRES_IN_3DAYS="Action failed";
	
	/** The Constant SUB_ACTION_ERROR_GET_USER_UNEXPIRED_PURCHASES. */
	public static final String SUB_ACTION_ERROR_GET_USER_UNEXPIRED_PURCHASES="Get Unexpired purchases failed";
	
	/** The Constant SUB_ACTION_ERROR_GET_PAYMENT_DETAILS. */
	public static final String  SUB_ACTION_ERROR_GET_PAYMENT_DETAILS= "Cannot get payment method ";
	
	/** The Constant SUB_ACTION_ERROR_GET_LAST_USER_PURCHASE. */
	public static final String  SUB_ACTION_ERROR_GET_LAST_USER_PURCHASE= "Cannot get last user purchase";
	
	
	/** The Constant SUB_ACTION_ERROR_GET_ALL_USER_PURCHASES. */
	public static final String SUB_ACTION_ERROR_GET_ALL_USER_PURCHASES= "Cannot get user purchases ";
	
	
	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_PURCHASE. */
	public static final String SUB_ACTION_ERROR__ADD_PURCHASE = "Add purchase error";
	
	/** The Constant SUB_ACTION_ADD_RENEWAL. */
	public static final String SUB_ACTION_ERROR_ADD_RENEWAL = "Add renewal error";
	
	/* ======================== UPDATE ============================= */

	/* ======================== DELETE ============================= */
	
	/**
	 * Override public contractor to make it static.
	 */
	private Purchase() {}
}
