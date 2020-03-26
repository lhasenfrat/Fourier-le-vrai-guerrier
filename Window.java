import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import java.util.Hashtable;


public class Window extends JFrame implements ActionListener {

    DispPan panelshow;
    JButton buttonstart;
    JButton buttonclear;
    JSlider curseurpreci;
    JTextArea scroll;
    JScrollPane scrollequation;
    JLabel precitext;
    PanneauEntree paneldraw;

    double c;

    public Window(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        /*Fenetre principale*/
        int screenW= screenSize.width;
        int screenH= screenSize.height;
        c = (double)screenH/1080 ; //coef de grandissement

        setLayout(null);
        setTitle("Projet guerrier- Fenetre principale");
        setLocation((int)((screenW-(1800*c))/2),(int)((screenH-(900*c))/2));
        setSize((int)(1800*c),(int)(900*c));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        /*Panel dessin*/
        paneldraw = new PanneauEntree();


        paneldraw.setBounds((int)(100*c),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Panel affichage*/
        panelshow = new DispPan();
        panelshow.setBackground(Color.white);

        panelshow.setBounds((int)(c*(100+200+700)),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Les boutons*/
        buttonstart = new JButton();
        buttonstart.setText("start");
        buttonstart.setBounds((int)(200*c),(int)(c*(50+500+50)),(int)(200*c),(int)(75*c));
        buttonclear = new JButton();
        buttonclear.setText("clear");
        buttonclear.setBounds((int)(200*c+100*c+200*c),(int)(c*(50+500+50)),(int)(200*c),(int)(75*c));

        /*Le curseur pour regler la precision*/ //à modifier en curseur après
        curseurpreci = new JSlider(0,100,50);
        curseurpreci.setMajorTickSpacing(25);
        curseurpreci.setPaintTicks(true);


        /*Autre info vers le curseur*/
        precitext = new JLabel();
        precitext.setText("PRECISION: ");
        precitext.setBounds((int)(c*(100+700+200+100+210)),(int)(c*(500+50+20)),(int)(c*250),(int)(c*25));
        curseurpreci.setBounds((int)(c*(100+700+200+100)),(int)(c*(50+500+50)),(int)(c*500),(int)(c*75));
        // Add positions label in the slider
        curseurpreci.setPaintLabels(true);
        Hashtable position = new Hashtable();
        position.put(0, new JLabel("0"));
        position.put(25, new JLabel("25"));
        position.put(50, new JLabel("50"));
        position.put(75, new JLabel("75"));
        position.put(100, new JLabel("100"));
        curseurpreci.setLabelTable(position);


        /*Affichage de l'équation*/
        scroll= new JTextArea();
        scrollequation = new JScrollPane(scroll);
        scrollequation.setBounds((int)(c*(100)),(int)(c*(50+500+50+75+50)),(int)(c*1600),(int)(c*75));

        /*Ajout à la fenetre principale*/

        this.add(paneldraw);
        this.add(panelshow);
        this.add(buttonstart);
        this.add(buttonclear);
        this.add(curseurpreci);
        this.add(scrollequation);

        /*Ajout au ActionListener*/
        buttonstart.addActionListener(this);
        buttonclear.addActionListener(this);


        setVisible(true);

    }

    public void actionPerformed (ActionEvent e){

        if ((e.getSource() == buttonstart)) {
            int nbrVect = paneldraw.listepoints.size(); //nbr de points du dessin dessiné
            double precinum = (double)(curseurpreci.getValue())/(double)(100); //pourcentage de points par rapport à ceux du dessin dessiné
            panelshow.ligne= tFourier(paneldraw.listepoints,(int)(precinum*nbrVect));
            //System.out.println("nbr points : "+(int)(precinum*nbrVect));


        }

        if ((e.getSource() == buttonclear)) {
            panelshow.ligne = new LinkedList<Complexe>();
            panelshow.dessin = new LinkedList<Complexe>();

        }

    }

    public static LinkedList<Complexe> tFourier(LinkedList<Complexe> l, int nbVect){	//méthode qui effectue la transformée de fourier
        double nbPoints = l.size();
        Complexe integrale = new Complexe(0,0);
        LinkedList<Complexe> renvoi = new LinkedList<Complexe>();
        for(int i = 0 ; i < nbVect ; i++){
            integrale = new Complexe(0,0);
            double t = 0;
            for(Complexe c : l){
                Complexe sc = new Complexe(1, c.getRho(), c.getTheta() - (i*Math.PI*2*t/nbPoints));
                integrale.sommeV1(sc);
                t++;
            }
            integrale.setRho(integrale.getRho()/(nbPoints));
            renvoi.add(integrale);
        }
        return(renvoi);
    }

}
