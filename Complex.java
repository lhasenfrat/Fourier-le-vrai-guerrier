import java.util.Objects;

    public class Complex {
        private double re;   // the real part
        private double im;   // the imaginary part

        // create a new object with the given real and imaginary parts
        public Complex(double real, double imag) {
            re = real;
            im = imag;
        }

        // return a string representation of the invoking Complex object
        public String toString() {
            if (im == 0) return re + "";
            if (re == 0) return im + "i";
            if (im <  0) return re + " - " + (-im) + "i";
            return re + " + " + im + "i";
        }

        // return abs/modulus/magnitude
        public double abs() {
            return Math.hypot(re, im);
        }

        // return a new Complex object whose value is (this + b)
        public Complex plus(Complex b) {
            Complex a = this;             // invoking object
            double real = a.re + b.re;
            double imag = a.im + b.im;
            return new Complex(real, imag);
        }

        // return a new Complex object whose value is (this - b)
        public Complex minus(Complex b) {
            Complex a = this;
            double real = a.re - b.re;
            double imag = a.im - b.im;
            return new Complex(real, imag);
        }

        // return a new Complex object whose value is (this * b)
        public Complex times(Complex b) {
            Complex a = this;
            double real = a.re * b.re - a.im * b.im;
            double imag = a.re * b.im + a.im * b.re;
            return new Complex(real, imag);
        }

        // return the real or imaginary part
        public double re() { return re; }
        public double im() { return im; }

        //return theta, rho
        public double theta() {return Math.atan2(im,re);}
        public double rho() {return Math.sqrt(Math.pow(re,2) + Math.pow(im,2));}

        // return a / b
        public Complex divides(Complex b) {
            Complex a = this;
            return a.times(b.reciprocal());
        }

        public void rotate(double angle){
            double theta0=this.theta() + angle;
            double rho0=this.rho();
            this.re = Math.cos(theta0)*rho0;
            this.im = Math.sin(theta0)*rho0;
        }

        public void setRho(double rho){
            double theta0 = this.theta();
            this.re = Math.cos(theta0)*rho;
            this.im = Math.sin(theta0)*rho;
        }

        // a static version of plus
        public static Complex plus(Complex a, Complex b) {
            double real = a.re + b.re;
            double imag = a.im + b.im;
            Complex sum = new Complex(real, imag);
            return sum;
        }

        // See Section 3.3.
        public boolean equals(Object x) {
            if (x == null) return false;
            if (this.getClass() != x.getClass()) return false;
            Complex that = (Complex) x;
            return (this.re == that.re) && (this.im == that.im);
        }
        

    }


