package guiControllers.item;


import java.util.Optional;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * The Class MessageBase.
 */
public class MessageBase extends AnchorPane {

	/** The pane. */
	private Pane pane;
	
	/** The date. */
	private final Label date;
	
	/** The flag. */
	private final ImageView flag;
	
	/** The from. */
	private final Label from;
	
	/** The subject. */
	private final Label subject;
	
	/** The btn delete. */
	private final Button btn_delete;
	
	/** The btn delete 0. */
	private final ImageView btn_delete0;
	
	/** The content. */
	private Label content;

	/** The msg. */
	private entities.Message msg;

	/** The isshowed. */
	private boolean isshowed = false;
	
	/**
	 * Instantiates a new message base.
	 *
	 * @param msg the msg
	 */
	public MessageBase(entities.Message msg) {
		this.msg = msg;

		pane = new Pane();
		date = new Label();
		flag = new ImageView();
		from = new Label();
		subject = new Label();
		btn_delete = new Button();
		btn_delete0 = new ImageView();
		content = new Label();

		setPrefHeight(54.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(57.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");

		date.setLayoutX(71.0);
		date.setLayoutY(18.0);
		date.setText(msg.getDate());
		
		content.setLayoutX(71);
		content.setLayoutY(70);
		content.setText(getcontent());
		
		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(22.0);
		flag.setLayoutY(16.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);

		Image imagee;
		if (msg.isIs_read()) {
			imagee = new Image(getClass().getResourceAsStream("/image/icons8-read-message-32.png"));
		} else {
			imagee = new Image(getClass().getResourceAsStream("/image/icons8-group-message-filled-32.png"));
		}

		flag.setImage(imagee);

		from.setLayoutX(215.0);
		from.setLayoutY(18.0);
		from.setText(msg.getFrom().getUsername());

		subject.setLayoutX(331.0);
		subject.setLayoutY(18.0);
		subject.setText(msg.getSubject());

		btn_delete.setDisable(false);
		btn_delete.setId("login_btn");
		btn_delete.setLayoutX(653.0);
		btn_delete.setLayoutY(13.0);
		btn_delete.setMinHeight(25.951171875);
		btn_delete.setMnemonicParsing(false);
		btn_delete.setOnMouseClicked(this::btn_delete_clicked);
		btn_delete.setPrefHeight(31.0);
		btn_delete.setPrefWidth(42.0);
		btn_delete.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		btn_delete.getStylesheets().add("//../style.css");

		btn_delete0.setFitHeight(28.0);
		btn_delete0.setFitWidth(21.0);
		btn_delete0.setPickOnBounds(true);
		btn_delete0.setPreserveRatio(true);
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-trash-48.png"));
		btn_delete0.setImage(imagee);
		btn_delete.setGraphic(btn_delete0);
		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		pane.getChildren().add(date);
		pane.getChildren().add(flag);
		pane.getChildren().add(from);
		pane.getChildren().add(subject);
		pane.getChildren().add(btn_delete);
		getChildren().add(pane);

	}

	/**
	 * Btn delete clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_delete_clicked(MouseEvent mouseEvent) {
		Alert alert = new Alert(AlertType.WARNING, "Are you sure to Delete that Message?",
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			client.requestHandler.Notifications.Delete_Notification(msg);
		}	
		
	}

	/**
	 * Updateimage.
	 */
	public void updateimage() {
		Image imagee;
		if (msg.isIs_read()) {
			imagee = new Image(getClass().getResourceAsStream("/image/icons8-read-message-32.png"));
		} else {
			imagee = new Image(getClass().getResourceAsStream("/image/icons8-group-message-filled-32.png"));
		}
		flag.setImage(imagee);
	}

	/**
	 * Show msg.
	 */
	public void show_msg() {
		if(!msg.isIs_read())
			client.requestHandler.Notifications.Update_Notifications(String.valueOf(msg.getId()));
		
		if(isshowed == false) {
			msg.setIs_read(true);
			isshowed = true;
			int lines = msg.getMessage().replace("\n", "").length();
			double size = lines*1.4;
			if(size < 150)
				size = 150;
			changeSize1(pane,size);
		}else {
			isshowed = false;
			changeSize2(pane,57);
			
			}
		
	}

	/**
	 * Change size 1.
	 *
	 * @param pane the pane
	 * @param height the height
	 */
	public void changeSize1(Pane pane, double height) {
		this.setDisable(true);
	    Duration cycleDuration = Duration.millis(500);
	    Timeline timeline = new Timeline(
	    	    new KeyFrame(cycleDuration,
	                    new KeyValue(pane.prefHeightProperty(),height,Interpolator.EASE_BOTH)),
	            new KeyFrame(cycleDuration,
	                    new KeyValue(this.prefHeightProperty(),height,Interpolator.EASE_BOTH))
	            );

	    timeline.play();
	    timeline.setOnFinished(event -> {
	    	pane.getChildren().add(content);
	    	this.setDisable(false);
	    });
	
	}
	
	/**
	 * Change size 2.
	 *
	 * @param pane the pane
	 * @param height the height
	 */
	public void changeSize2(Pane pane, double height) {
		this.setDisable(true);
		pane.getChildren().remove(content);
	    Duration cycleDuration = Duration.millis(500);
	    Timeline timeline = new Timeline(
	    	    new KeyFrame(cycleDuration,
	                    new KeyValue(pane.prefHeightProperty(),height,Interpolator.EASE_BOTH)),
	            new KeyFrame(cycleDuration,
	                    new KeyValue(this.prefHeightProperty(),height,Interpolator.EASE_BOTH))
	            );
	    timeline.setOnFinished(event-> {
	    	this.setDisable(false);
	    });
	    timeline.play();
	
	}
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getcontent() {
		return msg.getMessage();
	}

}
