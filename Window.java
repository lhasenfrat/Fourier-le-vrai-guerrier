import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
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
        setTitle("Dessin par série de Fourier- Fenetre principale");
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
        curseurpreci = new JSlider(5,9);
        curseurpreci.setMajorTickSpacing(5);
        curseurpreci.setMinorTickSpacing(1);
        curseurpreci.setBounds((int)(c*(1100)),(int)(c*(600)),(int)(c*500),(int)(c*75));
        curseurpreci.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        /*Ajout position label sur glisseur*/
        curseurpreci.setPaintLabels(true);
        Hashtable position = new Hashtable();
        position.put(5,new JLabel("5"));
        position.put(6,new JLabel("4"));
        position.put(7,new JLabel("3"));
        position.put(8,new JLabel("2"));
        position.put(9,new JLabel("1"));

        curseurpreci.setLabelTable(position);
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
        enter = new JLabel("Dessinez ici");
        enter.setFont(police);
        enter.setBounds((int)(350*c),(int)(1*c),(int)(500*c),(int)(50*c));

        exit = new JLabel("Affichage");
        exit.setFont(police);
        exit.setBounds((int)(1300*c),(int)(1*c),(int)(500*c),(int)(50*c));

        precitext = new JLabel();
        precitext.setText("Vitesse de l'animation : "+ String.valueOf(10 - curseurpreci.getValue()));
        precitext.setFont(police);
        precitext.setBounds((int)(c*(1200)),(int)(c*(570)),(int)(c*500),(int)(c*25));

        ImageIcon logo_INSA0 = new ImageIcon("logo_INSA.png");
        Image logoINSAim = logo_INSA0.getImage(); // transform into image pr changer la taille
        Image logoRESIZE = logoINSAim.getScaledInstance((int)(c*270), (int)(c*70),  java.awt.Image.SCALE_SMOOTH); //changer la taille
        ImageIcon logoRESIZEicon = new ImageIcon(logoRESIZE);  // transform it back
        logo_INSA = new JLabel(logoRESIZEicon); //Placer la photo dans un folder Images!
        logo_INSA.setBounds((int)(c*750),(int)(c*595),(int)(c*300),(int)(c*100));

        title_label = new JLabel("Dessin par série de Fourier");
        title_label.setBounds((int)(c*700),0,(int)(c*700),(int)(c*40));
        title_label.setFont(new Font("Osaka",Font.BOLD,25));
        title_label.setForeground(new Color (60,60,60));

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
                precitext.setText("Vitesse de l'animation : "+String.valueOf(10 - curseurpreci.getValue()));
                panelshow.angleRot = 2*Math.PI/Math.pow(2,curseurpreci.getValue());
                System.out.println(panelshow.angleRot);
                if(paneldraw.listepoints.size()!=0) {

                    buttonstart.doClick();
                }
            }
        });


        setVisible(true);

    }

    public void actionPerformed (ActionEvent e){

        if ((e.getSource() == buttonstart)) {
            if(paneldraw.listepoints.size()==0){
                JOptionPane.showMessageDialog(this,"Liste de points vide");
            }
            else {
                panelshow.dessin = new LinkedList<Complex>();
                String texteCadre = "Equation: " + "\n";

                panelshow.ligne = tFourier(paneldraw.listepoints);
                int a = 0;
                int i = 0;        //texte
                for (Complex c : panelshow.ligne) { //JScroll equation
                    texteCadre = texteCadre + c.re() + "exp(" + i + "t + " + c.theta() + ")";
                    i++;
                    if (a < 4) {
                        a++;
                    } // la loop pour sauter les lignes tous les 3 blocs
                    if (a == 3) {
                        texteCadre += "\n";
                        a = 0;
                    }
                }
                Font font1 = new Font("SansSerif", Font.BOLD, 15);
                scroll.setFont(font1);
                ; //Texte affichÃ© Ã  l'initialisation
                scroll.setText(texteCadre);

                panelshow.chrono.start();
            }
        }

        if ((e.getSource() == buttonclear)) {
            if(paneldraw.listepoints.size()==0){
                JOptionPane.showMessageDialog(this,"Liste de points déjà vide");
            }
            else {
                panelshow.ligne = new LinkedList<Complex>();
                panelshow.dessin = new LinkedList<Complex>();
            }
        }

        if (e.getSource()==boutoncarre) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();

            for (int i = 0 ; i< 200; i++)										// test avec un carré
                listeDepart.add(new Complex(100,i/2));

            for (int i = 0 ; i< 306; i++)
                listeDepart.add(new Complex(103 - i*2/3,100));
            for (int i = 0 ; i< 206; i++)
                listeDepart.add(new Complex(-100,106-i));
            for (int i = 0 ; i< 206; i++)
                listeDepart.add(new Complex(i - 106,-100));
            for (int i = 0 ; i< 106; i++)
                listeDepart.add(new Complex(100,i - 106));
            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints);


        } else  if (e.getSource()==boutonhexa) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();

            for (int i = 0 ; i< 100; i++)										// test avec un hexagone
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

            panelshow.ligne = tFourier(paneldraw.listepoints);


        } else  if (e.getSource()==boutonlosange) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();

            for (int i = 0 ; i< 128; i++)										// test avec un losange
                listeDepart.add(new Complex(128-i,i));

            for (int i = 0 ; i< 128; i++)
                listeDepart.add(new Complex( - i,128-i));
            for (int i = 0 ; i< 128; i++)
                listeDepart.add(new Complex(-128+i,-i));
            for (int i = 0 ; i< 128; i++)
                listeDepart.add(new Complex(i,-128+i));

            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints);

        } else  if (e.getSource()==boutonrandom) {
            panelshow.ligne = new LinkedList<Complex>();
            panelshow.dessin = new LinkedList<Complex>();
            LinkedList<Complex> listeDepart=new LinkedList<Complex>();
            int random =(int) (Math.random()*3);
            if (random==0) {
                for (int i = 0 ; i< 200; i++)										// test random 1
                    listeDepart.add(new Complex(200,-100+i));
                for (int i = 0 ; i< 400; i++)
                    listeDepart.add(new Complex(200- i,100-i/2));
                for (int i = 0 ; i< 400; i++)
                    listeDepart.add(new Complex(-200+i,-100));


            } else if (random==1) {
                
                for (int i = 0 ; i< 100; i++)										// test étoile
                    listeDepart.add(new Complex(i/2,-100+i));           //1
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(50+i,0));               //2
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(150-i*0.75,i*0.75));         //3
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(75+i/2,75+i));    //4
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(125-i*1.25,175-i/2));    //5
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(-i*1.25,125+i/2));    //6
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(-75-i*0.75,75-i*0.75));    //8
                for (int i = 0 ; i< 100; i++)
                    listeDepart.add(new Complex(-150+i,0));    //9 + 10


                paneldraw.listepoints=listeDepart;

                panelshow.ligne = tFourier(paneldraw.listepoints);        

            } else if (random==2) {

            } else {

            }

            paneldraw.listepoints=listeDepart;

            panelshow.ligne = tFourier(paneldraw.listepoints);



        }
    }

    public static LinkedList<Complex> tFourier(LinkedList<Complex> l){	//mÃ©thode qui effectue la transformÃ©e de fourier
        double div=l.size();
        div = 1./div;
        //Conversion LikedList en tableau
        Object[] objectArray = l.toArray();
        Complex[] complexArray = new Complex[objectArray.length];
        for(int i =0; i < complexArray.length; i++) {
            complexArray[i] = (Complex) objectArray[i];
        }

        if(complexArray.length<8){
            complexArray=changeTaille(complexArray, 8);
            div = 1/8.;
        }
        else if(8<complexArray.length && complexArray.length < 16) {
            complexArray=changeTaille(complexArray, 16);
            div = 1/16.;
        }
        else if(16<complexArray.length && complexArray.length < 32) {
            complexArray=changeTaille(complexArray, 32);
            div = 1/32.;
        }
        else if(32<complexArray.length && complexArray.length < 64) {
            complexArray=changeTaille(complexArray, 64);
            div = 1/64.;
        }
        else if((64<complexArray.length) && (complexArray.length<128)){
            complexArray=changeTaille(complexArray,128);
            div = 1/128.;
        }
        else if((128<complexArray.length) && (complexArray.length<256)) {
            complexArray=changeTaille(complexArray, 256);
            div = 1/256.;
        }
        else if((256<complexArray.length) && (complexArray.length<512)) {
            complexArray=changeTaille(complexArray, 512);
            div = 1/512.;
        }
        else if((512<complexArray.length) && (complexArray.length<1024)) {
            complexArray=changeTaille(complexArray, 1024);
            div = 1/1024.;
        }
        else if((1024<complexArray.length) && (complexArray.length<2048)) {
            complexArray=changeTaille(complexArray, 2048);
            div = 1/2048.;
        }
        else if((2048<complexArray.length) && (complexArray.length<4096)) {
            complexArray=changeTaille(complexArray, 4096);
            div = 1/4096.;
        }
        else if((4096<complexArray.length) && (complexArray.length<8192)) {
            complexArray=changeTaille(complexArray, 8192);
            div = 1/8192.;
        }
        else if((8192<complexArray.length) && (complexArray.length<16384)) {
            complexArray=changeTaille(complexArray, 16384);
            div = 1/16384.;
        }


        //FFT
        System.out.println("Taille du tableau : "+ complexArray.length);
        Complex[] outArray = FFT.fft(complexArray);

        //convert array to list
        List<Complex> transList = new LinkedList<>(Arrays.asList(outArray));
        LinkedList<Complex> listOut = new LinkedList<Complex>(transList);

        //Division pour normaliser
        for(int i =0;i<listOut.size();i++){
            listOut.set(i,listOut.get(i).scale(div));
        }
        System.out.println("div= "+div);
        System.out.println("Size de la liste sortie : " + listOut.size());
        return (listOut);

    }

    public static Complex[] changeTaille(Complex[] entree,int taille){
        System.out.println("Taille entrée : " + entree.length + ". Pas puissance de 2. Conversion...");
        Complex[] trans = new Complex[taille];
        for(int i = 0; i<entree.length;i++)
            trans[i]=entree[i];
        for(int i = entree.length;i<trans.length;i++)
            trans[i]= entree[entree.length-1];
        return trans;
    }
}
