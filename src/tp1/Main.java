//TP Réalisé par Valentin MORIN et surtout par Dahavid DUPONT
// Etudiants en MASTER TNSI à l'UVHC

package tp1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
	private static final Logger LOGGER= Logger.getLogger(Main.class.getName());
	
		public static void main(String [] args) throws Exception
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
			
			int[]coordonnesCase0 = t.trouve0();
			int[]coordonnesPieceADeplacer = t.trouvePieceADeplacer();
			
			for(int i = 0; i<5 ;i++) {
				t.deplacer(etats, coordonnesCase0, coordonnesPieceADeplacer);
				t.toString();
			}
		
			//fonction pour interdire les etats répétés : si etat présent dans la liste "etats" faire la fonction déplacer avec une autre piece
			// comment faire? ça yéssépo
		}
}
