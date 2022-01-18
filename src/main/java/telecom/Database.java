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

public class Database {

	private final String directory = "./data/db_saves/";

	private SortedMap<Integer, Collections> database;

	public Database() {
		this.database = new TreeMap<Integer, Collections>();
	}

	// Ajoute un element Data dans la Collections et la Serie appropries
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

	// Retourne toutes les donnees d'un capteur dont on donne l'id
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

	// Retourne toutes les mesures entre deux dates donnees
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
				HashMap<String, Series> series = this.database.get(k).getAllSeries();
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

	// Retourne toutes les mesures d'un capteur dont on donne l'id entre deux dates
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
				HashMap<String, Series> series = this.database.get(k).getAllSeries();
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

	// Recupere une collection depuis son fichier dans ./data/db_saves
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

	// Liste toutes les Collections dans /data/db_saves et les deserialize
	public void LoadDB() {
		File dir = new File(this.directory);
		File[] listFiles = dir.listFiles();

		for (File file : listFiles) {
			System.out.println(file.toString());
			this.LoadData(file.toString());
		}

	}

	// Sauvegarde toutes les Collections dans /data/db_saves
	public void saveDB() {
		Set<Integer> keys = this.database.keySet();

		for (Integer k : keys) {
			Collections collec = this.database.get(k);
			collec.SaveData(this.directory + k);
		}
	}

	// Donne le nombre total d'elements de la bdd
	public long sizeTotal() {
		long taille = 0;

		ArrayList<Data> result = new ArrayList<Data>();
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

	// Initialise la bdd avec des donnees generees aleatoirement
	public void randomInit() {
		try {
			DataGenerator dg = new DataGenerator(500);
			dg.ReadData(this, "500");
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
