//TP Réalisé par Valentin MORIN et Dahavid DUPONT
// Etudiants en MASTER TNSI à l'UVHC

package tp1;

import java.util.Scanner;

public class Main {

		public static void main(String [] args)
		{
			final Taquin t=new Taquin();
			Scanner sc = new Scanner(System.in);
			int taille = sc.nextInt();
			t.initialisation(taille);
			t.toString(taille);
			int nbMP = t.nbPiecesMalPlacee(t, taille);
			System.out.println("\n"+ nbMP + " pièces sont mal placées");
			sc.close();
			
			//résolution taquin
			//Interdire les états répétés
			//faire une fonction déplacer=> faire un tableau de possibilité
			//pour retenir les possibilités
			//faire un arbre de recherche pour trouver la solution
			//comparer a chaque boucle a la solution finale
		}
}
