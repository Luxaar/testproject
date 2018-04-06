package Snorkunking;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Tresor {
	int a;                                      // on initialise toutes les variables du programme       
	boolean game;								// indique si le jeu est lanc�
	boolean colorbo;							// indique si le joueur est sur le 'bouton' (pour l'enclencher, pas un 'vrai bouton')
	int modeJeu;								// indique le mode de jeu (AI ou 2 joueurs)
	int manche = 1;                             // 
	int scorefp1 = 0;							// score de joueur 1. Il �volue lorsque le joueur remonte a la surface
	int scorefp2 = 0;                           // score de joueur 2. ""
	int joueur;									// indique le tour du joueur ( 1 pour p1 et 2 pour p2)
	int profp1 = 0;								// profondeur de p1
	int profp2 = 0;								// profondeur de p2
	int scorep1 = 0;							// score interm�diaire de p1. score sur les tr�sors transport�s non remont�s.
	int tp1 = 0;								// nombre de tr�sors port�s par p1
	int scorep2 = 0;							// score interm�diaire de p2. ""
	int tp2 = 0;								// nombre de tr�sors port�s par p2
	int max1 = 12;								// max de niveaux dans cave 1
	int min1 = 9;								// min de niveaux dans cave 1
	int max2 = 9;								// max de niveaux dans cave 2
	int min2 = 6;								// min de niveaux dans cave 2
	int max3 = 6;								// max de niveaux dans cave 3
	int min3 = 3;								// min de niveaux dans cave 3
	int coeffoxygene = 2;           
	int nivcave1 = min1 + (int)(Math.random()*((max1-min1)+1));        // nombre de niveaux de cave 1 au hasard
	int nivcave2 = min2 + (int)(Math.random()*((max2-min2)+1));		   // nombre de niveaux de cave 2 au hasard
	int nivcave3 = min3 + (int)(Math.random()*((max3-min3)+1));        // nombre de niveaux de cave 3 au hasard
	int nbniv = nivcave1 + nivcave2 + nivcave3;						// nombre de niveaux total. �volue avec les manches.
	int nbini = nbniv;												// "". reste statique.
	int [][] tresors = new int [nbniv][2];                          //
	int mint1 = 1;
	int maxt1 = 3;
	int mint2 = 5;
	int maxt2 = 8;
	int mint3 = 10;
	int maxt3 = 12;
	int oxygene = nbini*coeffoxygene;
	int resolution = 3;
	double [][] bmenu = {{335,320,150,25},{930,320,150,25},{1100,100,120,25}};
	String [] smenu = {"VS AI", "MULTIPLAYER", "OPTIONS"};
	int [] modeMenu = {1,2,3};
	
	
	public void settresors(int nivplongeur) {
		if (joueur == 1) {
			scorep1 = scorep1 + tresors[nivplongeur][1];
			tp1++;
		}
		else if (joueur == 2) {
			scorep2 = scorep2 + tresors[nivplongeur][1];
			tp2++;
		}
		tresors[nivplongeur][0] = 0;
		tresors[nivplongeur][1] = 0;
	}
	
	public void manche2set() {
		profp1 = 0;
		profp2 = 0;
		manche++;
		oxygene = nbini*coeffoxygene;
		int compt = 0;
		int [] [] tabi = new int [nbniv][2];
		for (int i = 0;i<nivcave1;i++) {
			if (tresors[i][0] != 0) {
				tabi[compt] = tresors[i];
				compt++;
			}
		}
		int n = nivcave1;
		nivcave1 = compt;
		compt = 0;
		for (int i = 0;i<nivcave2;i++) {
			if (tresors[i+n][0] != 0) {
				tabi[compt+nivcave1] = tresors[i + n];
				compt++;
			}
		}
		n = n + nivcave2;
		nivcave2 = compt;

		compt = 0;
		for (int i = 0;i<nivcave3;i++) {
			if (tresors[i+n][0] != 0) {
				tabi[compt+nivcave1 + nivcave2] = tresors[i+n];
				compt++;
			}
		}
		nivcave3 = compt;

		nbniv = nivcave1 + nivcave2 + nivcave3;
		tresors = tabi; 

	}
	

	public Tresor() {
		for(int i = 0; i<nivcave1;i++) {
			tresors[i][0] = 1;
			tresors[i][1] =  mint1 + (int)(Math.random()*((maxt1-mint1)+1));
		}
		
		for(int i = 0; i<nivcave2;i++) {
			tresors[i+nivcave1][0] = 1;
			tresors[i+nivcave1][1] =  mint2 + (int)(Math.random()*((maxt2-mint2)+1));
		}
		
		for(int i = 0; i<nivcave3;i++) {
			tresors[i+nivcave1+nivcave2][0] = 1;
			tresors[i+nivcave1+nivcave2][1] =  mint3 + (int)(Math.random()*((maxt3-mint3)+1));
		}
		

		
		
	}

	public void afficher(int n1, int n2, int n3) {
		
		if (oxygene == coeffoxygene*nbniv) {
		StdDraw.setCanvasSize(10*1000/(10+resolution), 10*965/(10+resolution));
		}
		StdDraw.setXscale(0, 1000);       // on cr�� la fenetre
		StdDraw.setYscale(0, 965);
		Font currentFont = StdDraw.getFont();
		float size = 10*18/(10+resolution);
		currentFont = currentFont.deriveFont(size);
		StdDraw.setFont(currentFont);
		//Defaff affichage = new Defaff();
		if (manche == 1) {
			StdDraw.picture(1000/2, 965/2, "fond ecran tour 1.jpg",1000, 965);
		}
		else if (manche == 2) {
			StdDraw.picture(1000/2, 965/2, "fond ecran tour 2.jpg",1000, 965);
		}
		else if (manche == 3) {
			StdDraw.picture(1000/2, 965/2, "fond ecran tour 3.jpg",1000, 965);
		}
		for (int i = 0; i<n1; i++) {
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.filledRectangle(525, 850-26*i, 400, 12);
			StdDraw.picture(500, 850-26*i, "coffre.jpg", 28, 22);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*i, "x "+ tresors[i][0]);
		}
		for (int i = 0; i<n2; i++) {
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledRectangle(525, 850-26*(i+n1), 400, 12);
			StdDraw.picture(500, 850-26*(i+n1), "coffre.jpg", 28, 22);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*(i+n1), "x "+ tresors[i+n1][0]);
			}	
		for (int i = 0; i<n3; i++) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledRectangle(525, 850-26*(i+n1+n2), 400, 12);
			StdDraw.picture(500, 850-26*(i+n1+n2), "coffre.jpg", 28, 22);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*(i+n1+n2), "x "+ tresors[i+n1+n2][0]);
			}
		
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text(50, 900, "OXYGENE ");
		StdDraw.rectangle(50, 600 , 20, 8*nbini);
		StdDraw.filledRectangle(50, 600 , 20, 4*oxygene);  // oxygene
		StdDraw.filledCircle(350, 850-profp1*26, 8);
		StdDraw.filledRectangle(290, 65, 30, 20);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(150, 110, "coffre.jpg", 28, 22);
		StdDraw.text(185, 110, "x "+ tp1);
		StdDraw.picture(810, 110, "coffre.jpg",28,22);
		StdDraw.text(845, 110, "x "+ tp2);
		
		StdDraw.filledCircle(700, 850 - profp2*26, 8);
		StdDraw.filledRectangle(960, 65, 30, 20);
		StdDraw.text(50, 600, ""+oxygene);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(960, 65, ""+scorefp2);          // position score p2
		StdDraw.text(290, 65, ""+scorefp1);   // position score p1
		StdDraw.text(850, 930, "TOUR DE : JOUEUR " + joueur);
		

	}
	

	public void afficherencours1(int n1, int n2, int n3, int direction) {
		if (direction == 1) {
		if (profp1<n1+1) {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		}
		if (profp1>=n1+1 && profp1<n2+n1+1) {
			StdDraw.setPenColor(StdDraw.YELLOW);
		}
		if (profp1 >=n2 + n1+1) {
			StdDraw.setPenColor(StdDraw.RED);
		}
		if (profp1>0) {
		StdDraw.filledRectangle(350, 850-(profp1-direction)*26, 40, 12);
		}
		if (profp1 == 0) {
			StdDraw.picture(350, 881, "fondp1lvl-1.jpg",136,39);
		}
		}
		if (direction == -1) {
			if (profp1<n1-1) {
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			}
			if (profp1>=n1-1 && profp1<n2+n1-1) {
				StdDraw.setPenColor(StdDraw.YELLOW);
			}
			if (profp1 >=n2 + n1-1) {
				StdDraw.setPenColor(StdDraw.RED);
			}
			StdDraw.filledRectangle(350, 850-(profp1-direction)*26, 40, 12);
			}
		
		if (direction == 10) {
			if (profp1<n1) {
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				}
				if (profp1>=n1 && profp1<n2+n1) {
					StdDraw.setPenColor(StdDraw.YELLOW);
				}
				if (profp1 >=n2 + n1) {
					StdDraw.setPenColor(StdDraw.RED);
				}
			StdDraw.filledRectangle(560, 850-profp1*26, 40, 12);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*profp1, "x "+ tresors[profp1][0]);
			StdDraw.picture(185, 110, "fond bleu fonce.jpg",49,44);
			StdDraw.text(185, 110, "x "+ tp1);
		}
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(350, 850-profp1*26, 8);
		StdDraw.picture(50, 613, "fond oxygene.jpg",100,522);
		StdDraw.rectangle(50, 600 , 20, 8*nbini);
		StdDraw.filledRectangle(50, 600, 20, 4*oxygene);
		StdDraw.filledRectangle(290, 65, 30, 20);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(50, 600, ""+oxygene);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(290, 65, ""+scorefp1);
		
		if (profp1 != -1 && oxygene == 0) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledRectangle(560, 850-(nbniv-1)*26, 40, 12);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*(nbniv-1), "x "+ tresors[nbniv-1][0]);
			
			
			
		}
		StdDraw.picture(185, 110, "fond bleu fonce.jpg",49,44);
		StdDraw.text(185, 110, "x "+ tp1);
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void afficherencours2(int n1, int n2, int n3, int direction) {
		if (direction == 1) {
		if (profp2<n1+1) {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		}
		if (profp2>=n1+1 && profp2<n2+n1+1) {
			StdDraw.setPenColor(StdDraw.YELLOW);
		}
		if (profp2 >=n2 + n1+1) {
			StdDraw.setPenColor(StdDraw.RED);
		}
		if (profp2 >0) {
		StdDraw.filledRectangle(700, 850-(profp2-direction)*26, 40, 12);
		}
		if (profp2 == 0) {
			StdDraw.picture(700, 881, "fondp2lvl-1.jpg",155,39);
		}
		}
		if (direction == -1) {
			if (profp2<n1-1) {
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			}
			if (profp2>=n1-1 && profp2<n2+n1-1) {
				StdDraw.setPenColor(StdDraw.YELLOW);
			}
			if (profp2 >=n2 + n1-1) {
				StdDraw.setPenColor(StdDraw.RED);
			}
			StdDraw.filledRectangle(700, 850-(profp2-direction)*26, 40, 12);
			}
		
		if (direction == 10) {
			if (profp2<n1) {
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				}
				if (profp2>=n1 && profp2<n2+n1) {
					StdDraw.setPenColor(StdDraw.YELLOW);
				}
				if (profp2 >=n2 + n1) {
					StdDraw.setPenColor(StdDraw.RED);
				}
			StdDraw.filledRectangle(560, 850-profp2*26, 40, 12);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*profp2, "x "+ tresors[profp2][0]);
			StdDraw.picture(845, 110, "fond bleu fonce.jpg",49,44);
			StdDraw.text(845, 110, "x "+ tp2);
		}
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.picture(50, 613, "fond oxygene.jpg",100,522);
		StdDraw.rectangle(50, 600 , 20, 8*nbini);
		StdDraw.filledRectangle(50, 600, 20, 4*oxygene);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(960, 65, 30, 20);
		StdDraw.filledCircle(700, 850-profp2*26, 8);
		
		
		//StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(50, 600, ""+oxygene);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(960, 65, ""+scorefp2);
		
		if (profp2 != -1 && oxygene == 0) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledRectangle(560, 850-(nbniv-1)*26, 40, 12);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(535, 850-26*(nbniv-1), "x "+ tresors[nbniv-1][0]);
			
			
			
		}
		StdDraw.picture(845, 110, "fond bleu fonce.jpg",49,44);
		StdDraw.text(845, 110, "x "+ tp2);
			
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void menu() {
		a = 0;
		game = false;
		StdDraw.setCanvasSize(10*1242/(10+resolution), 10*675/(10+resolution));
		StdDraw.setXscale(0, 1242);       // on cr�� la fenetre
		StdDraw.setYscale(0, 675);
		StdDraw.picture(1242/2, 675/2, "Jaw'Snorkunking.jpg", 1242, 675);
		//StdDraw.picture(1242/2, 675/2, "Jaw'Snorkunking.jpg");
		Bouton bouton = new Bouton(bmenu,3,smenu,modeMenu, resolution);
	}
	
	public void bouton(double centrex, double centrey, double halfx, double halfy, String txt) {
		a = 0;
		while (a<10000000) {
			double xsouris = StdDraw.mouseX();
			double ysouris = StdDraw.mouseY();
			if (xsouris<=(centrex+halfx) && xsouris>=(centrex-halfx)) {
				if (ysouris<=(centrey+halfy) && ysouris>=(centrey-halfy)) {
					if (colorbo == false) {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledRectangle(centrex, centrey, halfx, halfy);
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.text(centrex, centrey, txt);
					colorbo = true;
					game = true;
					
				}
					a++;
				}
			}
			else {
				if (colorbo == true) {
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(centrex, centrey, halfx, halfy);
				StdDraw.setPenColor(StdDraw.BOOK_BLUE);
				StdDraw.text(centrex, centrey, txt);
				colorbo = false;
				a = 0;
			}
			}
		}
		}
	
	
}
