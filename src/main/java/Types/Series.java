package Types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Une série contient une date, une valeur et un nom
// La db stocke des collections de séries
public class Series implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8962558508769405528L;
	private ArrayList<Data> dataList;

	public Series() {
		this.dataList = new ArrayList<Data>();
	}

	public Series(Collection<Data> data) {
		this.dataList = new ArrayList<Data>(data);
	}

	public Data get(Integer i) {
		return this.dataList.get(i);
	}
	
	public Integer size() {
		return this.dataList.size();
	}

	public ArrayList<Data> getData() {
		return dataList;
	}

	public void setData(Collection<Data> data) {
		this.dataList = new ArrayList<Data>(data);
		Collections.sort(this.dataList);
	}

	public void addData(Data data) {
		this.dataList.add(data);
		Collections.sort(this.dataList);
	}

	public void removeData(Data data) {
		this.dataList.remove(data);
	}

	@Override
	public String toString() {
		return "Series [dataList=" + dataList + "]";
	}

	public void deltaCompression() {
		Double previousValue = 0.0;

		for (Data d : this.dataList) {
			d.setValue(d.getValue() - previousValue);
			previousValue += d.getValue();
		}
	}

	public void deltaDecompression() {
		Double previousValue = 0.0;

		for (Data d : this.dataList) {
			d.setValue(d.getValue() + previousValue);
			previousValue = d.getValue();
		}
	}

}
