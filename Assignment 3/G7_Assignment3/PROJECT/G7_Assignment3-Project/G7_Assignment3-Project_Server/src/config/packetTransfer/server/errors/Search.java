/*
 * this class defines errors for Requests actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Search.
 */
public class Search {
	
	/** The Constant WINDOW_RateRequest. */
	//public static final String WINDOW = "Requests";

	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_ERROR_GET_SEARCH_BY_CITY_NAME. */
	public static final String SUB_ACTION_ERROR_GET_SEARCH_BY_CITY_NAME = "Cannot search by city name";
	
	/** The Constant SUB_ACTION_ERROR_GET_SEARCH_BY_CITY_NAME. */
	public static final String SUB_ACTION_ERROR_GET_SEARCH_BY_TEXT_MAP = "Cannot search by map description ";
	
	
	/** The Constant SUB_ACTION_ERROR_GET_SEARCH_BY_TEXT_CITY. */
	public static final String SUB_ACTION_ERROR_GET_SEARCH_BY_TEXT_CITY = "Cannot search by city description ";
	
	
	/** The Constant SUB_ACTION_ERROR_GET_SEARCH_BY_POI. */
	public static final String SUB_ACTION_ERROR_GET_SEARCH_BY_POI = "Cannot search by place of interest ";
	
	
	
	/* ======================== ADD ============================= */
	
	/* ======================== UPDATE ============================= */
	
	/* ======================== DELETE ============================= */
	

	/**
	 * Override public contractor to make it static.
	 */
	private Search() {}

}
