/*
 * this class defines errors for EditCatalog actions
 */
package config.packetTransfer.server.errors;

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
	public static final String SUB_ACTION_ERROR_GET_LAST_COLLECTION_VERSION = "Get updatable collection version error";
	
	/** The Constant SUB_ACTION_GET_ALL_CATEGORIES. */
	public static final String SUB_ACTION_ERROR_GET_ALL_CATEGORIES = "Get all categories error";
	
	/** The Constant SUB_ACTION_ERROR_GET_ALL_EXTERNAL_MAPS_BY_CITY. */
	public static final String SUB_ACTION_ERROR_GET_ALL_EXTERNAL_MAPS_BY_CITY = "Get all external maps error";
	
	/** The Constant SUB_ACTION_ERROR_GET_EXTERNAL_FULL_MAP. */
	public static final String SUB_ACTION_ERROR_GET_EXTERNAL_FULL_MAP = "Get all external full map error";
	
	/* ======================== ADD ============================= */
	
	/** The Constant SUB_ACTION_ERROR_ADD_ACTION. *//*
	public static final String SUB_ACTION_ERROR_ADD_ACTION = "Add item error";
	*/
	/** The Constant SUB_ACTION_ERROR_ADD_POI. */
	//public static final String SUB_ACTION_ERROR_ADD_POI = "Add poi error";
	
	/** The Constant SUB_ACTION_ERROR_ADD_MAP. */
	//public static final String SUB_ACTION_ERROR_ADD_MAP = "Add map error";
	
	
	
	/** The Constant SUB_ACTION_ADD_POIS_OF_MAP. */
	public static final String SUB_ACTION_ERROR_ADD_POI_TO_MAP = "Poi wasn't added, already exist in city ";
	
	/** The Constant SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST. */
	public static final String SUB_ACTION_ERROR_ADD_COLLECTION_VERSION_CITY_REQUEST = "Collection version is exists requests";
	
	/** The Constant SUB_ACTION_ERROR_ADD_EXRERNAL_MAP_TO_CITY. */
	public static final String SUB_ACTION_ERROR_ADD_EXRERNAL_MAP_TO_CITY = "Map already exists";
	
	/** The Constant SUB_ACTION_ADD_POI_TO_CITY. */
	public static final String SUB_ACTION_ERROR_ADD_POI_TO_CITY = "Poi already exists";
	
	/** The Constant SUB_ACTION_ADD_TOUR_TO_CITY. */
	public static final String SUB_ACTION_ERROR_ADD_TOUR_TO_CITY = "Tour already exists";
	
	/** The Constant SUB_ACTION_ADD_CITY_TO_COUNTRY. */
	public static final String SUB_ACTION_ERROR_ADD_CITY_TO_COUNTRY = "City already exists in the country";
	
	/** The Constant SUB_ACTION_ADD_CATEGORY. */
	public static final String SUB_ACTION_ERROR_ADD_CATEGORY = "Category already exists";
	
	/* ======================== UPDATE ============================= */
	
	/** The Constant SUB_ACTION_ERROR_UPDATE_ACTION. */
	//public static final String SUB_ACTION_ERROR_UPDATE_ACTION = "Update item error";
	
	/** The Constant SUB_ACTION_UPDATE_MAP_OF_CITY. */
	public static final String SUB_ACTION_ERROR_UPDATE_MAP_OF_CITY = "Updating item of map error";
	
	/** The Constant SUB_ACTION_UPDATE_CITY_FROM_COUNTRY. */
	public static final String SUB_ACTION_ERROR_UPDATE_CITY_OF_COUNTRY = "City already exist";
	
	/** The Constant SUB_ACTION_DELETE_POI_FROM_CITY. */
	public static final String SUB_ACTION_ERROR_UPDATE_POI_OF_CITY = "Place of interest already exists";
	
	/** The Constant SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS. */
	public static final String SUB_ACTION_ERROR_UPDATE_TOUR_WITHOUT_POIS = "Tour name already exists";
	
	/** The Constant SUB_ACTION_UPDATE_TOUR. */
	public static final String SUB_ACTION_ERROR_UPDATE_TOUR = "Map already exists";
	
	/** The Constant SUB_ACTION_UPDATE_CATEGORY. */
	public static final String SUB_ACTION_ERROR_UPDATE_CATEGORY = "Category already exists";

	/* ======================== DELETE ============================= */
	
	/** The Constant SUB_ACTION_ERROR_DELETE_ACTION. */
	//public static final String SUB_ACTION_DELETE_ACTION_ERROR = "Delete item error";
	
	
	/** The Constant SUB_ACTION_DELETE_FROM_CITY. */
	public static final String SUB_ACTION_ERROR_DELETE_FROM_CITY = "item already deleted from city ";
	
	/** The Constant SUB_ACTION_DELETE_FROM_MAP. */
	public static final String SUB_ACTION_ERROR_DELETE_FROM_MAP = "item already deleted from map ";
	
	/** The Constant SUB_ACTION_DELETE_CATEGORY. */
	public static final String SUB_ACTION_ERROR_DELETE_CATEGORY = "Category already deleted";


	
	/**
	 * Override public contractor to make it static.
	 */
	private EditCatalog() { super(); }
}
