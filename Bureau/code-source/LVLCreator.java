
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * <b>LVLCreator est une classe qui permet la création et le stockage des
 * niveaux du jeu.</b>
 *
 * @author abdbah39
 */
public class LVLCreator {

    /**
     * Méthode main.
     *
     * @param args paramètre de la méthode main.
     * @throws Exception dans le cas où on ne peut pas enregistrer les niveaux
     * dans le dique dur.
     */
    public static void main(String[] args) throws Exception {

        Cube[] niveau1 = {
            new CubeVide(), new CubeA("a"), new CubeVide(), new CubeVide(), new CubeVide(), new CubeA("a"), new CubeVide(),
            new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.YELLOW), new CubeC(Color.YELLOW),
            new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.YELLOW), new CubeC(Color.YELLOW),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.GREEN.darker()), new CubeC(Color.GREEN.darker()),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.GREEN.darker()), new CubeC(Color.GREEN.darker()),
            new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeC(Color.YELLOW), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA),
            new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeC(Color.YELLOW), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA),};

        Cube[] niveau2 = {
            new CubeVide(), new CubeC(Color.MAGENTA), new CubeA("a"), new CubeC(Color.MAGENTA), new CubeVide(),
            new CubeA("a"), new CubeC(Color.MAGENTA), new CubeA("a"), new CubeC(Color.MAGENTA), new CubeA("a"),
            new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeA("a"), new CubeC(Color.YELLOW), new CubeC(Color.BLUE),
            new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeC(Color.BLUE),
            new CubeC(Color.YELLOW), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeC(Color.YELLOW),
            new CubeC(Color.YELLOW), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeC(Color.YELLOW),
            new CubeC(Color.MAGENTA), new CubeC(Color.YELLOW), new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeC(Color.MAGENTA),
            new CubeC(Color.MAGENTA), new CubeC(Color.YELLOW), new CubeC(Color.BLUE), new CubeC(Color.YELLOW), new CubeC(Color.MAGENTA),};

        Cube[] niveau3 = {
            new CubeVide(), new CubeVide(), new CubeA("a"), new CubeVide(), new CubeA("a"), new CubeVide(), new CubeA("a"),
            new CubeVide(), new CubeVide(), new CubeC(Color.GREEN.darker()), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA),
            new CubeVide(), new CubeVide(), new CubeC(Color.GREEN.darker()), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA),
            new CubeVide(), new CubeVide(), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.RED), new CubeC(Color.GREEN.darker()),
            new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.RED), new CubeC(Color.GREEN.darker()),
            new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA),
            new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA),
            new CubeC(Color.RED), new CubeC(Color.GREEN.darker()), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeO(), new CubeO(),
            new CubeC(Color.GREEN.darker()), new CubeC(Color.GREEN.darker()), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeO(), new CubeO()};

        Cube[] niveau4 = {
            new CubeVide(), new CubeVide(), new CubeVide(), new CubeVide(), new CubeA("a"), new CubeA("a"), new CubeA("a"), new CubeA("a"),
            new CubeVide(), new CubeVide(), new CubeVide(), new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.RED),
            new CubeVide(), new CubeVide(), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.RED),
            new CubeVide(), new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA),
            new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeO(),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeO(), new CubeO(),
            new CubeC(Color.RED), new CubeC(Color.RED), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeC(Color.BLUE), new CubeO(), new CubeO(), new CubeO(),
            new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeO(), new CubeO(), new CubeO(), new CubeO()};

        Cube[] niveau5 = {
            new CubeVide(), new CubeA("a"), new CubeA("a"), new CubeVide(), new CubeVide(),
            new CubeVide(), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA), new CubeVide(), new CubeVide(),
            new CubeVide(), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeVide(), new CubeVide(),
            new CubeVide(), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA), new CubeVide(), new CubeVide(),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA),
            new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA), new CubeC(Color.MAGENTA)};

        Cube[] niveau6 = {
            new CubeA("a"), new CubeVide(), new CubeBomb(), new CubeC(Color.BLUE), new CubeVide(), new CubeA("a"),
            new CubeBalloon(Color.BLUE), new CubeC(Color.BLUE), new CubeBomb(), new CubeBomb(), new CubeC(Color.RED), new CubeBalloon(Color.MAGENTA),
            new CubeC(Color.RED), new CubeC(Color.GREEN.darker()), new CubeC(Color.BLUE), new CubeC(Color.BLUE), new CubeC(Color.RED), new CubeC(Color.BLUE),
            new CubeC(Color.RED), new CubeC(Color.GREEN.darker()), new CubeA("a"), new CubeA("a"), new CubeC(Color.GREEN.darker()), new CubeC(Color.BLUE),
            new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA), new CubeC(Color.BLUE), new CubeC(Color.MAGENTA), new CubeC(Color.RED),
            new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker()), new CubeC(Color.MAGENTA),
            new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.RED), new CubeC(Color.MAGENTA), new CubeC(Color.GREEN.darker())};

        Niveau niv1 = new Niveau(2, 10, 7, 7, niveau1);
        Niveau niv2 = new Niveau(5, 10, 8, 5, niveau2);
        Niveau niv3 = new Niveau(3, 20, 9, 7, niveau3);
        Niveau niv4 = new Niveau(4, 20, 8, 8, niveau4);
        Niveau niv5 = new Niveau(2, 20, 9, 5, niveau5);
        Niveau niv6 = new Niveau(4, 20, 7, 6, niveau6);

        HashMap<Integer, Niveau> levels = new HashMap<Integer, Niveau>();
        levels.put(1, niv1);
        levels.put(2, niv2);
        levels.put(3, niv3);
        levels.put(4, niv4);
        levels.put(5, niv5);
        levels.put(6, niv6);

        File lvl = new File("src/lvl.ser");
        ObjectOutputStream outObjLvl = new ObjectOutputStream(new FileOutputStream(lvl));
        outObjLvl.writeObject((Object) levels);
        outObjLvl.close();

        ObjectInputStream inObjLvl = new ObjectInputStream(new FileInputStream(lvl));
        HashMap<Integer, Niveau> test = (HashMap<Integer, Niveau>) inObjLvl.readObject();
        inObjLvl.close();
        for (Integer i : test.keySet()) {
            System.out.println(test.get(i));
        }
    }

}
