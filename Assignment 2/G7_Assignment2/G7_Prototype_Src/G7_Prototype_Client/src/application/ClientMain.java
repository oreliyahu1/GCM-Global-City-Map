package application;

import java.io.IOException;
import client.ClientService;
import entities.Settings;
import guiControllers.LoadingController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * The Class ClientMain.
 */
public class ClientMain extends Application {
	
	/** The csrv- main service for managing communication protocol . */
	public static ClientService cSrv;
	
	/** The log-variable for collecting system.out request  */
	public static Logger log;
	
	/**
	 * Load loading window.
	 */
	private void loadLoadingWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Loading.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            LoadingController lodCon = fxmlLoader.getController();
            lodCon.setStage(stage);
            stage.setResizable(false);
            stage.setMaximized(false);
            scene.setFill(Color.TRANSPARENT); //creating a transparent form
            stage.initStyle(StageStyle.TRANSPARENT); //creating a transparent form without window's top bar
            stage.setTitle("Loading");
            stage.initModality(Modality.APPLICATION_MODAL);   //blocks all top-level windows from the same application
            stage.setOnCloseRequest(e->{ safeExit(); });
            stage.setScene(scene);
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			loadLoadingWindow();
			LoadingController.instance.show();
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ClientWindow.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
		    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("GCM - CLIENT");
			primaryStage.setOnCloseRequest(e->{ safeExit(); });
			LoadingController.instance.hide();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Safe exit.
	 * confirmation that log-out was successfully done and the log was closed as well.
	 */
	@SuppressWarnings("deprecation")
	public static void safeExit() {
		if(cSrv != null) { cSrv.deleteObservers();
		cSrv.stopClient(); 
		cSrv = null; }
		if(log != null) { log.close(); log = null; }
		Platform.exit();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Settings runSettings = new Settings(config.defaultConfig.Server.SERVER_DEFAULT_PORT, config.defaultConfig.Server.SERVER_DEFAULT_IP);
		ClientMain.cSrv = ClientService.getClientService(runSettings);
		log=null;
		launch(args);
	}
}
