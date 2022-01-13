package guiControllers.Catalog;

import java.io.IOException;
import java.util.ArrayList;

import animatefx.SlideInUp;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import guiControllers.item.CityBase_search;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The Class MapSearcherController.
 */
public class MapSearcherController {

	/** The instance. */
	public static MapSearcherController instance;
	
	/** The str. */
	private ArrayList<String> str = new ArrayList<String>();
	
	/** The search option. */
	private ObservableList<String> search_option;
	  
  	/** The pn items. */
  	@FXML
	private VBox pnItems;
    
    /** The search text. */
    @FXML
    private TextField search_text;
    
    /** The palces onpane. */
    @FXML
    private Label palces_onpane;

    /** The tours onpane. */
    @FXML
    private Label tours_onpane;

    /** The combo search. */
    @FXML
    private ComboBox<String> combo_search;
    
	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		MapSearcherController.instance = this;
		getPalces_onpane().setVisible(false);
		getTours_onpane().setVisible(false);
		getStr().add("City Name");
		getStr().add("City Description");
		getStr().add("Place of Interest Name");
    	getStr().add("Map Description");
    	setSearch_option(FXCollections.observableArrayList(getStr()));
    	getCombo_search().setItems(getSearch_option());
		
		
	}
	
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.ADVANCEDSEARCHER.getFXML()));

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
			HomeController.instance.Set_Title(FxmlView.ADVANCEDSEARCHER.getTitle());
		});
	}
	
	/**
	 * Gets the cities by name. response handler call this method and update the VBox list of cities
	 * same thing as methods below 
	 *
	 * @param Cities the cities
	 * @return the cities by name
	 */
	public void get_cities_by_name(ArrayList<entities.City> Cities) {
		getPnItems().getChildren().clear();
		
		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.City city : Cities) {
					
					CityBase_search sp = new CityBase_search(city);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

					});
					Platform.runLater(() -> {
						getPnItems().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
	}
	
	/**
	 * Gets the cities by maptext.
	 *
	 * @param Cities the cities
	 * @return the cities by maptext
	 */
	public void get_cities_by_maptext(ArrayList<entities.City> Cities) {
		getPnItems().getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.City city : Cities) {

					CityBase_search sp = new CityBase_search(city);
					sp.nset_layout_of_city_name_desc();
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

					});
					Platform.runLater(() -> {
						getPnItems().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
	}
	
	/**
	 * Gets the cities by placetext.
	 *
	 * @param Cities the cities
	 * @return the cities by placetext
	 */
	public void get_cities_by_placetext(ArrayList<entities.City> Cities) {
		getPnItems().getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.City city : Cities) {

					CityBase_search sp = new CityBase_search(city);
					sp.nset_layout_of_city_name_desc();
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

					});
					Platform.runLater(() -> {
						getPnItems().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
	}
	
	/**
	 * Gets the cities by desc.
	 *
	 * @param Cities the cities
	 * @return the cities by desc
	 */
	public void get_cities_by_desc(ArrayList<entities.City> Cities) {
		getPnItems().getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.City city : Cities) {

					CityBase_search sp = new CityBase_search(city);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});

					Platform.runLater(() -> {
						getPnItems().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
	}
	

    /**
     * Search change.
     *
     * @param event the event
     */
    @FXML
    void search_change(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			if (getCombo_search().getSelectionModel().getSelectedItem() != null) {
				if (getCombo_search().getSelectionModel().getSelectedItem().toString().equals("City Name")) {
					getPalces_onpane().setVisible(true);
					getTours_onpane().setVisible(true);
					client.requestHandler.Search.getallcitites_by_name(getSearch_text().getText());
				} else if (getCombo_search().getSelectionModel().getSelectedItem().toString().equals("Map Description")) {
					getPalces_onpane().setVisible(false);
					getTours_onpane().setVisible(false);
					client.requestHandler.Search.getallcitites_by_map_description(getSearch_text().getText());
				} else if (getCombo_search().getSelectionModel().getSelectedItem().toString()
						.equals("Place of Interest Name")) {
					getPalces_onpane().setVisible(false);
					getTours_onpane().setVisible(false);
					client.requestHandler.Search.getallcitites_by_poi_description(getSearch_text().getText());
				} else if (getCombo_search().getSelectionModel().getSelectedItem().toString().equals("City Description")) {
					getPalces_onpane().setVisible(true);
					getTours_onpane().setVisible(true);
					client.requestHandler.Search.getallcitites_by_description(getSearch_text().getText());
				} else {
					getPalces_onpane().setVisible(false);
					getTours_onpane().setVisible(false);
					Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
					notification.show("No Such an Option!", "error", s);
				}

			} else {
				Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show("Please Select an Option to search!", "error", s);
			}
		}
    }

	/**
	 * Gets the str.
	 *
	 * @return the str
	 */
	private ArrayList<String> getStr() {
		return str;
	}


	/**
	 * Gets the search option.
	 *
	 * @return the search option
	 */
	private ObservableList<String> getSearch_option() {
		return search_option;
	}

	/**
	 * Sets the search option.
	 *
	 * @param search_option the new search option
	 */
	private void setSearch_option(ObservableList<String> search_option) {
		this.search_option = search_option;
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
	 * Gets the palces onpane.
	 *
	 * @return the palces onpane
	 */
	private Label getPalces_onpane() {
		return palces_onpane;
	}

	/**
	 * Gets the tours onpane.
	 *
	 * @return the tours onpane
	 */
	private Label getTours_onpane() {
		return tours_onpane;
	}

	/**
	 * Gets the combo search.
	 *
	 * @return the combo search
	 */
	private ComboBox<String> getCombo_search() {
		return combo_search;
	}
  
}
