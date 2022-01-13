/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Tour entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class Tour.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Tour extends AbstractJsonToString {
	
	/** The city. */
	private City city;
	
	/** The name. */
	private String name;
	
	/** The recommended. */
	private boolean recommended;
	
	/** The description. */
	private String description;
	
	/** The number of pois. */
	//no include in DB
	private int numberOfPois;
	
	/** The number of tour duration. */
	private long numberOfTourDuration;
	
	
	/**
	 * Instantiates a new country.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Tour() {}

	

	/**
	 * Instantiates a new tour.
	 *
	 * @param city the city
	 * @param name the name
	 * @param recommended the recommended
	 * @param description the description
	 * @param numberOfPois the number of pois
	 * @param numberOfTourDuration the number of tour duration
	 */
	public Tour(City city, String name, boolean recommended,
			String description, int numberOfPois, long numberOfTourDuration) {
		this.city = city;
		this.name = name;
		this.recommended = recommended;
		this.description = description;
		this.numberOfPois = numberOfPois;
		this.numberOfTourDuration = numberOfTourDuration;
	}

	/**
	 * Instantiates a new tour.
	 *
	 * @param city the city
	 * @param name the name
	 * @param recommended the recommended
	 * @param description the description
	 */
	public Tour(City city, String name, boolean recommended, String description) {
		this.city = city;
		this.name = name;
		this.recommended = recommended;
		this.description = description;
	}

	/**
	 * Instantiates a new tour.
	 *
	 * @param city the city
	 * @param name the name
	 */
	public Tour(City city, String name) {
		this.city = city;
		this.name = name;
	}


	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public City getCity() {
		return city;
	}



	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(City city) {
		this.city = city;
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
	 * Checks if is recommended.
	 *
	 * @return true, if is recommended
	 */
	public boolean isRecommended() {
		return recommended;
	}



	/**
	 * Sets the recommended.
	 *
	 * @param recommended the new recommended
	 */
	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}


	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * Gets the number of pois.
	 *
	 * @return the number of pois
	 */
	public int getNumberOfPois() {
		return numberOfPois;
	}



	/**
	 * Sets the number of pois.
	 *
	 * @param numberOfPois the new number of pois
	 */
	public void setNumberOfPois(int numberOfPois) {
		this.numberOfPois = numberOfPois;
	}



	/**
	 * Gets the number of tour duration.
	 *
	 * @return the number of tour duration
	 */
	public long getNumberOfTourDuration() {
		return numberOfTourDuration;
	}



	/**
	 * Sets the number of tour duration.
	 *
	 * @param numberOfTourDuration the new number of tour duration
	 */
	public void setNumberOfTourDuration(long numberOfTourDuration) {
		this.numberOfTourDuration = numberOfTourDuration;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Tour) {
			if(this.getCity().equals(((Tour)obj).getCity()))
					return this.getName().equals(((Tour)obj).getName());
		}
		return false;
	}
	
	/**
	 * Hash map to Tour
	 * Get hash map and create Tour entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. tour
	 */
	public static Tour fromHashMap(HashMap<String, String> hm) {
		Country cre = new Country(hm.get(config.database.tables.columns.Tour.SHORTCOUNTRY));
		City cire = new City(cre, hm.get(config.database.tables.columns.Tour.CITY), Version.fromString(hm.get(config.database.tables.columns.Tour.COLLECTION_VERSION)));
		Tour re = new Tour(cire,
				hm.get(config.database.tables.columns.Tour.NAME),
				(Integer.parseInt(hm.get(config.database.tables.columns.Tour.RECOMMENDED))==1),
				hm.get(config.database.tables.columns.Tour.DESCRIPTION));
				
		if(hm.get(config.database.tables.columns.Tour.TOTAL_POIS) != null)
			re.setNumberOfPois(Integer.parseInt(hm.get(config.database.tables.columns.Tour.TOTAL_POIS)));
		
		if(hm.get(config.database.tables.columns.Tour.TOTAL_DURATION) != null)
			re.setNumberOfTourDuration(Integer.parseInt(hm.get(config.database.tables.columns.Tour.TOTAL_DURATION)));
		
		return re;
	}

	
}
