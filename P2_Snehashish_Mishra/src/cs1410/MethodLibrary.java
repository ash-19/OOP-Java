package cs1410;

import java.util.Scanner;

/**
 * A collection of methods for the second assignment of CS 1410.
 * 
 * @author Snehashish Mishra
 */
public class MethodLibrary
{
    /**
     * You can use this main method for experimenting with your methods if you
     * like, but it is not part of the assignment.
     */
    public static void main (String[] args)
    {
    	//It says above that main method not part of the assignment.
    	//Thus, I have not called any of the functions below from the main method. 
    	
    }

    /**
     * Returns the string that results from capitalizing the first character of
     * word, which is required to have at least one character. For example,
     * capitalize("hello") is "Hello" and capitalize("Jack") is "Jack".
     * 
     * IMPLEMENTATION NOTE: The Character.toUpperCase() method will be helpful
     * s.charAt() and s.substring() [where s is a String] will also be helpful
     * Learn more about them before starting!
     */
    public static String capitalize (String word)
    {
        String capitalized = "";
        
        char firstChar = word.charAt(0);
        
        if(Character.isLetter(firstChar) && Character.isLowerCase(firstChar))
        {
        	capitalized = capitalized + Character.toUpperCase(firstChar);
        }
        
        else    //if the 1st char is not lowercase character, then appends it as it is
        {
        	capitalized = capitalized + firstChar;
        }
        
        //Append beginning from the 2nd char (index = 1) of input string
        capitalized = capitalized + word.substring(1);
        
    	return capitalized;
    }

    /**
     * Reports whether or not c is a vowel ('a', 'e', 'i', 'o', 'u' or an
     * upper-case version).
     */
    public static boolean isVowel (char c)
    {
    	int index = 0;
    	char comparisonChar;
    	String vowels = "aeiouAEIOU";
    	
    	//Loop to compare each character -- in the vowels string above -- one by one 
    	//to the passed input character. If match occurs, returns true. Else returns false.
    	
    	while(index < vowels.length())
    	{
    		comparisonChar = vowels.charAt(index);
    		if( comparisonChar == c)
    		{
    			return true;
    		}
    		index++;
    	}
    	
    	return false;
    }

    /**
     * Returns the index within s of the first vowel ('a', 'e', 'i', 'o', 'u' or
     * an upper-case version) that occurs in s. If there is no vowel in s,
     * returns -1.
     * 
     * For example, the method should return 0 for "Apple", 1 for "hello", 2 for
     * "slope", 3 for "strength", and -1 for "xyzzy".
     * 
     * IMPLEMENTATION NOTE: This method is already completely implemented. There
     * is no need for you to change anything.
     */
    public static int firstVowelIndex (String s)
    {
	int i = 0;
	while (i < s.length())
	{
	    if (isVowel(s.charAt(i)))
	    {
		return i;
	    }
	    i = i + 1;
	}
	return -1;
    }

    /**
     * Returns the result of converting s to "Pig Latin". Convert a string s to
     * Pig Latin by using the following rules:
     * 
     * (1) If s contains no vowels, do nothing to it.
     * 
     * (2) Otherwise, if s starts with a vowel, append "way" to the end.
     * 
     * (3) Otherwise, move everything up to (but not including) the first vowel
     * to the end and add "ay".
     * 
     * For example, "hello" converts to "ellohay", "small" converts to
     * "allsmay", "eat" converts to "eatway", and "nth" converts to "nth".
     * 
     * IMPLEMENTATION NOTE: For full credit (and an easier implementation), use
     * the firstVowelIndex method provided above in your method's
     * implementation. You will also find the substring and charAt methods
     * available on String objects very useful.
     */
    public static String toPigLatin (String s)
    {
    	String finalPigLatin = "";
    	
    	//Condition no. (2). If first char is vowel, append "way" to orig string
    	if(firstVowelIndex(s) == 0)
    	{
    		return s + "way";
    	}
    	
    	//Condition no. (1). If no vowels found, return string as it is
    	else if (firstVowelIndex(s) == -1)
    	{
    		return s;
    	}
    	
    	//Condition no. (3)
    	else
    	{
    		finalPigLatin = finalPigLatin + s.substring(firstVowelIndex(s));
    		finalPigLatin = finalPigLatin + s.substring(0, (firstVowelIndex(s)));
    		finalPigLatin = finalPigLatin + "ay";
    	}
    	
    	return finalPigLatin;
    }

    /**
     * Returns the result of converting each word from words into Pig Latin and
     * then appending the results, with each converted word followed by a single
     * space character. A word is a sequence of characters separated from other
     * words by white space. If there are no words, returns the empty string.
     * 
     * For example, "" converts to "" and "This is a test" converts to
     * "isThay isway away esttay ".
     * 
     * IMPLEMENTATION NOTE: Use a Scanner to split the string into individual
     * words.
     */
    public static String convertToPigLatin (String words)
    {
    	Scanner inputString = new Scanner(words);
    	String convertedWord = "";
    	
    	//Convert each token to pigLatin and append to the above string
    	while (inputString.hasNext())
        {
            String token = inputString.next();
            convertedWord = convertedWord + toPigLatin(token) + " ";   
        }
    	
    	inputString.close();		//Closed the scanner to avoid any leaks
    	
    	return convertedWord;
    }

