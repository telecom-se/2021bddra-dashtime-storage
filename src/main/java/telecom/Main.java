package telecom;

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
<<<<<<< Updated upstream
=======
//import Utils.Init;
>>>>>>> Stashed changes

public class Main {

	public static void main(String[] args) throws IOException {

<<<<<<< Updated upstream
		/*
		Collections collec = new Collections();

		Collection<Data> toto = Arrays.asList(new Data(new Timestamp((long) 1), 40.0), new Data(new Timestamp((long) 2), 15.0), new Data(new Timestamp((long) 3), 2024.0));
		collec.addSerie("Toto", new Series(toto));
		
		Collection<Data> greg = Arrays.asList(new Data(new Timestamp((long) 89), 1.0), new Data(new Timestamp((long) 90), -8.9), new Data(new Timestamp((long) 91), 46.2));
		collec.addSerie("Greg", new Series(greg));
	
		System.out.println(collec);
		*/
		
		Collections collec2 = new Collections();
=======
//		Collections collec = Init.initialize();
//
//		Set<String> keys = collec.getKeys();
//		for (String k : keys) {
//			for (Data d : collec.getSerie(k).getData()) {
//				System.out.println(d.getValue());
//			}
//			
//		}
//		
//		System.out.println("==== Encryption ====");
//		collec.deltaCompression();
//		
//		for (String k : keys) {
//			for (Data d : collec.getSerie(k).getData()) {
//				System.out.println(d.getValue());
//			}
//			
//		}
//		
//		System.out.println("==== Desencryption ====");
//		collec.deltaDecompression();
//		
//		for (String k : keys) {
//			for (Data d : collec.getSerie(k).getData()) {
//				System.out.println(d.getValue());
//			}
//			
//		}
//
//		System.out.println(collec);
>>>>>>> Stashed changes
		
		/* // Creating and loading data
		Collections collec = new Collections();	
		DataGenerator dg = new DataGenerator(300);
		dg.ReadData(collec, "300");
		collec.SaveData("300");
		*/
		
<<<<<<< Updated upstream
		//Remplit collec2
		dg.ReadData(collec2,"500");
		
=======
		Collections collec = new Collections("db");
		collec.sortCollections();
	
		System.out.println("done");
>>>>>>> Stashed changes
	}
}
