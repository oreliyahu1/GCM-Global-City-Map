package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class AbstractJsonToString.
 */
public abstract class AbstractJsonToString {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Get json string
	 */
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
        try {
			return mapper.writeValueAsString(this);
		} catch (IOException e) {
			System.out.println("Error toString: " + e);
		}
        return null;
	}
	
	/**
	 * To object.
	 *
	 * @param <E> the element type
	 * @param type the type
	 * @param json the json
	 * @return the e
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <E> E toObject(Class<E> type, String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return  mapper.readValue(json, type);
	}
	
	/**
	 * Hash map to array list json entities.
	 *
	 * @param hm the hash map from db
	 * @return the array list of cities
	 */
	public static ArrayList<String> hashMapToArrayListJsonEntities(Function<HashMap<String, String>, AbstractJsonToString> func, HashMap<String, ArrayList<String>> hm){
		ArrayList<String> _re = new ArrayList<String>();
		while(!hm.isEmpty()) {
			HashMap<String, String> hmCity = new HashMap<String, String>();
			Iterator<Entry<String, ArrayList<String>>> iterator = hm.entrySet().iterator(); 
			while (iterator.hasNext()) {
				Entry<String, ArrayList<String>> entry = iterator.next(); 
				hmCity.put(entry.getKey(), entry.getValue().remove(0));
				if(entry.getValue().isEmpty()) //Because of remove by running need using iterator
					iterator.remove();
			}
			_re.add(func.apply(hmCity).toString());
		}
		return _re;
	}
}
