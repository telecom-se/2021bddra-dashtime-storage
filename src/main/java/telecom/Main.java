package telecom;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import Types.*;
import Utils.Init;

public class Main {

	public static void main(String[] args) {

		Collections collec = Init.initialize();

		System.out.println(collec);

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
	}
}