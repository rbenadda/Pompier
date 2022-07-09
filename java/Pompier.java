package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;



public class Pompier extends Displayable{ 							// on déclare une classe Pompier qui hérite de la classe Displayable 

	/* 	Variable   */ 
						 											// permet de connaître le positionnement du pompier (gauche,droite,haut,bas)												// permet de pouvoir afficher une image 		
	private int hauteur;
	private int largeur;
	/* Contructeur */

	public Pompier(int xi,int yi,int h,int l){												// initialisation des variables dans notre constructeur Pompier 
		super();													// respectivement :
		this.s = "Pompier"; 										// appelle du constructeur par défaut de la classe displayable
		this.x = xi;												// set le nom de l'instance à "Pompier"
		this.y = yi;												// set les positions (X,Y) de l'instance en pixels
		this.affichage = 6;											// set la position de l'instance à droite (2=bas,4=gauche,6=droite,8=haut)
		this.hauteur = h;
		this.largeur = l;
        this.sante = 100;
	}
	/* Méthodes */

	public void paint(Graphics g){
        try{														// Déclaration d'un try and Catch pour pouvoir gérer les erreurs sur les image (indispensable lorsqu'on veut dessiner une image) 
        		switch(this.getAff())									// On déclare un switch avec comme paramètre l'int Affichage de l'instance
        		{
        		case 4 :											// Si Affichage = 4 
            	img = ImageIO.read(new File("Image/prp.png"));			// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
       			break;
            	case 6 :											// Si Affichage = 6 
            	img = ImageIO.read(new File("Image/plp.png"));			// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
            	break;
            	case 8 :											// Si Affichage = 8 
            	img = ImageIO.read(new File("Image/pd.png"));				// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
            	break;
            	case 2 :											// Si Affichage = 2 
            	img = ImageIO.read(new File("Image/pf.png"));				// On initialise une image 
            	g.drawImage(img, x,y,50,50,null);					// On dessine l'image avec respectivement comme atribut (l'image,ordonnée,abscisse,hauteur,largeur,ImageObserver)
            	break;
            	}
            b = false;										// On set le boolean tire à false à la fin de l'affichage du tire
        } catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();									// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
	}

	public void action(KeyEvent e,ArrayList<Displayable> l) { 								// récupère la touche rentrée en paramètre 

        boolean bouge = true;

        switch(e.getKeyCode()) {									// on récupère le code de la touche passée en paramètre et on le compare grâce à un switch
            case KeyEvent.VK_UP : 									// si la touche est égal à flèche du haut 
            	if(this.getAff() == 8){ 							// on vérifie si l'état du personnage est à haut 
            		if ( this.getY() < 50){
               			this.setY(this.getY()); 
                    }							
               		else{
                        for(int i=0;i<l.size();i++){
                           if(l.get(i).getX() == this.getX() && l.get(i).getY() == this.getY()-50)
                            {
                                 bouge = false; 
                            }
                        }
                        if (bouge == false)
                        {
                            this.setY(this.getY());
                        }
                        else
                        {
                            this.setY(this.getY()-50);
                        }
                    }	
               	}
				else{												// Sinon
					this.setAff(8);									// On set sa position à Haut
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_DOWN :									// si la touche est égal à flèche du bas 
            	if(this.getAff() == 2){							// on vérifie si l'état du personnage est à bas 
            	
            		if (this.getY() > (this.getHauteur())-100){

            			this.setY(this.getY());
            		}	
            		else{
                        for(int i=0;i<l.size();i++){
                           if(l.get(i).getX() == this.getX() && l.get(i).getY() == this.getY()+50)
                            {
                                 bouge = false; 
                            }
                        }
                        if (bouge == false)
                        {
                            this.setY(this.getY());
                        }
                        else
                        {
                            this.setY(this.getY()+50);
                        }
                }
                }   
				else{												// Sinon
				    this.setAff(2);									// On set sa position à Bas
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_RIGHT :								// si la touche est égal à flèche du droite 
                if(this.getAff() == 6){							// on vérifie si l'état du personnage est à droite 
                	if(this.getX() > this.getLargeur() - 100){
                		this.setX(this.getX());					// si c'est le cas on change son abscisse
                	}
                	else{
                        for(int i=0;i<l.size();i++){
                           if(l.get(i).getX() == this.getX()+50 && l.get(i).getY() == this.getY())
                            {
                                 bouge = false; 
                            }
                        }
                        if (bouge == false)
                        {
                            this.setX(this.getX());
                        }
                        else
                        {
                            this.setX(this.getX()+50);
                        }
                    }
                }  
				else{												// Sinon
				this.setAff(6);									// On set sa position à Droite
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_LEFT :									// si la touche est égal à flèche du gauche
            	if(this.getAff() == 4){							// on vérifie si l'état du personnage est à gauche
            		if (this.getX() < 50){ 
            			this.setX(this.getX());
            		}
                    else{
                        for(int i=0;i<l.size();i++){
                           if(l.get(i).getX() == this.getX()-50 && l.get(i).getY() == this.getY())
                            {
                                 bouge = false; 
                            }
                        }
                        if (bouge == false)
                        {
                            this.setX(this.getX());
                        }
                        else
                        {
                            this.setX(this.getX()-50);
                        }
                    }
                }  
				else{												// Sinon
				this.setAff(4);									// On set sa position à gauche
				}
                break;												// Permet de sortir du switch
            case KeyEvent.VK_SPACE :								// si la touche est égal à l'espace 
                this.b = true;
                break;												// Permet de sortir du switch
        }
    }
    


    private int getHauteur(){
    	return this.hauteur;
    }

    private int getLargeur(){
    	return this.largeur;
    }

    public boolean enFeu(ArrayList<Displayable> l){
        for(int j=0;j<l.size();j++){
            if((l.get(j).getNom()).equals("Feu") && this.getX() == l.get(j).getX() && this.getY() == l.get(j).getY()){
                this.sante = this.sante - 50;
            }
        }
        if (sante < 0){
            return true;
        }
        return false;
    }
}