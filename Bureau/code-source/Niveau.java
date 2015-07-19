
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <b>Niveau est la classe qui contient toutes les informations relatives à un
 * niveau donné du jeu.</b>
 * <p>
 * Un niveau est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un numéro.</li>
 * <li>Un score à atteindre pour gagner le niveau.</li>
 * <li>Un nombre de déplacements limité ou pas en fonction du niveau.</li>
 * <li>Un nombre d'animaux qui change d'un niveau à un autre.</li>
 * <li>Des jokers qui sont: le marteau et les fusées (horizontales et
 * verticales)</li>
 * <li>Une grille de cubes.</li>
 * </ul>
 *
 * <p>
 * La suppression des cubes fait augmenter la cagnotte et le sauvetage des
 * animaux fait augmenter le score. De plus, le déblocage des jokers nécessite
 * un nombre de points fixés.
 *
 *
 * @author abdbah39
 */
public class Niveau implements Serializable {

    /**
     * Niveau courant.
     */
    private int niveau;

    /**
     * Points obtenus lors du sauvetage d'un animal.
     */
    private static final int rescudedAnimalPoint = 100;

    /**
     * Points obtenus lors de la suppression d'un cube.
     */
    private static final int deletedCubePoint = 50;

    /**
     * nombre de retour autorisée pour un niveau
     */
    private int nbRetourAutorise = 3;

    /**
     * Score déjà obtenu.
     */
    private int scoreNiveau;

    /**
     * Nombre de déplacements autorisés.
     */
    private int nbDeplacement;

    /**
     * Nombre d'animaux sauvés.
     */
    private int nbAnimaux;

    /**
     * Nombre total d'animaux dans le niveau courant.
     */
    private int nbAnimauxTotal;

    /**
     * Somme présente dans la cagnotte de l'utilisateur.
     */
    private int cagnotte;

    /**
     * cagnotte disponible pour les rockets *
     */
    private int cagnotteR = 0;

    /**
     * cagnotte diponible pour les marteau *
     */
    private int cagnotteH = 0;

    /**
     * Nombre de fusées débloquées.
     */
    private int unlockedRockets = 0;

    /**
     * Nombre de marteaux débloqués.
     */
    private int unlockedHammer = 0;

    /**
     * Grille de jeu.
     */
    Cube[][] grille;

