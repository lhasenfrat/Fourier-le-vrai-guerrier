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

    public DispPan(){
        this.ligne = new LinkedList<Complex>();
        this.dessin = new LinkedList<Complex>();
        angleRot = 2*Math.PI/256;
        chrono = new Timer(20, this);
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
        for(int j = 0 ; j < 100 ; j++){
            int i = 0;
            Complex lineEnd = new Complex(0,0);
            for(Complex c : ligne){
                lineEnd=lineEnd.plus(c);
                c.rotate(angleRot*i);
                i++;
            }
            dessin.add(lineEnd);
            this.repaint();
        }
    }

    public void paint(Graphics h){
        h.setColor(Color.white);
        h.fillRect(0,0,getWidth(),getHeight());
        //h.setColor(Color.black);
        Complex point1 = new Complex(this.getWidth()/2, this.getHeight()/2);	//on place le premier point au milieu du panneau pour tracer le premier trait
        for(Complex c : ligne){
            h.drawLine((int)(point1.re()), (int)(point1.im()), (int)(c.re() + point1.re()), (int)(c.im() + point1.im()));
            point1 = new Complex(c.re() + point1.re(), c.im() + point1.im());
        }
        point1 = new Complex(this.getWidth()/2 + (int)(dessin.getFirst().re()), this.getHeight()/2 + (int)(dessin.getFirst().im()));
        Complex point0 = new Complex(this.getWidth()/2 + (int)(dessin.getFirst().re()), this.getHeight()/2 + (int)(dessin.getFirst().im()));
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
