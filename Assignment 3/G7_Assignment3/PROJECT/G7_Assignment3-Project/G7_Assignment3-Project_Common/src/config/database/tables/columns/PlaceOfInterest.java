package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterest.
 * Static class
 * This class consist of column value's of PlaceOfInterest table on db scheme. 
 */
public final class PlaceOfInterest {

	/** The Constant NAME. */
	public static final String NAME = "poi";
	
	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = "descPoi";
	
	/** The Constant ACCESSIBLE. */
	public static final String ACCESSIBLE = "accessible";
	
	
	/** The Constant TYPE. */
	public static final String TYPE = config.database.tables.columns.Category.NAME;//References to category
	
	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.City.SHORTCOUNTRY; //References to city
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.City.NAME; //References to city 
	
	/** The Constant COLLECTION_VERSION. */
	public static final String COLLECTION_VERSION = config.database.tables.columns.City.MAPSCOLLECTIONVERSION;
		
	
	/**
	 * Override public contractor to make it static.
	 */
	private PlaceOfInterest() {}
	
}
