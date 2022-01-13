package entities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


// TODO: Auto-generated Javadoc
/**
 * The Class Download.
 */
public class Download {

	/** The down city. */
	private DownloadCity downCity;
	
	/** The maps. */
	private ArrayList<DownloadMap> downMaps;
	
	/**
	 * Instantiates a new Download
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Download() {}

	/**
	 * Instantiates a new download.
	 *
	 * @param user the user
	 * @param city the city
	 */
	public Download(User user, City city) {
		this.downCity = new DownloadCity(user, city);
		downMaps = new ArrayList<DownloadMap>();
	}

	/**
	 * Gets the down city.
	 *
	 * @return the down city
	 */
	public DownloadCity getDownCity() {
		return downCity;
	}

	/**
	 * Sets the down city.
	 *
	 * @param downCity the new down city
	 */
	public void setDownCity(DownloadCity downCity) {
		this.downCity = downCity;
	}

	/**
	 * Gets the down maps.
	 *
	 * @return the down maps
	 */
	public ArrayList<DownloadMap> getDownMaps() {
		return downMaps;
	}

	/**
	 * Sets the down maps.
	 *
	 * @param downMaps the new down maps
	 */
	public void setDownMaps(ArrayList<DownloadMap> downMaps) {
		this.downMaps = downMaps;
	}
	
	/**
	 * Save.
	 *
	 * @return true, if successful
	 */
	public boolean save() {
		try {
			String dirName = String.format("%s_%s_v%s_%s/",
					downCity.getCity().getCountry().getShortName(),
					downCity.getCity().getName().replaceAll(" ", ""),
					downCity.getCity().getVersion().toString().replace('.', '_'),
					new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()));
			
			File main = new File("download/" + dirName);
			main.mkdirs();		
			Files.copy(getClass().getResource("/index.html").openStream(), Paths.get(main.getPath() + "/index.html"));
			File js = new File(main + "/js");
			js.mkdirs();		
			Files.copy(getClass().getResource("/js/craftmap.js").openStream(), Paths.get(js.getPath() + "/craftmap.js"));
			Files.copy(getClass().getResource("/js/init.js").openStream(), Paths.get(js.getPath() + "/init.js"));
			Files.copy(getClass().getResource("/js/initindex.js").openStream(), Paths.get(js.getPath() + "/initindex.js"));
			Files.copy(getClass().getResource("/js/jquery.latest.min.js").openStream(), Paths.get(js.getPath() + "/jquery.latest.min.js"));
			downCity.save(js.getPath());
			File style = new File(main + "/style");
			style.mkdirs();
			Files.copy(getClass().getResource("/style/bootstrap.min.css").openStream(), Paths.get(style.getPath() + "/bootstrap.min.css"));
			Files.copy(getClass().getResource("/style/theme.css").openStream(), Paths.get(style.getPath() + "/theme.css"));
			File images = new File(main + "/style/images");
			images.mkdirs();
			Files.copy(getClass().getResource("/style/images/background.jpg").openStream(), Paths.get(images.getPath() + "/background.jpg"));
			Files.copy(getClass().getResource("/style/images/close.gif").openStream(), Paths.get(images.getPath() + "/close.gif"));
			Files.copy(getClass().getResource("/style/images/loader.gif").openStream(), Paths.get(images.getPath() + "/loader.gif"));
			Files.copy(getClass().getResource("/style/images/mapMarker.png").openStream(), Paths.get(images.getPath() + "/mapMarker.png"));

			for(DownloadMap dm : downMaps) {
				File filedm = new File(main.getPath() + "/" + dm.getMap().getName());
				filedm.mkdir();
				dm.save(filedm.getPath());
				Files.copy(getClass().getResource("/map_view.html").openStream(), Paths.get(filedm.getPath() + "/index.html"));
			}
			Desktop.getDesktop().open(main);
		} catch (IOException e) {
			System.out.println("AbstractJsonToFile error:" + e.toString());
			return false;
		}
		return true;
	}
}
