//TP Réalisé par Valentin MORIN et Dahavid DUPONT
// Etudiants en MASTER 1 TNSI à l'UVHC

package tp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Taquin {
	public int taille = 3;
	public int[][] taquin = new int[taille][taille];
	public int mauvaisePosition; 
	public int manhattan; 
	public int nbCoups;
	public Integer f; //nbCoups + manhattan
	public Taquin taquinParent;

	public void initTaquinFinal()
	{
		int i, j, v=0;
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				this.taquin[i][j] = v;
				v++;
			}
		}
		this.mauvaisePosition = 0;
		this.manhattan = 0;
		this.nbCoups = 0;
		this.f = 0;
		this.taquinParent = null;
	}

	public void initTaquin()
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
				x = (int) Math.floor((Math.random() * (v.size())));
				taquin[i][j] = v.get(x);
				v.remove(x);
			}
		}
		this.taquinParent = null;
	}

	void nbPieceMalPositionnees()
	{
		int i, j, x = 0;
		Taquin but = new Taquin();
		but.initTaquinFinal();

		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				if(this.taquin[i][j] != but.taquin[i][j])
					x++;
			}
		}
		this.mauvaisePosition = x;
	}

	int[] cherchePosition(int valeur)
	{
		int i,j;
		int[] pos = {0,0};
		for (i = 0; i < taille; i++)
		{
			for (j = 0; j < taille; j++)
			{
				if(this.taquin[i][j] == valeur)
				{
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}

	void distanceManhattan()
	{
		int i,j,m = 0;
		int[] pos = null;
		int x,y;

		Taquin but = new Taquin();
		but.initTaquinFinal();

		// Pour chaque pièce
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				// Si ce n'est pas la bonne pièce
				if(this.taquin[i][j] != but.taquin[i][j])
				{
					//On cherche la position dans l'état but
					pos = cherchePosition(but.taquin[i][j]);
					x = Math.abs(i - pos[0]);
					y = Math.abs(j - pos[1]);
					m += x;
					m += y;
				}
			}

		}

		this.manhattan = m;
	}

	void mouvementGauche()
	{
		int x, y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.taquin[x][y] == 0)
				{
					if(y == (taille-1))
					{
						return;
					}
					this.taquin[x][y] = this.taquin[x][y+1];
					this.taquin[x][y+1] = 0;
					return;
				}
			}
		}
	}

	void mouvementDroite()
	{
		int x, y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.taquin[x][y] == 0)
				{
					if(y == 0)
					{
						return;
					}
					this.taquin[x][y] = this.taquin[x][y-1];
					this.taquin[x][y-1] = 0;
					return;
				}
			}
		}
	}

	void mouvementBas()
	{
		int x,y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.taquin[x][y] == 0)
				{
					if(x == 0)
					{
						return;
					}
					this.taquin[x][y] = this.taquin[x-1][y];
					this.taquin[x-1][y] = 0;
					return;
				}
			}
		}
	}

	void mouvementHaut()
	{
		int x,y;
		for(x=0; x<taille; x++)
		{
			for(y=0; y<taille; y++)
			{
				if(this.taquin[x][y] == 0)
				{
					if(x == (taille-1))
					{
						return;
					}
					this.taquin[x][y] = this.taquin[x+1][y];
					this.taquin[x+1][y] = 0;
					return;
				}
			}
		}
	}

	void copie(Taquin autre)
	{
		int i, j;
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				autre.taquin[i][j] = this.taquin[i][j];
			}
		}
		autre.mauvaisePosition = this.mauvaisePosition;
		autre.manhattan = this.manhattan;
		autre.nbCoups = this.nbCoups;
		autre.f = this.f;
		autre.taquinParent = this.taquinParent;
	}

	//Retourne 1 si les deux taquins sont égaux
	int compare(Taquin autre)
	{
		int i, j;
		for(i=0; i<taille; i++)
		{
			for(j=0; j<taille; j++)
			{
				if(autre.taquin[i][j] != this.taquin[i][j])
					return 0;
			}
		}
		if(autre.mauvaisePosition != this.mauvaisePosition)
			return 0;
		if(autre.manhattan != this.manhattan)
			return 0;
		if(autre.nbCoups != this.nbCoups)
			return 0;
		if(autre.f != this.f)
			return 0;
		return 1;
	}

	public void afficheTaquin()
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
				String result = String.format("| %2d ", this.taquin[i][j]);
				System.out.print(result);
			}
			System.out.print("|\n+----+");
			for(k=0; k<(taille)-1; k++)
				System.out.print("----+");
			System.out.println("\n");
		}
		this.nbPieceMalPositionnees();
		System.out.println("Nombre de pièces mal positionnées : " + this.mauvaisePosition);
		this.distanceManhattan();
		System.out.print("Distance de Manhattan : " + this.manhattan + "\n");
		System.out.print("f : " + this.f + "\n");
	}

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
				courant.afficheTaquin();
				return;
			}

			courant.copie(haut);
			courant.copie(bas);
			courant.copie(gauche);
			courant.copie(droite);

			haut.mouvementHaut();
			haut.nbPieceMalPositionnees();
			haut.distanceManhattan();
			//si courant != haut -> mouvement effectué
			if(haut.compare(courant) == 0 && !fermes.contains(haut))
			{
				haut.nbCoups = haut.nbCoups + 1;
				haut.f = haut.nbCoups + haut.manhattan;
				haut.taquinParent = courant;
				fils.add(haut);
			}

			droite.mouvementDroite();
			droite.nbPieceMalPositionnees();
			droite.distanceManhattan();
			if(droite.compare(courant) == 0 && !fermes.contains(haut))
			{
				droite.nbCoups = droite.nbCoups + 1;
				droite.f = droite.nbCoups + droite.manhattan;
				droite.taquinParent = courant;
				fils.add(droite);
			}

			bas.mouvementBas();
			bas.nbPieceMalPositionnees();
			bas.distanceManhattan();
			if(bas.compare(courant) == 0 && !fermes.contains(haut))
			{
				bas.nbCoups = bas.nbCoups + 1;
				bas.f = bas.nbCoups + bas.manhattan;
				bas.taquinParent = courant;
				fils.add(bas);
			}

			gauche.mouvementGauche();
			gauche.nbPieceMalPositionnees();
			gauche.distanceManhattan();
			if(gauche.compare(courant) == 0 && !fermes.contains(haut))
			{
				gauche.nbCoups = gauche.nbCoups + 1;
				gauche.f = gauche.nbCoups + gauche.manhattan;
				gauche.taquinParent = courant;
				fils.add(gauche);
			}

			for (Taquin noeud : fils) 
			{
				//Est-ce la solution ?
				if(noeud.manhattan == 0)
				{
					fin = System.currentTimeMillis();
					System.out.println("Solution trouvée en " + (fin - debut) + "ms");
					courant.afficheTaquin();
					return;
				}

				ouverts.add(noeud);
			}
			fils.clear();
		}
	}

	public static void main (String args[])
	{
		Taquin t = new Taquin();
		t.initTaquin();
		t.nbPieceMalPositionnees();
		t.distanceManhattan();
		t.nbCoups = 0;
		t.f = t.manhattan;
		t.afficheTaquin();

		t.aEtoile();
	}
}