
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ModAdmin extends JFrame implements ActionListener {

    Container cont;
    JPanel panP;
    String[] nomCouleurs = {"BLUE", "GREEN", "MAGENTA", "RED", "YELLOW"};
    Color[] couleurs = {Color.BLUE, Color.GREEN.darker(), Color.MAGENTA, Color.RED, Color.YELLOW};
    Color colorCour = Color.WHITE;
    JMenuBar mBar;
    JMenu file;
    JMenuItem newN;
    JMenuItem download;
    JMenuItem save;
    JMenuItem exit;
    HashMap<Integer, Niveau> levels;
    JComboBox<Integer> comboLevels;
    JComboBox<String> comboColors;
    Niveau nivCour;
    ArrayList<PanelC> listCube;
    String[] s = {"Levels"};
    GridLayout greed;
    int niveauChoisi;
    JPanel panRadio;
    JRadioButton cubeVide;
    JRadioButton cubeColor;
    JRadioButton cubeBomb;
    JRadioButton cubeAnimal;
    JRadioButton cubeObstacle;
    JRadioButton cubeBalloon;
    ButtonGroup radioGroup;
    Cub typeCourant;
    ImageIcon imgA;
    ImageIcon imgB;
    ImageIcon obst;
    ImageIcon balG;
    ImageIcon balB;
    ImageIcon balP;
    ImageIcon balR;
    ImageIcon balY;
    JToolBar tBar;
    Dimension dimNiv;
    int nbAnimauxTotal;
    int nbDeplacements;

    public static void main(String[] args) {
        ModAdmin m = new ModAdmin();
    }

    public ModAdmin() {
        this.setTitle("Bienvenue dans le mod de creation");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        imgB = new ImageIcon("src/Bomb2.png");
        imgA = new ImageIcon("src/doggy.png");
        obst = new ImageIcon("src/woodB.png");
        balG = new ImageIcon("src/balloonG.png");
        balB = new ImageIcon("src/balloonB.png");
        balP = new ImageIcon("src/balloonP.png");
        balR = new ImageIcon("src/balloonR.png");
        balY = new ImageIcon("src/balloonY.png");
        mBar = new JMenuBar();
        file = new JMenu("File");
        newN = new JMenuItem("New");
        newN.addActionListener(this);
        download = new JMenuItem("Download");
        download.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(newN);
        file.add(download);
        file.add(save);
        file.add(exit);
        mBar.add(file);
        this.setJMenuBar(mBar);
        cont = this.getContentPane();
        panP = new JPanel();
        panP.setBackground(new Color(0xF8F8FF));
        comboLevels = new JComboBox(s);
        comboLevels.addActionListener(this);
        comboLevels.setEnabled(false);
        comboColors = new JComboBox(nomCouleurs);
        comboColors.addActionListener(this);
        comboColors.setEnabled(false);

        //radio panel
        cubeVide = new JRadioButton("Vide", true);
        cubeVide.setBackground(new Color(0xB0C4DE));
        cubeVide.addActionListener(this);
        cubeColor = new JRadioButton("Color");
        cubeColor.setBackground(new Color(0xB0C4DE));
        cubeColor.addActionListener(this);
        cubeBomb = new JRadioButton("Bomb");
        cubeBomb.setBackground(new Color(0xB0C4DE));
        cubeBomb.addActionListener(this);
        cubeAnimal = new JRadioButton("Animal");
        cubeAnimal.setBackground(new Color(0xB0C4DE));
        cubeAnimal.addActionListener(this);
        cubeObstacle = new JRadioButton("Obstacle");
        cubeObstacle.setBackground(new Color(0xB0C4DE));
        cubeObstacle.addActionListener(this);
        cubeBalloon = new JRadioButton("Balloon");
        cubeBalloon.setBackground(new Color(0xB0C4DE));
        cubeBalloon.addActionListener(this);
        radioGroup = new ButtonGroup();
        radioGroup.add(cubeVide);
        radioGroup.add(cubeColor);
        radioGroup.add(cubeObstacle);
        radioGroup.add(cubeBomb);
        radioGroup.add(cubeAnimal);
        radioGroup.add(cubeBalloon);
        panRadio = new JPanel();
        colorCour = Color.BLUE;
        tBar = new JToolBar();

        tBar.setBackground(new Color(0xB0C4DE));
        tBar.add(comboLevels);
        tBar.add(comboColors);
        tBar.add(cubeVide);
        tBar.add(cubeColor);
        tBar.add(cubeObstacle);
        tBar.add(cubeBomb);
        tBar.add(cubeAnimal);
        tBar.add(cubeBalloon);

        cont.add(tBar, "North");
        cont.add(panP);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == newN) {
            //comboLevels.setEnabled(false);
            levels = new HashMap<Integer, Niveau>();
            Dialog d = new Dialog(this);
            if (panP != null) {
                this.remove(panP);
                this.repaint();
                this.revalidate();
                panP = null;
            }

            dimNiv = d.lancerDialog();
            if (dimNiv != null) {
                panP = new JPanel();
                panP.setBackground(new Color(0xF8F8FF));
                greed = new GridLayout(dimNiv.height, dimNiv.width, 3, 3);
                panP.setLayout(greed);
                listCube = new ArrayList<PanelC>();
                for (int i = 0; i < dimNiv.height; i++) {
                    for (int k = 0; k < dimNiv.width; k++) {
                        PanelC l = null;
                        l = new PanelC(i, k, Cub.VIDE, Color.WHITE);
                        l.setPreferredSize(new Dimension(50, 50));
                        listCube.add(l);
                        panP.add(l);
                    }
                }
                cont.add(panP);
                panP.repaint();
                panP.revalidate();
                colorCour = Color.BLUE;
                comboColors.setSelectedIndex(0);
            }
        }

        if (source == download) {
            JFileChooser fchose = new JFileChooser();
            int r = fchose.showDialog(this, "Telecharger niveaux");
            if (r == JFileChooser.APPROVE_OPTION) {
                File lvl = fchose.getSelectedFile();
                try {
                    levels = FileWorker.uploadLvl(lvl);
                    comboLevels.setEnabled(true);
                    for (Integer i : levels.keySet()) {
                        comboLevels.addItem(i);
                        this.repaint();

                    }
                } catch (Exception ex) {
                    Logger.getLogger(ModAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        if (source == save) {
            JFileChooser fchose = new JFileChooser();
            fchose.setSelectedFile(new File("update.ser"));
            int r = fchose.showDialog(this, "Sauvgarder niveaux");
            if (r == JFileChooser.APPROVE_OPTION) {
                File lvl = new File(fchose.getSelectedFile().getAbsolutePath());
                nivCour = creerNiveau(listCube, dimNiv.height, dimNiv.width);
                levels.put(niveauChoisi, nivCour);
                ObjectOutputStream outObjLvl;
                try {
                    outObjLvl = new ObjectOutputStream(new FileOutputStream(lvl));
                    outObjLvl.writeObject((Object) levels);
                    outObjLvl.close();
                } catch (IOException ex) {
                    Logger.getLogger(ModAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (source == exit) {
            System.exit(1);
        }

        if (source == comboLevels) {
            niveauChoisi = comboLevels.getSelectedIndex();
            if (panP != null) {
                this.remove(panP);
                this.repaint();
                this.revalidate();
                panP = null;

            }
            panP = new JPanel();
            panP.setBackground(new Color(0xF8F8FF));
            if (niveauChoisi != 0) {
                nivCour = levels.get((Integer) niveauChoisi);
                nbAnimauxTotal = nivCour.getNbAnimauxTotal();
                nbDeplacements = nivCour.getNbDeplacements();
                dimNiv = new Dimension(nivCour.getLargeur(), nivCour.getHauteur());
                int hauteur = nivCour.getHauteur();
                int largeur = nivCour.getLargeur();
                greed = new GridLayout(nivCour.getHauteur(), nivCour.getLargeur(), 3, 3);
                panP.setLayout(greed);
                listCube = new ArrayList<PanelC>();
                for (int i = 0; i < hauteur; i++) {
                    for (int k = 0; k < largeur; k++) {
                        PanelC l = null;
                        colorCour = nivCour.grille[i][k].getColor();
                        if (nivCour.isAnimal(i, k)) {
                            l = new PanelC(i, k, Cub.ANIMAL);
                            l.initPanelC();
                        } else if (nivCour.isCube(i, k)) {
                            // colorCour=nivCour.grille[i][k].getColor();
                            l = new PanelC(i, k, Cub.COLOR, colorCour);
                            l.initPanelC();
                        } else if (nivCour.isObstacle(i, k)) {
                            l = new PanelC(i, k, Cub.OBSTACLE);
                            l.initPanelC();
                        } else if (nivCour.caseEstVide(i, k)) {
                            l = new PanelC(i, k, Cub.VIDE);
                            l.initPanelC();
                        } else if (nivCour.isBomb(i, k)) {
                            l = new PanelC(i, k, Cub.BOMB);
                            l.initPanelC();
                        } else if (nivCour.isBall(i, k)) {
                            //colorCour=nivCour.grille[i][k].getColor();
                            l = new PanelC(i, k, Cub.BALON, colorCour);
                            l.initPanelC();
                        }
                        l.setPreferredSize(new Dimension(50, 50));
                        listCube.add(l);
                        panP.add(l);
                    }
                }
                colorCour = Color.BLUE;
                comboColors.setSelectedIndex(0);
                cont.add(panP);
                panP.repaint();
                panP.revalidate();

            }
        }

        if (source == comboColors) {
            String s = (String) comboColors.getSelectedItem();
            for (int i = 0; i < nomCouleurs.length; i++) {
                if (nomCouleurs[i].equals(s)) {
                    colorCour = couleurs[i];
                }

            }
        }

        if (source == cubeVide) {
            typeCourant = Cub.VIDE;
            comboColors.setEnabled(false);
        }
        if (source == cubeColor) {
            typeCourant = Cub.COLOR;
            comboColors.setEnabled(true);
        }
        if (source == cubeAnimal) {
            typeCourant = Cub.ANIMAL;
            comboColors.setEnabled(false);
        }
        if (source == cubeBalloon) {
            typeCourant = Cub.BALON;
            comboColors.setEnabled(true);
        }
        if (source == cubeBomb) {
            typeCourant = Cub.BOMB;
            comboColors.setEnabled(false);
        }
        if (source == cubeObstacle) {
            typeCourant = Cub.OBSTACLE;
            comboColors.setEnabled(false);
        }

    }

    public Niveau creerNiveau(ArrayList<PanelC> list, int h, int l) {
        int larg = l;
        int haut = h;
        Niveau n;
        nbAnimauxTotal = 0;
        nbDeplacements = 10;
        Cube[][] c = new Cube[haut][larg];
        for (PanelC p : list) {
            if (p.type == Cub.ANIMAL) {
                c[p.getVX()][p.getVY()] = new CubeA("a");
                nbAnimauxTotal++;
            }
            if (p.type == Cub.BALON) {
                c[p.getVX()][p.getVY()] = new CubeBalloon(p.getColor());
            }
            if (p.type == Cub.BOMB) {
                c[p.getVX()][p.getVY()] = new CubeBomb();
            }
            if (p.type == Cub.COLOR) {
                c[p.getVX()][p.getVY()] = new CubeC(p.getColor());
            }
            if (p.type == Cub.OBSTACLE) {
                c[p.getVX()][p.getVY()] = new CubeO();
            }
            if (p.type == Cub.VIDE) {
                c[p.getVX()][p.getVY()] = new CubeVide();
            }
        }
        n = new Niveau(nbAnimauxTotal, nbDeplacements, haut, larg, c);
        return n;
    }

    class PanelC extends JPanel implements MouseListener {

        private Cub type;
        private int x, y;
        private Color c;
        JLabel jl;

        public PanelC(int x, int y, Cub c) {
            jl = null;
            this.x = x;
            this.y = y;
            type = c;
            this.addMouseListener(this);
        }

        public PanelC(int x, int y, Cub k, Color c) {
            jl = null;
            this.x = x;
            this.y = y;
            type = k;
            this.c = c;
            this.addMouseListener(this);
        }

        public int getVX() {
            return x;
        }

        public int getVY() {
            return y;
        }

        public Cub getType() {
            return type;
        }

        public Color getColor() {
            return c;
        }

        public void initPanelC() {

            if (type == Cub.ANIMAL) {
                this.c = null;
                if (jl != null) {
                    Container parent = jl.getParent();
                    if(parent!=null){
                    parent.remove(jl);
                    parent.revalidate();
                    parent.repaint();
                    }
                }
                jl = new JLabel(imgA);
                this.add(jl);
                this.setOpaque(false);

            }
            if (type == Cub.BALON) {
                this.c = colorCour;
                if (jl != null) {
                    Container parent = jl.getParent();
                    if(parent!=null){
                    parent.remove(jl);
                    parent.revalidate();
                    parent.repaint();
                    }
                }
                if (c.equals(Color.GREEN.darker())) {
                    Image img = balG.getImage();
                    Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    balG = new ImageIcon(newimg);
                    jl = new JLabel(balG);
                    this.add(jl);
                }
                if (c.equals(Color.BLUE)) {
                    Image img = balB.getImage();
                    Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    balB = new ImageIcon(newimg);
                    jl = new JLabel(balB);
                    this.add(jl);
                }
                if (c.equals(Color.MAGENTA)) {
                    Image img = balP.getImage();
                    Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    balP = new ImageIcon(newimg);
                    jl = new JLabel(balP);
                    this.add(jl);
                }
                if (c.equals(Color.RED)) {
                    Image img = balR.getImage();
                    Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    balR = new ImageIcon(newimg);
                    jl = new JLabel(balR);
                    this.add(jl);
                }
                if (c.equals(Color.YELLOW)) {
                    Image img = balY.getImage();
                    Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    balY = new ImageIcon(newimg);
                    jl = new JLabel(balY);
                    this.add(jl);
                }

                this.setOpaque(false);
            }
            if (this.type == Cub.BOMB) {
                this.c = null;
                if (jl != null) {
                    Container parent = jl.getParent();
                    if(parent!=null){
                    parent.remove(jl);
                    parent.revalidate();
                    parent.repaint();
                    }
                }
                jl = new JLabel(imgB);
                this.add(jl);
                this.setOpaque(false);
            }
            if (this.type == Cub.COLOR) {
                this.c = colorCour;
               if (jl != null) {
                    Container parent = jl.getParent();
                    if(parent!=null){
                    parent.remove(jl);
                    parent.revalidate();
                    parent.repaint();
                    }
                }
                this.setOpaque(true);
                this.setBackground(c);
            }
            if (this.type == Cub.OBSTACLE) {
                this.c = null;
                if (jl != null) {
                    Container parent = jl.getParent();
                    if(parent!=null){
                    parent.remove(jl);
                    parent.revalidate();
                    parent.repaint();
                    }
                }
                Image img = obst.getImage();
                Image newimg = img.getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH);
                obst = new ImageIcon(newimg);
                jl = new JLabel(obst);
                this.add(jl);
                this.setOpaque(false);
            }
            if (this.type == Cub.VIDE) {
                this.c = Color.WHITE;
                if (jl != null) {
                    Container parent = jl.getParent();
                    if(parent!=null){
                    parent.remove(jl);
                    parent.revalidate();
                    parent.repaint();
                    }
                }

                this.setOpaque(true);
                this.setBackground(c);

            }
            this.repaint();
            this.revalidate();
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            this.type = typeCourant;
            initPanelC();

        }

        @Override
        public void mousePressed(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {

        }

        @Override
        public void mouseEntered(MouseEvent me) {

        }

        @Override
        public void mouseExited(MouseEvent me) {

        }

    }

    class Dialog extends JDialog implements ActionListener {

        int hauteur, largeur;
        JLabel label = new JLabel("Saisir hauteur et largeur");
        JLabel haut = new JLabel("Hauteur:");
        JLabel lar = new JLabel("Largeur");
        JLabel numNiv = new JLabel("Numero de niveau:");
        JTextField h = new JTextField(2);
        JTextField l = new JTextField(2);
        JTextField n = new JTextField(2);
        JButton ok = new JButton("Ok");
        JButton anul = new JButton("Annuler");
        Boolean flag;

        public Dialog(JFrame f) {
            super(f, "Saisir hauteur et largeur", true);
            this.setSize(200, 200);
            this.setLocationRelativeTo(cont);
            ok.addActionListener(this);
            anul.addActionListener(this);
            Container cont = this.getContentPane();
            ok.addActionListener(this);
            anul.addActionListener(this);
            cont.setLayout(new FlowLayout());
            cont.add(label);
            cont.add(numNiv);
            cont.add(n);
            cont.add(haut);
            cont.add(h);
            cont.add(lar);
            cont.add(l);
            cont.add(ok);
            cont.add(anul);
        }

        public Dimension lancerDialog() {
            flag = false;
            setVisible(true);
            if (flag && !n.getText().equals("") && !l.getText().equals("") && !h.getText().equals("")) {
                niveauChoisi = Integer.parseInt(n.getText());
                int lar = Integer.parseInt(l.getText());
                int haut = Integer.parseInt(h.getText());
                if (niveauChoisi > 0 && lar > 0 && haut > 0) {
                    return new Dimension(lar, haut);
                }
                return null;
            } else {
                return null;
            }
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == ok) {
                flag = true;
                this.setVisible(false);
            }
            if (ae.getSource() == anul) {
                this.setVisible(false);
            }
        }

    }

    enum Cub {

        ANIMAL,
        OBSTACLE,
        VIDE,
        COLOR,
        BOMB,
        BALON;

    }

}
