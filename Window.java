import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Window extends JFrame implements ActionListener {

    DispPan panelshow;
    JButton buttonstart;
    JButton buttonclear;
    JSlider curseurpreci;
    JTextArea scroll;
    JScrollPane scrollequation;
    JLabel precitext;
    JLabel enter;
    JLabel exit;
    JLabel logo_INSA;
    JLabel title_label;
    PanneauEntree paneldraw;
    TemplateButton boutoncarre;
    TemplateButton boutonhexa;
    TemplateButton boutonlosange;
    TemplateButton boutonrandom;


    double c;

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
        setBackground(Color.MAGENTA);
        getContentPane().setBackground(Color.gray);

        /*Panel dessin*/
        paneldraw = new PanneauEntree();
        paneldraw.setBounds((int)(100*c),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Panel affichage*/
        panelshow = new DispPan();
        panelshow.setBackground(Color.white);
        panelshow.setBounds((int)(c*(100+200+700)),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Les boutons*/
        buttonstart = new JButton();
        buttonstart.setText("Start");
        buttonstart.setBounds((int)(200*c),(int)(c*(50+500+50)),(int)(200*c),(int)(75*c));
        buttonstart.setBackground(Color.green);
        buttonstart.setFont(new Font("Arial",Font.BOLD,20));
        buttonstart.setBorder(BorderFactory.createLineBorder(Color.black, 5)); // Line Border + Thickness of the Border

        buttonclear = new JButton();
        buttonclear.setText("Clear");
        buttonclear.setBounds((int)(200*c+100*c+200*c),(int)(c*(50+500+50)),(int)(200*c),(int)(75*c));
        buttonclear.setBackground(Color.red);
        buttonclear.setFont(new Font("Arial",Font.BOLD,20));
        buttonclear.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        boutoncarre = new TemplateButton("carre");
        boutoncarre.setBounds((int)(850*c),(int)(60*c),(int)(100*c),(int)(100*c));

        boutonhexa=new TemplateButton("hexa");
        boutonhexa.setBounds((int)(850*c),(int)(185*c),(int)(100*c),(int)(100*c));

        boutonlosange=new TemplateButton("losange");
        boutonlosange.setBounds((int)(850*c),(int)(310*c),(int)(100*c),(int)(100*c));


        boutonrandom=new TemplateButton("formelouche");
        boutonrandom.setBounds((int)(850*c),(int)(435*c),(int)(100*c),(int)(100*c));


        /*Le curseur pour regler la precision*/
        curseurpreci = new JSlider(0,100);
        curseurpreci.setMajorTickSpacing(25);
        curseurpreci.setMinorTickSpacing(5);
        curseurpreci.setBounds((int)(c*(100+700+200+100)),(int)(c*(50+500+50)),(int)(c*500),(int)(c*75));
        curseurpreci.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        /*Ajout position label sur glisseur*/
        curseurpreci.setPaintLabels(true);
        Hashtable position = new Hashtable();
        position.put(0,new JLabel("0"));
        position.put(25,new JLabel("25"));
        position.put(50,new JLabel("50"));
        position.put(75,new JLabel("75"));
        position.put(100,new JLabel("100"));
        curseurpreci.setLabelTable(position);
        curseurpreci.setPaintTicks(true);

        /*Affichage de l'Ã©quation*/
        scroll= new JTextArea();
        scroll.setText("En attente...");
        scroll.setFont(new Font("Arial",Font.ITALIC,30)); //Texte affichÃ© Ã  l'initialisation

        scrollequation = new JScrollPane(scroll);
        scrollequation.setBounds((int)(c*(100)),(int)(c*(50+500+50+75+50)),(int)(c*1600),(int)(c*75));
        scrollequation.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        /*Les Labels*/
        Font police = new Font("Arial",Font.BOLD,20); //Police Label (Arial, taille 20)
        enter = new JLabel("Dessiner ici");
        enter.setFont(police);
        enter.setBounds((int)(350*c),(int)(1*c),(int)(500*c),(int)(50*c));

        exit = new JLabel("Affichage");
        exit.setFont(police);
        exit.setBounds((int)(1300*c),(int)(1*c),(int)(500*c),(int)(50*c));

        precitext = new JLabel();
        precitext.setText("Precision : 50");
        precitext.setFont(police);
        precitext.setBounds((int)(c*(1250)),(int)(c*(500+50+20)),(int)(c*250),(int)(c*25));

        logo_INSA = new JLabel(new ImageIcon("./src/Images/logo_INSA.png"));
        logo_INSA.setBounds(0,screenH-300,100,100);

        title_label = new JLabel("Projet guerrier : le dessin par Fourier");
        title_label.setBounds(screenW-550,screenH-300,700,100);
        title_label.setFont(new Font("Osaka",Font.ITALIC,25));

        /*Ajout Ã  la fenetre principale*/

        this.add(paneldraw);
        this.add(panelshow);
        this.add(buttonstart);
        this.add(buttonclear);
        this.add(curseurpreci);
        this.add(scrollequation);
        this.add(enter);
        this.add(exit);
        this.add(precitext);
        this.add(logo_INSA);
        this.add(title_label);
        this.add(boutonlosange);
        this.add(boutonrandom);
        this.add(boutonhexa);
        this.add(boutoncarre);


        /*Ajout boutons au ActionListener*/
        buttonstart.addActionListener(this);
        buttonclear.addActionListener(this);
        boutoncarre.addActionListener(this);
        boutonlosange.addActionListener(this);
        boutonhexa.addActionListener(this);
        boutonrandom.addActionListener(this);
        /*Ajout curseur au ChangeListener*/
        curseurpreci.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                precitext.setText("Precision : " + String.valueOf(curseurpreci.getValue()));
            }
        });


        setVisible(true);

    }

    public void actionPerformed (ActionEvent e){

        if ((e.getSource() == buttonstart)) {
            String texteCadre = "";
            panelshow.ligne = tFourier(paneldraw.listepoints,paneldraw.listepoints.size() - 100);
            int i = 0;		//texte
             for(Complexe c : panelshow.ligne){
				texteCadre = texteCadre + c.getRe() + "exp(" + i + "t + " + c.getTheta() + ")" ;
				i++;
            }
            scroll.setText(texteCadre);
            panelshow.chrono.start();

        }

        if ((e.getSource() == buttonclear)) {
            panelshow.ligne = new LinkedList<Complexe>();
            panelshow.dessin = new LinkedList<Complexe>();

        }

        if (e.getSource()==boutoncarre) {
            panelshow.ligne = new LinkedList<Complexe>();
            panelshow.dessin = new LinkedList<Complexe>();
            LinkedList<Complexe> listeDepart=new LinkedList<Complexe>();

            for (int i = 0 ; i< 100; i++)										// test avec un carré
                listeDepart.add(new Complexe(100,i));

            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complexe(100 - i,100));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complexe(-100,100-i));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complexe(i - 100,-100));
            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complexe(100,i - 100));
            paneldraw.listepoints=listeDepart;

            panelshow.ligne= tFourier(paneldraw.listepoints, curseurpreci.getValue());


        } else  if (e.getSource()==boutonhexa) {
            panelshow.ligne = new LinkedList<Complexe>();
            panelshow.dessin = new LinkedList<Complexe>();

        } else  if (e.getSource()==boutonlosange) {
            panelshow.ligne = new LinkedList<Complexe>();
            panelshow.dessin = new LinkedList<Complexe>();
            LinkedList<Complexe> listeDepart=new LinkedList<Complexe>();

            for (int i = 0 ; i< 200; i++)										// test avec un carré
                listeDepart.add(new Complexe(200-i,i));

            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complexe( - i,200-i));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complexe(-200+i,-i));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complexe(i,-200+i));

            paneldraw.listepoints=listeDepart;

            panelshow.ligne= tFourier(paneldraw.listepoints, curseurpreci.getValue());

        } else  if (e.getSource()==boutonrandom) {
            panelshow.ligne = new LinkedList<Complexe>();
            panelshow.dessin = new LinkedList<Complexe>();

        }
    }

    public static LinkedList<Complexe> tFourier(LinkedList<Complexe> l, int nbVect){	//mÃ©thode qui effectue la transformÃ©e de fourier
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
