package telecom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import Types.*;

public class Collections implements Serializable {

	private static final long serialVersionUID = 2053443173473217147L;
	
	private HashMap<String, Series> collection;

	public Collections() {
		this.collection = new HashMap<String, Series>();
	}

	//Create Collections with loading DB
	public Collections(String dirToLoad) {
		this.collection = new HashMap<String, Series>();
		this.LoadDataFromDir(dirToLoad);
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
	
	public void LoadData(String filename) {
		File fichiers =  new File(filename) ;

		 // ouverture d'un flux sur un fichier
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichiers));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		 // désérialization de l'objet
		Collections m = null;
		try {
			m = (Collections)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(m) ;
		this.collection=m.collection;
		
	}
	
	public void LoadDataFromDir(String directory) {
		File dir = new File(directory);
		File[] listFiles = dir.listFiles();
		
		for(int i=0; i<listFiles.length; i++) {
			this.LoadData(listFiles[i].toString());
		}
	}
	
	public void sortCollections() {
		Set<String> keys = this.getKeys();
		for (String k : keys) {
			this.getSerie(k).sortSeries();
		}
	}
	
	public void SaveData(String filename) {
		File fichier =  new File("db\\" + filename);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichier));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		


		try {
			oos.writeObject(this) ;
		} catch (IOException e) {

			e.printStackTrace();
		}

		
		
		
		
		
	}

}
