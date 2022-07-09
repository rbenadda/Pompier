package Game.entite;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


/** Cette classe regroupe les attributs et les méthodes de tout ce qui sera afficher à l'écran */
public abstract class Displayable{

	/* variable */

	/** C'est la couleur de l'instance */
	protected Color c;							// Une couleur 
	/** C'est le nom de l'instance */
	protected String s;							// Le nom de l'instance
	/** C'est l'abscisse de l'instance */
	protected int x;							// Postion de l'abscisse
	/** C'est l'ordonnée de l'instance */
	protected int y;							// Position de l'ordornnée
	/** C'est le temps d'affichage de l'instance */
	protected double time; 						// Le temps d'affichage à l'écran
	/** C'est la liste de displayable que peut contenir l'instance */
	protected ArrayList<Displayable> l;			// Liste de displayable
	/** C'est la couleur de l'instance */
	protected Boolean b = false;				// Savoir si l'instance est en train de tirer
	/** C'est le sens d'affichage de l'instance */
	protected int affichage; 					// Savoir de quel sens est l'instance
	/** C'est le nom de l'espèce de l'instance */
	protected String espece;
	/** C'est l'état de de l'instance 
	*	L'état peut prendre différentes valeurs comme :
	*	<ul>
	*	<li> 0 : Ne brule pas </li>
	*	<li> 2 : Est en train de bruler </li>
	*	<li> 3 : Ne peut plus bruler </li>
	*	<li> 8 : Permet d'éteindre le feu</li>
	*	</ul>
	**/
	protected int etat = 0;						// Un entier permettant de savoir si l'objet est en train de bruler,s'il ne l'est pas où si il ne peut plus être brulé respectivement 0,2,3
	/** C'est la vie de l'instance */
	protected double sante;						// La vie d'une  instance
	/** C'est l'image de l'instance */
	protected Image img;						// l'image qui permettra de l'identifier
	/** Le nombre de tire de bombardier que le pompier aura le droit d'utiliser */
    protected int bombardier = 0;

	

	/* Mutateurs */

	/** retourne l'attribut C de l'instance  
	* @return La couleur de l'instance
	*/
	public Color getColor(){ 					// retourne la couleur de l'instance
		return this.c;
	}
	/** retourne l'attribut S de l'instance  
	* @return Le nom de l'instance
	*/
	public String getNom(){ 					// retourne le nom de l'instance
		return this.s;
	}
	public String getEspece(){ 					// retourne le nom de l'instance
		return this.espece;
	}
	/** retourne l'attribut L de l'instance  
	* @return La liste de l'instance
	*/
	public ArrayList<Displayable> getList(){	// retourne la liste de l'instance
		return this.l;		 
	}
	/** retourne l'attribut X de l'instance  
	* @return L'abscisse de l'instance
	*/
	public int getX(){							// retourne la valeur de l'abscisse de l'instance 
		return this.x;
	}
	/** retourne l'attribut Y de l'instance  
	* @return l'ordonnée de l'instance
	*/
	public int getY(){							// retourne la valeur de l'ordonnée de l'instance 
		return this.y;
	}
	/** retourne l'attribut b de l'instance  
	* @return Le boolean de l'instance
	*/
	public boolean getTire(){					// retourne la valeur du boolean tire de l'instance
    	return this.b;
    }
   	/** retourne l'attribut time de l'instance  
	* @return Le temps d'affichage de l'instance
	*/
    public double getTime(){					// retourne le temps d'affichage de l'instance
    	return this.time;
    }
   	/** retourne l'attribut affichage de l'instance  
	* @return Le sens d'affichage de l'instance
	*/
    public int getAff(){						// retourne le sens d'affichage de l'instance
    	return this.affichage;
    }
   	/** retourne l'attribut etat de l'instance  
	* @return L'etat de l'instance
	*/
    public int getEtat(){						// retourne l'etat de l'instance 
    	return this.etat;
    }
   	/** retourne l'attribut sante de l'instance  
	* @return Les points de vie de l'instance
	*/
    public double getSante(){					// retourne les points de vie l'instance
    	return this.sante;
    }
    /** assesseur du nombre de tirs restants */
    public int getBombardier(){
        return this.bombardier;
    }


