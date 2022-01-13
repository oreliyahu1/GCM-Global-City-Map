/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Coordinates entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinates.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Coordinates extends AbstractJsonToString {
	
	/** The x. */
	private double x;
	
	/** The y. */
	private double y;
	
	/**
	 * Instantiates a new Coordinates.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Coordinates() {}

	/**
	 * Instantiates a new coordinates.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Hash map to Coordinates
	 * Get hash map and create coordinates entitle.
	 *
	 * @param hm the hash map
	 * @return the entities.Coordinates
	 */
	public static Coordinates fromHashMap(HashMap<String, String> hm) {
		Coordinates re = new Coordinates(0,0);
		if(hm.get(config.database.tables.columns.PlaceOfInterestMap.LOCATION_X) != null)
			re.setX(Double.parseDouble(hm.get(config.database.tables.columns.PlaceOfInterestMap.LOCATION_X)));
		if(hm.get(config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y) != null)
			re.setY(Double.parseDouble(hm.get(config.database.tables.columns.PlaceOfInterestMap.LOCATION_Y)));
		return re;
	}

	
}
