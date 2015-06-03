
/**
 * <b> CubeBomb est une classe héritant de la classe Cube et représentant un
 * bombe.</b>
 * <p>
 * Le clic sur un cube représentant une bombe permet d'éliminer les huit cubes
 * autour de lui sauf si il y'a un animal autour de lui. Dans ce cas, il ne
 * l'élimine pas.
 * </p>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class CubeBomb extends Cube {

    /**
     * Constructeur CubeBomb.
     */
    public CubeBomb() {
        super();
    }

    /**
     * Affiche une description de la bombe.
     *
     * @return une chaine de caractères décrivant la bombe.
     */
    public String toString() {
        return "Bo";
    }

}
