package guiControllers.debuggingMode;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import application.ClientMain;
import application.GuiFormatCreator;
import application.Logger.Print;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * The Class DebuggingModeController.
 */
@SuppressWarnings("deprecation") // Because of using observable
public class DebuggingModeController {
	
	private double x,y;
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
    
    @FXML
    private Button btn_startApplication;
    
    @FXML
    private CheckBox cbox_log;
    
    @FXML
    private TextField txtf_maxbuffer;
    
    @FXML
    void click_checkbox_log(ActionEvent event) {
    	if(cbox_log.isSelected()){
    		ClientMain.cSrv.getRunSettings().setMaxKBLogBuffer(Integer.parseInt(txtf_maxbuffer.getText()));
    		ClientMain.cSrv.getRunSettings().save();
    		ClientMain.log.setMaxKBBuffer(ClientMain.cSrv.getRunSettings().getMaxKBLogBuffer());
    		txtf_maxbuffer.setEditable(false);
    	}
    	else
    		txtf_maxbuffer.setEditable(true);
    	
	    	switch(ClientMain.log.getPrint()){
			case BOTH:
				ClientMain.log.setPrint(Print.CONSOLE);
				break;
			case NEWOUTPUT:
				ClientMain.log.setPrint(Print.NOBODY);
				break;
			case CONSOLE:
				ClientMain.log.setPrint(Print.BOTH);
				break;
			case NOBODY:
				ClientMain.log.setPrint(Print.NEWOUTPUT);
				break;
	    }
    }
	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() 
    {	
		ClientMain.log.setNewOutPutStream(txtfield_log);
		if(ClientMain.log.getPrint() == Print.NOBODY)
			ClientMain.log.setPrint(Print.NEWOUTPUT);
		else if(ClientMain.log.getPrint() == Print.CONSOLE)
			ClientMain.log.setPrint(Print.BOTH);
		txtf_server_ip.setText(ClientMain.cSrv.getRunSettings().getIp());
		txtf_server_port.setText(Integer.toString(ClientMain.cSrv.getRunSettings().getPort()));
		txtf_maxbuffer.setText(Integer.toString(ClientMain.cSrv.getRunSettings().getMaxKBLogBuffer()));
		txtf_maxbuffer.setEditable(false);
		setEditableConfig(true);
		setOffline();
	    txtf_online_runtime.setEditable(false);
	    txtf_online_status.setEditable(false);
	    txtf_server_port.setTextFormatter(GuiFormatCreator.onlyNumber());
	    initClientObserver();
		System.out.println("Waiting for connecting");
		btn_click_connect(null);
		btn_click_startApplication(null);
    }
	
    @FXML
    void btn_click_startApplication(ActionEvent event) {
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
					if(btn_startApplication != null)
						btn_startApplication.setVisible(true);
			});
			stage.show();
			btn_startApplication.setVisible(false);
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
    	
    	if(!ClientMain.cSrv.isConnected())
    		ClientMain.cSrv.runClient();
    	else
    		ClientMain.cSrv.stopClient();
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
							case ObservableClient.CONNECTION_ESTABLISHED:
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
		btn_connect.setText("Connect");
	}
}