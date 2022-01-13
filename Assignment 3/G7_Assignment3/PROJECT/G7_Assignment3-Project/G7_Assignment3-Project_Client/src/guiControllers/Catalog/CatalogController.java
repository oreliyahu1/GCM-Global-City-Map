package guiControllers.Catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import animatefx.FadeIn;

import animatefx.FadeOutRight;

import animatefx.FadeOutUp;
import animatefx.SlideInUp;
import client.requestHandler.Catalog;
import entities.Permission;
import guiControllers.FxmlView;
import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.item.countryItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The Class CatalogController.
 */
public class CatalogController {

	/** The instance. - used for external updating from response handler */
	public static CatalogController instance;


	/** The Cities pane. */
	@FXML
	private Pane Cities_pane;
	
	/** The Country pane. */
	@FXML
	private Pane Country_pane;
	
	/** The top pane. */
	@FXML
	private Pane top_pane;

	/** The city selected. */
	@FXML
	private Pane city_selected;

	/** The city selected map pane. */
	@FXML
	private Pane city_selected_map_pane;


	/** The txt cities. */
	@FXML
	private Label txt_cities;

	/** The txt maps. */
	@FXML
	private Label txt_maps;

	/** The txt pois. */
	@FXML
	private Label txt_pois;

	/** The txt tours. */
	@FXML
	private Label txt_tours;
    
    /** The btn advanced search. */
    @FXML
    private Button btn_advanced_search;

	/** The n node for loading. */
	
	private Node[] n = new Node[1];
	
	/** The working collection. */
	private ObservableList<Node> workingCollection;
	
	/** The search text. */
	@FXML
	private TextField search_text;

	/** The pn items. */
	@FXML
	private VBox pnItems;

