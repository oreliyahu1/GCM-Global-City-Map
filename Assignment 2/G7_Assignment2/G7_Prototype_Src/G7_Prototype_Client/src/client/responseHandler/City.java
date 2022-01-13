package client.responseHandler;

import java.io.IOException;
import java.util.ArrayList;

import entities.AbstractJsonToString;
import entities.Packet;

/**
 * The response handler of city.
 * Static class 
 */
public class City {
	
	/**
	 * Override public contractor to make it static.
	 */
	private City(){}
	
	/**
	 * Response handler.
	 * Get packet from main response handler,and process received date.
	 * @param pkt the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void responseHandler(Packet<?> pkt) throws  IOException{
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.City.SUB_ACTION_GET_CITIES_NAMES:
				guiControllers.Window.CityController.instance.response_setCitiesNames(pkt.objectTranslation());
			break;
			case config.packetTransfer.actions.City.SUB_ACTION_GET_CITY:
				ArrayList<String> res = pkt.objectTranslation();
				guiControllers.Window.CityController.instance.response_setCity(AbstractJsonToString.toObject(entities.City.class, res.get(0)));	
			break;		
			case config.packetTransfer.actions.City.SUB_ACTION_ADD_CITY:
				client.requestHandler.City.getAllCityNames();
			break;	
			case config.packetTransfer.actions.City.SUB_ACTION_DELETE_CITY:
				client.requestHandler.City.getAllCityNames();
			break;
			case config.packetTransfer.actions.City.SUB_ACTION_EDIT_CITY:
				client.requestHandler.City.getAllCityNames();
			break;
			default:
				throw new IOException();
		}
	}
}
