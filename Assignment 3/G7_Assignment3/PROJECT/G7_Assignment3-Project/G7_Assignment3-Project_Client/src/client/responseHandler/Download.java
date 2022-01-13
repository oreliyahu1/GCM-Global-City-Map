/*
 * this class extends AbstractResponse 
 * it defines the next action according to the response status
 */

package client.responseHandler;

import java.io.IOException;
import java.util.ArrayList;
import entities.AbstractJsonToString;
import entities.Packet;



/**
 * The response handler of Download.
 * Static class 
 */
public class Download extends AbstractResponse{
	
	/**
	 * Instantiates a new download.
	 *
	 * @param window the window
	 */
	public Download(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			
			case config.packetTransfer.actions.Download.SUB_ACTION_GET_CITY_MAPS:
				res = pkt.objectTranslation();
				ArrayList<entities.Map> maps = new ArrayList<entities.Map>();
				for(String json : res)
					maps.add(AbstractJsonToString.toObject(entities.Map.class, json));
				guiControllers.subGui.DownloadLoadingController.instance.get_all_maps_of_city(maps);
			break;
			case config.packetTransfer.actions.Download.SUB_ACTION_GET_FULL_MAP_BY_CITY:
				res = pkt.objectTranslation();
				guiControllers.subGui.DownloadLoadingController.instance.add_fullmap_to_download((AbstractJsonToString.toObject(entities.Map.class, res.get(0))));
			break;
			case config.packetTransfer.actions.Download.SUB_ACTION_GET_POISMAP_BY_MAP:
				res = pkt.objectTranslation();
				ArrayList<entities.PlaceOfInterestMap> places = new ArrayList<entities.PlaceOfInterestMap>();
				for(String json : res)
					places.add(AbstractJsonToString.toObject(entities.PlaceOfInterestMap.class, json));
				guiControllers.subGui.DownloadLoadingController.instance.get_all_pois_maps(places);
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
		case config.packetTransfer.actions.Download.SUB_ACTION_GET_CITY_MAPS:
			
		break;
	
			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
