package guiControllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


/**
 * The Class DownloadController. NOT USED
 * 
 * 
 * 
 * NOT USED CLASS
 * 
 * 
 */
public class DownloadController {

	/** The instance. */
	public static DownloadController instance;
	
	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		DownloadController.instance = this;
	}
	
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.DOWNLOADS.getFXML()));
		HomeController.instance.Set_Title(FxmlView.DOWNLOADS.getTitle());
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
}
