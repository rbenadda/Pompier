package Game.entite.animaux;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import Game.entite.Displayable;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;


/** Classe qui déclare un animal */
public class Animaux extends Displayable{

	/** Constructeur qui appelle  la méthode initAnimaux
	* @param n Une matrice de displayable
	*/ 
	public Animaux(Displayable [][] n){

		initAnimaux(n); 																// appelle de la fonction initAnimaux
	}


	/** Méthode qui permet de déclarer un animal
	* @param n Une matrice de Displayable 
	*/
	public void initAnimaux(Displayable [][] n){

		Random r = new Random();														// On déclare un random
		int t = r.nextInt(3);															// On déclare un entier entre 0 et 2
		switch(t){																		// On déclare un switch avec comme condition t
			case 0:																		// si t = 0
			this.espece = "Cochon";														// On initialie l'attribur espece  à cochon
			try{																		// Déclaration d'un try and Catch pour pouvoir gérer les erreurs sur les images (indispensable lorsqu'on veut dessiner une image) 
			img = ImageIO.read(new File("Image/Cochon.png"));							// On initialise une image 
			 } catch(IOException e){													// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();														// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
			break;																		// On sort du switch
			case 1:																		// si t = 1
			this.espece = "Vache";														// On initialie l'attribur espece  à vache
			try{																		// Déclaration d'un try and Catch pour pouvoir gérer les erreurs sur les images (indispensable lorsqu'on veut dessiner une image) 
			img = ImageIO.read(new File("Image/Vache.png"));							// On initialise une image 
			 } catch(IOException e){													// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();														// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
			break;																		// On sort du switch
			case 2:																		// si t = 2
			this.espece = "Poulet";														// On initialie l'attribur espece  à poulet
			try{																		// Déclaration d'un try and Catch pour pouvoir gérer les erreurs sur les images (indispensable lorsqu'on veut dessiner une image) 
           	img = ImageIO.read(new File("Image/Poulet.png"));							// On initialise une image 
           	 } catch(IOException e){													// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();														// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
			break;																		// On sort du switch
		}
		this.s = "Animaux";																// initialise le nom de l'instance à animaux													
		l = new ArrayList<Displayable>() ; 												// initialisation de la liste de Displayable
		for(int i=0;i<n.length;i++){ 													// parcours de matrice
			for(int j=0;j<n[i].length;j++){												// parcours de matrice 
				if((n[i][j].getNom()).equals("Foret") && n[i][j].getEtat() == 0){		// si le nom est égale à foret et que son état est égal à 0
						l.add(n[i][j]);													// on l'ajoute dans la liste l
				}
			}
		}

		r = new Random();														// déclaration d'un random
		int i = r.nextInt(l.size());													// initialisation d'un entier contre intervalle{0,taille de la liste}
		setXY((l.get(i)).getX(),(l.get(i)).getY());										// set les coordonnées de l'animal à celle de l'élement choisi de la liste
	}


	/** Méthode paint qui permet d'afficher une image
	* @param g un objet Graphics
	*/
	public void paint(Graphics g){
		g.drawImage(img, x,y,50,50,null);		
      
	}
}