/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Country entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// TODO: Auto-generated Javadoc
/**
 * The Class Country.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Country extends AbstractJsonToString {
	
	/** The name. */
	private String name;
	
	/** The short name. */
	private String shortName;
	
	/** The capital. */
	private String capital;
	
	/** The population. */
	private long population;
	
	/** The area. */
	private int area;
	
	
	/** The number of cities. */
	//no include in DB
	private int numberOfCities;
	
	/** The number of maps. */
	private int numberOfMaps;
	
	/** The number of pois. */
	private int numberOfPois;
	
	/** The number of tours. */
	private int numberOfTours;
	
	/**
	 * Instantiates a new country.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Country() {}
	
	/**
	 * Instantiates a new country.
	 *
	 * @param shortName the short name
	 */
	public Country(String shortName) {
		this.shortName = shortName;
	}
	
	/**
	 * Instantiates a new country.
	 *
	 * @param name the name
	 * @param shortName the short name
	 * @param capital the capital
	 * @param population the population
	 * @param area the area
	 * @param numberOfCities the number of cities
	 * @param numberOfMaps the number of maps
	 * @param numberOfPois the number of pois
	 * @param numberOfTours the number of tours
	 */
	public Country(String name, String shortName, String capital, long population, int area, int numberOfCities, int numberOfMaps, int numberOfPois, int numberOfTours) {
		this.name = name;
		this.shortName = shortName;
		this.capital = capital;
		this.population = population;
		this.area = area;
		this.numberOfCities = numberOfCities;
		this.numberOfMaps = numberOfMaps;
		this.numberOfPois = numberOfPois;
		this.numberOfTours = numberOfTours;
	}

	/**
	 * Instantiates a new country.
	 *
	 * @param name the name
	 * @param shortName the short name
	 * @param capital the capital
	 * @param population the population
	 * @param area the area
	 */
	public Country(String name, String shortName, String capital, long population, int area) {
		this.name = name;
		this.shortName = shortName;
		this.capital = capital;
		this.population = population;
		this.area = area;
	}

	/**
	 * Gets the number of cities.
	 *
	 * @return the number of cities
	 */
	public int getNumberOfCities() {
		return numberOfCities;
	}


	/**
	 * Sets the number of cities.
	 *
	 * @param numberOfCities the new number of cities
	 */
	public void setNumberOfCities(int numberOfCities) {
		this.numberOfCities = numberOfCities;
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
	 * Gets the short name.
	 *
	 * @return the short name
	 */
	public String getShortName() {
		return shortName;
	}



	/**
	 * Sets the short name.
	 *
	 * @param shortName the new short name
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}



	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}



	/**
	 * Sets the capital.
	 *
	 * @param capital the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}



	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public long getPopulation() {
		return population;
	}



	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(long population) {
		this.population = population;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public int getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(int area) {
		this.area = area;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		String code = "0";
		for(int i = 0; i < this.shortName.length(); i++) {
			if((this.shortName.charAt(i) >= 'a') && (this.shortName.charAt(i) <= 'z'))
				code += this.shortName.charAt(i) - 'a';
			else if((this.shortName.charAt(i) >= 'A') && (this.shortName.charAt(i) <= 'Z'))
				code += this.shortName.charAt(i) - 'A';
			else
				code += '0';
		}
		return Integer.parseInt(code);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Country) {
			return this.getShortName().equals(((Country)obj).getShortName());
		}
		return false;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@JsonIgnore
	public String getDescription(){
		return String.format("Capital City: %s | Population: %s | Area: %d mk^2", this.capital, String.format("%,d", this.population), this.area);
	}
	
	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	@JsonIgnore
	public String getImagePath() {
		return String.format("/image/flags/%s.gif", this.shortName);
	}
	/**
	 * Hash map to country.
	 * Get hash map and create country entitle
	 * @param hm the hash map
	 * @return the entities. country
	 */
	public static Country fromHashMap(HashMap<String, String> hm) {
		Country re = new Country(hm.get(config.database.tables.columns.Country.NAME),
				hm.get(config.database.tables.columns.Country.SHORT),
				hm.get(config.database.tables.columns.Country.CAPITAL),
				Long.parseLong(hm.get(config.database.tables.columns.Country.POPUALTION)),
				Integer.parseInt(hm.get(config.database.tables.columns.Country.TOTAL_AREA)));
		
		if(hm.get(config.database.tables.columns.Country.TOTAL_CITIES) != null) 
			re.setNumberOfCities(Integer.parseInt(hm.get(config.database.tables.columns.Country.TOTAL_CITIES)));
		if(hm.get(config.database.tables.columns.Country.TOTAL_MAPS) != null) 
			re.setNumberOfMaps(Integer.parseInt(hm.get(config.database.tables.columns.Country.TOTAL_MAPS)));
		if(hm.get(config.database.tables.columns.Country.TOTAL_POIS) != null) 
			re.setNumberOfPois(Integer.parseInt(hm.get(config.database.tables.columns.Country.TOTAL_POIS)));
		if(hm.get(config.database.tables.columns.Country.TOTAL_TOURS) != null) 
			re.setNumberOfTours(Integer.parseInt(hm.get(config.database.tables.columns.Country.TOTAL_TOURS)));
		return re;
	}
	
}
