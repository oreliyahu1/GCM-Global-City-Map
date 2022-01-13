package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/** 
 * The Class AbstractJsonToDataFile.
 * Abstract class of data file
 */
public abstract class AbstractJsonToDataFile extends AbstractJsonToString {

	
	/**
	 * Save.
	 * Save the class in file, The content will be the entitle
	 *
	 * @param beforeData the before data
	 * @param filePath the file path
	 * @return true, If saved successfully
	 */
	protected boolean save(String beforeData, String filePath) {
		try {
			System.out.println("AbstractJsonToDataFile: Trying to save settings");
			File file = new File(filePath);
			if(!file.exists()) file.createNewFile();
			FileWriter fileW = new FileWriter(file);
			fileW.write(beforeData + this.toString()); //write setting(json format)
			fileW.close();
			System.out.println("AbstractJsonToDataFile: Settings saved successfully");
		} catch (IOException e) {
			System.out.println("AbstractJsonToDataFile error:" + e.toString());
			return false;
		}
		return true;
	}
}
