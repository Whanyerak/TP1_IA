package tp1;

import java.util.ArrayList;
import java.util.List;

public class Taquin {
	public int taquin[][] = new int[3][3];
	
	
	public Taquin() {
	}
	
	public void initialisation(int taille) {
		int i,j,k, l;
		List<Integer> list = new ArrayList<>();
		for(i=0; i<taille*taille; i++) {
			list.add(i);
		}
		for (i=0;i<taille;i++) {
			for(j=0;j<taille;j++) {
				k=(int) (Math.random()*list.size());
				l=list.get(k);
				taquin[i][j]=l;
				list.remove(k);
			}
		}
	}
	
	public void initialisationEtatFinal(int taille) {
		int i,j,k;
		k=0;
		for (i=0;i<taille;i++) {
			for(j=0;j<taille;j++) {
				taquin[i][j]=k;
				k++;
			}
		}
	}
	
	public void toString(int taille) {
		int i,j;
		for (i=0;i<taille;i++) {
			System.out.println("");
			for(j=0;j<taille;j++) {
				System.out.print("|");
				System.out.print(taquin[i][j]);
			}
			System.out.print("|");
		}
		System.out.println("");
	}
	
	public int nbPiecesMalPlacee(Taquin taquin, int taille) {
		int nbPM=0, i , j , k;
		for (i=0;i<taille;i++) {
			for(j=0;j<taille;j++) {
				switch(i) {
					case 0 : {
						k = 0;
						break;
					}
					case 1 : {
						k = 3;
						break; 
					}
					case 2 : {
						k = 6;
						break;
					}
					default : k=0;
				}
				if(this.taquin[i][j] != j+k)
					nbPM++;
			}
		}
		return nbPM;
	}
	
	public int distanceManhattan(Taquin taquin, int taille) {
		int dM = 0;
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
			}
		}
		return dM;
	}
}
