/*
* this class defines the actions and the name of Purchase window
 */
package config.packetTransfer.actions;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 * Static class
 * Consists of variable's for packet.
 */
public final class Purchase {

	/** The Constant WINDOW_ECITCITY. */
	public static final String WINDOW = "PURCHASE";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_LOGIN. */
	public static final String SUB_ACTION_GET_RATES = "GET_ALL_RATES";
	
	/** The Constant SUB_ACTION_GET_PURCHASES_EXPIRES_IN_3DAYS. */
	public static final String SUB_ACTION_GET_PURCHASES_EXPIRES_IN_3DAYS ="GET_PURCHASES_EXPIRES_IN_3DAYS";
	
	/** The Constant SUB_ACTION_GET_USER_UNEXPIRED_PURCHASES. */
	public static final String SUB_ACTION_GET_USER_UNEXPIRED_PURCHASES="GET_USER_UNEXPIRED_PURCHASES";
	
	/** The Constant SUB_ACTION_GET_PAYMENT_DETAILS. */
	public static final String  SUB_ACTION_GET_PAYMENT_DETAILS = "GET_PAYMENT_METHOD";
	
	/** The Constant SUB_ACTION_GET_ALL_USER_PURCHASES. */
	public static final String  SUB_ACTION_GET_ALL_USER_PURCHASES = "GET_ALL_USER_PURCHASES";
	
	/** The Constant SUB_ACTION_GET_LAST_USER_PURCHASE. */
	public static final String  SUB_ACTION_GET_LAST_USER_PURCHASE = "GET_LAST_USER_PURCHASE";
	
	/** The Constant SUB_ACTION_GET_ALL_USER_CITY_PURCHASES. */
	public static final String  SUB_ACTION_GET_ALL_USER_CITY_PURCHASES="GET_ALL_USER_CITY_PURCHASES";
	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_PURCHASE. */
	public static final String SUB_ACTION_ADD_PURCHASE = "ADD_PURCHASE";
	
	/** The Constant SUB_ACTION_ADD_RENEWAL. */
	public static final String SUB_ACTION_ADD_RENEWAL = "ADD_RENEWAL";
	
	/* ======================== UPDATE ============================= */

	/* ======================== DELETE ============================= */
	
	/**
	 * Override public contractor to make it static.
	 */
	private Purchase() {}
}
