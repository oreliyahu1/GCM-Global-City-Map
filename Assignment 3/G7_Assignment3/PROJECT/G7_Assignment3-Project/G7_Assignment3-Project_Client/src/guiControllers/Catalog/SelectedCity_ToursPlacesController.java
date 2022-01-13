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
import guiControllers.Messages.notification;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The Class SelectedCity_ToursPlacesController.
 */
public class SelectedCity_ToursPlacesController {

	/** The instance. */
	public static SelectedCity_ToursPlacesController instance;
	
	/** The selected tour. */
	public static entities.Tour selected_tour; //if null then add
	
	/** The edit tour. */
	public static boolean edit_tour;

    /** The pane add window 2. */
    @FXML
    private Pane pane_add_window2;

    /** The txt name. */
    @FXML
    private TextField txt_name;
    
    /** The checkbox recommended. */
    @FXML
    private CheckBox checkbox_recommended;

    /** The pane add window. */
    @FXML
    private Pane pane_add_window;
    
    /** The txt desc. */
    @FXML
    private TextField txt_desc;
    
	/** The scroll. */
	@FXML
	private ScrollPane scroll_;

	/** The pane 1. */
	@FXML
	private Pane pane1_;

	/** The pn items. */
	@FXML
	private VBox pnItems;

	/** The pn items 2. */
	@FXML
	private VBox pnItems2;

    /** The paneup. */
    @FXML
    private Pane paneup;
    
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
		SelectedCity_ToursPlacesController.instance = this;
		
		if (SelectedCity_ToursPlacesController.selected_tour != null) {
			if(selected_tour.getDescription() == null || selected_tour.getDescription().isEmpty())
				selected_tour.setDescription("");
			client.requestHandler.Catalog.getAllPlaces_in_tours(selected_tour);
		}else {
			client.requestHandler.Catalog.getAllPlaces_tours(GeneralValues.CITY);
		}
		
		
		
		if (!SelectedCity_ToursPlacesController.edit_tour) {
			btn_drag.setVisible(false);
			btn_save.setVisible(false);
			scrollitems.setPrefWidth(746);
			pnItems.setPrefWidth(746);
			pnItems2.setVisible(false);
			scroll_.setVisible(false);
			pane1_.setVisible(false);
			paneup.setPrefWidth(746);
			

		} else {
			btn_drag.setVisible(true);
			btn_save.setVisible(true);
			btn_save.getParent().setVisible(true);
			scroll_.setVisible(true);
			pane1_.setVisible(true);
			scrollitems.setPrefWidth(475);
		
		}

		if(SelectedCity_ToursPlacesController.selected_tour == null) {
			getTxt_desc().getParent().setVisible(true);
			pane_add_window2.setVisible(true);
		}else {
			getTxt_desc().getParent().setVisible(false);
			pane_add_window.setVisible(false);
			pane_add_window2.setVisible(false);
		}
		

		FXMLLoader loader3 = new FXMLLoader(getClass().getResource(FxmlView.LOADING.getFXML()));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pnItems.getChildren().add(n[0]);

