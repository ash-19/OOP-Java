package cs1410;

import static org.junit.Assert.*;

import org.junit.Test;

public class CacheTests
{	
    /*********************************************************
     * It tests all the attributes for a given Cache object c
     *********************************************************/
    @Test
    public void testAllAttributes ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCRQWK", c.getGcCode());
        assertEquals("geocadet", c.getOwner());
        assertEquals("Old Three Tooth", c.getTitle());
        assertEquals("N40 45.850", c.getLatitude());
        assertEquals("W111 48.045", c.getLongitude());
        assertEquals(3.5, c.getDifficulty(), 1e-6);
        assertEquals(3, c.getTerrain(), 1e-6);
    }
    
    //  NOTE: checkGcCode(String) is a private method.
    //  Thus, it is indirectly checked below when
    //  the passed string to Cache object c contains
    //  an invlaid GC-Code.
    
    /****************************************
     * It tests the exception handling when
     * the GC Code's prefix is not case-
     * sensitive: "Gc"
     ****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testGcCode1 ()
    {
        Cache c = new Cache("GcC7nb\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }
    
    /***************************************
     * It tests the exception handling when
     * the GC Code has an invalid prefix: 0C
     ***************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testGcCode2 ()
    {
        Cache c = new Cache("0CtbnY\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }
    
    /****************************************
     * It tests the exception handling when
     * fewer than 7 attributes are passed.
     ****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testFewerThanSeven ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\tN40 45.850\tW111 48.045");
    }
    
    /*****************************************
     * It tests the exception handling when
     * more than 7 attributes are passed
     *****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testMoreThanSeven ()
    {
        Cache c = new Cache("GCRQWK\tOld\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\textraAttribute\t");
    }
    
    /*****************************************
     * It tests the exception handling when
     * the difficulty cannot be parsed as a 
     * valid double: 3.5x
     *****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalDifficulty ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5x\t3\tN40 45.850\tW111 48.045");
    }
    
    /*****************************************
     * It tests the exception handling when
     * the terrain cannot be parsed as a 
     * valid double: e.5
     *****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalTerrainRating ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\te.5\t0\tN40 45.850\tW111 48.045");
    }
    
    /*****************************************
     * It tests the exception handling when
     * the difficulty and terrain rating is 
     * not within 1.0 to 5.0 range.
     * 
     * <li>NOTE: checkRating(double) method is 
     * indirectly tested here.
     *****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRange ()
    {
    	//Difficulty Rating: 3.7, Terrain Rating: 2.1
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.7\t2.1\tN40 45.850\tW111 48.045");
    }
    
    //NOTE: onlyWhitespaces(String) is a private method
    //Thus, it is indirectly tested below by passing
    //whitespaces as attributes to Cache object
    
    /*****************************************
    * It tests the exception handling when
    * the title is all whitespaces: "    "
    ******************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceString1 ()
    {
        Cache c = new Cache("GCRQWK\t    \tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
    }
    
    /*****************************************
     * It tests the exception handling when
     * owner, latitude, longitude are 
     * whitespaces: "  ", "\t", "\n" resp.
     *****************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceString2 ()
    {
        Cache c = new Cache("GCRQWK\tOld\t  \t3.5\t3\t\t\t\n");
    }
}
