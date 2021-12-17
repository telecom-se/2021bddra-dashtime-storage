package LoadDatabase;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.opencsv.CSVWriter;

/* Cette classe génère des données afin de peupler la base
 * Les données sont stockées dans un fichier csv*/

public class DataGenerator {
	
	public DataGenerator(int n) throws IOException {
		
		List<String[]> data = createCsvDataSimple(n);
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
	
	private static List<String[]> createCsvDataSimple(int n) {
        
		// Generate data
		//String[] header = {"name", "timestamp", "temperature"};
        String str = null;
        Random rand = new Random();
        List<String[]> list = new ArrayList<String[]>();        
        String idCapt;
        String temp;
        String ts;
        Date d = null;
        long unixtime;
        for (int i=0; i<n;i++){
   
        	idCapt = String.valueOf(rand.nextInt(64));		//Génère jusqu'à 64 id
        	temp = String.valueOf(rand.nextDouble()*35); 	//Génère jusqu'à 35°
        	
        	d = new Date((long) (1293861599+rand.nextDouble()*60*60*24*365));
        	
        	ts = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(d.getTime());
        	
        	str = idCapt +","+temp+","+ts;
        	list.add(str.split(","));
	
		}
        
        return list;
    }
}
