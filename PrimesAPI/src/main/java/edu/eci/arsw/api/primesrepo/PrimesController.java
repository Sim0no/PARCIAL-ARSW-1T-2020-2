package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * The type Primes controller.
 */
@RestController
public class PrimesController
{
    /**
     * The Prime service.
     */
    @Autowired
    PrimeService primeService;


    /**
     * Gets primes.
     *
     * @return the primes
     */
    @RequestMapping( value = "/primes", method = GET )
    public List<FoundPrime> getPrimes()
    {
        return primeService.getFoundPrimes();
    }


    /**
     * Return prime response entity.
     *
     * @param primeNumber the prime number
     * @return the response entity
     */
    @GetMapping("/primes/{primeNumber}")
    public ResponseEntity<?> returnPrime(@PathVariable String primeNumber) {
        try {
            FoundPrime data = primeService.getPrime(primeNumber);
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Not Prime found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add prime response entity.
     *
     * @param primeObject the prime object
     * @return the response entity
     */
    @PostMapping("/primes")
    public ResponseEntity<?> addPrime(@RequestBody FoundPrime primeObject) {
        try {
            primeService.addFoundPrime(primeObject);
            return new ResponseEntity<>(primeObject,HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Not possible, failed to add prime", HttpStatus.CONFLICT);
        }
    }






}
