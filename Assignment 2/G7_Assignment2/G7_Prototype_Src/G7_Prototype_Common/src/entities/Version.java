package entities;

/**
 * The Class Version.
 */
public class Version extends AbstractJsonToString {
	
	/** The v main. */
	private int vMain;
	
	/** The v sub. */
	private int vSub;
	
	/**
	 * Instantiates a new city.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Version() {}
	
	/**
	 * Instantiates a new version.
	 *
	 * @param vMain the v main
	 * @param vSub the v sub
	 */
	public Version(int vMain, int vSub) {
		this.setVersion(vMain, vSub);
	}	
	
	/**
	 * Sets the version.
	 *
	 * @param vMain the v main
	 * @param vSub the v sub
	 */
	public void setVersion(int vMain, int vSub) {
		this.setvMain(vMain);
		this.setvSub(vSub);
	}
	
	/**
	 * Gets the v main.
	 *
	 * @return the v main
	 */
	public int getvMain() {
		return vMain;
	}
	
	/**
	 * Sets the v main.
	 *
	 * @param vMain the new v main
	 */
	public void setvMain(int vMain) {
		if(vMain > 0)
			this.vMain = vMain;
		else
			this.vMain = 0;
	}
	
	/**
	 * Gets the v sub.
	 *
	 * @return the v sub
	 */
	public int getvSub() {
		return vSub;
	}
	
	/**
	 * Sets the v sub.
	 *
	 * @param vSub the new v sub
	 */
	public void setvSub(int vSub) {
		if(vSub > 0)
			this.vSub = vSub;
		else
			this.vSub = 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%d.%d", getvMain(), getvSub());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * comparing between version(equals the same version)
	 */
	@Override
	public boolean equals(Object ver) {
		if(ver instanceof Version)
			return (this.getvMain() == ((Version)ver).getvMain()) && (this.getvSub() == ((Version)ver).getvSub());
		return false;
	}
	
	/**
	 * String to version.
	 * Get string of version and create version entitle
	 * @param ver the ver
	 * @return the version
	 */
	public static Version fromString(String ver) {
		int vM = Integer.parseInt(ver.split("\\.")[0]);
		int vS = Integer.parseInt(ver.split("\\.")[1]);
		return new Version(vM,vS);
	}
}
