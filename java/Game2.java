package Game;
import Game.plateau.GameFrame;
import java.awt.*;
import javax.swing.*;

public class Game2 extends JFrame {

    private GameFrame gf;                               // Variable correspondant à notre JPanel

    /* Contructeur */
    private Game2() {
        initGame();                                     // On fait appelle à notre fonction initGame
    }
    /*   Méthodes  */
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
    
    public static void main(String[] args) {
        JFrame game = new Game2();                      // On initialise un Game de la Classe Game2  
    }
}