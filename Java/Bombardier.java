package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

/** Classe qui permet de supprimer tous les feux en un seule fois	*/
public class Bombardier extends Displayable{ 							

						 													
	/* Contructeur */
	/** Ce construteur permet d'initialiser une liste contenant tout les instances feux contenu dans une liste
	* @param k Liste de Displayable
	*/
	public Bombardier(ArrayList<Displayable> k){									// initialisation des variables dans notre constructeur Pompier 
		super();																	// initialise le construteur de la classe Displayable 
		this.l = new ArrayList<Displayable>() ; 									// initialisation de la liste
		this.time = 2;																// initialisation du temps d'affichage
		this.s = "Bombadier"; 														// initalisation du nom à Bombardier
		for(int i=0;i<k.size();i++){												// Pour i allant de 0 à la longeur de la liste k 
				if((k.get(i).getNom()).equals("Feu")){								// Si le nom de l'instance est égal à Feu
					l.add(k.get(i));												// alors on l'ajoute dans notre liste
					k.get(i).setEtat(8);											// On set l'etat de l'élement de k à la position i à 8
				}
			}
	}
	/* Méthodes */
	/** Méthode paint qui affiche une image selon un entier time 
	* @param g un objet Graphics
	*/
	public void paint(Graphics g){													
		if (this.time > 0){															// si le time est supperieur à 0									
        	try{																	// déclaration d'un try and catch permettant de gérer les exceptions
        		for(int i=0;i<l.size();i++){										// pour i allant de 0 à la longeur de la liste l
					img = ImageIO.read(new File("Image/bombardement.png"));			// on initialise une image
            		g.drawImage(img,l.get(i).getX(),l.get(i).getY(),50,50,null);	// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
            	
				}	
			l.remove(l);															// on supprime tout les éléments de l
			this.setTime((int)(getTime()-1));										// on diminue la variable Time de 1 
        } catch(IOException e){														// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();													// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
		}
	}

	
    
}