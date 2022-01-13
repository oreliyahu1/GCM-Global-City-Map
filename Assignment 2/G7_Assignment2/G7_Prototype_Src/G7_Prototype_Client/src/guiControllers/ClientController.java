package guiControllers;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import application.ClientMain;
import application.GuiFormatCreator;
import application.Logger;
import client.ocsf.ObservableClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class ClientController.
 */
@SuppressWarnings("deprecation") // Because of using observable
public class ClientController {
	
	/** The online runtime. */
	private Timeline online_runtime;
	
	/** The online runtime start. */
	private long online_runtime_start;
	
    /** The txtfield log. */
    @FXML
    private TextArea txtfield_log;

    /** The btn connect. */
    @FXML
    private Button btn_connect;

    /** The btn edit city. */
    @FXML
    private Button btn_editCity;
    
    /** The txtf server ip. */
    @FXML
    private TextField txtf_server_ip;

    /** The txtf server port. */
    @FXML
    private TextField txtf_server_port;

    /** The txtf online runtime. */
    @FXML
    private TextField txtf_online_runtime;

    /** The txtf online status. */
    @FXML
    private TextField txtf_online_status;
    
	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() 
    {	
		ClientMain.log = new Logger(txtfield_log, false);
		txtf_server_ip.setText(ClientMain.cSrv.getRunSettings().getIp());
		txtf_server_port.setText(Integer.toString(ClientMain.cSrv.getRunSettings().getPort()));
		setEditableConfig(true);
		btn_editCity.setVisible(false);
		setOffline();
	    txtf_online_runtime.setEditable(false);
	    txtf_online_status.setEditable(false);
	    txtf_server_port.setTextFormatter(GuiFormatCreator.onlyNumber());
	    initClientObserver();
		System.out.println("Waiting for connecting");
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
    	
    	if(!ClientMain.cSrv.isConnected())
    		ClientMain.cSrv.runClient();
    	else
    		ClientMain.cSrv.stopClient();
    }
    
    /**
     * Btn click edit city.
     * Initialize and open city window.
     * @param event the event
     */
    @FXML
    void btn_click_editCity(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Window/City.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/cityIcon.png")));
		    scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Edit city");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
        	System.out.println(e.getMessage());
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
	
	/**
	 * Sets the editable config.
	 *
	 * @param disable the new editable config
	 */
	private void setEditableConfig(boolean disable) {
		txtf_server_port.setEditable(disable);
		txtf_server_ip.setEditable(disable);
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
					    		setOffline();
					    		LoadingController.instance.show(btn_connect.getScene().getWindow(),"Connection lost, try to reconnect");
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
								setOnline();
								break;
							case ObservableClient.CONNECTION_CLOSED:
								setOffline();
								break;
						}
					}
				});
			}
		});
	}
	
	/**
	 * Sets the online.
	 * Initialize fields and create timer
	 */
	private void setOnline() {
	    txtf_online_runtime.setText("00:00:00");
	    txtf_online_status.setText("ON");
		online_runtime_start = System.currentTimeMillis();
	    online_runtime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        LocalTime triggerTime =  LocalTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() - online_runtime_start), ZoneOffset.UTC);  
	        txtf_online_runtime.setText(triggerTime.format(formatter));
	    } ));
	    online_runtime.setCycleCount(Animation.INDEFINITE); // Its necessary for the update timer field
	    online_runtime.play();
		setEditableConfig(false);
		btn_editCity.setVisible(true);
		btn_connect.setText("Disconnect");
	}
	
	/**
	 * Sets the offline.
	 */
	private void setOffline() {
	    if(online_runtime!=null) online_runtime.stop();
	    txtf_online_runtime.setText("00:00:00");
	    txtf_online_status.setText("OFF");
		setEditableConfig(true);
		btn_editCity.setVisible(false);
		btn_connect.setText("Connect");
	}
}