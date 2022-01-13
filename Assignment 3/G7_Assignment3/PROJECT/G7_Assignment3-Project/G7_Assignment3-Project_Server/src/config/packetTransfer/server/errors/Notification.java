/*
 * this class defines errors for Notification actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 * Static class
 * Consists of variable's for packet.
 */
public final class Notification {

	/** The Constant WINDOW_ECITCITY. */
	//public static final String WINDOW = "Notification";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_NEW_NOTIFICATION. */
	public static final String SUB_ACTION_ERROR_GET_NOTIFICATIONS = "recipient doesn't exist";
	
	/** The Constant SUB_ACTION_GET_NEW_NOTIFICATION. */
	//public static final String SUB_ACTION_ERROR_GET_NEW_NOTIFICATIONS = "Get new notifications error";
	
	/** The Constant SUB_ACTION_GET_ALL_NOTIFICATIONS. */
	//public static final String SUB_ACTION_ERROR_GET_ALL_NOTIFICATIONS = "Get all notifications error";

	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_NOTIFICATION. */
	public static final String SUB_ACTION_ERROR_ADD_NOTIFICATION = "recipient doesn't exist";
	
	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_READ_NOTIFICATION. */
	public static final String SUB_ACTION_ERROR_UPDATE_READ_NOTIFICATION = "Action failed";
	
	/* ======================== DELETE ============================= */
	
	/** The Constant SUB_ACTION_DELETE_NOTIFICATION. */
	public static final String SUB_ACTION_ERROR_DELETE_NOTIFICATIONS = "Notification already deleted";
	 
	/** The Constant SUB_ACTION_DELETE_NOTIFICATION. */
	//public static final String SUB_ACTION_ERROR_DELETE_NOTIFICATION = "Delete notification error";
	
	/** The Constant SUB_ACTION_DELETE_ALL_NOTIFICATIONS. */
	//public static final String SUB_ACTION_ERROR_DELETE_ALL_NOTIFICATIONS = "Delete all notifications error";

	
	/**
	 * Override public contractor to make it static.
	 */
	private Notification() {}
}
