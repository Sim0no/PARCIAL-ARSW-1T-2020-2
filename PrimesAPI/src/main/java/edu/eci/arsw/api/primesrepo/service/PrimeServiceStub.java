package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * The type Prime service stub.
 */
@Service
public class PrimeServiceStub implements PrimeService
{

    /**
     * The Primos.
     */
    CopyOnWriteArrayList<FoundPrime> primos;

    /**
     * Instantiates a new Prime service stub.
     */
    public PrimeServiceStub() {
        this.primos = new CopyOnWriteArrayList<>();
        primos.add(new FoundPrime("Simoncho", "2"));
        primos.add(new FoundPrime("Felipe", "3"));
        primos.add(new FoundPrime("Juancho", "5"));
        primos.add(new FoundPrime("Santi", "7"));
        primos.add(new FoundPrime("Checho", "11"));
        primos.add(new FoundPrime("Javi", "13"));
        primos.add(new FoundPrime("Edwin", "17"));

    }
    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws Exception {
        for(FoundPrime f:primos) {
            if(f.getPrime().equals(foundPrime.getPrime())){
                throw new Exception("Primo ya existente");
            }
        }
        primos.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        return primos;
    }

    @Override
    public FoundPrime getPrime( String prime ) throws Exception {
        FoundPrime ans=null;
        for(FoundPrime f:primos) {
            if(f.getPrime().equals(prime)){
                ans=f;
                break;
            }
        }
        if(ans==null){
            throw new Exception("Primo no encontrado");
        }
        return ans;
    }
}
