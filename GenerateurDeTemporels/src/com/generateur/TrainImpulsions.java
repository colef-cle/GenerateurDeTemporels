package com.generateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TrainImpulsions {

	private final static int PARA_LONGUEUR = 2; //  s
	private final static int PARA_ECH = 20000; //  Hz
	private final static float PARA_AMP = 1; //  m/s²
	private final static int PARA_FREQ = 30 ; // Hz 
	
	private final static String PARA_FOLDER = "C:/Users/VR6070/signauxGeneres"; 
	
	public TrainImpulsions() {
		try {
			File f = new File(PARA_FOLDER);
			f.mkdirs();
			FileWriter fw = new FileWriter(PARA_FOLDER+"/impulsions.csv");
			
			for(int i = 0; i< (PARA_LONGUEUR * PARA_ECH) ; i++) {
				double val = 0;
				
				if(i % (PARA_ECH/PARA_FREQ) == 0 ) {
					val = PARA_AMP;
				}
				
				
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
		new TrainImpulsions();
	}

}
