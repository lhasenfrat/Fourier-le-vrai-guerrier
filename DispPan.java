import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*; 
import java.awt.Color;

public class DispPan extends JPanel implements ActionListener{
	public LinkedList<Complexe> ligne;
	private Timer chrono;
	public LinkedList<Complexe> dessin;
	private double angleRot;

<<<<<<< HEAD
	
=======
>>>>>>> cd35152cd72277edfe45485f3b1b6ddd3d1e3b4a
	public DispPan(LinkedList<Complexe> d){
		this.ligne = d;
		this.dessin = new LinkedList<Complexe>();
		angleRot = 2*Math.PI/100;
		chrono = new Timer(50, this);
		chrono.start();
	}
<<<<<<< HEAD
	public DispPan(){
		this.ligne = new LinkedList<Complexe>();
		this.dessin = new LinkedList<Complexe>();
		angleRot = 2*Math.PI/100;
		chrono = new Timer(50, this);
		chrono.start();
	}

	public void actionPerformed(ActionEvent e){

=======
	public void actionPerformed(ActionEvent k){
>>>>>>> cd35152cd72277edfe45485f3b1b6ddd3d1e3b4a
		int i = 0;
		Complexe lineEnd = new Complexe(0,0);
		for(Complexe c : ligne){
			lineEnd.sommeV1(c);
			c.rotate(angleRot*i);
			i++;
		}
		dessin.add(lineEnd);
		this.repaint();
	}
	
<<<<<<< HEAD
	public void paint(Graphics g){

		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.black);
=======
	public void paint(Graphics h){
		h.setColor(Color.white);
		h.fillRect(0,0,getWidth(),getHeight());
		h.setColor(Color.black);
>>>>>>> cd35152cd72277edfe45485f3b1b6ddd3d1e3b4a
		Complexe point1 = new Complexe(this.getWidth()/2, this.getHeight()/2);	//on place le premier point au milieu du panneau pour tracer le premier trait
		for(Complexe c : ligne){
			h.drawLine((int)(point1.getRe()), (int)(point1.getIm()), (int)(c.getRe() + point1.getRe()), (int)(c.getIm() + point1.getIm()));
			point1 = new Complexe(c.getRe() + point1.getRe(), c.getIm() + point1.getIm());
		}
		point1 = new Complexe(this.getWidth()/2 + (int)(dessin.getFirst().getRe()), this.getHeight()/2 + (int)(dessin.getFirst().getIm()));
		Complexe point0 = new Complexe(this.getWidth()/2 + (int)(dessin.getFirst().getRe()), this.getHeight()/2 + (int)(dessin.getFirst().getIm()));
		for(Complexe c : dessin){
<<<<<<< HEAD

			g.drawLine((int)(point1.getRe()), (int)(point1.getIm()), (int)(c.getRe() + this.getWidth()/2), (int)(c.getIm()) + this.getHeight()/2);
=======
			h.setColor(Color.red);
			if (point1.equals(point0)) {
				h.setColor(Color.white); //de même, pour enlever le premier trait rouge qui connecte avec le milieu
			}
			h.drawLine((int)(point1.getRe()), (int)(point1.getIm()), (int)(c.getRe() + this.getWidth()/2), (int)(c.getIm()) + this.getHeight()/2);
>>>>>>> cd35152cd72277edfe45485f3b1b6ddd3d1e3b4a
			point1 = new Complexe(c.getRe() + this.getWidth()/2, c.getIm() + this.getHeight()/2);
		}
		
	}

	
}
