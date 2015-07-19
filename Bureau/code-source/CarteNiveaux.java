
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>CarteNiveaux est une classe qui permet l'affichage de la liste des niveaux
 * présents dans le jeu.</b>
 *
 * @author abdbah39
 */
public class CarteNiveaux extends JFrame implements ActionListener {

    /**
     * Instance de la classe Jeu qui permet la récupération des niveaux et des
     * utilisateurs stockés dans le disque dur.
     */
    private Jeu j = new Jeu();

    /**
     * Panel vertical contenant une liste de boutons.
     */
    private JPanel mv = new JPanel();

    /**
     * Fenêtre qui contient la grille du jeu.
     */
    private FenetreJeu fj;

    /**
     * Bouton qui sert à afficher la liste des niveaux.
     */
    private JButton carte;

    /**
     * Bouton qui permet de quitter le jeu.
     */
    private JButton quitter;

    /**
     * Bouton qui sert à afficher la liste des régles du jeu.
     */
    private JButton regles;

    /**
     * Bouton qui permet d'accéder à un niveau donné.
     */
    private JButton jouer;

    /**
     * Bouton qui permet de se déconnecter du jeu.
     */
    private JButton deconnexion;

    /**
     * Hauteur et largeur de l'écran.
     */
    private int largEcr, hautEcr;

    /**
     * Fichier contenant l'image qui servira comme image de fond pour la fenêtre
     * du jeu.
     */
    private File b = new File("src/Image.jpg");

    /**
     * Attribution d'une image de fond à la fenêtre de jeu.
     */
    private ImageFond img = new ImageFond(b);

    /**
     * Panel contenant la liste des niveaux.
     */
    private JPanel niveaux;

    /**
     * Panel donnant la possibilité à l'utilisateur d'accéder à un niveau.
     */
    private JPanel AccesJeu;

    /**
     * Nombre de niveaux présents dans le jeu.
     */
    private final int NbNiveaux = j.getNiveau().size();

    /**
     * Tableau qui stocke les différents panels du jeu.
     */
    private final String[] listContent = new String[NbNiveaux + 1];

    /**
     * Fenetre affichant la liste des régles du jeu.
     */
    private JDialog aide;

    /**
     * Boite de dialogue donnant la possibilité à l'utilisateur d'accéder à un
     * niveau.
     */
    private JDialog niveau;

    /**
     * Layout utilisé pour les différentes fenêtres du jeu.
     */
    private static CardLayout cl;

    /**
     * Constructeur CarteNiveaux.
     */
    public CarteNiveaux() throws Exception {
        initComponent();
        initMenuVertical();
    }

