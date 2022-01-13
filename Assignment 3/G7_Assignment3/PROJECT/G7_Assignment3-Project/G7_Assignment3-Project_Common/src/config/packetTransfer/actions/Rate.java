/*
 * this class defines the actions and the name of Rate window
 */
package config.packetTransfer.actions;

// TODO: Auto-generated Javadoc
/**
 * The Class Rate.
 * Static class
 * Consists of variable's for packet.
 */
public final class Rate {

	/** The Constant WINDOW_Rate. */
	public static final String WINDOW = "RATE";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_RATE. */
	public static final String SUB_ACTION_GET_ALL_RATES = "GET_RATE";
	
	/** The Constant SUB_ACTION_GET_ONETIME_RATES. */
	public static final String SUB_ACTION_GET_ONETIME_RATES = "GET_ONETIME_RATES";
	
	/** The Constant SUB_ACTION_GET_SUBSCRIPTION_RATES. */
	public static final String SUB_ACTION_GET_SUBSCRIPTION_RATES = "GET_SUBSCRIPTION_RATES";
	
	/* ======================== ADD ============================= */
	
	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_RATE. */
	public static final String SUB_ACTION_UPDATE_RATE = "UPDATE_RATE";
	
	/* ======================== DELETE ============================= */
	/** The Constant SUB_ACTION_UPDATE_RATE. */
	public static final String SUB_ACTION_DELETE_RATE = "DELETE_RATE";
	
	/**
	 * Override public contractor to make it static.
	 */
	private Rate() {}
}
