
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

/**
 * <b>Test est une classe qui permet d'afficher l'interface graphique du
 * jeu.</b>
 *
 * @author tghaou28
 * @author sgasan32
 * @author abdbah39
 */
public class TestInterfaceG {

    /**
     * Méthode main.
     *
     * @param args paramètre de la méthode main.
     */
    public static void main(String[] args) throws Exception {
        PagePrincipale p = new PagePrincipale();
        p.pack();
        p.setVisible(true);
    }
}
