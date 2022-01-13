package guiControllers;

/**
 * all the FXML controllers including the DatabaseController witch manage
 * all the controller in the package and connecting theme to the server
 */

import java.util.ArrayList;

import entities.Permission;
import guiControllers.Catalog.CatalogController;
import guiControllers.Catalog.CitiesController;
import guiControllers.Catalog.Selected_CityController;
import javafx.scene.layout.Pane;

/**
 * The Class SceneController.
 *
 * @author Sami Odeh
 * @version 1.4 [16.6.2019]
 */
public class SceneController {
	
	/** The stage list. for all scenes*/
	public static ArrayList<Pane> stageList = new ArrayList<Pane>();
	
	/** The stage titles. */
	public static ArrayList<String> stageTitles = new ArrayList<String>();
	
	/** The refresh (when back button click fom home controller ) commands. */
	public static ArrayList<String> refresh_commands = new ArrayList<String>();

	/** The Catalog scene. */
	public static Pane Catalog_scene = new Pane();

	/**
	 * Pop next scene in stack.
	 *
	 * @return stack top
	 */
	public static Pane pop() {
		if (SceneController.stageList.size() == 0)
			return new Pane();

		Pane scene = stageList.get(0);
		stageList.remove(0);
		checkSize();
		return scene;
	}

	/**
	 * Pop command.
	 *
	 * @return the string
	 */
	public static String pop_command() {

		if(refresh_commands.size() == 0)
			return null;
		String command = refresh_commands.get(0);
		run_commands(command);
		refresh_commands.remove(0);
		return command;
	}

	/**
	 * Pop title.
	 *
	 * @return the string
	 */
	public static String pop_title() {

		String title = stageTitles.get(0);
		stageTitles.remove(0);
		return title;
	}

	/**
	 * Push scene to the top of stack.
	 *
	 * @param scene added to the top of stack
	 */
	public static void push(Pane scene) {

		Pane p = new Pane();
		p.getChildren().addAll(scene.getChildren());
		stageList.add(0, p);
		checkSize();

	}

	/**
	 * Push title.
	 *
	 * @param title the title
	 */
	public static void push_title(String title) {
		stageTitles.add(0, title);
	}

	/**
	 * Push command.
	 *
	 * @param command the command
	 */
	public static void push_command(String command) {
		refresh_commands.add(0, command);
	}

	/**
	 * Delete.
	 */
	public static void delete() {
		stageList.clear();
		checkSize();
		stageTitles.clear();
		refresh_commands.clear();
	}

	/**
	 * check Size if 0 or not.
	 */
	public static void checkSize() {
		if (stageList.size() == 0)
			HomeController.instance.btn_back.setVisible(false);
		else
			HomeController.instance.btn_back.setVisible(true);
	}
	
	/**
	 * Run commands.
	 *
	 * @param command the command
	 */
	public static void run_commands(String command) {
		switch (command) {
		case "Catalog":
			CatalogController.instance.refresh_countries();
			break;
		case "Cities":
			if(HomeController.instance.user == null) {
				CitiesController.instance.refreshCities();
			}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT)) {
				client.requestHandler.Catalog.get_all_purchases_in_country_for_user(GeneralValues.COUNTRY.getShortName(), HomeController.instance.user.getUsername());

			}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.WORKER)) {
				CitiesController.instance.refreshCities();
			}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTWORKER)) {
				CitiesController.instance.refreshCities();
			}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER)) {
				CitiesController.instance.refreshCities();
			}else if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER)) {
				CitiesController.instance.refreshCities();
			}
			break;
		case "Selected_City":
			Selected_CityController.instance.refresh_recommended_tours();
			Selected_CityController.instance.refresh_maps();
			break;
		default:
			break;

		}
	}
}
