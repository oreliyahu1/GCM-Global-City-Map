package guiControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import animatefx.FadeOutLeft;
import animatefx.FadeOutUp;
import application.ClientMain;
import client.ocsf.ObservableClient;
import client.requestHandler.Catalog.MODE;
import guiControllers.Catalog.CatalogController;
import guiControllers.Profile.ProfileController;
import guiControllers.Registeration.LoginController;
import guiControllers.Subscribe.BuyController;

/**
 * The Class HomeController.
 */
@SuppressWarnings("deprecation")
public class HomeController {

	/** The instance. - used for external updating from response handler */
	public static HomeController instance;
	
	/** The user. */
	public entities.User user;

	/** The pane. */
	@FXML
	public Pane PANE;

	/** The title. */
	@FXML
	private Label TITLE;

	/** The txt welcome. */
	// welcome , role
	@FXML
	private Label txt_welcome;

	/** The txt role. */
	@FXML
	private Label txt_role;
	////////////////////////////////////////
	/** The btn subscribe. */
	// Clients buttons
	@FXML
	private Button btn_subscribe;
	
	/** The btn notification. */
	@FXML
	private Button btn_notification;
	
	/** The btn logout. */
	@FXML
	public Button btn_logout;
	
	/** The btn profile. */
	@FXML
	public ImageView btn_profile;
	
	/** The btn manage clients. */
	@FXML
	public Button btn_manage_clients;
	
	/** The btn catalog edit. */
	@FXML
	public Button btn_Catalog_edit;
    
    /** The btn manage requests. */
    @FXML
    private Button btn_manage_requests;
    
    /** The btn report. */
    @FXML
    private Button btn_report;

	/** The btn back. */
	@FXML
	public ImageView btn_back;
	
	/** The btn login. */
	// side left button Home,Login,Register
	@FXML
	public Button btn_login;

	/** The btn home. */
	@FXML
	public Button btn_home;
	
	/** The btn exit. */
	@FXML
	public ImageView btn_exit;
	
	/** The pane profile. */
	///////////////////////////////
	@FXML 
	private Pane pane_profile;
	
	/** The Notification label. */
	@FXML
	public Label Notification_label;
	
	
	/** The check notifications. */
	//Thread to check notifications
	private Timer check_notifications;

	/**
	 * Initialize.
	 * disable all buttons
	 * show login scene
	 */
	@FXML
	private void initialize() {
		HomeController.instance = this;

		// need to hide all buttons!
		btn_manage_requests.setVisible(false);
		btn_Catalog_edit.setVisible(false);
		btn_manage_clients.setVisible(false);
		btn_report.setVisible(false);
		btn_notification.getParent().setVisible(false);
		btn_logout.setVisible(false);
		btn_home.setVisible(false);
		Notification_label.setVisible(false);
		initClientObserver();
		// SceneController.push(PANE);
		LoginController LookupForm = new LoginController();
		try {
			LookupForm.start(PANE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		////

		btn_profile.setDisable(true);
		btn_back.setVisible(false);

	}

	/**
	 * Sets the title.
	 *
	 * @param title the title
	 */
	public void Set_Title(String title) {
		TITLE.setText(title);
	}

	/**
	 * Gets the title.
	 *
	 * @return the string
	 */
	public String Get_Title() {
		return TITLE.getText();
	}

	/**
	 * Profile mouse on.
	 *
	 * @param event the event
	 */
	@FXML
	void profile_mouse_on(MouseEvent event) {
		btn_profile.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}

	/**
	 * Profile mouse out.
	 *
	 * @param event the event
	 */
	@FXML
	void profile_mouse_out(MouseEvent event) {
		btn_profile.setStyle("");
	}

	/**
	 * Exit all.
	 *
	 * @param event the event
	 */

	@FXML
	void exit_all(MouseEvent event) {

		final Stage stage = (Stage) btn_exit.getScene().getWindow();
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)); // force WINDOW_CLOSE_REQUEST
	}


