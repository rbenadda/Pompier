package Game.entite.outils;
import Game.entite.Displayable;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;


public class Feu extends Displayable{

	private String nom;
	private int i,j,k;
	private Random r;
	private int frame;

	public Feu(Displayable [][] n){
		
		this.l = new ArrayList<Displayable>() ; 
		this.s = "Feu";
		this.frame = 1;
		for(i=0;i<n.length;i++){ 														
			for(j=0;j<n[i].length;j++){
				nom = n[i][j].getNom();
				if(nom.equals("Foret")){ 	
					l.add(n[i][j]);	
				}
			}
		}
		r = new Random();												// Déclaration d'un Random
		k = r.nextInt(l.size());
		for(i=0;i<n.length;i++){ 														/* on parcours la matrice */
			for(j=0;j<n[i].length;j++){
				if (n[i][j].getX() == l.get(k).getX() && n[i][j].getY() == l.get(k).getY()){
					n[i][j].setEtat(2);
				}
			}	
		}
		declarefeu(l.get(k).getX(),l.get(k).getY());
	}

	public Feu(int x,int y,Displayable [][] n){

		this.s = "Feu";
		this.frame = 1;
		declarefeu(x,y);
		for(i=0;i<n.length;i++){ 														/* on parcours la matrice */
			for(j=0;j<n[i].length;j++){
				if (n[i][j].getX() == this.getX() && n[i][j].getY() == this.getY()){
					n[i][j].setEtat(2);
					for (k=0;k<(n[i][j].getList()).size();k++){
						((n[i][j].getList()).get(k)).setEtat(2);
					}

				}
			}
		}
	}

	private void declarefeu(int xy, int zy){

		this.x = xy;
		this.y = zy;
		try{
			this.img = ImageIO.read(new File("Image/f1.png"));
		} catch(IOException e){										// Catch prend en attribut un throwable qui per met de gérer les exceptions
			e.printStackTrace();
		}
	}


	private void setEtatI(){

		if ( this.frame < 4 ){
			this.frame = this.frame + 1;
		}
		else{
			this.frame = 1;
		}
	}

	public void paint(Graphics g){

		switch(this.frame){
			case 1 : 
				try{
					this.img = ImageIO.read(new File("Image/f1.png"));
				} catch(IOException e){e.printStackTrace();}
				g.drawImage(img, this.x,this.y,50,50,null);
				this.setEtatI();
			break;
			case  2 :
				try{
					this.img = ImageIO.read(new File("Image/f2.png"));
				} catch(IOException e){e.printStackTrace();}
				g.drawImage(img, this.x,this.y,50,50,null);
				this.setEtatI();
			break;
			case  3 :
				try{
					this.img = ImageIO.read(new File("Image/f3.png"));
				} catch(IOException e){e.printStackTrace();}
				g.drawImage(img, this.x,this.y,50,50,null);
				this.setEtatI();
			break;
			case  4 :
				try{
					this.img = ImageIO.read(new File("Image/f4.png"));
				} catch(IOException e){e.printStackTrace();}
				g.drawImage(img, this.x,this.y,50,50,null);
				this.setEtatI();
			break;
		}
	}
}