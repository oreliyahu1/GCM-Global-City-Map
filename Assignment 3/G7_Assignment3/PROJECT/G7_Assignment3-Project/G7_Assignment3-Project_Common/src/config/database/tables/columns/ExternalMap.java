package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class ExternalMap.
 * Static class
 * This class consist of column value's of ExternalMap table on db scheme. 
 */
public final class ExternalMap {

	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.City.SHORTCOUNTRY; //References to city
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.City.NAME; //References to city
	
	/** The Constant NAME. */
	public static final String NAME = "map";
	
	/** The Constant IMAGE. */
	public static final String IMAGE = "image";
	
	/** The Constant MAP_VERSION. */
	public static final String MAP_VERSION = "mapVer";
	
	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = "description";
	

	
	/**
	 * Override public contractor to make it static.
	 */
	private ExternalMap() {}
	
}
