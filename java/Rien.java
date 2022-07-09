package Game.entite.terrain;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Rien extends Displayable{

	/* Contructeur */
	public Rien(){
		super();													// initialisation du construteur de la classe Displayable
		//this.c = new Color(128,128,0);							// intialisation de la couleur
		this.s = "Rien";											// inialisation du nom de la variable à Rien
		this.etat = 0;
		try{
		this.img = ImageIO.read(new File("Image/ground.png"));			// initailisation de l'attribut image de la classe displayable
		} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
			e.printStackTrace();
		}

	}
	/*  Méthodes  */
	public void paint(Graphics g){											// Paint nous permet d'afficher les instances Rien sur notre Jpanel

			g.drawImage(img, this.x,this.y,50,50,null);				// On dessine un rectangle rempli 
	}

}
