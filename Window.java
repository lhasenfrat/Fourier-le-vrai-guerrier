import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;


public class Window extends JFrame implements ActionListener {

    DispPan panelshow;
    JButton buttonstart;
    JButton buttonclear;
    JSlider curseurpreci;
    JTextArea scroll;
    JScrollPane scrollequation;
    JLabel precitext;
    PanneauEntree paneldraw;
    JPanel panelblank;


    public Window(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        /*Fenetre principale*/
        int screenW= screenSize.width;
        int screenH= screenSize.height;
        double c = (double)screenH/1080 ; //coef de grandissement

        setLayout(null);
        setTitle("Projet guerrier- Fenetre principale");
        setLocation((int)((screenW-(1800*c))/2),(int)((screenH-(900*c))/2));
        setSize((int)(1800*c),(int)(900*c));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        /*Panel dessin*/
        paneldraw = new PanneauEntree();
        panelblank = new JPanel();
        panelblank.setBackground(Color.WHITE);
        paneldraw.setLayout(null);
        panelblank.setLayout(null);
        panelblank.setBounds((int)(100*c),(int)(50*c),(int)(700*c),(int)(500*c));
        paneldraw.setBounds((int)(100*c),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Panel affichage*/
        panelshow = new DispPan(new LinkedList<Complexe>());
        panelshow.setLayout(null);
        panelshow.setBounds((int)(c*(100+200+700)),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Les boutons*/
        buttonstart = new JButton();
        buttonstart.setText("start");
        buttonstart.setBounds((int)(200*c),(int)(c*(50+500+50)),(int)(200*c),(int)(75*c));
        buttonclear = new JButton();
        buttonclear.setText("clear");
        buttonclear.setBounds((int)(200*c+100*c+200*c),(int)(c*(50+500+50)),(int)(200*c),(int)(75*c));

        /*Le curseur pour regler la precision*/ //à modifier en curseur après
        curseurpreci = new JSlider();
        precitext = new JLabel();
        precitext.setText("PRECISION: ");
        precitext.setBounds((int)(c*(100+700+200+100+210)),(int)(c*(500+50+20)),(int)(c*250),(int)(c*25));
        curseurpreci.setBounds((int)(c*(100+700+200+100)),(int)(c*(50+500+50)),(int)(c*500),(int)(c*75));

        /*Affichage de l'équation*/
        scroll= new JTextArea();
        scrollequation = new JScrollPane(scroll);
        scrollequation.setBounds((int)(c*(100)),(int)(c*(50+500+50+75+50)),(int)(c*1600),(int)(c*75));

        /*Ajout à la fenetre principale*/
        this.add(panelblank);
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
            panelshow.ligne = tFourier(paneldraw.listepoints, 100);
        }

        if ((e.getSource() == buttonclear)) {
            paneldraw.repaint(); /*ne marche pas*/
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
