package client;

import java.io.IOException;
import entities.Packet;
import guiControllers.LoadingController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * The Class ResponseHandler.
 * Static class
 * Main response handler
 */
public class ResponseHandler {

	/**
	 * Override public contractor to make it static.
	 */
	private ResponseHandler() {}
	
	/**
	 * Response handler.
	 * Get packet and send it to sub-response handler to continue execute according to window value.
	 * @param packet the packet
	 */
	public static void responseHandler(Packet<?> pkt) {
		LoadingController.instance.hide();
		System.out.println("ResponseHandler: package is entered processing");
		try {
			if(pkt.getAction() != config.packetTransfer.Actions.ACTION_TYPE.RESPONSE_SUCCESS) errorHandler(pkt);
				switch(pkt.getWindow()) {
					case config.packetTransfer.actions.City.WINDOW_ECITCITY:
						client.responseHandler.City.responseHandler(pkt);
					break;
					default:
						errorHandler(pkt);
					break;
				}
				System.out.println("ResponseHandler: package processing successful");
			}catch(IOException iex) {
				System.out.println("ResponseHandler: error " + iex.toString());
			}
	}
	
	/**
	 * Error handler.
	 * Show to user the alert window box.
	 * @param packet the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void errorHandler(Packet<?> pkt) throws IOException {
		String errorMSG = pkt.objectTranslationElem();
		Alert alert = new Alert(AlertType.ERROR, errorMSG, ButtonType.OK);
		alert.showAndWait();
	}
}
