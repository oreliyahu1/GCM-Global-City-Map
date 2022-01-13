/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the City entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class City.
 */
public class City extends AbstractJsonToString {
	
	/** The country. */
	private Country country;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The version. */
	private Version version;
	
	/** The number of maps. */
	private int numberOfMaps;
	
	/** The number of pois. */
	private int numberOfPois;
	
	/** The number of tours. */
	private int numberOfTours;
	
	/**
	 * Instantiates a new city.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private City() {}

	/**
	 * Instantiates a new city.
	 *
	 * @param country the country
	 * @param name the name
	 * @param description the description
	 * @param version the version
	 * @param numberOfMaps the number of maps
	 * @param numberOfPois the number of pois
	 * @param numberOfTours the number of tours
	 */
	public City(Country country, String name, String description, Version version, int numberOfMaps, int numberOfPois, int numberOfTours) {
		this.country = country;
		this.name = name;
		this.description = description;
		this.version = version;
		this.numberOfMaps = numberOfMaps;
		this.numberOfPois = numberOfPois;
		this.numberOfTours = numberOfTours;
	}

	/**
	 * Instantiates a new city.
	 *
	 * @param country the country
	 * @param name the name
	 * @param description the description
	 * @param version the version
	 */
	public City(Country country, String name, String description, Version version) {
		this.country = country;
		this.name = name;
		this.description = description;
		this.version = version;
	}
	
	/**
	 * Instantiates a new city.
	 *
	 * @param country the country
	 * @param name the name
	 * @param version the version
	 */
	public City(Country country, String name, Version version) {
		this.country = country;
		this.name = name;
		this.version = version;
	}
	
	/**
	 * Instantiates a new city.
	 *
	 * @param country the country
	 * @param name the name
	 */
	public City(Country country, String name) {
		this.country = country;
		this.name = name;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
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
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Version version) {
		this.version = version;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof City) {
			if(this.getCountry().equals(((City)obj).getCountry()))
					return this.getName().equals(((City)obj).getName()) && this.getVersion().equals(((City)obj).getVersion());
		}
		return false;
	}
	
	/**
	 * Hash map to city.
	 * Get hash map and create city entitle
	 * @param hm the hash map
	 * @return the entities. city
	 */
	public static City fromHashMap(HashMap<String, String> hm) {
		City re = new City(new Country(hm.get(config.database.tables.columns.City.SHORTCOUNTRY)),
				hm.get(config.database.tables.columns.City.NAME),
				hm.get(config.database.tables.columns.City.DESCRIPTION),
				Version.fromString(hm.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION)));
		
		if(hm.get(config.database.tables.columns.Country.NAME) != null) 
			re.getCountry().setName(hm.get(config.database.tables.columns.Country.NAME));
		if(hm.get(config.database.tables.columns.City.TOTAL_MAPS) != null) 
			re.setNumberOfMaps(Integer.parseInt(hm.get(config.database.tables.columns.City.TOTAL_MAPS)));
		if(hm.get(config.database.tables.columns.City.TOTAL_POIS) != null) 
			re.setNumberOfPois(Integer.parseInt(hm.get(config.database.tables.columns.City.TOTAL_POIS)));
		if(hm.get(config.database.tables.columns.City.TOTAL_TOURS) != null) 
			re.setNumberOfTours(Integer.parseInt(hm.get(config.database.tables.columns.City.TOTAL_TOURS)));
		return re;
	}
	
}
