package guiControllers.Profile;

import application.GuiFormatCreator;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The Class UpdatePasswordController.
 */
public class UpdatePasswordController {

	/** The txt current. */
	@FXML
	private PasswordField txt_current;

	/** The btn cancel. */
	@FXML
	private Button btn_cancel;

	/** The btn edit. */
	@FXML
	private Button btn_edit;

	/** The txt new. */
	@FXML
	private PasswordField txt_new;

	/** The txt new confirm. */
	@FXML
	private PasswordField txt_new_confirm;
	
	/** The instace. */
	public static UpdatePasswordController instace;
	
	/**
	 * Initialize.
	 */
	@FXML
    void initialize() {
		UpdatePasswordController.instace = this;
		txt_new_confirm.setTextFormatter(GuiFormatCreator.txtMaxField(32));
		txt_new.setTextFormatter(GuiFormatCreator.txtMaxField(32));
		txt_current.setTextFormatter(GuiFormatCreator.txtMaxField(32));
	}

	/**
	 * Close window.
	 */
	public void close_window() {
		Stage s = (Stage) btn_edit.getScene().getWindow();
		s.close();
	}

	/**
	 * Btn cancel click.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_cancel_click(MouseEvent event) {
		Stage s = (Stage) btn_edit.getScene().getWindow();
		s.close();
	}

	/**
	 * Btn update pass click.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_update_pass_click(MouseEvent event) {
		Stage s = (Stage) btn_edit.getScene().getWindow();
		
		if(!(txt_current.getText().equals(HomeController.instance.user.getPassword()))) {
			notification.show("Current Password is Incorrect!", "error",s);
		}else {
			if(txt_new.getText().equals(txt_new_confirm.getText())) {
				if(txt_new.getText().isEmpty() || txt_new_confirm.getText().isEmpty()) {
					notification.show("New Password is empty!", "error",s);
				}else {
					entities.User newuser = new entities.User(HomeController.instance.user.getUsername());
		    		newuser.setPassword(txt_new.getText());
		    		client.requestHandler.Profile.update_password(newuser);
				}
			}else {
				notification.show("Password does not match!", "error",s);
			}
		}
		
	}

}
