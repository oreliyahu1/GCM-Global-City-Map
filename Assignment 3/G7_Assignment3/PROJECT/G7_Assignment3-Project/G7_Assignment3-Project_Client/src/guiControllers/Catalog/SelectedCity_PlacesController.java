package guiControllers.Catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import animatefx.FadeIn;
import animatefx.FadeOutUp;
import animatefx.SlideInUp;
import client.requestHandler.Catalog;
import guiControllers.FxmlView;
import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.CatalogEdit.Add_Edit_Place_Controller;
import guiControllers.item.PlaceofInterestItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class SelectedCity_PlacesController.
 */
public class SelectedCity_PlacesController {

	/** The instance. */
	public static SelectedCity_PlacesController instance;

    /** The btn add. */
    @FXML
    private Button btn_add;
    
    /** The search text. */
    @FXML
    private TextField search_text;
    
    /** The pn items. */
    @FXML
    public VBox pnItems;
    
    /** The n. */
    private Node[] n = new Node[1];
	
	/** The working collection. */
    private ObservableList<Node> workingCollection;
	
	/**  The x,y to drag the scene. */
	private double x,y;
	
	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		SelectedCity_PlacesController.instance = this;
		
		if(!Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			btn_add.setVisible(false);
		}
		
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource(FxmlView.LOADING.getFXML()));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getPnItems().getChildren().add(n[0]);
	
	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITY_SELECTED_PLACES.getFXML()));

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
			HomeController.instance.Set_Title(FxmlView.CITY_SELECTED_PLACES.getTitle());
		});
	}
	
    /**
     * Btn add place click. open a new scene to add a place to the selected city
     *
     * @param event the event
     */
    @FXML
    void btn_add_place_click(MouseEvent event) {
   
   	 try {

   		Add_Edit_Place_Controller.add_edit_mode = true;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/Add_place.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
		
			Stage primaryStage = new Stage();
		
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			Add_Edit_Place_Controller.instance.label_country.setText(GeneralValues.COUNTRY.getName());
			Add_Edit_Place_Controller.instance.label_city.setText(GeneralValues.CITY.getName());
			
			scene.setOnMousePressed(event1 -> {
				x = event1.getSceneX();
				y = event1.getSceneY();
			});
			scene.setOnMouseDragged(event2 -> {

				primaryStage.setX(event2.getScreenX() - x);
				primaryStage.setY(event2.getScreenY() - y);

			});
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Refresh POI by calling request handler.
     */
    public void refreshPOI() {
    	client.requestHandler.Catalog.getAllPlaces(GeneralValues.CITY);
    }
	
	/**
	 * The Class NodeComparatorPlaces to compare places in list by name.
	 */
	private class NodeComparatorPlaces implements Comparator<Node> {
		
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
	 * Search event.
	 *
	 * @param event the event
	 */
	@FXML
	void search(KeyEvent event) {
		// if text is empty then show in sorted
		ArrayList<PlaceofInterestItem> found = new ArrayList<PlaceofInterestItem>();
		if (getSearch_text().getText().equals("")) {
			try {

				Collections.sort(getWorkingCollection(), new NodeComparatorPlaces());

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
					if (child instanceof PlaceofInterestItem) {
						PlaceofInterestItem sp = (PlaceofInterestItem) child;
						if (sp.getPlace_name().getText().toLowerCase().contains(getSearch_text().getText().toLowerCase())) {
							found.add(sp);
						}
					}
				}
				Collections.sort(getWorkingCollection(), new NodeComparatorPlaces());
				getPnItems().getChildren().setAll(getWorkingCollection());
				Collections.sort(found, Collections.reverseOrder(new NodeComparatorPlaces()));
			});

			Platform.runLater(() -> {
				for (PlaceofInterestItem sp : found) {

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
	
	
	/**
	 * Update places. response call this method and update the list of places
	 *
	 * @param places the places
	 */
	public void update_places(ArrayList<entities.PlaceOfInterest> places) {
		getPnItems().getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.PlaceOfInterest place : places) {
					PlaceofInterestItem sp = new PlaceofInterestItem(place);
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

				FadeOutUp animate = new FadeOutUp(n[0]);
				animate.play();
				animate.setOnFinished(event1 -> {
					Platform.runLater(() -> {
						getPnItems().getChildren().remove(n[0]);
						setWorkingCollection(FXCollections.observableArrayList(getPnItems().getChildren()));
						Collections.sort(getWorkingCollection(), new NodeComparatorPlaces());
						getPnItems().getChildren().setAll(getWorkingCollection());
						//getSearch_text().setDisable(false);
					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
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
	 * Gets the search text.
	 *
	 * @return the search text
	 */
	private TextField getSearch_text() {
		return search_text;
	}

	/**
	 * Gets the pn items.
	 *
	 * @return the pn items
	 */
	private VBox getPnItems() {
		return pnItems;
	}
}
