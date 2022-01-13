package guiControllers;

import java.io.IOException;
import java.util.ArrayList;

import animatefx.FadeOutRight;
import animatefx.SlideInUp;
import entities.AbstractRequest;
import entities.Permission;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.Catalog.Selected_CityController;
import guiControllers.item.RaterequestitemBase;
import guiControllers.item.RaterequestitemBasemy;
import guiControllers.item.VersionrequestitemBase;
import guiControllers.item.VersionrequestitemBasemy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class Manage_RequestsController.
 */
public class Manage_RequestsController {

	/** The pnl overview. */
	@FXML
	private Pane pnlOverview;

	/** The paneversions. */
	@FXML
	private Pane paneversions;

	/** The pnitemsv. */
	@FXML
	private VBox pnitemsv;
	
	/** The pnitemsv 1. */
	@FXML
	private VBox pnitemsv1;
	
	/** The panerates. */
	@FXML
	private Pane panerates;
	
	/** The paneratesmy. */
	@FXML
	private Pane paneratesmy;
	
	/** The paneversionsmy. */
	@FXML
	private Pane paneversionsmy;

	/** The pnitemsr. */
	@FXML
	private VBox pnitemsr;
	
	/** The pnitemsr 1. */
	@FXML
	private VBox pnitemsr1;
	

	/** The btn version. */
	@FXML
	private Button btn_version;

	/** The btn rates. */
	@FXML
	private Button btn_rates;

    /** The btn rates 1. */
    @FXML
    private Button btn_rates1;

    /** The btn version 1. */
    @FXML
    private Button btn_version1;

    /** The y. */
    private double x,y;
	
	/** The instance. */
	public static Manage_RequestsController instance;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		Manage_RequestsController.instance = this;

