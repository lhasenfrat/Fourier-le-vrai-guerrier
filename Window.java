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
    int puissancemax;


    double c;

    public Window(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        puissancemax=2048;

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
        panelshow.setBounds((int)(c*(1000)),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Les boutons*/
        buttonstart = new JButton();
        buttonstart.setText("Start");
        buttonstart.setBounds((int)(200*c),(int)(c*(600)),(int)(200*c),(int)(75*c));
        buttonstart.setBackground(Color.green);
        buttonstart.setFont(new Font("Arial",Font.BOLD,20));
        buttonstart.setBorder(BorderFactory.createLineBorder(Color.black, 5)); // Line Border + Thickness of the Border

        buttonclear = new JButton();
        buttonclear.setText("Clear");
        buttonclear.setBounds((int)(c*500),(int)(c*(600)),(int)(200*c),(int)(75*c));
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
        curseurpreci.setBounds((int)(c*(1100)),(int)(c*(600)),(int)(c*500),(int)(c*75));
        curseurpreci.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        /*Ajout position label sur glisseur*/

        curseurpreci.setPaintTicks(true);

        /*Affichage de l'Ã©quation*/
        scroll= new JTextArea();
        Font police = new Font("Arial",Font.BOLD,20);; //Texte affichÃ© Ã  l'initialisation
        scroll.setFont(police);
        scroll.setText("En attente...");

        scrollequation = new JScrollPane(scroll);
        scrollequation.setBounds((int)(c*(100)),(int)(c*(725)),(int)(c*1600),(int)(c*100));
        scrollequation.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        /*Les Labels*/
        enter = new JLabel("Dessiner ici");
        enter.setFont(police);
        enter.setBounds((int)(350*c),(int)(1*c),(int)(500*c),(int)(50*c));

        exit = new JLabel("Affichage");
        exit.setFont(police);
        exit.setBounds((int)(1300*c),(int)(1*c),(int)(500*c),(int)(50*c));

        precitext = new JLabel();
        precitext.setText(String.valueOf((int)(Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)))+" vecteurs");
        precitext.setFont(police);
        precitext.setBounds((int)(c*(1250)),(int)(c*(570)),(int)(c*250),(int)(c*25));

        logo_INSA = new JLabel(new ImageIcon("logo_INSA.png")); //Placer la photo dans un folder Images!
        logo_INSA.setBounds((int)(c*690),(int)(c*560),300,100);

        title_label = new JLabel("Projet guerrier : le dessin par Fourier");
        title_label.setBounds((int)(c*621),0,(int)(c*700),(int)(c*40));
        title_label.setFont(new Font("Osaka",Font.BOLD,25));
        title_label.setForeground(new Color (234,120,25));

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
                precitext.setText(String.valueOf((int)(Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)))+" Vecteurs");
            }
        });


        setVisible(true);

    }

    public void actionPerformed (ActionEvent e){

        if ((e.getSource() == buttonstart)) {
            panelshow.dessin = new LinkedList<Complex>();
            String texteCadre = "Equation: "+"\n";
            panelshow.ligne = tFourier(paneldraw.listepoints,(int)(1+Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)));
            int a = 0;
            int i = 0;		//texte
            for(Complex c : panelshow.ligne){
                texteCadre = texteCadre + c.re() + "exp(" + i + "t + " + c.theta() + ")" ;
                i++;
                if (a<4){ a++;} // la loop pour sauter les lignes tous les 3 blocs
                if (a==3){
                    texteCadre+= "\n"; a=0;}
            }
            Font font1 = new Font("SansSerif", Font.BOLD, 15);
            scroll.setFont(font1);; //Texte affichÃ© Ã  l'initialisation
            scroll.setText(texteCadre);

            panelshow.chrono.start();


        }

        if ((e.getSource() == buttonclear)) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();

        }

        if (e.getSource()==boutoncarre) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();

            for (int i = 0 ; i< 100; i++)										// test avec un carré
                listeDepart.add(new Complex(100,i));

            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complex(100 - i,100));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complex(-100,100-i));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complex(i - 100,-100));
            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complex(100,i - 100));
            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints,(int)(1+Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)));


        } else  if (e.getSource()==boutonhexa) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();

            for (int i = 0 ; i< 100; i++)										// test avec un carré
                listeDepart.add(new Complex(100-i/2,i));

            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complex(50-i,100));

            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complex(-50 - (i/2),100 -i));


            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complex(-100+i/2,-i));

            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complex(-50+i,-100));
            for (int i = 0 ; i< 100; i++)
                listeDepart.add(new Complex(50+i/2,-100+i));

            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints,(int)(1+Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)));


        } else  if (e.getSource()==boutonlosange) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();

            for (int i = 0 ; i< 200; i++)										// test avec un carré
                listeDepart.add(new Complex(200-i,i));

            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complex( - i,200-i));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complex(-200+i,-i));
            for (int i = 0 ; i< 200; i++)
                listeDepart.add(new Complex(i,-200+i));

            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints,(int)(1+Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)));

        } else  if (e.getSource()==boutonrandom) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();
            int random =(int) Math.random()*3;
            if (random==0) {
                for (int i = 0 ; i< 200; i++)										// test avec un carré
                    listeDepart.add(new Complex(200,-100+i));

                for (int i = 0 ; i< 400; i++)
                    listeDepart.add(new Complex( 200- i,100-i/2));
                for (int i = 0 ; i< 400; i++)
                    listeDepart.add(new Complex(-200+i,-100));


            } else if (random==1) {


            } else if (random==2) {

            } else {

            }

            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints,(int)(1+Math.exp(curseurpreci.getValue() * Math.log(puissancemax)/100)));



        }
    }

    public static LinkedList<Complex> tFourier(LinkedList<Complex> l, int nbVect){	//mÃ©thode qui effectue la transformÃ©e de fourier
        double nbPoints = l.size();
        Complex integrale = new Complex(0,0);
        LinkedList<Complex> renvoi = new LinkedList<Complex>();
        for(int i = 0 ; i < nbVect ; i++){
            integrale = new Complex(0,0);
            double t = 0;
            for(Complex c : l){
                double ctheta=c.theta() - (i*Math.PI*2*t/nbPoints);
                Complex sc = new Complex(Math.cos(ctheta)*c.rho(),Math.sin(ctheta)*c.rho());
                integrale=integrale.plus(sc);
                t++;
            }
            integrale.setRho(integrale.rho()/(nbPoints));
            renvoi.add(integrale);
        }
        return(renvoi);
    }








}
