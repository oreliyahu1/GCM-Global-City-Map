/*
 * 
 */
package guiControllers;

import java.io.File;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import application.GuiFormatCreator;
import application.Logger.Print;
import application.ServerMain;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import server.EchoServer;
import server.ocsf.OriginatorMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerController.
 */
@SuppressWarnings("deprecation") // Because of using observable
public class ServerController {
	
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

    /** The txtf db hostname. */
    @FXML
    private TextField txtf_db_hostname;

    /** The txtf db schema. */
    @FXML
    private TextField txtf_db_schema;

    /** The txtf db username. */
    @FXML
    private TextField txtf_db_username;

    /** The txtf db password. */
    @FXML
    private TextField txtf_db_password;

    /** The txtf server ip. */
    @FXML
    private TextField txtf_server_ip;

    /** The txtf server port. */
    @FXML
    private TextField txtf_server_port;

    /** The txtf online usersonline. */
    @FXML
    private TextField txtf_online_usersonline;

    /** The txtf online runtime. */
    @FXML
    private TextField txtf_online_runtime;

    /** The txtf online status. */
    @FXML
    private TextField txtf_online_status;

    /** The cbox log. */
    @FXML
    private CheckBox cbox_log;

    /** The cbox clients. */
    @FXML
    private CheckBox cbox_clients;

    /** The btn loadcsv. */
    @FXML
    private Button btn_loadcsv;

    /** The txtf maxbuffer. */
    @FXML
    private TextField txtf_maxbuffer;
    
    /** The listview clients. */
    @FXML
    private ListView<String> listview_clients;
    
    /** The list clients. */
    private ObservableList<String> list_clients;
    
	/**
	 * Sets the offline.
	 */
	private void setOffline() {
	    txtf_online_usersonline.setText("0");
	    if(online_runtime!=null) online_runtime.stop();
	    txtf_online_runtime.setText("00:00:00");
	    txtf_online_status.setText("OFF");
		setEditableConfig(true);
		btn_connect.setText("Connect");
		list_clients.clear();
	}
	
