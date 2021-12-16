package telecom;
import java.util.ArrayList;
import java.util.List;

import Types.*;
public class Collections {

	private ArrayList<Series> collection;

	public ArrayList<Series> getCollection() {
		return collection;
	}

	public void setCollection(ArrayList<Series> collection) {
		this.collection = collection;
	}
	
	public void addElement(Series serie) {
		collection.add(serie);
	}
	
	public Collections() {
		collection = new ArrayList();
	}
}
