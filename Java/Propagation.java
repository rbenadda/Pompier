package Game.fonction;
import Game.entite.Displayable;
import Game.entite.outils.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import Game.entite.animaux.Animaux;

/** Une classe static qui permet de gérer les interactions entre les objets */
public class Propagation{

	/* attributs */
	/** Une Liste de voisin */
	private static ArrayList<Displayable> voisin; 																								// Initialisation d'une liste de voisin
	/** Un Boolean qui permet de savoir s'il y a toujours un feu dans la liste */
	private static boolean noFeu;																												// Initialisation d'un boolean noFeu

	/* Méthodes*/
	/** Méthode qui permet de propager le feu 
	*	@param v une liste de voisin 
	*	@param l une liste de Displayable 
	*	@param n une matrice de displayable
	*/
	public static void propagationFeu(ArrayList<Displayable> v,ArrayList<Displayable> l,Displayable [][] n){

		if (v.size() > 0 ){																														// Si la taille de la liste est suppérieur à 0
			Random r = new Random();																											// On déclare un random
			int t = r.nextInt(20);																												// On déclare un int qu ipeut prendre comme valeur 0 à 4
			if(t == 0){																															// Si la valeur est égale à 0
				t = r.nextInt(v.size());																										// alors on déclare un entier entre 0 et la taille de la liste moins un
				if ((v.get(t).getEtat() == 0)){																									// Si l'etat de la valeur que l'on à choisi de la liste est égale à 0
					l.add(new Feu(v.get(t).getX(),v.get(t).getY(),n));																			// On ajoute un feu avec comme coordonnées celle qu iosnt contenu dans l'instance à la "case" t de la liste
				}
			}
		}
	}
	/** Méthode qui permet de savoir si une instance Eau est sur une Instance feu
	* @param l Une liste de Displayable 
	* @param n Une matrice de Displayable 
	*/
	public static void ArretFeu(ArrayList<Displayable> l,Displayable [][] n){

		ArrayList<Displayable> f = new ArrayList<Displayable>();																				// Déclaration et initialisation d'une liste de displayable 
		for(int k=0;k<l.size();k++){																											// pour k allant de 0 à la taille de la liste
			if((l.get(k).getNom()).equals("Feu")){																								// Si une instance à son nom égal à k contenu dans l
				for(int i=0;i<n.length;i++){																									// Parcours de matrice
					for(int j=0;j<n[i].length;j++){																								// Parcours de matrice
						if(l.get(k).getX() == n[i][j].getX() && l.get(k).getY() == n[i][j].getY() && n[i][j].getEtat() == 3){					// Si les coordonnées de l'instance contenu dans la liste sont égales à celle contenu dans la case[i][j] de la matrice et que son état est égal de 3
							f.add(l.get(k));																									// alors on l'ajoute dans la liste f
						}
					}
				}
			}
		}

		l.removeAll(f);																															// Supprime tout les éléments de f contenu dans l 

	}
	
