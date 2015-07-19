
import java.awt.Color;
import java.io.Serializable;

/**
 * <b>Cube est la classe représentant un cube dans la grille du jeu.</b>
 * <p>
 * Il s'agit de la classe principale à partir de laquelle héritent les classes :
 * <ul>
 * <li>CubeVide : cube vide.</li>
 * <li>CubeA: cube représentant les animaux.</li>
 * <li>CubeO: cube représentant les obstacles.</li>
 * <li>CubeC: cube coloré.</li>
 * <li>CubeBomb: cube représentant une bombe.</li>
 * <li>CubeBalloon: cube représentant un ballon.</li>
 * </ul>
 * 
 *
 * @author abdbah39
 */
public class Cube implements Serializable {

    /**
     * Attribut qui prends la valeur <code>true</code> si un des cubes qui
     * héritent de la classe Cube a été sélectionné par l'utilisateur.
     */
    private boolean selection;

    /**
     * Constructeur Cube.
     * <p>
     * A la construction d'un objet Cube, son champ "selection" est initialisé à
     * <code>false</code>.
     * </p>
     *
     * @see Cube#selection
     */
    public Cube() {
        this.selection = false;
    }

    /**
     * Renvoie l'état d'un cube : sélectionné ou pas.
     *
     * @return <code>true</code> si un cube est sélectionné ou
     * <code>false</code> sinon.
     */
    public boolean getSelection() {
        return this.selection;
    }

    /**
     * Permet de rendre un cube sélectionné.
     */
    public void selectionner() {
        this.selection = true;
    }

    /**
     * Permet de rendre un cube non sélectionné.
     */
    public void unSelect() {
        this.selection = false;
    }

    /**
     * Retourne le type d'un cube si c'est un cube représentant un animal ou
     * sinon elle retourne <code>null</code>.
     *
     * @return le type du cube.
     */
    public String getType() {
        return null;
    }

    /**
     * Retourne la couleur d'un cube si c'est un cube représenatant un cube
     * coloré ou un ballon, sinon elle retourne <code>null</code>.
     *
     * @return la couleur du cube.
     */
    public Color getColor() {
        return Color.black;
    }

}
