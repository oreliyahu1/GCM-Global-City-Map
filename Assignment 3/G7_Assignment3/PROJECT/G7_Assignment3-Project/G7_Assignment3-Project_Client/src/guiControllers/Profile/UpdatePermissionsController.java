package guiControllers.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class UpdatePermissionsController.
 * 
 * 
 * 
 * 
 * NOT USED CLASS
 * 
 * 
 * 
 * 
 * 
 */
public class UpdatePermissionsController {

	/** btn = button **/
	
    /** The pnl overview. */
    @FXML
    private Pane pnlOverview;

    /** The button cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn update. */
    @FXML
    private Button btn_update;

    /** The purchase. */
    @FXML
    private CheckBox purchase;

    /** The client. */
    @FXML
    private RadioButton client;

    /** The worker. */
    @FXML
    private RadioButton worker;

    /** The productdworker. */
    @FXML
    private RadioButton productdworker;

    /** The productdmanager. */
    @FXML
    private RadioButton productdmanager;

    /** The editcatalog. */
    @FXML
    private CheckBox editcatalog;

    /** The deletecatalog. */
    @FXML
    private CheckBox deletecatalog;

    /** The raterequest. */
    @FXML
    private CheckBox raterequest;

    /** The rateapproval. */
    @FXML
    private CheckBox rateapproval;

    /** The versionrequest. */
    @FXML
    private CheckBox versionrequest;

    /** The versionapproval. */
    @FXML
    private CheckBox versionapproval;

    /** The viewcatalog. */
    @FXML
    private CheckBox viewcatalog;

    /** The viewreport. */
    @FXML
    private CheckBox viewreport;

    /** The usermanagment. */
    @FXML
    private CheckBox usermanagment;
    
    /**
     * Close window.
     */
    public void close_window() {
		Stage s = (Stage) usermanagment.getScene().getWindow();
		s.close();
	}
    
    /**
     * Btn cancel click.
     *
     * @param event the event
     */
    @FXML
    void btn_cancel_click(MouseEvent event) {
    	Stage s = (Stage) usermanagment.getScene().getWindow();
		s.close();
    }

    /**
     * Btn edit permission click.
     *
     * @param event the event
     */
    @FXML
    void btn_edit_permission_click(MouseEvent event) {

    }

}
