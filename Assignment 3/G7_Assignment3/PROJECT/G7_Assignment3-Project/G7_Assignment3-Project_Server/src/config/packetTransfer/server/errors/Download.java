/*
 * this class defines errors for Download actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Download.
 * Static class
 * Consists of variable's for packet.
 */
public final class Download {

	
	/* ======================== GET ============================= */

	/** The Constant SUB_ACTION_GET_CITY_MAPS. */
	public static final String SUB_ACTION_ERROR_GET_CITY_MAPS ="You can download one time only";
	
	/** The Constant SUB_ACTION_ERROR_GET_FULL_MAP_BY_CITY. */
	public static final String SUB_ACTION_ERROR_GET_FULL_MAP_BY_CITY = "Calculating data failed, map doesn't exist ";
	
	/** The Constant SUB_ACTION_ERROR_GET_POISMAP_BY_MAP. */
	public static final String SUB_ACTION_ERROR_GET_POISMAP_BY_MAP="failed to get place of interest for this map";
	
	/* ======================== ADD ============================= */
	
	/* ======================== UPDATE ============================= */

	/* ======================== DELETE ============================= */
	
	/**
	 * Override public contractor to make it static.
	 */
	private Download() {}
}
