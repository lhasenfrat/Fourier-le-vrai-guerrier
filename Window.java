package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import org.jtransforms.fft.*;


public class Window extends JFrame implements ActionListener {

    JPanel paneldraw;
    JPanel panelshow;
    JButton buttonstart;
    JButton buttonclear;
    JSlider curseurpreci;
    JTextArea scroll;
    JScrollPane scrollequation;
    JLabel precitext;
    PanneauEntree pEntree;


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
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        /*Panel dessin*/
        paneldraw = new JPanel();
        paneldraw.setBackground(Color.green);
        paneldraw.setLayout(null);
        paneldraw.setBounds((int)(100*c),(int)(50*c),(int)(700*c),(int)(500*c));

        /*Panel affichage*/
        panelshow = new JPanel();
        panelshow.setBackground(Color.red);
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

        /*Création du Panneau d'entrée*/
        PanneauEntree pEntree = new PanneauEntree(); /*A PLACER*/

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

    }

    public void actionPerformed (ActionEvent e){

        if ((e.getSource() == buttonstart)) {
            fourier(pEntree.listepoints);
        }

        if ((e.getSource() == buttonclear)) {
        }

    }

    public double[] fourier (LinkedList<APoint> ini){

        /*Déplacement Linked vers tableau*/
        double[] four = new double[ini.size() * 2];
        for(int i = 0;i<ini.size()-1;i++){
            four[2*i] = ini.get(i).x;
            four[2*i+1] = ini.get(i).y;
        }

        /*Application de la tranformée*/
        DoubleFFT_1D dfft1d = new DoubleFFT_1D(four.length);
        dfft1d.complexForward(four); //Applique Fourier

        return four;
    }

}