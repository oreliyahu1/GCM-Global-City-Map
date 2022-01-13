package guiControllers.Registeration;


import java.io.IOException;

import animatefx.FadeOutRight;
import application.GuiFormatCreator;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.Messages.notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class RegisterController.
 */
public class RegisterController {

	/** The instance. */
	public static RegisterController instance;
    
    /** The registerpane. */
    @FXML
    private Pane registerpane;

    /** The Paymentpane. */
    @FXML
    private Pane Paymentpane;



    /** The txt username. */
    @FXML
    private TextField txt_username;
    
    /** The txt phone. */
    @FXML
    private TextField txt_phone;
    
    /** The txt email. */
    @FXML
    private TextField txt_email;

    /** The txt password. */
    @FXML
    private PasswordField txt_password;

    /** The txt confirm password. */
    @FXML
    private PasswordField txt_confirmPassword;
    
    /** The btn payment. */
    @FXML
    private Button btn_payment;

    /** The txt sub phone. */
    @FXML
    private ChoiceBox<String> txt_subPhone;
    

    /** The txt fname. */
    @FXML
    private TextField txt_fname;

    /** The txt lname. */
    @FXML
    private TextField txt_lname;
	
    /** The newuser. */
    private entities.User newuser;
    
    
	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() {
		RegisterController.instance = this;
		txt_fname.setTextFormatter(GuiFormatCreator.username(50));
		txt_lname.setTextFormatter(GuiFormatCreator.txtMaxField(50));
		txt_username.setTextFormatter(GuiFormatCreator.username(32));
		txt_password.setTextFormatter(GuiFormatCreator.txtMaxField(32));
		txt_confirmPassword.setTextFormatter(GuiFormatCreator.txtMaxField(32));
		txt_phone.setTextFormatter(GuiFormatCreator.txtNumberField(7));
		
	}
	
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.REGISTER.getFXML()));
		HomeController.instance.Set_Title(FxmlView.REGISTER.getTitle());
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
     * Btn click payment. once filled account details check credit card and register 
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void btn_click_payment(ActionEvent event) throws IOException {    	
    	boolean continue_to_payment = true;
    	if(txt_username.getText().isEmpty()) {
    		txt_username.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else
    	{
    		txt_username.getParent().setStyle("-fx-border-color:green;");
    	}
    	if(txt_fname.getText().isEmpty()) {
    		txt_fname.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else
    	{
    		txt_fname.getParent().setStyle("-fx-border-color:green;");
    	}
    	if(txt_lname.getText().isEmpty()) {
    		txt_lname.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else
    	{
    		txt_lname.getParent().setStyle("-fx-border-color:green;");
    	}
    	
 
    	if(txt_subPhone.getSelectionModel().getSelectedItem() == null) {
    		txt_subPhone.setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		txt_subPhone.setStyle("-fx-border-color:green;");
    	}
    	if(txt_phone.getText().isEmpty()) {
    		txt_phone.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		txt_phone.getParent().setStyle("-fx-border-color:green;");
    	}
    	if(txt_phone.getText().isEmpty()) {
    		txt_phone.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		txt_phone.getParent().setStyle("-fx-border-color:green;");
    	}
    	if(txt_email.getText().isEmpty()) {
    		txt_email.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		if(GuiFormatCreator.checkEmail(txt_email.getText()) == true) {
    			txt_email.getParent().setStyle("-fx-border-color:green;");
    		}else {
    			Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
    			notification.show("Incorrect Email Format!", "error", s);
    			continue_to_payment = false;
    		}
    		
    		
    	}

    	if(!(txt_confirmPassword.getText().equals(txt_password.getText()))) {
    		Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
    		notification.show("the two password does not match!", "error", s);
    		txt_confirmPassword.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		txt_confirmPassword.getParent().setStyle("-fx-border-color:green;");
    	}
    	
    	if(txt_confirmPassword.getText().isEmpty()) {
    		txt_confirmPassword.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		txt_confirmPassword.getParent().setStyle("-fx-border-color:green;");
    	}
    	if(txt_password.getText().isEmpty()) {
    		txt_password.getParent().setStyle("-fx-border-color:red;");
    		continue_to_payment = false;
    	}else {
    		txt_password.getParent().setStyle("-fx-border-color:green;");
    		
    	}
    
    	
    	
    	if(!continue_to_payment)
    		return;
    	
    	
    	newuser = new entities.User(txt_fname.getText(), txt_lname.getText(), txt_subPhone.getSelectionModel().getSelectedItem().toString()
    			+ txt_phone.getText(), txt_email.getText(),
    			txt_username.getText(), txt_password.getText(), null);
    	
    	FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			PaymentController LookupForm = new PaymentController();
			try {
				LookupForm.start(HomeController.instance.PANE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
   
    	
    }
    
    /**
     * Gets the user.
     *
     * @return the user
     */
    public entities.User get_user(){
    	return newuser;
    }

}
