import Game.plateau.GameFrame;
import java.awt.*;
import javax.swing.*;

/** Cette classe contient notre main. Elle permet de déclarer une fenêtre et de lui ajouter des composants qui permettent de lancer le jeu */
public class Game2 extends JFrame {

    /** Attribut contenu uniquement dans cette classe qui est le Jpanel de la fenêtre */
    private GameFrame gf;                               // Variable correspondant à notre JPanel

    /** Fait appelle à la méthode initGame()*/
    private Game2() {
        initGame();                                     // On fait appelle à notre fonction initGame
    }
    /** Déclare une fenêtre et ajoute un KeyListener et un Jpanel à l'intérieur de cette fenêtre */
    private void initGame() {

        // On initialise la fenêtre et ses composants (Jpanel)

        gf = new GameFrame();                           // On déclare notre GameFrame
        add(gf);                                        // On l'ajoute à notre JFrame
        addKeyListener(gf);                             // On ajoute notre Jpanel en tant qu'écouteur à notre Jframe
        setResizable(false);                            // On set le boolean à false pour que l'utilisateur ne puisse pas modifier la fenêtre
        pack();                                         // On fixe les dimensions de la fenêtre par rapport aux composants qui sont contenus
        setTitle("Game");                               // On fixe le titre de notre fenêtre
        setLocationRelativeTo(null);                    // On met notre fenêtre au centre de l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On ajout l'évenement "fermeture de la fenêtre" grâce à la croix
        setVisible(true);                               // On rend notre fenêtre visible
        gf.run();                                       // On appelle la fonction run du Jpanel
    }
    
    /* Fonction principale de notre programme, elle permet de déclare une nouvelle instance Game2 */
    public static void main(String[] args) {
        JFrame game = new Game2();                      // On initialise un Game de la Classe Game2  
    }
}