    /**
     * Constructeur niveau.
     *
     * @param nbA nombre d'animaux sauvés.
     * @param nbDeplacement nombre de déplacements autorisés.
     * @param hauteur hauteur de la grille.
     * @param largeur largeur de la grille.
     * @param c grille du jeu.
     */
    public Niveau(int nbA, int nbDeplacement, int hauteur, int largeur, Cube[] c) {
        grille = new Cube[hauteur][largeur];
        int k = 0;
        this.scoreNiveau = 0;
        this.cagnotte = 0;
        this.nbAnimaux = 0;
        this.nbDeplacement = nbDeplacement;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                this.grille[i][j] = c[k];
                k++;
            }
        }
        this.nbAnimauxTotal = nbA;
    }

    /**
     * Constructeur niveau utiliser uniquement pour mode administrateur.
     *
     * @param nbA nombre d'animaux sauvés.
     * @param nbDeplacement nombre de déplacements autorisés.
     * @param hauteur hauteur de la grille.
     * @param largeur largeur de la grille.
     * @param c grille du jeu.
     */
    public Niveau(int nbA, int nbDeplacement, int hauteur, int largeur, Cube[][] c) {
        grille = new Cube[hauteur][largeur];
        this.scoreNiveau = 0;
        this.cagnotte = 0;
        this.nbAnimaux = 0;
        this.nbDeplacement = nbDeplacement;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                this.grille[i][j] = c[i][j];
            }
        }
        this.nbAnimauxTotal = nbA;
    }

    /**
     * Constructeur Niveau utiliser uniquement pour carder l'etat actuel du
     * niveau dans le but de faire un retour en arrière.
     *
     * @param hauteur hauteur de la grille.
     * @param largeur largeur de la grille.
     * @param cagnotte omme d'argent obtenu par l'utilisateur dans le niveau
     * courant.
     * @param cagnotteR cagnotte pour débloquer la fusée.
     * @param cagnotteH cagnotte pour débloquer les marteaux.
     * @param scoreNiveau score obtenu par l'utilisateur dans le niveau courant.
     * @param nbAnimaux nombre d'animaux sauvés.
     * @param nbD nombre de déplacements autorisés.
     * @param unlockedRockets nombre de fusées débloqués.
     * @param unlockedHammer nombre de marteaux débloqués.
     * @param c grille du jeu.
     */
    public Niveau(int hauteur, int largeur, int cagnotte, int cagnotteR, int cagnotteH, int scoreNiveau, int nbAnimaux, int nbD,
            int unlockedRockets, int unlockedHammer, Cube[][] c) {
        grille = new Cube[hauteur][largeur];
        this.scoreNiveau = scoreNiveau;
        this.cagnotte = cagnotte;
        this.cagnotteR = cagnotteR;
        this.cagnotteH = cagnotteH;
        this.nbAnimaux = nbAnimaux;
        this.nbDeplacement = nbD;
        this.unlockedRockets = unlockedRockets;
        this.unlockedHammer = unlockedHammer;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                this.grille[i][j] = c[i][j];
            }
        }
    }

    /**
     * Retourne le nombre de déplacments autorisés dans le niveau courant.
     *
     * @return le nombre de déplacements autorisés.
     */
    public int getNbDeplacements() {
        return this.nbDeplacement;
    }

    /**
     * Retourne le nombre de fusée dans le niveau courant.
     *
     * @return le nombre de fusées disponibles.
     */
    public int getRockets() {
        return this.unlockedRockets;
    }

    /**
     * Retourne le nombre de fusée dans le niveau courant.
     *
     * @return le nombre de marteaux disponibles.
     */
    public int getHammer() {
        return this.unlockedHammer;
    }

    /**
     * Retourne la hauteur de la grille de jeu.
     *
     * @return la hauteur de la grille de jeu.
     */
    public int getHauteur() {
        return this.grille.length;

    }

    /**
     * Retourne la largeur de la grille de jeu.
     *
     * @return Retourne la largeur de la grille de jeu.
     */
    public int getLargeur() {
        return this.grille[0].length;
    }

    /**
     * Teste si un cube est sélectionné ou pas.
     *
     * @param x abcisse du cube.
     * @param y ordonnée du cube.
     * @return <code>true</code> si le cube est sélectionné ou
     * <code>false</code> sinon.
     */
    public boolean isSelected(int x, int y) {
        return grille[x][y].getSelection();

    }

    /**
     * Rend un cube sélectionné.
     *
     * @param x abcisse du cube.
     * @param y ordonnée du cube.
     */
    public void selected(int x, int y) {
        grille[x][y].selectionner();
    }

    /**
     *
     *
     * @param y indice de
     * @return true ou false en fonction de l'appartenance de y dans largeur.
     */
    public boolean estDansLargeur(int y) {
        return (y >= 0 && y < this.getLargeur());
    }

    /**
     *
     *
     * @param x
     * @return true ou false en fonction de l'appartenance de x dans la hauteur
     */
    public boolean estDansHauteur(int x) {
        return (x >= 0 && x < this.getHauteur());
    }

    /**
     *
     *
     * @param x
     * @param y
     * @return true ou false en fonction de l'appartenance de x et y dans le
     * niveau
     */
    public boolean estDansNiveau(int x, int y) {
        return (x >= 0 && x < this.getHauteur() && y >= 0 && y < this.getLargeur());
    }

    /**
     *
     *
     * @param x
     * @param y
     * @param c
     * @return
     */
    public boolean isSameColor(int x, int y, Color c) {
        if (estDansNiveau(x, y)) {
            return grille[x][y].getColor().equals(c);
        }
        return false;
    }

    /**
     * Retourne le score obtenu par l'utilisateur dans le niveau courant.
     *
     * @return le score obtenu par l'utilisateur dans le niveau courant.
     */
    public int getScoreNiveau() {
        return this.scoreNiveau;
    }

    /**
     *
     *
     * @param score score obtenu par l'utilisateur dans le niveau courant.
     */
    public void setScoreNiveau(int score) {
        this.scoreNiveau = score;
    }

    /**
     * Retourne la somme d'argent obtenu par l'utilisateur dans le niveau
     * courant.
     *
     * @return la somme d'argent obtenu par l'utilisateur dans le niveau
     * courant.
     */
    public int getCagnotte() {
        return this.cagnotte;
    }

    /**
     * Augmente le nombre d'animaux sauvés.
     */
    public void animalSauve() {
        nbAnimaux++;
    }

    /**
     * Dimunier le nombre de deplacement.
     */
    public void reduceNbDeplacement() {
        this.nbDeplacement--;
    }

    /**
     * Augmente le score de l'utilisateur.
     */
    public void augmenterScore() {
        this.scoreNiveau += this.rescudedAnimalPoint;
    }

    /**
     * `
     *
     * @param nbCubes nombre de cubes éliminés dans le niveau courant.
     */
    public void setCagnotte(int nbCubes) {
        int n = this.deletedCubePoint * nbCubes;
        this.cagnotteR += n / 2;
        this.cagnotteH += n / 2;
        this.cagnotte = this.cagnotteH + this.cagnotteR;
    }

    /**
     * Retourne le nombre d'animaux sauvés.
     *
     * @return le nombre d'animaux sauvés.
     */
    public int getNbAnimaux() {
        return this.nbAnimaux;
    }

    /**
     * Retourne le nombre total d'animaux présents dans le niveau courant.
     *
     * @return le nombre total d'animaux présents dans le niveau courant.
     */
    public int getNbAnimauxTotal() {
        return this.nbAnimauxTotal;
    }

    /**
     * Teste si tous les animaux présents dans le niveau courant sont sauvés.
     *
     * @return <code>true</code> si le nombre d'animaux sauvés est égal au
     * nombre total d'animaux présents dans le niveau ou <code>false</code>
     * sinon.
     */
    public boolean tousSauve() {
        return nbAnimaux == nbAnimauxTotal;
    }

    /**
     * Retourne le nombre de retours disponibles dans le niveau.
     * 
     * @return le nombre de retours disponibles dans le niveau.
     */
    public int getNbRetourAutorise() {
        return this.nbRetourAutorise;
    }

    /**
     * Diminue le nombre de retours disponibles dans le niveau.
     */
    public void useNbRetour() {
        this.nbRetourAutorise--;
    }

    /**
     * indique si on peut faire un retour ou pas *
     */
    public boolean retourIsAvailable() {
        return this.nbRetourAutorise > 0;
    }

    /**
     * debloque les fusees
     */
    public void unlockedRockets() {
        this.unlockedRockets = this.cagnotteR / 500;
    }

    /**
     * debloque les marteau
     */
    public void unlockedHammer() {
        this.unlockedHammer = this.cagnotteH / 400;
    }

    /**
     * Teste si l'utilisateur peut utiliser une fusée.
     *
     * @return <code>true</code> si il y'a des fusées disponibles ou
     * <code>false</code> sinon.
     */
    public boolean rocketIsAvailable() {
        return this.unlockedRockets > 0;
    }

    /**
     * Teste si l'utilisateur peut utiliser un marteau.
     *
     * @return <code>true</code> si il y'a des marteaux disponibles ou
     * <code>false</code> sinon.
     */
    public boolean hammerIsAvailable() {
        return this.unlockedHammer > 0;
    }

    /**
     * consomme les fusée
     */
    public void useRockets() {
        this.unlockedRockets--;
        this.cagnotteR -= 500;
    }

    /**
     * consomme les marteau
     */
    public void useHammer() {
        this.unlockedHammer--;
        this.cagnotteH -= 400;

    }

    /**
     * Affiche l'état du niveau courant.
     *
     * @return une chaine de caractères décrivant l'état du niveau courant.
     */
    public String toString() {
        return "Scores: " + this.scoreNiveau + "\n" + "Cagnotte: " + this.cagnotte + "\n" + "cagnotte fusée " + this.cagnotteR + "\n" + "cagnotte marteau " + this.cagnotteH + "\n"
                + "Rockets: " + this.unlockedRockets + "\n"
                + "Hammer: " + this.unlockedHammer + "\n"
                + "nb Animaux: " + nbAnimauxTotal + "\n"
                + "nb Deplacement restant: " + this.nbDeplacement + "\n"
                + "nb Retour disponible: " + this.nbRetourAutorise;

    }

    /**
     * Teste si une colonne d'ordonnée j dans la grille est vide.
     *
     * @param j ordonnée de la colonne.
     * @return <code>true</code> si un colonne dans la grille est vide ou
     * <code>false</code> sinon.
     */
    public boolean colonneEstVide(int j) {
        for (int i = 0; i < this.getHauteur(); i++) {
            if (isCube(i, j) || isAnimal(i, j) || isBall(i, j) || isBomb(i, j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Teste si la lingne d'abscisse i dans la grille est vide.
     *
     * @param i abscisse de la ligne.
     * @return <code>true</code> si la ligne dans la grille est vide ou
     * <code>false</code> sinon.
     */
    public boolean ligneEstVide(int i) {
        for (int j = 0; j < this.getLargeur(); i++) {
            if (isCube(i, j) || isAnimal(i, j) || isBall(i, j) || isBomb(i, j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Teste si une case de coordonnées i et j est vide dans la grille.
     *
     * @param i abcisse de la case.
     * @param j ordonnée de la case.
     * @return <code>true</code> si une case est vise ou <code>false</code>
     * sinon.
     */
    public boolean caseEstVide(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j] instanceof CubeVide;
        }
        return false;
    }

    /**
     * Teste si un cube représente un animal.
     *
     * @param i abcisse du cube.
     * @param j ordonnée du cube.
     * @return <code>true</code> si un cube représente un animal ou
     * <code>false</code> sinon.
     */
    public boolean isAnimal(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j] instanceof CubeA;
        }
        return false;
    }

    /**
     * Teste si un cube représente un obstacle.
     *
     * @param i abcisse du cube.
     * @param j ordonnée du cube.
     * @return <code>true</code> si un cube représente un obstacle ou
     * <code>false</code> sinon.
     */
    public boolean isObstacle(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j] instanceof CubeO;
        }
        return false;
    }

    /**
     * Teste si un cube représente un cube coloré.
     *
     * @param i abcisse du cube.
     * @param j ordonnée du cube.
     * @return <code>true</code> si un cube représente un cube coloré ou
     * <code>false</code> sinon.
     */
    public boolean isCube(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j] instanceof CubeC;
        }
        return false;
    }

    /**
     * Teste si un cube représente une bombe.
     *
     * @param i abcisse du cube.
     * @param j ordonnée du cube.
     * @return <code>true</code> si un cube représente une bombe ou
     * <code>false</code> sinon.
     */
    public boolean isBomb(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j] instanceof CubeBomb;
        }
        return false;

    }

    /**
     * Teste si un cube représente un ballon.
     *
     * @param i abcisse du cube.
     * @param j ordonnée du cube.
     * @return <code>true</code> si un cube représente un ballon ou
     * <code>false</code> sinon.
     */
    public boolean isBall(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j] instanceof CubeBalloon;
        }
        return false;

    }

    /**
     * Retourne la couleur d'un cube de coordonnées i et j.
     *
     * @param i abcisse du cube.
     * @param j ordonnée du cube.
     * @return la couleur du cube de coordonnées i et j.
     */
    public Color color(int i, int j) {
        if (estDansNiveau(i, j)) {
            return grille[i][j].getColor();
        }
        return null;

    }

    /**
     * Supprime un cube.
     *
     * @param i abcisse du cube à supprimer.
     * @param j ordonnée du cube à supprimer.
     */
    public void supprimerCube(int i, int j) {
        if (estDansNiveau(i, j)) {
            grille[i][j] = new CubeVide();
        }
    }

    /**
     * Retourne le nombre maximum du cube qu'on peut supprimer avec un click.
     *
     *
     * @return un tableau contenant l'abscisse et l'ordonnée du cube ainsi que
     * le nombre d'animaux qu'on peut supprimer.
     */
    public int[] optimalClick() {
        int[] coordonees = {-1, -1, 0};
        refresh_selected();
        Color c = color(0, 0);
        int max = selectedCubes(0, 0, c);
        if (max > 0) {
            coordonees[0] = 0;
        }
        coordonees[1] = 0;
        coordonees[2] = max;
        int provisoirMax = 0;
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (!isSelected(i, j) && isCube(i, j)) {
                    c = color(i, j);
                    provisoirMax = selectedCubes(i, j, c);
                    if (provisoirMax > max) {
                        max = provisoirMax;
                        coordonees[0] = i;
                        coordonees[1] = j;
                        coordonees[2] = max;
                    }
                }
            }
        }
        refresh_selected();
        return coordonees;
    }

    /**
     * selection les cubes ciblés par une bombe.
     *
     * @param x abscisse du cube.
     * @param y du cube.
     * @return le nombre d'animaux dans une colonne d'ordonnée j.
     */
    private int target_cubes_by_bomb(int x, int y) {
        int nbCubes = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isBomb(i, j) && !isSelected(i, j)) {
                    selected(i, j);
                    nbCubes++;
                    nbCubes += target_cubes_by_bomb(i, j);
                }
                if (isCube(i, j)) {
                    if (!isSelected(i, j)) {
                        nbCubes++;
                    }
                    selected(i, j);
                }
                if (isBall(x, y)) {
                    Color c = color(x, y);
                    nbCubes++;
                    nbCubes += target_cubes_by_ball(i, j, c);
                }
            }
        }

        // refresh_cubes();
        return nbCubes;

    }

    /**
     * selection les cubes ciblés par un ballon.
     *
     * @param x abscisse du cube.
     * @param y du cube.
     * @return le nombre d'animaux dans une colonne d'ordonnée j.
     */
    private int target_cubes_by_ball(int x, int y, Color c) {
        int nbCubes = 0;
        for (int i = 0; i < getHauteur(); i++) {
            for (int j = 0; j < getLargeur(); j++) {
                if (isSameColor(i, j, c) && isCube(i, j) && !isSelected(i, j)) {
                    nbCubes++;
                    selected(i, j);
                }
                if (isSameColor(i, j, c) && isBall(i, j) && !isSelected(i, j)) {
                    nbCubes++;
                    selected(i, j);
                }
            }
        }
        return nbCubes;
    }

    /**
     * Retourne le nombre maximum du cube qu'on peut supprimer avec une bombe.
     *
     *
     * @return un tableau contenant l'abscisse et l'ordonnée du cube ainsi que
     * le nombre d'animaux qu'on peut supprimer.
     */
    private int[] optimalBomb() {
        int[] coordonees = {-1, -1, 0};
        refresh_selected();
        int max = 0;
        int provisoirMax = 0;
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (!isSelected(i, j) && isBomb(i, j)) {
                    provisoirMax = target_cubes_by_bomb(i, j);
                    if (provisoirMax > max) {
                        max = provisoirMax;
                        coordonees[0] = i;
                        coordonees[1] = j;
                        coordonees[2] = max;
                    }
                }
            }
        }
        refresh_selected();
        return coordonees;
    }

    /**
     * Retourne le nombre maximum du cube qu'on peut supprimer avec un ballon.
     *
     * @return un tableau contenant l'abscisse et l'ordonnée du cube ainsi que
     * le nombre d'animaux qu'on peut supprimer.
     */
    private int[] optimalBall() {
        int[] coordonees = {-1, -1, 0};
        refresh_selected();
        int max = 0;
        int provisoirMax = 0;
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (isBall(i, j)) {
                    Color c = color(i, j);
                    provisoirMax = target_cubes_by_ball(i, j, c);
                    if (provisoirMax > max) {
                        max = provisoirMax;
                        coordonees[0] = i;
                        coordonees[1] = j;
                        coordonees[2] = max;
                    }
                }
            }
        }
        refresh_selected();
        return coordonees;
    }

    /**
     * Retourne le nombre maximum du cube qu'on peut supprimer avec un click,
     * une bombe et un ballon.
     *
     * @return un tableau contenant l'abscisse et l'ordonnée du cube ainsi que
     * le nombre d'animaux qu'on peut supprimer.
     */
    /**
     *
     * @param click
     * @param bomb
     * @param ball
     * @return un tableau contenant le max des coups possibles
     */
    private int[] max(int[] click, int[] bomb, int[] ball) {
        if (click[2] > bomb[2] && click[2] > ball[2]) {
            return click;
        } else if (bomb[2] > click[2] && bomb[2] > ball[2]) {
            return bomb;
        }
        return ball;
    }

    /**
     *
     * @return un tableau contenant les coordonnées du cube à cliquer puis le
     * nombre de cube qu'on peut supprimer
     */
    public int[] help() {
        int[] click = optimalClick();
        int[] bomb = optimalBomb();
        int[] ball = optimalBall();
        int[] optimalShoot = max(click, bomb, ball);
        if (optimalShoot[2] == 1) {
            System.out.print("utiliser un fusée");
            if (this.rocketIsAvailable()) {
                optimalShoot[2] = -1;
                return optimalShoot;
            } else if (this.hammerIsAvailable()) {
                optimalShoot[2] = -2;
                return optimalShoot;
            } else {
                optimalShoot[2] = -3;
                return optimalShoot;
            }

        }

        System.out.println("cliquer sur l'element (" + optimalShoot[0] + ";" + optimalShoot[1] + ";" + optimalShoot[2] + ")");
        return optimalShoot;

    }

    /**
     * Permet l'affichage de l'interface texte.
     */
    public void afficherGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                System.out.print(grille[i][j]);
                System.out.print('|');
            }
            System.out.println();
        }
        // details sur le parcours
        System.out.println(this);
        System.out.println();

    }

    /**
     *
     *
     * @return <code>true</code> si le déplacement vertical a été effectué ou
     * <code>false</code> sinon.
     */
    public boolean deplacementVertical() {
        boolean f = false;
        boolean flag = false;
        for (int j = 0; j < this.getLargeur(); j++) {
            f = deplacementVertical(j);
            if (f) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     *
     * @param j indice de la colonne.
     * @return <code>true</code> si le déplacement vertical a été effectué ou
     * <code>false</code> sinon.
     */
    public boolean deplacementVertical(int j) {
        boolean flag = true;
        boolean f = false;
        while (flag) {
            flag = false;
            for (int i = 0; i < this.getHauteur() - 1; i++) {
                if (!caseEstVide(i, j)
                        && !(grille[i][j] instanceof CubeO)) {
                    if (caseEstVide(i + 1, j)) {
                        grille[i + 1][j] = grille[i][j];
                        supprimerCube(i, j);
                        flag = true;
                        if (!f) {
                            f = true;
                        }
                    }
                }
            }
        }
        return f;
    }

    /**
     *
     * @return <code>true</code> si le déplacement horizontal a été effectué ou
     * <code>false</code> sinon.
     */
    public boolean deplacementHorizontal() {
        int i = this.getHauteur() - 1;
        int j = 1;
        boolean flag = false;

        for (; j < this.getLargeur(); j++) {

            if (!colonneEstVide(j)) {

                if (caseEstVide(this.getHauteur() - 1, j - 1)) {
                    for (; i >= 0; i--) {
                        if (caseEstVide(i, j - 1) && (isAnimal(i, j) || isCube(i, j) || isBall(i, j) || isBomb(i, j))) {
                            grille[i][j - 1] = grille[i][j];
                            supprimerCube(i, j);
                            flag = true;
                        }
                        if (isObstacle(i, j - 1) || isObstacle(i, j)) {
                            break;
                        }

                    }
                }
                for (; i >= 0; i--) {
                    if (isObstacle(i, j) && (caseEstVide(i, j - 1) || isObstacle(i, j - 1)) && caseEstVide(i - 1, j - 1)) {
                        i--;
                        for (; i >= 0; i--) {
                            if (caseEstVide(i, j - 1) && (isAnimal(i, j) || isCube(i, j) || isBall(i, j) || isBomb(i, j))) {
                                grille[i][j - 1] = grille[i][j];
                                supprimerCube(i, j);
                                flag = true;
                            }

                            if (isObstacle(i, j - 1)) {
                                break;
                            }
                            if (isObstacle(i, j)) {
                                i++;
                                break;
                            }

                        }
                    }

                }

            }
            if (this.deplacementVertical(j - 1) || this.deplacementVertical(j)) {
                j -= 1;
            }
            ;

            i = (this.getHauteur() - 1);
        }
        return flag;
    }

    /**
     * Permet de sauver tous les animaux du niveau courant.
     *
     * @return <code>true</code> s'il ne reste aucun animal dans la grille de
     * jeu ou <code>false</code> sinon.
     */
    public boolean sauverAnimaux() {
        boolean flag = false;
        for (int i = 0; i < this.getLargeur(); i++) {
            if (grille[this.getHauteur() - 1][i] instanceof CubeA) {
                for (int j = this.getHauteur() - 1; j >= 0; j--) {
                    if (grille[j][i] instanceof CubeA) {
                        supprimerCube(j, i);
                        animalSauve();
                        augmenterScore();
                        flag = true;
                    } else {
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Permet de remettre tous les cubes restants à leur place après chaque coup
     * de l'utilisateur.
     */
    public void reorganiserGrille() {
        boolean v = true;
        boolean h = true;
        boolean a = true;
        while (v || h || a) {
            v = deplacementVertical();
            if (v) {
                System.out.println("après deplacement verticlal");
                afficherGrille();
            }
            a = sauverAnimaux();
            if (a) {
                System.out.println("après le sauvetage des Animaux");
                afficherGrille();
                if (tousSauve()) {
                    v = h = a = false;
                    System.out.println("Tous les Animaux sont sauve");
                }
            }
            h = deplacementHorizontal();
            if (h) {
                System.out.println("après deplacement horizontal");
                afficherGrille();
            }
        }

    }

    /**
     * Retourne le nombre de cubes selectionnés.
     *
     * @param x abcisse du cube
     * @param y ordonnée du cube.
     * @param c couleur du cube.
     * @return le nombre de cubes selectionnés.
     */
    public int selectedCubes(int x, int y, Color c) {
        if (!estDansNiveau(x, y) || caseEstVide(x, y) || !isCube(x, y) || !isSameColor(x, y, c) || isSelected(x, y)) {
            return 0;
        } else {
            selected(x, y);
            return 1 + selectedCubes(x + 1, y, c) + selectedCubes(x - 1, y, c) + selectedCubes(x, y + 1, c) + selectedCubes(x, y - 1, c);
        }
    }

    /**
     * deselectionne toutes les cubes selectionner dans la grille
     */
    public void refresh_selected() {
        for (int i = 0; i < this.getHauteur(); i++) {
            for (int j = 0; j < this.getLargeur(); j++) {
                this.grille[i][j].unSelect();
            }
        }
    }

    /**
     * Return le nombre de cubes selectionnés dans la grille.
     *
     * @param x abcisse du cube.
     * @param y ordonnée du cube.
     * @return le nombre de cubes selectionnés dans la grille.
     */
    public int selectedCubes(int x, int y) {
        int nbCub = 0;
        refresh_selected();
        Color c = color(x, y);
        nbCub += selectedCubes(x + 1, y, c);
        nbCub += selectedCubes(x - 1, y, c);
        nbCub += selectedCubes(x, y + 1, c);
        nbCub += selectedCubes(x, y - 1, c);
        return nbCub;
    }

    /**
     * supprimer tous les elemetns selectionner de la grille
     */
    public void delete() {
        for (int i = 0; i < this.getHauteur(); i++) {
            for (int j = 0; j < this.getLargeur(); j++) {
                if (isSelected(i, j)) {
                    supprimerCube(i, j);
                }
            }
        }
    }

    /**
     * Supprime le cube de coordonnées x et y
     *
     * @param x abcisse du cube.
     * @param y ordonnée du cube.
     * @return le nombre de cubes supprimés (éventuellement 1).
     */
    public int supprimer(int x, int y) {
        int nbCubes = selectedCubes(x, y);
        if (nbCubes > 1) {
            delete();
        }
        return nbCubes;
    }

    /**
     * Permer l'utilisation d'une fusée four éliminer tous les cubes qui se
     * trouvent dans le colonne d'indice y dans la grille.
     *
     * @param y indice de la colonne sur laquelle on souhaite utiliser une
     * fusée.
     * @return le nombre de cubes supprimés par une fusée verticale.
     */
    public int rocket(int y) {
        int nbCubes = 0;
        if (estDansLargeur(y) && !colonneEstVide(y)) {
            for (int i = 0; i < this.getHauteur(); i++) {
                if (isCube(i, y)) {
                    supprimerCube(i, y);
                    nbCubes++;
                }
                if (isBomb(i, y)) {
                    nbCubes += bombs(i, y);
                }
                if (isBall(i, y)) {
                    nbCubes += balloon(i, y);
                }

            }
        }
        return nbCubes;

    }

    /**
     * Permer l'utilisation d'une fusée four éliminer tous les cubes qui se
     * trouvent dans la ligne d'indice x dans la grille.
     *
     * @param x indice de la ligne sur laquelle on souhaite utiliser une fusée.
     * @return le nombre de cubes supprimés par une fusée horizontale.
     */
    public int rocketX(int x) {
        int nbCubes = 0;
        if (estDansHauteur(x) && !ligneEstVide(x)) {
            for (int i = 0; i < this.getLargeur(); i++) {
                if (isCube(x, i)) {
                    supprimerCube(x, i);
                    nbCubes++;
                }
                if (isBomb(x, i)) {
                    nbCubes += bombs(x, i);
                }
                if (isBall(x, i)) {
                    nbCubes += balloon(x, i);
                }

            }
        }
        return nbCubes;

    }

    /**
     * Permet l'utilisation d'un marteau pour éliminer un cube dans la grille.
     *
     * @param x abcisse de la colonne sur laquelle on souhaite utiliser un
     * marteau.
     * @param y ordonnée de la colonne sur laquelle on souhaite utiliser un
     * marteau.
     * @return le nombre de cubes supprimés par un marteau.
     */
    public int hammer(int x, int y) {
        int nbCubes = 0;
        if (estDansNiveau(x, y)) {
            if (isCube(x, y)) {
                supprimerCube(x, y);
                nbCubes += 1;
            }
            if (isBomb(x, y)) {
                nbCubes += bombs(x, y);
            }
            if (isBall(x, y)) {
                nbCubes += balloon(x, y);
            }
        }
        return nbCubes;
    }

    /**
     * Permet l'utilisation d'une bombe pour éliminer tous les cubes autour
     * d'elle.
     *
     * @param x abcisse du cube représentant la bombe.
     * @param y ordonnée du cube représentant la bombe.
     * @return le nombre de cubes éliminés par la bombe.
     */
    public int bombs(int x, int y) {
        int nbCubes = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isBomb(i, j) && !isSelected(i, j)) {
                    selected(i, j);
                    nbCubes += bombs(i, j);
                    supprimerCube(i, j);
                }
                if (isCube(i, j)) {
                    supprimerCube(i, j);
                    nbCubes++;
                }
                if (isBall(x, y)) {
                    nbCubes += balloon(i, j);
                }
            }
        }
        return nbCubes;
    }

    /**
     * Permet l'utilisation d'un ballon pour éliminer tous les cubes de la même
     * couleur que lui.
     *
     * @param x abcisse du cube représentant un ballon.
     * @param y ordonnée du cube représentant un ballon.
     * @return le nombre de cubes éliminés par le ballon.
     */
    public int balloon(int x, int y) {
        int nbCubes = 0;
        Color c = color(x, y);
        for (int i = 0; i < getHauteur(); i++) {
            for (int j = 0; j < getLargeur(); j++) {
                if (isSameColor(i, j, c) && isCube(i, j)) {
                    supprimerCube(i, j);
                    nbCubes++;
                }
            }
        }

        supprimerCube(x, y);
        nbCubes++;
        return nbCubes;
    }

    /**
     * Remets à jour les paramètres des jokers.
     */
    public void updateTools() {
        unlockedRockets();
        unlockedHammer();

    }

    /**
     * Permet de jouer sur l'interface textuelle.
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        int x, y, f, m, v, bx, by, choice;
        afficherGrille();
        while (!tousSauve()) {
            System.out.println("choix de l'action à faire: \n");
            System.out.println(" 1-cliquer\n");
            System.out.println(" 2-fusée verticale\n");
            System.out.println(" 3-fusée horizontale\n");
            System.out.println(" 4-Marteau\n");
            System.out.println(" 5-Retour en Arrière\n");
            System.out.println(" Autes touches-continuer\n");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    help();
                    System.out.print("donner la position x: ");
                    x = sc.nextInt();
                    System.out.print("donner la position y: ");
                    y = sc.nextInt();
                    playDelete(x, y);
                    afficherGrille();
                    break;
                case 2:
                    if (rocketIsAvailable() && !tousSauve()) {
                        System.out.print("donner l'axe y de la fusée: ");
                        f = sc.nextInt();
                        playVerticalRocket(f);
                        afficherGrille();
                    } else {
                        System.out.println("pas de fusée disponible\n");
                    }

                    break;
                case 3:
                    if (rocketIsAvailable() && !tousSauve()) {
                        System.out.print("donner l'axe x de la fusée: ");
                        f = sc.nextInt();
                        playHorizontalRocket(f);
                        afficherGrille();
                    } else {
                        System.out.println("pas de fusée disponible\n");
                    }

                    break;
                case 4:
                    if (hammerIsAvailable() && !tousSauve()) {
                        System.out.print("donner l'abscisse du cube a detruire: ");
                        m = sc.nextInt();
                        System.out.println("donner l'ordonnée du cube a detruire: ");
                        v = sc.nextInt();
                        playHammer(m, v);
                        afficherGrille();
                    } else {
                        System.out.println("pas de marteau disponible\n");
                    }
                    break;
                case 5:
                    if (!Jeu.stackEvolution.empty()) {
                        undo(Jeu.stackEvolution.pop());
                        afficherGrille();
                    } else {
                        System.out.println("la pile est vide: vous ne pouvez plus reculer");
                    }
                    break;
                default:
                    System.out.println("action non valide");
                    break;

            }
        }
    }

    /**
     * Déclanche toutes les méthodes qui s'effectuent pour supprimer avec un
     * clic de la souris.
     *
     * @param x abcisse du cube.
     * @param y ordonnée du cube.
     */
    public void playDelete(int x, int y) {
        int nbDeletedCubes = 0;
        Niveau etat = this.picture();
        if (isBomb(x, y)) {
            nbDeletedCubes = bombs(x, y);
        } else if (isBall(x, y)) {
            nbDeletedCubes = balloon(x, y);
        } else {
            nbDeletedCubes = supprimer(x, y);
        }
        setCagnotte(nbDeletedCubes);
        updateTools();
        System.out.println("Après suppression");
        reorganiserGrille();
        if (nbDeletedCubes > 0) {
            Jeu.stackEvolution.push(etat);
            reduceNbDeplacement();
        }

    }

    /**
     * Déclanche toutes les methodes pour utiliser une fusée horizontale.
     *
     * @param x indice de la ligne.
     */
    public void playHorizontalRocket(int x) {
        int nbDeletedCubes = 0;
        Niveau etat = this.picture();
        nbDeletedCubes = rocketX(x);
        useRockets();
        setCagnotte(nbDeletedCubes);
        updateTools();
        reorganiserGrille();
        if (nbDeletedCubes > 0) {
            Jeu.stackEvolution.push(etat);
            reduceNbDeplacement();
        }
    }

    /**
     * Déclanche toutes les methodes pour utiliser une fusée verticale
     *
     * @param y indice de la colonne.
     */
    public void playVerticalRocket(int y) {
        int nbDeletedCubes = 0;
        Niveau etat = this.picture();
        nbDeletedCubes = rocket(y);
        useRockets();
        setCagnotte(nbDeletedCubes);
        updateTools();
        reorganiserGrille();
        if (nbDeletedCubes > 0) {
            Jeu.stackEvolution.push(etat);
            reduceNbDeplacement();
        }
    }

    /**
     * declanche toutes les methodes pour utiliser un marteau.
     *
     * @param x abcisse de la case.
     * @param y ordonnée de la case.
     */
    public void playHammer(int x, int y) {
        int nbDeletedCubes = 0;
        Niveau etat = this.picture();
        nbDeletedCubes = hammer(x, y);
        useHammer();
        setCagnotte(nbDeletedCubes);
        updateTools();
        reorganiserGrille();
        if (nbDeletedCubes > 0) {
            Jeu.stackEvolution.push(etat);
            reduceNbDeplacement();
        }
    }

    /**
     * Permet de revenir en arrière dans le niveau courant.
     *
     * @param prec niveau courant.
     */
    public void undo(Niveau prec) {
        if (retourIsAvailable()) {
            if (prec != null) {
                for (int i = 0; i < this.getHauteur(); i++) {
                    for (int j = 0; j < this.getLargeur(); j++) {
                        this.grille[i][j] = prec.grille[i][j];
                        if (!caseEstVide(i, j)) {
                            this.grille[i][j].unSelect();
                        }
                    }
                }
                this.scoreNiveau = prec.scoreNiveau;
                this.cagnotte = prec.cagnotte;
                this.cagnotteR = prec.cagnotteR;
                this.cagnotteH = prec.cagnotteH;
                this.nbAnimaux = prec.nbAnimaux;
                this.unlockedRockets = prec.unlockedRockets;
                this.unlockedHammer = prec.unlockedHammer;
                useNbRetour();
            }
        } else {
            System.out.println("Vous ne pouvez plus reculer à ce niveau");
        }
    }

    /**
     * Stocke l'état courant du niveau.
     *
     * @return l'état courant du niveau.
     */
    public Niveau picture() {
        return new Niveau(this.getHauteur(), this.getLargeur(), this.cagnotte, this.cagnotteR, this.cagnotteH, this.scoreNiveau, this.nbAnimaux, this.nbDeplacement,
                this.unlockedRockets, this.unlockedHammer, this.grille);

    }
}
