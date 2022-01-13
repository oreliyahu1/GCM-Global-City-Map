package application;

import javafx.scene.control.TextFormatter;

/**
 * The Class GuiFormatCreator.
 * 
 */
public class GuiFormatCreator {
	
	/**
	 * Override public contractor to make it static.
	 */
	private GuiFormatCreator() {}
	
	/**
	 * Create's TextFormatter of only numbers.
	 *
	 * @return the text formatter
	 */
	public static TextFormatter<String> onlyNumber()
	{
    	return new TextFormatter<>(change -> {
    	    if (!change.isContentChange()) return change;
    	    String text = change.getControlNewText();
    	    if(text.isEmpty()) return change;
    	    if(text.equals("0")) return change;
    	    if (!text.matches("^[1-9](?:\\d+)?$")) return null;
    	    try { //integer-limit
    	    	Integer.parseInt(text);
    	    }catch(Exception ex) {return null;}
    	    return change;
    	});
	}
	
	/**
	 * Create's TextFormatter of only numbers and one dot.(for version field).
	 *
	 * @return the text formatter
	 */
	public static TextFormatter<String> onlyNumbersAndOneDot()
	{
    	return new TextFormatter<>(change -> {
    	    if (!change.isContentChange()) return change;
    	    String text = change.getControlNewText();
    	    if(text.isEmpty()) return change;
    	    if (!text.matches("^([1-9](?:\\\\d+)?)+(?:\\.)?(?:\\d+)?$")) return null;
    	    return change;
    	});
	}
	
	/**
	 * Check IP address.
	 *
	 * @param string ip
	 * @return true, if valid ip.
	 */
	public static boolean checkIPAddress(String ip)
	{
		return ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
	}
	
	/**
	 * Check port.
	 *
	 * @param string port
	 * @return true, if valid port.(1-65535)
	 */
	public static boolean checkPort(String port) {
		return port.matches("^()([1-9]|[1-5]?[0-9]{2,4}|6[1-4][0-9]{3}|65[1-4][0-9]{2}|655[1-2][0-9]|6553[1-5])$");
	}
	
	/**
	 * Check version.
	 *
	 * @param string version return true if valid version.
	 * @return true, if successful
	 */
	public static boolean checkVersion(String version) {
		if(version.matches("^\\d+\\.\\d+?$")) {
			try {
				Integer.parseInt(version.split("\\.")[0]);
				Integer.parseInt(version.split("\\.")[1]);
				return true;
			}catch(Exception ex) {}
		}
		return false;
	}
	
	/**
	 * Check empty.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @return true, if one of all that was received is empty
	 */
	public static boolean checkEmpty(String arg0, String...arg1)
	{
		boolean res = arg0.isEmpty();
		for(String arg: arg1)
			res |= arg.isEmpty();
		return res;
	}
	
}
