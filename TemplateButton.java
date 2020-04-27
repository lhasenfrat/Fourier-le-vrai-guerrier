import javax.swing.*;
import java.awt.*;

public class TemplateButton extends JButton {
    boolean carre=false;
    boolean hexa=false;
    boolean losange=false;
    boolean formelouche=false;
    public TemplateButton(String forme)
    {


        setFont(new Font("Arial",Font.BOLD,50/2));

        if (forme == "carre")
        {
            carre=true;

        } else if (forme == "hexa")
        {
            hexa=true;

        } else if (forme == "losange")
        {
            losange=true;

        } else if (forme == "formelouche")
        {
            formelouche=true;

        }

    }
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.black);

        if (carre)
        {
            g.drawRect(getWidth()/4,getHeight()/4,getWidth()/2,getHeight()/2);
        } else if(hexa)
        {
            g.drawLine(getWidth()*5/16,getHeight()/4,getWidth()*11/16,getHeight()/4);
            g.drawLine(getWidth()*11/16,getHeight()/4, getWidth()*16/20,getHeight()/2);
            g.drawLine(getWidth()*16/20,getHeight()/2,getWidth()*11/16,getHeight()*3/4);
            g.drawLine(getWidth()*11/16,getHeight()*3/4,getWidth()*5/16,getHeight()*3/4);
            g.drawLine(getWidth()*5/16,getHeight()*3/4,getWidth()*1/5,getHeight()/2);
            g.drawLine(getWidth()*1/5,getHeight()/2,getWidth()*5/16,getHeight()/4);

            // g.drawOval(getWidth()/4,getHeight()/4,getWidth()/2,getHeight()/2);
        } else if(losange)
        {
            g.drawLine(getWidth()/2,getHeight()/4,3*getWidth()/4,getHeight()/2);
            g.drawLine(3*getWidth()/4,getHeight()/2,getWidth()/2,3*getHeight()/4);
            g.drawLine(getWidth()/2,getHeight()*3/4,getWidth()/4,getHeight()/2);
            g.drawLine(getWidth()/4,getHeight()/2,getWidth()/2,getHeight()/4);

        } else if(formelouche)
        {
            g.drawString("?",4*getWidth()/10,6*getWidth()/10);

        }
    }
}
