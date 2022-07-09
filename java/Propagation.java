package Game.fonction;
import Game.entite.Displayable;
import Game.entite.outils.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class Propagation{

	/* attributs */

	private static Random r;
	private static int t,i,j,k ;
	private static ArrayList<Displayable> voisin;
	private static boolean noFeu;

	/* Méthodes*/
	public static void propagationFeu(ArrayList<Displayable> v,ArrayList<Displayable> l,Displayable [][] n){

		if (v.size() > 0 ){
			r = new Random();
			t = r.nextInt(20);
			if(t == 0){
				t = r.nextInt(v.size());
				if ((v.get(t).getEtat() != 2)){
					l.add(new Feu(v.get(t).getX(),v.get(t).getY(),n));
				}
			}
		}
	}

	public static void ArretFeu(ArrayList<Displayable> l,Displayable [][] n){

		ArrayList<Integer> f = new ArrayList<Integer>();
		for(int k=0;k<l.size();k++){
			if((l.get(k).getNom()).equals("Feu")){
				for(int i=0;i<n.length;i++){
					for(int j=0;j<n[i].length;j++){
						if(l.get(k).getX() == n[i][j].getX() && l.get(k).getY() == n[i][j].getY() && n[i][j].getEtat() == 3){
							f.add(k);
						}
					}
				}
			}
		}
		for(int p=0;p<f.size();p++){
			int g = f.get(p);
			l.remove(g);
		}
	}
	
	public static ArrayList<Displayable> voisin(Displayable [][] n,int x,int y){

		voisin = new ArrayList<Displayable>();
		for(int i=0;i<n.length;i++){
			for(int j=0;j<n[i].length;j++){
				if(x == n[i][j].getX() && y == n[i][j].getY()){
					if ( j == 0 && i == 0){
						voisin = Propagation.getVoisin(n[i][j+1],voisin);
						voisin = Propagation.getVoisin(n[i+1][j],voisin);
					}
					if ( j == 0 && i == n.length-1){
						voisin = Propagation.getVoisin(n[i][j+1],voisin);
						voisin = Propagation.getVoisin(n[i-1][j],voisin);
					}
					if ( j == 0 && i > 0 && i < n.length-1){
						voisin = Propagation.getVoisin(n[i][j],voisin);
						voisin = Propagation.getVoisin(n[i][j],voisin);
						voisin = Propagation.getVoisin(n[i][j],voisin);
					}
					if ( j == n[i].length && i == 0){
						voisin = Propagation.getVoisin(n[i][j-1],voisin);
						voisin = Propagation.getVoisin(n[i+1][j],voisin);
					}
					if ( j == n[i].length-1 && i == n.length-1){
						voisin = Propagation.getVoisin(n[i][j-1],voisin);
						voisin = Propagation.getVoisin(n[i-1][j],voisin);
					}
					if (j == n[i].length-1 && i > 0 && i < n.length-1){
						voisin = Propagation.getVoisin(n[i][j-1],voisin);
						voisin = Propagation.getVoisin(n[i-1][j],voisin);
						voisin = Propagation.getVoisin(n[i+1][j],voisin);
					}
					if (i ==  n.length-1 && j > 0 && j < n[i].length-1){
						voisin = Propagation.getVoisin(n[i][j-1],voisin);
						voisin = Propagation.getVoisin(n[i][j+1],voisin);
						voisin = Propagation.getVoisin(n[i-1][j],voisin);
					}
					if ( i ==  0 && j > 0 && j < n[i].length-1){
						voisin = Propagation.getVoisin(n[i][j-1],voisin);
						voisin = Propagation.getVoisin(n[i][j+1],voisin);
						voisin = Propagation.getVoisin(n[i+1][j],voisin);
					}
					if (j > 0 && i == 0 && j < n[i].length-1){
						voisin = Propagation.getVoisin(n[i][j],voisin);
						voisin = Propagation.getVoisin(n[i][j],voisin);
						voisin = Propagation.getVoisin(n[i][j],voisin);
					}
					if ( i > 0 && i < n.length-1  && j > 0 && j < n[i].length-1){
						voisin = Propagation.getVoisin(n[i][j-1],voisin);
						voisin = Propagation.getVoisin(n[i][j+1],voisin);
						voisin = Propagation.getVoisin(n[i-1][j],voisin);
						voisin = Propagation.getVoisin(n[i+1][j],voisin);
					}
				}
			}
		}

		return voisin;
	}
		
	public static void Feu(ArrayList<Displayable> l,Displayable [][] m,int largueur,int hauteur){
		noFeu = false;
		for(int i=0;i<l.size();i++){
					if((l.get(i).getNom()).equals("Feu")){
						noFeu = true;
						Propagation.propagationFeu(Propagation.voisin(m,l.get(i).getX(),l.get(i).getY()),l,m);
					}
				}
		if ( noFeu == false ){
			l.add(new Feu(m));
		}
	}

	public static void Eau(ArrayList<Displayable> l,Displayable [][] m){

		for(int i=0;i<l.size();i++){
			if((l.get(i).getNom()).equals("Eau") && l.get(i).getTime() < 0){
				for(int j=0;j<l.size();j++){
					if((l.get(j).getNom()).equals("Feu") && l.get(i).getX() == l.get(j).getX() && l.get(i).getY() == l.get(j).getY()){
						l.get(j).setEtat(8);
					}
				}
			l.remove(i);
			}
		}
		for(int k=0;k<l.size();k++){
			if ( l.get(k).getEtat() == 8){
				l.get(k).setPasFeu(m);
				l.remove(k);
			}
		}
	}

	public static ArrayList<Displayable> getVoisin(Displayable v,ArrayList<Displayable> l){

		if ((v.getNom().equals("Foret")) && (v.getEtat() != 3)){l.add(v);}
		return l;
	}

	public static int updateScore(Displayable [][] m,int s){
		for(int i=0;i<m.length;i++){ 
			for(int j=0;j<m[i].length;j++){
				if(m[i][j].getEtat() == 2 ){
					s = s + (m[i][j].getList()).size();
				}
			}
		}
		return s;
	}
}