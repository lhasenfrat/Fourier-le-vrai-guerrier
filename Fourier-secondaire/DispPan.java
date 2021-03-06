import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*; 
import java.awt.Color;

public class DispPan extends JPanel implements ActionListener{
	public LinkedList<Complexe> ligne;
	public Timer chrono;
	public LinkedList<Complexe> dessin;
	private double angleRot;
	public LinkedList<Complexe> origine;
	
	public DispPan(LinkedList<Complexe> d,LinkedList<Complexe> o){
		this.ligne = d;
		this.origine = o;
		this.dessin = new LinkedList<Complexe>();
		angleRot = 2*Math.PI/(1000);
		chrono = new Timer(100, this);
	}
	public void actionPerformed(ActionEvent e){
		for(int k = 0 ; k < 1000 ; k++){
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
	}
	
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.blue);
		g.fillRect((int)(this.getWidth())/2,this.getHeight()/2, 100,10);
		g.setColor(Color.green);
		for(Complexe c : origine){
			g.fillRect((int)(c.getRe()) + this.getWidth()/2 - 3, (int)(c.getIm()) + this.getHeight()/2 - 3, 6,6);
		}
        g.setColor(Color.black);
		Complexe point1 = new Complexe(this.getWidth()/2, this.getHeight()/2);	//on place le premier point au milieu du panneau pour tracer le premier trait
		for(Complexe c : ligne){
			g.drawLine((int)(point1.getRe()), (int)(point1.getIm()), (int)(c.getRe() + point1.getRe()), (int)(c.getIm() + point1.getIm()));
			point1 = new Complexe(c.getRe() + point1.getRe(), c.getIm() + point1.getIm());
		}
		point1 = new Complexe(this.getWidth()/2 + (int)(dessin.getFirst().getRe()), this.getHeight()/2 + (int)(dessin.getFirst().getIm()));
		g.setColor(Color.red);
		for(Complexe c : dessin){
			g.drawLine((int)(point1.getRe()), (int)(point1.getIm()), (int)(c.getRe() + this.getWidth()/2), (int)(c.getIm()) + this.getHeight()/2);
			point1 = new Complexe(c.getRe() + this.getWidth()/2, c.getIm() + this.getHeight()/2);;
		}
		
	}
	
}
