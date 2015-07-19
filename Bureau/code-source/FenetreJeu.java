
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>FenetreJeu est une classe qui représente la fenêtre dans laquelle vont se
 * placer tous les composants du jeu.</b>
 *
 * @author abdbah39
 */
public class FenetreJeu extends JFrame implements ActionListener, MouseInputListener {

    /**
     * ArrayList qui stocke la liste des cubes se trouvant dans la grille du
     * jeu.
     */
    private ArrayList<PanelG> listPanelG;

    /**
     * Gille du jeu
     */
    private JPanel g = new JPanel();

    /**
     * Menu horizontal.
     */
    private JPanel mh = new JPanel();

    /**
     * Menu des jokers.
     */
    private JPanel mJokers = new JPanel();

    /**
     * Menu contenant les boutons : retour en arrière et aide.
     */
    private JPanel mOptions = new JPanel();

    /**
     * Menu affichant le nom de l'utilisateur courant s'il en existe un.
     */
    private JPanel mAux = new JPanel();

    /**
     * Hauteur et largeur de l'écran.
     */
    private int largEcr, hautEcr;

    /**
     * Niveau actuel.
     */
    private Niveau recupNiveau;

    /**
     * Layout qui organise les cubes dans la grille du jeu.
     */
    private GridLayout gl;

    /**
     * Fichier contenant l'image qui servira comme image de fond pour la fenêtre
     * du jeu.
     */
    private File b = new File("src/Image.jpg");

    /**
     * Image de fond de la fenêtre du jeu.
     */
    private ImageFond conteneur;

    /**
     * Boite de dialogue qui s'affiche dans le cas ou le joueur a réussi le
     * niveau courant.
     */
    private JDialog reussite = new JDialog();

    /**
     * récupération du conteneur principal de tous les composants du jeu.
     */
    private Container contenu = getContentPane();

    /**
     * Récupération de la boite à outils de java.
     */
    private Toolkit tk = Toolkit.getDefaultToolkit();

    /**
     * Determine quelle action l'utulisateur a fait dans le jeu.
     */
    public int action = 0;

    ImageIcon icon1 = new ImageIcon("src/fireworksH.png");
    JButton fuseeH = new JButton(icon1);

    ImageIcon icon2 = new ImageIcon("src/fireworks.png");
    JButton fuseeV = new JButton(icon2);

    ImageIcon icon3 = new ImageIcon("src/hammer.png");
    JButton marteau = new JButton(icon3);

    /**
     * Constructeur FenetreJeu.
     */
    public FenetreJeu() {
        initComponent();
        initGrille();
        initMenuHorizontal();
        initMenuJokers();
        initMenuOptions();
        initMenuAuxiliaire();
    }

    /**
     * Récupére le conteneur de la fenêtre du jeu.
     *
     * @return le conteneur de la fenêtre du jeu.
     */
    public ImageFond getConteneur() {
        return conteneur;
    }

    /**
     * Constructeur FenetreJeu.
     *
     * @param i niveau du jeu auquel on voudrais accéder.
     * @throws Exception dans le cas où on arrive pas à recharger le niveau
     * courant à partir du disque dur.
     */
    public FenetreJeu(int i) throws Exception {
        Jeu.stackEvolution = new Stack();
        Jeu.setNumNivCourant(i);
        recupNiveau = Jeu.rechargerNiveau(i);
        initComponent();
        initMenuHorizontal();
        initMenuJokers();
        initMenuOptions();
        initGrille();
        initMenuAuxiliaire();
    }

