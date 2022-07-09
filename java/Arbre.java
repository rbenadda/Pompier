package Game.entite.terrain;
import Game.entite.Displayable;
import java.awt.*;
import java.util.*;

class Arbre extends Displayable{

	/* Contructeur */
	public Arbre(){
	
	this.sante = (Math.random()*7500 + 2500 ); 					// initialisation des points de vie de l'instance entre 2500 et 12500
	Random rand = new Random();											// déclaration d'un Random
    int r = rand.nextInt(56) + 30;										// Sélection aléatoire du rouge entre 30 et 86
    int g = rand.nextInt(100) + 100;									// Sélection aléatoire du vert entre 100 et 200
    int b = rand.nextInt(56) + 30 ;										// Sélection aléatoire du bleu entre 30 et 86
    this.c = new Color(r,g,b);											// initialisation de la couleur 
    this.s = "Arbre";													// initialisation du nom de l'instance
    this.etat = 0;
	}
	
	/* Méthodes */
	public void paint(Graphics g){										// Paint nous permet d'afficher les arbres sur notre Jpanel
		if (this.sante > 0){
			g.setColor(this.c);												// On récupère la couleur contenu dans l'instance
			g.fillOval(x,y,11,6);											// On dessine un Oval rempli avec les coordonnées de la case où il se situe 
			g.setColor(Color.BLACK);										// On set la couleur de l'objet G à noir
			g.drawOval(x,y,11,6);											// On dessine un Oval vide pour faire les contours du feuillage		
			Color rc = new Color(160,82,45);								// On déclare une nouvelle couleur 
			g.setColor(rc);													// On set la couleur de l'objet G à cette nouvelle couleur
			g.fillRect(x+5,y+6,3,10);										// On dessine un recteangle rempli pour faire un tronc
		}
	}
	public void brule(){												// Fonction qui permet de simuler un feu sur un arbre
		this.sante = this.sante - 50;									// enleve 50 au nombre de sante 
	}
}
