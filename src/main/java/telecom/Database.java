package telecom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import LoadDatabase.DataGenerator;
import Types.Collections;
import Types.Data;
import Types.Series;

/**
 * Classe principale de notre systeme de stockage. Database est un ensemble de
 * Collection. Chaque Collection regroupe les Datas de tous les capteur pendant
 * un intervalle de 5 ans (cet intervalle est a adapter au volume de donnees et
 * au nombre de capteurs a gerer ; dans notre cas, il a ete decide
 * arbitrairement car nous n'avons pas de vraies donnees a gerer). Le but de
 * cette fragmentation est d'ameliorer le temps des recherches sur de grands
 * volumes de donnees.
 */
public class Database {
	// Dossier contenant les sauvegardes de nos Collections
	private final String directory = "./data/db_saves/";

	private SortedMap<Integer, Collections> database; // L'ensemble de nos Collections

	public Database() {
		this.database = new TreeMap<Integer, Collections>();
	}

	/**
	 * Permet d'ajouter un element Data dans la BDD. Cree automatiquement les
	 * Collections et Series adaptees si elles n'existent pas deja.
	 * 
	 * @param nomSerie Le nom du capteur dont vient la mesure
	 * @param data     L'element a ajouter
	 */
	public void addElement(String nomSerie, Data data) {
		Date elementDate = new Date();
		elementDate.setTime(data.getTimeStamp().getValue());

		Calendar cal = Calendar.getInstance();
		cal.setTime(elementDate);

		Integer annee = cal.get(Calendar.YEAR);

		Integer cleSerie = null;
		Set<Integer> keys = this.database.keySet();

		// On cherche si une collection avec le bon intervalle de temps (ici 5 ans)
		// existe
		for (Integer k : keys) {
			if (Integer.valueOf(k) < Integer.valueOf(annee) && Integer.valueOf(annee) < Integer.valueOf(k + 5)
					|| annee.equals(k)) {
				cleSerie = k;
				break;
			}
		}

		// Si une collection existe
		if (cleSerie != null) {
			System.out.println("La collection existe");
			Collections collec = this.database.get(cleSerie);
			collec.addElement(nomSerie, data);
		}
		// Si on ne trouve pas de collection
		else {
			System.out.println("La collection n'existe pas");
			cleSerie = annee - annee % 5;

			Collections collec = new Collections();
			collec.addElement(nomSerie, data);
			this.database.put(cleSerie, collec);
		}
	}

	/**
	 * Permet de recuperer toutes les mesures d'un capteur.
	 * 
	 * @param nomSerie Le nom du capteur
	 * @return La liste de toutes les mesures (Datas) du capteur
	 */
	public ArrayList<Data> getCompleteSerie(String nomSerie) {
		ArrayList<Data> result = new ArrayList<Data>();
		Set<Integer> keys = this.database.keySet();

		for (Integer k : keys) {
			Series serie = this.database.get(k).getSerie(nomSerie);
			for (Data d : serie.getData()) {
				result.add(d);
			}
		}

		return result;
	}

	/**
	 * Permet de recuperer les mesures de tous les capteurs entre deux dates.
	 * 
	 * @param start  La date de debut de la recherche
	 * @param finish La date de fin de la recherche
	 * @return La liste de toutes les mesures (Datas) entre les deux dates
	 */
	public ArrayList<Data> getDataBetween(Date start, Date finish) {
		ArrayList<Data> result = new ArrayList<Data>();
		Set<Integer> keys = this.database.keySet();

		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		Integer beginning = cal.get(Calendar.YEAR);
		cal.setTime(finish);
		Integer end = cal.get(Calendar.YEAR);

		for (Integer k : keys) {
			if (k + 5 > beginning && end >= k) {
				// Si l'intervalle de temps correspond à la requete
				HashMap<String, Series> series = this.database.get(k).getCollection();
				Set<String> keys2 = series.keySet();
				for (String l : keys2) {
					Series s = series.get(l);
					for (int i = 0; i < s.size(); i++) {
						if (s.get(i).getTimeStamp().getValue() >= start.getTime()
								&& s.get(i).getTimeStamp().getValue() <= finish.getTime()) {
							result.add(s.get(i));
						}
						if (s.get(i).getTimeStamp().getValue() > finish.getTime()) {
							break;
						}
					}
				}
			}
			if (end < k) {
				// Si on a depasse l'intervalle
				break;
			}
		}

		return result;
	}