    /**
     * Initialisation des composants de la fenêtre de jeu.
     */
    public void initComponent() {
        this.setTitle("Niveau" + Jeu.getNumNivCourant());
        this.setResizable(false);

        Image image = tk.getImage("src/wolf.png");

        conteneur = new ImageFond(b);

        Dimension dimEcran = tk.getScreenSize();
        largEcr = dimEcran.width;
        hautEcr = dimEcran.height;

        this.setExtendedState(this.MAXIMIZED_BOTH);

        contenu.setLayout(new BorderLayout());
        contenu.add(conteneur);

        conteneur.setLayout(new BorderLayout());
        conteneur.add(g, BorderLayout.CENTER);
        conteneur.add(mOptions, BorderLayout.EAST);
        conteneur.add(mAux, BorderLayout.WEST);
        conteneur.add(mh, BorderLayout.NORTH);
        conteneur.add(mJokers, BorderLayout.SOUTH);

        this.setSize(new Dimension(largEcr, hautEcr));
        this.setMinimumSize(new Dimension(3 * largEcr / 4, 3 * hautEcr / 4));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Initialisation du menu affichant le nom de l'utilisateur courant dans le
     * cas où un utilisateur est connecté.
     */
    public void initMenuAuxiliaire() {
        mAux.setOpaque(false);
        mAux.setPreferredSize(new Dimension(largEcr / 5, hautEcr));
        Font f = new Font("Times new Roman", Font.BOLD, 20);
        JPanel nom = new JPanel();
        nom.setOpaque(false);
        JLabel jn = new JLabel();
        jn.setFont(f);
        if (Jeu.getUtilisateurCourant() != null) {
            jn.setText("Bienvenue " + Jeu.getUtilisateurCourant().getId());
        }
        nom.add(jn);
        mAux.add(nom);
    }

    /**
     * Initialisation de la grille où se déroule le jeu.
     */
    public void initGrille() {
        listPanelG = new ArrayList<PanelG>();
        int hautG = g.getSize().height;
        gl = new GridLayout(recupNiveau.getHauteur(), recupNiveau.getLargeur());
        gl.setHgap(5);
        gl.setVgap(5);
        g.setPreferredSize(new Dimension(2 * recupNiveau.getHauteur(), 2 * recupNiveau.getLargeur()));
        g.setLayout(gl);
        for (int i = 0; i < recupNiveau.getHauteur(); i++) {
            for (int k = 0; k < recupNiveau.getLargeur(); k++) {
                PanelG l = null;
                if (recupNiveau.isAnimal(i, k)) {
                    l = new PanelG(i, k);
                    ImageIcon a = new ImageIcon("src/doggy.png");
                    Image img = a.getImage();
                    Image newimg = img.getScaledInstance(60, 60,
                            java.awt.Image.SCALE_SMOOTH);
                    a = new ImageIcon(newimg);
                    JLabel jl = new JLabel(a);
                    l.add(jl);
                    l.setOpaque(false);
                } else if (recupNiveau.isCube(i, k)) {
                    l = new PanelG(i, k);
                    l.setBackground(recupNiveau.grille[i][k].getColor());
                } else if (recupNiveau.isObstacle(i, k)) {
                    l = new PanelG(i, k);
                    ImageIcon h = new ImageIcon("src/woodB.png");
                    Image img = h.getImage();
                    Image newimg = img.getScaledInstance(80, 60,
                            java.awt.Image.SCALE_SMOOTH);
                    h = new ImageIcon(newimg);
                    JLabel jl = new JLabel(h);
                    l.add(jl);
                    l.setOpaque(false);
                } else if (recupNiveau.caseEstVide(i, k)) {
                    l = new PanelG(i, k);
                    l.setOpaque(false);
                } else if (recupNiveau.isBomb(i, k)) {
                    l = new PanelG(i, k);
                    ImageIcon b = new ImageIcon("src/Bomb2.png");
                    Image img = b.getImage();
                    Image newimg = img.getScaledInstance(60, 60,
                            java.awt.Image.SCALE_SMOOTH);
                    b = new ImageIcon(newimg);
                    JLabel jl = new JLabel(b);
                    l.add(jl);
                    l.setOpaque(false);
                } else if (recupNiveau.isBall(i, k)) {
                    l = new PanelG(i, k);
                    if (recupNiveau.grille[i][k].getColor().equals(Color.green)) {
                        ImageIcon c = new ImageIcon("src/balloonG.png");
                        Image img = c.getImage();
                        Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                        c = new ImageIcon(newimg);
                        JLabel jl = new JLabel(c);
                        l.add(jl);
                        l.setOpaque(false);
                    } else if (recupNiveau.grille[i][k].getColor().equals(Color.red)) {
                        ImageIcon d = new ImageIcon("src/balloonR.png");
                        Image img = d.getImage();
                        Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                        d = new ImageIcon(newimg);
                        JLabel jl = new JLabel(d);
                        l.add(jl);
                        l.setOpaque(false);
                    } else if (recupNiveau.grille[i][k].getColor().equals(Color.yellow)) {
                        ImageIcon e = new ImageIcon("src/balloonY.png");
                        Image img = e.getImage();
                        Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                        e = new ImageIcon(newimg);
                        JLabel jl = new JLabel(e);
                        l.add(jl);
                        l.setOpaque(false);
                    }
                    if (recupNiveau.grille[i][k].getColor().equals(Color.blue)) {
                        ImageIcon f = new ImageIcon("src/balloonB.png");
                        Image img = f.getImage();
                        Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                        f = new ImageIcon(newimg);
                        JLabel jl = new JLabel(f);
                        l.add(jl);
                        l.setOpaque(false);
                    }
                    if (recupNiveau.grille[i][k].getColor().equals(Color.magenta)) {
                        ImageIcon g = new ImageIcon("src/balloonP.png");
                        Image img = g.getImage();
                        Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                        g = new ImageIcon(newimg);
                        JLabel jl = new JLabel(g);
                        l.add(jl);
                        l.setOpaque(false);
                    }
                }
                listPanelG.add(l);
                l.setPreferredSize(new Dimension(60, 60));
                l.addMouseListener(this);
                g.setOpaque(false);
                g.add(l);
            }
        }

    }

    /**
     * Initialise le menu horizontal qui contient le nombre d'animaux présents
     * dans le jeu, le nombre de déplacements autorisés et la cagnotte de
     * l'utilisateur.
     */
    public void initMenuHorizontal() {
        mh.setOpaque(false);
        mh.setPreferredSize(new Dimension(largEcr, hautEcr / 7));
        mh.setLayout(new FlowLayout());

        Font f = new Font("Times new Roman", Font.BOLD, 16);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        JLabel j1 = new JLabel();
        j1.setFont(f);
        j1.setText("Animaux : \n" + Integer.toString(recupNiveau.getNbAnimaux()) + " / " + Integer.toString(recupNiveau.getNbAnimauxTotal()) + "\n");
        p1.setBackground(Color.blue);
        p1.add(j1);
        p1.setPreferredSize(new Dimension(largEcr / 7, hautEcr / 11));
        p1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));

