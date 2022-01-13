/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the CreditCard entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditCard.
 */
public class CreditCard  extends AbstractPayment {
	
	/** The owner. */
	private User owner;
	
	/** The number. */
	private long number;
	
	/** The cvv. */
	private int cvv;
	
	/** The expirey date. */
	private String expireyDate;

	/**
	 * Instantiates a new CreditCard 
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private CreditCard() {}

	/**
	 * Instantiates a new credit card.
	 *
	 * @param number the number
	 * @param cvv the cvv
	 * @param expireyDate the expirey date
	 */
	public CreditCard(long number ,int cvv, LocalDate expireyDate) 
	{
		super(CreditCard.class);
        this.number=number;
        this.cvv=cvv;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.expireyDate = formatter.format(expireyDate).toString();
	}
	
	/**
	 * Instantiates a new credit card.
	 *
	 * @param number the number
	 * @param cvv the cvv
	 * @param expireyDate the expirey date
	 */
	public CreditCard(long number ,int cvv , String expireyDate) 
	{
		super(CreditCard.class);
        this.number=number;
        this.cvv=cvv;
        this.expireyDate = expireyDate;
	}
	
	/**
	 * Instantiates a new credit card.
	 *
	 * @param owner the owner
	 * @param number the number
	 * @param cvv the cvv
	 * @param expireyDate the expirey date
	 */
	public CreditCard(User owner, long number ,int cvv, LocalDate expireyDate) 
	{
		super(CreditCard.class);
		this.owner=owner;
        this.number=number;
        this.cvv=cvv;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.expireyDate = formatter.format(expireyDate).toString();
	}
	
	/**
	 * Instantiates a new credit card.
	 *
	 * @param owner the owner
	 * @param number the number
	 * @param cvv the cvv
	 * @param expireyDate the expirey date
	 */
	public CreditCard(User owner, long number ,int cvv, String expireyDate) 
	{
		super(CreditCard.class);
		this.owner=owner;
        this.number=number;
        this.cvv=cvv;
        this.expireyDate = expireyDate;
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Gets the cvv.
	 *
	 * @return the cvv
	 */
	public int getCvv() {
		return cvv;
	}

	/**
	 * Sets the cvv.
	 *
	 * @param cvv the new cvv
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	/**
	 * Gets the expirey date.
	 *
	 * @return the expirey date
	 */
	public String getExpireyDate() {
		return expireyDate;
	}

	/**
	 * Sets the expirey date.
	 *
	 * @param expireyDate the new expirey date
	 */
	public void setExpireyDate(String expireyDate) {
		this.expireyDate = expireyDate;
	}
	

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * From hash map.
	 *
	 * @param hm the hm
	 * @return the credit card
	 */
	public static CreditCard fromHashMap(HashMap<String, String> hm) {
		return new entities.CreditCard(new User(hm.get(config.database.tables.columns.CreditCard.OWNER)),
				Long.parseLong(hm.get(config.database.tables.columns.CreditCard.CARDNUMBER)),
				Integer.parseInt(hm.get(config.database.tables.columns.CreditCard.CVV)),
				hm.get(config.database.tables.columns.CreditCard.EXPIRY));
	}
}
