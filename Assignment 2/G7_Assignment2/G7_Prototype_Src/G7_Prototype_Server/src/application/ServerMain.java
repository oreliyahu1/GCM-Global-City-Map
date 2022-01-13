package application;
	
import java.net.InetAddress;
import java.net.UnknownHostException;

import entities.Database;
import entities.Settings;
import server.ServerService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * The Class ServerMain.
 */
public class ServerMain extends Application {
	
	/** The srv- main service for managing communication protocol . */
	public static ServerService srv;
	
	/** The log-variable for collecting system.out request  */
	public static Logger log;
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Server.fxml"));
		    //ServerController srvC = fxmlLoader.getController();
			Scene scene = new Scene(fxmlLoader.load());
		    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("GCM - SERVER");
			//Safe exit.
			//confirmation that log-out was successfully done and the log was closed as well.
			primaryStage.setOnCloseRequest(e->{ 
				if(srv != null) { 
				srv.deleteObservers();
				srv.stopServer(); 
				srv = null; }
				if(log != null) { log.close(); log = null; }
				Platform.exit(); });
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the local host ip of  computer
	 * @throws return the default window's ip
	 * @return the local host ip 
	 */
	public static String getLocalHostIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return config.defaultConfig.Server.SERVER_DEFAULT_IP;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Settings runSettings = new Settings(config.defaultConfig.Server.SERVER_DEFAULT_PORT, ServerMain.getLocalHostIp(), new Database(config.defaultConfig.Database.DB_DEFAULT_URL, config.defaultConfig.Database.DB_DEFAULT_HOSTNAME, config.defaultConfig.Database.DB_DEFAULT_PORT, config.defaultConfig.Database.DB_DEFAULT_SCHEMA, config.defaultConfig.Database.DB_DEFAULT_USERNAME, config.defaultConfig.Database.DB_DEFAULT_PASSWORD, config.defaultConfig.Database.DB_DEFAULT_SERVERTIMEZONE));
		ServerMain.srv = ServerService.getServerService(runSettings);
		log = null;
		launch(args);
	}
}
