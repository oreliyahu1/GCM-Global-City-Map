package guiControllers.Catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

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
import guiControllers.Profile.ProfileController;
import guiControllers.item.Map;
import guiControllers.item.RecommendedTour;
import guiControllers.subGui.DownloadLoadingController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class Selected_CityController.
 */
public class Selected_CityController {

	/** The instance. */
	public static Selected_CityController instance;

	/** The city purchase. */
	public static entities.Purchase city_purchase;

	/** The btn pushcollection. */
	@FXML
	private Button btn_pushcollection;

	/** The btn download city. */
	@FXML
	private Button btn_download_city;

	/** The current title. */
	public String current_title;

	/** The n. */
	Node[] n = new Node[1];
	
	/** The working collection. */
	ObservableList<Node> workingCollection;

	/** The y. */
	private double x,y;

    /** The search text. */
    @FXML
    private TextField search_text;
    
	/** The pn items. maps list*/
	@FXML
	private VBox pnItems; 
	
	/**  The pn items 1 recommended tours list. */
	@FXML
	private VBox pnItems1; 

	/** The btn add map. */
	@FXML
	private Button btn_add_map;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		Selected_CityController.instance = this;
		
		if(!Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			btn_add_map.setVisible(false);
			btn_pushcollection.setVisible(false);
		}
		
