import java.util.LinkedList; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class FenetreTest extends JFrame{
	private DispPan p;
	
	public FenetreTest(LinkedList<Complexe> tes, LinkedList<Complexe> tesFin ){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
        setLocation(100, 100);
        setBackground(Color.white);
        p = new DispPan(tes, tesFin);
        add(p);
	}
}
