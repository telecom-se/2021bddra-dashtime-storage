package Types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Une serie est un ensemble de Datas (des mesures + leur dates (des
 * Timestamps))
 */
public class Series implements Serializable {

	private static final long serialVersionUID = -8962558508769405528L;
	private ArrayList<Data> dataList; // La liste des Datas

	public Series() {
		this.dataList = new ArrayList<Data>();
	}

	public Series(Collection<Data> data) {
		this.dataList = new ArrayList<Data>(data);
	}

	/**
	 * Permet de recuperer un element Data depuis dataList
	 * 
	 * @param i L'indice de l'element a recuperer
	 * @return Un Data
	 */
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

	/**
	 * Rajoute un element Data dans la liste dataList
	 * 
	 * @param data L'element a ajouter
	 */
	public void addData(Data data) {
		this.dataList.add(data);
		Collections.sort(this.dataList);
	}

	/**
	 * Supprime un element Data specifie de la dataList
	 * 
	 * @param data L'element a enlever de la liste
	 */
	public void removeData(Data data) {
		this.dataList.remove(data);
	}

	@Override
	public String toString() {
		return "Series [dataList=" + dataList + "]";
	}

	/**
	 * Permet d'effectuer la delta compression sur notre dataList pour optimiser son
	 * stockage
	 */
	public void deltaCompression() {
		Double previousValue = 0.0;

		for (Data d : this.dataList) {
			d.setValue(d.getValue() - previousValue);
			previousValue += d.getValue();
		}
	}

	/**
	 * Permet de recuperer les donnees qui ont ete compressees via delta compression
	 * puis stockees
	 */
	public void deltaDecompression() {
		Double previousValue = 0.0;

		for (Data d : this.dataList) {
			d.setValue(d.getValue() + previousValue);
			previousValue = d.getValue();
		}
	}

}
