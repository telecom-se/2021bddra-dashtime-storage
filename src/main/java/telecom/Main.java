package telecom;

import java.text.ParseException;

import java.io.IOException;
import LoadDatabase.DataGenerator;

public class Main {

	public static void main(String[] args) throws IOException {

		// Genere un fichier de 500 lignes
		// DataGenerator dg = new DataGenerator(500);
		Database db = new Database();

		// Remplit collec2
		try {
			DataGenerator.ReadData(db, "500");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(db.sizeTotal());

		db.saveDB();

		System.out.println("========= LOAD DB ==========");

		Database db2 = new Database();

		db2.LoadDB();
		System.out.println("db2 size: " + db2.sizeTotal());

	}

}
