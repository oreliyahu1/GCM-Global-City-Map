package entities;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DownloadMap.
 */
public class DownloadCity extends AbstractJsonToDataFile {

	/** The username. */
	private User user;
	
	/** The city. */
	private City city;
	
	/** The maps. */
	private ArrayList<Map> maps;

	/**
	 * Instantiates a new DownloadCity
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private DownloadCity() {}
	
	/**
	 * Instantiates a new download city.
	 *
	 * @param user the user
	 * @param city the city
	 */
	public DownloadCity(User user, City city) {
		this.user=user;
		this.city=city;
		maps = new ArrayList<Map>();
	}
	
	/**
	 * Instantiates a new download city.
	 *
	 * @param user the user
	 * @param city the city
	 * @param maps the maps
	 */
	public DownloadCity(User user, City city, ArrayList<Map> maps) {
		this(user,city);
		this.maps = maps;
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
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the maps.
	 *
	 * @return the maps
	 */
	public ArrayList<Map> getMaps() {
		return maps;
	}

	/**
	 * Sets the maps.
	 *
	 * @param maps the new maps
	 */
	public void setMaps(ArrayList<Map> maps) {
		this.maps = maps;
	}

	/**
	 * Save.
	 *
	 * @param folder the folder
	 * @return true, if successful
	 */
	public boolean save(String folder) {
		return super.save("var data=", folder + "/data.js");
	}

}
