package entities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class AbstractSettings.
 * Abstract class of client and server
 */
public abstract class AbstractSettings extends AbstractJsonToString {

	/**
	 * Update.
	 * Abstract function, need to create a entitle of extend setting and load settings parameters
	 * @param json the json of settings
	 */
	protected abstract void update(String json);
	
	/**
	 * Save.
	 * Save the class in file, The content will be the entitle
	 * @param file the file name
	 * @return true, If saved successfully
	 */
	protected boolean save(String file) {
		try {
			System.out.println("AbstractSettings: Trying to save settings");
			File settings = new File(file + ".config");
			if(!settings.exists()) settings.createNewFile();
			FileWriter settingsW = new FileWriter(settings);
			settingsW.write(this.toString()); //write setting(json format)
			settingsW.close();
			System.out.println("AbstractSettings: Settings saved successfully");
		} catch (IOException e) {
			System.out.println("AbstractSettings error:" + e.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * Open.
	 * Load the class in file, The content of file will be update the settings
	 * @param file the file name
	 * @return true, if successfully opened
	 */
	protected boolean open(String file) {
		try {
			System.out.println("AbstractSettings: Trying to load settings");
			File settings = new File(file + ".config");
			if(settings.exists()) {
				FileReader settingsR = new FileReader(settings);
				BufferedReader br = new BufferedReader(settingsR);
				String temp;
				String jsonSettings = "";
				while((temp=br.readLine()) != null)
					jsonSettings += temp;
				br.close();
				settingsR.close();
				update(jsonSettings); //use abstract function - needs to override
				System.out.println("AbstractSettings: Settings successfully loaded");
				return true;
			}
			System.out.println("AbstractSettings: File not found");
		} catch (IOException e) {
			System.out.println("AbstractSettings error:" + e.toString());
			return false;
		}
		return false;
	}
}
