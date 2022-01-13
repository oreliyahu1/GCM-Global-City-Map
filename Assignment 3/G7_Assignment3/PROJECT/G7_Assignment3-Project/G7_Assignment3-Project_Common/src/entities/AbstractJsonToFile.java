package entities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/** 
 * The Class AbstractSettings.
 * Abstract class of client and server
 */
public abstract class AbstractJsonToFile extends AbstractJsonToDataFile {

	/**
	 * Update.
	 * Abstract function, need to create a entitle of extend setting and load file parameters
	 * @param json the json of entities
	 */
	protected abstract void update(String json);
	
	/**
	 * Save.
	 * Save the class in file, The content will be the entitle
	 * @param file the file name
	 * @return true, If saved successfully
	 */
	protected boolean save(String file) {
		return super.save("", file);
	}
	
	/**
	 * Open.
	 * Load the class in file, The content of file will be update the settings
	 *
	 * @param filePath the file path
	 * @return true, if successfully opened
	 */
	protected boolean open(String filePath) {
		try {
			System.out.println("AbstractJsonToFile: Trying to load settings");
			File file = new File(filePath);
			if(file.exists()) {
				FileReader fileR = new FileReader(file);
				BufferedReader br = new BufferedReader(fileR);
				String temp;
				String jsonSettings = "";
				while((temp=br.readLine()) != null)
					jsonSettings += temp;
				br.close();
				fileR.close();
				update(jsonSettings); //use abstract function - needs to override
				System.out.println("AbstractJsonToFile: Settings successfully loaded");
				return true;
			}
			System.out.println("AbstractJsonToFile: File not found");
		} catch (IOException e) {
			System.out.println("AbstractJsonToFile error:" + e.toString());
			return false;
		}
		return false;
	}
}
