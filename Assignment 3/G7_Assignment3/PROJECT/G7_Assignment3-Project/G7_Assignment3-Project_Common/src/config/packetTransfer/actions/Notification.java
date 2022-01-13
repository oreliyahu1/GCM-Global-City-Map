/*
 * this class defines the actions and the name of Purchase window
 */
package config.packetTransfer.actions;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 * Static class
 * Consists of variable's for packet.
 */
public final class Notification {

	/** The Constant WINDOW_ECITCITY. */
	public static final String WINDOW = "Notification";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_NEW_NOTIFICATION. */
	public static final String SUB_ACTION_GET_NEW_NOTIFICATIONS = "GET_NEW_NOTIFICATIONS";
	
	/** The Constant SUB_ACTION_GET_ALL_NOTIFICATIONS. */
	public static final String SUB_ACTION_GET_ALL_NOTIFICATIONS = "GET_ALL_NOTIFICATIONS";

	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_NOTIFICATION. */
	public static final String SUB_ACTION_ADD_NOTIFICATION = "ADD_NOTIFICATION";
	
	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_READ_NOTIFICATION. */
	public static final String SUB_ACTION_UPDATE_READ_NOTIFICATION = "UPDATE_READ_NOTIFICATION";
	
	/* ======================== DELETE ============================= */
	/** The Constant SUB_ACTION_DELETE_NOTIFICATION. */
	public static final String SUB_ACTION_DELETE_NOTIFICATION = "DELETE_NOTIFICATION";
	
	/** The Constant SUB_ACTION_DELETE_ALL_NOTIFICATIONS. */
	public static final String SUB_ACTION_DELETE_ALL_NOTIFICATIONS = "DELETE_ALL_NOTIFICATIONS";

	
	/**
	 * Override public contractor to make it static.
	 */
	private Notification() {}
}
