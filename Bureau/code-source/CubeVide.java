
import java.awt.Color;

/**
 * <b> CubeVide est une classe héritant de la classe Cube et représentant un
 * cube vide.</b>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class CubeVide extends Cube {

    /**
     * Constructeur CubeVide.
     */
    public CubeVide() {
        super();
    }

    /**
     * Affiche une description du cube.
     *
     * @return une chaine de caractères décrivant le cube.
     */
    public String toString() {
        return "__";
    }

}
