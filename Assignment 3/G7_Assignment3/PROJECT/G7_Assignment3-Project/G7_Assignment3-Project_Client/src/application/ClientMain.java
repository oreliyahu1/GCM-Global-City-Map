package application;

import java.io.IOException;
import application.Logger.Print;
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

// TODO: Auto-generated Javadoc
/**
 * The Class ClientMain.
 */
public class ClientMain extends Application {

	/** The y. */
	private double x, y;
	/** The csrv- main service for managing communication protocol . */
	public static ClientService cSrv;

	/** The log-variable for collecting system.out request */
	public static Logger log;

	/** The debugging mode. */
	private static Boolean debuggingMode = false;

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
			scene.setFill(Color.TRANSPARENT); // creating a transparent form
			stage.initStyle(StageStyle.TRANSPARENT); // creating a transparent form without window's top bar
			stage.setTitle("Loading");
			stage.initModality(Modality.APPLICATION_MODAL); // blocks all top-level windows from the same application
			stage.setOnCloseRequest(e -> {
				safeExit();
			});
			stage.setScene(scene);
			LoadingController.instance.show();
			LoadingController.instance.hide();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Load debugger mode.
	 */
	public void loadDebuggerMode() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/debuggingMode/DebuggingModeWindow.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			
			Stage primaryStage = new Stage();
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
			scene.getStylesheets().add(getClass().getResource("/debuggingMode/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("GCM - Debugging Mode");
			primaryStage.setOnCloseRequest(e -> {
				safeExit();
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		loadLoadingWindow();
		if (ClientMain.debuggingMode) {
			loadDebuggerMode();
			
		} else {
			ClientMain.cSrv.runClient();
			if (ClientMain.cSrv.isConnected()) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Home.fxml"));
					Scene scene = new Scene(fxmlLoader.load());
					primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.initStyle(StageStyle.TRANSPARENT); // set stage borderless (without max,min.. etc)
					primaryStage.setTitle("GCM Application");

					scene.setOnMousePressed(event1 -> {
						x = event1.getSceneX();
						y = event1.getSceneY();
					});
					scene.setOnMouseDragged(event2 -> {

						primaryStage.setX(event2.getScreenX() - x);
						primaryStage.setY(event2.getScreenY() - y);

					});

					primaryStage.setOnCloseRequest(e -> {
						safeExit();
					});
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/configMode/ConfigModeWindow.fxml"));
					Scene scene = new Scene(fxmlLoader.load());
				
					primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.setTitle("GCM - Config Mode");
					primaryStage.setOnCloseRequest(e -> {
						safeExit();
					});
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Safe exit. confirmation that log-out was successfully done and the log was
	 * closed as well.
	 */
	@SuppressWarnings("deprecation")
	public static void safeExit() {
		if (cSrv != null) {
			cSrv.deleteObservers();
			cSrv.stopClient();
			cSrv = null;
		}
		if (log != null) {
			log.close();
			log = null;
		}
		Platform.exit();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ClientMain.log = new Logger(Print.NOBODY);		
		Settings runSettings = new Settings(config.Settings.SERVER_DEFAULT_PORT,
				config.Settings.SERVER_DEFAULT_IP, config.Settings.LOGGER_DEFAULT_MAXBUFFER);
		ClientMain.cSrv = ClientService.getClientService(runSettings);
		if(args.length > 0) {
			if (args[0].equals("debuggingMode"))
				ClientMain.debuggingMode = true;
			try {
				ClientMain.log.setPrint(Print.valueOf(args[1]));
			} catch(Exception ex) {}
		}
		launch(args);
	}
}
