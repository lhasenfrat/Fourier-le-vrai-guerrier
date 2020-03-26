import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.LinkedList;

public class PanneauEntree extends JPanel implements  MouseMotionListener,MouseListener
{

    int xmouse;
    int ymouse;
    int xmousebefore;
    int ymousebefore;
    boolean needclear=true;
    boolean ispressed=false;
    boolean ispressedbefore=false;
    LinkedList<Complexe> listepoints ;
    LinkedList<LignesEntree> listecoordreelles ;

    MouseEvent souris;

    public PanneauEntree()
    {
        needclear=true;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        listepoints = new LinkedList<Complexe>();
        listecoordreelles= new LinkedList<LignesEntree>();
    }


    public void paint(Graphics z){
       

        z.drawLine(xmousebefore,ymousebefore,xmouse,ymouse);
        System.out.println(xmousebefore +" "+   ymousebefore+" " + xmouse + " "+ymouse);
        LignesEntree ligne = new LignesEntree(xmousebefore, ymousebefore,xmouse,ymouse);
        listecoordreelles.add(ligne);

        for (LignesEntree l : listecoordreelles)
        {
            z.drawLine(l.x1,l.y1,l.x2,l.y2);

        }
        if (needclear)
        {
            listecoordreelles.clear();
            z.setColor(Color.WHITE);
            z.fillRect(0,0,getWidth(),getHeight());
            needclear=false;
        }

    }
    public void mouseEntered(MouseEvent e)
    {}

    public void mouseExited(MouseEvent e)
    {}

    public void mouseClicked(MouseEvent e)
    {}

    public void mousePressed(MouseEvent e)
    {
        needclear=true;

        if ( !ispressedbefore)
        {
            ispressed=true;
            ispressedbefore=true;
        } else if (ispressedbefore)
        {
            ispressed=true;
            listepoints.clear();

        }


    }

    public void mouseReleased(MouseEvent e)
    {

        ispressed=false;
        xmousebefore=(int)(listepoints.getLast().getRe() +this.getWidth() / 2);
        ymousebefore=(int)(listepoints.getLast().getIm() + this.getHeight() / 2);
        xmouse=(int)(listepoints.getFirst().getRe() + this.getWidth() / 2);
        ymouse=(int)(listepoints.getFirst().getIm() + this.getHeight() / 2);


        this.repaint();
    }
    public void mouseDragged(MouseEvent e)
    {

            xmousebefore=xmouse;
            ymousebefore=ymouse;
            xmouse = e.getX();
            ymouse = e.getY();

            if (xmouse!=0 && ymouse!=0 && xmousebefore!=0 && ymousebefore!=0)
            {
                listepoints.add(new Complexe(xmouse - this.getWidth() / 2, ymouse - this.getHeight() / 2));
                this.repaint();
            }

    }
    public void mouseMoved(MouseEvent e)
    { }

}
