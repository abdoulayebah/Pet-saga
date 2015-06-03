
import java.awt.Color;

/**
 * <b> CubeC est une classe héritant de la classe Cube et représentant un cube
 * coloré.</b>
 * <p>
 * Le clic sur un cube coloré induit l'élimination de tous ses voisins de la
 * même couleur que lui.
 * </p>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class CubeC extends Cube {

    /**
     * Couleur du cube.
     */
    Color c;

    /**
     * Constructeur CubeC.
     *
     * @param c couleur du cube.
     */
    public CubeC(Color c) {
        this.c = c;
    }

    /**
     * Retourne la couleur du cube.
     *
     * @return la couleur du cube.
     */
    public Color getColor() {
        return this.c;
    }

    /**
     * Affiche une description du cube coloré.
     *
     * @return une chaine de caractères décrivant le cube coloré.
     */
    public String toString() {
        if (this.c.equals(Color.BLUE)) {
            return "B ";
        }
        if (this.c.equals(Color.RED)) {
            return "R ";
        }
        if (this.c.equals(Color.YELLOW)) {
            return "J ";
        }
        if (this.c.equals(Color.MAGENTA)) {
            return "M ";
        }
        if (this.c.equals(Color.GREEN)) {
            return "V ";
        }
        if (this.c.equals(Color.GREEN.darker())) {
            return "K ";
        }
        return "F ";

    }
}
