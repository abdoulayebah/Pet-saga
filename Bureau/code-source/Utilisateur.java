
import java.io.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * <b>Utilisateur est une classe qui contient toutes les information liées à un
 * utilisateur du jeu.</b>
 * <p>
 * Un utilisateur est caractérisé par :
 * <ul>
 * <li>Un identifiant unique.</li>
 * <li>Un mot de passe unique.</li>
 * <li>Le dernier niveau qu'il a atteint dans le jeu.</li>
 * <li>Un score.</li>
 * <li>Une cagnotte.</li>
 * </ul>
 * 
 *
 * @author tghaou28
 * @author sgasan32
 * @author abdbah39
 */
public class Utilisateur implements Serializable {

    /**
     * Identifiant de l'utilisateur.
     */
    private String id;

    /**
     * Mot de passe de l'utilisateur.
     */
    private char[] mdp;

    /**
     * Dernier niveau atteint par l'utilisateur.
     */
    private int niveau;

    /**
     * Score total de l'utilisateur.
     */
    private int score;

    /**
     * Somme d'argent présente dans la cagnotte de l'utilisateur.
     */
    private int cagnotte;

    /**
     * Liste des utilisateurs sous forme d'un HashMap.
     */
    private HashMap<String, Utilisateur> users = new HashMap<>();

    /**
     * Constructeur Utilisateur.
     *
     * @param id identifiant de l'utilisateur.
     * @param mdp mot de passe de l'utilisateur.
     * @param niveau dernier niveau atteint par l'utilisateur.
     * @param score score total de l'utilisateur
     * @param cagnotte somme d'argent présente dans la cagnotte de
     * l'utilisateur.
     *
     * @see Utilisateur#id
     * @see Utilisateur#mdp
     * @see Utilisateur#niveau
     * @see Utilisateur#score
     * @see Utilisateur#cagnotte
     */
    public Utilisateur(String id, char[] mdp, int niveau, int score, int cagnotte) {
        this.id = id;
        this.mdp = mdp;
        this.niveau = niveau;
        this.score = score;
        this.cagnotte = cagnotte;
    }

    /**
     * Constructeur Utilisateur.
     *
     * @param id identifiant de l'utilisateur.
     * @param mdp mot de passe de l'utilisateur.
     *
     * @see Utilisateur#id
     * @see Utilisateur#mdp
     */
    public Utilisateur(String id, char[] mdp) {
        this.id = id;
        this.mdp = mdp;
    }

    /**
     * Renvoie les informations liées à l'utilisateur sous forme d'une chaine de
     * caractères.
     *
     * @return une chaine de caractères décrivant l'utilisatur.
     */
    public String toString() {
        return id + " (" + niveau + ") " + score + " " + cagnotte + ".";
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return un string représentant l'identifiant de l'utilisateur.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return le mot de passe de l'utilisateur.
     */
    public char[] getMdp() {
        return this.mdp;
    }

    /**
     * Retourne le dernier niveau atteint par l'utilisateur.
     *
     * @return le drnier niveau atteint par l'utilisateur.
     */
    public int getNiveau() {
        return this.niveau;
    }

    /**
     * Retourne le score de l'utilisateur.
     *
     * @return le score de l'utilisateur.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Retourne la somme d'argent présente dans la cagnotte de l'utilisateur.
     *
     * @return la somme que possède l'utilisateur dans sa cagnotte.
     */
    public int getCagnotte() {
        return this.cagnotte;
    }

    /**
     * Met à jour l'identifiant de l'utilisateur.
     *
     * @param id identifiant de l'utilisateur.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Met à jour le mot de passe de l'utilisateur.
     *
     * @param mdp mot de passe de l'utilisteur.
     */
    public void setMdp(char[] mdp) {
        this.mdp = mdp;
    }

    /**
     * Met à jour le niveau atteint par l'utilisateur.
     *
     * @param niveau dernier niveau débloqué par l'utilisateur.
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * Met à jour le score total de l'utilisateur.
     *
     * @param score score total de l'utilisateur.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Met à jour la somme que possède l'utilisateur.
     *
     * @param cagnotte somme présente dans la cagnotte de l'utilisateur.
     */
    public void setCagnotte(int cagnotte) {
        this.cagnotte = cagnotte;
    }

}
