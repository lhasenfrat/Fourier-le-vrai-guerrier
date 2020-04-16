import java.util.LinkedList; 

public class NewTest {
    public static void main(String[] args){
		
        LinkedList<Complexe> listeDepart = new LinkedList<Complexe>();
        
        /*for(int i = 0 ; i<1024 ; i++){									// test avec un cercle
			listeDepart.add(new Complexe(1, 70, 2*Math.PI*i/1000)); 		
		}*/
		
		for (int i = 0 ; i< 100; i++)										// test avec un carré
			listeDepart.add(new Complexe(100,i));
		for (int i = 0 ; i< 200; i++)
			listeDepart.add(new Complexe(100 - i,100));
		for (int i = 0 ; i< 200; i++)
			listeDepart.add(new Complexe(-100,100-i));
		for (int i = 0 ; i< 200; i++)
			listeDepart.add(new Complexe(i - 100,-100));
		for (int i = 0 ; i< 100; i++)
			listeDepart.add(new Complexe(100,i - 100));
			
			
			
        LinkedList<Complexe> listeFin = tFourier(listeDepart,100);			//On applique la tranformée
		FenetreTest f = new FenetreTest(listeFin, listeDepart);				//On affiche l'animation de sortie 
		f.setVisible(true);
    }
    public static Complex[] dft(Complex[] x) {
        int n = x.length;
        Complex ZERO = new Complex(0, 0);
        Complex[] y = new Complex[n];
        for (int k = 0; k < n; k++) {
            y[k] = ZERO;
            for (int j = 0; j < n; j++) {
                int power = (k * j) % n;
                double kth = -2 * power *  Math.PI / n;
                Complex wkj = new Complex(Math.cos(kth), Math.sin(kth));
                y[k] = y[k].plus(x[j].times(wkj));
            }
        }
        return y;
    }
    
	public static LinkedList<Complexe> tFourier(LinkedList<Complexe> l, int nbVect){	//méthode qui effectue la transformée de fourier
			int nbPoints = l.size();
			Complexe integrale = new Complexe(0,0);
			LinkedList<Complexe> renvoi = new LinkedList<Complexe>();
			for(int i = 0 ; i < nbPoints ; i++){
				integrale = new Complexe(0,0);
				int t = 0;
			for(Complexe c : l){
				int power = (t * i) % nbPoints;
				double kth = -2 * power *  Math.PI / nbPoints;
				Complexe wkj = new Complexe(Math.cos(kth), Math.sin(kth));
				integrale.sommeV1(c.mult(wkj));
				t++;
			}
			integrale.setRho(integrale.getRho()/(nbPoints));
			renvoi.add(integrale);
		}
		for(int i = 0 ; i < 2 ; i++){
			renvoi.removeLast();
		}

		
			return(renvoi);
		}
}