	/**
	 * Inits the echo server observer.
	 * Adds observer to handle triggers of server service
	 */
	private void initEchoServerObserver() {
		ServerMain.srv.addObserverEchoServer(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				Platform.runLater(new Runnable() {	
					@Override
					public void run() {
						switch((String)((OriginatorMessage)arg).getMessage()) {
							case EchoServer.CLIENT_CONNECTED:
								if(cbox_clients.isSelected())
									list_clients.setAll(ServerMain.srv.getClientsConnected());
								txtf_online_usersonline.setText(String.valueOf(ServerMain.srv.getNumberOfClients()));
								break;
							case EchoServer.CLIENT_DISCONNECTED:
								if(cbox_clients.isSelected())
									list_clients.setAll(ServerMain.srv.getClientsConnected());
								txtf_online_usersonline.setText(String.valueOf(ServerMain.srv.getNumberOfClients()));
								break;
							case EchoServer.CLIENT_EXCEPTION:
								if(cbox_clients.isSelected())
									list_clients.setAll(ServerMain.srv.getClientsConnected());
								txtf_online_usersonline.setText(String.valueOf(ServerMain.srv.getNumberOfClients()));
								break;
							case EchoServer.SERVER_STARTED:
								list_clients.clear();
								setOnline();
								break;
							case EchoServer.SERVER_CLOSED:
								setOffline();
								break;
							case EchoServer.SERVER_STOPPED:
								setOffline();
								break;
							case EchoServer.LISTENING_EXCEPTION:
								setOffline();
								break;	
							case EchoServer.USERNAME_LOGIN:
								if(cbox_clients.isSelected())
									list_clients.setAll(ServerMain.srv.getClientsConnected());
								break;	
							case EchoServer.USERNAME_LOGOUT:
								if(cbox_clients.isSelected())
									list_clients.setAll(ServerMain.srv.getClientsConnected());
								break;	
							case EchoServer.USERNAME_TOKEN_EXPIRED:
								if(cbox_clients.isSelected())
									list_clients.setAll(ServerMain.srv.getClientsConnected());
								break;	
						}
					}
				});
			}
		});
	}
	
    /**
     * Click checkbox clients.
     *
     * @param event the event
     */
    @FXML
    void click_checkbox_clients(ActionEvent event) {
    	if(cbox_clients.isSelected() && ServerMain.srv.isConnected()) {
    		list_clients.setAll(ServerMain.srv.getClientsConnected());
    	}else {
        	list_clients = FXCollections.observableArrayList();	
    		listview_clients.setItems(list_clients);
    	}
    }

    /**
     * Click checkbox log.
     *
     * @param event the event
     */
    @FXML
    void click_checkbox_log(ActionEvent event) {
    	if(cbox_log.isSelected()){
    		ServerMain.srv.getRunSettings().setMaxKBLogBuffer(Integer.parseInt(txtf_maxbuffer.getText()));
    		ServerMain.srv.getRunSettings().save();
    		ServerMain.log.setMaxKBBuffer(ServerMain.srv.getRunSettings().getMaxKBLogBuffer());
    		txtf_maxbuffer.setEditable(false);
    	}
    	else
    		txtf_maxbuffer.setEditable(true);
    	
    	switch(ServerMain.log.getPrint()){
    		case BOTH:
    			ServerMain.log.setPrint(Print.CONSOLE);
    			break;
    		case NEWOUTPUT:
    			ServerMain.log.setPrint(Print.NOBODY);
    			break;
    		case CONSOLE:
    			ServerMain.log.setPrint(Print.BOTH);
    			break;
    		case NOBODY:
    			ServerMain.log.setPrint(Print.NEWOUTPUT);
    			break;
    	}
    }
    
    /**
     * Btn click loadcsv.
     *
     * @param event the event
     */
    @FXML
    void btn_click_loadcsv(ActionEvent event) {
    	DirectoryChooser directoryChooser = new DirectoryChooser();
    	File initFolder = new File("initializeDB/");
    	if(initFolder.exists())
    		directoryChooser.setInitialDirectory(initFolder);
    	File selectedDirectory = directoryChooser.showDialog((Stage)btn_loadcsv.getScene().getWindow());

    	if(selectedDirectory != null) {
    		Alert alert = new Alert(AlertType.WARNING, "This may take a while if you have define a print mode in the LOG", ButtonType.OK, ButtonType.CANCEL);
    		alert.showAndWait();
    		if(alert.getResult() == ButtonType.OK)
    			ServerMain.srv.initializationDBTables(selectedDirectory);
    	}
    }

    
	/**
	 * Sets the online.
	 */
	private void setOnline() {
	    txtf_online_usersonline.setText("0");
	    txtf_online_runtime.setText("00:00:00");
	    txtf_online_status.setText("ON");
		online_runtime_start = System.currentTimeMillis();
	    online_runtime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        LocalTime triggerTime =  LocalTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()-online_runtime_start), ZoneOffset.UTC);  
	        txtf_online_runtime.setText(triggerTime.format(formatter));
	    } ));
	    online_runtime.setCycleCount(Animation.INDEFINITE);// Its necessary for the update timer field, running infinity
	    online_runtime.play();
		setEditableConfig(false);
		btn_connect.setText("Stop");
		}
	
	/** 
	 * Initialize.
	 */
	@FXML
    private void initialize() 
    {	
		ServerMain.log.setNewOutPutStream(txtfield_log);
		if(ServerMain.log.getPrint() == Print.NOBODY)
			ServerMain.log.setPrint(Print.NEWOUTPUT);
		else if(ServerMain.log.getPrint() == Print.CONSOLE) 
			ServerMain.log.setPrint(Print.BOTH);
		txtf_server_ip.setText(ServerMain.srv.getRunSettings().getIp());
		txtf_server_port.setText(Integer.toString(ServerMain.srv.getRunSettings().getPort()));
		txtf_db_hostname.setText(ServerMain.srv.getRunSettings().getDb().getDbHostname());
		txtf_db_schema.setText(ServerMain.srv.getRunSettings().getDb().getDbSchema());
		txtf_db_username.setText(ServerMain.srv.getRunSettings().getDb().getDbUsername());
		txtf_db_password.setText(ServerMain.srv.getRunSettings().getDb().getDbPassword());
		txtf_maxbuffer.setText(Integer.toString(ServerMain.srv.getRunSettings().getMaxKBLogBuffer()));
		setEditableConfig(true);
		txtf_online_usersonline.setEditable(false);
		txtf_online_runtime.setEditable(false);
		txtf_online_status.setEditable(false);
		txtf_server_ip.setEditable(false);
		txtf_maxbuffer.setEditable(false);
    	list_clients = FXCollections.observableArrayList();
    	listview_clients.setItems(list_clients);
		setOffline();
		txtf_maxbuffer.setTextFormatter(GuiFormatCreator.onlyNumber());
		txtf_server_port.setTextFormatter(GuiFormatCreator.onlyNumber());
		System.out.println("Waiting for connecting");
		initEchoServerObserver();
    }

	/**
	 * Sets the editable config.
	 *
	 * @param disable the new editable config
	 */
	private void setEditableConfig(boolean disable) {
		txtf_db_hostname.setEditable(disable);
		txtf_db_schema.setEditable(disable);
		txtf_db_username.setEditable(disable);
		txtf_db_password.setEditable(disable);
		txtf_server_port.setEditable(disable);
		btn_loadcsv.setDisable(disable);
	}
	
	/**
	 * Check before connect.
	 * Checking fields before making a connection
	 * @return true, if successful
	 */
	private boolean checkBeforeConnect() {
    	if(GuiFormatCreator.checkEmpty(txtf_db_hostname.getText(), txtf_db_password.getText(), txtf_db_schema.getText(), txtf_db_username.getText(), txtf_server_ip.getText(), txtf_server_port.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "One of the fields is empty", ButtonType.OK);
    		alert.showAndWait();
    		return false;
    	}
    	if(!GuiFormatCreator.checkPort(txtf_server_port.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Server port need to have a valid port", ButtonType.OK);
    		alert.showAndWait();
    		return false;
    	}
    	if(!GuiFormatCreator.checkIPAddress(txtf_db_hostname.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "DB hostname need to have a valid ip address", ButtonType.OK);
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
     * Btn click connect.
     *
     * @param event the event
     */
    @FXML
    private void btn_click_connect(ActionEvent event) {
    	if(!checkBeforeConnect()) return;
    	
    	ServerMain.srv.getRunSettings().setIp(txtf_server_ip.getText());
    	ServerMain.srv.getRunSettings().setPort(Integer.parseInt(txtf_server_port.getText()));
    	ServerMain.srv.getRunSettings().getDb().setDbHostname(txtf_db_hostname.getText());
    	ServerMain.srv.getRunSettings().getDb().setDbSchema(txtf_db_schema.getText());
    	ServerMain.srv.getRunSettings().getDb().setDbUsername(txtf_db_username.getText());
    	ServerMain.srv.getRunSettings().getDb().setDbPassword(txtf_db_password.getText());
    	ServerMain.srv.getRunSettings().setMaxKBLogBuffer(Integer.parseInt(txtf_maxbuffer.getText()));
		
    	if(!ServerMain.srv.isConnected())
    		ServerMain.srv.runServer();
    	else
    		ServerMain.srv.stopServer();
    }
    
}