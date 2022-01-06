package telecom;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
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
		
		Collections collec2 = new Collections();
		
		//Génère un fichier de 500 lignes
		DataGenerator dg = new DataGenerator(500);
		
		//Remplit collec2
		dg.ReadData(collec2,"500");
	}
}
