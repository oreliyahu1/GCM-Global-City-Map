/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the DailyReport entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// TODO: Auto-generated Javadoc
/**
 * The Class DailyReport.
 */

@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class DailyReport extends AbstractJsonToString {
	
	/** The date. */
	private String date;
	
	/** The user. */
	private ArrayList<User> user;
	
	/** The city. */
	private ArrayList<City> city;
	
	/** The activity. */
	private ArrayList<Activity> activity;
	
	/** The message. */
	private ArrayList<Message> message;
	
	/** The purchase. */
	private ArrayList<Purchase> purchase;
	
	/** The rate request. */
	private ArrayList<RateRequest> rateRequest;
	
	/** The version request. */
	private ArrayList<VersionRequest> versionRequest;
	
	/**
	 * Instantiates a new DailyReport.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private DailyReport() {}

	/**
	 * Instantiates a new daily report.
	 *
	 * @param date the date
	 */
	public DailyReport(String date) {
		this.date = date;
	}
	
	/**
	 * Instantiates a new daily report.
	 *
	 * @param date the date
	 * @param user the user
	 * @param city the city
	 * @param activity the activity
	 * @param message the message
	 * @param purchase the purchase
	 * @param rateRequest the rate request
	 * @param versionRequest the version request
	 */
	public DailyReport(String date, ArrayList<User> user, ArrayList<City> city, ArrayList<Activity> activity, ArrayList<Message> message,
			ArrayList<Purchase> purchase, ArrayList<RateRequest> rateRequest,
			ArrayList<VersionRequest> versionRequest) {
		this.user = user;
		this.city = city;
		this.activity = activity;
		this.message = message;
		this.purchase = purchase;
		this.rateRequest = rateRequest;
		this.versionRequest = versionRequest;
	}

	

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public ArrayList<User> getUser() {
		return user;
	}


	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(ArrayList<User> user) {
		this.user = user;
	}


	/**
	 * Gets the activity.
	 *
	 * @return the activity
	 */
	public ArrayList<Activity> getActivity() {
		return activity;
	}


	/**
	 * Sets the activity.
	 *
	 * @param activity the new activity
	 */
	public void setActivity(ArrayList<Activity> activity) {
		this.activity = activity;
	}


	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public ArrayList<Message> getMessage() {
		return message;
	}


	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(ArrayList<Message> message) {
		this.message = message;
	}


	/**
	 * Gets the purchase.
	 *
	 * @return the purchase
	 */
	public ArrayList<Purchase> getPurchase() {
		return purchase;
	}


	/**
	 * Sets the purchase.
	 *
	 * @param purchase the new purchase
	 */
	public void setPurchase(ArrayList<Purchase> purchase) {
		this.purchase = purchase;
	}


	/**
	 * Gets the rate request.
	 *
	 * @return the rate request
	 */
	public ArrayList<RateRequest> getRateRequest() {
		return rateRequest;
	}


	/**
	 * Sets the rate request.
	 *
	 * @param rateRequest the new rate request
	 */
	public void setRateRequest(ArrayList<RateRequest> rateRequest) {
		this.rateRequest = rateRequest;
	}


	/**
	 * Gets the version request.
	 *
	 * @return the version request
	 */
	public ArrayList<VersionRequest> getVersionRequest() {
		return versionRequest;
	}


	/**
	 * Sets the version request.
	 *
	 * @param versionRequest the new version request
	 */
	public void setVersionRequest(ArrayList<VersionRequest> versionRequest) {
		this.versionRequest = versionRequest;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public ArrayList<City> getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(ArrayList<City> city) {
		this.city = city;
	}

	/**
	 * Hash map to map
	 * Get hash map and create DailyReport entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. DailyReport
	 */
	public static DailyReport fromHashMap(HashMap<String, String> hm) {
		try {
			return AbstractJsonToString.toObject(entities.DailyReport.class, hm.get(config.database.tables.columns.DailyReport.REPORT));	
		} catch (IOException e) {
			return new DailyReport(hm.get(config.database.tables.columns.DailyReport.DATE));
		}
	}

	
}
