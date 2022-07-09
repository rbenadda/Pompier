package Game.entite.terrain;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


/**
*Cette classe permet l'initialisation d'un zone où le pompier pourra se déplacer librement sans que le feu se propage
*/
public class Rien extends Displayable{

	/** Constructeur vide qui initie les paramètres String à "Rien" et Etat à 0. */
	public Rien(){
		super();													// initialisation du construteur de la classe Displayable
		this.s = "Rien";											// inialisation du nom de la variable à Rien
		this.etat = 0;												// initialisation de son etat à 0 (0=ne brule pas,1=brule,3=ne peut plus bruler)
		try{
		this.img = ImageIO.read(new File("Image/ground.png"));			// initailisation de l'attribut image de la classe displayable
		} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
			e.printStackTrace();
		}

	}
	/** Méthode paint qui prend en paramètre un graphics g et qui affiche l'image stocker dans notre instance. Cette fonction ne retourne rien
	* @param g est un graphics
	*/
	public void paint(Graphics g){											// Paint nous permet d'afficher les instances Rien sur notre Jpanel

			g.drawImage(img, this.x,this.y,50,50,null);				// On dessine un rectangle rempli 
	}

}
