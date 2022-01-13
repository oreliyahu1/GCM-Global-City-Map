/*
  * this class extends the abstract class AbstractJsonToString.
 * it defines the VersionRequest entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class VersionRequest.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class VersionRequest extends AbstractRequest {

	/** The city. */
	private City city;
	/**
	 * Instantiates a new VersionRequest.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private VersionRequest() {}
	
	/**
	 * Instantiates a new version request.
	 *
	 * @param id the id
	 * @param user the user
	 * @param status the status
	 * @param date the date
	 * @param city the city
	 */
	public VersionRequest(int id, User user, AbstractRequest.STATUS status, String date, City city) {
		super(id,user,status,date);
		this.city = city;
	}

	/**
	 * Instantiates a new version request.
	 *
	 * @param user the user
	 * @param city the city
	 */
	public VersionRequest(User user, City city) {
		super(user);
		this.city = city;
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
	 * Hash map to VersionRequest
	 * Get hash map and create VersionRequest entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. VersionRequest
	 */
	public static VersionRequest fromHashMap(HashMap<String, String> hm) {

		Country cre = new Country(hm.get(config.database.tables.columns.VersionRequest.SHORTCOUNTRY));
		if(hm.get(config.database.tables.columns.Country.NAME) != null)
			cre.setName(hm.get(config.database.tables.columns.Country.NAME));
		City cire = new City(cre, hm.get(config.database.tables.columns.VersionRequest.CITY), Version.fromString(hm.get(config.database.tables.columns.VersionRequest.COLLECTION_VERSION)));
		User ure = new User(hm.get(config.database.tables.columns.VersionRequest.USERNAME_REQUEST));
		VersionRequest re = new VersionRequest(ure, cire);

		if(hm.get(config.database.tables.columns.VersionRequest.ID) != null)
			re.setId(Integer.parseInt(hm.get(config.database.tables.columns.VersionRequest.ID)));
		if(hm.get(config.database.tables.columns.VersionRequest.DATE) != null)
			re.setDate(hm.get(config.database.tables.columns.VersionRequest.DATE));
		if(hm.get(config.database.tables.columns.RateRequest.STATUS) != null)
			re.setStatus(AbstractRequest.STATUS.valueOf(hm.get(config.database.tables.columns.RateRequest.STATUS)));
		
		return re;
	}
	
}
