package cs1410;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/***********************************************************************
 * A CacheList is a collection of Cache objects together with these six
 * constraints:
 * 
 * <ol>
 * <li>A title constraint</li>
 * <li>An owner constraint</li>
 * <li>A minimum difficulty constraint</li>
 * <li>A maximum difficulty constraint</li>
 * <li>A minimum terrain constraint</li>
 * <li>A maximum terrain constraint</li>
 * </ol>
 * 
 * @author Snehashish Mishra
 ************************************************************************/
public class CacheList
{
    // The caches being managed by this CacheList. They are arranged in
    // ascending order according to cache title.
    private ArrayList<Cache> allCaches;
    
    //Other members of the class
    private String titleConstraint;
    private String ownerConstraint;
    private double minDifficulty, maxDifficulty;
    private double minTerrain, maxTerrain;

    /*****************************************************************************
     * Creates a CacheList from the specified Scanner. Each line of the Scanner
     * should contain the description of a cache in a format suitable for
     * consumption by the Cache constructor. The resulting CacheList should
     * contain one Cache object corresponding to each line of the Scanner.
     * 
     * Sets the initial value of the title and owner constraints to the empty
     * string, sets the minimum difficulty and terrain constraints to 1.0, and
     * sets the maximum difficulty and terrain constraints to 5.0.
     * 
     * Throws an IOException if the Scanner throws an IOException, or
     * an IllegalArgumentException if any of the individual lines are not
     * appropriate for the Cache constructor.
     * 
     * When an IllegalArgumentException e is thrown, e.getMessage() is the
     * number of the line that was being read when the error that triggered the
     * exception was encountered. Lines are numbered beginning with 1.
     *******************************************************************************/
    public CacheList (Scanner caches) throws IOException
    {
        allCaches = new ArrayList<Cache>();
        int exceptionLine = 1;		//Since lines are numbered beginning at 1
        
        try		//Check if individual lines are not appropriate for Cache constructor
        {
        	while(caches.hasNextLine())
        	{
        		allCaches.add(new Cache(caches.nextLine()) );
        		exceptionLine++;		//Keep track of current line number
        	}
        }
        catch(IllegalArgumentException a)
        {
        	//Pass the line number where the exception occurred
        	throw new IllegalArgumentException(Integer.toString(exceptionLine));
        }
        
        Collections.sort(allCaches, new CacheComparator());
        
        titleConstraint = "";
        ownerConstraint = "";
        minDifficulty = 1.0d;
        minTerrain = 1.0d;
        maxDifficulty = 5.0d;
        maxTerrain = 5.0d;
    }

    /*****************************************************
     * Sets the title constraint of 'this' object 
     * to the specified value.
     *****************************************************/
    public void setTitleConstraint (String title)
    {
        titleConstraint = title;
    }

    /*****************************************************
     * Sets the owner constraint to the specified value.
     *****************************************************/
    public void setOwnerConstraint (String owner)
    {
        ownerConstraint = owner;
    }

    /************************************************************************
     * Sets the minimum and maximum difficulty constraints to the specified
     * values.
     ************************************************************************/
    public void setDifficultyConstraints (double min, double max)
    {
    	minDifficulty = min;
    	maxDifficulty = max;
    }

    /***************************************************************************
     * Sets the minimum and maximum terrain constraints to the specified values.
     ***************************************************************************/
    public void setTerrainConstraints (double min, double max)
    {
    	minTerrain = min;
    	maxTerrain = max;
    }

    /***************************************************************************
     * Returns a list that contains each cache c from the CacheList so long as
     * c's title contains the title constraint as a substring, c's owner equals
     * the owner constraint (unless the owner constraint is empty), c's
     * difficulty rating is between the minimum and maximum difficulties
     * (inclusive), and c's terrain rating is between the minimum and maximum
     * terrains (inclusive).
     * 
     * The returned list is arranged in ascending order by cache title.
     ****************************************************************************/
    public ArrayList<Cache> select ()
    {
        ArrayList<Cache> caches = new ArrayList<Cache>();
        
        //Create an iterator over ArrayList of Cache collection 
        //and loop through it until the last item
        for(Iterator<Cache> i = allCaches.iterator(); i.hasNext();)
        {
        	Cache c = (Cache)i.next();
        	
        	//Check above conditions and if met, add cache 'c' to 'caches' ArrayList
        	if(c.getTitle().contains(titleConstraint) && 
        	   (ownerConstraint.equals("") || c.getOwner().equals(ownerConstraint)) &&
        	   c.getDifficulty() >= minDifficulty && c.getDifficulty() <= maxDifficulty &&
        	   c.getTerrain() >= minTerrain && c.getTerrain() <= maxTerrain )
        	{
        		caches.add(c);	//If meets all the requirements, add the 'cache c' to 'caches' list
        	}
        }
        return caches;
    }

    /**************************************************************************
     * Returns a list containing all the owners of all of the Cache objects in
     * this CacheList. There are no duplicates. The list is arranged in
     * ascending order.
     **************************************************************************/
    public ArrayList<String> getOwners ()
    {
        //Create an empty ownersSet HashSet to avoid duplicates
        HashSet<String> ownersSet = new HashSet<String>();
        
        Cache c;
        
        //Creates iterator from allCaches, 
        //loops through until i has items,
        //and adds the owner of current cache object in ownersSet HashSet.
        for(Iterator<Cache> i = allCaches.iterator(); i.hasNext(); ownersSet.add(c.getOwner()))
        {
        	c = (Cache)i.next();	//Get current item from iterator i
        }
        
        //Copy final HashSet to 'owners' list
        ArrayList<String> owners = new ArrayList<String>(ownersSet);
        
        //Sort 'owners' list
        Collections.sort(owners, new StringComparator());
        
        return owners;
    }

    /****************************************
     * Useful for sorting lists of strings.
     ****************************************/
    private class StringComparator implements Comparator<String>
    {
        public int compare (String s1, String s2)
        {
            return s1.compareToIgnoreCase(s2);
        }
    }

    /**************************************
     * Useful for sorting lists of caches.
     **************************************/
    private class CacheComparator implements Comparator<Cache>
    {
        public int compare (Cache c1, Cache c2)
        {
            return c1.getTitle().compareToIgnoreCase(c2.getTitle());
        }
    }
}
