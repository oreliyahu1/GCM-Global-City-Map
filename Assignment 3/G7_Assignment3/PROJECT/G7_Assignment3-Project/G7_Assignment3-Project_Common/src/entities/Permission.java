
package entities;

// TODO: Auto-generated Javadoc
/**
 * The Class Permission.
 */
public class Permission extends AbstractJsonToString {
	
	/** The premission. */
	private long premission;
	
	/**
	 * The Enum Role.
	 */
	public static enum Role {
	    
    	/** The client. */
    	CLIENT(1), 
    	/** The worker. */
	    WORKER(2),
    	/** The productworker. */
	    PRODUCTWORKER(4),
	    /** The productmanager. */
	    PRODUCTMANAGER(8), 
	    /** The companymanager. */
	    COMPANYMANAGER(16);
	    
	    /** The id. */
 private final long id;
	    
    	/**
    	 * Instantiates a new role.
    	 *
    	 * @param id the id
    	 */
    	Role(long id) { this.id = id; }
	    
    	/**
    	 * Gets the value.
    	 *
    	 * @return the value
    	 */
    	public long getValue() { return id; }
	}
	
	/*	
	/**
	 * The Enum System.
	 
	 WE DONT HAVE ENOUGH TIME!!!!!!!!!!!!!!!!!!
	public static enum System {
		
		*//** The purchase. *//*
		PURCHASE(32), 
		*//** The user management. *//*
	    USER_MANAGEMENT(64), 
	    *//** The user management permissions. *//*
	    USER_MANAGEMENT_PERMISSIONS(128), 
	    *//** The version request. *//*
	    VERSION_REQUEST(256), 
	    *//** The version request approval. *//*
		VERSION_REQUEST_APPROVAL(512), 
		*//** The rate request. *//*
	    RATE_REQUEST(1024), 
	    *//** The rate request approval. *//*
		RATE_REQUEST_APPROVAL(2048), 
		*//** The catalog view. *//*
	    CATALOG_VIEW(4096), 
	    *//** The catalog edit. *//*
		CATALOG_EDIT(8192), 
		*//** The catalog delete. *//*
		CATALOG_DELETE(16384), 
		*//** The view report. *//*
		VIEW_REPORT(32768); 
		
		*//** The id. *//*
	    private final long id;
	    
    	*//**
    	 * Instantiates a new system.
    	 *
    	 * @param id the id
    	 *//*
    	System(long id) { this.id = id; }
	    
    	*//**
    	 * Gets the value.
    	 *
    	 * @return the value
    	 *//*
    	public long getValue() { return id; }
	}
	*/
	/**
	 * Instantiates a new Person
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Permission() {}

	/**
	 * Instantiates a new permission.
	 *
	 * @param premission the premission
	 */
	public Permission(long premission) {
		this.premission = premission;
	}

	/**
	 * Gets the premission.
	 *
	 * @return the premission
	 */
	public long getPremission() {
		return premission;
	}

	/**
	 * Sets the premission.
	 *
	 * @param premission the new premission
	 */
	public void setPremission(long premission) {
		this.premission = premission;
	}
	
	/**
	 * Adds the role.
	 *
	 * @param role the role
	 */
	public void addRole(Permission.Role role) {
		for(Permission.Role arole : Permission.Role.values())
			deleteRole(arole);
		this.premission |= role.id;
	}
	
	/**
	 * Delete role.
	 *
	 * @param role the role
	 */
	public void deleteRole(Permission.Role role) {
		this.premission -= (this.premission & role.id);
	}
	
	/**
	 * Check role.
	 *
	 * @param role the role
	 * @return true, if successful
	 */
	public boolean checkRole(Permission.Role role) {
		if((this.premission & role.id) == 0) return false;
		return true;
	}
	
/*	*//**
	 * Adds the system premission.
	 *
	 * @param premission the premission
	 *//*
	public void addSystemPremission(Permission.System premission) {
		this.premission |= premission.id;
	}
	
	*//**
	 * Delete system premission.
	 *
	 * @param premission the premission
	 *//*
	public void deleteSystemPremission(Permission.System premission) {
		this.premission -= (this.premission & premission.id);
	}
	
	*//**
	 * Check system premission.
	 *
	 * @param premission the premission
	 * @return true, if successful
	 *//*
	public boolean checkSystemPremission(Permission.System premission) {
		if((this.premission & premission.id) == 0) return false;
		return true;
	}*/
}
