package telecom;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import Types.Data;

public class Database {
	private HashMap<Integer, Collections> database;

	public Database() {

	}

	public void addElement(String nom, Data data) {
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
			collec.addElement(nom, data);
		}
		// Si on ne trouve pas de collection
		else {
			Collections collec = new Collections();
			collec.addElement(nom, data);
			this.database.put(cleSerie, collec);
		}
	}
}
