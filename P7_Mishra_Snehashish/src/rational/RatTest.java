package rational;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class RatTest
{
    @Test
    public void testConstructor1 ()
    {
        Rat r = new Rat();
        assertEquals("0", r.toString());
    }

    @Test
    public void testConstructor2 ()
    {
        Rat r = new Rat(5);
        assertEquals("5", r.toString());
    }

    @Test
    public void testConstructor3 ()
    {
        Rat r1 = new Rat(1, 2);
        assertEquals("1/2", r1.toString());

        Rat r2 = new Rat(5, 15);
        assertEquals("1/3", r2.toString());

        Rat r3 = new Rat(-4, -2);
        assertEquals("2", r3.toString());

        Rat r4 = new Rat(6, -8);
        assertEquals("-3/4", r4.toString());

        Rat r5 = new Rat(3, -15);
        assertEquals("-1/5", r5.toString());
        
        Rat r6 = new Rat(new BigInteger("20000000000000000000"), new BigInteger("2"));
        assertEquals("10000000000000000000", r6.toString());
        
        try {
            new Rat(4, 0);
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {           
        }
    }

    @Test
    public void testAdd ()
    {
        Rat r1 = new Rat(2,5);
        Rat r2 = new Rat(3,4);
        assertEquals("23/20", r1.add(r2).toString());
        
        r1 = new Rat(1, 7);
        r2 = new Rat(-1, 5);
        assertEquals("-2/35", r1.add(r2).toString());
        
        r1 = new Rat(new BigInteger("12345678000"), new BigInteger(1 + ""));
        r2 = new Rat(1, 3);
        assertEquals("37037034001/3", r1.add(r2).toString());
        
        r1 = new Rat(new BigInteger("1234567800000000"), new BigInteger(1 + ""));
        r2 = new Rat(1, 3);
        assertEquals("3703703400000001/3", r1.add(r2).toString());
    }

    @Test
    public void testSub ()
    {
        Rat r1 = new Rat(2,5);
        Rat r2 = new Rat(3,4);
        assertEquals("-7/20", r1.sub(r2).toString());
        
        r1 = new Rat(1, 7);
        r2 = new Rat(-1, 5);
        assertEquals("12/35", r1.sub(r2).toString());
        
        r1 = new Rat(new BigInteger("12345678000"), new BigInteger(1 + ""));
        r2 = new Rat(1, 3);
        assertEquals("37037033999/3", r1.sub(r2).toString());
        
        r1 = new Rat(new BigInteger("1234567800000000"), new BigInteger(1 + ""));
        r2 = new Rat(1, 3);
        assertEquals("3703703399999999/3", r1.sub(r2).toString());
    }

    @Test
    public void testMul ()
    {
        Rat r1 = new Rat(2,5);
        Rat r2 = new Rat(3,4);
        assertEquals("3/10", r1.mul(r2).toString());
        
        r1 = new Rat(1, 7);
        r2 = new Rat(-1, 5);
        assertEquals("-1/35", r1.mul(r2).toString());
        
        r1 = new Rat(new BigInteger("34567890"), new BigInteger(1 + ""));
        r2 = new Rat(new BigInteger("20000001"), new BigInteger("1"));
        assertEquals("691357834567890", r1.mul(r2).toString());
    }

    @Test
    public void testDiv ()
    {
        Rat r1 = new Rat(2,5);
        Rat r2 = new Rat(3,4);
        assertEquals("8/15", r1.div(r2).toString());
        
        r1 = new Rat(1, 7);
        r2 = new Rat(-1, 5);
        assertEquals("-5/7", r1.div(r2).toString());
        
        r1 = new Rat(new BigInteger("345678903840019"), new BigInteger(1 + ""));
        r2 = new Rat(new BigInteger("1"), new BigInteger("2"));
        assertEquals("691357807680038", r1.div(r2).toString());
        
        try {
            r1 = new Rat(3,4);
            r2 = new Rat(0);
            r1.div(r2);
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    @Test
    public void testCompareTo ()
    {
        Rat r1 = new Rat(3,4);
        Rat r2 = new Rat(6,8);
        Rat r3 = new Rat(1,2);
        assertEquals(0, r1.compareTo(r2));
        assertTrue(r1.compareTo(r3) > 0);
        assertTrue(r3.compareTo(r1) < 0);
    }

    @Test
    public void testToDouble ()
    {
        Rat r1 = new Rat(3,4);
        Rat r2 = new Rat(1,3);
        assertEquals(0.75, r1.toDouble(), 1e-12);
        assertEquals(0.3333333333333, r2.toDouble(), 1e-12);
    }

    //Can ignore it
    @Test
    public void testGcd ()
    {
        assertEquals(2, Rat.gcd(6, 20));
        assertEquals(1, Rat.gcd(11, 29));
        assertEquals(10, Rat.gcd(30, 20));
    }

}
