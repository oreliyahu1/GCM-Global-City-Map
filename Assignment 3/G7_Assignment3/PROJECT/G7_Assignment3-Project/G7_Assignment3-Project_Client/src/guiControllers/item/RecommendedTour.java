package guiControllers.item;


import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * The Class RecommendedTour.
 */
public class RecommendedTour extends AnchorPane {

    /** The pane. */
    private final Pane pane;
    
    /** The Map name. */
    private final Label Map_name;
    
    /** The flag. */
    private final ImageView flag;
    
    /** The label 0. */
    private final Label label0;
    
    /** The line. */
    private final Line line;
    
    /** The label 1. */
    private final Label label1;
    
    /** The label 2. */
    private final Label label2;
    
    /**
     * Instantiates a new recommended tour.
     *
     * @param tour the tour
     */
    public RecommendedTour(entities.Tour tour) {

        pane = new Pane();
        Map_name = new Label();
        flag = new ImageView();
        label0 = new Label();
        line = new Line();
        label1 = new Label();
        label2 = new Label();
    
        setPrefHeight(81.0);
        setPrefWidth(710.0);
        setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

        pane.setLayoutX(0.0);
        pane.setLayoutY(0.0);
        pane.setPrefHeight(101.0);
        pane.setPrefWidth(710.0);
        pane.setStyle("-fx-border-color: #BDBBC3");

        Map_name.setLayoutX(68.0);
        Map_name.setLayoutY(25.0);
        Map_name.setText(tour.getName());

        
        flag.setFitHeight(31.0);
        flag.setFitWidth(25.0);
        flag.setLayoutX(14.0);
        flag.setLayoutY(22.0);
        flag.setPickOnBounds(true);
        flag.setPreserveRatio(true);
        flag.setSmooth(true);
        Image image = new Image(getClass().getResourceAsStream("/image/icons8-map-pinpoint-48.png"));
        flag.setImage(image);
        
        label0.setLayoutX(240.0);
        label0.setLayoutY(25.0);
        label0.setText(String.valueOf(tour.getNumberOfPois()));

        line.setEndX(490.0);
        line.setLayoutX(182.0);
        line.setLayoutY(55.0);
        line.setStartX(-120.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));

        label1.setLayoutX(62.0);
        label1.setLayoutY(66.0);
		Tooltip tooltip = new Tooltip(tour.getDescription());
		Tooltip.install(label1, tooltip);
		String desc = tour.getDescription();
		if (tour.getDescription() != null) {
			if (tour.getDescription().length() > 117) {
				desc = desc.substring(0, 117);
				desc += "...";
			}
		}
		label1.setText(desc);
        label1.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        label1.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        label1.setWrapText(false);
        
        int  num_seconds = (int)tour.getNumberOfTourDuration();
        int dayss = num_seconds / (3600* 24);
		num_seconds = num_seconds % (24*3600);
		int hourss = num_seconds / 3600;
		num_seconds = num_seconds % (24*3600);
		num_seconds %=3600;
		int minss = num_seconds /60;
    
        //new
        //need to cov from sec to time
        label2.setLayoutX(345.0);
        label2.setLayoutY(25.0);
        String time = String.format("%02dD :%02dH :%02dM",dayss, hourss, minss);
        label2.setText(time);
        
     
        pane.getStylesheets().add("/style.css");
        getStylesheets().add("/style.css");

        pane.getChildren().add(Map_name);
        pane.getChildren().add(flag);
        pane.getChildren().add(label0);
        pane.getChildren().add(line);
        pane.getChildren().add(label1);
     
        //new
        pane.getChildren().add(label2);
        //
        getChildren().add(pane);

    }

	/**
	 * Gets the map name.
	 *
	 * @return the map name
	 */
	public Labeled getMap_name() {
		// TODO Auto-generated method stub
		return Map_name;
	}
}
