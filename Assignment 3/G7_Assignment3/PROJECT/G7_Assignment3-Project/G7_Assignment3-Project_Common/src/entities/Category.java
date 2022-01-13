/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the Category entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class Category.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class Category extends AbstractJsonToString {
	
	/** The category. */
	private String category;
	
	/**
	 * Instantiates a new Category.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Category() {}

	/**
	 * Instantiates a new category.
	 *
	 * @param category the category
	 */
	public Category(String category) {
		this.category = category;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Category) {
			return this.getCategory().equals(((Category)obj).getCategory());
		}
		return false;
	}
	
	/**
	 * Hash map to Category
	 * Get hash map and create category entitle.
	 *
	 * @param hm the hash map
	 * @return the entities.Coordinates
	 */
	public static Category fromHashMap(HashMap<String, String> hm) {
		Category re = new Category(null);
		if(hm.get(config.database.tables.columns.Category.NAME) != null)
			re.setCategory(hm.get(config.database.tables.columns.Category.NAME));
		return re;
	}

	
}