		if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTWORKER)) {
			btn_rates1.setVisible(false);
			btn_version.setVisible(false);
			btn_rates.setVisible(false);
		}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER)) {
			btn_rates.setVisible(false);
			
		}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER)) {
			//nothing to hide
		}
		
	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.MANAGE_REQUESTS.getFXML()));
		HomeController.instance.Set_Title(FxmlView.MANAGE_REQUESTS.getTitle());
		Node n = null;
		try {
			n = fxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pane.getChildren().clear();
		pane.getChildren().add(n);
	}

    /**
     * Btn set rate clicked. see all rates 
     *
     * @param event the event
     */
    @FXML
    void btn_set_rate_clicked(MouseEvent event) {

  
    	 try {

    		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Set_Rates.fxml"));
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
	 * Button enabled style.
	 *
	 * @param btn the btn
	 */
	private void button_enabled_style(Button btn) {
		btn.setStyle("-fx-border-color:  #2A73FF; -fx-border-radius:20;-fx-background-color: white;");
	}
	
	/**
	 * Button disabled style.
	 *
	 * @param btn the btn
	 */
	private void button_disabled_style(Button btn) {
		btn.setStyle("-fx-border-color:  #2A73FF; -fx-border-radius:20;");
	}
    
    /**
     * Btn myrates clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_myrates_clicked(MouseEvent event) {
    	button_enabled_style(btn_rates1);
    	button_disabled_style(btn_rates);
    	button_disabled_style(btn_version);
    	button_disabled_style(btn_version1);
		paneratesmy.setVisible(true);
		paneratesmy.toFront();
		 paneversions.setVisible(false);
    	 paneversionsmy.setVisible(false);
    	 panerates.setVisible(true);
    	 refresh_myratesrequests();
    }

    /**
     * Btn myversion clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_myversion_clicked(MouseEvent event) {
    	button_enabled_style(btn_version1);
    	button_disabled_style(btn_rates);
    	button_disabled_style(btn_version);
    	button_disabled_style(btn_rates1);
    	paneversionsmy.setVisible(true);
		 paneversionsmy.toFront();
		 panerates.setVisible(false);
		 paneversions.setVisible(true);
		 paneratesmy.setVisible(false);
		 refresh_myversionrequests();
    }
    
	/**
	 * Btn rates clicked.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_rates_clicked(MouseEvent event) {
		button_enabled_style(btn_rates);
		button_disabled_style(btn_rates1);
    	button_disabled_style(btn_version);
    	button_disabled_style(btn_version1);
		panerates.setVisible(true);
		panerates.toFront();
		 paneversions.setVisible(false);
    	 paneversionsmy.setVisible(false);
    	 paneratesmy.setVisible(true);
    
    	 refresh_rates();

	}

	/**
	 * Btn version clicked.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_version_clicked(MouseEvent event) {
		button_enabled_style(btn_version);
		
		button_disabled_style(btn_rates);
    	button_disabled_style(btn_rates1);
    	button_disabled_style(btn_version1);
		 paneversions.setVisible(true);
		 paneversions.toFront();
		 
		 panerates.setVisible(false);
    	 paneversionsmy.setVisible(true);
    	 paneratesmy.setVisible(false);
    	 
    	 refresh_versions();
   
	}
	
	/**
	 * Refresh myversionrequests. call the request handler
	 */
	public void refresh_myversionrequests() {
		client.requestHandler.ManageRequests.getAll_myversion_requests(HomeController.instance.user.getUsername());
	}
	
	/**
	 * Refresh myratesrequests. call the request handler
	 */
	public void refresh_myratesrequests() {
		client.requestHandler.ManageRequests.getAll_myrates_requests(HomeController.instance.user.getUsername());
	}
	
	/**
	 * Refresh versions. call the request handler
	 */
	public void refresh_versions() {
		client.requestHandler.ManageRequests.getAll_version_requests();
		
	}
	
	/**
	 * Refresh rates. call the request handler
	 */
	public void refresh_rates() {
		client.requestHandler.ManageRequests.getAll_rates_requests();
	}
	
	/**
	 * Update myrate requests.
	 *
	 * @param requests the requests
	 */
	public void update_myrate_requests(ArrayList<entities.RateRequest> requests) {
		pnitemsr1.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.RateRequest req : requests) {
					RaterequestitemBasemy sp = new RaterequestitemBasemy(req);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
			
					Platform.runLater(() -> {
						pnitemsr1.getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}
	
	/**
	 * Update rate requests.
	 *
	 * @param requests the requests
	 */
	public void update_rate_requests(ArrayList<entities.RateRequest> requests) {
		pnitemsr.getChildren().clear();

		
		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.RateRequest req : requests) {
					RaterequestitemBase sp = new RaterequestitemBase(req);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

					});
					Platform.runLater(() -> {
						pnitemsr.getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}
	
	/**
	 * Update myversion requests.
	 *
	 * @param requests the requests
	 */
	public void update_myversion_requests(ArrayList<entities.VersionRequest> requests) {
		pnitemsv1.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.VersionRequest req : requests) {
					VersionrequestitemBasemy sp = new VersionrequestitemBasemy(req);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
				
					Platform.runLater(() -> {
						pnitemsv1.getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}
	
	/**
	 * Update version requests. response handler update the list
	 *
	 * @param requests the requests
	 */
	public void update_version_requests(ArrayList<entities.VersionRequest> requests) {
		pnitemsv.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.VersionRequest req : requests) {
					VersionrequestitemBase sp = new VersionrequestitemBase(req);
					if (!(req.getStatus().equals(AbstractRequest.STATUS.REJECT))) {
						sp.setOnMouseEntered(event -> {
							sp.setStyle("-fx-background-color : #BDBBC3");
						});
					}
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
						//show maps collection that needs to be approved/rejected!
						if(req.getStatus().equals(AbstractRequest.STATUS.REJECT))
							return;
						client.requestHandler.Catalog.setMode(client.requestHandler.Catalog.MODE.VIEWREQUEST);
						entities.City city = req.getCity();
						FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
						anim1.play();
						anim1.setResetOnFinished(true);
						anim1.setOnFinished(event1 -> {
							SceneController.push(HomeController.instance.PANE);
							SceneController.push_title(HomeController.instance.Get_Title());
							Selected_CityController LookupForm = new Selected_CityController();
							try {
								LookupForm.start(HomeController.instance.PANE);
								GeneralValues.CITY = city;
								GeneralValues.COUNTRY = city.getCountry();
								client.requestHandler.Catalog.getAllRecommendedTours(city);

						    	//Selected_CityController.instance.update_maps();
								client.requestHandler.Catalog.getAllMapsOfCity(city);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						});
				    	
						
						
					});
					Platform.runLater(() -> {
						pnitemsv.getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}

}
