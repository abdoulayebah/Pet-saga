
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * <b>Connexion est une classe qui affiche une boite de dialogue permettant à
 * l'utilisateur de se connecter en inscrivant son identifiant et son mot de
 * passe, pour accéder au jeu.</b>
 * <p>
 * L'utilisateur saisit son identifiant et son mot de passe dans les champs
 * prévus pour cela. Si l'identifiant et le mot de passe saisis existent, il
 * accède directement au jeu, sinon un message d'erreur s'affiche pour qu'il
 * réessaie de se connecter avec un autre identifiant et mot de passe.
 * </p>
 *
 * @author abdbah39
 */
public class Connexion extends JDialog implements ActionListener {

    /**
     * Label identifiant de l'utilisateur.
     */
    private JLabel nomLabel;

    /**
     * Label mot de passe.
     */
    private JLabel mdpLabel;

    /**
     * Zone de texte pour l'inscription de l'identifiant.
     */
    private JTextField nom;

    /**
     * Zone de texte pour l'inscripion du mot de passe.
     */
    private JPasswordField mdp;

    /**
     * Panel contenant les labels et les zones de textes.
     */
    private JPanel panneau;

    /**
     * Panel contenant les boutons Ok et Annuler.
     */
    private JPanel sud;

    /**
     * Panel contenant les boutons Réessayer et Annuler.
     */
    private JPanel sud2;

    /**
     * Bouton de validation de la connexion.
     */
    private JButton ok;

    /**
     * Bouton d'annulation de la connexion.
     */
    private JButton annuler;

    /**
     * Bouton invitant l'utilisateur à essayer un autre identifiant et/ou mot de
     * passe s'il n'arrive pas à se connecter.
     */
    private JButton reessayer;

    /**
     * Bouton permettant la fermeture de la boite de dialogue servant à la
     * connexion.
     */
    private JButton annuler2;

    /**
     * Identifiant de l'utilisateur.
     */
    private String identifiant;

    /**
     * Classe servant à afficher la liste de niveaux du jeu pour pouvoir y
     * jouer.
     */
    private CarteNiveaux cn;

    /**
     * Constructeur Connexion.
     * 
     * @throws Exception 
     */
    public Connexion() throws Exception {
    	this.setModal(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimEcran = tk.getScreenSize();
        int largEcr = dimEcran.width;
        int hautEcr = dimEcran.height;

        this.setSize(largEcr / 3, hautEcr / 4);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        nomLabel = new JLabel("Nom d'utilisateur");
        mdpLabel = new JLabel("Mot de passe");

        nom = new JTextField();
        mdp = new JPasswordField();

        panneau = new JPanel();
        panneau.setLayout(new GridLayout(2, 2, 20, 20));
        panneau.add(nomLabel);
        panneau.add(nom);
        panneau.add(mdpLabel);
        panneau.add(mdp);

        sud = new JPanel();
        sud2 = new JPanel();

        ok = new JButton("OK");
        ok.addActionListener(this);
        annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        sud.add(ok);
        sud.add(annuler);

        reessayer = new JButton("Reessayer");
        reessayer.addActionListener(this);
        annuler2 = new JButton("Annuler");
        annuler2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        sud2.add(reessayer);
        sud2.add(annuler2);

        cn = new CarteNiveaux();

        this.add(panneau, BorderLayout.NORTH);
        this.add(sud, BorderLayout.SOUTH);

    }

    /**
     * Compare entre le mot de passe entré par l'utilisateur et son mot de passe
     * personnel.
     *
     * @param m mot de passe entré par l'utilisateur pour s'identifier.
     * @param id identifiant de l'utilisateur.
     * @return <code>true</code> si le mot de passe entré par l'utilisateur est
     * identque à son mot de passe ou renvoie <code>false</code> dans le cas
     * contraire.
     * @throws Exception dans le cas où on ne peut pas récupérer les
     * utilisateurs à partir du disque dur.
     */
    public boolean motDePassesIdentiques(String id, char[] m) throws Exception {
        char[] t = FileWorker.recupererUtilisateurs().get(id).getMdp();
        for (int i = 0; i < t.length; i++) {
            if (t[i] != m[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Teste si l'utilisateur a réussi à se connecter.
     *
     * @return <code>true</code> si la connexion est réussie et renvoie
     * <code>false</code> si les identifiant et/ou mot de passe de l'utilisateur
     * ne figurent pas parmi la liste des idetifiants et mots de passes
     * enregistrés.
     */
    public boolean connexion() {
        identifiant = this.nom.getText();
        char[] motDePasse = this.mdp.getPassword();
        System.out.println(identifiant);
        for (int i = 0; i < motDePasse.length; i++) {
            System.out.print(motDePasse[i] + "");
        }
        System.out.println();
        try {
            if (FileWorker.recupererUtilisateurs().containsKey(identifiant)) {
                if (motDePassesIdentiques(identifiant, motDePasse) == true) {
                    System.out.println("Connexion réussie.");
                    dispose();
                    return true;
                } else {
                    System.out.println("Mot de passe incorrect !");
                    return false;
                }
            } else {
                System.out.println("Identifiant ou mot de passe incorrects.");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Permet l'affichage d'une boite de dialogue permettant à l'utilisateur de
     * choisir entre le mode standard et le mode compétition afin, par la suite,
     * d'accéder au jeu.
     *
     * @throws Exception dans le cas où on ne peut pas récupérer les
     * utilisateurs dans le disque dur.
     */
    public void accederAuJeu() throws Exception {
        cn.setVisible(true);
        this.dispose();
        Jeu.setUtilisateurCourant(FileWorker.recupererJoueur(identifiant));
    }

    /**
     * Gere les événements liés au clic de la souris sur un bouton.
     *
     * @param e évenénement lié au clic sur un bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean test = false;
        if (e.getActionCommand().equals("OK")) {
            test = connexion();
            if (test == true) {
                try {
                    accederAuJeu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                this.remove(panneau);
                this.remove(sud);
                this.validate();
                JTextArea area = new JTextArea();
                area.setText("Identifiant et/ou mot de passe incorrects.");
                area.setOpaque(false);
                this.add(area, BorderLayout.CENTER);
                this.add(sud2, BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();
            }
        } else if (e.getActionCommand().equals("Reessayer")) {
            this.dispose();
            Connexion i = null;
            try {
                i = new Connexion();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            i.setVisible(true);
        }
    }

}
