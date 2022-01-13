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
import guiControllers.Catalog.Selected_CityController;
import guiControllers.CatalogEdit.AddMap_Controller;
import guiControllers.CatalogEdit.Mapcollection_Controller;
import guiControllers.Messages.notification;
import javafx.stage.Stage;

/**
 * The response handler of EditCatalog.
 */
public class EditCatalog extends Catalog{
	
	/**
	 * Instantiates a new edits the catalog.
	 *
	 * @param window the window
	 */
	public EditCatalog(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.Catalog#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_window()) {
		case "":
			if(!super.responseHandler(pkt)) {
				switch(pkt.getSub_action()) {
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_MAP:
					//close window
					guiControllers.Catalog.SelectedMap_CityController.instance.refreshPoiMap();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Place was Deleted Successfully from Map!","success",s);
		
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CITY_TO_COUNTRY:
					//close window
					guiControllers.CatalogEdit.AddCity_Controller.instance.close_window();
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("City was Successfully added!","success",s);
					guiControllers.Catalog.CitiesController.instance.refreshCities();
		
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CITY_OF_COUNTRY:
					//close window
					guiControllers.CatalogEdit.EditCity_Controller.instance.close_window();
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("City was Successfully Updated!","success",s);
					
					guiControllers.Catalog.CitiesController.instance.refreshCities();
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST:
					//close window
					System.out.println("pushed map collection!");
					guiControllers.CatalogEdit.Mapcollection_Controller.instance.close_window();
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Maps Collection was Pushed Successfully and the Request is waiting a Manager Response!","success",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_LAST_COLLECTION_VERSION:
					//close window
					res = pkt.objectTranslation();
					Mapcollection_Controller.instance.setVersion(AbstractJsonToString.toObject(entities.City.class, res.get(0)));
				
					break;
	
				default:
					return false;
				}
			}
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP:
			
			switch(pkt.getSub_action()) {
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_CITY:
					res = pkt.objectTranslation();
					ArrayList<entities.PlaceOfInterest> POIS_OF_CITY = new ArrayList<entities.PlaceOfInterest>();
					for(String json : res)
						POIS_OF_CITY.add(AbstractJsonToString.toObject(entities.PlaceOfInterest.class, json));
					guiControllers.CatalogEdit.AddPlaceToMapController.instance.update_places(POIS_OF_CITY);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_MAP:
					//close window
					guiControllers.CatalogEdit.AddPlaceToMapController.instance.close_window();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Place was Added Successfully!","success",s);
					guiControllers.Catalog.SelectedMap_CityController.instance.refreshPoiMap();
					
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_MAP_FROM_CITY:
					//close window
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Map was Successfully Deleted from the City!","success",s);
					guiControllers.Catalog.Selected_CityController.instance.refresh_maps();
					
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_CITY_MAPS:
					res = pkt.objectTranslation();
					ArrayList<entities.Map> MAPS_CITY = new ArrayList<entities.Map>();
					for(String json : res)
						MAPS_CITY.add(AbstractJsonToString.toObject(entities.Map.class, json));
					guiControllers.CatalogEdit.AddMap_Controller.instance.update_maps_items(MAPS_CITY);
					
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_FULL_MAP_OF_CITY:
					res = pkt.objectTranslation();
					ArrayList<entities.Map> map = new ArrayList<entities.Map>();
					for(String json : res)
						map.add(AbstractJsonToString.toObject(entities.Map.class, json));
					guiControllers.CatalogEdit.AddMap_Controller.instance.get_image_of_map(map.get(0));
				
					
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_EXTERNAL_FULL_MAP:
					res = pkt.objectTranslation();
					ArrayList<entities.Map> map_image = new ArrayList<entities.Map>();
					for(String json : res)
						map_image.add(AbstractJsonToString.toObject(entities.Map.class, json));
					guiControllers.CatalogEdit.AddMap_Controller.instance.get_image_of_map(map_image.get(0));
					/*guiControllers.CatalogEdit.AddMap_Controller.instance.close_window();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("New Map succesfully Added to the City!","success",s);*/
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_EXTERNAL_MAPS_BY_CITY:
					//close window
					res = pkt.objectTranslation();
					ArrayList<entities.Map> maps = new ArrayList<entities.Map>();
					for(String json : res)
						maps.add(AbstractJsonToString.toObject(entities.Map.class, json));
					guiControllers.CatalogEdit.AddMap_Controller.instance.update_maps_items(maps);
				
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY:
					//close window
					guiControllers.CatalogEdit.AddMap_Controller.instance.close_window();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					AddMap_Controller.instance.close_window();
					Selected_CityController.instance.refresh_maps();
					notification.show("New Map succesfully Added to the City!","success",s);
				
					break;

				default:
					break;
				
			}
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI:
			
			switch(pkt.getSub_action()) {
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_CITY:
					//close window
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Place was added to the City!","success",s);
					guiControllers.Catalog.SelectedCity_PlacesController.instance.refreshPOI();
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_CITY:
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Place was Deleted from the City!","success",s);
					guiControllers.Catalog.SelectedCity_PlacesController.instance.refreshPOI();
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_POI_OF_CITY:
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Place was Updated Successfully!","success",s);
					guiControllers.Catalog.SelectedCity_PlacesController.instance.refreshPOI();
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES:
					//close window
					res = pkt.objectTranslation();
					ArrayList<entities.Category> categories = new ArrayList<entities.Category>();
					for(String json : res)
						categories.add(AbstractJsonToString.toObject(entities.Category.class, json));
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.update_categories(categories);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CATEGORY:
					//close window
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("New Category succesfully Added!","success",s);
					
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CATEGORY:
					//close window
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Category was Successfully Updatedd!","success",s);
					
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_CATEGORY:
					//close window
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Category was Successfully Deleted!","success",s);
					
					break;
				default:
					break;
				
			}
			break;
	case config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_TOUR:
			
			switch(pkt.getSub_action()) {
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_CITY:
					res = pkt.objectTranslation();
					ArrayList<entities.PlaceOfInterest> POIS_OF_CITY = new ArrayList<entities.PlaceOfInterest>();
					for(String json : res)
						POIS_OF_CITY.add(AbstractJsonToString.toObject(entities.PlaceOfInterest.class, json));
					guiControllers.Catalog.SelectedCity_ToursPlacesController.instance.update_all_places_add_edit_tour(POIS_OF_CITY);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS:
					guiControllers.CatalogEdit.Edit_Tour_Controller.instance.close_window();
					guiControllers.Catalog.SelectedCity_ToursController.instance.refresh_tours_list();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Tour was succesfully Updated!","success",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR:
					guiControllers.Catalog.SelectedCity_ToursPlacesController.instance.refresh_and_back_to_tourspane();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Tour was succesfully Updated with it's new Places!","success",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_TOUR_FROM_CITY:
					guiControllers.Catalog.SelectedCity_ToursController.instance.refresh_tours_list();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Tour was succesfully Deleted!","success",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_TOUR_TO_CITY:

					guiControllers.Catalog.SelectedCity_ToursPlacesController.instance.refresh_and_back_to_tourspane();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("Tour was succesfully Added!","success",s);
					break;
				default:
					break;
				
			}
			break;
			
			
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.Catalog#errorHandler(entities.Packet)
	 */
	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		switch(pkt.getSub_window()) {
		case "":
			switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST:
				//close window
				guiControllers.CatalogEdit.Mapcollection_Controller.instance.close_window();
	
				break;
			case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CITY_TO_COUNTRY:
				//close window
				guiControllers.CatalogEdit.AddCity_Controller.instance.close_window();
				break;
			default:
				return super.errorHandler(pkt);
			}
		case config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP:
			switch(pkt.getSub_action()) {
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_MAP:
					//close window
					guiControllers.CatalogEdit.AddPlaceToMapController.instance.close_window();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show(pkt.getData(),"error",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_MAP_FROM_CITY:
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show(pkt.getData(),"error",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_FULL_MAP_OF_CITY:
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_EXTERNAL_FULL_MAP:
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_EXTERNAL_MAPS_BY_CITY:
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY:
					//close window
					guiControllers.CatalogEdit.AddMap_Controller.instance.close_window();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show(pkt.getData(),"error",s);
					break;					
				default:
					return super.errorHandler(pkt);
				
			}
			
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI:
			
			switch(pkt.getSub_action()) {
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_CITY:
					//close window
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show(pkt.getData(),"error",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_CITY:
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					 notification.show(pkt.getData(),"error",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_POI_OF_CITY:
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show(pkt.getData(),"error",s);
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES:
					break;
				case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CATEGORY:
					//close window
					guiControllers.CatalogEdit.Add_Edit_Place_Controller.instance.close_window();
					 s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show(pkt.getData(),"error",s);
					
					break;
				default:
					return super.errorHandler(pkt);
			}
		default:
			return super.errorHandler(pkt);
			
		}
		return true;
	}


}
