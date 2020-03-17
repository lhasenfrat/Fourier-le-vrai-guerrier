import java.util.LinkedList; 

public class NewTest {
    public static void main(String[] args){
		
        LinkedList<Complexe> listeDepart = new LinkedList<Complexe>();
        
        /*for(int i = 0 ; i<1000 ; i++){									// test avec un cercle
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
			
        LinkedList<Complexe> listeFin = tFourier(listeDepart,780);			//On applique la tranformée
		FenetreTest f = new FenetreTest(listeFin, listeDepart);				//On affiche l'animation de sortie 
		f.setVisible(true);
    }
    
    public static LinkedList<Complexe> tFourier(LinkedList<Complexe> l, int nbVect){	//méthode qui effectue la transformée de fourier et l'affiche dans le DispPan
		double nbPoints = l.size();
		Complexe integrale = new Complexe(0,0);
		LinkedList<Complexe> renvoi = new LinkedList<Complexe>();
		for(int i = 0 ; i < nbVect ; i++){
			integrale = new Complexe(0,0);
			double t = 0;
		for(Complexe c : l){
			Complexe sc = new Complexe(1, c.getRho(), c.getTheta() - (i*Math.PI*2*t/nbPoints)); 
			integrale.sommeV1(sc);
			t++;
		}
		integrale.setRho(integrale.getRho()/(nbPoints));
		renvoi.add(integrale);
	}

	
		return(renvoi);
	}
}
