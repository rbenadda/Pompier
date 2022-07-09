package Game.entite.terrain;
import Game.entite.Displayable;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Foret extends Displayable{

	private Random r;
	private int n;
	private Image img;
	/* Contructeur */
	public Foret(){
		super();												// initialisation du construteur de la classe Displayable
		this.l = new ArrayList<Displayable>();						// déclaration de la liste de l'instance
		this.c = new Color(0,150,0);							// intialisation de la couleur
		this.s = "Foret";										// inialisation du nom de la variable à Terrebrulee
		this.etat = 0;
		r = new Random();								// déclaration d'un random
       	n = r.nextInt(5)+1;								// Déclaration d'un int qui peut prendre comme valeur (1,2,3,4,5)
		while (n != 0){											// Tanque la valuer de l'entier n'est pas égal à 0
			l.add(new Arbre());									// on ajout une nouvelle instance arbre dans notre liste
			n--;												// on décrémente la valeur de n de 1
		}
	}
	/*  Méthodes */


	public void paint(Graphics g){	
		int ctp = 0;

		for(int i = 0;i<this.l.size();i++){
			if ((this.l.get(i)).getSante() < 0){
				ctp++;
			}
		}
		if ( ctp == this.l.size())
			{
			try{
				this.img = ImageIO.read(new File("Image/lave.png"));
				g.drawImage(img, x,y,50,50,null);
				this.etat = 3;
			} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
			e.printStackTrace();
			}
			}
		if ( this.getEtat() == 2)
			{
			for (int k=0;k<l.size();k++)
			{
				this.l.get(k).brule();
			}
			g.setColor(new Color(139,0,0));										// On set la couleur de l'objet G à la couleur de l'instance 
			g.fillRect(x,y,50,50);
		}
		if (this.getEtat() == 0)
		{
			g.setColor(this.c);										// On set la couleur de l'objet G à la couleur de l'instance 
			g.fillRect(x,y,50,50);	
		}								// On dessine un recteangle rempli
	}
}
