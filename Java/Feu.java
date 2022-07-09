package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

/** Une classe qui permet de créer un feu */
public class Feu extends Displayable{

	/** entier qui permet de savoir quelle image doit être afficher
	*/
	private int frame; 

	/** Ce constructeur permet d'initialiser un feu de manière aléatoire.
	*	@param n une matrice de Displayable
	*/
	public Feu(Displayable [][] n){
		
		this.l = new ArrayList<Displayable>() ;  												// initialisation de la liste d'arbre 
		this.s = "Feu";																			// initialisation de son nom
		this.frame = 1;																			// initialisation de son état à 1
		for(int i=0;i<n.length;i++){ 															// Parcours de matrice
			for(int j=0;j<n[i].length;j++){														// Parcours de matrice
				if((n[i][j].getNom()).equals("Foret")){ 										// si le nom de l'instance contenu dans la case[i][j] de la matrice est égal foret
					l.add(n[i][j]);																// alors on ajoute l'instance à la case[i][j] de la matrice dans la liste de forets
				}
			}
		}
		Random r = new Random();																// Déclaration d'un Random
		int k = r.nextInt(l.size());															// initialisation d'un entier avec comme intervalle possible {0,la taille de la liste de forets}
		for(int i=0;i<n.length;i++){ 														    // Parcours de matrice
			for(int j=0;j<n[i].length;j++){														// Parcours de matrice
				if (n[i][j].getX() == l.get(k).getX() && n[i][j].getY() == l.get(k).getY()){	// si les coordonnées de la case[i][j] de la matrice son égal à celle de l'instance à la valeur k (random) de la liste
					n[i][j].setEtat(2);															// alors on set l'état de la case[i][j] de la matrice à 2 ( en train de bruler)
				}
			}	
		}
		declarefeu(l.get(k).getX(),l.get(k).getY());											// On appelle la méthode déclare feu qui permet de set les coordonnées de l'instance avec les coordonnées de la case de la matrice où on vient de mettre son état à 2
	}


	/** Ce constructeur permet de déclarer un feu suivant des coordonnées passé en paramètre
	* 	@param x nouvelle abscisse de l'instance
	*	@param y nouvelle ordonnée de l'instance
	*	@param n Matrice de  Displayable
	*/
	public Feu(int x,int y,Displayable [][] n){

		this.s = "Feu";																		// Initialisation du nom de l'instance à Feu
		this.frame = 1;																		// Initialisation de sa frame à 1
		declarefeu(x,y);																	// on set les coordonnées de l'instance à celle passé en paramètre
		for(int i=0;i<n.length;i++){ 														/* on parcours la matrice */
			for(int j=0;j<n[i].length;j++){													/* on parcours la matrice */
				if (n[i][j].getX() == this.getX() && n[i][j].getY() == this.getY()){		// si les coordonnées de la case[i][j] de la matrice sont égales à celle de l'instance 
					n[i][j].setEtat(2);														// alors on set l'état de la case[i][j] de la matrice à 2 ( en train de bruler )
					for (int k=0;k<(n[i][j].getList()).size();k++){							// Pour chaque arbre contenu dans la liste de la case[i][j] de la matrice
						((n[i][j].getList()).get(k)).setEtat(2);						 	// On set son état à 2 ( en train de bruler )
					}

				}
			}
		}
	}
	/** Méthode qui permet de set les coordonnées de l'instance et d'initialiser l'image
	*	@param xy La nouvelle abscisse
	*	@param zy La nouvelle ordonnée
	*/
	private void declarefeu(int xy, int zy){												

		this.setXY(xy,zy);																		// On set les coordonnées dans l'instance grâce à la fonction setXY 
		try{																					// On déclare un try and catch pour gérer les erreurs
			this.img = ImageIO.read(new File("Image/f1.png"));									// On initialise l'image 
		} catch(IOException e){																	// Catch prend en attribut un throwable qui per met de gérer les exceptions
			e.printStackTrace();
		}
	}

	/** Méthode qui permet de changer la frame de l'instance 
	*/
	private void setEtatI(){

		if ( this.frame < 4 ){																	// Si la frame est inférieur à 4
			this.frame = this.frame + 1;														// alors on ajoute 1
		}
		else{																					// sinon
			this.frame = 1;																		// on le set à 1
		}
	}


	/** Méthode paint qui permet d'afficher une image suivant le chiffre de la frame
	* @param g un objet Graphics
	*/
	public void paint(Graphics g){

		switch(this.frame){																		// déclaration d'un switch
			case 1 : 																			// Si Frame = 1
				try{																			// On déclare un try and catch pour gérer les erreurs
					this.img = ImageIO.read(new File("Image/f1.png"));							// On initialise l'image 
				} catch(IOException e){e.printStackTrace();}									// Catch prend en attribut un throwable qui per met de gérer les exceptions
				g.drawImage(img, this.x,this.y,50,50,null);										// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				this.setEtatI();																// On appelle la fonction qui change la frame de l'instant
			break;																				// Stop
			case  2 : 																			// Si Frame = 2
				try{																			// On déclare un try and catch pour gérer les erreurs
					this.img = ImageIO.read(new File("Image/f2.png"));							// On initialise l'image 
				} catch(IOException e){e.printStackTrace();}									// Catch prend en attribut un throwable qui per met de gérer les exceptions
				g.drawImage(img, this.x,this.y,50,50,null);										// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				this.setEtatI();																// On appelle la fonction qui change la frame de l'instant
			break;																				// Stop
			case  3 : 																			// Si Frame = 3
				try{																			// On déclare un try and catch pour gérer les erreurs
					this.img = ImageIO.read(new File("Image/f3.png"));							// On initialise l'image 
				} catch(IOException e){e.printStackTrace();}									// Catch prend en attribut un throwable qui per met de gérer les exceptions
				g.drawImage(img, this.x,this.y,50,50,null);										// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				this.setEtatI();																// On appelle la fonction qui change la frame de l'instant
			break;																				// Stop
			case  4 : 																			// Si Frame = 4
				try{																			// On déclare un try and catch pour gérer les erreurs
					this.img = ImageIO.read(new File("Image/f4.png"));							// On initialise l'image 
				} catch(IOException e){e.printStackTrace();}									// Catch prend en attribut un throwable qui per met de gérer les exceptions
				g.drawImage(img, this.x,this.y,50,50,null);										// dessiner l'image avec comme paramètre respectifs (l'image,x,y,dimension,l'oject observer)
				this.setEtatI();																// On appelle la fonction qui change la frame de l'instant
			break;																				// Stop
		}
	}
}