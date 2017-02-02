package mobiles;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;

/**
 * A Bob is a kind of Mobile. A Bod consists of a weight hanging from a vertical
 * wire.
 * 
 * Here's a diagram:
 * 
 * <pre>
 *                             |
 *                             W
 * </pre>
 * 
 * W is a weight.
 */
public class Bob implements Mobile
{
    // The weight of the Bob
    private int weight;

    /**
     * Creates a Bob with the given weight.
     */
    public Bob(int weight)
    {
        this.weight = weight;
    }

    /**
     * Draws this Bob on g, beginning at point (x,y).
     */
    @Override
    public void display (Graphics2D g, double x, double y)
    {
        FontMetrics fm = g.getFontMetrics();
        int weightWidth = fm.stringWidth(weight + "");
        int height = fm.getHeight();
        g.draw(new Line2D.Double(x, y, x, y + WIRE));
        g.draw(new Arc2D.Double(x - height, y + WIRE, 2*height, 2*height, 0, 360, Arc2D.OPEN));
        g.drawString(weight + "", (float) (x - weightWidth / 2), (float) (y + WIRE + 1.25*height));
    }

    @Override
    public int weight ()
    {
        return weight;	// Return this.weight
    }

    @Override
    public boolean isBalanced ()
    {
        return true;	// When only bod, always balanced
    }

    @Override
    public int depth ()
    {
        return 1;		// Depth of bob is 1
    }

    @Override
    public int bobCount ()
    {
        return 1;		// Single Bob
    }

    @Override
    public int rodCount ()
    {
        return 0;		// No rods
    }

    @Override
    public int longestRod ()
    {
        return 0;		// No rods
    }

    @Override
    public int heaviestBob ()
    {
        return weight;	// Single bob. Thus, return this.weight
    }
}
