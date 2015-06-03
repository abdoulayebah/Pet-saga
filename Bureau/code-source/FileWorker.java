
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * <b>FileWorker est une classe qui permet le stockage et la récupération des
 * iformations sur l'utilisateur ainsi que les niveaux du jeu</b>
 *
 * @author tghaou28
 * @author abdbah39
 * @author sgasan32
 */
public class FileWorker {

    /**
     * Sauvegarde la liste des utilisateurs dans un fichier.
     *
     * @param u liste des utilisateurs stckés dans un HashMap.
     * @throws IOException dans le cas où le fichier "users.ser" ne peut pas être
     * crée.
     */
    public static void sauvegarderUtilisateurs(HashMap<String, Utilisateur> u) throws IOException {
        File usr = new File("src/users.ser");
        if (!usr.exists()) {
            usr.createNewFile();
        }

        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(usr));
            oos.writeObject(u);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Permet de récupérer la liste des utilisateurs sous forme d'un HashMap.
     *
     * @return la liste des utilisateurs sous forme d'un Hashmap.
     * @throws Exception dans le cas où on ne peut pas récupérer la liste des
     * utilisateurs à partir du disque dur.
     */
    public static HashMap<String, Utilisateur> recupererUtilisateurs() throws Exception {
        File file = new File("src/users.ser");
        if (!file.exists()) {
            return null;
        }
        HashMap<String, Utilisateur> u;

        FileInputStream in = new FileInputStream(file);
        try (ObjectInputStream inObj = new ObjectInputStream(in)) {
            u = (HashMap<String, Utilisateur>) inObj.readObject();
        }
        return u;
    }

    /**
     * Permet de récupérer l'utilisateur qui a l'identifiant "id" à partir du
     * disque dur.
     *
     * @param id identifiant de l'utilisateur courant du jeu.
     * @return l'utilisateur du jeu dont l'identifiant est "id".
     * @throws Exception dans le cas où on ne peut pas récupérer l'utilisateur
     * qui à l'identifiant "id" à partir du disque dur.
     */
    public static Utilisateur recupererJoueur(String id) throws Exception {
        return recupererUtilisateurs().get(id);
    }

    /**
     * Récupère la liste des niveaux qui sont stockés dans le fichier "lvl.ser"
     * et les stocke dans un Hashmap.
     *
     * @return un Hashmap contenant la liste des niveaux du jeu.
     * @throws Exception dans le cas où on ne peut pas récupérer le fichier à
     * partir du disque dur.
     */
    @SuppressWarnings("empty-statement")
    public static HashMap<Integer, Niveau> uploadLvl() throws Exception {
        File upd = new File("src/update.ser");
        File lvl = new File("src/lvl.ser");
        if (!lvl.exists()) {
            return null;
        }
        HashMap<Integer, Niveau> updates;
        HashMap<Integer, Niveau> levels;
        try (ObjectInputStream inObjLvl = new ObjectInputStream(new FileInputStream(lvl))) {
            levels = (HashMap<Integer, Niveau>) inObjLvl.readObject();
            inObjLvl.close();
        };
        if (upd.exists()) {
            try (ObjectOutputStream outObjLvl = new ObjectOutputStream(new FileOutputStream(lvl))) {
                FileInputStream in = new FileInputStream(upd);
                ObjectInputStream inObj = new ObjectInputStream(in);
                updates = (HashMap<Integer, Niveau>) inObj.readObject();
                inObj.close();
                in.close();
                upd.delete();
                for (Integer i : updates.keySet()) {
                    levels.put(i, updates.get(i));
                }
                outObjLvl.writeObject((Object) levels);
                outObjLvl.close();
            }
        }
        return levels;
    }

    /**
     * Récupère la liste des niveaux qui sont stockés dans le fichier "lvl" et
     * les stocke dans un Hashmap.
     *
     * @param lvl fichier à partir duquel on veut récupérer les niveaux du jeu
     * déjà crées.
     * @return un Hashmap contenant la liste des niveaux du jeu.
     * @throws Exception dans le cas où on ne peut pas récupérer le fichier à
     * partir du disque dur.
     */
    public static HashMap<Integer, Niveau> uploadLvl(File lvl) throws Exception {
        HashMap<Integer, Niveau> levels;
        try (ObjectInputStream inObjLvl = new ObjectInputStream(new FileInputStream(lvl))) {
            levels = (HashMap<Integer, Niveau>) inObjLvl.readObject();
            inObjLvl.close();
        };
        return levels;
    }

}