	/** Méthode qui récupère les cases voisines d'une instance passé en paramètre
	* @param n Une matrice de Displayable 
	* @param x L'abscisse d'une instance
	* @param y L'ordonnée d'une instance
	*/
	public static ArrayList<Displayable> voisin(Displayable [][] n,int x,int y){

		voisin = new ArrayList<Displayable>();																									// Déclaration d'une liste voisin de Displayable
		for(int i=0;i<n.length;i++){																											// parcours de matrice 
			for(int j=0;j<n[i].length;j++){																										// parcours de matrice
				if(x == n[i][j].getX() && y == n[i][j].getY()){																					// Si les coordonnées de la case[i][j] de la matrice sont égales à celle passé en paramètre alors
					if ( j == 0 && i == 0){																										// si i = 0 et j = 0 
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
					}
					if ( j == 0 && i == n.length-1){																							// si i = 0 et j = 15
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i-1][j],voisin);																		// On appelle la Méthode voisin sur la case haut
					}
					if ( j == 0 && i > 0 && i < n.length-1){																					// si i = et j =
						voisin = Propagation.getVoisin(n[i-1][j],voisin);																		// On appelle la Méthode voisin sur la case haut
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case droit																		// On appelle la Méthode voisin sur la case gauche
					}
					if ( j == n[i].length && i == 0){																							// si i = et j =
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
					}
					if ( j == n[i].length-1 && i == n.length-1){																				// si i = et j =
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i-1][j],voisin);																		// On appelle la Méthode voisin sur la case haut
					}
					if (j == n[i].length-1 && i > 0 && i < n.length-1){																			// si i = et j =
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i-1][j],voisin);																		// On appelle la Méthode voisin sur la case haut
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
					}
					if (i ==  n.length-1 && j > 0 && j < n[i].length-1){																		// si i = et j =
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case droit																		// On appelle la Méthode voisin sur la case droit																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i-1][j],voisin);																		// On appelle la Méthode voisin sur la case haut
					}
					if ( i ==  0 && j > 0 && j < n[i].length-1){																				// si i = et j =
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case droit																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
					}
					if (j > 0 && i == 0 && j < n[i].length-1){																					// si i = et j =
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case gauche
					}
					if ( i > 0 && i < n.length-1  && j > 0 && j < n[i].length-1){																// si i = et j =
						voisin = Propagation.getVoisin(n[i][j-1],voisin);																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i][j+1],voisin);																		// On appelle la Méthode voisin sur la case droit																		// On appelle la Méthode voisin sur la case gauche
						voisin = Propagation.getVoisin(n[i-1][j],voisin);																		// On appelle la Méthode voisin sur la case haut
						voisin = Propagation.getVoisin(n[i+1][j],voisin);																		// On appelle la Méthode voisin sur la case bas
					}
				}
			}
		}

		return voisin;																															// retourne la liste de voisin
	}
	/** Méthode qui vérifie s'il y a un feu dans la liste 
	*	@param l liste de displayable 
	*	@param m Matrice de displayable
	*	@param largueur Largeur de la matrice
	* 	@param hauteur Hauteur de la matrice
	*/
	public static void Feu(ArrayList<Displayable> l,Displayable [][] m,int largueur,int hauteur){
		noFeu = false;																															// Déclare un boolean un false
		for(int i=0;i<l.size();i++){																											// POur i allant de 0 à la longueur de la liste
					if((l.get(i).getNom()).equals("Feu")){																						// si le nom de l'élément de la liste est égal à feu
						noFeu = true;																											// alors in set noFeu à true
						Propagation.propagationFeu(Propagation.voisin(m,l.get(i).getX(),l.get(i).getY()),l,m);									// On appelle la Méthode propagationFeu et voisin
					}
				}
		if ( noFeu == false ){																													// Si le boolean noFeu est égal à false
			l.add(new Feu(m));																													// On ajoute une instance feu dans la liste l
		}
	}


	/** Méthode qui vérifie si un feu est sur une instance eau
	* @param l Liste de Displayable
	* @param m Matrice de Displayable
	*/
	public static void Eau(ArrayList<Displayable> l,Displayable [][] m){

		for(int i=0;i<l.size();i++){																											// Pour i allant de 0 à la taille de la liste 
			if((l.get(i).getNom()).equals("Eau") && l.get(i).getTime() < 0){																	// si le nom de l'élément contenu dans l est égal à Eau et si son temps est inférieur à 0
				for(int j=0;j<l.size();j++){																									// Pour j allant de 0 à la taille de la liste 
					if((l.get(j).getNom()).equals("Feu") && l.get(i).getX() == l.get(j).getX() && l.get(i).getY() == l.get(j).getY()){			// si le nom de l'élement est égal à Feu et que ces coordonnées sont égal à un élément contenu dans l
						l.get(j).setEtat(8);																									// alors on set son état à 8
					}
				}
			l.remove(i);																														// On enlève l'élement à la position i
			}
		}
		for(int k=0;k<l.size();k++){																											// POur k allant 0 à la taille de la liste
			if ( l.get(k).getEtat() == 8){																										// Si l'état de l'élement de la liste est égal à 8
				l.get(k).setPasFeu(m);																											// On appelle la fonction setpasfeu de l'élement à la position k
				l.remove(k);																													// On supprime l'élement à la position k
			}
		}
	}


	/** Méthode qui retourne les voisins d'une case
	*	@param v Un displayable
	*	@param l Une liste de Displayable
	*	@return Une liste de displayable 
	*/
	public static ArrayList<Displayable> getVoisin(Displayable v,ArrayList<Displayable> l){

		if ((v.getNom().equals("Foret")) && (v.getEtat() != 3)){l.add(v);}																		// Si le nom de l'instance est Foret et que son état est différent de 3	
		return l;																																// on l'ajoute dans la liste l
	}


	/** Méthode qui met à jour le score
	*	@param m Une matrice de displayable
	*	@param s Une entier
	*	@return Le score mis à jour
	*/
	public static int updateScore(Displayable [][] m,int s){
		for(int i=0;i<m.length;i++){ 																											// parcours de matrice
			for(int j=0;j<m[i].length;j++){																										// parcours de matrice
				if(m[i][j].getEtat() == 2 ){																									// si l'état de la case[i][j] de la matrice est égal à 2
					s = s + (m[i][j].getList()).size();																							// Alors on récupère la taille de la liste de l'élement et on l'ajoute au score
				}
			}
		}
		return s;																																// On retourne le score
	}


	/** Méthode qui permet de déclarer un animal suivant un % de chance
	* @param l Une liste de Displayable
	* @param m Une matrice de Displayable 
	*/
	public static void Animal(ArrayList<Displayable> l,Displayable [][] m){

		Random r = new Random();																												// déclaration d'un random
		int i =r.nextInt(100);																													// déclaration d'un entier entre 0 et 99
		if(i == 1){																																// si i = 0
			l.add(new Animaux(m));																												// alors on ajoute une instance animal à la liste passé en paramètre
		}
	}


	/** Méthode qui permet de savoir si une instance animal est sur un Pompier ou un Feu
	*	@param l Liste de Displayable
	*	@param s Score du joueur
	*/
	public static int SurAnimal(ArrayList<Displayable> l,int s){

		ArrayList<Displayable> f = new ArrayList<Displayable>() ; 																				// déclaration d'un liste de displayable
		for(int i=0;i<l.size();i++){																											// Pour i allant de 0 à la taille de la liste 
			if((l.get(i).getNom()).equals("Feu")){																								// si le nom de l'élément contenu dans l est égal à Feu 
				for(int j=0;j<l.size();j++){																									// Pour j allant de 0 à la taille de la liste 
					if((l.get(j).getNom()).equals("Animaux") && l.get(i).getX() == l.get(j).getX() && l.get(i).getY() == l.get(j).getY()){		// si le nom de l'élement est égal à Animal et que ces coordonnées sont égal à un élément contenu dans l
						f.add(l.get(j));																										// on ajoute l'élement animal à la liste f
						s = s + 1000;																											// On ajoute 1000 au score
					}
				}
			l.removeAll(f);																														// on enlève toutes les instances animals contenu dans les deux listes															
			}
		}
		for(int i=0;i<l.size();i++){																											// Pour i allant de 0 à la taille de la liste 
			if((l.get(i).getNom()).equals("Pompier")){																							// si le nom de l'élément contenu dans l est égal à Pompier
				for(int j=0;j<l.size();j++){																									// Pour j allant de 0 à la taille de la liste 
					if((l.get(j).getNom()).equals("Animaux") && l.get(i).getX() == l.get(j).getX() && l.get(i).getY() == l.get(j).getY()){		// si le nom de l'élement est égal à Animal et que ces coordonnées sont égal à un élément contenu dans l
						f.add(l.get(j));																										// On ajoute l'élement animal à la liste f
						if ((l.get(j).getEspece()).equals("Cochon")){	s = s - 500;	}
						if ((l.get(j).getEspece()).equals("Vache")){	s = s - 1000;	}
						if ((l.get(j).getEspece()).equals("Poulet")){	s = s - 1500;	}
					}
				}
			l.removeAll(f);																														// on enlève toutes les instances animals contenu dans les deux listes
			}
		}
		return s;																																// On retourne le score
	}
}
