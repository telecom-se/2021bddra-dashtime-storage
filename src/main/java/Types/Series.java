package Types;

import java.util.Collection;
import java.util.LinkedList;

// Une série contient une date, une valeur et un nom
// La db stocke des collections de séries
public class Series {

	private LinkedList<Data> dataList;

	public Series() {
		this.dataList = new LinkedList<Data>();
	}

	public Series(Collection<Data> data) {
		this.dataList = new LinkedList<Data>(data);
	}

	public LinkedList<Data> getData() {
		return dataList;
	}

	public void setData(Collection<Data> data) {
		this.dataList = new LinkedList<Data>(data);
	}

	public void addData(Data data) {
		this.dataList.add(data);
	}

	public void removeData(Data data) {
		this.dataList.remove(data);
	}
}
