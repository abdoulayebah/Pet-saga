
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>PagePrincipale est une classe qui représente la première fenêtre qui
 * s'affiche dès que l'utilisateur ouvre le jeu.</b>
 * <p>
 * Cette fenêtre contient trois boutons :
 * <ul>
 * <li>Jouer : permet d'accéder au jeu.</li>
 * <li>Connexion : permet de s'identifier pour accéder à sa session.</li>
 * <li>Inscription : permet de s'inscrire au jeu.</li>
 * </ul>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class PagePrincipale extends JFrame implements ActionListener {

    /**
     * Fichier contenant l'image de fond.
     */
    private File image = new File("src/unnamed.png");

    /**
     * Hauteur et largeur de l'écran.
     */
    private int largEcr, hautEcr;

    /**
     * Image de fond.
     */
    private ImageFond img;

    /**
     * Classe contenant la liste des niveaux du jeu.
     */
    private CarteNiveaux cn;

    /**
     * Panel contenant les boutons: Jouer, Connexion et Inscription.
     */
    private JPanel boutons;

    /**
     * Panel seravnt à organiser la fenêtre principale du jeu.
     */
    private JPanel facultatif;

    /**
     * Bouton qui permet d'accéder au jeu.
     */
    private JButton jouer;

    /**
     * Bouton qui permet à un utilisateur de s'inscrire au jeu pour la première
     * fois.
     */
    private JButton connexion;

    /**
     * Bouton qui permet à un utilisateur de s'identifier.
     */
    private JButton inscription;

    /**
     * Constructeur PagePrincipale.
     *
     * @throws Exception dans le cas où on ne peut pas créer une instance de
     * "CarteNiveaux".
     */
    public PagePrincipale() throws Exception {

        Container contenu = getContentPane();
        boutons = new JPanel();
        facultatif = new JPanel();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimEcran = tk.getScreenSize();
        largEcr = dimEcran.width;
        hautEcr = dimEcran.height;

        this.setExtendedState(this.MAXIMIZED_BOTH);

        facultatif.setPreferredSize(new Dimension(largEcr, hautEcr / 3));
        facultatif.setOpaque(false);

        cn = new CarteNiveaux();
        cn.setVisible(false);

        img = new ImageFond(image);
        img.setLayout(new BorderLayout());
        img.setLayout(new FlowLayout());
        img.add(facultatif);
        img.add(boutons);
        contenu.add(img);

        jouer = new JButton("Jouer");
        jouer.setPreferredSize(new Dimension(150, 70));
        jouer.addActionListener(this);

        connexion = new JButton("Connexion");
        connexion.setPreferredSize(new Dimension(150, 70));
        connexion.addActionListener(this);

        inscription = new JButton("Inscription");
        inscription.setPreferredSize(new Dimension(150, 70));
        inscription.addActionListener(this);

        boutons.setOpaque(false);
        boutons.setLayout(new GridLayout(3, 1, 20, 20));
        boutons.add(jouer);
        boutons.add(connexion);
        boutons.add(inscription);

        this.setSize(new Dimension(largEcr, hautEcr));
        this.setMinimumSize(new Dimension(3 * largEcr / 4, 3 * hautEcr / 4));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Gere les événements liés au clic de la souris sur un bouton.
     *
     * @param e évenénement lié au clic sur un bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Jouer")) {
            try {
                cn.setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Connexion")) {
            Connexion cnx = null;
            try {
                cnx = new Connexion();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            cnx.setVisible(true);
        } else if (e.getActionCommand().equals("Inscription")) {
            Inscription i = new Inscription();
            i.setVisible(true);
        }
    }

}
