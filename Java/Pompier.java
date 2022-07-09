package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


/** Une classe qui permet de créer un pompier */
public class Pompier extends Displayable{ 							// on déclare une classe Pompier qui hérite de la classe Displayable 

	/* 	Variable   */ 
	/** La hauteur de la fenêtre dans lequel le pompier va se déplacer */		
	private int hauteur;
    /** La largeur de la fenêtre dans lequel le pompier va se déplacer */
	private int largeur;
	/* Contructeur */


    /** Construteur de la classe Pompier 
    * @param xi abscisse du pompier
    * @param yi ordonnée du pompier
    * @param h hauteur de la fenêtre
    * @param l largeur de la fenêtre
    */
	public Pompier(int xi,int yi,int h,int l){						// initialisation des variables dans notre constructeur Pompier 
		this.s = "Pompier"; 										// set le nom de l'instance à "Pompier"
		this.x = xi;												// set X de l'instance
		this.y = yi;												// set Y de l'instance
		this.affichage = 6;											// set la position de l'instance à droite (2=bas,4=gauche,6=droite,8=haut)
		this.hauteur = h;                                           // On stock la hauteur de la fenêtre de jeu dans le pompier
		this.largeur = l;                                           // On stock la largeur de la fenêtre de jeu dans le pompier
        this.sante = 1;                                             // On set la vie du pompier à 1
        this.bombardier = 3;
	}
	/* Méthodes */

    /** Méthode qui permet d'afficher le pomiper selon la variable affichage */
	public void paint(Graphics g){
        try{														// Déclaration d'un try and Catch pour pouvoir gérer les erreurs sur les image (indispensable lorsqu'on veut dessiner une image) 
        		switch(this.getAff())								// On déclare un switch avec comme paramètre l'int Affichage de l'instance
        		{
        		case 4 :											// Si Affichage = 4 
            	img = ImageIO.read(new File("Image/prp.png"));		// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
       			break;
            	case 6 :											// Si Affichage = 6 
            	img = ImageIO.read(new File("Image/plp.png"));		// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
            	break;
            	case 8 :											// Si Affichage = 8 
            	img = ImageIO.read(new File("Image/pd.png"));		// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
            	break;
            	case 2 :											// Si Affichage = 2 
            	img = ImageIO.read(new File("Image/pf.png"));		// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
            	break;
            	}
            b = false;										        // On set le boolean tire à false à la fin de l'affichage du tire
        } catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();									// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
	}

