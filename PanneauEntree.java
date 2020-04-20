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
    LinkedList<Complex> listepoints ;
    LinkedList<int[]> listecoordreelles ;

    MouseEvent souris;

    public PanneauEntree()
    {
        needclear=true;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        listepoints = new LinkedList<Complex>();
        listecoordreelles= new LinkedList<int[]>();
    }


    public void paint(Graphics z){


        z.drawLine(xmousebefore,ymousebefore,xmouse,ymouse);
        int[] ligne ={xmousebefore, ymousebefore,xmouse,ymouse};
        listecoordreelles.add(ligne);

        for (int[] l : listecoordreelles)
        {
            z.drawLine(l[0],l[1],l[2],l[3]);

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
        xmousebefore=(int)(listepoints.getLast().re() +this.getWidth() / 2);  //Coordonnées où on lâche
        ymousebefore=(int)(listepoints.getLast().im() + this.getHeight() / 2);
        xmouse=(int)(listepoints.getFirst().re() + this.getWidth() / 2);  //Coordonnées de départ
        ymouse=(int)(listepoints.getFirst().im() + this.getHeight() / 2);
        double xvector = (xmouse-xmousebefore);  //Vecteur à tracer
        double yvector = (ymouse-ymousebefore);
        double norme= Math.sqrt(xvector*xvector+yvector*yvector); //Longueur de ce vect
        xvector=xvector/norme;   //Vecteur unité directeur
        yvector=yvector/norme;
        int xcurrent=xmousebefore;
        int ycurrent=ymousebefore;
        System.out.println("Vous avez tracé "+listepoints.size()+" points.");
        if(listepoints.size()<16)
            lastLine(16,xcurrent,ycurrent,norme,xvector,yvector);
        else if(16<listepoints.size()&&listepoints.size()<32)
            lastLine(32,xcurrent,ycurrent,norme,xvector,yvector);
        else if(32<listepoints.size()&&listepoints.size()<64)
            lastLine(64,xcurrent,ycurrent,norme,xvector,yvector);
        else if(64<listepoints.size()&&listepoints.size()<128)
            lastLine(128,xcurrent,ycurrent,norme,xvector,yvector);
        else if(128<listepoints.size()&&listepoints.size()<256)
            lastLine(256,xcurrent,ycurrent,norme,xvector,yvector);
        else if(256<listepoints.size()&&listepoints.size()<512)
            lastLine(512,xcurrent,ycurrent,norme,xvector,yvector);
        else if(512<listepoints.size()&&listepoints.size()<1024)
            lastLine(1024,xcurrent,ycurrent,norme,xvector,yvector);
        else if(1024<listepoints.size()&&listepoints.size()<2048)
            lastLine(2048,xcurrent,ycurrent,norme,xvector,yvector);
        else if(2048<listepoints.size()&&listepoints.size()<4096)
            lastLine(256,xcurrent,ycurrent,norme,xvector,yvector);
        else if(4096<listepoints.size()&&listepoints.size()<8192)
            lastLine(256,xcurrent,ycurrent,norme,xvector,yvector);


/*        for(int i=0;i<(int)(norme);i++)
        {
            listepoints.add(new Complex(xcurrent +i*xvector - this.getWidth() / 2, ycurrent + i*yvector- this.getHeight() / 2));
        }

        while(listepoints.size()<3000){
            listepoints.add(new Complex(xmouse - this.getWidth() / 2, ymouse- this.getHeight() / 2));
        }
*/
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
            listepoints.add(new Complex(xmouse - this.getWidth() / 2, ymouse - this.getHeight() / 2));

            this.repaint();
        }

    }
    public void mouseMoved(MouseEvent e)
    { }

    public void lastLine(int reach,int xcurrent, int ycurrent, double norme, double xvector, double yvector){ //Trace la dernière ligne, asse en puissance de 2
        double pointsLeft = reach - listepoints.size();
        for(int i=0;i<(int)(norme*pointsLeft);i++)
        {
            listepoints.add(new Complex(xcurrent +i*xvector/pointsLeft - this.getWidth() / 2, ycurrent + i*yvector- this.getHeight() / 2));
        }
        while(listepoints.size()>reach){
            listepoints.remove(listepoints.size()-1);
        }
    }
}
