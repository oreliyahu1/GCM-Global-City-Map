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
import guiControllers.SetRates_Controller;
import guiControllers.Messages.notification;
import javafx.stage.Stage;

/**
 * The response handler of Manage Requests.
 * Static class 
 */
public class ManageRequests extends AbstractResponse{
	
	/**
	 * Instantiates a new manage requests.
	 *
	 * @param window the window
	 */
	public ManageRequests(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_VERSION_REQUESTS:
				res = pkt.objectTranslation();
				ArrayList<entities.VersionRequest> resreq = new ArrayList<entities.VersionRequest>();
				for(String json : res)
					resreq.add(AbstractJsonToString.toObject(entities.VersionRequest.class, json));
				guiControllers.Manage_RequestsController.instance.update_version_requests(resreq);
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATE_REQUESTS:
				res = pkt.objectTranslation();
				ArrayList<entities.RateRequest> resreqrate = new ArrayList<entities.RateRequest>();
				for(String json : res)
					resreqrate.add(AbstractJsonToString.toObject(entities.RateRequest.class, json));
				guiControllers.Manage_RequestsController.instance.update_rate_requests(resreqrate);
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_RATE_REQUEST_RESPONSE:
				res = pkt.objectTranslation();
				ArrayList<entities.RateRequest> userrequests = new ArrayList<entities.RateRequest>();
				for(String json : res)
					userrequests.add(AbstractJsonToString.toObject(entities.RateRequest.class, json));
				guiControllers.Manage_RequestsController.instance.update_myrate_requests(userrequests);
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_VERSION_REQUEST_RESPONSE:
				res = pkt.objectTranslation();
				ArrayList<entities.VersionRequest> uservreq = new ArrayList<entities.VersionRequest>();
				for(String json : res)
					uservreq.add(AbstractJsonToString.toObject(entities.VersionRequest.class, json));
				guiControllers.Manage_RequestsController.instance.update_myversion_requests(uservreq);
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_VERSION_REQUEST:
				Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Manage_RequestsController.instance.refresh_versions();
				notification.show("The Version Request is updated!", "green", s);
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_RATE_REQUEST:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Manage_RequestsController.instance.refresh_rates();
				notification.show("The Rate Request is updated!", "green", s);
			break;
			
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_RATE_REQUEST:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Manage_RequestsController.instance.refresh_myratesrequests();
				notification.show("The Rate Request is Deleted!", "green", s);
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_VERSION_REQUEST:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Manage_RequestsController.instance.refresh_myversionrequests();
				notification.show("The Version Request is Deleted!", "green", s);
				
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_ONETIME_RATE_REQUEST:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				SetRates_Controller.instance.close_window();
				guiControllers.Manage_RequestsController.instance.refresh_myratesrequests();
				notification.show("The One Time Rate Request is Added and Waiting for Approval!", "green", s);
				
				
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_SUBSCRIPTION_RATE_REQUEST:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				notification.show("The SUBSCRIBTION Request is Added and Waiting for Approval!", "green", s);
				SetRates_Controller.instance.close_window();
				guiControllers.Manage_RequestsController.instance.refresh_myratesrequests();
			break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_RATE:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Manage_RequestsController.instance.refresh_myratesrequests();
				notification.show("The Rate Request Has been Setted Successfully!", "green", s);
				break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Manage_RequestsController.instance.refresh_myversionrequests();
				notification.show("The Version Request Has been Setted Successfully!", "green", s);
				break;
			case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATES:
				res = pkt.objectTranslation();
				ArrayList<entities.Rate> resrates = new ArrayList<entities.Rate>();
				for(String json : res)
					resrates.add(AbstractJsonToString.toObject(entities.Rate.class, json));
				guiControllers.SetRates_Controller.instance.refresh_rates(resrates);
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
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_RATE:

			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_VERSION_REQUESTS:
			
			break;
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
