package guiControllers.CatalogEdit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import animatefx.FadeOutUp;
import animatefx.SlideInUp;

import guiControllers.FxmlView;
import guiControllers.GeneralValues;
import guiControllers.Catalog.SelectedMap_CityController;
import guiControllers.item.PlaceofInterestItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Class AddPlaceToMapController.
 */
public class AddPlaceToMapController {

	/** The instance. */
	public static AddPlaceToMapController instance;
	
    /** The pnl overview. */
    @FXML
    private Pane pnlOverview;



    /** The pn items. */
    @FXML
    public VBox pnItems;

    /** The txt location. */
    @FXML
    private Label txt_Location;
    
    /** The btn exit. */
    @FXML
    private ImageView btn_exit;
    
    /** The n. */
    private Node[] n = new Node[1];
	
	/** The working collection. */
	private ObservableList<Node> workingCollection;
    

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	AddPlaceToMapController.instance = this;
    	
    	getTxt_Location().setText("Location Selected: " + GeneralValues.COUNTRY.getName()
    	 + ", " + GeneralValues.CITY.getName() + ", " 
    			+ SelectedMap_CityController.instance.selected_map.getName() + ", " + "(" 
    			+ SelectedMap_CityController.instance.selected_Coordinates.getX() + "," + SelectedMap_CityController.instance.selected_Coordinates.getY() + ")");
    	
    	
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource(FxmlView.LOADING.getFXML()));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getPnItems().getChildren().add(n[0]);
		
		client.requestHandler.Catalog.getAllPlaces_EDITMAP(SelectedMap_CityController.instance.selected_map.getCity());
		//client.requestHandler.Catalog.getAllPlaces(SelectedMap_CityController.instance.selected_map.getCity());
    	
    }

	/**
	 * The Class NodeComparator.
	 */
	private class NodeComparator implements Comparator<Node> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Node o1, Node o2) {
			PlaceofInterestItem sp1 = (PlaceofInterestItem) o1;
			PlaceofInterestItem sp2 = (PlaceofInterestItem) o2;
			String s1 = (String) sp1.getPlace_name().getText();
			String s2 = (String) sp2.getPlace_name().getText();

			return s1.compareTo(s2);
		}
	}
	

    /**
     * Close window.
     */

    public void close_window() {
    	Stage s = (Stage)getPnItems().getScene().getWindow();
		s.close();
    }
    
    /**
     * Exit all.
     *
     * @param event the event
     */
    @FXML
    void exit_all(MouseEvent event) {
    	Stage s = (Stage)getPnItems().getScene().getWindow();
		s.close();
    }
    
    /**
     * Update places.
     *
     * @param places the places
     */
    public void update_places(ArrayList<entities.PlaceOfInterest> places) {


		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.PlaceOfInterest place : places) {
					//entities.Map selectedMap = new entities.Map(SelectedMap_CityController.instance.selected_map.getCity(), SelectedMap_CityController.instance.selected_map.getName());
					entities.PlaceOfInterestMap place1 = new entities.PlaceOfInterestMap(place, SelectedMap_CityController.instance.selected_map, SelectedMap_CityController.instance.selected_Coordinates);
					PlaceofInterestItem sp = new PlaceofInterestItem(place);
					//delete buttons from list of item (place)
					sp.btn_delete.setVisible(false);
					sp.btn_edit.setVisible(false);
					
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
						//on_place_click(place1);
						client.requestHandler.Catalog.add_poiToMap(place1);
						
						///need to close window
						
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
						try {
							setWorkingCollection( FXCollections.observableArrayList(getPnItems().getChildren())) ;
							Collections.sort(getWorkingCollection(), new NodeComparator());
							getPnItems().getChildren().setAll(getWorkingCollection());
							// search_text.setDisable(false);
						}catch(ClassCastException e) {
							
						}
					
					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
    	
    	
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
	 * Gets the txt location.
	 *
	 * @return the txt location
	 */
	private Label getTxt_Location() {
		return txt_Location;
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

}
