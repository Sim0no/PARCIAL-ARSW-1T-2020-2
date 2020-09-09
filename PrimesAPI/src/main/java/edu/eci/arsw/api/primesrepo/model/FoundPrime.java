package edu.eci.arsw.api.primesrepo.model;


/**
 * The type Found prime.
 */
public class FoundPrime
{
    /**
     * The User.
     */
    String user;

    /**
     * The Prime.
     */
    String prime;

    /**
     * Instantiates a new Found prime.
     */
    public FoundPrime(){}

    /**
     * Instantiates a new Found prime.
     *
     * @param user  the user
     * @param prime the prime
     */
    public FoundPrime(String user, String prime)
    {
        this.user=user;
        this.prime=prime;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser( String user )
    {
        this.user = user;
    }

    /**
     * Gets prime.
     *
     * @return the prime
     */
    public String getPrime()
    {
        return prime;
    }

    /**
     * Sets prime.
     *
     * @param prime the prime
     */
    public void setPrime( String prime )
    {
        this.prime = prime;
    }
}
