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
import guiControllers.Profile.ProfileController;
import javafx.stage.Stage;

/**
 * The response handler of purchases.
 * Static class 
 */
public class Purchase extends AbstractResponse{
	
	public Purchase(String window) {
		super(window);
	}

	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_PURCHASE:
				Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show("You have Successfully Purchased the City!","success",s);
				
				
			break;
			case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_RATES:
				res = pkt.objectTranslation();
				ArrayList<entities.Rate> resrates = new ArrayList<entities.Rate>();
				for(String json : res)
					resrates.add(AbstractJsonToString.toObject(entities.Rate.class, json));
				guiControllers.Subscribe.SubscribeController.instace.refresh_rates(resrates);
			break;
			case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_PAYMENT_DETAILS:
				res = pkt.objectTranslation();
				if(!res.isEmpty())
					guiControllers.Subscribe.SubscribeController.instace.get_payment(AbstractJsonToString.toObject(entities.CreditCard.class, res.get(0)));
			break;
			case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_RENEWAL:
				s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show("You Have Successfully Renewed your Purchase!","success",s);
				ProfileController.instance.refresh_purchases();
				break;
			/*case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_USER_UNEXPIRED_PURCHASES:
				res = pkt.objectTranslation();
				ArrayList<entities.Purchase> unexp_purch = new ArrayList<entities.Purchase>();
				for(String json : res)
					unexp_purch.add(AbstractJsonToString.toObject(entities.Purchase.class, json));
				guiControllers.Catalog.CitiesController.instance.check_subscribtion_to_enter_city(unexp_purch);
			break;*/
			default:
				return false;
		}	
		return true;
	}

	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_PURCHASE:
			//error adding purchase
		break;
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_RATES:
		break;
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
