package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    public static void main(String[] args) throws InterruptedException {

        int maxPrim = 1000;
        final int numeroHilos = 4;
        final int rangoMaximo=10000;

        PrimesResultSet prs = new PrimesResultSet("simon");

        ArrayList<PrimeFinder> hilos = new ArrayList<PrimeFinder>();
        int rango = rangoMaximo/numeroHilos;
        int rangoInicial;
        int rangoFinal;

        for (int i = 0; i < numeroHilos; i++) {
            if (i != numeroHilos - 1) {
                rangoInicial = rango * i;
                rangoFinal = rango + rangoInicial-1;
                hilos.add(new PrimeFinder(new BigInteger(Integer.toString(rangoInicial)), new BigInteger(Integer.toString(rangoFinal)), prs));

            }
            else{
                rangoInicial = rango * i;
                rangoFinal = rangoMaximo;
                hilos.add(new PrimeFinder(new BigInteger(Integer.toString(rangoInicial)), new BigInteger(Integer.toString(rangoFinal)), prs));
            }
//           
        }
//        PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("10000"), prs);

        for (PrimeFinder p:hilos) {
            p.start();
        }
        for (PrimeFinder p:hilos) {
            p.despertar();
        }


        System.out.println("Prime numbers found:");

        System.out.println(prs.getPrimes());

        boolean task_not_finished = true;
        while (task_not_finished) {
            try {
                //check every 10ms if the idle status (10 seconds without mouse
                //activity) was reached.
                Thread.sleep(10);
                
                if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                    System.out.println("Idle CPU ");
                } else {
                    System.out.println("User working again!");
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}