	/**
	 * Permet de recuperer les mesures d'un capteur entre deux dates.
	 * 
	 * @param nom    Le nom du capteur
	 * @param start  La date de debut de la recherche
	 * @param finish La date de fin de la recherche
	 * @return La liste de toutes les mesures (Datas) d'un capteur entre les deux
	 *         dates
	 */
	public ArrayList<Data> getOneSerieBetween(String nom, Date start, Date finish) {
		ArrayList<Data> result = new ArrayList<Data>();
		Set<Integer> keys = this.database.keySet();

		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		Integer beginning = cal.get(Calendar.YEAR);
		cal.setTime(finish);
		Integer end = cal.get(Calendar.YEAR);

		for (Integer k : keys) {
			if (k + 5 > beginning && end >= k) {
				// Si l'intervalle de temps correspond à la requete
				HashMap<String, Series> series = this.database.get(k).getCollection();
				Set<String> keys2 = series.keySet();

				if (keys2.contains(nom)) {
					// Si la serie existe
					Series s = series.get(nom);
					for (int i = 0; i < s.size(); i++) {
						if (s.get(i).getTimeStamp().getValue() >= start.getTime()
								&& s.get(i).getTimeStamp().getValue() <= finish.getTime()) {
							result.add(s.get(i));
						}
						if (s.get(i).getTimeStamp().getValue() > finish.getTime()) {
							break;
						}
					}
				}
			}
			if (end < k) {
				// Si on a depasse l'intervalle
				break;
			}
		}

		return result;
	}

	/**
	 * Permet de restaurer les donnees d'une Collection dans la database.
	 * 
	 * @param filename Le chemin d'acces au fichier de sauvegarde
	 */
	private void LoadData(String filename) {
		File fichiers = new File(filename);
		String nomfichier = filename.split("\\\\")[3];
		System.out.println(nomfichier);

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
			m = (Collections) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(m);

		this.database.put(Integer.parseInt(nomfichier), m);

	}

	/**
	 * Permet de restaurer toutes les Collections sauvegardees.
	 */
	public void LoadDB() {
		File dir = new File(this.directory);
		File[] listFiles = dir.listFiles();

		for (File file : listFiles) {
			System.out.println(file.toString());
			this.LoadData(file.toString());
		}

	}

	/**
	 * Permet de sauvegarder toutes les Collesctions de la Database.
	 */
	public void saveDB() {
		Set<Integer> keys = this.database.keySet();

		for (Integer k : keys) {
			Collections collec = this.database.get(k);
			collec.SaveData(this.directory + k);
		}
	}

	/**
	 * Permet d'obtenir le nombre de mesures (Datas) dans la Databes.
	 * 
	 * @return Le nombre d'elements Data dans la Database
	 */
	public long sizeTotal() {
		long taille = 0;

		Set<Integer> keys = this.database.keySet();

		for (Integer k : keys) {
			Collections collec = this.database.get(k);
			Set<String> keys2 = collec.getKeys();
			for (String k2 : keys2) {
				Series serie = collec.getSerie(k2);
				taille += serie.getData().size();
			}
		}

		return taille;
	}

	/**
	 * Initialise la Database avec des mesures generees aleatoirement.
	 */
	public void randomInit() {
		try {
			DataGenerator dg = new DataGenerator(500); // Genere 500 mesures aleatoires
			DataGenerator.ReadData(this, "500");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
