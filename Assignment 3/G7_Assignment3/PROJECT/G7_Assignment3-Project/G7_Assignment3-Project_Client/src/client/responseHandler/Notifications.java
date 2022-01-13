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
import javafx.stage.Stage;

/**
 * The response handler of Notification.
 * Static class 
 */
public class Notifications extends AbstractResponse{
	
	/**
	 * Instantiates a new notifications.
	 *
	 * @param window the window
	 */
	public Notifications(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.Notification.SUB_ACTION_GET_ALL_NOTIFICATIONS:
				res = pkt.objectTranslation();
				ArrayList<entities.Message> resMessages = new ArrayList<entities.Message>();
				for(String json : res)
					resMessages.add(AbstractJsonToString.toObject(entities.Message.class, json));
				guiControllers.Notification_Controller.instance.getAllnotifications(resMessages);
			break;
			case config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_NOTIFICATION:
				Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Notification_Controller.instance.refresh_notifications();
				notification.show("the Message was Deleted.", "green", s);
			break;
			case config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_ALL_NOTIFICATIONS:
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
				guiControllers.Notification_Controller.instance.refresh_notifications();
				notification.show("ALL MESSAGES WERE DELETED!", "green", s);
			break;
			case config.packetTransfer.actions.Notification.SUB_ACTION_UPDATE_READ_NOTIFICATION:
				String current_num = HomeController.instance.Notification_label.getText();
				HomeController.instance.Notification_label.setText(String.valueOf(Integer.parseInt(current_num) -1));
				if((Integer.parseInt(current_num)-1) == 0)
					HomeController.instance.Notification_label.setVisible(false);
				//nothing to do
			break;
			case config.packetTransfer.actions.Notification.SUB_ACTION_GET_NEW_NOTIFICATIONS:
				res = pkt.objectTranslation();
				ArrayList<entities.Message> allnewmsgs = new ArrayList<entities.Message>();
				for(String json : res)
					allnewmsgs.add(AbstractJsonToString.toObject(entities.Message.class, json));
				guiControllers.HomeController.instance.update_notifications(allnewmsgs);
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
		case config.packetTransfer.actions.Notification.SUB_ACTION_GET_ALL_NOTIFICATIONS:
			
			break;
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
