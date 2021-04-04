package com.generateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Hamming {

	private final static int PARA_LONGUEUR = 2; //  s
	private final static int PARA_ECH = 20000; //  Hz
	
	private final static String PARA_FOLDER = "C:/Users/VR6070/signauxGeneres"; 
	
	public Hamming() {
		try {
			File f = new File(PARA_FOLDER);
			f.mkdirs();
			FileWriter fw = new FileWriter(PARA_FOLDER+"/hamming.csv");
			int N = PARA_LONGUEUR * PARA_ECH;
			float divider = 0;
			double[] ham = new double[N];
			
			for(int i = 0; i< N ; i++) {
				ham[i] = ( 0.54 - 0.46 * Math.cos(2 * Math.PI * i / (N-1)) );
				divider += ham[i];
			}
			
			for(int i = 0; i< N ; i++) {
				double val = ham[i] * N / divider;
				fw.write(val+"\r\n");
				
				if(i % PARA_ECH == 0) {
					fw.flush();
				}
			}
			fw.flush();fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Hamming();
	}

}
