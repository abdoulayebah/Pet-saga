
/**
 * <b> CubeO est une classe héritant de la classe Cube et représentant un
 * obstacle.</b>
 * <p>
 * Les obstacles sont fixes et ne peuvent pas être sélectionnés ni éliminés. Ils
 * sont là pour augmenter le niveau de difficulté du jeu et pousser le joueur à
 * être plus stratégique pour gagner.
 * </p>
 *
 * @author abdbah39
 */
public class CubeO extends Cube {

    /**
     * Constructeur CubeO.
     */
    public CubeO() {
        super();
    }

    /**
     * Affiche une description de l'obstacle.
     *
     * @return une chaine de caractères décrivant l'obstacle.
     */
    public String toString() {
        return "O ";
    }
}
