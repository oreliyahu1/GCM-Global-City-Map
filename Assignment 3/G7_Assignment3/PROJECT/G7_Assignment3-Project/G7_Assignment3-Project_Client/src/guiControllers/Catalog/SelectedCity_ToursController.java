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
import guiControllers.FxmlView;
import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.item.Toursitem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The Class SelectedCity_ToursController.
 */
public class SelectedCity_ToursController {

	
	/** The instance. */
	public static SelectedCity_ToursController instance;
	
	/** The btn add. */
	@FXML
	private Button btn_add;

    /** The search text. */
    @FXML
    private TextField search_text;
    
    /** The pn items. */
    @FXML
    private VBox pnItems;
    
    /** The n. */
    private Node[] n = new Node[1];
    
    /** The working collection. */
    private ObservableList<Node> workingCollection;
	
	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		SelectedCity_ToursController.instance = this;

		if (!Catalog.getMode().equals(Catalog.MODE.EDIT)) {
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
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITY_SELECTED_TOURS.getFXML()));
		
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
			HomeController.instance.Set_Title(FxmlView.CITY_SELECTED_TOURS.getTitle());
		});
	}
	  
  	/**
  	 * Btn addtour click.
  	 *
  	 * @param event the event
  	 */
  	@FXML
	    void btn_addtour_click(MouseEvent event) {
		  SelectedCity_ToursPlacesController.edit_tour = true;
			SelectedCity_ToursPlacesController.selected_tour = null;
		  FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				SceneController.push(HomeController.instance.PANE);
				SceneController.push_title(HomeController.instance.Get_Title());
				SelectedCity_ToursPlacesController LookupForm = new SelectedCity_ToursPlacesController();
				
				try {
					
					LookupForm.start(HomeController.instance.PANE,null);
	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
			
	    }
	
	/**
	 * The Class NodeComparatorTours. compare by tours name
	 */
	private class NodeComparatorTours implements Comparator<Node> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Node o1, Node o2) {
			Toursitem sp1 = (Toursitem) o1;
			Toursitem sp2 = (Toursitem) o2;
			String s1 = (String) sp1.getTour_name().getText();
			String s2 = (String) sp2.getTour_name().getText();

			return s1.compareTo(s2);
		}
	}
	
	
	/**
	 * Search.
	 *
	 * @param event the event
	 */
	@FXML
	void search(KeyEvent event) {
		// if text is empty then show in sorted
		ArrayList<Toursitem> found = new ArrayList<Toursitem>();
		if (getSearch_text().getText().equals("")) {
			try {

				Collections.sort(getWorkingCollection(), new NodeComparatorTours());

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
					if (child instanceof Toursitem) {
						Toursitem sp = (Toursitem) child;
						if (sp.getTour_name().getText().toLowerCase().contains(getSearch_text().getText().toLowerCase())) {
							found.add(sp);
						}
					}
				}
				Collections.sort(getWorkingCollection(), new NodeComparatorTours());
				getPnItems().getChildren().setAll(getWorkingCollection());
				Collections.sort(found, Collections.reverseOrder(new NodeComparatorTours()));
			});

			Platform.runLater(() -> {
				for (Toursitem sp : found) {

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
	 * Tour list click. on click open tour in a new scene and show it's places 
	 *
	 * @param tour the tour
	 */
	private void tour_list_click(entities.Tour tour) {
		SelectedCity_ToursPlacesController.edit_tour = false;
		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			SelectedCity_ToursPlacesController LookupForm = new SelectedCity_ToursPlacesController();
			
			SelectedCity_ToursPlacesController.selected_tour = tour;
			try {
				
				LookupForm.start(HomeController.instance.PANE, tour);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	}
	
	/**
	 * Update tours. response call the method and update tours list
	 *
	 * @param tours the tours
	 */
	public void update_tours(ArrayList<entities.Tour> tours) {
		
		getPnItems().getChildren().clear();
		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Tour tour : tours) {
					Toursitem sp = new Toursitem(tour);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
						tour_list_click(tour);
					
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
						workingCollection = FXCollections.observableArrayList(getPnItems().getChildren());
						Collections.sort(getWorkingCollection(), new NodeComparatorTours());
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
	 * Refresh tours list by calling requestHandler.
	 */
	public void refresh_tours_list() {
		client.requestHandler.Catalog.getAllTours(GeneralValues.CITY);
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

	/**
	 * Gets the working collection.
	 *
	 * @return the working collection
	 */
	private ObservableList<Node> getWorkingCollection() {
		return workingCollection;
	}
	
}
