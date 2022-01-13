/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Activity entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
// TODO: Auto-generated Javadoc
/**
 * The Class Activity.
 */
public class Activity extends AbstractJsonToString {
	
	/**
	 * The Enum type.
	 */
	public enum type{
		
		/** The download. */
		DOWNLOAD,
		
		/** The view. */
		VIEW;
		
		/**
		 * From string.
		 *
		 * @param value the value
		 * @return the type
		 */
		public static type fromString(String value){
			for(type rtype : type.values()) {
				if(rtype.name().equalsIgnoreCase(value))
					return rtype;
			}
			return null;
		}	
	}
	
	/** The id. */
	private int id;
	
	/** The user. */
	private User user;
	
	/** The map. */
	private Map map;
	
	/** The purchase. */
	private Purchase purchase;
	
	/** The date. */
	private String date;
	
	/** The atype. */
	private Activity.type atype;
	
	/**
	 * Instantiates a new city.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Activity() {}

	/**
	 * Instantiates a new activity.
	 *
	 * @param id the id
	 * @param user the user
	 * @param map the map
	 * @param purchase the purchase
	 * @param date the date
	 * @param atype the atype
	 */
	public Activity(int id, User user, Map map, Purchase purchase, String date, type atype) {
		this.id = id;
		this.user = user;
		this.map = map;
		this.purchase = purchase;
		this.date = date;
		this.atype = atype;
	}
	
	/**
	 * Instantiates a new activity.
	 *
	 * @param user the user
	 * @param map the map
	 * @param purchase the purchase
	 * @param atype the atype
	 */
	public Activity(User user, Map map, Purchase purchase, type atype) {
		this.user = user;
		this.map = map;
		this.purchase = purchase;
		this.atype = atype;
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
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}



	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Map map) {
		this.map = map;
	}



	/**
	 * Gets the purchase.
	 *
	 * @return the purchase
	 */
	public Purchase getPurchase() {
		return purchase;
	}



	/**
	 * Sets the purchase.
	 *
	 * @param purchase the new purchase
	 */
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
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
	 * Gets the atype.
	 *
	 * @return the atype
	 */
	public Activity.type getAtype() {
		return atype;
	}



	/**
	 * Sets the atype.
	 *
	 * @param atype the new atype
	 */
	public void setAtype(Activity.type atype) {
		this.atype = atype;
	}



	/**
	 * Hash map to Activity.
	 * Get hash map and create Activity entitle
	 * @param hm the hash map
	 * @return the entities. Activity
	 */
	public static Activity fromHashMap(HashMap<String, String> hm) {
		Country cre = new Country(hm.get(config.database.tables.columns.Activity.SHORTCOUNTRY));
		if(hm.get(config.database.tables.columns.Country.NAME) != null) 
			cre.setName(hm.get(config.database.tables.columns.Country.NAME));
		City cire = new City(cre, hm.get(config.database.tables.columns.Activity.CITY), Version.fromString(hm.get(config.database.tables.columns.Activity.COLLECTION_VERSION)));
		
		Map mre = new Map(cire, hm.get(config.database.tables.columns.Activity.MAP));
		User ure = new User(hm.get(config.database.tables.columns.Activity.USERNAME));
		Purchase pre = new Purchase(Integer.parseInt(hm.get(config.database.tables.columns.Activity.PURCHASE_ID)));
		
		Activity re = new Activity(ure,mre,pre,Activity.type.fromString(hm.get(config.database.tables.columns.Activity.TYPE)));
		
		if(hm.get(config.database.tables.columns.Activity.ID) != null) 
			re.setId(Integer.parseInt(hm.get(config.database.tables.columns.Activity.ID)));
		if(hm.get(config.database.tables.columns.Activity.DATE) != null) 
			re.setDate(hm.get(config.database.tables.columns.Activity.DATE));
		return re;
	}
	
}
