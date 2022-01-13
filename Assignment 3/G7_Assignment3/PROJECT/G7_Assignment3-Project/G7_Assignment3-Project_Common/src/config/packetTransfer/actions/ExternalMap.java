/*
 * this class defines the actions and the name of  window
 */
package config.packetTransfer.actions;

// TODO: Auto-generated Javadoc
/**
 * The Class ExternalMap.
 * Static class
 * Consists of variable's for packet.
 */
public final class ExternalMap extends Catalog {

	/** The Constant WINDOW_ECITCITY. */
	public static final String WINDOW = "EXTERNAL_MAP";
	
	/* ======================== GET ============================= */
	
	/** The Constant SUB_ACTION_GET_ALL_COUNTRIES. */
	public static final String SUB_ACTION_GET_ALL_COUNTRIES = "GET_ALL_COUNTRIES";
	
	/** The Constant SUB_ACTION_GET_ALL_CITIES. */
	public static final String SUB_ACTION_GET_ALL_CITIES = "GET_ALL_CITIES";
	
	/** The Constant SUB_ACTION_GET_ALL_CITY_EXTERNAL_MAPS. */
	public static final String SUB_ACTION_GET_ALL_CITY_EXTERNAL_MAPS = "GET_ALL_CITY_EXTERNAL_MAPS";
	
	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_CITY. */
	public static final String SUB_ACTION_ADD_CITY_TO_COUNTRY = "ADD_CITY";
	
	/** The Constant SUB_ACTION_ADD_EXTERNAL_MAP. */
	public static final String SUB_ACTION_ADD_EXTERNAL_MAP = "ADD_EXTERNAL_MAP";
	
	/* ======================== UPDATE ============================= */
	
	/** The Constant SUB_ACTION_UPDATE_EXTERNAL_MAP. */
	public static final String SUB_ACTION_UPDATE_EXTERNAL_MAP = "UPDATE_EXTERNAL_MAP";
	
	/* ======================== DELETE ============================= */

	/**
	 * Override public contractor to make it static.
	 */
	private ExternalMap() { }
}
