package server.database;

import entities.Packet.ACTION_TYPE;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseException.
 */
public class ResponseException extends Exception{

	/** The error. */
	private ACTION_TYPE error;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new response exception.
	 *
	 * @param at the at
	 */
	public ResponseException(ACTION_TYPE at) {
		super(at.toString() + " db results error");
		this.error = at;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public ACTION_TYPE getError() {
		return error;
	}
}
