package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class Tour.
 * Static class
 * This class consist of column value's of Tour table on db scheme. 
 */


public class Tour {

	/** The Constant NAME. */
	public static final String NAME = "tour";
 
	/** The Constant SHORTCOUNTRY. */
	public static final String SHORTCOUNTRY = config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY; //References to PlaceOfInterest
	
	/** The Constant CITY. */
	public static final String CITY = config.database.tables.columns.PlaceOfInterest.CITY; //References to PlaceOfInterest
	
	/** The Constant POI. */
	public static final String POI = config.database.tables.columns.PlaceOfInterest.NAME; //References to PlaceOfInterest
	
	/** The Constant SECTIME. */
	public static final String SECTIME = "secTime";
	
	/** The Constant INDEX. */
	public static final String INDEX = "index";
	
	/** The Constant COLLECTION_VERSION. */
	public static final String COLLECTION_VERSION = config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION;  //References to PlaceOfInterest
	
	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = "descTour";
	
	/** The Constant RECOMMENDED. */
	public static final String RECOMMENDED = "recommended" ;
	
	
	/** The Constant TOTAL_DURATION. */
	//more data for client
	public static final String TOTAL_DURATION = "durs";
	
	/** The Constant TOTAL_POIS. */
	public static final String TOTAL_POIS = config.database.tables.columns.City.TOTAL_POIS;
	
	
	/**
	 * Override public contractor to make it static.
	 */
	private Tour() {}
	
	
	
	
}
