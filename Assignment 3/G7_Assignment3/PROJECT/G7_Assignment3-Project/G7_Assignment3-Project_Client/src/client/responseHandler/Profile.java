/*
 * this class extends AbstractResponse 
 * it defines the next action according to the response status
 */

package client.responseHandler;

import java.io.IOException;
import java.util.ArrayList;
import entities.AbstractJsonToString;
import entities.Packet;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import guiControllers.Profile.UpdateCreditController;
import guiControllers.Profile.UpdatePasswordController;
import javafx.stage.Stage;

/**
 * The response handler of Profile.
 * Static class 
 */
public class Profile extends AbstractResponse{
	
	/**
	 * Instantiates a new profile.
	 *
	 * @param window the window
	 */
	public Profile(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_ACCOUNT_DETAILS:
				Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show("Profile Details Successfully Updated!","success",s);
				
			break;
			case config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_ACCOUNT_PASSWORD:
				s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				UpdatePasswordController.instace.close_window();
				notification.show("Password Successfully Updated!","success",s);
				
			break;
			case config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_PAYMENT_METHOD:
				s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				UpdateCreditController.instance.close_window();
				notification.show("Credit Card Successfully Updated!","success",s);
				
			break;
			case config.packetTransfer.actions.Profile.SUB_ACTION_GET_ALL_PURCHASES:
				res = pkt.objectTranslation();
				ArrayList<entities.Purchase> respur = new ArrayList<entities.Purchase>();
				for(String json : res)
					respur.add(AbstractJsonToString.toObject(entities.Purchase.class, json));
				guiControllers.Profile.ProfileController.instance.update_purchase_list(respur);
			break;
			
				
			default:
				return false;
		}	
		return true;
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#errorHandler(entities.Packet)
	 */
	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_ACCOUNT_DETAILS:
			
			break;
			
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