		if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT)) {
			btn_download_city.setVisible(true);
		}else {
			btn_download_city.setVisible(false);
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
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITY_SELECTED.getFXML()));
		
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
			HomeController.instance.Set_Title(FxmlView.CITY_SELECTED.getTitle());
		});
		
	}
	
    /**
     * Btn add map clicked select a map from the external system to add to the selected city.
     *
     * @param event the event
     */
    @FXML
    void btn_add_map_clicked(MouseEvent event) {
    
    	 try {

    		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/AddMap.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();
			
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.initStyle(StageStyle.UNDECORATED);
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
  	 * Btn tours click. on click show tour list in a new scene
  	 *
  	 * @param event the event
  	 */
  	@FXML
	    void btn_tours_click(MouseEvent event) {

		   FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				SceneController.push(HomeController.instance.PANE);
				SceneController.push_title(HomeController.instance.Get_Title());
				SceneController.push_command("Selected_City");
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
   	 * Btn places click. show places list in VBox on a new scene
   	 *
   	 * @param event the event
   	 */
   	@FXML
	    void btn_places_click(MouseEvent event) {
		   FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				SceneController.push(HomeController.instance.PANE);
				SceneController.push_title(HomeController.instance.Get_Title());
				SceneController.push_command("Selected_City");
				SelectedCity_PlacesController LookupForm = new SelectedCity_PlacesController();

				
				try {
					LookupForm.start(HomeController.instance.PANE);
					client.requestHandler.Catalog.getAllPlaces(GeneralValues.CITY);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
	    }
	
	/**
	 * Map click. show map image with it's Locations according to coordinate in a new scene
	 *
	 * @param map the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void Map_click(entities.Map map) throws IOException {
		
		if(HomeController.instance.user == null)
		{
			Alert alert = new Alert(AlertType.WARNING, "Ok", ButtonType.OK);
        	alert.setTitle("Login Required");
        	alert.setContentText("Please Login in order to preview the map!");
        	alert.showAndWait();
        	return;
		}
		
		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			SceneController.push_command("Selected_City");
			SelectedMap_CityController LookupForm = new SelectedMap_CityController();

			
			try {
				LookupForm.start(HomeController.instance.PANE);
				SelectedMap_CityController.instance.selected_map = map;
				client.requestHandler.Catalog.getAllPOIOFMap(map);
				
				if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT))
					client.requestHandler.Catalog.getFullMapOfCity(map,city_purchase);
				else
					client.requestHandler.Catalog.getFullMapOfCity(map);
				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	}
	
	/**
	 * The Class NodeComparatorMap to compare by map name.
	 */
	private class NodeComparatorMap implements Comparator<Node> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Node o1, Node o2) {
			Map sp1 = (Map) o1;
			Map sp2 = (Map) o2;
			String s1 = (String) sp1.getMap_name().getText();
			String s2 = (String) sp2.getMap_name().getText();

			return s1.compareTo(s2);
		}
	}
	
	/**
	 * Search event on enter keyboard.
	 *
	 * @param event the event
	 */
	@FXML
	void search(KeyEvent event) {
		// if text is empty then show in sorted
		ArrayList<Map> found = new ArrayList<Map>();
		if (getSearch_text().getText().equals("")) {
			try {

				Collections.sort(getWorkingCollection(), new NodeComparatorMap());

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
					if (child instanceof Map) {
						Map sp = (Map) child;
						if (sp.getMap_name().getText().toLowerCase().contains(getSearch_text().getText().toLowerCase())) {
							found.add(sp);
						}
					}
				}
				Collections.sort(getWorkingCollection(), new NodeComparatorMap());
				getPnItems().getChildren().setAll(getWorkingCollection());
				Collections.sort(found, Collections.reverseOrder(new NodeComparatorMap()));
			});

			Platform.runLater(() -> {
				for (Map sp : found) {

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
     * Btn push collection click to ask thhe manager for approving the edited collection of maps.
     *
     * @param event the event
     */
    @FXML
    void btn_push_collection_click(MouseEvent event) {

  
    	 try {

    		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/push_maps_collection.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();

				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.initStyle(StageStyle.UNDECORATED);
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
     * Btn download city clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_download_city_clicked(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Download Map Collection", ButtonType.YES, ButtonType.NO);

		alert.setContentText("Download Map Collection of Selected City?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			try {
				DownloadLoadingController.purchase_selected = city_purchase;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/subGui/DownloadLoading.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				primaryStage.setResizable(false);
				primaryStage.setTitle("GCM - Update Credit");
				primaryStage.initModality(Modality.APPLICATION_MODAL);// This is important if you don't want the user to
																		// interact with other windows
				primaryStage.showAndWait();

				if(ProfileController.instance != null)
					ProfileController.instance.refresh_purchases();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
	////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Update recommended tours. show recommended list in VBox (response handller call this method ) 
	 *
	 * @param tours the tours
	 */
	public void update_recommended_tours(ArrayList<entities.Tour> tours) {
		
		getPnItems1().getChildren().clear();
		

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Tour tour : tours) {
					RecommendedTour sp = new RecommendedTour(tour);

					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
					
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
					
					});

					Platform.runLater(() -> {
						getPnItems1().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

				

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
		
		
		
	}
	
	/**
	 * Refresh maps call the request handler. call the request handler and sent to server the selected city
	 */
	public void refresh_maps() {
		client.requestHandler.Catalog.getAllMapsOfCity(GeneralValues.CITY);
	}
	
	/**
	 * Refresh recommended tours. all the request handler and sent to server the selected city
	 */
	public void refresh_recommended_tours() {
		client.requestHandler.Catalog.getAllRecommendedTours(GeneralValues.CITY);
	}
	
	/**
	 * Update maps. after calling the request handler , response handler calls this method and update map list
	 *
	 * @param maps the maps
	 */
	public void update_maps(ArrayList<entities.Map> maps) {
		//clear all before adding!
		pnItems.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Map map : maps) {
					Map sp = new Map(map);

					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
					
						try {
							Map_click(map);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
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
						workingCollection = FXCollections.observableArrayList(pnItems.getChildren());
						Collections.sort(getWorkingCollection(), new NodeComparatorMap());
						pnItems.getChildren().setAll(getWorkingCollection());
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
	 * Gets the pn items 1.
	 *
	 * @return the pn items 1
	 */
	private VBox getPnItems1() {
		return pnItems1;
	}

}
