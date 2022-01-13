package entities;

// TODO: Auto-generated Javadoc
/**
 * The Class Request.
 */
public abstract class AbstractRequest extends AbstractJsonToString {
	
	/**
	 * The Enum STATUS.
	 */
	public enum STATUS{
		
		/** The waiting. */
		WAITING,
		
		/** The approve. */
		APPROVE,
		
		/** The reject. */
		REJECT;
	}
	
	/** The id. */
	private int id;
	
	/** The user. */
	private User user;
	
	/** The status. */
	private STATUS status;
	
	/** The date. */
	private String date;
	
	/**
	 * Instantiates a new abstract request.
	 */
	protected AbstractRequest() {}

	/**
	 * Instantiates a new abstract request.
	 *
	 * @param id the id
	 * @param user the user
	 * @param status the status
	 * @param date the date
	 */
	public AbstractRequest(int id, User user, STATUS status, String date) {
		this.id = id;
		this.user = user;
		this.status = status;
		this.date = date;
	}

	/**
	 * Instantiates a new abstract request.
	 *
	 * @param user the user
	 */
	public AbstractRequest(User user) {
		this.user = user;
		this.status = STATUS.WAITING;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public STATUS getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(STATUS status) {
		this.status = status;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
}
