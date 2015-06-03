
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * <b>ImageFond est une classe qui met une image de fond pour la fenêtre de
 * jeu</b>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class ImageFond extends JPanel {

    /**
     * Classe qui étend de la classe image et qui permet la modification de
     * l'image qui va servir comme image de fond de la fenêtre du jeu.
     */
    BufferedImage image;

    /**
     * Constructeur ImageFond.
     *
     * @param x fichier contenant l'image de fond.
     */
    public ImageFond(File x) {

        try {
            image = ImageIO.read(x);
        } catch (IOException ex) {
            System.out.println("Image introuvable");
        }
    }

    /**
     * Permet le dessin de l'image de fond.
     *
     * @param g contexte graphique.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
