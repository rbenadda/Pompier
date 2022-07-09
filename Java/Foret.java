package Game.entite.terrain;
import Game.entite.Displayable;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Foret extends Displayable{

	/** Une image */
	private Image img;													// un attribut de type Image
	/** Initialisation des attributs de foret respectivement la liste d'arbre,sa couleur, son nom, son etat,le nombre d'arbre dans sa liste
	*/
	public Foret(){
		super();														// initialisation du construteur de la classe Displayable
		this.l = new ArrayList<Displayable>();							// déclaration de la liste de l'instance
		this.c = new Color(0,150,0);									// initialisation de la couleur
		this.s = "Foret";												// initialisation du nom de la variable à Terrebrulee
		this.etat = 0;													// initialisation de l'état de l'instance à ne brule pas
		Random r = new Random();										// déclaration d'un random
       	int n = r.nextInt(5)+1;											// Déclaration d'un int qui peut prendre comme valeur (1,2,3,4,5)
		while (n != 0){													// Tanque la valuer de l'entier n'est pas égal à 0
			l.add(new Arbre());											// on ajout une nouvelle instance arbre dans notre liste
			n--;														// on décrémente la valeur de n de 1
		}
	}
	/*  Méthodes */

	/** Méthode paint qui permet d'afficher une image/un dessin suivant la vie de l'instance
	* @param g un objet Graphics
	*/
	public void paint(Graphics g){	 

		int ctp = 0; 													// on déclare un compteur à 0
		for(int i = 0;i<this.l.size();i++){								// pour chaque arbre contenu dans la liste
			if ((this.l.get(i)).getSante() < 0){						// on vérifie si leur vie est inférieur à 0
				ctp++;													// si c'est le cas on incrémente notre compteur de 1
			}
		}			
		if ( ctp == this.l.size())										// si le compteur est égal au nombre d'arbre contenu dans la liste
			{
			try{														// alors on déclare une image (dans un try and catch pour récuperer les erreurs)
				this.img = ImageIO.read(new File("Image/lave.png"));	// on déclare l'image contenu dans l'instance
				g.drawImage(img, x,y,50,50,null);						// on dessine l'image grâce au coordonnée de l'instance et de l'image
				this.etat = 3;											// on set son état à 3 (ne peut plus bruler)
			} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
			e.printStackTrace();
			}
			}
		if ( this.getEtat() == 2)										// si sion état est égal à 2 ( est en train de bruler ) 
			{
			for (int k=0;k<l.size();k++)								// pour chaque arbre de la liste
			{
				this.l.get(k).brule();									// on appelle la Méthode brule
			}
			g.setColor(new Color(139,0,0));								// On set la couleur de l'objet G à la couleur de l'instance 
			g.fillRect(x,y,50,50);										// On dessine un rectangle plein avec comme paramètre respectif (abscisse,ordonnée,largueur,hauteur)					
		}
		if (this.getEtat() == 0)										// Si son état est égal à 0 ( ne brule pas )
		{
			g.setColor(this.c);											// On set la couleur de l'objet G à la couleur de l'instance 
			g.fillRect(x,y,50,50);										// On dessine un rectangle plein avec comme paramètre respectif (abscisse,ordonnée,largueur,hauteur)
		}								
	}
}
