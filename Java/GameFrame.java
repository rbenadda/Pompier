package Game.plateau;
import Game.entite.Displayable;
import Game.entite.outils.*;
import Game.fonction.*;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

/** Class qui contient la fonction run de notre jeu */
public class GameFrame extends JPanel implements KeyListener{
	/* Varaibles */
	/** C'est la Largeur de la fenêtre */
    private static final int LARGEUR = 1200; 																// Paramètre de ma fenêtre
    /** C'est la Hauteur de la fenêtre */
    private static final int HAUTEUR = 800; 																// Paramètre de ma fenêtre
    /** C'est la matrice du terrain */
    private Displayable [][] m;																				// Matrice de Displayable qui nous permettra de stocker le terrain
    /** C'est la liste qui contient tout ce qui sera afficher à l'écran */
    private ArrayList<Displayable> l; 																		// Liste contenant les displayables à afficher 	
    /** C'est Le boolean qui permet de savoir si on est en jeu ou non */
    private boolean inGame; 																				// Savoir si on est en jeu ou non
    /** C'est Le nombre de vie */
    private int nbrvie;																						// Initialisation du nombre de vie
    /** C'est le Jlabel associé à La classe GameFrame */
    private JLabel jL;																						// Initialisation d'un JLabel
    /** C'est le score initialisé à 0 */
    private int score = 0;																					// Initialisation du score à zero
    /** C'est le temps de jeu initialisé à 60 secondes ou 60 000 millisecondes */
    private int timer = 60000;																				// Initialisation du temps de jeu en milliseconde 
    /* Contructeur */
    /** Il appelle la Méthode initGameFrame */
	public GameFrame(){ 

		initGameFrame();																					// constructeur de la classe GameFrame	
	}								 

	/** Méthode qui permet d'initialiser la fenêtre,les Displayables et le jLabel */
    private void initGameFrame(){
 		
    	int yi=0;																							// Initialisation d'un entier à 0
    	int xi=0;																							// Initialisation d'un entier à 0
        setBackground(Color.BLACK); 																		// on set la couleur du fond à blanc (même si pas obligatoire)
   		setPreferredSize(new Dimension(LARGEUR, HAUTEUR+50)); 												// on set la taille de notre jPanel au variable stocker dans cette classe et on oblige notre jFrame à avoir la même taille
   		this.inGame = true; 																				// on set le boolean qui nous sert à savoir si on est en jeu à true
   		this.nbrvie = 3;																					// On set le nombre de vie à 3 
		m = new Displayable[16][24]; 																		// on déclare une matrice de Displayable de taille 24*16 ce qui correspond à 24*50 = 1200 et 16*50 = 800
		l = new ArrayList<Displayable>();																	// on déclare la matrice de Dispalyable qui va contenir (pompier,feu,eau)
		for(int i=0;i<m.length;i++){ 																		// on parcours la matrice //
			for(int j=0;j<m[i].length;j++){																	// on parcours la matrice //
				m[i][j]= ZoneFactory.createRandomZone();													// On appelle la Méthode createRandomZone qui nous renvoie une zone (rien,foret) dans notre case[i][j] de la matrice
				m[i][j].setXY(xi,yi);																		// On appelle la Méthode SetXY de displaybles pour set les coordonnées de la case[i][j] de la matrice à (xi,yi) respectivement
				xi = xi+50; 																				// On incrémente xi de 50 (50 corespond à une taille de notre case[i][j])
			}
			yi = yi+50;																						// On incrémente yi de 50 (50 corespond à une taille de notre case[i][j])
			xi = 0; 																						// On set xi à 0 lorsqu'on à atteint un ligne de la matrice
		}
		l.add(new Pompier(100,100,this.HAUTEUR,this.LARGEUR));												// Ajout d'un Pompier dans la liste		
		l.add(new Feu(m));																					// Ajout un nouveau feu dans la liste de displayable
					
		jL  = new JLabel("");																				// On déclare un Jlabel
		this.setLayout(new FlowLayout(FlowLayout.LEFT));													// On set sa position à gauche de la fenêtre
    	this.add(jL); 																						// On l'ajoute dans notre Jframe
    }


