package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class City.
 * Static class
 * This class consist of column value's of City table on db scheme. 
 */
public final class City {

	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.Country.SHORT; //References to country
	
	/** The Constant NAME. */
	public static final String NAME = "city";
	
	/** The Constant MAPSCOLLECTIONVERSION. */
	public static final String MAPSCOLLECTIONVERSION = config.database.tables.columns.Map.COLLECTION_VERSION;
	
	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = "description";
	
	/** The Constant TOTAL_MAPS. */
	//more data for client
	public static final String TOTAL_MAPS = config.database.tables.columns.Country.TOTAL_MAPS;
	
	/** The Constant TOTAL_POIS. */
	public static final String TOTAL_POIS = config.database.tables.columns.Country.TOTAL_POIS;
	
	/** The Constant TOTAL_TOURS. */
	public static final String TOTAL_TOURS = config.database.tables.columns.Country.TOTAL_TOURS;
	
	/**
	 * Override public contractor to make it static.
	 */
	private City() {}
	
}
