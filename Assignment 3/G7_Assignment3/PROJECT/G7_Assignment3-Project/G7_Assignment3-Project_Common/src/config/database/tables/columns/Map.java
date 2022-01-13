package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 * Static class
 * This class consist of column value's of Map table on db scheme. 
 */
public final class Map {

	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.ExternalMap.SHORTCOUNTRY;
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.ExternalMap.CITY;
	
	/** The Constant NAME. */
	public static final String NAME = config.database.tables.columns.ExternalMap.NAME;
	
	/** The Constant IMAGE. */
	public static final String IMAGE = config.database.tables.columns.ExternalMap.IMAGE;
	
	/** The Constant MAP_VERSION. */
	public static final String MAP_VERSION = config.database.tables.columns.ExternalMap.MAP_VERSION;
	
	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = config.database.tables.columns.ExternalMap.DESCRIPTION;
	
	/** The Constant COLLECTION_VERSION. */
	public static final String COLLECTION_VERSION = "collectionVer";

	
	/** The Constant TOTAL_POIS. */
	//more data for client
	public static final String TOTAL_POIS = config.database.tables.columns.City.TOTAL_POIS;
	/**
	 * Override public contractor to make it static.
	 */
	private Map() {}
	
}
