package Snorkunking;

import java.awt.Font;

import edu.princeton.cs.introcs.StdDraw;

public class Bouton {
	int modeJeu;
	
	public Bouton() {
		
	}


	public Bouton(double [][] coord, int nb, String [] text, int [] mode, int resolution) {
		int a = 0;
		Font currentFont = StdDraw.getFont();
		float size = 10*30/(10+resolution);
		currentFont = currentFont.deriveFont(size);
		StdDraw.setFont(currentFont);
		for (int i =0 ;i<nb;i++) {
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(coord[i][0], coord[i][1], coord[i][2], coord[i][3]);
				StdDraw.setPenColor(StdDraw.BOOK_BLUE);
				StdDraw.text(coord[i][0], coord[i][1], text[i]);
			}
		boolean colorbo = false;
		while (a<10000000) {
			boolean noRepeat = false;
			double xsouris = StdDraw.mouseX();
			double ysouris = StdDraw.mouseY();
			for (int i = 0; i<nb; i++) {
			
			if (xsouris<=(coord[i][0]+coord[i][2]) && xsouris>=(coord[i][0]-coord[i][2])) {
				if (ysouris<=(coord[i][1]+coord[i][3]) && ysouris>=(coord[i][1]-coord[i][3])) {
					if (colorbo == false && noRepeat == false) {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledRectangle(coord[i][0], coord[i][1], coord[i][2], coord[i][3]);
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.text(coord[i][0], coord[i][1], text[i]);
					colorbo = true;
					modeJeu = mode[i];
					noRepeat = true;
					}
					a++;
				}
			}
			else {
				if (colorbo == true && noRepeat == false) {
					//Bouton bouton = new Bouton(coord, nb, text, mode, resolution);
				}
				
			}
	}

}
	}}
