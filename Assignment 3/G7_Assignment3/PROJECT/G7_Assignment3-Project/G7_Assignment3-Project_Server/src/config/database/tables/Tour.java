/*
 * this class defines Tour table with columns , primary key and foreign keys
 */
package config.database.tables;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Tour. Static class This class consist of columns types of Tour
 * table on db scheme.
 */
public final class Tour {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "tour";

	/** The Constant DB_TABLE. */
	@SuppressWarnings("serial")
	public static final java.util.Map<String, String> DB_TABLE = new HashMap<String, String>() {
		{
			put(config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.Tour.SHORTCOUNTRY));
			put(config.database.tables.columns.Tour.CITY, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.Tour.CITY));
			put(config.database.tables.columns.Tour.POI, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.Tour.POI));
			put(config.database.tables.columns.Tour.COLLECTION_VERSION, config.database.tables.PlaceOfInterest.DB_TABLE.get(config.database.tables.columns.Tour.COLLECTION_VERSION));
			put(config.database.tables.columns.Tour.NAME, "varchar(50)");

			put(config.database.tables.columns.Tour.DESCRIPTION, "varchar(300)");
			put(config.database.tables.columns.Tour.RECOMMENDED, "bool");
			put(config.database.tables.columns.Tour.SECTIME, "bigint");
			put(config.database.tables.columns.Tour.INDEX, "int");

			// multiple define primary and references keys
			put("", String.format("primary key(`%s`,`%s`,`%s`,`%s`,`%s`),"
					+ "foreign key(`%s`,`%s`,`%s`,`%s`) references `%s`(`%s`,`%s`,`%s`,`%s`) on delete cascade on update cascade" ,
					config.database.tables.columns.Tour.SHORTCOUNTRY, config.database.tables.columns.Tour.CITY, config.database.tables.columns.Tour.POI, config.database.tables.columns.Tour.COLLECTION_VERSION, config.database.tables.columns.Tour.NAME,
					config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION,
					config.database.tables.PlaceOfInterest.TABLE_NAME,
					config.database.tables.columns.PlaceOfInterest.SHORTCOUNTRY, config.database.tables.columns.PlaceOfInterest.CITY, config.database.tables.columns.PlaceOfInterest.NAME, config.database.tables.columns.PlaceOfInterest.COLLECTION_VERSION
					));

		}
	};

	/**
	 * Override public contractor to make it static.
	 */
	private Tour() {
	}

}
