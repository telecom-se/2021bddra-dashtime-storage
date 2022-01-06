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
		 /*
		Collection<Data> toto = Arrays.asList(new Data(new Timestamp((long) 1), 40.0), new Data(new Timestamp((long) 2), 15.0), new Data(new Timestamp((long) 3), 2024.0));
		collec.addSerie("Toto", new Series(toto));
		
		Collection<Data> greg = Arrays.asList(new Data(new Timestamp((long) 89), 1.0), new Data(new Timestamp((long) 90), -8.9), new Data(new Timestamp((long) 91), 46.2));
		collec.addSerie("Greg", new Series(greg));*/
		
		//System.out.println(collec);

		
		
		
		//collec.SaveData("tmp/serialize_collec.ser");
		collec.LoadData("tmp/serialize_collec.ser");
	

		 // fermeture du flux dans le bloc finally*/
	}
}