/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the PlaceOfInterestTour entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterestTour.
 */
public class PlaceOfInterestTour extends PlaceOfInterest {

	/** The tour. */
	private Tour tour;
	
	/** The sec time. */
	private int secTime;
	
	/** The index. */
	private int index;
	
	/**
	 * Instantiates a new PlaceOfInterestTour.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private PlaceOfInterestTour() {}

	/**
	 * Instantiates a new place of interest tour.
	 *
	 * @param poi the poi
	 * @param tour the tour
	 * @param secTime the sec time
	 * @param index the index
	 */
	public PlaceOfInterestTour(PlaceOfInterest poi, Tour tour, int secTime, int index) {
		super(poi.getCity(), poi.getName(), poi.getDescription(), poi.isAccessible(), poi.getCategory());
		this.secTime = secTime;
		this.index = index;
	}
	
	/**
	 * Instantiates a new place of interest tour.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param accessible the accessible
	 * @param tour the tour
	 * @param secTime the sec time
	 * @param index the index
	 */
	public PlaceOfInterestTour(City city, String name, String description, boolean accessible, Tour tour, int secTime, int index) {
		super(city, name, description, accessible);
		this.secTime = secTime;
		this.index = index;
	}

	/**
	 * Instantiates a new place of interest tour.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param accessible the accessible
	 * @param category the category
	 * @param tour the tour
	 * @param secTime the sec time
	 * @param index the index
	 */
	public PlaceOfInterestTour(City city, String name, String description, boolean accessible, Category category, Tour tour, int secTime, int index) {
		super(city, name, description, accessible, category);
		this.secTime = secTime;
		this.index = index;
	}

	/**
	 * Gets the sec time.
	 *
	 * @return the sec time
	 */
	public int getSecTime() {
		return secTime;
	}

	/**
	 * Sets the sec time.
	 *
	 * @param secTime the new sec time
	 */
	public void setSecTime(int secTime) {
		this.secTime = secTime;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/* (non-Javadoc)
	 * @see entities.PlaceOfInterest#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PlaceOfInterestTour) {
			if(super.equals(obj))
					return this.getName().equals(((PlaceOfInterestTour)obj).getName()) && this.getIndex() == ((PlaceOfInterestTour)obj).getIndex() && this.getTour().equals(((PlaceOfInterestTour)obj).getTour());
		}
		return false;
	}
	
	/**
	 * Gets the tour.
	 *
	 * @return the tour
	 */
	public Tour getTour() {
		return tour;
	}

	/**
	 * Sets the tour.
	 *
	 * @param tour the new tour
	 */
	public void setTour(Tour tour) {
		this.tour = tour;
	}

	/**
	 * Hash map to PlaceOfInterestTour
	 * Get hash map and create PlaceOfInterestTour entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. PlaceOfInterestTour
	 */
	public static PlaceOfInterestTour fromHashMap(HashMap<String, String> hm) {	
		PlaceOfInterest pre = PlaceOfInterest.fromHashMap(hm);
		Tour tre = new Tour(pre.getCity(), hm.get(config.database.tables.columns.Tour.NAME));
		PlaceOfInterestTour re = new PlaceOfInterestTour(pre,tre,
				Integer.parseInt(hm.get(config.database.tables.columns.Tour.SECTIME)),
						Integer.parseInt(hm.get(config.database.tables.columns.Tour.INDEX)));
		
		return re;
	}

	
	
}
