package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class PrimeFinder extends Thread{
        
	
	
    private boolean suspender;
    BigInteger a,b;
    PrimesResultSet prs;
    public PrimeFinder(BigInteger _a, BigInteger _b, PrimesResultSet prs){
        this.a=_a;
        this.b=_b;
        this.prs = prs;
        this.suspender = true;


    }

    public void run(){
        boolean flag =false;
        int itCount=0;
        BigInteger i=this.a;
        MathUtilities mt=new MathUtilities();
        while (i.compareTo(this.b)<=0){
            System.out.println("xd");
            synchronized(this){
                while (isSuspender()){
                    try {
                        wait();
                    } catch (InterruptedException ignored) {

                    }
                }

            }
            itCount++;
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }

            i=i.add(BigInteger.ONE);
        }
    }
    public boolean isSuspender() {
        return suspender;
    }

    public void setSuspender(boolean suspender) {
        this.suspender = suspender;
    }
    public synchronized void despertar() {
        this.suspender=false;
        notify();
    }
    public synchronized void dormir(){
        this.suspender=true;
    }

	
	
	
	
	
}
