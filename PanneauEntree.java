import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    MouseEvent souris;

    public PanneauEntree()
    {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        listepoints = new LinkedList<Complexe>();
        setBackground(Color.WHITE);
    }


    public void paint(Graphics g){
       

        g.drawLine(xmousebefore,ymousebefore,xmouse,ymouse);
        if (needclear)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
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
