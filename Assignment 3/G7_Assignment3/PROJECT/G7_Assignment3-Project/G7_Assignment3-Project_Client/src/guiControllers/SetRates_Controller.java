package guiControllers;
import java.util.ArrayList;

import application.GuiFormatCreator;
import guiControllers.Messages.notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


// TODO: Auto-generated Javadoc
/**
 * The Class SetRates_Controller.
 */
public class SetRates_Controller {


	/** The purchase. */
	public static entities.Purchase purchase;
	
	/** The instance. */
	public static SetRates_Controller instance;
	
	/** The type list. */
	ObservableList<String> typeList;
	
	/** The str. */
	ArrayList<String> str = new ArrayList<String>();  
    
    /** The label. */
    @FXML
    private Label label;

    /** The dayshbox. */
    @FXML
    private HBox dayshbox;
    
    /** The pnl overview. */
    @FXML
    private Pane pnlOverview;

    /** The combobox type. */
    @FXML
    private ComboBox<String> combobox_type;

    /** The txt field days. */
    @FXML
    private TextField txt_field_days;

    /** The txt field price. */
    @FXML
    private TextField txt_field_price;

    /** The btn cancel. */
    @FXML
    public Button btn_cancel;

    /** The btn set. */
    @FXML
    private Button btn_set;

    /**
     * Btn cancel click.
     *
     * @param event the event
     */
    @FXML
    void btn_cancel_click(MouseEvent event) {
    	Stage s = (Stage)btn_cancel.getScene().getWindow();
		s.close();
    }
    
    /**
     * Close window.
     */
    public void close_window() {
    	Stage s = (Stage)btn_cancel.getScene().getWindow();
		s.close();
    }
    
    /**
     * Btn set rate.
     *
     * @param event the event
     */
    @FXML
    void btn_set_rate(MouseEvent event) {
    
    	if(combobox_type.getSelectionModel().getSelectedItem() == null) {
    		Stage s = (Stage)btn_cancel.getScene().getWindow();
			notification.show("Please Select a type!", "error", s);
			return;
    	}
    	if(txt_field_price.getText().isEmpty()) {
    		Stage s = (Stage)btn_cancel.getScene().getWindow();
			notification.show("Price is empty!", "error", s);
    		return;
    	}
    	entities.Rate rate;
    	if(combobox_type.getSelectionModel().getSelectedItem().toString().equals("Periodically purchase")) {
    		if(txt_field_days.getText().isEmpty()) {
        		Stage s = (Stage)btn_cancel.getScene().getWindow();
    			notification.show("Days is empty!", "error", s);
        		return;
        	}
    		if(Integer.parseInt(txt_field_days.getText()) > 180 || Integer.parseInt(txt_field_days.getText()) <= 0) {
        		Stage s = (Stage)btn_cancel.getScene().getWindow();
    			notification.show("Maximum days is 180!", "error", s);
    			return;
        	}
    		rate = new entities.Rate(entities.Rate.type.SUBSCRIPTION,Integer.parseInt(txt_field_price.getText()),Integer.parseInt(txt_field_days.getText()));
    		entities.RateRequest request = new entities.RateRequest(HomeController.instance.user, rate);
    		client.requestHandler.ManageRequests.AddOneRateReuestsubscribtion(request);
    		
    	}else if(combobox_type.getSelectionModel().getSelectedItem().toString().equals("One time purchase")) {
    		rate = new entities.Rate(entities.Rate.type.ONETIME, Integer.parseInt(txt_field_price.getText()));
    		entities.RateRequest request = new entities.RateRequest(HomeController.instance.user, rate);
    		client.requestHandler.ManageRequests.AddOneRateReuestonetime(request);
    		
    	}else {
    		Stage s = (Stage)btn_cancel.getScene().getWindow();
			notification.show("Please Select a type!", "error", s);
    	}
    }
    
    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	txt_field_days.setTextFormatter(GuiFormatCreator.txtNumberField(6));
    	txt_field_price.setTextFormatter(GuiFormatCreator.txtNumberField(6));
    	SetRates_Controller.instance = this;
    	str.add("Periodically purchase");
    	str.add("One time purchase");
    	typeList = FXCollections.observableArrayList(str);
    	combobox_type.setItems(typeList);
 
    	combobox_type.valueProperty().addListener((obs, oldItem, newItem) -> {   //By clicking on a value within the combobox, the state will be drawn//
            if (newItem != null) {
                if(combobox_type.getValue().toString().equals("Periodically purchase")) {
                	dayshbox.setVisible(true);
                }else {
                	dayshbox.setVisible(false);
                }
            } else {
                
            }
        });
    }
    
    /**
     * Refresh rates by calling the request handler.
     *
     * @param rates the rates
     */
    public void refresh_rates(ArrayList<entities.Rate> rates) {
   	 ArrayList<String> rates_details = new ArrayList<String>();
   	 for(entities.Rate rate : rates){
   		 if(rate.getRtype().equals(entities.Rate.type.SUBSCRIPTION))
   			 rates_details.add("SUBSCRIPTION  Days:" + rate.getDays() + "  Costs:" + rate.getPrice());
   		 else
   			 rates_details.add("ONETIME  Costs:" + rate.getPrice());
   	 }
      // To Creating a Observable List
      ObservableList<String> obs = FXCollections.observableArrayList(rates_details);
      // Create a ListView
      ListView<String> listView = new ListView<String>(obs);
      // To set multiple selection model
      listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      StackPane root = new StackPane();
      root.getChildren().add(listView);
 
      Stage stage = new Stage();
	  
      Scene scene = new Scene(root, 350, 200);
      stage.setScene(scene);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    }
    
    /**
     * Btn see rates clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_see_rates_clicked(MouseEvent event) {
    	client.requestHandler.ManageRequests.getAllRates();
   
    }
    
    
    
}
    
  
  
    

    

    



    
    








   