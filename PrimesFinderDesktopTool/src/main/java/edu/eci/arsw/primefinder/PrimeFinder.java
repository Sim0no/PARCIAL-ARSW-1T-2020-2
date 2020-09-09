package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The type Prime finder.
 */
public class PrimeFinder extends Thread{
        
	
	
    private boolean suspender;
    /**
     * The A.
     */
    BigInteger a, /**
     * The B.
     */
    b;
    /**
     * The Prs.
     */
    PrimesResultSet prs;

    /**
     * Instantiates a new Prime finder.
     *
     * @param _a  the a
     * @param _b  the b
     * @param prs the prs
     */
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

    /**
     * Is suspender boolean.
     *
     * @return the boolean
     */
    public boolean isSuspender() {
        return suspender;
    }

    /**
     * Sets suspender.
     *
     * @param suspender the suspender
     */
    public void setSuspender(boolean suspender) {
        this.suspender = suspender;
    }

    /**
     * Despertar.
     */
    public synchronized void despertar() {
        this.suspender=false;
        notify();
    }

    /**
     * Dormir.
     */
    public synchronized void dormir(){
        this.suspender=true;
    }

	
	
	
	
	
}
