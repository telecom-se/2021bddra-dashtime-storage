package telecom;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import Types.*;

public class Collections implements Serializable {

	private static final long serialVersionUID = 1298341689084360243L;

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

	public Set<String> getKeys() {
		return this.collection.keySet();
	}

	public void deltaCompression() {
		this.collection.forEach((titre, serie) -> serie.deltaCompression());
	}

	public void deltaDecompression() {
		this.collection.forEach((titre, serie) -> serie.deltaDecompression());
	}

	@Override
	public String toString() {
		return "Collections [collection=" + collection + "]";
	}
}
