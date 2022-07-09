package Game.plateau;
import Game.entite.Displayable;
import Game.entite.outils.*;
import Game.fonction.*;
import Game.Game2;



import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

public class GameFrame extends JPanel implements KeyListener{
	/* Varaibles */
    private static final int LARGEUR = 1200; 							// Paramètre de ma fenêtre
    private static final int HAUTEUR = 800; 							// Paramètre de ma fenêtre
    private Displayable [][] m;											// Matrice de Displayable qui nous permettra de stocker le terrain
    private ArrayList<Displayable> l; 										
    private static boolean inGame; 										// savoir si on est en jeu ou non
    private int i,j,k,nbrArbre;
    private int nbrvie;
    private JLabel jL;
    private int score = 0;
    private int timer = 120000;
    /* Contructeur */
	public GameFrame(){ 

		initGameFrame();												//constructeur de la classe GameFrame	
	}										    						// on appelle une méthode qui permet d'afficher la zone de jeu, le(s) pompier(s), le feu et autres Displayables 

	/* assesseur */


    /* 	Méthodes   */
    
    private void initGameFrame() {

    // 		
    	int yi=0,xi=0;

        setBackground(Color.GREEN); 									// on set la couleur du fond à noir (même si pas obligatoire)
   		setPreferredSize(new Dimension(LARGEUR, HAUTEUR+50)); 				// on set la taille de notre jPanel au variable stocker dans cette classe et on oblige notre jFrame à avoir la même taille
   		this.inGame = true; 											// on set le boolean qui nous sert à savoir si on est en jeu à true
   		this.nbrvie = 3;
		m = new Displayable[16][24]; 									// on déclare une matrice de Displayable de taille 24*16 ce qui correspond à 24*50 = 1200 et 16*50 = 800
		l = new ArrayList<Displayable>();
		for(int i=0;i<m.length;i++){ 														/* on parcourt la matrice */
			for(int j=0;j<m[i].length;j++){
				m[i][j]= ZoneFactory.createRandomZone();
				m[i][j].setXY(xi,yi);
				xi = xi+50; 							
			}
			yi = yi+50;
			xi = 0; 
		}
		l.add(new Pompier(100,100,this.HAUTEUR,this.LARGEUR));	// Ajout d'un Pompier dans la liste		
		l.add(new Feu(m));
					
		jL  = new JLabel("");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
    	this.add(jL); 		
    }

	public void run() {

		while(this.inGame == true){
			try{Thread.sleep(100);}catch(InterruptedException e){}
				Propagation.Eau(this.l,this.m);
				Propagation.Feu(this.l,this.m,this.LARGEUR,this.HAUTEUR);
				Propagation.ArretFeu(this.l,this.m);
				score = Propagation.updateScore(m,score);
				timer = timer - 100;
				//System.out.println(timer);
			this.repaint();
		}
		 
    }

    public void paintComponent(Graphics g) { 							// la fonction paintComponent appelle la méthode Component.update() qui efface et redessine le fond dans le jpanel par défaut
    															
    	super.paintComponent(g);
    	dopaint(g);														// on appelle la fonction dopaint()
    }

