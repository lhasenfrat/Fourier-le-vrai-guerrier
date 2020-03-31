import java.util.LinkedList; 
public class TestDisp{
	public static void main(String[] args){
		LinkedList<Complexe> t = new LinkedList<Complexe>();
		t.add(new Complexe(1, 175, 0));
		t.add(new Complexe(1, 125, 0.7));
		t.add(new Complexe(1, 100, 4));
		t.add(new Complexe(1, 50, 2));
		t.add(new Complexe(1, 20, 1));
		t.add(new Complexe(1, 10, 3));
		FenetreTest f = new FenetreTest(t); 
		f.setVisible(true);
	}
}