    /**
     * Initialisation des composants du Panel contenant les niveaux du jeu.
     *
     * @throws Exception dans le cas où on ne peut pas créer une instance de
     * jeu.
     */
    public void initComponent() throws Exception {
        j = new Jeu();
        for (int i = 0; i < listContent.length; i++) {
            listContent[i] = "Card" + i;
        }

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimEcran = tk.getScreenSize();
        largEcr = dimEcran.width;
        hautEcr = dimEcran.height;

        cl = new CardLayout();

        this.setExtendedState(this.MAXIMIZED_BOTH);
        Container contenu = getContentPane();
        img.setLayout(cl);

        niveaux = new JPanel();
        niveaux.setOpaque(false);

        contenu.setLayout(new BorderLayout());
        contenu.add(img, BorderLayout.CENTER);
        img.add(niveaux, listContent[0]);
        contenu.add(mv, BorderLayout.EAST);

        niveaux.setLayout(new FlowLayout());
        for (int i = 0; i < NbNiveaux; i++) {
            ImageIcon img = new ImageIcon("src/wolf.png");
            JButton n = new JButton();
            n.setText("" + (int) (i + 1));
            n.setIcon(img);
            Font f = new Font("Times New Roman", Font.BOLD, 60);
            n.setFont(f);
            n.setForeground(Color.white);
            n.setVerticalTextPosition(SwingConstants.CENTER);
            n.setHorizontalTextPosition(SwingConstants.CENTER);
            n.setBorderPainted(false);
            n.setPreferredSize(new Dimension(150, 150));
            n.addActionListener(this);
            niveaux.add(n);
        }
        this.setSize(new Dimension(largEcr, hautEcr));
        this.setMinimumSize(new Dimension(3 * largEcr / 4, 3 * hautEcr / 4));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Initialisation du menu vertical contenant les boutons qui permettent
     * d'aller à la carte, de voir les régles du jeu, de se déconnecter ou de
     * quitter le jeu.
     */
    public void initMenuVertical() {
        BoxLayout bl = new BoxLayout(mv, BoxLayout.Y_AXIS);
        mv.setLayout(bl);
        mv.setPreferredSize(new Dimension(largEcr / 16, hautEcr));
        mv.setBackground(Color.green.darker());

        carte = new JButton("Carte");
        carte.setPreferredSize(new Dimension(largEcr / 16, 60));
        carte.setToolTipText("<HTML>Quitter le niveau actuel<BR> et revenir à la Liste des niveuax.</HTML>");
        carte.addActionListener(this);
        mv.add(carte);

        regles = new JButton("Régles");
        regles.setPreferredSize(new Dimension(largEcr / 16, 60));
        regles.setToolTipText("Régles du jeu.");
        regles.addActionListener(this);
        mv.add(regles);

        deconnexion = new JButton("Déconnexion");
        deconnexion.setPreferredSize(new Dimension(largEcr / 16, 60));
        deconnexion.setToolTipText("Se déconnecter du jeu.");
        deconnexion.addActionListener(this);
        mv.add(Box.createVerticalGlue());
        mv.add(deconnexion);

        quitter = new JButton("Quitter");
        quitter.setPreferredSize(new Dimension(largEcr / 16, 60));
        quitter.setToolTipText("Quitter le jeu.");
        quitter.addActionListener(this);
        mv.add(quitter);

    }

    /**
     * Initialisation de la fenêtre qui affiche la liste des régles du jeu.
     */
    public void initFenetreAide() {
        aide = new JDialog();
        aide.setPreferredSize(new Dimension(largEcr / 2, 2 * hautEcr / 3));
        aide.setResizable(false);

        Font f = new Font("Times New Roman", Font.BOLD, 20);
        JTextArea FenAide = new JTextArea();
        FenAide.setFont(f);
        FenAide.setBackground(new Color(131, 182, 216));
        FenAide.setText("Appuie sur les groupes de blocs pour les éliminer ! " + "\n");
        FenAide.append("Sauve les animaux en les amenant en bas de l'ecran." + "\n");
        FenAide.append("Utilise des jokers sur les blocs de cubes pour les éliminer." + "\n");
        FenAide.setEditable(false);
        aide.add(FenAide);

        aide.pack();
        aide.setVisible(true);
        aide.setLocationRelativeTo(null);
    }

    /**
     * Permet d'accéder à un niveau dont le numéro est mis en argument.
     *
     * @param i Numéro du niveau auquel on voudrait accéder.
     */
    public void AccederAuNiveau(int i) {
        niveau = new JDialog();
        AccesJeu = new JPanel();
        AccesJeu.setBackground(new Color(131, 182, 216));
        niveau.setSize(600, 600);

        JTextArea fenAccesNiveau = new JTextArea();
        fenAccesNiveau.setText("Niveau " + i + "\n");
        Font f = new Font("Times New Roman", Font.BOLD, 30);
        fenAccesNiveau.setFont(f);
        fenAccesNiveau.append("Sauve " + j.getNiveau().get(i).getNbAnimauxTotal() + " animaux.");
        fenAccesNiveau.setBackground(new Color(131, 182, 216));
        fenAccesNiveau.setEditable(false);

        jouer = new JButton("Jouer");
        jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    fj = new FenetreJeu(i);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                img.add(fj.getConteneur(), listContent[i]);
                cl.show(img, listContent[i]);
                niveau.dispose();
            }
        });

        niveau.setLayout(new BorderLayout());
        niveau.add(fenAccesNiveau, BorderLayout.CENTER);
        niveau.add(jouer, BorderLayout.SOUTH);
        niveau.setLocationRelativeTo(null);
        niveau.setVisible(true);
    }

    /**
     * Gere les événements liés au clic de la souris sur un bouton ou un panel.
     *
     * @param e évenénement lié au clic sur un bouton ou un JPanel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("" + 1)) {
            AccederAuNiveau(1);
        }
        for (int i = 2; i <= NbNiveaux; i++) {
            if (e.getActionCommand().equals("" + i)) {
                if (j.getUtilisateurCourant() == null && i <= Jeu.getNumNivCourant()) {
                    AccederAuNiveau(i);
                } else if (j.getUtilisateurCourant() != null) {
                    if (i <= j.getUtilisateurCourant().getNiveau()) {
                        AccederAuNiveau(i);
                    }
                }
            }
        }

        if (e.getActionCommand().equals("Quitter")) {
            int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter le jeu", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION);
            if (rep == 0) {
                if (Jeu.getUtilisateurCourant() != null){ 
                    try {
                        Jeu.sauvegarderUtilisateur(Jeu.getUtilisateurCourant());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.exit(0);
            }
        } else if (e.getActionCommand().equals("Carte")) {
            cl.show(img, listContent[0]);
        } else if (e.getActionCommand().equals("Régles")) {
            initFenetreAide();
        } else if (e.getActionCommand().equals("Déconnexion")) {
            if (Jeu.getUtilisateurCourant() != null){ 
                try {
                    Jeu.sauvegarderUtilisateur(Jeu.getUtilisateurCourant());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.dispose();
            PagePrincipale pp = null;
            try {
                pp = new PagePrincipale();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            pp.setVisible(true);
        }
    }
}
