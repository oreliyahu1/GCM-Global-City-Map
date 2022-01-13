/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the PlaceOfInterest entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
// TODO: Auto-generated Javadoc
/**
 * The Class PlaceOfInterest.
 */
public class PlaceOfInterest extends AbstractJsonToString {

	/** The city. */
	private City city;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The accessible. */
	private boolean accessible;
	
	/** The category. */
	private Category category;
	
	/**
	 * Instantiates a new PlaceOfInterest.
	 * Need to be used by jackson(lib).
	 */
	protected PlaceOfInterest() {}

	/**
	 * Instantiates a new place of interest.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param accessible the accessible
	 */
	public PlaceOfInterest(City city, String name, String description, boolean accessible) {
		this.city = city;
		this.name = name;
		this.description = description;
		this.accessible = accessible;
	}
	
	/**
	 * Instantiates a new place of interest.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param accessible the accessible
	 * @param category the category
	 */
	public PlaceOfInterest(City city, String name, String description, boolean accessible, Category category) {
		this.city = city;
		this.name = name;
		this.description = description;
		this.accessible = accessible;
		this.category = category;
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
	 * Checks if is accessible.
	 *
	 * @return true, if is accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}

	/**
	 * Sets the accessible.
	 *
	 * @param accessible the new accessible
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PlaceOfInterest) {
			if(this.getCity().equals(((PlaceOfInterest)obj).getCity()))
					return this.getName().equals(((PlaceOfInterest)obj).getName());
		}
		return false;
	}
	
	/**
	 * Hash map to PlaceOfInterest
	 * Get hash map and create PlaceOfInterest entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. PlaceOfInterest
	 */
	public static PlaceOfInterest fromHashMap(HashMap<String, String> hm) {
		Country cre = new Country(hm.get(config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY));
		City cire = new City(cre, hm.get(config.database.tables.columns.PlaceOfInterest.CITY), Version.fromString(hm.get(config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION)));
		
		PlaceOfInterest re = new PlaceOfInterest(cire,
				hm.get(config.database.tables.columns.PlaceOfInterest.NAME),
				hm.get(config.database.tables.columns.PlaceOfInterest.DESCRIPTION),
				(Integer.parseInt(hm.get(config.database.tables.columns.PlaceOfInterest.ACCESSIBLE))==1));
			
		if(hm.get(config.database.tables.columns.PlaceOfInterest.TYPE) != null)
			re.setCategory(new Category(hm.get(config.database.tables.columns.PlaceOfInterest.TYPE)));
		
		return re;
	}

	
	
}
