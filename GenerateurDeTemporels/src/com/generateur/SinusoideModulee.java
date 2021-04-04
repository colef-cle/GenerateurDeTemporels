package com.generateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SinusoideModulee {

	private final static float PARA_FREQ = 150; //  Hz
	private final static float PARA_FREQ_MOD = 25; //  Hz
	private final static int PARA_LONGUEUR = 10; //  s
	private final static int PARA_ECH = 20000; //  Hz
	private final static float PARA_AMP = 1; //  m/s²
	private final static float PARA_AMP_MOD = 0.25f; //  m/s²
	
	private final static String PARA_FOLDER = "C:/Users/VR6070/signauxGeneres"; 
	
	public SinusoideModulee() {
		try {
			File f = new File(PARA_FOLDER);
			f.mkdirs();
			FileWriter fw = new FileWriter(PARA_FOLDER+"/sinusModule.csv");
			
			for(int i = 0; i< (PARA_LONGUEUR * PARA_ECH) ; i++) {
				/*Calcul de la pulsation W = 2.PI.f*/
				float w = (float) (2 * Math.PI * PARA_FREQ);
				float w_mod = (float) (2 * Math.PI * PARA_FREQ_MOD);
				
				double val = PARA_AMP * ( 1 + PARA_AMP_MOD * Math.sin( (float)i / (float)PARA_ECH * w_mod )) * Math.sin( (float)i / (float)PARA_ECH * w );
				
				
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
		new SinusoideModulee();
	}

}
