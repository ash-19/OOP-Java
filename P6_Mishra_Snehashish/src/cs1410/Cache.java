package cs1410;

/*********************************************************************************
 * Represents a variety of information about a geocache. A geocache has a title,
 * an owner, a difficulty rating, a terrain rating, a GC code, a latitude, and a
 * longitude.
 * 
 * @author Snehashish Mishra
 **********************************************************************************/
public class Cache
{
    //Representation of the class members
	private String gcCode;
	private String title;
	private String owner;
	private double difficultyRating;
	private double terrainRating;
	private String latitude;
	private String longitude;

    /******************************************************************************
     * Creates a Cache from a string that consists of these seven cache
     * attributes: the GC code, the title, the owner, the difficulty rating, the
     * terrain rating, the latitude, and the longitude, in that order, separated
     * by single TAB ('\t') characters.
     * 
     * If any of the following problems are present, throws an
     * IllegalArgumentException:
     * <ul>
     * <li>Fewer than seven attributes</li>
     * <li>More than seven attributes</li>
     * <li>A GC code that is anything other than "GC" followed by one or more
     * upper-case letters and/or digits</li>
     * <li>A difficulty or terrain rating that parses to anything other than the
     * doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, or 5.</li>
     * <li>A title, owner, latitude, or longitude that consists only of white
     * space</li>
     ********************************************************************************/
    public Cache (String attributes)
    {
        // Split the string into individual attributes
    	String[] cacheAttributes;
    	cacheAttributes = attributes.split("\t");
    	
    	//Number of attributes Exception
    	if(cacheAttributes.length != 7)
    		throw new IllegalArgumentException("Incorrect number of Attributes");
    	
    	gcCode = cacheAttributes[0];
    	
    	//GC-Code invalid format exception
    	if( checkGcCode(gcCode))
    		throw new IllegalArgumentException("Invalid GC Code");
    	
    	title = cacheAttributes[1];
    	owner = cacheAttributes[2];
    	
    	//Check for parsing exception
    	try
    	{
    		difficultyRating = Double.parseDouble(cacheAttributes[3]);
    		terrainRating = Double.parseDouble(cacheAttributes[4]);
    	}
    	catch(NumberFormatException n)
    	{
    		throw new IllegalArgumentException("Incorrect Difficulty/Terrain Rating");
    	}
    	
    	//Check for double values range exception
    	if( checkRating(difficultyRating) || checkRating(terrainRating) )
    		throw new IllegalArgumentException("Unknown Difficulty/Terrain Rating");
    	
    	latitude = cacheAttributes[5];
    	longitude = cacheAttributes[6];
    	
    	//Whitespaces-only attribute exception
    	if(onlyWhitespaces(title) || onlyWhitespaces(owner) || 
    	   onlyWhitespaces(latitude) || onlyWhitespaces(longitude))
    		throw new IllegalArgumentException("Attribute with only whitespaces detected");
    }

    /**************************************************
     * Checks the range of the passed double value.
     * <li>If the num is not within 1.0 to 5.0, or is
     * not a multiple of 0.5, returns true.
     * <li>Else returns false
     **************************************************/
    private boolean checkRating(double rating) 
    {
		double num = rating;
		
		if(num >= 1.0d && num <= 5.0d)	//Check if num is inside the range
		{
			//Cross-check with 0.5 multiples within 1.0 to 5.0
			for(double i = 1.0d; i <= 5.0d; i += 0.5)
			{
				if(num == i)
					return false;	//num in range AND multiple of 0.5
			}
			return true;	//num in range but not a multiple of 0.5
		}
		else
			return true;	//num out of range
	}

	/***************************************************
     * Checks the format of the passed GC-Code string.
     * <li>If the GC-Code starts with the prefix "GC" and
     * consists of only uppercase letters or digits 
     * thereafter, then returns false.
     * <li>If the passed string is empty or does not
     * comply with the above format, returns true
     ****************************************************/
    private boolean checkGcCode(String input) 
    {
		String inputString = input;
		
    	if( inputString.isEmpty() )		//Empty GC-Code
    		return true;
    	
    	if( inputString.startsWith("GC"))	//Check Starting prefix
    	{
    		String temp = inputString.substring(2);	//Start after "GC"
    		
    		//Loop through each char from above string to check validity 
    		for(int i = 0; i < temp.length(); i++)
    		{
    			char c = temp.charAt(i);
    			
    			//If current character is neither in upper-case nor a digit
    			if( !Character.isUpperCase(c) && !Character.isDigit(c))
    				return true;
    		}
    	}
    	else
    		return true;		//Does not begin with "GC" sequence
    	
    	return false;			//Properly formatted GC-Code string
	}

	/************************************************
     * Checks and returns true if the passed string 
     * is entirely composed of whitespaces. If not, 
     * then returns false.
     ************************************************/
    private boolean onlyWhitespaces(String inputString) 
    {
		String temp = inputString;
		int counter = 0;			//To compare with passed string length 
		
		for(int i = 0; i < temp.length(); i++)
		{
			if(Character.isWhitespace(temp.charAt(i)))	//Is current char whitespace?
				counter++;
		}
		
		if(counter == temp.length())		//Does the passed string contains only whitespaces?
			return true;
		
		return false;
	}

	/************************************
     * Converts this cache to a string
     ************************************/
    public String toString ()
    {
        return getTitle() + " by " + getOwner();
    }

    /**********************************
     * Returns the owner of this cache
     **********************************/
    public String getOwner ()
    {
    	//Returns this.owner
        return owner;
    }

    /**********************************
     * Returns the title of this cache
     **********************************/
    public String getTitle ()
    {
        return title;
    }

    /**********************************************
     * Returns the difficulty rating of this cache
     **********************************************/
    public double getDifficulty ()
    {
        return difficultyRating;
    }

    /*******************************************
     * Returns the terrain rating of this cache
     *******************************************/
    public double getTerrain ()
    {
        return terrainRating;
    }

    /************************************
     * Returns the GC code of this cache
     ************************************/
    public String getGcCode ()
    {
        return gcCode;
    }

    /*************************************
     * Returns the latitude of this cache
     *************************************/
    public String getLatitude ()
    {
        return latitude;
    }

    /**************************************
     * Returns the longitude of this cache
     **************************************/
    public String getLongitude ()
    {
       return longitude;
    }
}
