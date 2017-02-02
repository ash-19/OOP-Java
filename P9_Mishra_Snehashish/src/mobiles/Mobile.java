package mobiles;

import java.awt.Graphics2D;

/**
 * A Mobile is either a Rod or a Bob.
 */
public interface Mobile
{
    /** 
     * Draws this Mobile on g, beginning at point (x,y).
     */
    public void display (Graphics2D g, double x, double y);
    
    /**
     * Returns the total weight of all the Bobs in this Mobile.
     */
    public int weight ();
    
    /**
     * Reports whether all the Rods in this Mobile are completely
     * horizontal.  A Rod will be horizontal if the product of its
     * left length and the weight of its left Mobile equals the
     * product of its right length and the weight of its right Mobile.
     */
    public boolean isBalanced ();
    
    /**
     * Returns the length of the longest path through this Mobile.  There is
     * one path for every Bob in the Mobile.  Each path leads from the top of
     * the Mobile to a Bob, and its length is the number of Rods encountered
     * along the way plus one.
     */
    public int depth ();
    
    /**
     * Returns the number of Bobs contained in this Mobile.
     */
    public int bobCount ();
    
    /**
     * Returns the number of Rods contained in this Mobile.
     */
    public int rodCount ();
    
    /**
     * Returns the length of the longest Rod contained in this Mobile.
     * If there are no Rods, returns zero.
     */
    public int longestRod ();
    
    /**
     * Returns the weight of the heaviest Bob contained in this Mobile.
     */
    public int heaviestBob ();
        
    
    public final static double WIRE = 100;
    public final static double UNIT = 10;
    public final static double GAP = 2;
    public final static double TOP = 10;
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
}
