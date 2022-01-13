/*
 * this class defines errors for Rate actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Rate.
 * Static class
 * Consists of variable's for packet.
 */
public final class Rate {

	/** The Constant WINDOW_RateRequest. */
	//public static final String WINDOW = "Rate";
	
	/* ======================== GET ============================= */
	
	/** The Constant SUB_ACTION_GET_RATE. */
	public static final String SUB_ACTION_ERROR_GET_RATES = "Action failed"; 
	
	/** The Constant SUB_ACTION_GET_RATE. */
	//public static final String SUB_ACTION_ERROR_GET_ALL_RATES = "GET_RATE";
	
	/** The Constant SUB_ACTION_GET_ONETIME_RATES. */
	//public static final String SUB_ACTION_ERROR_GET_ONETIME_RATES = "GET_ONETIME_RATES";
	
	/** The Constant SUB_ACTION_GET_SUBSCRIPTION_RATES. */
	//public static final String SUB_ACTION_ERROR_GET_SUBSCRIPTION_RATES = "GET_SUBSCRIPTION_RATES";
	
	/* ======================== ADD ============================= */
	
	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_RATE. */
	public static final String SUB_ACTION_ERROR_UPDATE_RATE = "Rate already exist";
	
	/* ======================== DELETE ============================= */
	/** The Constant SUB_ACTION_UPDATE_RATE. */
	public static final String SUB_ACTION_ERROR_DELETE_RATE = "rate already deleted";
	
	/**
	 * Override public contractor to make it static.
	 */
	private Rate() {}
}
