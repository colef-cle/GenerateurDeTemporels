package com.generateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sinusoide3F {

	private final static float PARA_FREQ1 = 75; //  Hz
	private final static float PARA_FREQ2 = 150; //  Hz
	private final static float PARA_FREQ3 = 152; //  Hz
	private final static int PARA_LONGUEUR = 20; //  s
	private final static int PARA_ECH = 20000; //  Hz
	private final static float PARA_AMP1 = 1; //  m/s²
	private final static float PARA_AMP2 = 1.5f; //  m/s²
	private final static float PARA_AMP3 = 1.5f; //  m/s²
	
	private final static String PARA_FOLDER = "C:/Users/VR6070/signauxGeneres"; 
	
	public Sinusoide3F() {
		try {
			File f = new File(PARA_FOLDER);
			f.mkdirs();
			FileWriter fw = new FileWriter(PARA_FOLDER+"/sinus3F.csv");
			
			for(int i = 0; i< (PARA_LONGUEUR * PARA_ECH) ; i++) {
				/*Calcul de la pulsation W = 2.PI.f*/
				float w1 = (float) (2 * Math.PI * PARA_FREQ1);
				float w2 = (float) (2 * Math.PI * PARA_FREQ2);
				float w3 = (float) (2 * Math.PI * PARA_FREQ3);
				double val = PARA_AMP1 * Math.sin( (float)i / (float)PARA_ECH * w1 );
				val += PARA_AMP2 * Math.sin( (float)i / (float)PARA_ECH * w2 );
				val += PARA_AMP3 * Math.sin( (float)i / (float)PARA_ECH * w3 );
				
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
		new Sinusoide3F();
	}

}
