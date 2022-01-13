package guiControllers;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import animatefx.FadeOutUp;
import animatefx.ZoomOut;

import entities.Rate;
import entities.Activity.type;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * The Class Reports_Controller.
 */
public class Reports_Controller {

	/** The datefrom. */
	@FXML
	private DatePicker datefrom;

	/** The dateto. */
	@FXML
	private DatePicker dateto;

	/** The combo city. */
	@FXML
	private ComboBox<String> combo_city;

	/** The combo country. */
	@FXML
	private ComboBox<String> combo_country;

	/** The btn report. */
	@FXML
	private Button btn_report;

	/** The btn create. */
	@FXML
	private Button btn_create;
	
	/** The report panee. */
	@FXML
	private Pane report_panee;

	/** The checkbox all. */
	@FXML
	private CheckBox checkbox_all;
	
	/** The select city. */
	private entities.City select_city;
	 
 	/** The line chart. */
 	LineChart<String, Number> lineChart ;
	
	/** The total users. */
	private int total_users=0;
	
	/** The total messages. */
	private int total_messages = 0;
	
	/** The total rates. */
	private int total_rates=0;
	
	/** The total version. */
	private int total_version=0;
	
	/** The total downloads. */
	private int total_downloads = 0;
	
	/** The total views. */
	private int total_views = 0;
	
	/** The total subscriptions. */
	private int total_subscriptions = 0;
	
	/** The total onetime. */
	private int total_onetime = 0;
	
	/** The total renews. */
	private int total_renews = 0;
	
	/** The date when report starts */
	private LocalDate foundDate = LocalDate.now();
	
	/** The total maps. */
	ArrayList<Integer> total_maps = new ArrayList<Integer>();
	
	/** The reports everything. */
	ArrayList<entities.DailyReport> REPORTS_EVERYTHING = null;
	
	/** The vbox. */
	VBox vbox = new VBox();
	
	 /** The series 1. */
 	XYChart.Series<String, Number> series1 = new XYChart.Series<>();
	
