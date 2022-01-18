package telecom;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import LoadDatabase.DataGenerator;
import Types.*;
import Utils.Init;

public class Main {

	public static void main(String[] args) throws IOException {
/*
		Collections collec = Init.initialize();

		Set<String> keys = collec.getKeys();
		for (String k : keys) {
			for (Data d : collec.getSerie(k).getData()) {
				System.out.println(d.getValue());
			}
			
		}
		
		System.out.println("==== Encryption ====");
		collec.deltaCompression();
		
		for (String k : keys) {
			for (Data d : collec.getSerie(k).getData()) {
				System.out.println(d.getValue());
			}
			
		}
		
		System.out.println("==== Desencryption ====");
		collec.deltaDecompression();
		
		for (String k : keys) {
			for (Data d : collec.getSerie(k).getData()) {
				System.out.println(d.getValue());
			}
			
		}

		System.out.println(collec);
		*/
		Collections collec2 = new Collections();
		
		//Génère un fichier de 500 lignes
		DataGenerator dg = new DataGenerator(500);
		Database db = new Database();
		
		//Remplit collec2
		try {
			dg.ReadData(db,"500");
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
		
		/*
		System.out.println(new Date());
		long timestamp = new Date().getTime();
		System.out.println(timestamp);
		Date d = new Date();
		d.setTime(timestamp);
		System.out.println(d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		System.out.println(cal.get(Calendar.YEAR));
		*/
		
		
	}
		
}
