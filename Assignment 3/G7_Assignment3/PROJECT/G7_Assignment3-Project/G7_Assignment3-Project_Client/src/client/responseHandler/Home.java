/*
 * this class extends AbstractResponse 
 * it defines the next action according to the response status
 */

package client.responseHandler;

import java.io.IOException;
import java.util.ArrayList;

import animatefx.FadeOutUp;
import entities.AbstractJsonToString;
import entities.Packet;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.Messages.notification;
import guiControllers.Registeration.LoginController;
import javafx.stage.Stage;

/**
 * The response handler of home.
 * Static class 
 */
public class Home extends AbstractResponse{
	
	/**
	 * Instantiates a new home.
	 *
	 * @param window the window
	 */
	public Home(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.Home.SUB_ACTION_GET_LOGIN:
				ArrayList<String> res = pkt.objectTranslation();
				guiControllers.HomeController.instance.login(AbstractJsonToString.toObject(entities.User.class, res.get(0)));
			break;
			case config.packetTransfer.actions.Home.SUB_ACTION_UPDATE_LOGOUT:
				guiControllers.HomeController.instance.logout();
				Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
	    		notification.show("Logged out Successfully", "success", s);
			break;
			case config.packetTransfer.actions.Home.SUB_ACTION_ADD_REGISTER:
				//register
				
				FadeOutUp anim1 = new FadeOutUp(HomeController.instance.PANE);
				anim1.play();
				anim1.setResetOnFinished(true);
				anim1.setOnFinished(event1 -> {
					// SceneController.push(PANE);
					LoginController LookupForm = new LoginController();
					try {
						LookupForm.start(HomeController.instance.PANE);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					Stage sm = (Stage)HomeController.instance.PANE.getScene().getWindow();
		    		notification.show("You have Register Successfully! Please login now", "success", sm);
				});
				SceneController.delete();
				
				
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
			case config.packetTransfer.actions.Home.SUB_ACTION_GET_LOGIN:
				Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show(pkt.getData(),"error",s);
				//error login
			break;
			case config.packetTransfer.actions.Home.SUB_ACTION_UPDATE_LOGOUT:
				guiControllers.HomeController.instance.logout();
				alertError(pkt);
			break;
			case config.packetTransfer.actions.Home.SUB_ACTION_ADD_REGISTER:
				//error register
				s = (Stage)HomeController.instance.PANE.getScene().getWindow();
	    		notification.show(pkt.getData(), "error", s);
				
			break;
	
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
