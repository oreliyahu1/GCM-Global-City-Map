/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the PlaceOfInterestMap entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterestMap.
 */
public class PlaceOfInterestMap extends PlaceOfInterest {

	/** The map. */
	private Map map;
	
	/** The cordinates. */
	private Coordinates cordinates;
	
	/**
	 * Instantiates a new PlaceOfInterestMap.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private PlaceOfInterestMap() {}

	/**
	 * Instantiates a new place of interest map.
	 *
	 * @param poi the poi
	 * @param map the map
	 * @param cordinates the cordinates
	 */
	public PlaceOfInterestMap(PlaceOfInterest poi, Map map, Coordinates cordinates) {
		super(poi.getCity(), poi.getName(), poi.getDescription(), poi.isAccessible());
		if(poi.getCategory() != null)
			this.setCategory(poi.getCategory());
		this.cordinates = cordinates;
		this.map = map;
	}
	
	/**
	 * Instantiates a new place of interest map.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param accessible the accessible
	 * @param map the map
	 * @param cordinates the cordinates
	 */
	public PlaceOfInterestMap(City city, String name, String description, boolean accessible, Map map, Coordinates cordinates) {
		super(city, name, description, accessible);
		this.cordinates = cordinates;
	}

	/**
	 * Instantiates a new place of interest map.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param accessible the accessible
	 * @param category the category
	 * @param map the map
	 * @param cordinates the cordinates
	 */
	public PlaceOfInterestMap(City city, String name, String description, boolean accessible, Category category, Map map, Coordinates cordinates) {
		super(city, name, description, accessible, category);
		this.cordinates = cordinates;
	}


	/**
	 * Gets the cordinates.
	 *
	 * @return the cordinates
	 */
	public Coordinates getCordinates() {
		return cordinates;
	}


	/**
	 * Sets the cordinates.
	 *
	 * @param cordinates the new cordinates
	 */
	public void setCordinates(Coordinates cordinates) {
		this.cordinates = cordinates;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see entities.PlaceOfInterest#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PlaceOfInterestMap) {
			if(super.equals(obj))
					return this.getName().equals(((PlaceOfInterestMap)obj).getName()) && this.getMap().equals(((PlaceOfInterestMap)obj).getMap());
		}
		return false;
	}
	
	/**
	 * Hash map to PlaceOfInterestMap
	 * Get hash map and create PlaceOfInterestMap entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. PlaceOfInterestMap
	 */
	public static PlaceOfInterestMap fromHashMap(HashMap<String, String> hm) {	
		PlaceOfInterest pre = PlaceOfInterest.fromHashMap(hm);
		Map mre = new Map(pre.getCity(), hm.get(config.database.tables.columns.PlaceOfInterestMap.MAP));
		PlaceOfInterestMap re = new PlaceOfInterestMap(pre,mre,
				new Coordinates(Double.parseDouble(hm.get(config.database.tables.columns.PlaceOfInterestMap.LOCATION_X)),
						Double.parseDouble(hm.get(config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y))));	
		return re;
	}

	
	
}
