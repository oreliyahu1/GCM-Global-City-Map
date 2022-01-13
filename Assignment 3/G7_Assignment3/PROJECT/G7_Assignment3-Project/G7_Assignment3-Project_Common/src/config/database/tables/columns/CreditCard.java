package config.database.tables.columns;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditCard.
 * Static class
 * This class consist of column value's of CreditCard table on db scheme. 
 */
public final class CreditCard {


	/** The Constant OWNER. */
	public static final String OWNER = config.database.tables.columns.User.USERNAME; //References to user
	
	/** The Constant CARDNUMBER. */
	public static final String CARDNUMBER = "number";
	
	/** The Constant CVV. */
	public static final String CVV = "cvv";

	/** The Constant EXPIRY. */
	public static final String EXPIRY = "exp";
	
	/**
	 * Override public contractor to make it static.
	 */
	private CreditCard() {}
	
}
