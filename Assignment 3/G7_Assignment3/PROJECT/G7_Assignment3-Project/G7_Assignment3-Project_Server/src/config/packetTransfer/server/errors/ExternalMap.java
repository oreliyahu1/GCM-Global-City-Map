/*
 * this class defines errors for ExternalMap actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class ExternalMap.
 * Static class
 * Consists of variable's for packet.
 */
public class ExternalMap {



	/* ======================== GET ============================= */
	
	/** The Constant SUB_ACTION_GET_ALL_COUNTRIES. */
	public static final String SUB_ACTION_ERROR_GET_ALL_COUNTRIES = "cannot get countries";
	
	/** The Constant SUB_ACTION_GET_ALL_CITIES. */
	public static final String SUB_ACTION_ERROR_GET_ALL_CITIES = "cannot get cities";
	
	/** The Constant SUB_ACTION_GET_ALL_CITY_EXTERNAL_MAPS. */
	public static final String SUB_ACTION_ERROR_GET_ALL_CITY_EXTERNAL_MAPS = "cannot get maps";
	
	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_CITY. */
	public static final String SUB_ACTION_ERROR_ADD_CITY_TO_COUNTRY = "cannot add city to selected country";
	
	/** The Constant SUB_ACTION_ADD_EXTERNAL_MAP. */
	public static final String SUB_ACTION_ERROR_ADD_EXTERNAL_MAP = "cannot add map to selected city";
	
	/* ======================== UPDATE ============================= */
	
	/** The Constant SUB_ACTION_UPDATE_EXTERNAL_MAP. */
	public static final String SUB_ACTION_ERROR_UPDATE_EXTERNAL_MAP = "cannot update the map";
	
	/* ======================== DELETE ============================= */
	
	/**
	 * Override public contractor to make it static.
	 */
	protected ExternalMap() {}
}
