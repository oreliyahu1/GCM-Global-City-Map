package guiControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import animatefx.FadeIn;
import animatefx.SlideInUp;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.item.UserBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The Class Manage_ClientsController.
 */
public class Manage_ClientsController {

	/** The pn items. */
	@FXML
	private VBox pnItems;
	
	/** The search text. */
	@FXML
	private TextField search_text;
	
	/** The working collection. */
	ObservableList<Node> workingCollection;
	
	/** The instance. */
	public static Manage_ClientsController instance;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		Manage_ClientsController.instance = this;
		GeneralValues.Found_Department_manager = false;
		client.requestHandler.ManageClients.getAllusers();
	}

	/**
	 * Refresh users. call the request handler
	 */
	public void refresh_users() {
		client.requestHandler.ManageClients.getAllusers();
	}
	
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.MANAGE_CLIENTS.getFXML()));
		HomeController.instance.Set_Title(FxmlView.MANAGE_CLIENTS.getTitle());
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
	 * The Class NodeComparator. compare by user username
	 */
	private class NodeComparator implements Comparator<Node> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Node o1, Node o2) {
			UserBase sp1 = (UserBase) o1;
			UserBase sp2 = (UserBase) o2;
			String s1 = (String) sp1.get_user().getUsername();
			String s2 = (String) sp2.get_user().getUsername();

			return s1.compareTo(s2);
		}
	}
	
	/**
	 * Search. search on vbox list and sort it so searched item will be in the top of the list
	 *
	 * @param event the event
	 */
	@FXML
	void search(KeyEvent event) {
		// if text is empty then show in sorted
		ArrayList<UserBase> found = new ArrayList<UserBase>();
		if (search_text.getText().equals("")) {
			try {

				Collections.sort(workingCollection, new NodeComparator());

				pnItems.getChildren().setAll(workingCollection);

			} catch (Exception c) {
				System.out.println("Cannot sort");
			}

			return;
		}

		if (event.getCode().equals(KeyCode.ENTER)) {
			Platform.runLater(() -> {
				if (workingCollection == null)
					return;
				for (Node child : workingCollection) {
					if (child instanceof UserBase) {
						UserBase sp = (UserBase) child;
						if (sp.get_user().getUsername().toLowerCase().contains(search_text.getText().toLowerCase())) {
							found.add(sp);
						}
					}
				}
				Collections.sort(workingCollection, new NodeComparator());
				pnItems.getChildren().setAll(workingCollection);
				Collections.sort(found, Collections.reverseOrder(new NodeComparator()));
			});

			Platform.runLater(() -> {
				for (UserBase sp : found) {

					sp.toBack();
					sp.setStyle("-fx-background-color : #BDBBC3");
					FadeIn anim1 = new FadeIn(sp);
					anim1.play();
					anim1.setResetOnFinished(true);
					anim1.setOnFinished(event2 -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
				}
			});
		}

	}

	/**
	 * Update users.
	 *
	 * @param users the users
	 */
	public void update_users(ArrayList<entities.User> users) {
		pnItems.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.User user : users) {

					UserBase sp = new UserBase(user);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {
						sp.show_profile();
					});
					Platform.runLater(() -> {
						pnItems.getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}
			
			
			}
		};
		Thread thread = new Thread(runn);
		thread.start();
		
		Platform.runLater(() -> {
			workingCollection = FXCollections.observableArrayList(pnItems.getChildren());
			Collections.sort(workingCollection, new NodeComparator());
			pnItems.getChildren().setAll(workingCollection);

		});

	}

}
