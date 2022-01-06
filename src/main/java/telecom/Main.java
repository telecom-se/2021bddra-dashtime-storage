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

public class Main {

	public static void main(String[] args) throws IOException {

		/*
		Collections collec = new Collections();

		Collection<Data> toto = Arrays.asList(new Data(new Timestamp((long) 1), 40.0), new Data(new Timestamp((long) 2), 15.0), new Data(new Timestamp((long) 3), 2024.0));
		collec.addSerie("Toto", new Series(toto));
		
		Collection<Data> greg = Arrays.asList(new Data(new Timestamp((long) 89), 1.0), new Data(new Timestamp((long) 90), -8.9), new Data(new Timestamp((long) 91), 46.2));
		collec.addSerie("Greg", new Series(greg));
	
		System.out.println(collec);
		*/
		
		Collections collec2 = new Collections();
		
		//Génère un fichier de 500 lignes
		DataGenerator dg = new DataGenerator(500);
		
		//Remplit collec2
		dg.ReadData(collec2,"500");
		
	}
}
