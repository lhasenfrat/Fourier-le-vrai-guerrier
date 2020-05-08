import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;
import java.awt.Color;

public class DispPan extends JPanel implements ActionListener{
    public LinkedList<Complex> ligne;
    public Timer chrono;
    public LinkedList<Complex> dessin;
    public double angleRot;
    public int vitesse;

    public DispPan(){
        this.ligne = new LinkedList<Complex>();
        this.dessin = new LinkedList<Complex>();
        angleRot = 2*Math.PI/256;
        vitesse = 20;
        chrono = new Timer(vitesse, this);
        
        //on lance le chrono pendant un court instant sinon la fenêtre s'affiche mal
        chrono.start();
        try {
            Thread.sleep(100);					
        }
        catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        chrono.stop();


    }

    public void actionPerformed(ActionEvent k){
            int i = 0;
            Complex lineEnd = new Complex(0,0);
            
            //on fait tourner chaque vecteurs de la transformée de Fourier
            for(Complex c : ligne){
                lineEnd=lineEnd.plus(c);
                c.rotate(angleRot*i);
                i++;
            }
            dessin.add(lineEnd);
            this.repaint();
    }

    public void paint(Graphics h){
		
		//on efface l'ecran
        h.setColor(Color.white);
        h.fillRect(0,0,getWidth(),getHeight());
        
        //on trace les vecteurs en noir
        h.setColor(Color.black);
        Complex point1 = new Complex(this.getWidth()/2, this.getHeight()/2);	//on place le premier point au milieu du panneau pour tracer le premier trait
        for(Complex c : ligne){
            h.drawLine((int)(point1.re()), (int)(point1.im()), (int)(c.re() + point1.re()), (int)(c.im() + point1.im()));
            point1 = new Complex(c.re() + point1.re(), c.im() + point1.im());
        }
        point1 = new Complex(this.getWidth()/2 + (int)(dessin.getFirst().re()), this.getHeight()/2 + (int)(dessin.getFirst().im()));
        Complex point0 = new Complex(this.getWidth()/2 + (int)(dessin.getFirst().re()), this.getHeight()/2 + (int)(dessin.getFirst().im()));
        
        //on affiche le tracé en rouge
        for(Complex c : dessin){
			h.setColor(Color.red);
            if (point1.equals(point0)) {
                h.setColor(Color.white); // pour enlever le premier trait rouge qui connecte avec le milieu
            }
            h.drawLine((int)(point1.re()), (int)(point1.im()), (int)(c.re() + this.getWidth()/2), (int)(c.im()) + this.getHeight()/2);
            point1 = new Complex(c.re() + this.getWidth()/2, c.im() + this.getHeight()/2);
        }
    }
}
