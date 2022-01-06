package telecom;

import java.io.Serializable;
import java.util.HashMap;

import Types.*;

public class Collections implements Serializable {

	private static final long serialVersionUID = 2053443173473217147L;
	
	private HashMap<String, Series> collection;

	public Collections() {
		this.collection = new HashMap<String, Series>();
	}

	public void addSerie(String nom, Series serie) {
		this.collection.put(nom, serie);
	}
	
	public Series getSerie(String nom) {
		return this.collection.get(nom);
	}

	@Override
	public String toString() {
		return "Collections [collection=" + collection + "]";
	}
	
	

}
