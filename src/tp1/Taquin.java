//TP Réalisé par Valentin MORIN et surtout par Dahavid DUPONT
// Etudiants en MASTER TNSI à l'UVHC

package tp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Taquin {
	public int taille = 3;
	public int[][] tab = new int[taille][taille];
	public int mp; // Pièces mal positionnées
	public int manhattan; // Distance de Manhattan: somme des distances de chaque pièce à sa position but
	public int g; //nombre de coups
	public Integer f; // g+manhattan
	public int visite; //A-t-il était visité?
	public Taquin parent;

	public void initBut()
	{
		int i, j, v=0;
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				this.tab[i][j] = v;
				v++;
			}
		}
		this.mp = 0;
		this.manhattan = 0;
		this.g = 0;
		this.f = 0;
		this.parent = null;
	}
	
	public void init()
	{
		// v = tableau de valeurs
		// x = Tampon pour l'indice
		int i, j;
		ArrayList<Integer> v = new ArrayList<Integer>();
		int x; 

		//Initialisation
		for(i=0; i<(taille*taille); i++)
		{
			v.add(i); 
		}

		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				x = (int) Math.floor((Math.random() * (v.size()-1)));
				tab[i][j] = v.get(x);
				v.remove(x);
			}
		}
		this.parent = null;
	}
	
	void nbMalPositionnees()
	{
		int i, j, x = 0;
		Taquin but = new Taquin();
		but.initBut();

		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				if(this.tab[i][j] != but.tab[i][j])
					x++;
			}
		}
		this.mp = x;
	}
	
	int[] chercherPosition(int valeur)
	{
		int i,j;
		int[] pos = {0,0};
		for (i = 0; i < taille; i++)
		{
			for (j = 0; j < taille; j++)
			{
				if(this.tab[i][j] == valeur)
				{
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}
	
	void manhattan()
	{
		int i,j,m = 0;
		int[] pos = null;
		int x,y;

		Taquin but = new Taquin();
		but.initBut();

		// Pour chaque pièce
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				// Si ce n'est pas la bonne pièce
				if(this.tab[i][j] != but.tab[i][j])
				{
					//On cherche la position dans l'état but
					pos = chercherPosition(but.tab[i][j]);
					x = Math.abs(i - pos[0]);
					y = Math.abs(j - pos[1]);
					m += x;
					m += y;
				}
			}

		}

		this.manhattan = m;
	}
	
	void deplacementGauche()
	{
		int x, y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.tab[x][y] == 0)
				{
					if(y == (taille-1))
					{
						return;
					}
					this.tab[x][y] = this.tab[x][y+1];
					this.tab[x][y+1] = 0;
					return;
				}
			}
		}
	}

	void deplacementDroite()
	{
		int x, y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.tab[x][y] == 0)
				{
					if(y == 0)
					{
						return;
					}
					this.tab[x][y] = this.tab[x][y-1];
					this.tab[x][y-1] = 0;
					return;
				}
			}
		}
	}

	void deplacementBas()
	{
		int x,y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.tab[x][y] == 0)
				{
					if(x == 0)
					{
						return;
					}
					this.tab[x][y] = this.tab[x-1][y];
					this.tab[x-1][y] = 0;
					return;
				}
			}
		}
	}

	void deplacementHaut()
	{
		int x,y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.tab[x][y] == 0)
				{
					if(x == (taille-1))
					{
						return;
					}
					this.tab[x][y] = this.tab[x+1][y];
					this.tab[x+1][y] = 0;
					return;
				}
			}
		}
	}
	
	void copie(Taquin nouveau)
	{
		int i, j;
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				nouveau.tab[i][j] = this.tab[i][j];
			}
		}
		nouveau.mp = this.mp;
		nouveau.manhattan = this.manhattan;
		nouveau.g = this.g;
		nouveau.f = this.f;
		nouveau.parent = this.parent;
	}
	
	//Retourne 1 si les deux taquins sont égaux
	int compare(Taquin nouveau)
	{
		int i, j;
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				if(nouveau.tab[i][j] != this.tab[i][j])
					return 0;
			}
		}
		if(nouveau.mp != this.mp)
			return 0;
		if(nouveau.manhattan != this.manhattan)
			return 0;
		if(nouveau.g != this.g)
			return 0;
		if(nouveau.f != this.f)
			return 0;
		return 1;
	}
	
	public void afficherTaquin()
	{
		int i, j, k;
		System.out.print("+----+");
		for(i=0; i<((taille)-1); i++)
		{
			System.out.print("----+");
		}
		System.out.println("\n");
		for(i=0; i<(taille); i++)
		{
			for(j=0; j<taille; j++)
			{
				String result = String.format("| %2d ", this.tab[i][j]);
				System.out.print(result);
			}
			System.out.print("|\n+----+");
			for(k=0; k<(taille)-1; k++)
				System.out.print("----+");
			System.out.println("\n");
		}
		this.nbMalPositionnees();
		System.out.println("Nombre de pièces mal positionnées : " + this.mp);
		this.manhattan();
		System.out.print("Distance de Manhattan : " + this.manhattan + "\n");
		System.out.print("f : " + this.f + "\n");
	}
	