        JLabel j2 = new JLabel();
        j2.setFont(f);
        j2.setText("Déplacements : " + Integer.toString(recupNiveau.getNbDeplacements()));
        p2.setBackground(Color.red);
        p2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));
        p2.setPreferredSize(new Dimension(largEcr / 8, hautEcr / 11));
        p2.add(j2);

        JLabel j3 = new JLabel();
        j3.setFont(f);
        j3.setText("Cagnotte : " + "\n" + Integer.toString(recupNiveau.getCagnotte()) + "\n");
        p3.setPreferredSize(new Dimension(largEcr / 7, hautEcr / 11));
        p3.setBackground(Color.blue);
        p3.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));
        p3.add(j3);

        mh.add(p1);
        mh.add(p2);
        mh.add(p3);
    }

    /**
     * Initialise le menu qui contient les jokers disponibles dans le jeu.
     */
    public void initMenuJokers() {
        mJokers.setOpaque(false);
        Font f = new Font("Times New Roman", Font.BOLD, 40);

        fuseeH.setText("" + recupNiveau.getRockets());
        fuseeH.setFont(f);
        fuseeH.setForeground(Color.black);
        fuseeH.setVerticalTextPosition(SwingConstants.CENTER);
        fuseeH.setHorizontalTextPosition(SwingConstants.CENTER);
        fuseeH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = 1;
            }
        });
        fuseeH.setPreferredSize(new Dimension(100, 110));
        fuseeH.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));
        fuseeH.setOpaque(true);
        mJokers.add(fuseeH);

        fuseeV.setText("" + recupNiveau.getRockets());
        fuseeV.setFont(f);
        fuseeV.setForeground(Color.black);
        fuseeV.setVerticalTextPosition(SwingConstants.CENTER);
        fuseeV.setHorizontalTextPosition(SwingConstants.CENTER);
        fuseeV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = 2;
            }
        });
        fuseeV.setPreferredSize(new Dimension(100, 110));
        fuseeV.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));
        fuseeV.setOpaque(true);
        mJokers.add(fuseeV);

        marteau.setText("" + recupNiveau.getHammer());
        marteau.setFont(f);
        marteau.setForeground(Color.black);
        marteau.setVerticalTextPosition(SwingConstants.CENTER);
        marteau.setHorizontalTextPosition(SwingConstants.CENTER);
        marteau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = 3;
            }
        });
        marteau.setPreferredSize(new Dimension(100, 110));
        marteau.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.yellow));
        marteau.setOpaque(true);
        mJokers.add(marteau);
    }

    /**
     * Initialise le menu qui contient les boutons : retour en arrière et aide.
     */
    public void initMenuOptions() {
        BoxLayout bl = new BoxLayout(mOptions, BoxLayout.Y_AXIS);
        mOptions.setLayout(bl);
        Font f = new Font("Times New Roman", Font.BOLD, 40);

        mOptions.setOpaque(false);
        mOptions.setPreferredSize(new Dimension(largEcr / 5, hautEcr));
        ImageIcon img = new ImageIcon("src/arrow.png");
        ImageIcon img2 = new ImageIcon("src/help.png");
        JButton retour = new JButton(img);
        retour.setText("" + recupNiveau.getNbRetourAutorise());
        retour.setFont(f);
        retour.setForeground(Color.black);
        retour.setVerticalTextPosition(SwingConstants.CENTER);
        retour.setHorizontalTextPosition(SwingConstants.CENTER);

        JButton aide = new JButton(img2);
        mOptions.add(retour);
        mOptions.add(Box.createVerticalGlue());
        mOptions.add(aide);
        mOptions.add(Box.createHorizontalStrut(100));

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!Jeu.stackEvolution.empty()) {
                    g.removeAll();
                    mh.removeAll();
                    mJokers.removeAll();
                    mOptions.removeAll();
                    g.validate();
                    mh.validate();
                    mOptions.validate();
                    mJokers.validate();
                    recupNiveau.undo(Jeu.stackEvolution.pop());
                    initGrille();
                    initMenuHorizontal();
                    initMenuJokers();
                    initMenuOptions();
                    g.revalidate();
                    mh.revalidate();
                    mOptions.revalidate();
                    mJokers.revalidate();
                    g.repaint();
                    mh.repaint();
                    mJokers.repaint();
                    mOptions.repaint();
                } else {
                    System.out.println("la pile est vide: vous ne pouvez plus reculer");
                }
            }
        });

        retour.setOpaque(false);
        retour.setBorderPainted(false);
        retour.setToolTipText("Revenir en arrière.");

        aide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!recupNiveau.tousSauve() && recupNiveau.getNbDeplacements() > 0) {
                    int[] coord = recupNiveau.help();
                    if (coord[2] == -1) {
                        fuseeV.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.CYAN));
                        fuseeH.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.CYAN));
                        fuseeV.repaint();
                        fuseeH.repaint();
                    } else if (coord[2] == -2) {
                        marteau.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.CYAN));
                        marteau.repaint();
                    } else if (coord[2] == -3) {
                        JOptionPane.showMessageDialog(g, "Pas d'aide");
                    } else {
                        for (PanelG p : listPanelG) {
                            if (p.get_Ligne() == coord[0] && p.get_Colonne() == coord[1]) {
                                p.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.CYAN));
                                p.repaint();
                            }
                        }
                    }
                }
            }
        });
        aide.setOpaque(false);
        aide.setBorderPainted(false);
        aide.setToolTipText("Obtenir de l'aide.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component cmp = e.getComponent();

        if (!recupNiveau.tousSauve() && recupNiveau.getNbDeplacements() > 0) {
            int x = 0;
            int y = 0;
            int nbDeletedCubes = 0;
            PanelG tmp = (PanelG) cmp;
            x = tmp.get_Ligne();
            y = tmp.get_Colonne();
            Niveau etat = recupNiveau.picture();
            g.removeAll();
            mh.removeAll();
            mJokers.removeAll();
            mOptions.removeAll();
            g.validate();
            mh.validate();
            mOptions.validate();
            mJokers.validate();
            if (action == 0) {
                tk.beep();
                recupNiveau.playDelete(x, y);
                action = 0;
            } else if (action == 1) {
                tk.beep();
                if (recupNiveau.rocketIsAvailable() && !recupNiveau.tousSauve()) {
                    recupNiveau.playHorizontalRocket(x);
                }
                action = 0;
            } else if (action == 2) {
                tk.beep();
                if (recupNiveau.rocketIsAvailable() && !recupNiveau.tousSauve()) {
                    recupNiveau.playVerticalRocket(y);
                }
                action = 0;
            } else if (action == 3) {
                tk.beep();
                if (recupNiveau.hammerIsAvailable() && !recupNiveau.tousSauve()) {
                    recupNiveau.playHammer(x, y);
                }
                action = 0;
            }
            initGrille();
            initMenuHorizontal();
            initMenuJokers();
            initMenuOptions();
            g.revalidate();
            mh.revalidate();
            mOptions.revalidate();
            mJokers.revalidate();
            g.repaint();
            mh.repaint();
            mJokers.repaint();
            mOptions.repaint();
        }
        if (recupNiveau.tousSauve()) {
        	reussite.setModal(true);
            reussite.setSize(600, 600);
            reussite.setResizable(false);
            reussite.setLocationRelativeTo(null);
            Font f = new Font("Times New Roman", Font.BOLD, 30);
            JTextArea fin = new JTextArea();
            fin.setFont(f);
            fin.setBackground(new Color(131, 182, 216));
            fin.setText("Niveau terminé !" + "\n");
            fin.append("Cliquez sur SUIVANT pour continuer");
            fin.setEditable(false);
            JButton suivant = new JButton("Suivant");
            suivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Jeu.setNumNivCourant(Jeu.getNumNivCourant() + 1);
                    try {
                        recupNiveau = Jeu.getNiveau().get(Jeu.getNumNivCourant());
                        if (Jeu.getUtilisateurCourant() != null) {
                            Jeu.getUtilisateurCourant().setNiveau(Jeu.getNumNivCourant());
                        }
                        Jeu.stackEvolution = new Stack();
                        reussite.dispose();
                        g.removeAll();
                        mh.removeAll();
                        mJokers.removeAll();
                        mOptions.removeAll();
                        g.validate();
                        mh.validate();
                        mOptions.validate();
                        mJokers.validate();
                        initGrille();
                        initMenuHorizontal();
                        initMenuJokers();
                        initMenuOptions();
                        g.revalidate();
                        mh.revalidate();
                        mOptions.revalidate();
                        mJokers.revalidate();
                        g.repaint();
                        mh.repaint();
                        mJokers.repaint();
                        mOptions.repaint();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            reussite.setLayout(new BorderLayout());
            reussite.add(fin, BorderLayout.CENTER);
            reussite.add(suivant, BorderLayout.SOUTH);
            reussite.setVisible(true);
        }
        if (recupNiveau.getNbDeplacements() <= 0 && !recupNiveau.tousSauve()) {
            JDialog echec = new JDialog();
            echec.setModal(true);
            echec.setSize(600, 600);
            echec.setResizable(false);
            echec.setLocationRelativeTo(null);
            JTextArea jta = new JTextArea();
            jta.setEditable(false);
            JPanel sud = new JPanel();
            Font f = new Font("Times New Roman", Font.BOLD, 30);
            jta.setBackground(new Color(131, 182, 216));
            jta.setText("Niveau non terminé !" + "\n");
            jta.append("Trop peu d'animaux sauvés ! " + "\n");
            jta.setFont(f);
            JButton reessayer = new JButton("Reessayer");
            JButton quitter = new JButton("Quitter");
            BoxLayout bl = new BoxLayout(sud, BoxLayout.X_AXIS);
            sud.setLayout(bl);
            sud.add(Box.createHorizontalStrut(200));
            sud.add(reessayer);
            sud.add(quitter);
            sud.setBackground(new Color(131, 182, 216));
            reessayer.setPreferredSize(new Dimension(80, 60));
            quitter.setPreferredSize(new Dimension(80, 60));
            reessayer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Jeu j = new Jeu(Jeu.getNumNivCourant());
                        Jeu.stackEvolution = new Stack();
                        recupNiveau = Jeu.getNiveau().get(Jeu.getNumNivCourant());
                        echec.dispose();
                        g.removeAll();
                        mh.removeAll();
                        mJokers.removeAll();
                        mOptions.removeAll();
                        g.validate();
                        mh.validate();
                        mOptions.validate();
                        mJokers.validate();
                        initGrille();
                        initMenuHorizontal();
                        initMenuJokers();
                        initMenuOptions();
                        g.revalidate();
                        mh.revalidate();
                        mOptions.revalidate();
                        mJokers.revalidate();
                        g.repaint();
                        mh.repaint();
                        mJokers.repaint();
                        mOptions.repaint();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            quitter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    echec.dispose();
                }
            });
            echec.setLayout(new BorderLayout());
            echec.add(jta, BorderLayout.CENTER);
            echec.add(sud, BorderLayout.SOUTH);
            echec.setVisible(true);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
