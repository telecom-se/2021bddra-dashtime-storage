package LoadDatabase;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Types.Collections;
import Types.Data;
import Types.Series;
import telecom.Database;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/* Cette classe génère des données afin de peupler la base
 * Les données sont stockées dans un fichier csv*/

public class DataGenerator {
	
	//Constructeur pour writer
	public DataGenerator(int n) throws IOException {
		
		List<String[]> data = createCsvDataSample(n);
		CSVWriter writer = null;
		try{
			String file = n+".csv";
			writer = new CSVWriter(new FileWriter(".\\data\\" + file));	
			writer.writeAll(data);			
		}catch (IOException e) {
			System.out.println("no");
		}finally {
			writer.close();
		}
	}
	
	//Constructeur pour reader
	public DataGenerator() {
		
	}
	
	private static List<String[]> createCsvDataSample(int n) {
        
		// Generate data
		List<String[]> list = new ArrayList<String[]>();        
		Random rand = new Random();
        String str = null;
        Date d = null;
        String idCapt;
        String temp;
        String ts;
        
        Long startTs = Timestamp.valueOf("1970-01-01 00:00:00").getTime(); //1 janvier 1970 minuit
        Long endTs = Timestamp.valueOf("2025-01-01 00:00:00").getTime(); //1 lanvier 2025 minuit
        Long diff = endTs - startTs + 1;
        
        for (int i=0; i<n;i++){
        	//Id & value generated
        	idCapt = String.valueOf(rand.nextInt(64));		//Génère jusqu'à 64 id
        	temp = String.valueOf(rand.nextDouble()*35); 	//Génère jusqu'à 35°
        	//Timestamp
        	ts = new SimpleDateFormat("yyyyMMddhhmmss").format(new Timestamp(startTs + (long)(Math.random()*diff)));
        	//Parsing
        	str = idCapt +","+temp+","+ts;
        	list.add(str.split(","));	
		}
        return list;
    }
	
	// Fonction permettant la lecture et le remplissage de la base à partir des données générées ci-dessus
	public void ReadData (Database db,String path) throws NumberFormatException, ParseException {
		//Création d'une collection
		//Collections collec = new Collections();
		String PATH = ".\\data\\" + path + ".csv";
		//Remplissage de la collection
		try {
            Reader reader = Files.newBufferedReader(Paths.get(PATH));
            CSVReader csvReader = new CSVReader(reader);      
			String [] buffer;
			
			int i = 0;
			while((buffer = csvReader.readNext()) != null) {				
				//[0] : id, [1] : value, [2] : ts
				Data data = new Data(new Types.Timestamp(new SimpleDateFormat("yyyyMMddhhmmss").parse(buffer[2]).getTime()), Double.parseDouble(buffer[1]));
				db.addElement(buffer[0], data);
				
				i++;
				System.out.println("READ DATA: " + i);
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
