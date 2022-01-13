package guiControllers.item;

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;

/**
 * The Class countryItem extends the anchorpane container from the scene builder
 */
public class countryItem extends AnchorPane {

    /** The pane. */
    private final Pane pane;
    
    /** The country name. */
    public final Label country_name;
    
    /** The label. */
    private final Label label;
    
    /** The flag. */
    private final ImageView flag;
    
    /** The line. */
    private final Line line;
    
    /** The img. */
    private final ImageView img;
    
    /** The label 0. */
    private final Label label0;
    
    /** The label 1. */
    private final Label label1;
    
    /** The label 2. */
    private final Label label2;
    
    /** The label 3. */
    private final Label label3;
    
    /** The primary key. */
    private String PRIMARY_KEY;
    
    /**
     * Instantiates a new country item.
     *
     * @param c the c
     */
    public countryItem(entities.Country c) {

    	setShortName(c.getShortName());
    	
        pane = new Pane();
        country_name = new Label();
        label = new Label();
        flag = new ImageView();
        line = new Line();
        img = new ImageView();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();

        setPrefHeight(92.0);
        setPrefWidth(710.0);
        setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

        pane.setLayoutX(0.0);
        pane.setLayoutY(0.0);
        pane.setPrefHeight(92.0);
        pane.setPrefWidth(710.0);
        pane.setStyle("-fx-border-color: #BDBBC3");

        country_name.setLayoutX(68.0);
        country_name.setLayoutY(25.0);
        country_name.setText(c.getName());
        //country_name.setText("bshara");
        label.setLayoutX(185.0);
        label.setLayoutY(25.0);
        label.setText(String.valueOf(c.getNumberOfCities()));

        flag.setFitHeight(31.0);
        flag.setFitWidth(25.0);
        flag.setLayoutX(14.0);
        flag.setLayoutY(22.0);
        flag.setPickOnBounds(true);
        flag.setPreserveRatio(true);
        flag.setSmooth(true);
        
        Image image = new Image(getClass().getResourceAsStream("/image/icons8-europe-48.png"));
        flag.setImage(image);
        
        line.setEndX(380.0);
        line.setLayoutX(188.0);
        line.setLayoutY(51.0);
        line.setStartX(-120.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));
        line.setTranslateY(3.0);

        img.setFitHeight(74.86678314208984);
        img.setFitWidth(112.34029234142193);
        img.setLayoutX(591.0);
        img.setLayoutY(9.0);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        img.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); -fx-stroke-width: 2.0;-fx-border-radius: 20 20 20 20;-fx-background-radius: 10 10 10 10;-fx-padding: 10;-fx-background-color: firebrick;");
     //  
        try {
        image = new Image(getClass().getResourceAsStream(c.getImagePath()));
        }catch(Exception ex) {
        	image = new Image(getClass().getResourceAsStream("/image/flags/unknown_flag.gif"));
        	System.out.println("error load image of " + c.getName() + " / " + c.getImagePath());
        }
        img.setImage(image);
        
        label0.setLayoutX(280.0);
        label0.setLayoutY(25.0);
        label0.setText(String.valueOf(c.getNumberOfMaps()));

        label1.setLayoutX(68.0);
        label1.setLayoutY(60.0);
        label1.setText(c.getDescription());
        label1.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        label1.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        label1.setWrapText(false);
        
        ///new
        label2.setLayoutX(380.0);
        label2.setLayoutY(25.0);
        label2.setText(String.valueOf(c.getNumberOfPois()));
        
        label3.setLayoutX(510.0);
        label3.setLayoutY(25.0);
        label3.setText(String.valueOf(c.getNumberOfTours()));
      ///end new
        
        pane.getStylesheets().add("/style.css");
        getStylesheets().add("/style.css");

        pane.getChildren().add(country_name);
        pane.getChildren().add(label);
        pane.getChildren().add(flag);
        pane.getChildren().add(line);
        pane.getChildren().add(img);
        pane.getChildren().add(label0);
        pane.getChildren().add(label1);
        
      ///new
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
      ///end new
        
        getChildren().add(pane);

    }
    
    /**
     * Gets the short name.
     *
     * @return the short name
     */
    public String getShortName() {
		return PRIMARY_KEY;
	}
	
	/**
	 * Sets the short name.
	 *
	 * @param shortName the new short name
	 */
	public void setShortName(String shortName) {
		this.PRIMARY_KEY= shortName;
	}
}
