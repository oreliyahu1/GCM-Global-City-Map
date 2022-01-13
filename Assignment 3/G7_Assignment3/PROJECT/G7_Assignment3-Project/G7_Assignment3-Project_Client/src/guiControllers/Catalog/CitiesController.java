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
import guiControllers.Messages.notification;
import guiControllers.Subscribe.SubscribeController;
import guiControllers.item.CityBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class CitiesController.
 */
public class CitiesController {

	/** The instance. */
	public static CitiesController instance;

	/** The purchases list of country user. */
	public static ArrayList<entities.Purchase> purchases_list_of_country_user = new ArrayList<entities.Purchase>(); ;
	
	/** The n. */
	private Node[] n = new Node[1];
	
	/** The working collection. */
	private ObservableList<Node> workingCollection;
	
	/** The y. */
	private double x,y;
	
	/** The search text. */
	@FXML
	private TextField search_text;

	/** The gcm title. */
	@FXML
	private Label GCM_TITLE;

	/** The pn items. */
	@FXML
	private VBox pnItems;

	/** The exit. */
	@FXML
	private ImageView exit;

	/** The btn add city. */
	@FXML
	private Button btn_add_city;
	
	/** The clicked city. */
	private entities.City clicked_city;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {

		CitiesController.instance = this;

		if (!Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			btn_add_city.setVisible(false);
		}

		FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/subGui/Loading.fxml"));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getPnItems().getChildren().add(n[0]);
		Platform.runLater(() -> {
			HomeController.instance.Set_Title(FxmlView.CITIES.getTitle());
		});

	}

	/**
	 * Start the controller.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITIES.getFXML()));

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
			HomeController.instance.Set_Title(FxmlView.CITIES.getTitle());
		});

	}

	/**
	 * Refresh cities call the request handler to get all entites of cities.
	 */
	public void refreshCities() {
		if ((Catalog.getMode().equals(Catalog.MODE.GUEST) || (Catalog.getMode().equals(Catalog.MODE.EDIT) )))
		{
		client.requestHandler.Catalog.getCitiesOfCountry(GeneralValues.COUNTRY.getShortName());	
		}else if((Catalog.getMode().equals(Catalog.MODE.CLIENT))){
			client.requestHandler.Catalog.get_all_purchases_in_country_for_user(GeneralValues.COUNTRY.getShortName(), HomeController.instance.user.getUsername());
	
		}
		
	}

	/**
	 * Delete cities VBox list.
	 */
	public void delete_cities() {
		getPnItems().getChildren().clear();
	}