	/**
	 * The Class NodeComparator. to compare between country names
	 */
	private class NodeComparator implements Comparator<Node> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Node o1, Node o2) {
			countryItem sp1 = (countryItem) o1;
			countryItem sp2 = (countryItem) o2;
			String s1 = (String) sp1.country_name.getText();
			String s2 = (String) sp2.country_name.getText();

			return s1.compareTo(s2);
		}
	}

	/**
	 * Start.
	 * Start the Catalog Controller to show the Countries in a VBox List
	 * @param pane the pane
	 * @param mode the mode
	 * @throws Exception the exception
	 */
	public void start(Pane pane, client.requestHandler.Catalog.MODE mode) throws Exception {

		client.requestHandler.Catalog.setMode(mode);
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CATALOG.getFXML()));

		Node n = null;
		try {
			n = fxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pane.getChildren().clear();
		pane.getChildren().add(n);

		Platform.runLater(() -> {
			HomeController.instance.Set_Title(FxmlView.CATALOG.getTitle());
		});
	}

	   /**
   	 * Btn map searcher click.
   	 * to search for an country in the VBox list
   	 * @param event the event
   	 */
   	@FXML
	    void btn_map_searcher_click(MouseEvent event) {
	
			FadeOutUp anim1 = new FadeOutUp(HomeController.instance.PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				SceneController.push(HomeController.instance.PANE);
				SceneController.push_title(HomeController.instance.Get_Title());
				SceneController.push_command("Catalog");
				MapSearcherController LookupForm = new MapSearcherController();
				try {
					LookupForm.start(HomeController.instance.PANE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	

			});
	    }


	/**
	 * Initialize of the controller. start the counter of all to zero
	 */
	@FXML
	void initialize() {

	
			client.requestHandler.Catalog.getAllCountries();

		
		CatalogController.instance = this;
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource(FxmlView.LOADING.getFXML()));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getPnItems().getChildren().add(n[0]);
	
		getTxt_cities().setText("0");
		getTxt_maps().setText("0");
		getTxt_pois().setText("0");
		getTxt_tours().setText("0");
		
		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			btn_advanced_search.setVisible(false);
		}
	}

	/**
	 * Refresh countries list.
	 */
	public void refresh_countries() {
		client.requestHandler.Catalog.getAllCountries();
	}
	
	/**
	 * Search event when clicked Enter. show the typed country on the first of the VBox list
	 *
	 * @param event the event
	 */
	@FXML
	void search(KeyEvent event) {
		// if text is empty then show in sorted
		ArrayList<countryItem> found = new ArrayList<countryItem>();
		if (getSearch_text().getText().equals("")) {
			try {

				Collections.sort(getWorkingCollection(), new NodeComparator());

				getPnItems().getChildren().setAll(getWorkingCollection());

			} catch (Exception c) {
				System.out.println("Cannot sort");
			}

			return;
		}

		if (event.getCode().equals(KeyCode.ENTER)) {
			Platform.runLater(() -> {
				if (getWorkingCollection() == null)
					return;
				for (Node child : getWorkingCollection()) {
					if (child instanceof countryItem) {
						countryItem sp = (countryItem) child;
						if (sp.country_name.getText().toLowerCase().contains(getSearch_text().getText().toLowerCase())) {
							found.add(sp);
						}
					}
				}
				Collections.sort(getWorkingCollection(), new NodeComparator());
				getPnItems().getChildren().setAll(getWorkingCollection());
				Collections.sort(found, Collections.reverseOrder(new NodeComparator()));
			});

			Platform.runLater(() -> {
				for (countryItem sp : found) {

					sp.toBack();
					sp.setStyle("-fx-background-color : #BDBBC3");
					FadeIn anim1 = new FadeIn(sp);
					anim1.play();
					anim1.setResetOnFinished(true);
					anim1.setOnFinished(event2 -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
				}
			});
		}

	}


/////////////////////// functions for the response handler


	/**
	 * Update counties.
	 * Catalog response handler heads to that method and fill the list with countries
	 * @param countries the countries
	 */
	// this function adds all the counties to the list!
	public void update_counties(ArrayList<entities.Country> countries) {
		// clear all before adding!
		getPnItems().getChildren().clear();

		getTxt_cities().setText("0");
		getTxt_maps().setText("0");
		getTxt_pois().setText("0");
		getTxt_tours().setText("0");
		for (entities.Country c : countries) {
			getTxt_cities().setText(String.valueOf(Integer.parseInt(getTxt_cities().getText()) + c.getNumberOfCities()));
			getTxt_maps().setText(String.valueOf(Integer.parseInt(getTxt_maps().getText()) + c.getNumberOfMaps()));
			getTxt_pois().setText(String.valueOf(Integer.parseInt(getTxt_pois().getText()) + c.getNumberOfPois()));
			getTxt_tours().setText(String.valueOf(Integer.parseInt(getTxt_tours().getText()) + c.getNumberOfTours()));
		}

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Country c : countries) {
					countryItem sp = new countryItem(c);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

						try {
							country_click(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});

					Platform.runLater(() -> {
						getPnItems().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

				FadeOutUp animate = new FadeOutUp(n[0]);
				animate.play();
				animate.setOnFinished(event1 -> {
					Platform.runLater(() -> {
						getPnItems().getChildren().remove(n[0]);
						setWorkingCollection(FXCollections.observableArrayList(getPnItems().getChildren()));
						Collections.sort(getWorkingCollection(), new NodeComparator());
						getPnItems().getChildren().setAll(getWorkingCollection());
						getSearch_text().setDisable(false);
					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}

	/**
	 * Country click. on click start a new scene that contain the cities of selected country
	 *
	 * @param c the c entities.country
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// when click on country
	private void country_click(entities.Country c) throws IOException {

		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			SceneController.push_command("Catalog");
			CitiesController LookupForm = new CitiesController();
			try {
				GeneralValues.COUNTRY = c;
				LookupForm.start(HomeController.instance.PANE);
				
				if(HomeController.instance.user == null) {
					CitiesController.instance.refreshCities();
				}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT)) {
					client.requestHandler.Catalog.get_all_purchases_in_country_for_user(c.getShortName(), HomeController.instance.user.getUsername());

				}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.WORKER)) {
					CitiesController.instance.refreshCities();
				}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTWORKER)) {
					CitiesController.instance.refreshCities();
				}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER)) {
					CitiesController.instance.refreshCities();
				}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER)) {
					CitiesController.instance.refreshCities();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});


	}
	
	/**
	 * Gets the pn items.
	 *
	 * @return the pn items
	 */
	private VBox getPnItems() {
		return pnItems;
	}

	/**
	 * Gets the search text.
	 *
	 * @return the search text
	 */
	private TextField getSearch_text() {
		return search_text;
	}

	/**
	 * Gets the working collection.
	 *
	 * @return the working collection
	 */
	private ObservableList<Node> getWorkingCollection() {
		return workingCollection;
	}

	/**
	 * Sets the working collection.
	 *
	 * @param workingCollection the new working collection
	 */
	private void setWorkingCollection(ObservableList<Node> workingCollection) {
		this.workingCollection = workingCollection;
	}

	/**
	 * Gets the txt cities.
	 *
	 * @return the txt cities
	 */
	private Label getTxt_cities() {
		return txt_cities;
	}

	/**
	 * Gets the txt maps.
	 *
	 * @return the txt maps
	 */
	private Label getTxt_maps() {
		return txt_maps;
	}

	/**
	 * Gets the txt pois.
	 *
	 * @return the txt pois
	 */
	private Label getTxt_pois() {
		return txt_pois;
	}

	/**
	 * Gets the txt tours.
	 *
	 * @return the txt tours
	 */
	private Label getTxt_tours() {
		return txt_tours;
	}

	

}
