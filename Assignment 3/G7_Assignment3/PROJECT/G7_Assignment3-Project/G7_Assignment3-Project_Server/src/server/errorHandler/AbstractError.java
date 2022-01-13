package server.errorHandler;
import entities.Packet;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractError.
 * abstract class
 * 
 */
/*All classes that extend this class ,define an error handler for each action in the system according to the action type  
 *(get,add,delete,update)
 * */

public abstract class AbstractError {
	
	/** The window. */
	protected String window;
	
	/**
	 * Instantiates a new abstract error.
	 *
	 * @param window the window
	 */
	public AbstractError(String window) {
		this.window = window;
	}

	/**
	 * Handle.
	 *
	 * @param pkt the pkt
	 * @return the packet
	 */
	public Packet<String> handle(Packet<String> pkt){
			switch(pkt.getAction()) {
			case GET:
				return getAction(pkt);
			case ADD:
				return addAction(pkt);
			case UPDATE:
				return updateAction(pkt);
			case DELETE:
				return deleteAction(pkt);
			default:
				return pkt;
		}
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbstractError)
			return ((AbstractError)obj).getWindow().equals(this.getWindow());
		return false;
	}


	/**
	 * Gets the window.
	 *
	 * @return the window
	 */
	public String getWindow() {
		return window;
	}


	/**
	 * Sets the window.
	 *
	 * @param window the new window
	 */
	public void setWindow(String window) {
		this.window = window;
	}
	
	/**
	 * Gets the action.
	 *
	 * @param pkt the pkt
	 * @return the action
	 */
	protected abstract Packet<String> getAction(Packet<String> pkt);
	
	/**
	 * Delete action.
	 *
	 * @param pkt the pkt
	 * @return the packet
	 */
	protected abstract Packet<String> deleteAction(Packet<String> pkt);
	
	/**
	 * Adds the action.
	 *
	 * @param pkt the pkt
	 * @return the packet
	 */
	protected abstract Packet<String> addAction(Packet<String> pkt);
	
	/**
	 * Update action.
	 *
	 * @param pkt the pkt
	 * @return the packet
	 */
	protected abstract Packet<String> updateAction(Packet<String> pkt);
	
}
