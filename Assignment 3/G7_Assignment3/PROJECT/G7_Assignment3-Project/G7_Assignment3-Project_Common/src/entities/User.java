/*
 * this class extends the abstract class AbstractJsonToString.
 * it defines the User entity , with "fromHashMap" function which initializes an object correspondingly
 */
package entities;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //jackson ignore unknow values
public class User extends AbstractPerson {
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The permission. */
	private Permission permission;
	
	/** The created. */
	private String created;

	/**
	 * Instantiates a new Person
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private User() { super(); }
	
	/**
	 * Instantiates a new user.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param phoneNumber the phone number
	 * @param email the email
	 * @param username the username
	 * @param password the password
	 * @param permission the permission
	 */
	public User(String firstName, String lastName, String phoneNumber, String email, String username, String password, Permission permission) {
		super(firstName, lastName, phoneNumber, email);
		this.username = username;
		this.password = password;
		this.permission = permission;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param phoneNumber the phone number
	 * @param email the email
	 * @param username the username
	 * @param password the password
	 * @param permission the permission
	 * @param created the created date
	 */
	public User(String firstName, String lastName, String phoneNumber, String email, String username, String password, Permission permission, String created) {
		super(firstName, lastName, phoneNumber, email);
		this.username = username;
		this.password = password;
		this.permission = permission;
		this.created=created;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 */
	public User(String username) {
		super(null, null, null, null);
		this.username = username;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the permission.
	 *
	 * @return the permission
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * Sets the permission.
	 *
	 * @param permission the new permission
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			return this.getUsername().equals(((User)obj).getUsername());
		}
		return false;
	}
	
	
	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * From hash map.
	 *
	 * @param hm the hm
	 * @return the user
	 */
	public static User fromHashMap(HashMap<String, String> hm) {
		entities.User re= new entities.User(hm.get(config.database.tables.columns.User.USERNAME));
		
		if(hm.get(config.database.tables.columns.User.FIRSTNAME) != null)
			re.setFirstName(hm.get(config.database.tables.columns.User.FIRSTNAME));
		if(hm.get(config.database.tables.columns.User.LASTNAME) != null)
			re.setLastName(hm.get(config.database.tables.columns.User.LASTNAME));
		if(hm.get(config.database.tables.columns.User.PHONENUMBER) != null)
			re.setPhoneNumber(hm.get(config.database.tables.columns.User.PHONENUMBER));
		if(hm.get(config.database.tables.columns.User.EMAIL) != null)
			re.setEmail(hm.get(config.database.tables.columns.User.EMAIL));
		if(hm.get(config.database.tables.columns.User.PASSWORD) != null)
			re.setPassword(hm.get(config.database.tables.columns.User.PASSWORD));
		if(hm.get(config.database.tables.columns.User.PREMISSION) != null)
			re.setPermission(new Permission(Long.parseLong(hm.get(config.database.tables.columns.User.PREMISSION))));
		

		if(hm.get(config.database.tables.columns.User.CREATED) != null)
			re.setCreated(hm.get(config.database.tables.columns.User.CREATED));
		return re;
	}
	
}
