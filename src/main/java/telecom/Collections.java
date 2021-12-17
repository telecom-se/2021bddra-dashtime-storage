package telecom;

import java.util.HashMap;

import Types.*;

public class Collections {

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
}
