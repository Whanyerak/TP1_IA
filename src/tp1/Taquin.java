package tp1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Taquin {
	private  static final Logger LOGGER = Logger.getLogger(Taquin.class.getName());
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
		int x, y;
		int x1, y1;
		for (int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				
				if(this.taquin[i][j]==0) {
					x= trouveX(0);
					y = trouveY(0);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==1) {
					x= trouveX(1);
					y = trouveY(1);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==2) {
					x= trouveX(2);
					y = trouveY(2);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==3) {
					x= trouveX(3);
					y = trouveY(3);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==4) {
					x= trouveX(4);
					y = trouveY(4);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				if(this.taquin[i][j]==5) {
					x= trouveX(5);
					y = trouveY(5);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==6) {
					x= trouveX(6);
					y = trouveY(6);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==7) {
					x= trouveX(7);
					y = trouveY(7);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
				if(this.taquin[i][j]==8) {
					x= trouveX(8);
					y= trouveY(8);
					x1=i;
					y1=j;
					calculX(x, x1, dM);
					calculY(y, y1, dM);
				}
			  }
			}
		}
		return dM;
	}
	
	public int calculX(int x, int x1, int dM) {
		if(x1>x) {
			while(x1 != x) {
				dM++;
				x1++;
			}
			return dM;
		}
		if(x1<x) {
			while(x1 != x) {
				dM++;
				x1--;
			}
			return dM;
		}
		if(x1 == x) {
			return dM;
		}
		return dM;
	}
	public int calculY(int y, int y1, int dM) {
		if(y1>y) {
			while(y1 != y) {
				dM++;
				y1++;
			}
		}
		if(y1<y) {
			while(y1 != y) {
				dM++;
				y1--;
			}
		}
		return dM;
	}
	
	public int trouveX(int valeur) {
		switch(valeur) {
			case 0 : return 0;
			case 1 : return 0;
			case 2 : return 0;
			case 3 : return 1;
			case 4 : return 1;
			case 5 : return 1;
			case 6 : return 2;
			case 7 : return 2;
			case 8 : return 2;
			default : {
				LOGGER.warning("Warning : Il y a eu un incident avec les valeurs, X= -1");
				return -1;
			}
		}
	}
	
	public int trouveY(int valeur) {
		switch(valeur) {
			case 0 : return 0;
			case 1 : return 1;
			case 2 : return 2;
			case 3 : return 0;
			case 4 : return 1;
			case 5 : return 2;
			case 6 : return 0;
			case 7 : return 1;
			case 8 : return 2;
			default : {
				LOGGER.warning("Warning : Il y a eu un incident avec les valeurs, Y= -1");
				return -1;
			}
		}
	}
}
