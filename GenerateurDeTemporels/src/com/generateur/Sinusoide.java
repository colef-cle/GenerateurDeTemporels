package com.generateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sinusoide {

	private final static float PARA_FREQ = 150; //  Hz
	private final static int PARA_LONGUEUR = 2; //  s
	private final static int PARA_ECH = 20000; //  Hz
	private final static float PARA_AMP = 1; //  m/s²
	
	private final static String PARA_FOLDER = "C:/Users/VR6070/signauxGeneres"; 
	
	public Sinusoide() {
		try {
			File f = new File(PARA_FOLDER);
			f.mkdirs();
			FileWriter fw = new FileWriter(PARA_FOLDER+"/sinus.csv");
			
			for(int i = 0; i< (PARA_LONGUEUR * PARA_ECH) ; i++) {
				/*Calcul de la pulsation W = 2.PI.f*/
				float w = (float) (2 * Math.PI * PARA_FREQ);
				
				double val = PARA_AMP * Math.sin( (float)i / (float)PARA_ECH * w );
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
		new Sinusoide();
	}

}
