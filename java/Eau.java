package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Eau extends Displayable{


	/* Contructeur */
	public Eau(int i,int e,int aff){
		super(); 													// initialisation du construteur de la classe Displayable
		this.c = new Color(0,0,255);								// intialisation de la couleur
		this.s = "Eau";												// inialisation du nom de la variable à EAU
		this.time = 1;
		this.affichage = aff;
		switch(this.getAff()){
			case 2: this.setXY(i,e+50);break;
			case 4: this.setXY(i-50,e);break;
			case 6: this.setXY(i+50,e);break;
			case 8: this.setXY(i,e-50);break;
		}
	}

	/* Méthodes*/
	public void paint(Graphics g){
		try{			
			switch (this.getAff()){
			case 2 :
				img = ImageIO.read(new File("Image/Eaub.png"));
				g.drawImage(img,this.x+10,this.y,25,50,null);
				break;
			case 4 :
				img = ImageIO.read(new File("Image/Eaug.png"));
				g.drawImage(img,this.x,this.y,50,25,null);
				break;
			case 6 :
				img = ImageIO.read(new File("Image/Eaud.png"));
				g.drawImage(img,this.x,this.y,50,25,null);
				break;
			case 8 :
				img = ImageIO.read(new File("Image/Eauh.png"));
				g.drawImage(img,this.x,this.y,25,50,null);
				break;
		}
		} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
            e.printStackTrace();									// Affiche l'exception et l'état de la pile d'éxecution au moment de son appel
		}
		
		this.time= this.time - 1;
	}

}
