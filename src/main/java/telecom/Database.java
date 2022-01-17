package telecom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import Types.Data;
import Types.Series;
import Types.Timestamp;

public class Database {
	private SortedMap<Integer, Collections> database;

	public Database() {
		this.database = new TreeMap<Integer, Collections>();
	}

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
			if ((annee > k && annee - 5 < k) || annee == k) {
				cleSerie = k;
				break;
			}
		}

		// Si une collection existe
		if (cleSerie != null) {
			Collections collec = this.database.get(cleSerie);
			collec.addElement(nomSerie, data);
		}
		// Si on ne trouve pas de collection
		else {
			Collections collec = new Collections();
			collec.addElement(nomSerie, data);
			this.database.put(cleSerie, collec);
		}
	}

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
					for(int i = 0; i < s.size(); i++) {
						if(s.get(i).getTimeStamp().getValue() >= start.getTime() && s.get(i).getTimeStamp().getValue() <= finish.getTime()) {
							result.add(s.get(i));
						}
						if(s.get(i).getTimeStamp().getValue() > finish.getTime()) {
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
}
