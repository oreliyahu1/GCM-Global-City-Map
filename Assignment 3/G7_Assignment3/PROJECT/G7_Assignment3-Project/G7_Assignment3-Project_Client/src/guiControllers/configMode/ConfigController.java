package guiControllers.configMode;

import application.ClientMain;
import application.GuiFormatCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class DebuggingModeController.
 */
public class ConfigController {

	private double x,y;
    /** The btn connect. */
    @FXML
    private Button btn_connect;
    
    /** The txtf server ip. */
    @FXML
    private TextField txtf_server_ip;

    /** The txtf server port. */
    @FXML
    private TextField txtf_server_port;
    
	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() 
    {	
		txtf_server_ip.setText(ClientMain.cSrv.getRunSettings().getIp());
		txtf_server_port.setText(Integer.toString(ClientMain.cSrv.getRunSettings().getPort()));
	    txtf_server_port.setTextFormatter(GuiFormatCreator.onlyNumber());
		Alert alert = new Alert(AlertType.ERROR, "Cannot connect to server", ButtonType.OK);
		alert.showAndWait();
    }

	private void openApplication() {
		try {
			Stage stage = new Stage();
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initStyle(StageStyle.TRANSPARENT);  //set stage borderless (without max,min.. etc)
			stage.setTitle("GCM Application");
			//if of != null because of force exit from all windows
			scene.setOnMousePressed(event1 -> {
				x = event1.getSceneX();
				y = event1.getSceneY();
			});
			scene.setOnMouseDragged(event2 -> {

				stage.setX(event2.getScreenX() - x);
				stage.setY(event2.getScreenY() - y);

			});
			
			stage.setOnCloseRequest(e->{
					if(stage != null)
						stage.close();
					if(ClientMain.cSrv != null)
						ClientMain.cSrv.stopClient();
			});
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * Btn click connect.
     *
     * @param event the event
     */
    @FXML
    void btn_click_connect(ActionEvent event) {
    	if(!checkBeforeConnect()) return;
    	
    	ClientMain.cSrv.getRunSettings().setIp(txtf_server_ip.getText());
    	ClientMain.cSrv.getRunSettings().setPort(Integer.parseInt(txtf_server_port.getText()));
    	
    	ClientMain.cSrv.runClient();
    	if(ClientMain.cSrv.isConnected()) {
    		openApplication();
    		((Stage)btn_connect.getScene().getWindow()).hide();
    	}else {
			Alert alert = new Alert(AlertType.ERROR, "Cannot connect to server", ButtonType.OK);
			alert.showAndWait();
    	}	
    }

	/**
	 * Check before connect.
	 *
	 * @return true, if successful
	 */
	private boolean checkBeforeConnect() {
    	if(GuiFormatCreator.checkEmpty(txtf_server_ip.getText(), txtf_server_port.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "One of the fields is empty", ButtonType.OK);
    		alert.showAndWait();
    		return false;
    	}
    	if(!GuiFormatCreator.checkPort(txtf_server_port.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Server port need to have a valid port", ButtonType.OK);
    		alert.showAndWait();
    		return false;
    	}
    	if(!GuiFormatCreator.checkIPAddress(txtf_server_ip.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Server ip need to have a valid ip address", ButtonType.OK);
    		alert.showAndWait();
    		return false;		
    	}
    	return true;
	}
}