    public void dopaint(Graphics g){ 									// affichage du terrain
    	  										// respectivement xi = ordonnée ; yi = abscisse ; nbrArbre = nombre d'arbres par forêts													// variable qui permet de récuperer la valeur contenu à [x][y] de la matrice;
    	if(inGame == true && timer > 0){ 											//tantque je suis en jeu
    		for(int i=0;i<m.length;i++){ 								// parcours de matrice
				for(int j=0;j<m[i].length;j++){ 						// 
					m[i][j].paint(g); 										// je récupère la valeur contenu à [x][y] de la matrice et je la met dans z
					if((m[i][j].getNom()).equals("Foret")){ 							// s'il c'est une forêt 
						nbrArbre = (m[i][j].getList()).size(); 							// je rérupère la taille de la liste 
						switch(nbrArbre){ 								// affichage des arbres 
						case 1:
							(m[i][j].getList()).get(0).setXY(m[i][j].getX() + 18,m[i][j].getY() + 18);// même fonctionnement pour chaque *case* . On a donc notre *nbrArbre* qui va nous
							(m[i][j].getList()).get(0).paint(g);							// donner quel case doit-on réaliser. pour le *case* 1, on récupère le premier élement
							break;										// de la list (m[i][j].getList()) et on set ses coordonnées grâce à la méthode setXY puis on appelle la méthode paint(g); 
						case 2:
							(m[i][j].getList()).get(0).setXY(m[i][j].getX() + 5,m[i][j].getY() + 30);
							(m[i][j].getList()).get(0).paint(g);
							(m[i][j].getList()).get(1).setXY(m[i][j].getX() + 30,m[i][j].getY() + 5);
							(m[i][j].getList()).get(1).paint(g);
							break;
						case 3:
							(m[i][j].getList()).get(0).setXY(m[i][j].getX() + 18,m[i][j].getY() + 18);
							(m[i][j].getList()).get(0).paint(g);
							(m[i][j].getList()).get(1).setXY(m[i][j].getX() + 5,m[i][j].getY() + 30);
							(m[i][j].getList()).get(1).paint(g);
							(m[i][j].getList()).get(2).setXY(m[i][j].getX() + 30,m[i][j].getY() + 5);
							(m[i][j].getList()).get(2).paint(g);
							break;						
						case 4:
							(m[i][j].getList()).get(0).setXY(m[i][j].getX() + 5,m[i][j].getY() + 30);
							(m[i][j].getList()).get(0).paint(g);
							(m[i][j].getList()).get(1).setXY(m[i][j].getX() + 30,m[i][j].getY() + 5);
							(m[i][j].getList()).get(1).paint(g);
							(m[i][j].getList()).get(2).setXY(m[i][j].getX() + 30,m[i][j].getY() + 30);
							(m[i][j].getList()).get(2).paint(g);
							(m[i][j].getList()).get(3).setXY(m[i][j].getX() + 5,m[i][j].getY() + 5);
							(m[i][j].getList()).get(3).paint(g);
							break;
						case 5:
							(m[i][j].getList()).get(0).setXY(m[i][j].getX() + 18,m[i][j].getY() + 18);
							(m[i][j].getList()).get(0).paint(g);
							(m[i][j].getList()).get(1).setXY(m[i][j].getX() + 5,m[i][j].getY() + 30);
							(m[i][j].getList()).get(1).paint(g);
							(m[i][j].getList()).get(2).setXY(m[i][j].getX() + 30,m[i][j].getY() + 5);
							(m[i][j].getList()).get(2).paint(g);
							(m[i][j].getList()).get(3).setXY(m[i][j].getX() + 5,m[i][j].getY() + 5);
							(m[i][j].getList()).get(3).paint(g);
							(m[i][j].getList()).get(4).setXY(m[i][j].getX() + 30,m[i][j].getY() + 30);
							(m[i][j].getList()).get(4).paint(g);
							break;			
						}
					}				
				}			 
			}
			for(k=0;k<l.size();k++){
				l.get(k).paint(g);
			}	
			this.information(score,nbrvie);
		}
		else{ 
		  g.setColor(Color.black); // on affiche
    	  g.fillRect(0, 0, 1200, 900); // un rectangle noir qui fait la taille de la fenêtre
    	  g.setColor(Color.WHITE);
    	  g.drawString("Perdu ",400,400);
		}
	}

	private void information(int s,int nv){
		String info,enBas;

		enBas = "<html><body><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";
		info =  enBas + score;
		this.jL.setText( enBas +"Score : "+ score);
		
		
	}






	public void keyTyped(KeyEvent e) {
	}																// on a besoin de définir cette focntion pour pouvoir récupérer les touches, en fait parti du keylistener														
	public void keyPressed(KeyEvent e) {
	}


    public void keyReleased(KeyEvent e){	
    	
		int p=0;										//cette fonction permet de faire les évemenements lorsqu'on a une touche au clavier qui est appuyer 
		for(i=0;i<l.size();i++){
			 if((l.get(i).getNom()).equals("Pompier")){
				l.get(i).action(e,this.l);
				boolean d = l.get(i).enFeu(this.l);
				if (d == true){p = i;}
				if (l.get(i).getTire() == true){
					l.add(new Eau(l.get(i).getX(),l.get(i).getY(),l.get(i).getAff()));
				}
				if ( d == true ){
					l.remove(p);
					this.nbrvie = this.nbrvie - 1;
					score = score + 10000;
					if (this.nbrvie == 0)
					{
						this.inGame = false;
					}
					l.add(new Pompier(100,100,this.HAUTEUR,this.LARGEUR));
			}								
			}	
  		}
													//cette fonction permet de faire les évemenements de la fonction lorsqu'une touche est relachée 
	}															    // elle s'appelle toute seule
}