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
import guiControllers.Manage_ClientsController;
import guiControllers.Messages.notification;
import javafx.stage.Stage;


/**
 * The response handler of Manageclients.
 * Static class 
 */
public class ManageClients extends AbstractResponse{
	
	/**
	 * Instantiates a new manage clients.
	 *
	 * @param window the window
	 */
	public ManageClients(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USERS:
				res = pkt.objectTranslation();
				ArrayList<entities.User> users = new ArrayList<entities.User>();
				for(String json : res)
					users.add(AbstractJsonToString.toObject(entities.User.class, json));
				guiControllers.Manage_ClientsController.instance.update_users(users);
			break;
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_DETAILS:
				Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				Manage_ClientsController.instance.refresh_users();
				notification.show("User Details Successfully Updated!","success",s);
			break;
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_PASSWORD:
				s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show("User Password Successfully Updated!","success",s);
			break;
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PAYMENT_METHOD:
				s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				Manage_ClientsController.instance.refresh_users();
				notification.show("User Credit Card Successfully Updated!","success",s);
			break;
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USER_PURCHASES:
				res = pkt.objectTranslation();
				ArrayList<entities.Purchase> userpur = new ArrayList<entities.Purchase>();
				for(String json : res)
					userpur.add(AbstractJsonToString.toObject(entities.Purchase.class, json));
				guiControllers.Profile.PurchaseController.instance.refresh_purchase_list(userpur);
			break;
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PERMISSION:
				s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				Manage_ClientsController.instance.refresh_users();
				notification.show("Permission Successfully Updated!","success",s);
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
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USERS:
			
			break;
	
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
