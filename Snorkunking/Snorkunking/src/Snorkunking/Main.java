package Snorkunking;

import java.io.File;

import javax.swing.JDialog;

public class Main {
	static boolean continu = true;
	
	public static void main(String[] args) {
		File f = new File("musicjaw.wav");
		try {
			StreamingLineSound s = new StreamingLineSound (f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (continu == true) {
			Affichage affichage = new Affichage();
			affichage.gameOver();
		}
	}

	
}
