
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * <b>Inscription est une classe qui affiche une boite de dialogue permettant à
 * l'utilisateur de s'inscrire pour la première fois au jeu.</b>
 * <p>
 * L'utilisateur saisit un identifiant et un mot de passe dans les champs prévus
 * pour cela. Si l'identifiant et le mot de passe saisis n'existent pas déjà, un
 * nouvel utilisateur est crée et il est stocké dans un Hashmap qui sera stocké
 * dans le disque dûr. Dans le cas contraire, un message d'erreur s'affiche
 * invitant l'utilisateur à réessayer de s'inscrire.
 * </p>
 *
 * @author abdbah39
 */
public class Inscription extends JDialog implements ActionListener {

    /**
     * Label identifiant.
     */
    private JLabel nomLabel;

    /**
     * Label mot de passe.
     */
    private JLabel mdpLabel;

    /**
     * Zone de texte permettant l'inscription de l'identifiant.
     */
    private JTextField nom;

    /**
     * Zone de texte permettant l'inscription du mot de passe.
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
    private JPanel ins;

    /**
     * Bouton de validation de l'inscription.
     */
    private JButton ok;

    /**
     * Bouton d'annulation de l'inscription.
     */
    private JButton annuler;

    /**
     * Bouton invitant l'utilisateur à essayer un autre identifiant et/ou mot de
     * passe si ceux qu'il à saisi existent déjà.
     */
    private JButton reessayer;

    /**
     * Bouton permettant la fermeture de la boite de dialogue servant à
     * l'inscription.
     */
    private JButton annuler2;

    /**
     * Constructeur Inscription.
     */
    public Inscription() {
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

        ok = new JButton("OK");
        ok.addActionListener(this);
        annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        sud.add(ok);
        sud.add(annuler);

        ins = new JPanel();
        reessayer = new JButton("Reessayer");
        reessayer.addActionListener(this);
        annuler2 = new JButton("Annuler");
        annuler2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ins.add(reessayer);
        ins.add(annuler2);

        this.add(panneau, BorderLayout.NORTH);
        this.add(sud, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            boolean b = false;
            String identifiant = this.nom.getText();
            char[] motDePasse = this.mdp.getPassword();
            System.out.println(identifiant);
            for (int i = 0; i < motDePasse.length; i++) {
                System.out.print(motDePasse[i] + "");
            }
            System.out.println();
            Utilisateur u = new Utilisateur(identifiant, motDePasse);
            Jeu j = null;
            try {
                j = new Jeu();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                b = j.creerUtilisateur(u);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (b == true) {
                this.remove(panneau);
                this.remove(sud);
                JTextArea area = new JTextArea();
                area.setText("Sauvegarde réussie");
                area.setOpaque(false);
                this.add(area);
                this.validate();
                System.out.println("Sauvegarde réussie !");
                dispose();
            } else {
                this.remove(panneau);
                this.remove(sud);
                this.validate();
                JTextArea area = new JTextArea();
                area.setText("Sauvegarde non réussie");
                area.setOpaque(false);
                this.add(area, BorderLayout.CENTER);
                this.add(ins, BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();
                System.out.println("Sauvegarde non réussie !");
            }
        } else if (e.getActionCommand().equals("Reessayer")) {
            this.dispose();
            Inscription i = new Inscription();
            i.setVisible(true);
        }
    }

}
