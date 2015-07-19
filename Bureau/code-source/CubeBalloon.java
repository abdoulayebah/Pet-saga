
import java.awt.Color;

/**
 * <b> CubeBalloon est une classe héritant de la classe Cube et représentant un
 * ballon.</b>
 * <p>
 * Le clic sur un cube représentant un ballon permet d'éliminer tous les cubes
 * la même couleur que lui se trouvant dans la grille.
 * </p>
 *
 * @author abdbah39
 */
public class CubeBalloon extends Cube {

    /**
     * Couleur du ballon.
     */
    Color c;

    /**
     * Constructeur CubeBallonn.
     *
     * @param c couleur du ballon.
     * @see CubeBalloon#c
     */
    public CubeBalloon(Color c) {
        super();
        this.c = c;
    }

    /**
     * Retourne la couleur du ballon.
     *
     * @return la couleur du ballon.
     */
    public Color getColor() {
        return this.c;
    }

    /**
     * Affiche une description du ballon.
     *
     * @return une chaine de caractères décrivant le ballon.
     */
    public String toString() {
        if (this.c.equals(Color.BLUE)) {
            return "bB";
        }
        if (this.c.equals(Color.RED)) {
            return "bR";
        }
        if (this.c.equals(Color.YELLOW)) {
            return "bJ";
        }
        if (this.c.equals(Color.MAGENTA)) {
            return "bM";
        }
        if (this.c.equals(Color.GREEN)) {
            return "bV";
        }
        return "F ";

    }

}
