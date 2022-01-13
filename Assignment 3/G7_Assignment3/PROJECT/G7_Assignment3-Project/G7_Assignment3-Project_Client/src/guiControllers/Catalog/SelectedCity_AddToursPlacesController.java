package guiControllers.Catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import animatefx.FadeOutRight;
import animatefx.FadeOutUp;
import animatefx.SlideInUp;
import guiControllers.FxmlView;
import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.itemtour.PlaceBase;
import guiControllers.itemtour.PlaceofInterestItem;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * The Class SelectedCity_AddToursPlacesController.
 * 
 * 
 * 
 * 
 * 
 * 
 * THIS CLASS IS NOT USED 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class SelectedCity_AddToursPlacesController {

	/** The instance. */
	public static SelectedCity_AddToursPlacesController instance;

	/** The scroll. */
	@FXML
	private ScrollPane scroll_;

	/** The pane 1. */
	@FXML
	private Pane pane1_;

	/** The pn items. */
	@FXML
	public VBox pnItems;

	/** The pn items 2. */
	@FXML
	public VBox pnItems2;

	/** The btn drag. */
	@FXML
	private ImageView btn_drag;

	/** The scrollitems. */
	@FXML
	private ScrollPane scrollitems;

	/** The btn save. */
	@FXML
	private Button btn_save;
	
	/** The n. */
	private Node[] n = new Node[1];

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {

		FXMLLoader loader3 = new FXMLLoader(getClass().getResource(FxmlView.LOADING.getFXML()));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pnItems2.getChildren().add(n[0]);

		Tooltip.install(btn_drag, new Tooltip("Drag and drop the Places in order to change Position!"));


	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITY_SELECTED_ADDTOURS_PLACES.getFXML()));

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
			HomeController.instance.Set_Title(FxmlView.CITY_SELECTED_ADDTOURS_PLACES.getTitle());

		});

	}

	/**
	 * Btn save click.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_save_click(MouseEvent event) {
	}

	/**
	 * Update all places add edit tour.
	 *
	 * @param placesofcity the placesofcity
	 */
	public void update_all_places_add_edit_tour(ArrayList<entities.PlaceOfInterest> placesofcity) {

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.PlaceOfInterest place : placesofcity) {

					PlaceBase sp = new PlaceBase(place);

					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");

					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");

					});
					sp.setOnMouseClicked(event -> {

					});

					Platform.runLater(() -> {
						pnItems2.getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

				FadeOutUp animate = new FadeOutUp(n[0]);
				animate.play();
				animate.setOnFinished(event1 -> {
					Platform.runLater(() -> {
						pnItems2.getChildren().remove(n[0]);
					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}

	/** The tab drag key. */
	private final String TAB_DRAG_KEY = "item";
	
	/** The dragging tab. */
	ObjectProperty<PlaceofInterestItem> draggingTab = new SimpleObjectProperty<PlaceofInterestItem>();;

	/**
	 * Adds the one place to tour.
	 *
	 * @param place the place
	 */
	public void add_one_place_to_tour(entities.PlaceOfInterestTour place) {
		PlaceofInterestItem sp = new PlaceofInterestItem(place);
		// delete buttons from list of item (place)
		sp.add_edit_layout();

		sp.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			}
		});
		sp.setOnDragDetected(event -> {
			Dragboard dragboard = sp.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent clipboardContent = new ClipboardContent();
			clipboardContent.putString(TAB_DRAG_KEY);
			dragboard.setContent(clipboardContent);
			SnapshotParameters spp = new SnapshotParameters();
			spp.setFill(Color.WHITE);
			dragboard.setDragView(sp.snapshot(spp, null), event.getX(), event.getY());
			draggingTab.set(sp);
			event.consume();
		});
		sp.setOnDragDropped(event -> {

			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasString()) {
				VBox parent = (VBox) sp.getParent();
				PlaceofInterestItem source = (PlaceofInterestItem) event.getGestureSource();
				int sourceIndex = parent.getChildren().indexOf(source);
				int targetIndex = parent.getChildren().indexOf(sp);
				List<Node> nodes = new ArrayList<Node>(parent.getChildren());
				if (sourceIndex < targetIndex) {
					Collections.rotate(nodes.subList(sourceIndex, targetIndex + 1), -1);
				} else {
					Collections.rotate(nodes.subList(targetIndex, sourceIndex + 1), 1);
				}
				parent.getChildren().clear();
				parent.getChildren().addAll(nodes);
				success = true;
			}
			event.setDropCompleted(success);
			event.consume();

		});

		sp.setOnMouseEntered(event -> {
			sp.setStyle("-fx-background-color : #BDBBC3");
		});
		sp.setOnMouseExited(event -> {
			sp.setStyle("-fx-background-color : #EBE8F9");
		});

		Platform.runLater(() -> {
			pnItems.getChildren().add(sp);
			new SlideInUp(sp).play();
		});

	}

	/**
	 * Gets the pn items.
	 *
	 * @return the pn items
	 */
	public VBox getPnItems() {
		return pnItems;
	}

	/**
	 * Gets the pn items 2.
	 *
	 * @return the pn items 2
	 */
	public VBox getPnItems2() {
		return pnItems2;
	}

	/**
	 * Refresh and back to tourspane.
	 */
	public void refresh_and_back_to_tourspane() {
		SceneController.pop();
		SceneController.pop_title();
		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SelectedCity_ToursController LookupForm = new SelectedCity_ToursController();

			try {
				LookupForm.start(HomeController.instance.PANE);

				client.requestHandler.Catalog.getAllTours(GeneralValues.CITY);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		
	}


}
