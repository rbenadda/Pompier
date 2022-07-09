package Game.fonction;
import Game.entite.Displayable;
import Game.entite.terrain.*;
import java.util.*;


/** Une classe static qui permet de générer une zone aléatoire */
public class ZoneFactory{

	/** Utilisation d'un random pour pouvoir générer les zones de façon aléatoire */
	private static Random r; // déclaration d'un random utilisable uniquement dans cette classe
	/** Utilisation d'un int pour pouvoir générer un entier aléatoire */
	private static int n; // déclaration d'un int utilisable uniquement dans cette classe
	/** Cette méthode permet de renvoyer un displayble de façon aléatoire */
	public static Displayable createRandomZone(){ 							// Elle nous permet de choisir aléatoirement un terrain (Foret,TerreBrulee,Rien) et de le renvoyer
		r = new Random();													// Déclaration d'un Random
		n = r.nextInt(5);													// Déclaration d'un int qui peut prendre comme valeur (0,1,2)
		switch (n){															// Déclaration d'un switch avec comme condition n
			case 0:															// Si n=0,1
			return (new Foret());											// alors on retourne une instance foret
			case 1:															// Si n=2
			return (new Foret());											// alors on retourne une instance Rien
			case 2:															
			return (new Rien());

		}
		return (new Rien());												// Le cas par default on retourne une instance Rien
	}	
}