    /** Permet de faire touner le Jeu et les differentes méthodes qui le composent */
	public void run() {

		while(this.inGame == true){																			// Tanque Le boolean in game est vrai
			try{Thread.sleep(100);}catch(InterruptedException e){}											// On dit à notre machine d'attendre 100 millisecondes
				Propagation.Eau(this.l,this.m);																// Appelle de la Méthode Eau qui éteint tout les feux
				Propagation.Animal(this.l,this.m);															// Appelle de la Méthode Animal qui à un chance d'ajouter un Animal
				score = Propagation.SurAnimal(this.l,this.score);											// Appelle de la Méthode SurAnimal qui permet de savoir si un animal est sur un feu ou sur un Pompier et update le score en fonction
				Propagation.Feu(this.l,this.m,this.LARGEUR,this.HAUTEUR);									// Appelle de la Méthode Feu qui Feu qui propage le feu
				Propagation.ArretFeu(this.l,this.m);														// Appelle de la Méthode ArretFeu qui supprime les feu qui sont sur les mêmes coordonnées qu'une instance Eau 
				score = Propagation.updateScore(m,score);													// Appelle de la Méthode updateScore qui met à jour le score
				timer = timer - 100;																		// Enlève 100 millisecondes au temps restant
				this.repaint();																				// Méthode de Jframe qui update et fait appelle au méthode paint des differentes instances
		}
		 
    }

    /** Permet d'effacer et de redessiner les composants de la fenêtre 
    * @param g un objet Graphics */
    public void paintComponent(Graphics g) { 																// la fonction paintComponent appelle la méthode Component.update() qui efface et redessine le fond dans le jpanel par défaut
    															
    	super.paintComponent(g);																			// On appelle le constructeur de la classe paintcomponent
    	dopaint(g);																							// on appelle la fonction dopaint()
    }