		Tooltip.install(btn_drag, new Tooltip("Drag and drop the Places in order to change Position!"));
		
		
	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @param tour the tour
	 * @throws Exception the exception
	 */
	public void start(Pane pane, entities.Tour tour) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITY_SELECTED_TOURS_PLACES.getFXML()));

		Node n = null;
		try {
			n = fxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pane.getChildren().clear();
		pane.getChildren().add(n);

		if(tour == null) {
			Platform.runLater(() -> {
				HomeController.instance.Set_Title(FxmlView.CITY_SELECTED_ADDTOURS_PLACES.getTitle());

			});
		}else {
			Platform.runLater(() -> {
				HomeController.instance.Set_Title(FxmlView.CITY_SELECTED_TOURS_PLACES.getTitle());

			});
		}
		

	}

	/**
	 * Btn save click. save on adding a tour or editing a tour
	 *
	 * @param event the event
	 */
	@FXML
	void btn_save_click(MouseEvent event) {

		for (Node n : pnItems.getChildren()) {
			if (n instanceof PlaceofInterestItem) {
				PlaceofInterestItem spp = (PlaceofInterestItem) n;
				System.out.println(spp.getplacetour().getName());
				if (spp.getsectime() == 0) {
					Stage s = (Stage) pnItems.getScene().getWindow();
					notification.show("Please dont select a place with total time of 00:00:00:!", "error", s);
					return;
				}
			}
		}
		if (SelectedCity_ToursPlacesController.selected_tour == null) {
			Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
			if (getTxt_name().getText().isEmpty()) {
				notification.show("Please Type a tour name!", "error", s);
				return;
			} else if (getTxt_desc().getText().isEmpty()) {
				notification.show("Please Type a tour description!", "error", s);
				return;
			} else if (pnItems.getChildren().isEmpty()) {
				notification.show("Please Fill the tour with at least 1 place of interest!", "error", s);
				return;
			}

			ArrayList<entities.PlaceOfInterestTour> list_save = new ArrayList<entities.PlaceOfInterestTour>();
			entities.Tour newtour = new entities.Tour(GeneralValues.CITY, getTxt_name().getText(),
					getCheckbox_recommended().isSelected(), getTxt_desc().getText());

			int index = 1;
			for (Node sp : getPnItems().getChildren()) {
				if (sp instanceof PlaceofInterestItem) {
					PlaceofInterestItem item = (PlaceofInterestItem) sp;
					item.getplacetour().setIndex(index); // set index
					item.getplacetour().setSecTime(item.getsectime()); // set time
					item.getplacetour().setTour(newtour);
					list_save.add(item.getplacetour());
					index++;
				}
			}
			client.requestHandler.Catalog.AddTour(list_save);
		} else {

			if (pnItems.getChildren().isEmpty()) {
				Stage s = (Stage) HomeController.instance.PANE.getScene().getWindow();
				notification.show("Please Fill the tour with at least 1 place of interest!", "error", s);
				return;
			}
			ArrayList<entities.PlaceOfInterestTour> list_save = new ArrayList<entities.PlaceOfInterestTour>();
			int index = 1;
			for (Node sp : getPnItems().getChildren()) {
				if (sp instanceof PlaceofInterestItem) {
					PlaceofInterestItem item = (PlaceofInterestItem) sp;
					item.getplacetour().setIndex(index); // set index
					item.getplacetour().setSecTime(item.getsectime()); // set time
					item.getplacetour().setTour(selected_tour);
					list_save.add(item.getplacetour());
					index++;
				}
			}
			client.requestHandler.Catalog.UpdateTours_hard(list_save);
		}

	}

	/**
	 * Update all places add edit tour. response call this method
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

					Platform.runLater(() -> {
						boolean found = false;

						for (Node n : pnItems.getChildren()) {
							if (n instanceof PlaceofInterestItem) {
								PlaceofInterestItem spp = (PlaceofInterestItem) n;
								System.out.println(spp.getplacetour().getName());
								if (spp.getplacetour().getName().equals(place.getName()))
									found = true;
							}
						}
						if (!found)
							pnItems2.getChildren().add(sp);
						new SlideInUp(sp).play();
					});

				}
				
				FadeOutUp animate = new FadeOutUp(n[0]);
				animate.play();
				animate.setOnFinished(event1 -> {
					Platform.runLater(() -> {
						pnItems.getChildren().remove(n[0]);
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
	 * response call this method
	 * Update places of tour. with drag and drop in order to re position the place orders
	 *
	 * @param placesintour the placesintour
	 */
	public void update_places_of_tour(ArrayList<entities.PlaceOfInterestTour> placesintour) {

		pnItems.getChildren().clear();
		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.PlaceOfInterestTour placeintour : placesintour) {

					PlaceofInterestItem sp = new PlaceofInterestItem(placeintour);
					// delete buttons from list of item (place)
					
					if (!SelectedCity_ToursPlacesController.edit_tour) {
						sp.client_layout();
					}else {
						sp.add_edit_layout();
					}
					
					sp.setOnDragOver(event -> {
						final Dragboard dragboard = event.getDragboard();
						if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString())
								&& draggingTab.get() != null) {
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

				FadeOutUp animate = new FadeOutUp(n[0]);
				animate.play();
				animate.setOnFinished(event1 -> {
					Platform.runLater(() -> {
						pnItems.getChildren().remove(n[0]);

					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

		if (SelectedCity_ToursPlacesController.edit_tour) {
			client.requestHandler.Catalog.getAllPlaces_tours(GeneralValues.CITY);

		}



	}

	/**
	 * Adds the one place to tour. and make it draggable
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

	/**
	 * Gets the txt name.
	 *
	 * @return the txt name
	 */
	private TextField getTxt_name() {
		return txt_name;
	}

	/**
	 * Gets the txt desc.
	 *
	 * @return the txt desc
	 */
	private TextField getTxt_desc() {
		return txt_desc;
	}


	/**
	 * Gets the checkbox recommended.
	 *
	 * @return the checkbox recommended
	 */
	private CheckBox getCheckbox_recommended() {
		return checkbox_recommended;
	}


}