    /**
     * Returns a String of length width that begins and ends with the character
     * edge and contains width-2 copies of the character inner in between. For
     * example, if edge is '+', inner is '-', and width is 8, the method should
     * return "+------+".
     * 
     * The method does not print anything. The parameter width must be greater
     * than or equal to 2.
     * 
     * IMPLEMENTATION NOTE: You'll need to use a loop to append the correct
     * number of inner characters.
     */
    public static String makeLine (char edge, char inner, int width)
    {
    	String line = "";
    	int index;
    	
    	line = line + edge;							// Appends first + sign
    	 
    	for(index  = 1; index < width-1; index++)   // Loop to append width copies of "-" to +
    	{
    		line = line + inner;
    	}
    	
    	line = line + edge;							//Appends Last + sign
    	
    	return line;
    }

    /**
     * Returns a string which, when printed out, will be a rectangle shaped like
     * this:
     * 
     * <pre>
     * +----------+
     * |          |
     * |          |
     * |          |
     * |          |
     * |          |
     * +----------+
     * </pre>
     * 
     * The returned string should consist of height lines, each ending with a
     * newline. In addition to the newline, the first and last lines should
     * begin and end with '+' and should contain width-2 '-' symbols. In
     * addition to the newline, the other lines should begin and end with '|'
     * and should contain width-2 spaces.
     * 
     * The method does not print anything. Both parameters must be greater than
     * or equal to 2.
     * 
     * IMPLEMENTATION NOTE: For full credit (and for easier implementation),
     * make use of your makeLine method in your implementation of makeRectangle.
     * You'll need to use a loop to call makeLine the correct number of times.
     * 
     */
    public static String makeRectangle (int height, int width)
    {
    	String rectangle = "";
    	int index;
    	
    	rectangle = rectangle + makeLine('+', '-', width) + "\n";	  //1st Line
    	
    	for(index = 0; index < height-2; index++)					  // Loop to append height 
    	{															  // copies of lines
    		rectangle = rectangle + makeLine('|', ' ', width) + "\n";
    	}
    	
    	rectangle = rectangle + makeLine('+', '-', width) + "\n";	  //Last line
    	
    	return rectangle;
    }

    /**
     * Returns the integer that follows n in a Hailstone sequence. If n is 1,
     * returns 1. Otherwise, returns either n/2 (if n is even) or 3n+1 (if n is
     * odd). The parameter n must be positive.
     */
    public static int nextHailstone (int n)
    {
    	int hailstoneNumber;
    	
    	//Contains all even numbers including 0 since 0 % 2 = 0
    	if( (n % 2) == 0)
    	{
    		hailstoneNumber = n/2;
    	}
    	
    	else if (n == 1)
    	{
    		hailstoneNumber = n;
    	}
    	
    	//if not 1 or even, then it has to be odd
    	else
    	{
    		hailstoneNumber = ( (3*n) + 1 );
    	}
    	
    	return hailstoneNumber;
    }

    /**
     * Returns a string consisting of a Hailstone sequence beginning with the
     * positive integer n and ending with 1. The string should consist of a
     * sequence of numerals, with each numeral followed by a single space. When
     * a numeral m (other than 1) appears in the sequence, it should be followed
     * by nextHailstone(m).
     * 
     * For example, nextHailstone(1) should return "1 " and nextHailstone(5)
     * should return "5 16 8 4 2 1 ".
     * 
     * IMPLEMENTATION NOTE: Make use of your nextHailstone method. You'll need
     * to use a loop to call it the right number of times.
     */
    public static String hailstones (int n)
    {
    	int m = n;   //to be consistent with the problem's notation
    	String hailstoneSequence = "";
    	
    	do
    	{
    		hailstoneSequence = hailstoneSequence + m + " ";
    		m = nextHailstone(m);
    	}while(m != 1);
    	
    	// if n is 1 to begin with, the do while loop will run atleast once and we get
    	// the answer. So, no need to run the if statement when input num is 1.
    	
    	if(n != 1)
    	{
    		hailstoneSequence = hailstoneSequence + "1" + " ";
    	}
    		
    	return hailstoneSequence;
    }

    /**
     * Returns the number of words from the words parameter that have at least
     * min but no more than max characters. Words in the string are delimited by
     * white space. For example,
     * countWords("Now is the time for all good people", 4, 6) should return 3.
     * 
     * IMPLEMENTATION NOTE: Use a Scanner to split the string into individual
     * words.  We wrote a method in lecture to count tokens.
     */
    public static int countWords (String words, int min, int max)
    {
    	Scanner wordToken = new Scanner(words);
    	String eligibleWords;
    	int numberOfWords = 0;
    	
    	while(wordToken.hasNext())			//Counts the no. of words that meets criteria
    	{
    		eligibleWords = wordToken.next();
    		if ( (eligibleWords.length() >= min) && (eligibleWords.length() <= max) )
        	{
        		numberOfWords++;
        	}
    	}
    	wordToken.close();
    	
    	return numberOfWords;
    }

    /**
     * Returns the string that results from stripping all of the white space
     * from text.
     * 
     * IMPLEMENTATION NOTE:  Scanner comes to the rescue once again.  
     */
    public static String stripWhiteSpace (String text)
    {
    	Scanner textScanner = new Scanner(text);
    	String withoutSpaces = "";
    	
    	while(textScanner.hasNext())		//Appends tokens while removing spaces
    	{
    		withoutSpaces = withoutSpaces + textScanner.next();
    	}
    	
    	textScanner.close();
    	return withoutSpaces;
    }
}
