/*
 * this class is used to download Map collections
 * Map collection consist of : Maps, Places, Images
 * saved in file Downlaods in the localDisk and can be opened with any browser
 * downloaded files uses javascrip and html to view the required maps 
 * 
 * Credit to Group 7
 */
package guiControllers.subGui;

import java.util.ArrayList;

import entities.Activity;
import entities.Download;
import entities.DownloadMap;
import guiControllers.HomeController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;


/**
 * The Class DownloadLoadingController. to download javascript maps and saves it on local disk
 */
public class DownloadLoadingController {

	
   /** The instance. */
   public static DownloadLoadingController instance;

   	/** The download. */
	   public static entities.Download download;
   	
   	/** The purchase selected. */
	   public static entities.Purchase purchase_selected;
   	
	/** The downloadsize. */
	private int downloadsize = 0;
    
    /** The txt download. */
    @FXML
    private Label txt_download;


    /** The Loading bar. */
    @FXML
    private ProgressIndicator LoadingBar;
    
    /** The progrssbar. */
    @FXML
    private ProgressBar progrssbar;

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	DownloadLoadingController.instance = this;
    	progrssbar.setProgress(0);
        progrssbar.progressProperty().unbind();
  
      
       
        client.requestHandler.Download.getAllMapsOfCityforDownload(purchase_selected);

    }
    
    /**
     * Close window.
     */
    public void close_window() {
		Stage sb = (Stage) txt_download.getScene().getWindow();
		sb.close();
	}

	/**
	 * Gets the all maps of city.
	 *
	 * @param maps the maps
	 * @return the all maps of city
	 */
	public void get_all_maps_of_city(ArrayList<entities.Map> maps) {
		if(maps.size() == 0)
			close_window();
		download = new Download(HomeController.instance.user,purchase_selected.getCity());
		download.getDownCity().getMaps().addAll(maps);
		downloadsize = maps.size();
		
		//progrss bar
		txt_download.setText("Downloading Maps...");
		 new Thread(() -> {
			 
	            for(int i = 0; i <=100; i++){
	               final int position = i;
	               Platform.runLater(() -> {
	            	   progrssbar.setProgress(position/300.0);
	               });
	               try{
	                   Thread.sleep(5);
	               }catch(Exception e){ System.err.println(e); }
	            }
	            for(entities.Map map : download.getDownCity().getMaps()) {
	    			entities.Activity downloadRequest = new Activity(HomeController.instance.user, map, purchase_selected, entities.Activity.type.DOWNLOAD);
	    			client.requestHandler.Download.getFullMapOfCity(downloadRequest);
	    		}
	       }).start(); //progrss bar
		
	
	
	}

	/**
	 * Adds the fullmap to download.
	 *
	 * @param fullMap the full map
	 */
	public void add_fullmap_to_download(entities.Map fullMap) {
		txt_download.setText("Downloading Maps Images...");
		 new Thread(() -> {
			 
	            for(int i = 100; i <=200; i++){
	               final int position = i;
	               Platform.runLater(() -> {
	            	   progrssbar.setProgress(position/300.0);
	               });
	               try{
	                   Thread.sleep(5);
	               }catch(Exception e){ System.err.println(e); }
	            }
	        	
	       }).start();
		 download.getDownMaps().add(new DownloadMap(fullMap));
 		client.requestHandler.Download.getPOISMAP_BY_MAP(fullMap);
		 txt_download.setText("Downloading Places of Maps...");
	}
	
	/**
	 * Gets the all pois maps.
	 *
	 * @param places the places
	 * @return the all pois maps
	 */
	public void get_all_pois_maps(ArrayList<entities.PlaceOfInterestMap> places) {
		downloadsize--;
		 new Thread(() -> {
			 
	            for(int i = 200; i <=300; i++){
	               final int position = i;
	               Platform.runLater(() -> {
	            	   progrssbar.setProgress(position/300.0);
	               });
	               try{
	                   Thread.sleep(5);
	               }catch(Exception e){ System.err.println(e); }
	            }
	            txt_download.setText("Downloaded!...");
	    	
	       }).start();
			if(!places.isEmpty()) {
    			entities.Map map = places.get(0).getMap();
    			for(DownloadMap dm : download.getDownMaps()) {
    				if (dm.getMap().getName().equals(map.getName())) {
    					dm.getPoimaps().addAll(places);
    					break;
    				}
    			}
    		}
		if(downloadsize ==0)
			download.save();
		close_window();
		
		
	}
	
   
    

}
