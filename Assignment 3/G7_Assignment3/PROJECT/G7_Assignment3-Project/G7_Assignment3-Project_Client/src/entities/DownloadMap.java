package entities;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DownloadMap.
 */
public class DownloadMap extends AbstractJsonToDataFile {

	/** The maps. */
	private Map map;
	
	/** The poimaps. */
	private ArrayList<PlaceOfInterestMap> poimaps;

	/**
	 * Instantiates a new DownloadMap
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private DownloadMap() {}
	
	/**
	 * Instantiates a new download map.
	 *
	 * @param map the map
	 * @param poimaps the poimaps
	 */
	public DownloadMap(Map map, ArrayList<PlaceOfInterestMap> poimaps) {
		this.map = map;
		this.poimaps = poimaps;
	}

	/**
	 * Instantiates a new download map.
	 *
	 * @param map the map
	 */
	public DownloadMap(Map map) {
		this(map, new ArrayList<PlaceOfInterestMap>());
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
	 * Gets the poimaps.
	 *
	 * @return the poimaps
	 */
	public ArrayList<PlaceOfInterestMap> getPoimaps() {
		return poimaps;
	}

	/**
	 * Sets the poimaps.
	 *
	 * @param poimaps the new poimaps
	 */
	public void setPoimaps(ArrayList<PlaceOfInterestMap> poimaps) {
		this.poimaps = poimaps;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return this.getMap().equals(((DownloadMap)obj).getMap());
	}

	/**
	 * Save.
	 *
	 * @param folder the folder
	 * @return true, if successful
	 */
	public boolean save(String folder) {
		return super.save("var data=", folder + "/data.js");
	}

}
