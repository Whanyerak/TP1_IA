//TP Réalisé par Valentin MORIN et Dahavid DUPONT
// Etudiants en MASTER TNSI à l'UVHC

package tp1;

import java.util.logging.Logger;

public class Main {
	private static final Logger LOGGER= Logger.getLogger(Main.class.getName());
	
		public static void main(String [] args)
		{
			final Taquin t=new Taquin();
			final Taquin taquinFinal = new Taquin();
			t.initialisation();
			taquinFinal.initialisationEtatFinal();
			t.toString();
			taquinFinal.toString();
			int nbMP = t.nbPiecesMalPlacee();
			LOGGER.info(nbMP + " pièces ne sont pas correctement placées");
			int dM = t.distanceManhattan();
			LOGGER.info("La somme des distances de Manhattan est " + dM);
			
			//résolution taquin
			//Interdire les états répétés
			//faire une fonction déplacer=> faire un tableau de possibilité
			//pour retenir les possibilités
			//faire un arbre de recherche pour trouver la solution
			//comparer a chaque boucle a la solution finale
		}
}
