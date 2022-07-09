package Game.entite.terrain;
import Game.entite.Displayable;
import java.awt.*;
import java.util.*;


/** Cette classe définie un arbre */
class Arbre extends Displayable{

	/** Constructeur vide qui initalise donc comme paramètre une couleur random pour le feuillage, un nom à "Arbre" et son état à 0 */
	public Arbre(){
	
	this.sante = (Math.random()*7500 + 2500 ); 							// initialisation des points de vie de l'instance entre 2500 et 12500
	Random rand = new Random();											// déclaration d'un Random
    int r = rand.nextInt(56) + 30;										// Sélection aléatoire du rouge entre 30 et 86
    int g = rand.nextInt(100) + 100;									// Sélection aléatoire du vert entre 100 et 200
    int b = rand.nextInt(56) + 30 ;										// Sélection aléatoire du bleu entre 30 et 86
    this.c = new Color(r,g,b);											// initialisation de la couleur 
    this.s = "Arbre";													// initialisation du nom de l'instance
    this.etat = 0;														// intialisation de son état à 0 (ne brule pas)
	}
	
	/** Permet de dessiner un arbre avec un feuillage et un tronc en utilisant u paramètre graphics qui nous permet de faire un disque et un rectangle pour simuler un arbre
	* @param g un objet graphics 
	*/
	public void paint(Graphics g){											// Paint nous permet d'afficher les arbres sur notre Jpanel
		if (this.sante > 0){												// si la vie de l'arbre est inférieur à 0
			g.setColor(this.c);												// On récupère la couleur contenu dans l'instance
			g.fillOval(x,y,11,6);											// On dessine un Oval rempli avec les coordonnées de la case où il se situe 
			g.setColor(Color.BLACK);										// On set la couleur de l'objet G à noir
			g.drawOval(x,y,11,6);											// On dessine un Oval vide pour faire les contours du feuillage		
			Color rc = new Color(160,82,45);								// On déclare une nouvelle couleur 
			g.setColor(rc);													// On set la couleur de l'objet G à cette nouvelle couleur
			g.fillRect(x+5,y+6,3,10);										// On dessine un recteangle rempli pour faire un tronc
		}
	}
	/** Permet de simuler un feu sur un arbre à chaque tour de boucle, on enlevera 50 point de vie à l'arbre 
	*/
	public void brule(){												// Fonction qui permet de simuler un feu sur un arbre
		this.sante = this.sante - 50;									// enleve 50 au nombre de sante 
	}
}