	/* Assesseurs */


	/** défini les coordonnées de notre instance
	*	@param i L'abscisse de notre instance
	* 	@param e L'ordonnée de notre instance
	*/
	public void setXY(int i,int e){			// cette fonction permet de donner deux entier en paramètres et de set ses positions
		this.x=i;
		this.y=e;
	}
	/** défini la coordonnée X de notre instance 
	* @param i L'abscisse de notre instance
	*/
	public void setX(int i){					// cette fonction permet de donner un entier en paramètre et de changer l'axe des ordonnées
		this.x=i;
	}
	/** défini la coordonnée Y de notre instance 
	* @param i L'ordonnée de notre instance
	*/
	public void setY(int i){					// cette fonction permet de donner un entier en paramètre et de changer l'axe des abscisses
		this.y=i;
	}
	/** défini Le sens d'affichage de notre instance
	* @param i Le sens d'affichage de notre instance
	*/
	public void setAff(int i){					// cette fonction permet de donner un entier en paramètre et de changer le sens de son affichage 
    	this.affichage = i;
    }
    /** défini Le temps d'affichage de notre instance
    * @param i Le temps d'affichage
    */
    public void setTime(int i){					// cette fonction permet de donner un entier en paramètre et de changer la durée d'affichage de l'instance
    	this.time = i;
    }
    /** défini L'état de notre instance
    * @param i l'état de notre instance
    */
    public void setEtat(int i){					// cette fonction permet de donner un entier en paramètre et de modifier l'état de l'instance
    	this.etat = i;
    }
    /** Permet de parcourir une matrice et de vérifier si les coordonnées de l'instance sont égaux à celle d'une case de la matrice d'où de set son état à ne brule pas (0)
    * @param n une matrice de displayble
    */
 	public void setPasFeu(Displayable [][] n){
		for(int i=0;i<n.length;i++){ 														/* on parcours la matrice */
			for(int j=0;j<n[i].length;j++){													/* on parcours la matrice */
				if (n[i][j].getX() == this.getX() && n[i][j].getY() == this.getY()){		// si les coordonnées de l'instance sont égales à celle d'une case de la matrice alors
					n[i][j].setEtat(0);														// On set l'état de la case de la matrice à 0 (ne brule pas)
					for (int k=0;k<(n[i][j].getList()).size();k++){							// On parcours la liste contenu dans l'instance de la case de la matrice
						(n[i][j].getList()).get(k).setEtat(0);								// On set sont état à 0 (ne brule pas)
					}
				}
			}
		}
	}

	/** Méthode paint de l'instance, qui sera redédini dans les classes qui héritent
	* @param g un objet graphics
	*/
	public void paint(Graphics g){						// déclaration d'une méthode paint qui permet l'affichage de l'instance
	}											// notre méthode est vide ce qui permet au classes qui hérité de Displayable de pouvoir définir leur propre paint()
	/** Méthode qui permet de récupérer un Keyevent et une ArrayList et qui permet de réaliser quelque chose lorsqu'une touche est appuyer. Cette Méthode sera redéfini dans les classes héritantes
	* @param e Un keyEvent qui permet de récuperer une touche/un click souris appuyer
	* @param l Une liste de Displayable
	*/
	public void action(KeyEvent e,ArrayList<Displayable> l) {}
	/** Méthode qui permet de diminuer les points de vie de l'instance, comme pour les Méthodes précédentes, elle sera redéfini dans les classes qui héritent*/
	public void brule(){}
	/** Méthode qui permet de vérifier si une instance est en feu ou non 
	* 	@param l liste de displayable 
	*	@return On retourne false de base pour pouvoir redefinir cette fonction dans les classes qui héritent
	*/
	public boolean enFeu(ArrayList<Displayable> l){
		return false;
    }

}
