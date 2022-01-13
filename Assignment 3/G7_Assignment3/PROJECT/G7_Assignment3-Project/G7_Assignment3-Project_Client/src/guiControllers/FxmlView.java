package guiControllers;

import client.requestHandler.Catalog;
import guiControllers.Catalog.SelectedCity_ToursPlacesController;
import guiControllers.Catalog.SelectedMap_CityController;


/**
 * The Enum FxmlView. to read fxml path and titles for each scene
 */
public enum FxmlView {

	/** The catalog. */
	CATALOG {
		@Override
		public String getTitle() {
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog";
			return "GCM -> Catalog";
		}

		@Override
		public String getFXML() {
			return "/Catalog/Catalog.fxml";
		}
	},
	
	/** The login. */
	LOGIN {

		@Override
		public String getTitle() {
			return "GCM -> Login";
		}

		@Override
		public String getFXML() {
			return "/Registeratin/Login.fxml";
		}

	},
	
	/** The loading. */
	LOADING {

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "Loading";
		}

		@Override
		public String getFXML() {
			return "/subGui/Loading.fxml";
		}

	},
	
	/** The cities. */
	CITIES{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName();
			return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName();
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/Cities.fxml";
		}
		
		
	},
	
	/** The register. */
	REGISTER{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Sign Up";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Registeratin/Register.fxml";
		}
		
		
	},
	
	/** The buy. */
	BUY{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> SubScribe";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/subscribe/Buy.fxml";
		}
		
		
	},
	
	/** The profile. */
	PROFILE{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Profile";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/profile/clientdetails.fxml";
		}
		
		
	},
	
	/** The city selected. */
	CITY_SELECTED{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName() + " -> "
						+ GeneralValues.CITY.getName();
			return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName();
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/city_selected.fxml";
		}
		
		
	},
	
	/** The city selected map. */
	CITY_SELECTED_MAP{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName() + " -> "
						+ GeneralValues.CITY.getName() + " -> "
						+ SelectedMap_CityController.instance.selected_map.getName();
			return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName()
					+ " -> " + SelectedMap_CityController.instance.selected_map.getName();
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/city_selected_map.fxml";
		}
		
		
	},
	
	/** The payment. */
	PAYMENT{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Sign Up -> Payment";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Registeratin/Payment.fxml";
		}
		
		
	},
	
	/** The downloads. */
	DOWNLOADS{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Downloads";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Downloads.fxml";
		}
		
		
	},
	
	/** The city selected places. */
	CITY_SELECTED_PLACES{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName()
						+ " -> Places";
			return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName()
					+ " -> Places";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/city_selected_places.fxml";
		}
		
		
	},
	
	/** The city selected tours. */
	CITY_SELECTED_TOURS{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName() + " -> "
						+ GeneralValues.CITY.getName() + " -> Tours";
			return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName()
					+ " -> Tours";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/city_selected_tours.fxml";
		}
		
		
	},
	
	/** The city selected tours places. */
	CITY_SELECTED_TOURS_PLACES{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName() + " -> "
						+ GeneralValues.CITY.getName() + " -> Tours -> "
						+ SelectedCity_ToursPlacesController.selected_tour.getName();
			return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName()
					+ " -> Tours -> " + SelectedCity_ToursPlacesController.selected_tour.getName();
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/city_selected_tours_places.fxml";
		}
		
		
	},
	
	/** The city selected addtours places. */
	CITY_SELECTED_ADDTOURS_PLACES{

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			if (Catalog.getMode().equals(Catalog.MODE.EDIT))
				return "GCM -> Edit Catalog -> " + GeneralValues.COUNTRY.getName() + " -> "
						+ GeneralValues.CITY.getName() + " -> Tours -> New Tour";
			return "GCM -> Catalog -> " + GeneralValues.COUNTRY.getName() + " -> " + GeneralValues.CITY.getName()
					+ " -> Tours -> New Tour";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/city_selected_Addtours_places.fxml";
		}
		
		
	},/** The manage clients. */
MANAGE_CLIENTS{
		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Manage Users";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Manage_clients.fxml";
		}
	},
/** The manage requests. */
MANAGE_REQUESTS{
		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Manage Requests";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Manage_requests.fxml";
		}
	},
/** The notifications. */
NOTIFICATIONS{
		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Notifications";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Notifications.fxml";
		}
	},
/** The reports. */
REPORTS{
		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Reports";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Reports.fxml";
		}
	},
/** The advancedsearcher. */
ADVANCEDSEARCHER{
		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return "GCM -> Catalog -> Advanced Search";
		}

		@Override
		public String getFXML() {
			// TODO Auto-generated method stub
			return "/Catalog/Map_Searcher.fxml";
		}
	};
	


	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public abstract String getTitle();

	/**
	 * Gets the fxml.
	 *
	 * @return the fxml
	 */
	public abstract String getFXML();
}
