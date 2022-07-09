package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Eau extends Displayable{


	/** Initialisation des attributs de Eau respectivement son nom "Eau", son Time (la durée à l'écran), son affichage (savoir dans quelle direction lancer l'eau)
	* @param i la nouvelle coordonnée x de l'instance
	* @param e la nouvelle coordonnée y de l'instance
	* @param aff la direction où le pompier est orienté
	*/
	public Eau(int i,int e,int aff){
		super(); 													// initialisation du construteur de la classe Displayable
		this.s = "Eau";												// initialisation du nom de la variable à EAU
		this.time = 1;												// initialisation de son temps d'afficage à 1
		this.affichage = aff;										// intitialisation de aff qui permet de savoir dans quel sens lancer l'eau (gauche;droite,haut,bas)
		switch(this.getAff()){										// déclaration d'un switch qui prend en paramétre aff
			case 2: this.setXY(i,e+50);break;						// 2:bas alors on ajoute 50 à y
			case 4: this.setXY(i-50,e);break;						// 4:gauche alors on retire 50 à x
			case 6: this.setXY(i+50,e);break;						// 6:droite alors on ajoute 50 à x
			case 8: this.setXY(i,e-50);break;						// 8:haut alors on retire 50 à y
		}
	}

	/** Affiche une image suivant le sens,La position x et y de l'instance 
	* @param g est un graphics 
	*/
	public void paint(Graphics g){
		try{														// try and catch pour gérer les erreurs
			switch (this.getAff()){									// déclaration d'un switch qui prend affiche en paramètre
			case 2 :												// si 2 : 
				img = ImageIO.read(new File("Image/Eaub.png"));		// alors utiliser l'image bas
				g.drawImage(img,this.x,this.y,25,50,null);			// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				break;
			case 4 :												// si 4 : 
				img = ImageIO.read(new File("Image/Eaug.png"));		// alors utiliser l'image gauche
				g.drawImage(img,this.x,this.y,50,25,null);			// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				break;												
			case 6 :												// si 6 : 
				img = ImageIO.read(new File("Image/Eaud.png"));		// alors utiliser l'image droite
				g.drawImage(img,this.x,this.y,50,25,null);			// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				break;
			case 8 :												// si 8 : 
				img = ImageIO.read(new File("Image/Eauh.png"));		// alors utiliser l'image heut
				g.drawImage(img,this.x,this.y,25,50,null);			// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				break;
		}
		} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();									// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
		
		this.setTime((int)(this.getTime()-1));						// On définie à chaque fois que l'on repaint notre affichage à -1 (on caste en int pour eviter une perte d'information )
	}

}