	/**
	 * Home page. open a new scene for  home
	 *
	 * @param event the event
	 */
	@FXML
	void HomePage(MouseEvent event) {
		
		btn_home.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
		
			CatalogController LookupForm = new CatalogController();
			try {
				if(user == null) {
					
					LookupForm.start(PANE,client.requestHandler.Catalog.MODE.GUEST);
				}else {
					LookupForm.start(PANE,client.requestHandler.Catalog.MODE.CLIENT);
				}
				
			//CatalogController.instance.EDIT_MODE = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_home.setDisable(false);

		});
		SceneController.delete();

	}
	   
   	/**
   	 * Btn report clicked open a new scene for report.
   	 *
   	 * @param event the event
   	 */
   	@FXML
	    void btn_report_clicked(MouseEvent event) {
		   btn_report.setDisable(true);
			FadeOutUp anim1 = new FadeOutUp(PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				// SceneController.push(PANE);
				Reports_Controller LookupForm = new Reports_Controller();
				try {
					LookupForm.start(PANE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btn_report.setDisable(false);

			});
			SceneController.delete();
	    }

    /**
     * Btn catalog edit click.
     *
     * @param event the event
     */
    @FXML
    void btn_Catalog_edit_click(MouseEvent event) {
    	client.requestHandler.Catalog.setMode(MODE.EDIT);
    	btn_home.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			CatalogController LookupForm = new CatalogController();
			try {
				LookupForm.start(PANE,client.requestHandler.Catalog.MODE.EDIT);
				//CatalogController.instance.EDIT_MODE = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_home.setDisable(false);

		});
		SceneController.delete();
    }

    
	/**
	 * Click back.
	 *
	 * @param event the event
	 */
	@FXML
	void click_back(MouseEvent event) {
		Platform.runLater(() -> {
			if (SceneController.stageList.size() == 0)
				return;
			Pane scene = SceneController.pop();
			String title = SceneController.pop_title();
			SceneController.pop_command();
			FadeOutLeft anim1 = new FadeOutLeft(PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				PANE.getChildren().clear();
				PANE.getChildren().addAll(scene.getChildren());
				Set_Title(title);
				PANE.toFront();
			});
			
		});
	}

	/**
	 * Profile click. open profile scene
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void profile_click(MouseEvent event) throws IOException {

		btn_profile.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			ProfileController LookupForm = new ProfileController();
			try {
				LookupForm.start(PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_profile.setDisable(false);

		});
		SceneController.delete();

	}

	/**
	 * Subscribe click. open subscribe scene
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void subscribe_click(MouseEvent event) throws IOException {

		btn_subscribe.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			BuyController LookupForm = new BuyController();
			try {
				LookupForm.start(PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_subscribe.setDisable(false);

		});
		SceneController.delete();

	}




	/**
	 * Login page. open login scene
	 *
	 * @param event the event
	 */
	@FXML
	public void LoginPage(MouseEvent event) {
		btn_login.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			LoginController LookupForm = new LoginController();
			try {
				LookupForm.start(PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_login.setDisable(false);

		});
		SceneController.delete();

	}


	/**
	 * Btn click logout. call the request handler in order to logout from the system
	 *
	 * @param event the event
	 */
	/// btn_click_logout
	@FXML
	void btn_click_logout(ActionEvent event) {
		client.requestHandler.Home.logout();
	}

	/**
	 * Inits the client observer.
	 * Adds observer to handle triggers of client service
	 */
	private void initClientObserver() {
	    ClientMain.cSrv.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				Platform.runLater(new Runnable() {	
					@Override
					public void run() {
						switch((String)arg) {
							case ObservableClient.CONNECTION_EXCEPTION:
					    		ClientMain.cSrv.stopClient();
					    		logout();
					    		LoadingController.instance.show(txt_welcome.getScene().getWindow(),"Connection lost, try to reconnect");
					    		Thread th = new Thread(new Runnable() {								
									@Override
									public void run() {
										while((ClientMain.cSrv != null) && (!ClientMain.cSrv.runClient()))  {
											try {
												Thread.sleep(4000);
											} catch (InterruptedException e) { }
										}
									}
								});
					    		// Using thread instead of platform.runlater because of sleep.
								th.start();
								break;
							case ObservableClient.CONNECTION_ESTABLISHED:
								LoadingController.instance.hide();
								break;
						}
					}
				});
			}
		});
	}
	
/**
 * Update notifications.
 *
 * @param allnewmsgs the allnewmsgs
 */
