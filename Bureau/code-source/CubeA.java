
/**
 * <b> CubeA est une classe héritant de la classe Cube et représentant un
 * animal.</b>
 * <p>
 * Le clic sur un cube représentant un animal ne modifie rien dans le jeu mais
 * le sauvetage des animaux par l'élimination des cubes qui se trouvent au
 * dessous d'eux permet d'augmenter le score de l'utilsateur et de débloquer le
 * niveau suivant dans le jeu.
 * </p>
 *
 * @author abdbah39
 */
public class CubeA extends Cube {

    /**
     * Race de l'animal.
     */
    private String race;

    /**
     * Constructeur CubeA.
     *
     * @param a race de l'animal crée.
     * @see CubeA#race
     */
    public CubeA(String a) {
        super();
        this.race = a;
    }

    /**
     * Retourne la race de l'animal.
     *
     * @return la race de l'animal.
     */
    public String getType() {
        return this.race;
    }

    /**
     * Affiche une description de l'animal.
     *
     * @return une chaine de caractères décrivant l'animal.
     */
    public String toString() {
        return this.race + " ";
    }
}
