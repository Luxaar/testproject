package Snorkunking;

public class Ai {
	
	int direction;
	

	public Ai(int profondeur, int [][] tresors, int oxygene, int tp2, int ini) {
		direction = 0;
		int nbtresorhaut = 0;
		for (int i = 0; i<profondeur;i++) {
			nbtresorhaut = nbtresorhaut + tresors[i][0];
		}
		if (6*profondeur > oxygene && direction == 0) {
			if (tp2 > 2) {
			direction = -1;
			}
			else  if(tresors[profondeur][0] > 0 && tp2<3) {
				direction = 10;
			}
			else {
				direction = -1;
			}
		}
		if (profondeur < (2*ini)/10 && direction == 0) {
			if (tp2 == 0) {
				direction = 1;
			}
			if (tp2 > 0 && tresors[profondeur][0] == 0) {
				direction = -1;
			}
			if (tp2 > 0 && tresors[profondeur][0] > 0) {
				direction = 10;
			}
		}
		if (profondeur > ((2*ini)/10)-1 && direction == 0) {
			if (tresors[profondeur][0] > 0) {
				direction = 10;
			}
			else {
				direction = -1;
			}
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
