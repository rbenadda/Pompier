package Game.entite;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public abstract class Displayable{

	/* variable */
	protected Color c;							// Une couleur 
	protected String s;							// Le nom de l'instance
	protected int x;							// Postion de l'abscisse
	protected int y;							// Position de l'ordornnée
	protected double time; 
	protected ArrayList<Displayable> l;				// Liste de displayable
	protected Boolean b = false;
	protected int affichage; 
	protected int etat = 0;
	protected double sante;
	protected Image img;

	

	/* Mutateurs */
	public Color getColor(){ 				// retourne la couleur de l'instance
		return this.c;
	}
	public String getNom(){ 					// retourne le nom de l'instance
		return this.s;
	}

	public ArrayList<Displayable> getList(){		// retourne la liste de l'instance
		return this.l;		 
	}
	public int getX(){						// retourne la valeur de l'ordonnée de l'instance 
		return this.x;
	}
	public int getY(){						// retourne la valeur de l'abscisse de l'instance 
		return this.y;
	}
	public boolean getTire(){
    	return this.b;
    }
    public double getTime(){
    	return this.time;
    }
    public int getAff(){
    	return this.affichage;
    }

    public int getEtat(){
    	return this.etat;
    }
    public double getSante(){
    	return this.sante;
    }


	/* Assesseurs */

	public void setXY(int i,int e){			// cette fonction permet de donner deux entier en paramètres et de set ses positions
		this.x=i;
		this.y=e;
	}
	public void setX(int i){					// cette fonction permet de donner un entier en paramètre et de changer l'axe des ordonnées
		this.x=i;
	}
	public void setY(int i){					// cette fonction permet de donner un entier en paramètre et de changer l'axe des abscisses
		this.y=i;
	}
	public void setAff(int i){
    	this.affichage = i;
    }
    public void setEtat(int i){
    	this.etat = i;
    }
 	public void setPasFeu(Displayable [][] n){
		for(int i=0;i<n.length;i++){ 														/* on parcours la matrice */
			for(int j=0;j<n[i].length;j++){
				if (n[i][j].getX() == this.getX() && n[i][j].getY() == this.getY()){
					n[i][j].setEtat(0);
					for (int k=0;k<(n[i][j].getList()).size();k++){
						(n[i][j].getList()).get(k).setEtat(0);
					}
				}
			}
		}
	}


	/* Méthodes */
	public void paint(Graphics g){						// déclaration d'une méthode paint qui permet l'affichage de l'instance
	}											// notre méthode est vide ce qui permet au classes qui hérité de Displayable de pouvoir définir leur propre paint()

	public void action(KeyEvent e,ArrayList<Displayable> l) {}
	public void brule(){
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