package guiControllers.Registeration;

import java.io.IOException;

import animatefx.FadeOutRight;
import animatefx.FadeOutUp;
import application.GuiFormatCreator;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.Catalog.CatalogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * The Class LoginController.
 */
public class LoginController {

	/** The instance. */
	public static LoginController instance;

	/** The txt login username. */
	@FXML
	private TextField txt_login_username;

	/** The txt login password. */
	@FXML
	private PasswordField txt_login_password;

	/** The btn login. */
	@FXML
	private Button btn_login;

	/** The btn next. */
	@FXML
	private Button btn_next;

	/** The btn signup. */
	@FXML
	private Button btn_signup;

	/**
	 * Btn click guest.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_next_click(MouseEvent event) {
		// need to change PANE in home
		FadeOutUp anim1 = new FadeOutUp(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			CatalogController userLookupForm = new CatalogController();
			try {
				userLookupForm.start(HomeController.instance.PANE, client.requestHandler.Catalog.MODE.GUEST);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 HomeController.instance.btn_login.setDisable(false);
			 HomeController.instance.btn_home.setVisible(true);
		});
		
	
	}
	
	/**
	 * Btn click signin.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_login_clicked(MouseEvent event) {
		if ((txt_login_username.getText().isEmpty()) || (txt_login_password.getText().isEmpty()))
			return;
		client.requestHandler.Home.login(txt_login_username.getText(), txt_login_password.getText());
		
		
		SceneController.stageList.clear();
	}
	
	/**
	 * Btn click signup. in order to register an account
	 *
	 * @param event the event
	 */
	@FXML
	void btn_signup_clicked(MouseEvent event) {
		
		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			RegisterController LookupForm = new RegisterController();
			try {
				LookupForm.start(HomeController.instance.PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}

	/**
	 * Initialize. set Regex for fields
	 */
	@FXML
	void initialize() {
		LoginController.instance = this;
		txt_login_username.setTextFormatter(GuiFormatCreator.username(32));
		txt_login_password.setTextFormatter(GuiFormatCreator.txtMaxField(32));
	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.LOGIN.getFXML()));
		HomeController.instance.Set_Title(FxmlView.LOGIN.getTitle());
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

}
