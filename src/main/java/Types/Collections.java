package Types;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Une collection est un ensemble de Series identifiees par cle (ici un String)
 * 
 * Dans notre cas, les Series sont toutes les mesures d'un meme capteur dont le
 * nom/l'identifiant est la cle de la HashMap
 */
public class Collections implements Serializable {

	private static final long serialVersionUID = 2053443173473217147L;

	private HashMap<String, Series> collection; // Ensemble de Series

	public Collections() {
		this.collection = new HashMap<String, Series>();
	}

	/**
	 * Permet d'ajouter un element Data dans notre collection. Cet element sera
	 * ajoute dans la Serie qui lui correspond. Si cette Serie n'existe pas, elle
	 * sera creee.
	 * 
	 * @param nom  Nom de la Serie dans laquelle on veut ajouter notre element
	 * @param data L'element a ajouter
	 */
	public void addElement(String nom, Data data) {
		Set<String> keys = this.collection.keySet();

		if (keys.contains(nom)) {
			System.out.println("Serie existe");
			this.getSerie(nom).addData(data);
		} else {
			this.addSerie(nom, new Series(Set.of(data)));
		}
	}

	/**
	 * Permet d'ajouter une Serie complete dans notre Collection.
	 * 
	 * @param nom   Le nom de la Serie a ajouter
	 * @param serie La Serie a ajouter
	 */
	public void addSerie(String nom, Series serie) {
		this.collection.put(nom, serie);
	}

	/**
	 * Permet de recuperer une Serie en particulier. Si elle n'existe pas, la
	 * fonction retourne NULL.
	 * 
	 * @param nom Le nom de la Serie recherchee
	 * @return Une Serie
	 */
	public Series getSerie(String nom) {
		return this.collection.get(nom);
	}

	public HashMap<String, Series> getCollection() {
		return this.collection;
	}

	/**
	 * Permet de recuperer l'ensemble des cles de la Collection (ici le nom de tous
	 * les capteurs).
	 * 
	 * @return Un Set de toutes les cles
	 */
	public Set<String> getKeys() {
		return this.collection.keySet();
	}

	/**
	 * Effectue la delta compression sur toutes les Series presentes dans la
	 * Collection.
	 */
	public void deltaCompression() {
		this.collection.forEach((titre, serie) -> serie.deltaCompression());
	}

	/**
	 * Effectue une decompression sur toutes les Series presentes dans la
	 * Collection.
	 */
	public void deltaDecompression() {
		this.collection.forEach((titre, serie) -> serie.deltaDecompression());
	}

	@Override
	public String toString() {
		return "Collections [collection=" + collection + "]";
	}

	/**
	 * Permet de restaurer une Collection depuis un fichier.
	 * 
	 * @param filename Le chemin d'acces au fichier de sauvegarde
	 */
	public void LoadData(String filename) {
		File fichiers = new File(filename);

		// ouverture d'un flux sur un fichier
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichiers));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// deserialization de l'objet
		Collections m = null;
		try {
			m = (Collections) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(m);
		m.deltaDecompression();
		this.collection = m.collection;

	}

	/**
	 * Permet de sauvegarder une Collection dans un fichier.
	 * 
	 * @param filename Le fichier ou sauvegarder la Collection
	 */
	public void SaveData(String filename) {
		File fichier = new File(filename);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichier));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		try {
			this.deltaCompression();
			oos.writeObject(this);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
