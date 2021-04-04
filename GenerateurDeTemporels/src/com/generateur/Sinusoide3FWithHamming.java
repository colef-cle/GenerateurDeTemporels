package com.generateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sinusoide3FWithHamming {

	private final static float PARA_FREQ1 = 75; //  Hz
	private final static float PARA_FREQ2 = 150; //  Hz
	private final static float PARA_FREQ3 = 820; //  Hz
	private final static int PARA_LONGUEUR = 2; //  s
	private final static int PARA_ECH = 20000; //  Hz
	private final static float PARA_AMP1 = 1; //  m/s²
	private final static float PARA_AMP2 = 1.5f; //  m/s²
	private final static float PARA_AMP3 = 0.3f; //  m/s²
	
	private final static String PARA_FOLDER = "C:/Users/VR6070/signauxGeneres"; 
	
	public Sinusoide3FWithHamming() {
		try {
			File f = new File(PARA_FOLDER);
			f.mkdirs();
			FileWriter fw = new FileWriter(PARA_FOLDER+"/sinus3F-With-Hamming.csv");
			
			int  N = (PARA_LONGUEUR * PARA_ECH);
	    	double[] ham =  new double[N];
	        float divider = 0;
	        for(int i = 0; i< N; i++){
	        	ham[i] = 0.54 - 0.46 * Math.cos(2 * Math.PI * i / (N-1));
	        	divider += ham[i];
	        }
	        		
			
			for(int i = 0; i< N ; i++) {
				/*Calcul de la pulsation W = 2.PI.f*/
				float w1 = (float) (2 * Math.PI * PARA_FREQ1);
				float w2 = (float) (2 * Math.PI * PARA_FREQ2);
				float w3 = (float) (2 * Math.PI * PARA_FREQ3);
				double val = PARA_AMP1 * Math.sin( (float)i / (float)PARA_ECH * w1 );
				val += PARA_AMP2 * Math.sin( (float)i / (float)PARA_ECH * w2 );
				val += PARA_AMP3 * Math.sin( (float)i / (float)PARA_ECH * w3 );
				
				val *= ham[i];
				val *= N/divider;
				
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
		new Sinusoide3FWithHamming();
	}

}
