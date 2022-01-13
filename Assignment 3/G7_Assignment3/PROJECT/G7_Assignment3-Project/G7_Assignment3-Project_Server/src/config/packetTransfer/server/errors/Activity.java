/*
 * this class defines errors for activity actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Activity.
 * Static class
 * Consists of variable's for packet.
 */
public final class Activity {
	
	
		/* ======================== GET ============================= */
		/** The Constant SUB_ACTION_GET_ALL_ACTIVITIES. */
		public static final String SUB_ACTION_ERROR_GET_ALL_ACTIVITIES = "Requested activity not available";
		
		/* ======================== ADD ============================= */
		/** The Constant SUB_ACTION_ERROR_ADD_ACTIVITY. */
		public static final String SUB_ACTION_ERROR_ADD_ACTIVITY = "Requested activty already added";
		
		/** The Constant SUB_ACTION_ADD_DOWNLOAD. *//*
		public static final String SUB_ACTION_ERROR_ADD_DOWNLOAD = "Add Download Activity error";
									   
		*//** The Constant SUB_ACTION_ADD_VIEW. *//*
		public static final String SUB_ACTION_ERROR_ADD_VIEW = "Add view Activity error";
		*/
		/* ======================== UPDATE ============================= */

		/* ======================== DELETE ============================= */

	
	/**
	 * Override public contractor to make it static.
	 */
	private Activity() {}
	
}