//	static Taquin getMin(ArrayList<Taquin> a)
//	{
//		int min = 999;
//		Taquin ta = new Taquin();
//		for(Taquin t : a)
//		{
//			if(t.f < min)
//			{
//				min = t.f;
//				t.copie(ta);
//			}
//		}
//		return ta;
//	}
	
	public void aEtoile()
	{
		ArrayList<Taquin> ouverts = new ArrayList<Taquin>();
		ArrayList<Taquin> fermes = new ArrayList<Taquin>();
		ArrayList<Taquin> fils = new ArrayList<Taquin>();
		final long debut = System.currentTimeMillis();
		final long fin;
		
		ouverts.add(this);
		
		while(ouverts.size() > 0)
		{
			Taquin courant = new Taquin();
			Taquin haut = new Taquin();
			Taquin bas = new Taquin();
			Taquin gauche = new Taquin();
			Taquin droite = new Taquin();
			
			//System.out.println("ouverts : " + ouverts.size());
			//System.out.println("fermes : " + fermes.size());
			
			//Tri la liste
			Collections.sort(ouverts, new Comparator<Taquin>()
			{
			    public int compare(Taquin o1, Taquin o2)
			    {
			         return o1.f.compareTo(o2.f);
			    }
			});
			
			//On sélectionne le taquin avec la plus petite heuristique
			courant = ouverts.get(0);
			//courant.afficherTaquin();
			
			fermes.add(courant);
			ouverts.remove(courant);
			
			//Est-ce la solution ?
			if(courant.manhattan == 0)
			{
				fin = System.currentTimeMillis();
				System.out.println("Solution trouvée en " + (fin - debut) + "ms");
				courant.afficherTaquin();
				return;
			}

			courant.copie(haut);
			courant.copie(bas);
			courant.copie(gauche);
			courant.copie(droite);
				
			haut.deplacementHaut();
			haut.nbMalPositionnees();
			haut.manhattan();
			//si courant != haut -> mouvement effectué
			if(haut.compare(courant) == 0 && !fermes.contains(haut))
			{
				haut.g = haut.g + 1;
				haut.f = haut.g + haut.manhattan;
				haut.parent = courant;
				fils.add(haut);
			}

			droite.deplacementDroite();
			droite.nbMalPositionnees();
			droite.manhattan();
			if(droite.compare(courant) == 0 && !fermes.contains(haut))
			{
				droite.g = droite.g + 1;
				droite.f = droite.g + droite.manhattan;
				droite.parent = courant;
				fils.add(droite);
			}

			bas.deplacementBas();
			bas.nbMalPositionnees();
			bas.manhattan();
			if(bas.compare(courant) == 0 && !fermes.contains(haut))
			{
				bas.g = bas.g + 1;
				bas.f = bas.g + bas.manhattan;
				bas.parent = courant;
				fils.add(bas);
			}

			gauche.deplacementGauche();
			gauche.nbMalPositionnees();
			gauche.manhattan();
			if(gauche.compare(courant) == 0 && !fermes.contains(haut))
			{
				gauche.g = gauche.g + 1;
				gauche.f = gauche.g + gauche.manhattan;
				gauche.parent = courant;
				fils.add(gauche);
			}
			
			courant.initBut();
			
			//System.out.println("Fils = " + fils.size());
			
			for (Taquin noeud : fils) 
			{
				//Est-ce la solution ?
//				if(noeud.manhattan == 0)
//				{
//					fin = System.currentTimeMillis();
//					System.out.println("Solution trouvée en " + (fin - debut) + "ms");
//					courant.afficherTaquin();
//					return;
//				}
//				else
//				{
//					System.out.println("action");
//					if (!ouverts.contains(noeud) && !fermes.contains(noeud)) 
//					{
//						ouverts.add(noeud);
//					}
//					if ((ouverts.contains(noeud)) && (ouverts.get(ouverts.indexOf(noeud)).g < noeud.g))
//					{
//						System.out.println("action2");
//						ouverts.remove(ouverts.indexOf(noeud));
//						ouverts.add(noeud);
//					}
//					if ((fermes.contains(noeud)) && (fermes.get(fermes.indexOf(noeud)).g < noeud.g))
//					{
//						System.out.println("action3");
//						fermes.remove(fermes.indexOf(noeud));
//						ouverts.add(noeud);
//					}
//				}
				ouverts.add(noeud);
			}
			
			fils.clear();
			
		}//while
	}
	
	public static void main (String args[])
	{
		Taquin t = new Taquin();
		t.init();
		t.nbMalPositionnees();
		t.manhattan();
		t.g = 0;
		t.f = t.manhattan;
		t.afficherTaquin();
		
		
		t.aEtoile();
	}
}