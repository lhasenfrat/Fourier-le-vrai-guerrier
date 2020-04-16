import java.util.LinkedList; 


/******************************************************************************
 *  Compilation:  javac FFT.java
 *  Execution:    java FFT n
 *  Dependencies: Complex.java
 *
 *  Compute the FFT and inverse FFT of a length n complex sequence
 *  using the radix 2 Cooley-Tukey algorithm.

 *  Bare bones implementation that runs in O(n log n) time and O(n)
 *  space. Our goal is to optimize the clarity of the code, rather
 *  than performance.
 *
 *  This implementation uses the primitive root of unity w = e^(-2 pi i / n).
 *  Some resources use w = e^(2 pi i / n).
 *
 *  Reference: https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/05DivideAndConquerII.pdf
 *
 *  Limitations
 *  -----------
 *   -  assumes n is a power of 2
 *
 *   -  not the most memory efficient algorithm (because it uses
 *      an object type for representing complex numbers and because
 *      it re-allocates memory for the subarray, instead of doing
 *      in-place or reusing a single temporary array)
 *  
 *  For an in-place radix 2 Cooley-Tukey FFT, see
 *  https://introcs.cs.princeton.edu/java/97data/InplaceFFT.java.html
 *
 ******************************************************************************/

public class FFT {

    // compute the FFT of x[], assuming its length n is a power of 2
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // base case
        if (n == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        // compute FFT of even terms
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] evenFFT = fft(even);

        // compute FFT of odd terms
        Complex[] odd  = even;  // reuse the array (to avoid n log n space)
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] oddFFT = fft(odd);

        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = evenFFT[k].plus (wk.times(oddFFT[k]));
            y[k + n/2] = evenFFT[k].minus(wk.times(oddFFT[k]));
        }
        return y;
    }


    // compute the inverse FFT of x[], assuming its length n is a power of 2
    public static Complex[] ifft(Complex[] x) {
        int n = x.length;
        Complex[] y = new Complex[n];

        // take conjugate
        for (int i = 0; i < n; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < n; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by n
        for (int i = 0; i < n; i++) {
            y[i] = y[i].scale(1.0 / n);
        }

        return y;

    }

    // compute the circular convolution of x and y
    public static Complex[] cconvolve(Complex[] x, Complex[] y) {

        // should probably pad x and y with 0s so that they have same length
        // and are powers of 2
        if (x.length != y.length) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }

        int n = x.length;

        // compute FFT of each sequence
        Complex[] a = fft(x);
        Complex[] b = fft(y);

        // point-wise multiply
        Complex[] c = new Complex[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i].times(b[i]);
        }

        // compute inverse FFT
        return ifft(c);
    }


    // compute the linear convolution of x and y
    public static Complex[] convolve(Complex[] x, Complex[] y) {
        Complex ZERO = new Complex(0, 0);

        Complex[] a = new Complex[2*x.length];
        for (int i = 0;        i <   x.length; i++) a[i] = x[i];
        for (int i = x.length; i < 2*x.length; i++) a[i] = ZERO;

        Complex[] b = new Complex[2*y.length];
        for (int i = 0;        i <   y.length; i++) b[i] = y[i];
        for (int i = y.length; i < 2*y.length; i++) b[i] = ZERO;

        return cconvolve(a, b);
    }

    // compute the DFT of x[] via brute force (n^2 time)
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
                y[k] = y[k].plus(x[j].times(wkj).scale(0.00006103515625));
            }
        }
        return y;
    }

    // display an array of Complex numbers to standard output
    public static void show(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }
    
    public static void main(String[] args){
		Complex[] tableauDep = new  Complex[16384];
		double teta = 0;
		for (int i = 0 ; i < 1024 ; i++)
			tableauDep[i] = new Complex(128,i/8.0);		
		for (int i = 0 ; i < 2048 ; i++)
			tableauDep[i + 1024] = new Complex(128  - i/8.0,128);	
		for (int i = 0 ; i < 2048 ; i++)
			tableauDep[i + 3072] = new Complex(-128,128 - i/8.0);	
		for (int i = 0 ; i < 2048 ; i++)
			tableauDep[i + 5120] = new Complex(i/8.0 - 128,-128);
		for (int i = 0 ; i < 1024 ; i++)
			tableauDep[i + 7168] = new Complex(128,i/8.0 - 128);
		for (int i = 0 ; i < 8192 ; i++)
			tableauDep[i + 8192] = new Complex(0, 0);
				
		LinkedList<Complexe> listeDep = new LinkedList<Complexe>();
        for(int i = 0 ; i<16384 ; i++){
			listeDep.add(new Complexe(tableauDep[i].re(), tableauDep[i].im()));
		}
		
		Complex[] tableauFin = fft(tableauDep); 	
        LinkedList<Complexe> listeFin = new LinkedList<Complexe>();
        for(int i = 0 ; i<tableauDep.length ; i++){									// test avec un cercle
			listeFin.add(new Complexe(tableauFin[i].re(), tableauFin[i].im()));
		}		
		FenetreTest f = new FenetreTest(listeFin, listeDep);				//On affiche l'animation de sortie 
		f.setVisible(true);
    }

}


/*Copyright © 2000–2019, Robert Sedgewick and Kevin Wayne.
Last updated: Tue Jan 14 09:42:25 EST 2020.*/
