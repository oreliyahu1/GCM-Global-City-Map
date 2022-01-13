/*
 * AbstractResponse an abstract class , 
 * calls the corresponding function according to the response status returned with the packet from the server 
 */
package client.responseHandler;

import java.io.IOException;

import entities.Packet;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import javafx.stage.Stage;


/**
 * The Class AbstractResponse.
 */
public abstract class AbstractResponse {
	
	/** The window. */
	private String window;
	
	/**
	 * Instantiates a new abstract response.
	 *
	 * @param window the window
	 */
	public AbstractResponse(String window) {
		this.window = window;
	}

	/**
	 * execute
	 * Get packet from main response handler,and process received date.
	 *
	 * @param pkt the packet
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean execute(Packet<?> pkt) throws IOException{
		switch(pkt.getAction()) {
			case RESPONSE_SUCCESS:
				responseHandler(pkt);
			break;
			case RESPONSE_ERROR:
				errorHandler(pkt);
			break;
			default:
				return false;
		}
		return true;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbstractResponse)
			return ((AbstractResponse)obj).getWindow().equals(this.getWindow());
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
	 * Alert error.
	 *
	 * @param pkt the pkt
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void alertError(Packet<?> pkt) throws IOException{
		
		Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
		notification.show(pkt.getData(), "error", s);
		
		/*Alert alert = new Alert(AlertType.ERROR, pkt.getData(), ButtonType.OK);
		alert.showAndWait();*/
		
	}
	
	/**
	 * Response handler.
	 * Get packet from main response handler,and process received date.
	 * @param pkt the packet
	 * @return boolean if excute
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract boolean responseHandler(Packet<?> pkt) throws IOException;
	
	/**
	 * Error handler.
	 * Show to user the alert window box.
	 *
	 * @param pkt the pkt
	 * @return boolean if excute
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract boolean errorHandler(Packet<?> pkt) throws IOException;
	
}
