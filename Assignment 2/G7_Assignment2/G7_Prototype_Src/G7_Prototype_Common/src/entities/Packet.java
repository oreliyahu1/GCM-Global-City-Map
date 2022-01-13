package entities;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import config.packetTransfer.Actions.ACTION_TYPE;

/**
 * The Class Packet.
 * Used for transporting data to client-server.
 * @param <T> the generic type
 */
public class Packet<T> extends AbstractJsonToString{
	
	/** The action. */
	private ACTION_TYPE action;
	
	/** The window. */
	private String window;
	
	/** The sub action. */
	private String sub_action;
	
	/** The token. */
	private String token;
	
	/** The data. */
	private String data;
	
	/**
	 * Instantiates a new city.
	 * Need to be used by jackson(lib).
	 */
	@SuppressWarnings("unused")
	private Packet() {}
	
	/**
	 * Instantiates a new packet.
	 *
	 * @param action the action
	 * @param window the window
	 * @param sub_action the sub action
	 * @param token the token
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Packet(ACTION_TYPE action, String window, String sub_action, String token, ArrayList<T> data) throws IOException{
		this.action = action;
		this.window = window;
		this.sub_action = sub_action;
		this.token = token;
		ObjectMapper mapper = new ObjectMapper();
		this.data = mapper.writeValueAsString(data);
	}
	
	/**
	 * Instantiates a new packet.
	 *
	 * @param action the action
	 * @param window the window
	 * @param sub_action the sub action
	 * @param token the token
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Packet(ACTION_TYPE action, String window, String sub_action , String token, T data) throws IOException{
		this.action = action;
		this.window = window;
		this.sub_action = sub_action;
		this.token = token;
		ObjectMapper mapper = new ObjectMapper();
		this.data = mapper.writeValueAsString(data);
	}

	/**
	 * Object translation.
	 * translate the date json to ArrayList<E>
	 * @param <E> the element type
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public <E> ArrayList<E> objectTranslation() throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(data, new TypeReference<ArrayList<E>>(){});
	}
	
	/**
	 * Object translation.
	 * translate the date json to <E>
	 * @param <E> the element type
	 * @return the element
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public <E> E objectTranslationElem() throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(data, new TypeReference<E>(){});
	}
	
	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public ACTION_TYPE getAction() {
		return action;
	}


	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(ACTION_TYPE action) {
		this.action = action;
	}


	/**
	 * Gets the window.
	 *
	 * @return the window
	 */
	public String getWindow() {
		return window;
	}


	/**
	 * Sets the window.
	 *
	 * @param window the new window
	 */
	public void setWindow(String window) {
		this.window = window;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the sub action.
	 *
	 * @return the sub action
	 */
	public String getSub_action() {
		return sub_action;
	}

	/**
	 * Sets the sub action.
	 *
	 * @param sub_action the new sub action
	 */
	public void setSub_action(String sub_action) {
		this.sub_action = sub_action;
	}
}
