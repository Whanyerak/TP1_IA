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
	public Integer f;		//Correspond au nombre de coup joués + la distance de Manhattan = heuristique
//	public Taquin taquinParent;

	//Procédure d'initialisation de l'état final du taquin recherché.
	public void initTaquinFinal() {
		int i, j, v = 0;
		
		for(i=0; i<taille; i++)
			for(j=0; j<taille; j++) {
				this.taquin[i][j] = v;
				v++;
			}
		
		this.mauvaisePosition = 0;
		this.manhattan = 0;
		this.nbCoups = 0;
		this.f = 0;
		//this.taquinParent = null;
	}

	//Procédure d'initialisation d'un taquin aléatoire.
	public void initTaquin() {
		int i, j, x;
		ArrayList<Integer> v = new ArrayList<Integer>();

		//Création d'un tableau contenant les 9 valeurs du taquin.
		for(i=0; i<(taille*taille); i++)
			v.add(i); 

		//Initialisation du taquin de manière aléatoire en utilisant les valeurs du tableau précédent de manière unique.
		for(i=0; i<taille; i++)
			for(j=0; j<taille; j++) {
				x = (int) Math.floor((Math.random() * (v.size())));
				taquin[i][j] = v.get(x);
				v.remove(x);
			}
		
		//this.taquinParent = null;
	}

	//Procédure permettant de calculer le nombre de pièces mal placées sur le taquin.
	void nbPieceMalPositionnees() {
		int i, j, x = 0;
		Taquin but = new Taquin();
		but.initTaquinFinal();

		for(i=0; i<taille; i++)
			for(j=0; j<taille; j++)
				if(this.taquin[i][j] != but.taquin[i][j])
					x++;
		
		this.mauvaisePosition = x;
	}

	//Procédure permettant de chercher la position d'une pièce du taquin.
	int[] cherchePosition(int valeur) {
		int i,j;
		int[] pos = {0,0};
		
		for (i = 0; i < taille; i++)
			for (j = 0; j < taille; j++)
				if(this.taquin[i][j] == valeur) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
		
		return pos;
	}

	//Procédure permettant de calculer la distance de Manhattan du taquin.
	void distanceManhattan() {
		int i, j, m = 0;
		int[] pos = null;
		int x,y;

		Taquin but = new Taquin();
		but.initTaquinFinal();

		//Pour chaque pièce
		for(i=0; i<taille; i++)
			for(j=0; j<taille; j++)
				// Si ce n'est pas la bonne pièce
				if(this.taquin[i][j] != but.taquin[i][j]) {
					//On cherche la position dans l'état but
					pos = cherchePosition(but.taquin[i][j]);
					x = Math.abs(i - pos[0]);
					y = Math.abs(j - pos[1]);
					m += x;
					m += y;
				}

		this.manhattan = m;
	}

	//Procédure permettant de déplacer une pièce à gauche.
	void mouvementGauche() {
		int x, y;
		
		for(x=0; x<taille; x++)
			for(y=0; y<taille; y++)
				if(this.taquin[x][y] == 0) {
					if(y == (taille-1))
						return;
					
					this.taquin[x][y] = this.taquin[x][y+1];
					this.taquin[x][y+1] = 0;
					return;
				}
		
	}

	//Procédure permettant de déplacer une pièce à droite.
	void mouvementDroite() {
		int x, y;
		
		for(x=0; x<taille; x++)
			for(y=0; y<taille; y++)
				if(this.taquin[x][y] == 0) {
					if(y == 0)
						return;

					this.taquin[x][y] = this.taquin[x][y-1];
					this.taquin[x][y-1] = 0;
					return;
				}
		
	}

	//Procédure permettant de déplacer une pièce vers le bas.
	void mouvementBas() {
		int x,y;
		
		for(x=0; x<taille; x++)
			for(y=0; y<taille; y++)
				if(this.taquin[x][y] == 0) {
					if(x == 0)
						return;

					this.taquin[x][y] = this.taquin[x-1][y];
					this.taquin[x-1][y] = 0;
					return;
				}

	}

	//Procédure permettant de déplacer une pièce vers le haut.
	void mouvementHaut() {
		int x,y;
		
		for(x=0; x<taille; x++)
			for(y=0; y<taille; y++)
				if(this.taquin[x][y] == 0) {
					if(x == (taille-1))
						return;

					this.taquin[x][y] = this.taquin[x+1][y];
					this.taquin[x+1][y] = 0;
					return;
				}

	}

	//Procédure permettant de copier le taquin dans un autre.
	void copie(Taquin autre) {
		int i, j;
		
		for(i=0; i<taille; i++)
			for(j=0; j<taille; j++)
				autre.taquin[i][j] = this.taquin[i][j];

		autre.mauvaisePosition = this.mauvaisePosition;
		autre.manhattan		   = this.manhattan;
		autre.nbCoups 		   = this.nbCoups;
		autre.f 			   = this.f;
		//autre.taquinParent     = this.taquinParent;
		
	}

	//Procédure de comparaison du taquin avec un autre (retourne 1 si égaux)
	int compare(Taquin autre) {
		int i, j;
		
		for(i=0; i<taille; i++)
			for(j=0; j<taille; j++)
				if(autre.taquin[i][j] != this.taquin[i][j])
					return 0;

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

	//Procédure permettant d'afficher le taquin.
	public void afficheTaquin() {
		int i, j, k;
		
		System.out.print("+----+");
		for(i=0; i<((taille)-1); i++)
			System.out.print("----+");

		System.out.println("\n");
		for(i=0; i<(taille); i++) {
			for(j=0; j<taille; j++) {
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

	//Procédure exécutant l'algorithme A*.
	public void aEtoile() {
		
		//Ouvert contient les noeuds ouverts à parcourir.
		ArrayList<Taquin> ouverts = new ArrayList<Taquin>();
		//Fermes contient les noeuds déjà traités qui ne doivent pas être retestés.
		ArrayList<Taquin> fermes = new ArrayList<Taquin>();
		//Fils contient les états fils d'un état courant.
		ArrayList<Taquin> fils = new ArrayList<Taquin>();
		
		final long debut = System.currentTimeMillis();
		final long fin;

		ouverts.add(this);
		while(ouverts.size() > 0) {
			Taquin courant = new Taquin();
			Taquin haut = new Taquin();
			Taquin bas = new Taquin();
			Taquin gauche = new Taquin();
			Taquin droite = new Taquin();

			//Debug de la taille des listes ouverts et fermes.
//			System.out.println("ouverts : " + ouverts.size());
//			System.out.println("fermes : " + fermes.size());

			//On tri la liste des noeuds ouverts par ordre d'heuristique croissant.
			Collections.sort(ouverts, new Comparator<Taquin>() {
				public int compare(Taquin o1, Taquin o2) {
					return o1.f.compareTo(o2.f);
				}
			});

			//On sélectionne le taquin avec la plus petite heuristique
			courant = ouverts.get(0);

			//On l'ajoute à la liste des noeuds traités.
			fermes.add(courant);
			
			//On le supprime de la liste des noeuds à traiter.
			ouverts.remove(courant);
			
			//On teste si le taquin courant est la solution.
			if(courant.manhattan == 0) {
				fin = System.currentTimeMillis();
				System.out.println("Solution trouvée en " + (fin - debut) + "ms");
				courant.afficheTaquin();
				return;
			}

			//On crée ci-dessous les états fils du Taquin.
			courant.copie(haut);
			courant.copie(bas);
			courant.copie(gauche);
			courant.copie(droite);

			haut.mouvementHaut();
			haut.nbPieceMalPositionnees();
			haut.distanceManhattan();
			//On vérifie que le mouvement à bien été effectué et on complète les infos du taquin fils s'il n'a pas déjà été traité.
			if(haut.compare(courant) == 0 && !fermes.contains(haut)) {
				haut.nbCoups 	  = haut.nbCoups + 1;
				haut.f 			  = haut.nbCoups + haut.manhattan;
				//haut.taquinParent = courant;
				fils.add(haut);
			}

			droite.mouvementDroite();
			droite.nbPieceMalPositionnees();
			droite.distanceManhattan();
			//On vérifie que le mouvement à bien été effectué et on complète les infos du taquin fils s'il n'a pas déjà été traité.
			if(droite.compare(courant) == 0 && !fermes.contains(droite)) {
				droite.nbCoups = droite.nbCoups + 1;
				droite.f = droite.nbCoups + droite.manhattan;
				//droite.taquinParent = courant;
				fils.add(droite);
			}

			bas.mouvementBas();
			bas.nbPieceMalPositionnees();
			bas.distanceManhattan();
			//On vérifie que le mouvement à bien été effectué et on complète les infos du taquin fils s'il n'a pas déjà été traité.
			if(bas.compare(courant) == 0 && !fermes.contains(bas)) {
				bas.nbCoups = bas.nbCoups + 1;
				bas.f = bas.nbCoups + bas.manhattan;
				//bas.taquinParent = courant;
				fils.add(bas);
			}

			gauche.mouvementGauche();
			gauche.nbPieceMalPositionnees();
			gauche.distanceManhattan();
			//On vérifie que le mouvement à bien été effectué et on complète les infos du taquin fils s'il n'a pas déjà été traité.
			if(gauche.compare(courant) == 0 && !fermes.contains(gauche)) {
				gauche.nbCoups = gauche.nbCoups + 1;
				gauche.f = gauche.nbCoups + gauche.manhattan;
				//gauche.taquinParent = courant;
				fils.add(gauche);
			}

			for (Taquin noeud : fils) {
				//On teste si le noeud est le résultat.
				if(noeud.manhattan == 0) {
					
					fin = System.currentTimeMillis();
					System.out.println("Solution trouvée en " + (fin - debut) + "ms");
					noeud.afficheTaquin();
					return;
			
				/*Les conditions ci-dessous ne fonctionnent pas, en effet le programme entre toujours dans le premier if
				mais jamais dans les suivants. Le problème vient de la comparaison avec la liste "ouvert".*/
				} else {
					//	System.out.println("action");
					if (!ouverts.contains(noeud) && !fermes.contains(noeud)) {
						System.out.println("action");
						ouverts.add(noeud);
					} else if (ouverts.contains(noeud) && ouverts.get(ouverts.indexOf(noeud)).f < noeud.f) {
						System.out.println("action2");
						ouverts.remove(ouverts.indexOf(noeud));
						ouverts.add(noeud);
					} else if ((fermes.contains(noeud)) && fermes.get(fermes.indexOf(noeud)).f < noeud.f) {
						System.out.println("action3");
						fermes.remove(noeud);
						ouverts.add(noeud);
					} else System.out.println("bite");
				}

				//On ajoute le noeud à la liste des noeuds ouverts.
				//ouverts.add(noeud);
			}
			//On vide la liste des fils.
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