	/** The instance. */
	public static Reports_Controller instance;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		Reports_Controller.instance = this;
		datefrom.setValue(LocalDate.now());
		dateto.setValue(LocalDate.now());
		checkbox_all.setSelected(false);
		combo_city.setVisible(false);
		combo_country.setVisible(false);
		btn_create.setVisible(false);
		
		
	
	}
    
    /**
     * On change.
     *
     * @param event the event
     */
    @FXML
    void onChange(ActionEvent event) {
    	if(checkbox_all.isSelected()) {
    		combo_city.setDisable(true);
    		combo_country.setDisable(true);
    		combo_country.getSelectionModel().clearSelection();
    		combo_city.getSelectionModel().clearSelection();
    		select_city = null;
    		if(REPORTS_EVERYTHING != null)
    			get_one_report(REPORTS_EVERYTHING);
    	}else {
    		vbox.getChildren().clear();
    		report_panee.getChildren().clear();
    		combo_city.setDisable(false);
    		combo_country.setDisable(false);
    		combo_country.getSelectionModel().clearSelection();
    		combo_city.getSelectionModel().clearSelection();
    		combo_country.getItems().clear();
    		combo_city.getItems().clear();
 
    	}
    }
    
	/**
	 * Creates the map chart.
	 */
	void create_map_chart() {
		
	
		final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Total Maps per Day");
        //creating the line chart with two axis created above
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false); // disable animations

        //defining a series to display data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        lineChart.setPrefHeight(report_panee.getPrefHeight()/3);
        // add series to chart
        lineChart.getData().add(series);
        vbox.getChildren().add(lineChart);  
        
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      for(Integer in_day : total_maps) {
    	  foundDate = foundDate.plusDays(1);
    	  String formattedString = foundDate.format(formatter);
          // Update the chart
          Platform.runLater(() -> {
              series.getData().add(new XYChart.Data<>(formattedString, in_day));

          });
      }
            
       
		
	}
	    
    	/**
    	 * Creates the chart.
    	 */
    	void create_chart() {
		    CategoryAxis xAxis = new CategoryAxis();  
		      xAxis.setCategories(FXCollections.<String>
		      observableArrayList(Arrays.asList("Downloads", "Subscriptions", "Views", "One Time","Renews")));
		   
		       
		      NumberAxis yAxis = new NumberAxis();
		      yAxis.setLabel("");
		     
		      //Creating the Bar chart
		      BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); 
		     
		        
		      //Prepare XYChart.Series objects by setting data       
		      series1 = new XYChart.Series<>();
		      if(select_city != null) {
		    	  series1.setName(select_city.getName());
		      }else {
		    	  series1.setName("ALL");
		      }
		      
		      series1.getData().add(new XYChart.Data<>("Downloads", total_downloads));
		      series1.getData().add(new XYChart.Data<>("Subscriptions", total_subscriptions));
		      series1.getData().add(new XYChart.Data<>("Views", total_views));
		      series1.getData().add(new XYChart.Data<>("One Time", total_onetime));
		      series1.getData().add(new XYChart.Data<>("Renews", total_renews));
		      //Setting the data to bar chart       
		      barChart.getData().add(series1); 
		      barChart.setPrefWidth(report_panee.getPrefWidth()/2);
		      //barChart.setPrefHeight(report_panee.getPrefHeight()/2);
		      
		      CategoryAxis xAxis2 = new CategoryAxis();  
		      xAxis2.setCategories(FXCollections.<String>
		      observableArrayList(Arrays.asList("Users", "Messages", "Rate Requests", "Version Requests")));
		     
		       
		      NumberAxis yAxis2 = new NumberAxis();
		      yAxis2.setLabel("");
		     
		      //Creating the Bar chart
		      BarChart<String, Number> barChart2 = new BarChart<>(xAxis2, yAxis2); 
		     
		        
		      //Prepare XYChart.Series objects by setting data       
		      XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		      series2.setName("General");
		      series2.getData().add(new XYChart.Data<>("Users", total_users));
		      series2.getData().add(new XYChart.Data<>("Messages", total_messages));
		      series2.getData().add(new XYChart.Data<>("Rate Requests", total_rates));
		      series2.getData().add(new XYChart.Data<>("Version Requests", total_version));
		      //Setting the data to bar chart       
		      barChart2.getData().add(series2); 
		      barChart2.setPrefWidth(report_panee.getPrefWidth()/2);
		      //barChart2.setPrefHeight(report_panee.getPrefHeight()/2);
		      HBox hbox = new HBox();
		      hbox.getChildren().addAll(barChart,barChart2);
		      vbox.getChildren().add(hbox);
		      hbox.setPrefHeight(report_panee.getPrefHeight()*2/3);
		      /*hbox.setLayoutX(report_panee.getLayoutX());
		      hbox.setLayoutY(report_panee.getLayoutY() + report_panee.getHeight()/5);
		      hbox.getChildren().addAll(barChart,barChart2);*/
	        report_panee.getChildren().add(vbox);
	    }
	
	/**
	 * Gets the reports.
	 *
	 * @param REPORTS the reports
	 * @return the reports
	 */
	public void get_reports(ArrayList<entities.DailyReport> REPORTS) {
		
		if(btn_report.getText().equals("Reset")) {
	
				FadeOutUp anim1 = new FadeOutUp(HomeController.instance.PANE);
				anim1.play();
				anim1.setResetOnFinished(true);
				anim1.setOnFinished(event1 -> {
					// SceneController.push(PANE);
					Reports_Controller LookupForm = new Reports_Controller();
					try {
						LookupForm.start(HomeController.instance.PANE);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

				});
				SceneController.delete();
			return;
		}
		
		if(datefrom.getValue().isAfter(dateto.getValue())) {
			Stage s = (Stage)datefrom.getScene().getWindow();
			notification.show("Please choose proper dates!", "error", s);
			return;
		}
		REPORTS_EVERYTHING = REPORTS;
		
		datefrom.setDisable(true);
		dateto.setDisable(true);
		btn_report.setText("Reset");
		checkbox_all.setDisable(false);
		
		combo_city.setVisible(true);
		combo_country.setVisible(true);
		btn_create.setVisible(true);
		combo_city.getItems().clear();
		combo_country.getItems().clear();
	
		
		HashMap<entities.Country, ArrayList<entities.City>> country = new HashMap<entities.Country, ArrayList<entities.City>>();
		
		if(REPORTS == null)
			return;
		
		for(entities.DailyReport dr : REPORTS) {
			for(entities.City city : dr.getCity()) {
				if(!country.containsKey(city.getCountry())) {
					country.put(city.getCountry(), new ArrayList<entities.City>());		
				}
				if(!country.get(city.getCountry()).contains(city))
					country.get(city.getCountry()).add(city);
			}
		}
		
		ArrayList<entities.Country> countries = new ArrayList<entities.Country>(country.keySet());
		ArrayList<String> country_names = new ArrayList<String>();
		for(entities.Country country_inlist : countries)
			country_names.add(country_inlist.getName());
		ObservableList<String> observableList = FXCollections.observableList(country_names);
		combo_country.setItems(observableList);
		combo_country.valueProperty().addListener((obs, oldItem, newItem) -> {   //By clicking on a value within the combobox, the state will be drawn//
            if (newItem != null) {
            	//get cities of country in the other list
            	int index = combo_country.getSelectionModel().getSelectedIndex();
            	entities.Country pk_country = countries.get(index);
            	ArrayList<entities.City> cities = country.get(pk_country);
            	
            	ArrayList<String> cities_names = new ArrayList<String>();
            	for(entities.City city_inlist : cities)
            		cities_names.add(city_inlist.getName());
            	Set<String> set = new LinkedHashSet<String>(); 
            	  
                // Add the elements to set 
                set.addAll(cities_names); 
          
                // Clear the list 
                cities_names.clear(); 
                // add the elements of set 
                // with no duplicates to the list 
                cities_names.addAll(set); 
            	ObservableList<String> observableList2 = FXCollections.observableList(cities_names);
				combo_city.setItems(observableList2);
				
				combo_city.valueProperty().addListener((obs2, oldItem2, newItem2) -> {
					 if (newItem2 != null) {
						 String cityname = combo_city.getSelectionModel().getSelectedItem().toString();
						 for(entities.City c : cities) {
							 if(c.getName().equals(cityname))
							 select_city = c;
						 }

							//we have built the combobox of city and country with selecting!
							//get infos!
							get_one_report(REPORTS);
							
					 }
					
				});
            	
            }
        });		
	}
	
	/**
	 * Gets the one report.
	 *
	 * @param REPORTS the reports
	 * @return the one report
	 */
	private void get_one_report(ArrayList<entities.DailyReport> REPORTS) {
		//we need to get the report of the selected city and country
		total_users=0;
		total_messages = 0;
	    total_rates=0;
		total_version=0;
		total_downloads = 0;
		total_views = 0;
		total_subscriptions = 0;
		total_onetime = 0;
		total_renews = 0;
		total_maps.clear();
		foundDate = LocalDate.now();
		
		for(entities.DailyReport dr : REPORTS) {
			total_users+=dr.getUser().size();
			total_messages+=dr.getMessage().size();
			total_rates+=dr.getRateRequest().size();
			total_version+=dr.getVersionRequest().size();	
		}
		
		
		entities.City c = select_city;
		String found1 = REPORTS.get(0).getDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		foundDate = LocalDate.parse(found1, formatter);
	
		for (entities.DailyReport dr : REPORTS) {
			//number of maps
			if (checkbox_all.isSelected()) {
				
				int total = 0;
				for (entities.City city : dr.getCity()) {
					total += city.getNumberOfMaps();
					
				}
				total_maps.add(total);
			} else {
				boolean foundcity = false;
				for (entities.City city : dr.getCity()) {
					if (city.getName().equals(c.getName()) && city.getCountry().getShortName().equals(c.getCountry().getShortName())) {
						total_maps.add(city.getNumberOfMaps());
						foundcity = true;
					}
				}
				if(!foundcity)
					total_maps.add(0);

			}
			
			// downloads, views
			for (entities.Activity adr : dr.getActivity()) {
				if (checkbox_all.isSelected()) {
					if (adr.getAtype() == type.DOWNLOAD) {
						total_downloads++;
					} else if (adr.getAtype() == type.VIEW) {
						total_views++;
					}

				} else {
					if (c.equals(adr.getMap().getCity())) {
						if (adr.getAtype() == type.DOWNLOAD) {
							total_downloads++;
						} else if (adr.getAtype() == type.VIEW) {
							total_views++;
						}
					}
				}

			}
			// subscriptions
			for (entities.Purchase adr : dr.getPurchase()) {
				if (checkbox_all.isSelected()) {
					if (adr.getPurchasePrice().getRtype() == Rate.type.SUBSCRIPTION) {
						total_subscriptions++;
					} else if (adr.getPurchasePrice().getRtype() == Rate.type.ONETIME) {
						total_onetime++;
					}else if(adr.getRenewal() != 0) {
						total_renews++;
					}
				} else {
					if (c.equals(adr.getCity())) {
						if (adr.getPurchasePrice().getRtype() == Rate.type.SUBSCRIPTION) {
							total_subscriptions++;
						} else if (adr.getPurchasePrice().getRtype() == Rate.type.ONETIME) {
							total_onetime++;
						}else if(adr.getRenewal() != 0) {
							total_renews++;
						}
					}
				}
			}
		}
		vbox.getChildren().clear();
		report_panee.getChildren().clear();
		
		create_chart();
		create_map_chart();
	}
	
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.REPORTS.getFXML()));
		HomeController.instance.Set_Title(FxmlView.REPORTS.getTitle());
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
     * Btn search clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_search_clicked(MouseEvent event) {
    	LocalDate localdate1 = datefrom.getValue();
    	LocalDate localdate2 = dateto.getValue();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String date1_string = localdate1.format(formatter);
    	String date2_string = localdate2.format(formatter);
    	//btn_report.setDisable(true);
    	client.requestHandler.Report.get_report(date1_string, date2_string);
    }
	
	/**
	 * Btn create click.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_create_click(MouseEvent event) {

		WritableImage image = HomeController.instance.PANE.snapshot(new SnapshotParameters(), null);

		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

		// Prompt user to select a file
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			ZoomOut anim = new ZoomOut(HomeController.instance.PANE);
			anim.setResetOnFinished(true);
			anim.setSpeed(4);
			anim.setOnFinished(event2 -> {

				try {

					ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

				} catch (IOException ex) {
					ex.printStackTrace();
				}

			});
			anim.play();
		}
	}

    /**
     * Combofrom change.
     *
     * @param event the event
     */
    @FXML
    void combofrom_change(ActionEvent event) {
    	btn_create.setVisible(false);
    }

    /**
     * Comboto change.
     *
     * @param event the event
     */
    @FXML
    void comboto_change(ActionEvent event) {
    	btn_create.setVisible(false);
    }

}
