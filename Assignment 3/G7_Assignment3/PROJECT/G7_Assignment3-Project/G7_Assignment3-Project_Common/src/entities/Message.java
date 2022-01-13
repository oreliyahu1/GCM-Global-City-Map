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
public class Message extends AbstractJsonToString {
	
	/** The id. */
	private int id;
	
	/** The to. */
	private User to;
	
	/** The from. */
	private User from;
	
	/** The subject. */
	private String subject;
	
	/** The message. */
	private String message;
	
	/** The date. */
	private String date;
	
	/** The is read. */
	private boolean is_read;
	
	/**
	 * Instantiates a new Message
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Message() { super(); }
	
	
	/**
	 * Instantiates a new message.
	 *
	 * @param id the id
	 * @param to the to
	 * @param from the from
	 * @param subject the subject
	 * @param message the message
	 * @param date the date
	 * @param is_read the is read
	 */
	public Message(int id, User to, User from, String subject, String message, String date, boolean is_read) {
		this.id = id;
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.date = date;
		this.is_read=is_read;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param to the to
	 * @param from the from
	 * @param subject the subject
	 * @param message the message
	 */
	public Message(User to, User from, String subject, String message) {
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.message = message;
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
	 * Gets the to.
	 *
	 * @return the to
	 */
	public User getTo() {
		return to;
	}


	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(User to) {
		this.to = to;
	}


	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public User getFrom() {
		return from;
	}


	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(User from) {
		this.from = from;
	}


	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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


	/**
	 * Checks if is checks if is read.
	 *
	 * @return true, if is checks if is read
	 */
	public boolean isIs_read() {
		return is_read;
	}


	/**
	 * Sets the checks if is read.
	 *
	 * @param is_read the new checks if is read
	 */
	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Message) {
			return this.getId() == ((Message)obj).getId();
		}
		return false;
	}
	
	/**
	 * From hash map.
	 *  Get hash map and create Message entity
	 * @param hm the hm
	 * @return the message
	 */
	public static Message fromHashMap(HashMap<String, String> hm) {
		Message mre = new Message(new User(hm.get(config.database.tables.columns.Message.TO)),
				new User(hm.get(config.database.tables.columns.Message.FROM)),
				hm.get(config.database.tables.columns.Message.SUBJECT),
				hm.get(config.database.tables.columns.Message.MESSAGE));
		
		if(hm.get(config.database.tables.columns.Message.ID) != null)
			mre.setId(Integer.parseInt(hm.get(config.database.tables.columns.Message.ID)));
		if(hm.get(config.database.tables.columns.Message.DATE) != null)
			mre.setDate(hm.get(config.database.tables.columns.Message.DATE));
		if(hm.get(config.database.tables.columns.Message.IS_READ) != null)
			mre.setIs_read(Integer.parseInt(hm.get(config.database.tables.columns.Message.IS_READ))==1);
		return mre;
	}
	
}
