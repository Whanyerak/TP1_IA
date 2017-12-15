//TP Réalisé par Valentin MORIN et surtout par Dahavid DUPONT
// Etudiants en MASTER TNSI à l'UVHC

package tp1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
	private static final Logger LOGGER= Logger.getLogger(Main.class.getName());
	
		public static void main(String [] args)
		{
			final Taquin t=new Taquin();
			final Taquin taquinFinal = new Taquin();
			//t.initialisation();
			
			taquinFinal.initialisationEtatFinal();
			t.toString();
			taquinFinal.toString();
			int nbMP = t.nbPiecesMalPlacee();
			LOGGER.info(nbMP + " pièces ne sont pas correctement placées");
			int dM = t.distanceManhattan();
			LOGGER.info("La somme des distances de Manhattan est " + dM);
			
			List<Integer> dist = new ArrayList<>();
			for(int i=0; i<3; i++) {
				for(int j = 0; j<3; j++) {
					if(t.taquin[i][j]!= 0) {
						int distance = t.distanceManhattanUnique(i, j);
						dist.add(distance);
					}
				}
			}
			
			List<int[][]> etats = new ArrayList<>();
			t.deplacer(etats);
			etats.add(t.getTaquin());
			
			//fonction pour trouver taquin[i][j]= 0
			//fontion pour récupérer les pieces autour du 0 /!\ ne pas dépasser les bornes du tableau sinon exception outOfBounts
			//récupérer les distances de manhattan des pieces autour du 0
			//fonction déplacer pour déplacer un piece dans le taquin
		}
}
