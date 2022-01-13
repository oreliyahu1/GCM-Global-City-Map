/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Map entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Base64;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 */

@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Map extends AbstractJsonToString {
	
	/** The city. */
	private City city;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The map ver. */
	private Version mapVer;
	
	/** The base 64 image. */
	private String base64_image;
	
	/** The number of pois. */
	private int numberOfPois;
	
	/**
	 * Instantiates a new Map.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Map() {}

	/**
	 * Instantiates a new map.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param mapVer the map ver
	 * @param numberOfPois the number of pois
	 */
	public Map(City city, String name, String description, Version mapVer, int numberOfPois) {
		this.city = city;
		this.name = name;
		this.description = description;
		this.mapVer = mapVer;
		this.numberOfPois = numberOfPois;
	}

	/**
	 * Instantiates a new map.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param mapVer the map ver
	 * @param base64_image the base 64 image
	 * @param numberOfPois the number of pois
	 */
	public Map(City city, String name, String description, Version mapVer, String base64_image, int numberOfPois) {
		this.city = city;
		this.name = name;
		this.description = description;
		this.mapVer = mapVer;
		this.base64_image=base64_image;
		this.numberOfPois = numberOfPois;
	}
	
	/**
	 * Instantiates a new map.
	 *
	 * @param city the city
	 * @param name the name
	 * @param description the description
	 * @param mapVer the map ver
	 */
	public Map(City city, String name, String description, Version mapVer) {
		this.city = city;
		this.name = name;
		this.description = description;
		this.mapVer = mapVer;
	}
	
	/**
	 * Instantiates a new map.
	 *
	 * @param city the city
	 * @param name the name
	 */
	public Map(City city, String name) {
		this.city = city;
		this.name = name;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the map ver.
	 *
	 * @return the map ver
	 */
	public Version getMapVer() {
		return mapVer;
	}

	/**
	 * Sets the map ver.
	 *
	 * @param mapVer the new map ver
	 */
	public void setMapVer(Version mapVer) {
		this.mapVer = mapVer;
	}

	/**
	 * Gets the number of pois.
	 *
	 * @return the number of pois
	 */
	public int getNumberOfPois() {
		return numberOfPois;
	}

	/**
	 * Sets the number of pois.
	 *
	 * @param numberOfPois the new number of pois
	 */
	public void setNumberOfPois(int numberOfPois) {
		this.numberOfPois = numberOfPois;
	}

	/**
	 * Gets the base 64 image.
	 *
	 * @return the base 64 image
	 */
	public String getBase64_image() {
		return base64_image;
	}

	/**
	 * Sets the base 64 image.
	 *
	 * @param base64_image the new base 64 image
	 */
	public void setBase64_image(String base64_image) {
		this.base64_image = base64_image;
	}
	
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	@JsonIgnore
	public BufferedImage getImage() {
		try {
			byte[] imageByte = Base64.getDecoder().decode(this.base64_image);
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageByte));
			return bufImg;
		} catch (IOException e) {
			System.out.println("Map error: cannot create image from base64 string " + e.toString());
		}
        return null;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	@JsonIgnore
	public void setImage(BufferedImage image) {
		try {
			ByteArrayOutputStream imageByte = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", imageByte);
			setBase64_image(Base64.getEncoder().encodeToString(imageByte.toByteArray()));
		} catch (IOException e) {
			System.out.println("Map error: cannot create base64 image string " + e.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Map) {
			if(this.getCity().equals(((Map)obj).getCity()))
					return this.getName().equals(((Map)obj).getName()) && this.getMapVer().equals(((Map)obj).getMapVer());
		}
		return false;
	}
	
	/**
	 * Hash map to map
	 * Get hash map and create country,city,map entitle.
	 *
	 * @param hm the hash map
	 * @return the entities. map
	 */
	public static Map fromHashMap(HashMap<String, String> hm) {
		Country cre = new Country(hm.get(config.database.tables.columns.Map.SHORTCOUNTRY));
		City cire = new City(cre, hm.get(config.database.tables.columns.Map.CITY));
		if(hm.get(config.database.tables.columns.Map.COLLECTION_VERSION) != null)
			cire.setVersion(Version.fromString(hm.get(config.database.tables.columns.Map.COLLECTION_VERSION)));
		Map re = new Map(cire, hm.get(config.database.tables.columns.Map.NAME), hm.get(config.database.tables.columns.Map.DESCRIPTION), Version.fromString(hm.get(config.database.tables.columns.Map.MAP_VERSION)));
		
		if(hm.get(config.database.tables.columns.Map.IMAGE) != null)
			re.setBase64_image(hm.get(config.database.tables.columns.Map.IMAGE));
		if(hm.get(config.database.tables.columns.Map.TOTAL_POIS) != null)
			re.setNumberOfPois(Integer.parseInt(hm.get(config.database.tables.columns.Map.TOTAL_POIS)));
		
		return re;
	}

	
}
