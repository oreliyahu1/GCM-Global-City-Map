/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the RateRequest entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class RateRequest.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class RateRequest extends AbstractRequest {

	/** The rate. */
	private Rate rate;
	/**
	 * Instantiates a new RateRequest.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private RateRequest() {}
	
	
	/**
	 * Instantiates a new rate request.
	 *
	 * @param id the id
	 * @param user the user
	 * @param status the status
	 * @param date the date
	 * @param rate the rate
	 */
	public RateRequest(int id, User user, AbstractRequest.STATUS status, String date, Rate rate) {
		super(id,user,status,date);
		this.rate = rate;
	}

	/**
	 * Instantiates a new rate request.
	 *
	 * @param user the user
	 * @param rate the rate
	 */
	public RateRequest(User user, Rate rate) {
		super(user);
		this.rate = rate;
	}
	

	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public Rate getRate() {
		return rate;
	}


	/**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 */
	public void setRate(Rate rate) {
		this.rate = rate;
	}


	/**
	 * Hash map to RateRequest
	 * Get hash map and create RateRequest entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. RateRequest
	 */
	public static RateRequest fromHashMap(HashMap<String, String> hm) {
		Rate rre = new Rate(Rate.type.fromString(hm.get(config.database.tables.columns.RateRequest.TYPE)), Double.parseDouble(hm.get(config.database.tables.columns.RateRequest.PRICE)));
		if(hm.get(config.database.tables.columns.RateRequest.NUMBEROFDAYS) != null)
			rre.setDays(Integer.parseInt(hm.get(config.database.tables.columns.RateRequest.NUMBEROFDAYS)));
		User ure = new User(hm.get(config.database.tables.columns.RateRequest.USERNAME_REQUEST));
		RateRequest re = new RateRequest(ure, rre);
		
		if(hm.get(config.database.tables.columns.RateRequest.ID) != null)
			re.setId(Integer.parseInt(hm.get(config.database.tables.columns.RateRequest.ID)));
		if(hm.get(config.database.tables.columns.RateRequest.DATE) != null)
			re.setDate(hm.get(config.database.tables.columns.RateRequest.DATE));
		if(hm.get(config.database.tables.columns.RateRequest.STATUS) != null)
			re.setStatus(AbstractRequest.STATUS.valueOf(hm.get(config.database.tables.columns.RateRequest.STATUS)));
		
		return re;
	}
}
