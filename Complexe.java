public class Complexe{
    private double re;
    private double im;
    private double rho;
    private double theta;
    
    public Complexe(double r , double i){
        re = r;
        im = i;
        majPolaire();
    }
    
    public Complexe(int a , double r , double  o){
        if(a%2 == 1){
            this.rho = r;
            this.theta = o;
            majCartesien();
        }  
        else{
            re = r;
            im = o;
            majPolaire();
        }
        
        
    }
    
    public Complexe(){
        re = 0;
        im = 0;
        majPolaire();
    }
    
    public String toString(){
        if(this.im < 0)
            return  this.re + " - " + "i*" + (-this.im) ;
        else
            return   this.re + " + " + "*" + this.im ;
    }
    
    public double getRe(){
        return this.re;
    }
    
    public double getIm(){
        return this.im;
    }
    
    public double getTheta(){
        return this.theta;
    }

    public boolean estDansCadrant(int i){
        int k = i%4;
        boolean b = false;
        switch(k){
        case 1:
            b = (re>0 && im>0);
            break;
        
        case 2:
            b = (re<0 && im>0);
            break;


        
        case 3:
            b = (re<0 && im<0);
            break;


        
        case 4:
            b = (re>0 && im<0);
            break;

        }
        return b;
        
        
    }
    
    public boolean estDansCadrant(){
        if(re>0 && im>0)
            return true;
        else
            return false;
    }
    
    public Complexe milieu(Complexe c2){
		double reFin = (this.getRe() + c2.getRe())/2;
		double imFin = (this.getIm() + c2.getIm())/2;
		return new Complexe(reFin, imFin);
	}
    
    public double getRho(){
        return this.rho;
    }
    
    private void majPolaire(){
        this.rho = Math.sqrt(Math.pow(re,2) + Math.pow(im,2));
        this.theta = Math.atan2(im,re);
    }
    
    private void majCartesien(){
        this.re = Math.cos(this.theta)*rho;
        this.im = Math.sin(this.theta)*rho;
    }
    
    public void setRe(double x){
        this.re = x;
        majPolaire();
    }
    
    public void setIm(double x){
        this.im = x;
        majPolaire();
    }
    
    public void setRho(double x){
        this.rho = x;
        majCartesien();
    }
    
    public void setTheta(double x){
        this.theta  = x;
        majCartesien();
    }
    
    public void sommeV1(Complexe z1){
        this.re = this.re + z1.re;
        this.im = this.im + z1.im;
        majPolaire();
    }
    
    public Complexe sommeDiv(Complexe z, double n){
        return new Complexe(this.re + z.re/n , this.im + z.im/n);
    }
    
    public void rotate(double angle){
		this.theta = this.theta + angle;
		majCartesien();
	}
    
    public Complexe sommeV2(Complexe z){
        return new Complexe(this.re + z.re , this.im + z.im);
    }
    
    public Complexe mult(Complexe z){
        return new Complexe(1 , this.rho*z.rho , this.theta + z.theta);
    }
    
    public boolean equals(Complexe z){
        return this.toString().equals(z.toString());
    }
}