	public void action(KeyEvent e,ArrayList<Displayable> l) { 		// récupère la touche rentrée en paramètre 

        boolean bouge = true;                                       // on set le boolean bouge à true
        switch(e.getKeyCode()) {									// on récupère le code de la touche passée en paramètre et on le compare grâce à un switch
            case KeyEvent.VK_UP : 									// si la touche est égal à flèche du haut 
            	if(this.getAff() == 8){ 							// on vérifie si l'état du personnage est à haut 
            		if ( this.getY() < 50){                         // si Y est inférieur à 50
               			this.setY(this.getY());                     // alors on ne change pas Y
                    }							
               		else{                                           // Sinon
                        for(int i=0;i<l.size();i++){                // pour i allant de 0 à la liste passer en paramètre
                           if(l.get(i).getX() == this.getX() && l.get(i).getY() == this.getY()-50 && (l.get(i).getNom()).equals("Feu") )    // si l'élement dans la liste est égal à Feu et que son X est égal à celui de l'instance Pompier et son Y est égal à celui de l'instance -50
                            {
                                 bouge = false;                     // On set le boolean bouge à false 
                            }
                        }
                        if (bouge == false)                         // si le boolean bouge est à false
                        {
                            this.setY(this.getY());                 // alors on ne change pas ces coordonnées
                        }
                        else                                        // Sinon
                        {
                            this.setY(this.getY()-50);              // on diminue Y d'une case 
                        }
                    }	
               	}
				else{												// Sinon
					this.setAff(8);									// On set sa position à Haut
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_DOWN :									// si la touche est égal à flèche du bas 
            	if(this.getAff() == 2){							    // on vérifie si l'état du personnage est à bas 
            		if (this.getY() > (this.getHauteur())-100){     // si Y est suppérrieur à la hauteur moins deux cases alors
            			this.setY(this.getY());                     // On ne change pas ses coordonnées
            		}	
            		else{
                        for(int i=0;i<l.size();i++){                // Pour i allant 0 à la longeur de la liste passé en paramètre
                           if(l.get(i).getX() == this.getX() && l.get(i).getY() == this.getY()+50 && (l.get(i).getNom()).equals("Feu") )   // si l'élement dans la liste est égal à Feu et que son X est égal à celui de l'instance Pompier et son Y est égal à celui de l'instance +50
                            {
                                 bouge = false;                     // On set le boolean bouge à false  
                            }
                        }
                        if (bouge == false)                         // si le boolean bouge est à false
                        {
                            this.setY(this.getY());                 // On ne change pas ses coordonnées
                        }
                        else                                        // Sinon
                        {
                            this.setY(this.getY()+50);              // On augmente y d'une case
                        }
                }
                }   
				else{												// Sinon
				    this.setAff(2);									// On set sa position à Bas
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_RIGHT :								// si la touche est égal à flèche du droite 
                if(this.getAff() == 6){							    // on vérifie si l'état du personnage est à droite 
                	if(this.getX() > this.getLargeur() - 100){
                		this.setX(this.getX());			    		// si c'est le cas on change son abscisse
                	}
                	else{                                           // Sinon
                        for(int i=0;i<l.size();i++){                // Pour i allant 0 à la longeur de la liste passé en paramètre                
                           if(l.get(i).getX() == this.getX()+50 && l.get(i).getY() == this.getY() && (l.get(i).getNom()).equals("Feu") )    // si l'élement dans la liste est égal à Feu et que sont X + 50 est égal à celui de l'instance Pompier et son Y est égal à celui de l'instance 
                            {
                                 bouge = false;                     // On set le boolean bouge à false  
                            }
                        }
                        if (bouge == false)                         // si le boolean bouge est à false
                        {
                            this.setX(this.getX());                 // On ne change pas ses coordonnées
                        }
                        else                                        // Sinon
                        {
                            this.setX(this.getX()+50);              // On augmente X d'une case
                        }
                    }
                }  
				else{												// Sinon
				this.setAff(6);									    // On set sa position à Droite
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_LEFT :									// si la touche est égal à flèche du gauche
            	if(this.getAff() == 4){							    // on vérifie si l'état du personnage est à gauche
            		if (this.getX() < 50){ 
            			this.setX(this.getX());                     // On ne change pas ses coordonnées
            		}
                    else{
                        for(int i=0;i<l.size();i++){                // Pour i allant 0 à la longeur de la liste passé en paramètre
                           if(l.get(i).getX() == this.getX()-50 && l.get(i).getY() == this.getY() && (l.get(i).getNom()).equals("Feu") )   // si l'élement dans la liste est égal à Feu et que sont X -50 est égal à celui de l'instance Pompier et son Y est égal à celui de l'instance 
                            {
                                 bouge = false;                     // On set le boolean bouge à false  
                            }
                        }
                        if (bouge == false)                         // si le boolean bouge est à false
                        {
                            this.setX(this.getX());                 // On ne change pas son X
                        }
                        else                                        // Sinon
                        {
                            this.setX(this.getX()-50);              // On diminue X d'une case
                        }
                    }
                }  
				else{												// Sinon
				this.setAff(4);									    // On set sa position à gauche
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_SPACE :								// si la touche est égal à l'espace 
                this.b = true;                                      // alors on set le boolean qui permet de tirer à true
                break;												// Permet de sortir du switch
            case KeyEvent.VK_A:                                     // Si on appuie sur A
                if (bombardier > 0){                                // si le nombre de tire de bombardier est suppérieur à 0
                    l.add(new Bombardier(l));                       // On ajoute un bombardier dans la liste de Displayable
                    this.bombardier = this.bombardier -1;           // on diminue le nombre de tire de bombardier de 1
                }
                break;
        }
    }
    



    /** assesseur de la hauteur contenu dans l'instance */
    private int getHauteur(){
    	return this.hauteur;
    }
    /** assesseur de la largeur contenu dans l'instance */
    private int getLargeur(){
    	return this.largeur;
    }
    /** Méthode permettant de diminuer la vie du pompier */
    public void brule(){
        this.sante = this.sante -1; 
    }



    /* Méthode qui permet de savoir si le pompier est sur un feu et de savoir s'il est mort */
    public boolean enFeu(ArrayList<Displayable> l){
        for(int j=0;j<l.size();j++){                              // pour j allant de 0 à la longeur de la liste passer en paramètre
            if((l.get(j).getNom()).equals("Feu") && this.getX() == l.get(j).getX() && this.getY() == l.get(j).getY()){  // si le nom de l'élement de la liste est égal à Feu et que ses coordonnées sont égales à celle de l'instance
                this.brule();                                     // On appelle la fonction brule
            }
        }
        if (this.getSante() < 0){                                 // Si la sante de l'instance est inférieur à 0
            return true;                                          // On retourne true
        }
        return false;                                             // sinon on retourne false
    }
}