
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * <p>
 * Jeu est la classe qui permet la création et le stockage de la liste des
 * niveaux dans le dique dur ainsi que la récupération des utilisateurs inscrits
 * au jeu.</p>
 *
 * @author tghaou28
 * @author sgasan32
 * @author abdbah39
 */
public class Jeu {

    /**
     * Hashmap qui stocke la liste des niveaux du jeu.
     */
    private static HashMap<Integer, Niveau> niveau = new HashMap<Integer, Niveau>();

    /**
     * Hashmap qui stocke tous les utilisateurs qui s'inscrivent au jeu.
     */
    private static HashMap<String, Utilisateur> users = new HashMap<String, Utilisateur>();

    /**
     * Pile d'exécution qui stocke les changements d'état du niveau courant.
     */
    public static Stack<Niveau> stackEvolution = new Stack();

    /**
     * Niveau courant initialisé à un et qui augmente à la fin de chaque niveau.
     */
    private static Integer numNivCourant;

    /**
     * Niveau courant dans le jeu.
     */
    private static Niveau nivCourant;

    /**
     * Utilisateur du jeu.
     */
    private static Utilisateur utilisateurCourant;

    /**
     * Constructeur Jeu.
     *
     * @throws Exception Si jamais le
     *
     * @see Jeu#niveau
     * @see Jeu#nivCourant
     */
    public Jeu() throws Exception {
        this.niveau = FileWorker.uploadLvl();
        this.numNivCourant = 1;
        this.utilisateurCourant = null;
        this.users = FileWorker.recupererUtilisateurs();
    }

    /**
     * Constructeur jeu.
     *
     * @param i niveau courant.
     * @throws Exception dans le cas où on ne peut pas récupérer la liste des
     * utilisateurs du jeu dans le disque dur.
     *
     * @see Jeu#niveau
     * @see Jeu#nivCourant
     */
    public Jeu(int i) throws Exception {
        this.niveau = FileWorker.uploadLvl();
        this.numNivCourant = i;
        this.utilisateurCourant = null;
        this.users = FileWorker.recupererUtilisateurs();
    }

    /**
     * Renvoie la liste des niveaux.
     *
     * @return un Hashmap où sont stockés tous les niveaux.
     */
    public static HashMap<Integer, Niveau> getNiveau() {
        return niveau;
    }

    public static Niveau rechargerNiveau(int i) throws Exception {
        nivCourant = FileWorker.uploadLvl().get(i);
        return nivCourant;
    }

    /**
     * Renvoie le niveau courant.
     *
     * @return le niveau courant.
     */
    public static int getNumNivCourant() {
        return numNivCourant;
    }

    public static void setNumNivCourant(int i) {
        numNivCourant = i;
    }

    public static Utilisateur getUtilisateurCourant() {
        return utilisateurCourant;
    }

    public static void setUtilisateurCourant(Utilisateur u) {
        utilisateurCourant = u;
    }

    public static HashMap<String, Utilisateur> getUsers() {
        return users;
    }

    /**
     * Méthode main.
     *
     * @param args paramètre de la méthode main.
     * @throws Exception dans le cas où on ne peut pas créer une instance de
     * jeu.
     */
    public static void main(String[] args) throws Exception {
        Jeu j = new Jeu();
        Niveau niv = niveau.get(numNivCourant);
        niv.play();
        while (numNivCourant != niveau.size()) {
            nextLevel();
        }
    }

    /**
     * Permet l'affichage de l'interface textuelle et permet de lancer une
     * nouvelle partie au niveau du terminal.
     */
    public static void nextLevel() {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.print(" 1-niveau suivant\n 2-quitter");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                numNivCourant++;
                //j = new Jeu(nivCourant);
                Jeu.stackEvolution = new Stack();
                Niveau niv = niveau.get(numNivCourant);
                niv.play();
                break;
            case 2:
                System.out.print("vous avez decidé de quitter. Aurevoir!");
        }
    }

    /**
     * Sauvegarde les utilisateurs dans un HashMap.
     *
     * @param u Utilisateur que l'on veut stocker.
     * @return <code>false</code> si l'utilisateur existe déjà ou
     * <code>true</code> sinon.
     * @throws Exception où on ne peut pas créer un utilisateur et le
     * sauvegarder dans le disque dur.
     */
    public static boolean creerUtilisateur(Utilisateur u) throws Exception {
        File usr = new File("users.ser");
        if (!usr.exists()) {
            usr.createNewFile();
            users.put(u.getId(), u);
            FileWorker.sauvegarderUtilisateurs(users);
            return true;
        } else if (FileWorker.recupererUtilisateurs().containsKey(u.getId())) {
            return false;
        } else {
            users.put(u.getId(), u);
            FileWorker.sauvegarderUtilisateurs(users);
            return true;
        }
    }

    public static void sauvegarderUtilisateur(Utilisateur u) throws Exception {
        if (FileWorker.recupererUtilisateurs().containsKey(u.getId())) {
            users.put(u.getId(), u);
            FileWorker.sauvegarderUtilisateurs(users);
        }
    }

}
