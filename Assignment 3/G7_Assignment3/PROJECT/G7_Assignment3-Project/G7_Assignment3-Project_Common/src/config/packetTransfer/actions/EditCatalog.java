/*
* this class defines the actions and the name of EditCatalog window
 */
package config.packetTransfer.actions;

// TODO: Auto-generated Javadoc
/**
 * The Class EditCatalog.
 * Static class
 * Consists of variable's for packet.
 */
public final class EditCatalog extends Catalog {

	/** The Constant WINDOW_ECITCITY. */
	public static final String WINDOW = "EDIT_CATALOG";
	
	/** The Constant SUB_WINDOW_EDIT_CITY. */
	public static final String SUB_WINDOW_EDIT_CITY = "EDIT_CATALOG_CITY";
	
	/** The Constant SUB_WINDOW_EDIT_MAP. */
	public static final String SUB_WINDOW_EDIT_MAP = "EDIT_CATALOG_MAP";
	
	/** The Constant SUB_WINDOW_EDIT_POI. */
	public static final String SUB_WINDOW_EDIT_POI = "EDIT_CATALOG_POI";
	
	/** The Constant SUB_WINDOW_EDIT_TOUR. */
	public static final String SUB_WINDOW_EDIT_TOUR = "EDIT_CATALOG_TOUR";
	
	/** The Constant SUB_WINDOW_EDIT_CATEGORIES. */
	public static final String SUB_WINDOW_EDIT_CATEGORIES = "EDIT_CATALOG_CATEGORIES";
	
	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_COLLECTION_VERSION_HISTORY. */
	//public static final String SUB_ACTION_GET_COLLECTION_VERSION_HISTORY = "GET_COLLECTION_VERSION_HISTORY";
	
	/** The Constant SUB_ACTION_GET_LAST_COLLECTION_VERSION. */
	public static final String SUB_ACTION_GET_LAST_COLLECTION_VERSION = "GET_LAST_COLLECTION_VERSION";
	
	/** The Constant SUB_ACTION_GET_ALL_CATEGORIES. */
	public static final String SUB_ACTION_GET_ALL_CATEGORIES = "GET_ALL_CATEGORIES";
	
	/** The Constant SUB_ACTION_GET_ALL_EXTERNAL_MAPS_BY_CITY. */
	public static final String SUB_ACTION_GET_ALL_EXTERNAL_MAPS_BY_CITY = "GET_ALL_EXTERNAL_MAPS_BY_CITY";
	
	/** The Constant SUB_ACTION_GET_EXTERNAL_FULL_MAP. */
	public static final String SUB_ACTION_GET_EXTERNAL_FULL_MAP = "GET_EXTERNAL_FULL_MAP";
	

	/* ======================== ADD ============================= */
	/** The Constant SUB_ACTION_ADD_POIS_OF_MAP. */
	public static final String SUB_ACTION_ADD_POI_TO_MAP = "ADD_POI_TO_MAP";
	
	/** The Constant SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST. */
	public static final String SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST = "ADD_COLLECTION_VERSION_CITY_REQUEST";
	
	/** The Constant SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY. */
	public static final String SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY = "ADD_EXRERNAL_MAP_TO_CITY";
	
	/** The Constant SUB_ACTION_ADD_POI_TO_CITY. */
	public static final String SUB_ACTION_ADD_POI_TO_CITY = "ADD_POI_TO_CITY";
	
	/** The Constant SUB_ACTION_ADD_TOUR_TO_CITY. */
	public static final String SUB_ACTION_ADD_TOUR_TO_CITY = "ADD_TOUR_TO_CITY";
	
	/** The Constant SUB_ACTION_ADD_CITY_TO_COUNTRY. */
	public static final String SUB_ACTION_ADD_CITY_TO_COUNTRY = "ADD_CITY_TO_COUNTRY";
	
	/** The Constant SUB_ACTION_ADD_CATEGORY. */
	public static final String SUB_ACTION_ADD_CATEGORY = "ADD_CATEGORY";
	
	/* ======================== UPDATE ============================= */
	
	/** The Constant SUB_ACTION_UPDATE_MAP_OF_CITY. */
	public static final String SUB_ACTION_UPDATE_MAP_OF_CITY = "UPDATE_MAP_OF_CITY";
	
	/** The Constant SUB_ACTION_UPDATE_CITY_FROM_COUNTRY. */
	public static final String SUB_ACTION_UPDATE_CITY_OF_COUNTRY = "UPDATE_CITY_OF_COUNTRY";
	
	/** The Constant SUB_ACTION_DELETE_POI_FROM_CITY. */
	public static final String SUB_ACTION_UPDATE_POI_OF_CITY = "UPDATE_POI_OF_CITY";
	
	/** The Constant SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS. */
	public static final String SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS = "UPDATE_TOUR_WITHOUT_POIS";
	
	/** The Constant SUB_ACTION_UPDATE_TOUR. */
	public static final String SUB_ACTION_UPDATE_TOUR = "UPDATE_TOUR";
	
	/** The Constant SUB_ACTION_UPDATE_CATEGORY. */
	public static final String SUB_ACTION_UPDATE_CATEGORY = "UPDATE_CATEGORY";

	/* ======================== DELETE ============================= */
	/** The Constant SUB_ACTION_DELETE_MAP_FROM_CITY. */
	public static final String SUB_ACTION_DELETE_MAP_FROM_CITY = "DELETE_MAP_FROM_CITY";
	
	/** The Constant SUB_ACTION_DELETE_POI_TO_MAP. */
	public static final String SUB_ACTION_DELETE_POI_FROM_MAP = "DELETE_POI_FROM_MAP";
	
	/** The Constant SUB_ACTION_DELETE_POI_FROM_CITY. */
	public static final String SUB_ACTION_DELETE_POI_FROM_CITY = "DELETE_POI_FROM_CITY";
	
	/** The Constant SUB_ACTION_DELETE_TOUR_FROM_CITY. */
	public static final String SUB_ACTION_DELETE_TOUR_FROM_CITY = "DELETE_TOUR_FROM_CITY";

	/** The Constant SUB_ACTION_DELETE_CATEGORY. */
	public static final String SUB_ACTION_DELETE_CATEGORY = "DELETE_CATEGORY";
	
	/**
	 * Override public contractor to make it static.
	 */
	private EditCatalog() { super(); }
}
