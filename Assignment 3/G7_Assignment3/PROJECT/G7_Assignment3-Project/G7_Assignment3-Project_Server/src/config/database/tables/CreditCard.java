/*
 * this class defines CreditCard table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditCard.
 * Static class
 * This class consist of columns types of CreditCard table on db scheme. 
 */
public final class CreditCard {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "creditcard";
	
	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final Map<String,String> DB_TABLE = new HashMap<String, String>()
	{
	   {
	     put(config.database.tables.columns.CreditCard.OWNER, config.database.tables.User.DB_TABLE.get(config.database.tables.columns.User.USERNAME) + " primary key");
	     put(config.database.tables.columns.CreditCard.CARDNUMBER, "bigint");
	     put(config.database.tables.columns.CreditCard.CVV, "int");
	     put(config.database.tables.columns.CreditCard.EXPIRY, "date");
	     
	     //define references keys
	     put("", String.format("foreign key(`%s`) references `%s`(`%s`) on delete cascade on update cascade", config.database.tables.columns.CreditCard.OWNER, config.database.tables.User.TABLE_NAME, config.database.tables.columns.User.USERNAME));
	     
	   }
	};

	/**
	 * Override public contractor to make it static.
	 */
	private CreditCard() {}
	
}
