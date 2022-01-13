/*
 * this class defines errors for catalog actions
 */
package config.packetTransfer.server.errors;

/**
 * The Class Catalog.
 * Static class
 * Consists of variable's for packet.
 */
public class Catalog {


	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_COUNTRIES. */
	public static final String SUB_ACTION_ERROR_GET = "Calculating data failed"; 
	
	/** The Constant SUB_ACTION_GET_COUNTRIES. */
	//public static final String SUB_ACTION_ERROR_GET_COUNTRIES = "Get countries error";
	
	/** The Constant SUB_ACTION_GET_COUNTRY_CITIES_NAMES. */
	//public static final String SUB_ACTION_ERROR_GET_COUNTRY_CITIES_NAMES = "Get all country cities error";
	
	/** The Constant SUB_ACTION_GET_CITY_MAPS. */
	public static final String SUB_ACTION_ERROR_GET_CITY_MAPS = "City doesn't exist ";
	
	/** The Constant SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY. */
	public static final String SUB_ACTION_ERROR_GET_RECOMMENDED_TOURS_OF_CITY = "Calculating data failed, city doesn't exist ";

	/** The Constant SUB_ACTION_GET_TOURS_OF_CITY. */
	public static final String SUB_ACTION_ERROR_GET_TOURS_OF_CITY = "Calculating data failed, city doesn't exist ";

	/** The Constant SUB_ACTION_GET_FULL_MAP_OF_CITY. */
	public static final String SUB_ACTION_ERROR_GET_FULL_MAP_OF_CITY = "Calculating data failed, city doesn't exist ";
	
	/** The Constant SUB_ACTION_GET_POIS_OF_CITY. */
	public static final String SUB_ACTION_ERROR_GET_POIS_OF_CITY = "Calculating data failed, city doesn't exist ";

	/** The Constant SUB_ACTION_GET_POIS_OF_MAP. */
	public static final String SUB_ACTION_ERROR_GET_POIS_OF_MAP = "Calculating data failed, map doesn't exist ";
	
	/** The Constant SUB_ACTION_GET_POIS_OF_TOUR. */
	public static final String SUB_ACTION_ERROR_GET_POIS_OF_TOUR = "Calculating data failed, tour doesn't exist ";
	
	/** The Constant SUB_ACTION_ERROR_GET_PURCHASES_BY_COUNTRY_USER. */
	public static final String SUB_ACTION_ERROR_GET_PURCHASES_BY_COUNTRY_USER = "Calculating data failed, purchases doesn't exist ";
	
	/* ======================== ADD ============================= */

	/* ======================== UPDATE ============================= */

	/* ======================== DELETE ============================= */
	
	/**
	 * Override public contractor to make it static.
	 */
	protected Catalog() {}
}
