/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Tour entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Purchase extends AbstractJsonToString {
	
	/** The id. */
	private int id;
	
	/** The user. */
	private User user;
	
	/** The purchase price. */
	private Rate purchasePrice;
	
	/** The date. */
	private String date;
	
	/** The city. */
	private City city;
	
	/** The renewal. */
	private int renewal;
	
	/** The downloaded. */
	private boolean downloaded;
	
	/**
	 * Instantiates a new purchase.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Purchase() {}

	/**
	 * Instantiates a new purchase.
	 *
	 * @param id the id
	 * @param user the user
	 * @param purchasePrice the purchase price
	 * @param date the date
	 * @param city the city
	 * @param renewal the renewal
	 * @param downloaded the downloaded
	 */
	public Purchase(int id, User user, Rate purchasePrice, String date, City city, int renewal, boolean downloaded) {
		this.id = id;
		this.user = user;
		this.purchasePrice = purchasePrice;
		this.date = date;
		this.city = city;
		this.renewal = renewal;
		this.downloaded = downloaded;
	}
	
	/**
	 * Instantiates a new purchase.
	 *
	 * @param user the user
	 * @param purchasePrice the purchase price
	 * @param city the city
	 */
	public Purchase(User user, Rate purchasePrice, City city) {
		this.user = user;
		this.purchasePrice = purchasePrice;
		this.city = city;
	}
	
	/**
	 * Instantiates a new purchase.
	 *
	 * @param id the id
	 */
	public Purchase(int id) {
		this.id=id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Gets the purchase price.
	 *
	 * @return the purchase price
	 */
	public Rate getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Sets the purchase price.
	 *
	 * @param purchasePrice the new purchase price
	 */
	public void setPurchasePrice(Rate purchasePrice) {
		this.purchasePrice = purchasePrice;
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
	 * Gets the renewal.
	 *
	 * @return the renewal
	 */
	public int getRenewal() {
		return renewal;
	}

	/**
	 * Sets the renewal.
	 *
	 * @param renewal the new renewal
	 */
	public void setRenewal(int renewal) {
		this.renewal = renewal;
	}
	
	/**
	 * Checks if is downloaded.
	 *
	 * @return true, if is downloaded
	 */
	public boolean isDownloaded() {
		return downloaded;
	}

	/**
	 * Sets the downloaded.
	 *
	 * @param downloaded the new downloaded
	 */
	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
	}

	/**
	 * Hash map to Purchase.
	 * Get hash map and create Purchase entitle
	 * @param hm the hash map
	 * @return the entities. Purchase
	 
	 */
	
	public static Purchase fromHashMap(HashMap<String, String> hm)  {
		Country cre = new Country(hm.get(config.database.tables.columns.Purchase.SHORTCOUNTRY));
		if(hm.get(config.database.tables.columns.Country.NAME) != null) 
			cre.setName(hm.get(config.database.tables.columns.Country.NAME));
		City cire = new City(cre, hm.get(config.database.tables.columns.Purchase.CITY), Version.fromString(hm.get(config.database.tables.columns.Purchase.COLLECTION_VERSION)));
		User ure = new User(hm.get(config.database.tables.columns.Purchase.USER));
		Rate rre = new Rate(Rate.type.fromString(hm.get(config.database.tables.columns.Purchase.TYPE)),Double.parseDouble(hm.get(config.database.tables.columns.Purchase.PRICE)));
		if(hm.get(config.database.tables.columns.Purchase.NUMBEROFDAYS) != null) 
			rre.setDays(Integer.parseInt(hm.get(config.database.tables.columns.Purchase.NUMBEROFDAYS)));
		
		Purchase re = new Purchase(ure,rre,cire);
		
		if(hm.get(config.database.tables.columns.Purchase.RENEWAL) != null) 
			re.setRenewal(Integer.parseInt(hm.get(config.database.tables.columns.Purchase.RENEWAL)));
		if(hm.get(config.database.tables.columns.Purchase.DATE) != null) 
			re.setDate(hm.get(config.database.tables.columns.Purchase.DATE));			
		if(hm.get(config.database.tables.columns.Purchase.ID) != null) 
			re.setId(Integer.parseInt(hm.get(config.database.tables.columns.Purchase.ID)));	
		if(hm.get(config.database.tables.columns.Purchase.DOWNLOADED) != null) 
			re.setDownloaded(Integer.parseInt(hm.get(config.database.tables.columns.Purchase.DOWNLOADED))==1);		

		return re;
	}
	
}
