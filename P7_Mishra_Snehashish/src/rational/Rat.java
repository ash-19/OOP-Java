package rational;

import java.math.BigDecimal;
import java.math.BigInteger;

/****************************************************
 * Provides rational number (fraction) objects.
 * @author Snehashish Mishra
 ****************************************************/
public class Rat
{
    // These two instance variables represent the rational number num/den
    // These constraints are maintained:
    //    gcd(num, den) = 1
    //    den > 0
    private BigInteger num;
    private BigInteger den;
    
    /********************************
     * Creates the rational number 0
     ********************************/
    public Rat ()
    {
        num = new BigInteger(0 + "");
        den = new BigInteger(1 + "");
    }

    /**********************************
     * Creates the rational number n
     **********************************/
    public Rat (long n)
    {
        num = new BigInteger(n + "");
        den = new BigInteger(1 + "");
    }

    /****************************************************
     * Takes in num and den as longs.
     * If d is zero, throws an IllegalArgumentException. 
     * Otherwise creates the rational number n/d
     ****************************************************/
    public Rat (long n, long d)
    {
    	num = new BigInteger(n + "");
    	den = new BigInteger(d + "");
    	
    	//Is denominator zero?
    	if(den.equals(BigInteger.ZERO))
    		throw new IllegalArgumentException("Denominator is Zero!");
    	
    	if(den.compareTo(BigInteger.ZERO) < 0)	// Make neg. denominator positive
    	{
    		den = den.negate();
    		num = num.negate();
    	}
    	
    	BigInteger g = num.gcd(den);			// Find GCD between num and den
    	num = num.divide(g);					// Reduce num to lowest form
    	den = den.divide(g);					// Reduce den to lowest form
    }

    /****************************************************
     * Takes in num and den as BigIntegers.
     * If d is zero, throws an IllegalArgumentException. 
     * Otherwise creates the rational number n/d 
     * using BigInteger type
     ****************************************************/
    public Rat (BigInteger n, BigInteger d)
    {
    	num = new BigInteger(n + "");
    	den = new BigInteger(d + "");
    	
    	// Is denominator zero?
    	if(den.equals(BigInteger.ZERO))
    		throw new IllegalArgumentException("Denominator is Zero!");
    	
    	if(den.compareTo(BigInteger.ZERO) < 0)	// Make neg. denominator positive
    	{
    		den = den.negate();
    		num = num.negate();
    	}
    	
    	BigInteger g = num.gcd(den);			// Find GCD between num and den
    	num = num.divide(g);					// Reduce num to lowest form
    	den = den.divide(g);					// Reduce den to lowest form
    }

	/*********************************
     * Returns the sum of this and r
     * Rat x = new Rat(5, 3);
     * Rat y = new Rat(1, 5);
     * Rat z = x.add(y);
     * a/b + c/d = (ad + bc) / bd
     **********************************/
    public Rat add (Rat r)
    {
    	Rat a = this;
    	
    	// Numerator of final rat = ad + bc
    	BigInteger numerator = a.num.multiply(r.den).add(r.num.multiply(a.den));
    	
    	// Denominator of final rat = bd
    	BigInteger denominator = a.den.multiply(r.den);
    	
    	return new Rat(numerator, denominator);	//Returns a new Rat which is the sum of this and r
    }

    /***************************************
     * Returns the difference of this and r
     * Rat x = new Rat(5, 3);
     * Rat y = new Rat(1, 5);
     * Rat z = x.sub(y);
     * a/b + c/d = (ad - bc) / bd
     ****************************************/
    public Rat sub (Rat r)
    {
    	Rat a = this;
    	
    	// Numerator of final rat = ad - bc
    	BigInteger numerator = a.num.multiply(r.den).subtract((r.num.multiply(a.den)));
    	
    	// Denominator of final rat = bd
    	BigInteger denominator = a.den.multiply(r.den);
    	
        return new Rat(numerator, denominator);	//Returns a new Rat which is the difference of this and r
    }

    /*************************************
     * Returns the product of this and r
     * Rat x = new Rat(5, 3);
     * Rat y = new Rat(1, 5);
     * Rat z = x.mul(y);
     * a/b * c/d = ac/bd
     **************************************/
    public Rat mul (Rat r)
    {
    	Rat a = this;
    	
    	BigInteger numerator = a.num.multiply(r.num);	   // Numerator of final rat = ac
    	BigInteger denominator = a.den.multiply(r.den);    // Denominator of final rat = bd

        return new Rat(numerator, denominator);
    }

    /****************************************************
     * If r is zero, throws an IllegalArgumentException. 
     * Otherwise, returns the quotient of this and r.
     * a/b * c/d = ad/bc
     ****************************************************/
    public Rat div (Rat r)
    {
    	Rat a = this;
    	
    	// Is second rational number zero?
    	if(r.num.equals(BigInteger.ZERO))
    		throw new IllegalArgumentException("Passed rational number is zero");
    	
    	BigInteger numerator = a.num.multiply(r.den);		// Numerator of final rat = ad 
    	BigInteger denominator = a.den.multiply(r.num);    // Denominator of final rat = bc

        return new Rat(numerator, denominator);
    }

    /**********************************************
     * Returns a negative number if this < r, 
     * zero if this = r, 
     * a positive number if this > r.
     * To compare a/b and c/d, compare ad and bc   
     **********************************************/
    public int compareTo (Rat r)
    {
    	Rat a = this;
    	
    	BigInteger left = a.num.multiply(r.den);		// ad
    	BigInteger right = a.den.multiply(r.num);		// bc
    	
    	return left.compareTo(right);	//compareTo() returns -1 for <, 0 for ==, 1 for >
    }

    /****************************************************
     * Returns a string version of this in simplest 
     * and lowest terms. Examples: 3/4 => "3/4" ,
     * 6/8 => "3/4" , 2/1 => "2", 
     * 0/8 => "0", 3/-4 => "-3/4"
     ****************************************************/
    @Override
    public String toString ()
    {
    	if(den.equals(BigInteger.ONE))
    		return num + "";
    	else
    		return num + "/" + den;
    }

    /****************************************************
     * Converts this to the closest double
     ***************************************************/
    public double toDouble ()
    {
    	BigDecimal numerator = new BigDecimal(num);		//Since can't cast BigIntgers to double
    	BigDecimal denominator = new BigDecimal(den);
    	
    	// quotient of num/den with 16 digits after decimal place, HALF_EVEN rounding mode
    	BigDecimal q = numerator.divide(denominator, 16, 6);
    	
    	return q.doubleValue();		//Return double value of quotient obtained
    }

    /*************************************************************************
     * CAN IGNORE THIS METHOD SINCE JOSEPH SAID NOT NEEDED
     * AND DIDN'T USE ANYWHERE
     * Returns the greatest common divisor of a and b, where a >= 0 and b > 0.
     *************************************************************************/
    public static long gcd (long a, long b)
    {
    	BigInteger newA = new BigInteger(a + "");
    	BigInteger newB = new BigInteger(b + "");
    	
    	return newA.gcd(newB).longValue();
    }

}