/////////////////////// functions for the response handler
	public void update_notifications(ArrayList<entities.Message> allnewmsgs) {
		int size = allnewmsgs.size();
		if(size > 0) {
			Notification_label.setVisible(true);
			Notification_label.setText(String.valueOf(size));
			
		}else {
			Notification_label.setVisible(false);
			Notification_label.setText("");
		}
	}
	
	/**
	 * Logout. show guest layout
	 */
	public void logout() {

		if(check_notifications != null) {
			check_notifications.cancel();
			check_notifications.purge(); //removes all cancelled tasks from the timer
		}
		  HomeController.instance.user = null; 
		  txt_welcome.setText("Welcome,");
		  txt_role.setText("Guest");
		  
		  btn_profile.setDisable(true);
		  btn_manage_requests.setVisible(false);
			btn_Catalog_edit.setVisible(false);
			btn_manage_clients.setVisible(false);
			btn_report.setVisible(false);
			btn_notification.getParent().setVisible(false);
			btn_logout.setVisible(false);
			btn_home.setVisible(false);
			
			//show login
			btn_login.setVisible(true);
			btn_login.toBack();

			pane_profile.toBack();
			
			//load login page!
			btn_login.setDisable(true);
			FadeOutUp anim1 = new FadeOutUp(PANE);
			anim1.play();
			anim1.setResetOnFinished(true);
			anim1.setOnFinished(event1 -> {
				// SceneController.push(PANE);
				LoginController LookupForm = new LoginController();
				try {
					LookupForm.start(PANE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btn_login.setDisable(false);

			});
			SceneController.delete();
		  
		 

	}

    /**
     * Btn manage clients. show manage users scene
     *
     * @param event the event
     */
    @FXML
    void btn_manage_clients(MouseEvent event) {
    	btn_manage_clients.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			Manage_ClientsController LookupForm = new Manage_ClientsController();
			try {
				LookupForm.start(PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_manage_clients.setDisable(false);

		});
		SceneController.delete();
    }

    /**
     * Btn manage requests clicked. show manage requests scene
     *
     * @param event the event
     */
    @FXML
    void btn_manage_requests_clicked(MouseEvent event) {
    	btn_manage_requests.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			Manage_RequestsController LookupForm = new Manage_RequestsController();
			try {
				LookupForm.start(PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_manage_requests.setDisable(false);

		});
		SceneController.delete();
    }

    
    /**
     * Btn notification clicked. show notification scene
     *
     * @param event the event
     */
    @FXML
    void btn_notification_clicked(MouseEvent event) {
    	btn_notification.setDisable(true);
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			Notification_Controller LookupForm = new Notification_Controller();
			try {
				LookupForm.start(PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_notification.setDisable(false);
		

		});
		SceneController.delete();
    }
    
	/**
	 * Login. on login triggered
	 *
	 * @param user the user
	 */
	public void login(entities.User user) {
		// welcome to "user"
		txt_welcome.setText(String.format("Welcome %s %s", user.getFirstName(), user.getLastName()));
		btn_profile.setDisable(false);
		HomeController.instance.user = user;
		check_notifications = new Timer(true); //run daemon thread
		check_notifications.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					client.requestHandler.Notifications.getNewNotifications(user);
				}catch(Exception ex) {}
			}
		}, 0, 20 *1000);
		// show catalog
		FadeOutUp anim1 = new FadeOutUp(PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			// SceneController.push(PANE);
			CatalogController userLookupForm = new CatalogController();
			try {

					userLookupForm.start(PANE,client.requestHandler.Catalog.MODE.CLIENT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btn_login.setVisible(false);
			btn_home.setVisible(true);
		});
		// end show catalog

		if (user.getPermission().checkRole(entities.Permission.Role.CLIENT)) {
			//Client_Layout();
			txt_role.setText("Client");
			Client_Layout();

		} else if (user.getPermission().checkRole(entities.Permission.Role.WORKER)) {
			//Worker_Layout();
			txt_role.setText("Worker");
			Worker_Layout();

		} else if (user.getPermission().checkRole(entities.Permission.Role.PRODUCTMANAGER)) {
			txt_role.setText("Product Department Manager");
			Product_d_M_Layout();

		} else if (user.getPermission().checkRole(entities.Permission.Role.COMPANYMANAGER)) {
			txt_role.setText("Commpany Manager");
			Company_Manager_Layout();
			
		}else if (user.getPermission().checkRole(entities.Permission.Role.PRODUCTWORKER)) {
			Worker_Department_Layout();
			
		}

	}

	/**
	 * Client layout.
	 */
	private void Client_Layout() {
		txt_role.setText("Client");		
		//need buttons:
		//Catalog,Notifications,Logout!
		//rest are uneeded!
		
		//first of all hide uneeded buttons! and push them to front
		//delete login
		btn_login.toFront(); // to reduce gaps
		btn_login.setVisible(false); //hide login
		
		btn_Catalog_edit.toFront();
		btn_Catalog_edit.setVisible(false); 
		
		btn_manage_clients.toFront();
		btn_manage_clients.setVisible(false); 
		
		btn_manage_requests.toFront();
		btn_manage_requests.setVisible(false);
		
		btn_report.toFront();
		btn_report.setVisible(false);
		
		//now show needed buttons and push them to back!
		
		btn_logout.setVisible(true);
		btn_logout.toBack();
		
		btn_notification.getParent().setVisible(true);
		btn_notification.getParent().toBack();
		
		
		btn_home.setVisible(true);
		btn_home.toBack();

		pane_profile.toBack();
		
	
	}
	
	/**
	 * Worker layout.
	 */
	private void Worker_Layout() {
		txt_role.setText("Worker");
		

		
		//need buttons:
		//Catalog,Notifications,Logout!
		//rest are uneeded!
		
		//first of all hide uneeded buttons! and push them to front
		//delete login
		btn_login.toFront(); // to reduce gaps
		btn_login.setVisible(false); //hide login
		
		btn_Catalog_edit.toFront();
		btn_Catalog_edit.setVisible(false); 
		
		btn_manage_clients.toFront();
		btn_manage_clients.setVisible(false); 
		
		btn_manage_requests.toFront();
		btn_manage_requests.setVisible(false);
		
		btn_report.toFront();
		btn_report.setVisible(false);
		
		//now show needed buttons and push them to back!
		
		btn_logout.setVisible(true);
		btn_logout.toBack();
		
		btn_notification.getParent().setVisible(true);
		btn_notification.getParent().toBack();
		
		
		btn_home.setVisible(true);
		btn_home.toBack();

		pane_profile.toBack();
		
	}
	
	/**
	 * Worker department layout.
	 */
	private void Worker_Department_Layout() {
		txt_role.setText("Product Department Worker");
		
		//need buttons:
		//Catalog,Edit catalog,Notifications,manage requests,Logout!
		//rest are uneeded!
		
		//first of all hide uneeded buttons! and push them to front
		//delete login
		btn_login.toFront(); // to reduce gaps
		btn_login.setVisible(false); //hide login

		btn_manage_clients.toFront();
		btn_manage_clients.setVisible(false); 

		btn_report.toFront();
		btn_report.setVisible(false);
		
		//now show needed buttons and push them to back!
		
		btn_logout.setVisible(true);
		btn_logout.toBack();
		
		btn_manage_requests.setVisible(true);
		btn_manage_requests.toBack();
		
		btn_notification.getParent().setVisible(true);
		btn_notification.getParent().toBack();
		
		btn_Catalog_edit.setVisible(true);
		btn_Catalog_edit.toBack();
		
		btn_home.setVisible(true);
		btn_home.toBack();

		pane_profile.toBack();
		
	}
	
	/**
	 * Product d M layout.
	 */
	private void Product_d_M_Layout() {
		
		//need buttons:
		//ALL except manage clients
		btn_manage_clients.toFront();
		btn_manage_clients.setVisible(false); 
		//now show needed buttons and push them to back!
		
		btn_logout.setVisible(true);
		btn_logout.toBack();
		
		btn_report.setVisible(true);
		btn_report.toBack();
		
		btn_manage_requests.setVisible(true);
		btn_manage_requests.toBack();
		
		btn_notification.getParent().setVisible(true);
		btn_notification.getParent().toBack();
		
		btn_Catalog_edit.setVisible(true);
		btn_Catalog_edit.toBack();
		
		btn_home.setVisible(true);
		btn_home.toBack();

		pane_profile.toBack();
	}

/**
 * Company manager layout.
 */
private void Company_Manager_Layout() {
		
		//need buttons:
		//ALL

		//now show needed buttons and push them to back!
		
		btn_logout.setVisible(true);
		btn_logout.toBack();
		
		btn_report.setVisible(true);
		btn_report.toBack();
		
		btn_manage_requests.setVisible(true);
		btn_manage_requests.toBack();
		
		btn_manage_clients.setVisible(true);
		btn_manage_clients.toBack();
		
		btn_notification.getParent().setVisible(true);
		btn_notification.getParent().toBack();
		
		btn_Catalog_edit.setVisible(true);
		btn_Catalog_edit.toBack();
		
		btn_home.setVisible(true);
		btn_home.toBack();

		pane_profile.toBack();
	}
	

}
