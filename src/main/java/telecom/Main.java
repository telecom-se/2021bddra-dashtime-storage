package telecom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;

import Types.*;

public class Main {

	public static void main(String[] args) {

		 Collections collec = new Collections();
		
		Collection<Data> toto = Arrays.asList(new Data(new Timestamp((long) 1), 40.0), new Data(new Timestamp((long) 2), 15.0), new Data(new Timestamp((long) 3), 2024.0));
		collec.addSerie("Toto", new Series(toto));
		
		Collection<Data> greg = Arrays.asList(new Data(new Timestamp((long) 89), 1.0), new Data(new Timestamp((long) 90), -8.9), new Data(new Timestamp((long) 91), 46.2));
		collec.addSerie("Greg", new Series(greg));
		
		System.out.println(collec);

		File fichier =  new File("tmp/serialize_greg.ser") ;


		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichier));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		


		try {
			oos.writeObject(collec) ;
		} catch (IOException e) {

			e.printStackTrace();
		}

		
		
		
		
		// dans une méthode main
		 // on simplifie le code en retirant la gestion des exceptions
		File fichiers =  new File("tmp/serialize_greg.ser") ;

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
			m = (Collections)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(m) ;
		System.out.println(m.toString()) ;

		 // fermeture du flux dans le bloc finally*/
	}
}