    /** Méthode qui contient le décors de notre jeu 
    *  @param g un objet Graphics */
    private void dopaint(Graphics g){ 																		
    	  																									
    	if(inGame == true && timer > 0){ 																	//tantque je suis en jeu et que le timer est suppérieur à 0
    		for(int i=0;i<m.length;i++){ 																	// parcours de matrice
				for(int j=0;j<m[i].length;j++){ 															// parcours de matrice
					m[i][j].paint(g); 																		// je récupère la valeur contenu à [x][y] de la matrice et je la met dans z
					if((m[i][j].getNom()).equals("Foret")){ 												// s'il c'est une forêt 
						int nbrArbre = (m[i][j].getList()).size(); 											// je rérupère la taille de la liste 
						switch(nbrArbre){ 																	// affichage des arbres 
						case 1:
							(m[i][j].getList()).get(0).setXY(m[i][j].getX() + 18,m[i][j].getY() + 18);		// même fonctionnement pour chaque *case* . On a donc notre *nbrArbre* qui va nous
							(m[i][j].getList()).get(0).paint(g);											// donner quel case doit-on réaliser. pour le *case* 1, on récupère le premier élement
							break;																			// de la list (m[i][j].getList()) et on set ses coordonnées grâce à la méthode setXY puis on appelle la méthode paint(g); 
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
			int nbr = 0;																						// On initialise un entier à 0 
			for (int i=0;i<this.l.size();i++){																	// Pour i allant de 0 à la longeur de la liste de Displayable
		
				if ((l.get(i).getNom()).equals("Pompier")){														// Si l'élement de l'instance est égal à pompier
					nbr = nbr + l.get(i).getBombardier();														// On ajoute le nombre de tir restant à notre compteur de tur total
				}
			}
			int z = 800;																						// Déclaration d'un entier z qui prend l'abscisse x																						
			while(nbr != 0){
				try{
					Image img = ImageIO.read(new File("Image/bombardement.png"));								// On déclare une image
					g.drawImage(img, z,800,50,50,null);															// On la dessine avec comme paramètre l'image,le z,800 (le plus bas de notre ordonnée)
					z = z + 50;																					// On ajoute 50 à Z
					nbr = nbr - 1;
				} catch(IOException e){																			// Catch prend en attribut un throwable qui per met de gérer les exceptions
				e.printStackTrace();
				}
			}

			for(int k=0;k<l.size();k++){																		// pour k allant de 0 à la taille de la liste
				l.get(k).paint(g);																				// On appelle la méthode paint de l'instance contenu à get(k)
			}	
			this.information(score,nbrvie);																		// On appelle la Méthode information qui prends en paramètre le score et le nbrScore
			z = 1000;																							// Déclaration d'un entier z qui prend l'abscisse x
			for(int d=0;d<nbrvie;d++){																			// pour d=0 allant du nbr de vie restante
				try{
					Image img = ImageIO.read(new File("Image/coeur.png"));										// On déclare une image
					g.drawImage(img, z,800,50,50,null);															// On la dessine avec comme paramètre l'image,le z,800 (le plus bas de notre ordonnée)
					z = z + 50;																					// On ajoute 50 à Z
				} catch(IOException e){																			// Catch prend en attribut un throwable qui per met de gérer les exceptions
				e.printStackTrace();
				}
			}
		}
		else{ 
		  g.setColor(Color.black); // on affiche
    	  g.fillRect(0, 0, 1200, 900); // un rectangle noir qui fait la taille de la fenêtre
    	  g.setColor(Color.WHITE);
    	  g.drawString("Fin de partie ",600,400);
		}
	}


	/** Méthode qui permet d'afficher le score et le temps 
	* @param s Le score du jeu 
	* @param nv Le nombre de vie du joueur
	*/
	private void information(int s,int nv){

		String enBas;																							// On déclare un String 
																												// On initialise enbas pour que notre texte soit en bas à gauche
		enBas = "<html><body><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";
		this.jL.setText( enBas +"Score : "+ score + " / " + "Temps : "+ timer/1000);							// On set le texte de notre Jlabel		
	}
	/**   Permet d'agir sur mon jeu selon les touches pressées avec un code retour différents à celui du keyPressed
	* @param e Récupère la touche\Click souris
	*/
	public void keyTyped(KeyEvent e) {
	}					
	/**   Permet d'agir sur mon jeu selon les touches pressées
	* @param e Récupère la touche\Click souris
	*/																		
	public void keyPressed(KeyEvent e) {
	}

	/**   Permet d'agir sur mon jeu selon les touches relachées
	* @param e Récupère la touche\Click souris
	*/
    public void keyReleased(KeyEvent e){	
    	
		int p=0;																								// Déclaration d'un entier à 0 
			for(int i=0;i<l.size();i++){																		// Pour i allant de 0 à la taille de la liste de Displayable
				if((l.get(i).getNom()).equals("Pompier")){														// Si le nom de l'instance à la place i de la liste est égale à Pompier
					l.get(i).action(e,this.l);																	// On appelle la fonction action de pompier
					boolean d = l.get(i).enFeu(this.l);															// On récupère le boolean revoyer par la Méthode Enfeu
					if (d == true){p = i;}																		// Si d == true, on set p à la valeur de i
					if (l.get(i).getTire() == true){															// si L'instance est en train de tirer
						l.add(new Eau(l.get(i).getX(),l.get(i).getY(),l.get(i).getAff()));						// Alors on ajoute une instance eau avec comme attributs le X,Y Et le sens de l'instance pompier de la place i de la liste
						
					}
					if ( d == true ){																			// Si d = true 
						l.remove(p);																			// On enlève l'instance à la place p de la liste de displayable
						this.nbrvie = this.nbrvie - 1;															// On enlère 1 au nombre de vie
						score = score + 10000;																	// On ajoute 10000 au score
					if (this.nbrvie == 0)																		// Si le nombre de vie est égal à 0
					{
						this.inGame = false;																	// On passe le boolean InGame à faux
					}
					l.add(new Pompier(100,100,this.HAUTEUR,this.LARGEUR));										// On ajoute un nouveau pompier avec comme coordonnées 100,100 
			}								
			}	
  		}
													
	}				
}
