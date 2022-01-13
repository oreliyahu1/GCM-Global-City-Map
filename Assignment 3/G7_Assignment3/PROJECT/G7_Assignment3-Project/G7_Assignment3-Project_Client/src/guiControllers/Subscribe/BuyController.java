package guiControllers.Subscribe;

import java.io.IOException;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class BuyController.
 */
public class BuyController {

	/** The instance. */
	public static BuyController instance;
	
	/** The y. */
	private double x, y;
	
	/** The btn subscribe. */
	@FXML
	private Button btn_subscribe;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		BuyController.instance = this;

	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.BUY.getFXML()));
		HomeController.instance.Set_Title(FxmlView.BUY.getTitle());
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
	 * Subscribe click. on click buy collection of map or download it
	 *
	 * @param event the event
	 */
	@FXML
	void subscribe_click(MouseEvent event) {
		// open secondery scene buy
	
		Scene scene = null;
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/subscribe/Subscribe.fxml"));
			scene = new Scene(fxmlLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("Subscribe");
		stage.setScene(scene);

		scene.setOnMousePressed(event1 -> {
			x = event1.getSceneX();
			y = event1.getSceneY();
		});
		scene.setOnMouseDragged(event2 -> {

			stage.setX(event2.getScreenX() - x);
			stage.setY(event2.getScreenY() - y);

		});

		stage.show();
		// Hide this current window (if this is what you want)

	}

}
