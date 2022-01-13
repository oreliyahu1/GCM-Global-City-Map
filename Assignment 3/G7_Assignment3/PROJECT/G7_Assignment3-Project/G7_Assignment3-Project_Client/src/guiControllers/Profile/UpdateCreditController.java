package guiControllers.Profile;


import java.util.Calendar;

import application.GuiFormatCreator;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The Class UpdateCreditController.
 */
public class UpdateCreditController {

    /** The label. */
    @FXML
    private Label label;

    /** The x 71. */
    @FXML
    private Font x71;

    /** The pnl overview. */
    @FXML
    private Pane pnlOverview;

    /** The txt num. */
    @FXML
    private PasswordField txt_num;

    /** The x 3. */
    @FXML
    private Insets x3;

    /** The btn cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn update. */
    @FXML
    private Button btn_update;

    /** The txt cvv. */
    @FXML
    private PasswordField txt_cvv;

    /** The combo month. */
    @FXML
    private ComboBox<Integer> combo_month;

    /** The combo year. */
    @FXML
    private ComboBox<Integer> combo_year;
    
    /** The x 311. */
    @FXML
    private Insets x311;
    
    /** The instance. */
    public static UpdateCreditController instance;
    
    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	UpdateCreditController.instance = this;
    	
    	txt_num.setTextFormatter(GuiFormatCreator.txtNumberField(16));
    	txt_cvv.setTextFormatter(GuiFormatCreator.txtNumberField(3));
	
    	
    	Calendar now = Calendar.getInstance();
		 
    	 ObservableList<Integer> year = FXCollections.observableArrayList();
		 ObservableList<Integer> month = FXCollections.observableArrayList();
		 for(int i=0 ; i<12 ; i++) {
			 year.add( (now.get(Calendar.YEAR) + i));
			 month.add((i+1));
		 }
		 combo_year.setItems(year);
		 combo_month.setItems(month);
		 
		 
		 
	}
    
    /**
     * Close window.
     */
    public void close_window() {
		Stage s = (Stage) btn_update.getScene().getWindow();
		s.close();
	}

	/**
	 * Btn cancel click.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_cancel_click(MouseEvent event) {
		Stage s = (Stage) btn_update.getScene().getWindow();
		s.close();
	}

    /**
     * Btn update Credit card click.
     *
     * @param event the event
     */
    @FXML
    void btn_update_pass_click(MouseEvent event) {
    	Stage s = (Stage) btn_update.getScene().getWindow();
    	if(combo_year.getSelectionModel().getSelectedItem() == null || combo_month.getSelectionModel().getSelectedItem() == null ||
    			txt_cvv.getText().isEmpty() ) {
    		notification.show("Please Fill All Fields!", "error",s);
    	}else {
    		String date = combo_year.getSelectionModel().getSelectedItem().intValue() + "-" + combo_month.getSelectionModel().getSelectedItem().intValue() + "-01";
    		long num = Long.parseLong(txt_num.getText()); 
    		
    		entities.CreditCard card = new entities.CreditCard(HomeController.instance.user,num, Integer.valueOf(txt_cvv.getText()), date);
    		client.requestHandler.Profile.update_credit(card);
    	}
    }

}
