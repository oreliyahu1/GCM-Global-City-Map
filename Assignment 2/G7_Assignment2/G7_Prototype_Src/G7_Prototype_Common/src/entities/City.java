package entities;

import java.util.HashMap;

/**
 * The Class City.
 */
public class City extends AbstractJsonToString {
	
	/** The name. */
	private String name;
	
	/** The number of maps. */
	private int numberOfMaps;
	
	/** The number of POI. */
	private int numberOfPOI;
	
	/** The number of tours. */
	private int numberOfTours;
	
	/** The ver. */
	private Version ver;
	
	/**
	 * Instantiates a new city.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private City() {}
	
	/**
	 * Instantiates a new city.
	 *
	 * @param name the name
	 * @param numberOfMaps the number of maps
	 * @param numberOfPOI the number of POI
	 * @param numberOfTours the number of tours
	 */
	public City(String name, int numberOfMaps, int numberOfPOI, int numberOfTours) {
		this.name = name;
		this.numberOfMaps = numberOfMaps;
		this.numberOfPOI = numberOfPOI;
		this.numberOfTours = numberOfTours;
		this.ver=new Version(0,0);
	}
	
	/**
	 * Instantiates a new city.
	 *
	 * @param name the name
	 * @param numberOfMaps the number of maps
	 * @param numberOfPOI the number of POI
	 * @param numberOfTours the number of tours
	 * @param ver the ver
	 */
	public City(String name, int numberOfMaps, int numberOfPOI, int numberOfTours, Version ver) {
		this.name = name;
		this.numberOfMaps = numberOfMaps;
		this.numberOfPOI = numberOfPOI;
		this.numberOfTours = numberOfTours;
		this.ver=ver;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the number of maps.
	 *
	 * @return the number of maps
	 */
	public int getNumberOfMaps() {
		return numberOfMaps;
	}
	
	/**
	 * Sets the number of maps.
	 *
	 * @param numberOfMaps the new number of maps
	 */
	public void setNumberOfMaps(int numberOfMaps) {
		this.numberOfMaps = numberOfMaps;
	}
	
	/**
	 * Gets the number of POI.
	 *
	 * @return the number of POI
	 */
	public int getNumberOfPOI() {
		return numberOfPOI;
	}
	
	/**
	 * Sets the number of POI.
	 *
	 * @param numberOfPOI the new number of POI
	 */
	public void setNumberOfPOI(int numberOfPOI) {
		this.numberOfPOI = numberOfPOI;
	}
	
	/**
	 * Gets the number of tours.
	 *
	 * @return the number of tours
	 */
	public int getNumberOfTours() {
		return numberOfTours;
	}
	
	/**
	 * Sets the number of tours.
	 *
	 * @param numberOfTours the new number of tours
	 */
	public void setNumberOfTours(int numberOfTours) {
		this.numberOfTours = numberOfTours;
	}
	
	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Version getVersion() {
		return ver;
	}
	
	/**
	 * Sets the version.
	 *
	 * @param ver the new version
	 */
	public void setVersion(Version ver) {
		this.ver = ver;
	}
	
	/**
	 * Hash map to city.
	 * Get hash map and create city entitle
	 * @param hm the hash map
	 * @return the entities. city
	 */
	public static City fromHashMap(HashMap<String, String> hm) {
		return new entities.City(hm.get(config.database.tables.columns.City.NAME),
				Integer.parseInt(hm.get(config.database.tables.columns.City.NO_MAPS)),
						Integer.parseInt(hm.get(config.database.tables.columns.City.NO_POI)),
								Integer.parseInt(hm.get(config.database.tables.columns.City.NO_TOURS)),
										Version.fromString(hm.get(config.database.tables.columns.City.VERSION)));
	}
	
}
