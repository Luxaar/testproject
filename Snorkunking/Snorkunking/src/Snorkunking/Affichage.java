package Snorkunking;

import edu.princeton.cs.introcs.StdDraw;
import sun.audio.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.concurrent.*;
import java.io.*;
import javax.swing.*;

public class Affichage {
	Tresor tresor = new Tresor();
	
	int direction;
	
	public Affichage() {
		tresor.menu();
		deroulementGame();
			

		
	}
	
	public void deroulementGame() {
		commandes();
		commandes();
		commandes();
		gameOver();
		
	}
	
	

	public void plongeur1() {
		direction = 0;
		while (direction == 0) {
		if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && tresor.profp1>=0) {
			direction = -1;
			tresor.profp1 = tresor.profp1 - 1;
			tresor.oxygene = tresor.oxygene - tresor.tp1 - 1;
			if (tresor.profp1 == -1) {
				tresor.scorefp1 = tresor.scorefp1 + tresor.scorep1;
				tresor.tp1 = 0;
				tresor.scorep1 = 0;
			}		
				
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && tresor.profp1<tresor.nbniv-1) {
			direction = 1;
			tresor.profp1++;
			tresor.oxygene = tresor.oxygene - tresor.tp1 - 1;
		}
		if (tresor.profp1 >=0) {
		if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && tresor.tresors[tresor.profp1][0] >= 1) {
			direction = 10;
			tresor.settresors(tresor.profp1);
			tresor.oxygene = tresor.oxygene - 1;
		}

		}
		}
		if (tresor.oxygene < 0)
			tresor.oxygene = 0;
		
	}
	
	public void plongeur2() {
		if (tresor.modeJeu==1) {
			Ai ai = new Ai(tresor.profp2, tresor.tresors, tresor.oxygene, tresor.tp2, tresor.nbini);
			if (ai.direction == -1) {
				tresor.profp2 = tresor.profp2 - 1;
				tresor.oxygene = tresor.oxygene - tresor.tp2 - 1;
				if (tresor.profp2 == -1) {
					tresor.scorefp2 = tresor.scorefp2 + tresor.scorep2;
					tresor.tp2 = 0;
					tresor.scorep2 = 0;
				}		
			}
			else if (ai.direction == 1) {
				tresor.profp2++;
				tresor.oxygene = tresor.oxygene - tresor.tp2 - 1;
			}
			else if (ai.direction == 10) {
				tresor.settresors(tresor.profp2);
				tresor.oxygene = tresor.oxygene - 1;
			}
			else {
				plongeur2();
			}
			direction = ai.direction;
		}
		if (tresor.modeJeu == 2) {
		direction = 0;
		while (direction == 0) {
		if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && tresor.profp2>=0) {
			direction = -1;
			tresor.profp2 = tresor.profp2 - 1;
			tresor.oxygene = tresor.oxygene - tresor.tp2 - 1;
			if (tresor.profp2 == -1) {
				tresor.scorefp2 = tresor.scorefp2 + tresor.scorep2;
				tresor.tp2 = 0;
				tresor.scorep2 = 0;
			}		
				
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && tresor.profp2<tresor.nbniv-1) {
			direction = 1;
			tresor.profp2++;
			tresor.oxygene = tresor.oxygene - tresor.tp2 - 1;
		}
		if (tresor.profp2 >=0) {
		if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && tresor.tresors[tresor.profp2][0] == 1) {
			direction = 10;
			tresor.settresors(tresor.profp2);
			tresor.oxygene = tresor.oxygene - 1;
		}

		}
		}
		}
		if (tresor.oxygene < 0)
			tresor.oxygene = 0;
		
		
	}
	
	
	public void noyade() {
		if (tresor.oxygene <=0) {
			if(tresor.profp2 >-1) {
				tresor.tresors[tresor.nbniv-1][0] = tresor.tresors[tresor.nbniv-1][0] + tresor.tp2;
				tresor.tresors[tresor.nbniv-1][1] = tresor.tresors[tresor.nbniv-1][1] + tresor.scorep2;
				tresor.tp2 = 0;
				tresor.scorep2 = 0;

			}
			if(tresor.profp1 >-1) {
				tresor.tresors[tresor.nbniv-1][0] = tresor.tresors[tresor.nbniv-1][0] + tresor.tp1;
				tresor.tresors[tresor.nbniv-1][1] = tresor.tresors[tresor.nbniv-1][1] + tresor.scorep1;
				tresor.tp1 = 0;
				tresor.scorep1 = 0;

			}
		}
	}
	
	
	public void affjoueur() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(850, 930, 100, 15);
		if (tresor.joueur == 1) {
			StdDraw.setPenColor(StdDraw.BLUE);
		}
		if (tresor.joueur == 2) {
			StdDraw.setPenColor(StdDraw.BLACK);
		}
		StdDraw.text(850, 930, "TOUR DE : JOUEUR " + tresor.joueur);
	}

	public void commandes() {
		tresor.afficher(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3);
		while (tresor.oxygene > 0) {
			if(tresor.profp1>tresor.profp2) {
				tresor.joueur = 1;
				affjoueur();
				plongeur1();
				tresor.afficherencours1(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
				if(tresor.oxygene>0) {
					tresor.joueur = 2;
					affjoueur();
					plongeur2();
					tresor.afficherencours2(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
				}
			}
			else if (tresor.profp1<tresor.profp2) {
				tresor.joueur = 2;
				affjoueur();
				plongeur2();
				tresor.afficherencours2(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
				if(tresor.oxygene>0) {
					tresor.joueur = 1;
					affjoueur();
					plongeur1();
					tresor.afficherencours1(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
				}
			}
			else {
				tresor.joueur = 1 + (int)(Math.random()*((2-1)+1));
				if (tresor.joueur == 1) {
					affjoueur();
					plongeur1();
					tresor.afficherencours1(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
					if(tresor.oxygene>0) {
						tresor.joueur = 2;
						affjoueur();
						plongeur2();
						tresor.afficherencours2(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
					}
				}
				else {
					affjoueur();
					plongeur2();
					tresor.afficherencours2(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
					if(tresor.oxygene>0) {
						tresor.joueur = 1;
						affjoueur();
						plongeur1();
						tresor.afficherencours1(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
					}
				}
			}
		}
		noyade();
		tresor.afficherencours1(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
		tresor.afficherencours2(tresor.nivcave1, tresor.nivcave2, tresor.nivcave3, direction);
		tresor.manche2set();
	}
	
	public void gameOver() {
		Font currentFont = StdDraw.getFont();
		float size = 10*70/(10+tresor.resolution);
		currentFont = currentFont.deriveFont(size);
		StdDraw.setFont(currentFont);
		if (tresor.scorefp1 > tresor.scorefp2) {
			StdDraw.picture(1000/2, 965/2, "gameoverp1.jpg", 1000, 965);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(180, 370, ""+tresor.scorefp1);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(800, 370, ""+tresor.scorefp2);
		}
		else if (tresor.scorefp1 < tresor.scorefp2) {
			StdDraw.picture(1000/2, 965/2, "gameoverp2.jpg", 1000, 965);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(180, 370, ""+tresor.scorefp1);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(800, 370, ""+tresor.scorefp2);
			}
		else {
			StdDraw.picture(1000/2, 965/2, "gameoverdraw.jpg", 1000, 965);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(180, 370, ""+tresor.scorefp1);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(800, 370, ""+tresor.scorefp2);
		}
		Font font = StdDraw.getFont();
		float s = 10*30/(10+tresor.resolution);
		font = font.deriveFont(s);
		StdDraw.setFont(font);
		tresor.bouton(500, 50, 100, 35, "MENU");

		
	}
	
}

