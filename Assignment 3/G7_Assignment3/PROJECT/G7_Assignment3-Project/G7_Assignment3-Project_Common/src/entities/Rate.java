/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Rate entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Rate.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Rate extends AbstractJsonToString {
	
	/**
	 * The Enum type.
	 */
	public enum type{
		
		/** The onetime. */
		ONETIME,
		
		/** The subscription. */
		SUBSCRIPTION;
		
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
	
	/** The price. */
	private double price;
	
	/** The rtype. */
	private Rate.type rtype;

	/** The days. */
	private int days;
	
	/**
	 * Instantiates a new Rate.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Rate() {}


	/**
	 * Instantiates a new rate.
	 *
	 * @param rtype the rtype
	 * @param price the price
	 */
	public Rate(type rtype, double price) {
		this.price = price;
		this.rtype = rtype;
	}
	
	/**
	 * Instantiates a new rate.
	 *
	 * @param rtype the rtype
	 * @param price the price
	 * @param days the days
	 */
	public Rate(type rtype, double price, int days) {
		this.price = price;
		this.rtype = rtype;
		this.days = days;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}



	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}



	/**
	 * Gets the rtype.
	 *
	 * @return the rtype
	 */
	public Rate.type getRtype() {
		return rtype;
	}



	/**
	 * Sets the rtype.
	 *
	 * @param rtype the new rtype
	 */
	public void setRtype(Rate.type rtype) {
		this.rtype = rtype;
	}



	/**
	 * Gets the days.
	 *
	 * @return the days
	 */
	public int getDays() {
		return days;
	}



	/**
	 * Sets the days.
	 *
	 * @param days the new days
	 */
	public void setDays(int days) {
		this.days = days;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rate) {
			if(this.getRtype().equals(((Rate)obj).getRtype()))
					return this.getDays() == ((Rate)obj).getDays() && this.getPrice() == ((Rate)obj).getPrice();
		}
		return false;
	}
	
	/**
	 * Hash map to Rate
	 * Get hash map and create Rate entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. Rate
	 */
	public static Rate fromHashMap(HashMap<String, String> hm) {
		Rate re = new Rate(Rate.type.fromString(hm.get(config.database.tables.columns.Rate.TYPE)), Double.parseDouble(hm.get(config.database.tables.columns.Rate.PRICE)));
		if(hm.get(config.database.tables.columns.Rate.NUMBEROFDAYS) != null)
			re.setDays(Integer.parseInt(hm.get(config.database.tables.columns.Rate.NUMBEROFDAYS)));
		return re;
	}
}




