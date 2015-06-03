
import java.awt.*;
import javax.swing.*;

/**
 * <b>PanelG est une classe dérivant de JPanel qui permet de construire un
 * Jpanel représentant les cubes colorés dans la grille en lui allouant des
 * coordonnés x et y.</b>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class PanelG extends JPanel {

    /**
     * Abcisse du Jpanel.
     */
    private int x;

    /**
     * Ordonnée du Jpanel.
     */
    private int y;

    /**
     * Constructeur PanelG.
     *
     * @param a abcisse du Jpanel.
     * @param b ordonnée du Jpanel.
     */
    public PanelG(int a, int b) {
        super();
        this.x = a;
        this.y = b;
    }

    /**
     * Retourne l'ordonnée du Jpanel.
     *
     * @return l'ordonnée du Jpanel.
     */
    public int get_Colonne() {
        return this.y;
    }

    /**
     * Retourne l'abcisse du JPanel.
     *
     * @return l'abcisse du Jpanel.
     */
    public int get_Ligne() {
        return this.x;
    }

}