	/**
	 * The Class NodeComparatorCity by name of entity city.
	 */
	private class NodeComparatorCity implements Comparator<Node> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Node o1, Node o2) {
			CityBase sp1 = (CityBase) o1;
			CityBase sp2 = (CityBase) o2;
			String s1 = (String) sp1.getCity_name().getText();
			String s2 = (String) sp2.getCity_name().getText();

			return s1.compareTo(s2);
		}
	}
	
	/**
	 * Search event. show toped city in the top of VBox list
	 *
	 * @param event the event
	 */
	@FXML
	void search(KeyEvent event) {
		// if text is empty then show in sorted
		ArrayList<CityBase> found = new ArrayList<CityBase>();
		if (getSearch_text().getText().equals("")) {
			try {

				Collections.sort(getWorkingCollection(), new NodeComparatorCity());

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
					if (child instanceof CityBase) {
						CityBase sp = (CityBase) child;
						if (sp.getCity_name().getText().toLowerCase().contains(getSearch_text().getText().toLowerCase())) {
							found.add(sp);
						}
					}
				}
				Collections.sort(getWorkingCollection(), new NodeComparatorCity());
				getPnItems().getChildren().setAll(getWorkingCollection());
				Collections.sort(found, Collections.reverseOrder(new NodeComparatorCity()));
			});

			Platform.runLater(() -> {
				for (CityBase sp : found) {

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
	 * Exit all.
	 *
	 * @param event the event
	 */
	@FXML
	void exit_all(MouseEvent event) {
		Platform.exit();
	}

	/**
	 * City click.
	 *
	 * @param c the c show maps
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void city_click(entities.City c) throws IOException {

		
		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			SceneController.push_command("Cities");
			Selected_CityController LookupForm = new Selected_CityController();
			try {
				LookupForm.start(HomeController.instance.PANE);
				GeneralValues.CITY = c;
				client.requestHandler.Catalog.getAllRecommendedTours(c);
				client.requestHandler.Catalog.getAllMapsOfCity(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	/**
	 * Btn add city click.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_add_city_click(MouseEvent event) {
	
		 try {

			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/AddNewCity.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();
				
				// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public entities.Country get_country() {
		return GeneralValues.COUNTRY;
	}

/////////////////////// functions for the response handler
	/**
	 * Gets the all purchases by username country.
	 *
	 * @param purchases the purchases
	 * @return the all purchases by username country
	 */

	public void get_all_purchases_by_username_country(ArrayList<entities.Purchase> purchases) {
		purchases_list_of_country_user = new ArrayList<entities.Purchase>();
		getPurchases_list_of_country_user().addAll(purchases);
		client.requestHandler.Catalog.getCitiesOfCountry(GeneralValues.COUNTRY.getShortName());

	}
	
	/**
	 * Check subscribtion to enter city.
	 *
	 * @param IsSubscribed the is subscribed
	 */
	public void check_subscribtion_to_enter_city(boolean IsSubscribed) {

		if (!IsSubscribed) {
			ButtonType yes = new ButtonType("Yes I want :)", ButtonBar.ButtonData.OK_DONE);
			ButtonType no = new ButtonType("No, I don't want", ButtonBar.ButtonData.CANCEL_CLOSE);
			Alert alert = new Alert(AlertType.INFORMATION,
					"You didn't purchase that City yet, Do you want to buy this city now?", yes, no);

			alert.setTitle("Wait...");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == yes) {
				Scene scene = null;
				try {
					SubscribeController.city = getClicked_city();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/subscribe/Subscribe.fxml"));
					scene = new Scene(fxmlLoader.load());
					scene.setFill(Color.TRANSPARENT);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setTitle("Purchase Maps Collections of " + getClicked_city().getName());
				stage.setScene(scene);

				stage.show();

			}

		} else {

			try {
				city_click(getClicked_city());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Update cities. response handler arrives here and updates the list of VBox
	 *
	 * @param cities the cities
	 */
	public void update_cities(ArrayList<entities.City> cities) {
		getPnItems().getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.City city : cities) {
					CityBase sp = new CityBase(city);
					
					sp.set_subscribed(true);
					if(HomeController.instance.user == null) {
						//do nothing
						sp.set_subscribed(false);
					}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT)) {
						boolean hide = false;
						for (entities.Purchase purchasee : getPurchases_list_of_country_user()) {
							if (city.getName().equals(purchasee.getCity().getName())) {
								hide = true;
								sp.setpurchase_city(purchasee);
							}

						}
						if (!hide) {
							sp.show_subscribe();
							sp.set_subscribed(false);
						} else {
							sp.set_subscribed(true);
						}
					}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.WORKER)) {
						sp.set_subscribed(true);
					}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER)) {
						sp.set_subscribed(true);
					}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTWORKER)) {
						sp.set_subscribed(true);
					}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER)) {
						sp.set_subscribed(true);
					}
					
					
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
						
						
						setClicked_city(city);
						Selected_CityController.city_purchase = sp.get_city_purchase();
						if(HomeController.instance.user == null) {
							//do nothing if guest!
							Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
							notification.show("Please Login as a Client!", "error", s);
						}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT)) {
							check_subscribtion_to_enter_city(sp.IsSubscribed());
						}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.WORKER)) {
							try {
								city_click(getClicked_city());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTWORKER)) {
							try {
								city_click(getClicked_city());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER)) {
							try {
								city_click(getClicked_city());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER)) {
							try {
								city_click(getClicked_city());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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
						setWorkingCollection( FXCollections.observableArrayList(getPnItems().getChildren()));
						Collections.sort(getWorkingCollection(), new NodeComparatorCity());
						getPnItems().getChildren().setAll(getWorkingCollection());
						// getSearch_text().setDisable(false); //changfe asdlasdlkjsadkl
					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}

	/**
	 * Gets the purchases list of country user.
	 *
	 * @return the purchases list of country user
	 */
	private static ArrayList<entities.Purchase> getPurchases_list_of_country_user() {
		return purchases_list_of_country_user;
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


	/**
	 * Gets the clicked city.
	 *
	 * @return the clicked city
	 */
	private entities.City getClicked_city() {
		return clicked_city;
	}

	/**
	 * Sets the clicked city.
	 *
	 * @param clicked_city the new clicked city
	 */
	private void setClicked_city(entities.City clicked_city) {
		this.clicked_city = clicked_city;
	}

}
