package cs1410;

import java.util.Scanner;

/***********************************************************
 * Contains 10 public, static methods based mainly 
 * on loops as per the description in the third assignment.
 * 
 * @author Snehashish Mishra
 * 
 ***********************************************************/

public class Loops 
{
	
	/******************************************
	 * Main method. Nothing in it since 
	 * wasn't mentioned in the assignment
	 ******************************************/
	public static void main(String[] args) 
	{	
		//Nothing to report here, commander!
	}
	
	
	/*********************************************************************
	 * It finds and returns the total number of positive factors 
	 * of the passed parameter which is supposed to be a positive integer
	 * @param n
	 *********************************************************************/
	public static int countFactors(int n)
	{
		int count = n;
		int totalFactors = 1;   //Positive integer will have atleast 1 factor
		while(count > 1)		//Counts all the factors of n
		{
			if(n % count == 0)  //If n is divisible, then count is a factor
			{
				totalFactors++;
			}
			count--;
		}
		return totalFactors;
	}
	
	
	/*************************************************
	 * It finds and returns the number of whitespace 
	 * characters contained in the passed string
	 * @param s
	 **************************************************/
	public static int countWhitespace(String s)
	{
		int index = 0, numOfWhiteSpaces = 0;
		char nextChar;							  //Makes it easier to track next extracted 
												  //char from string
		
		while(index < s.length())				  //Checks for spaces in s
		{
			nextChar = s.charAt(index);
			if(Character.isWhitespace(nextChar))  //Is the char whitespace?
			{
				numOfWhiteSpaces++;
			}
			index++;
		}
		
		return numOfWhiteSpaces;
	}
	
	
	/*******************************************************
	 * It returns the average length of all the tokens in s.
	 * If s is empty, it return zero
	 * @param s
	 *******************************************************/
	public static double averageTokenLength(Scanner s)
	{
		double cumTokenLength = 0;  // Cumulative length of all tokens
		int totalTokens = 0;	    // Total No of tokens
		String currentToken = "";	
		
		while(s.hasNext())			// Counts the no. of tokens and cum length
		{
			currentToken = s.next();
			cumTokenLength += currentToken.length(); //Add length of token to cum. length
			totalTokens++;
		}
		
		if(totalTokens == 0)		 // Means s is empty
		{
			return 0;
		}
		
		return cumTokenLength/totalTokens;	// Average length of tokens
	}
	
	
	/****************************************************************
	 * It checks if 'string t' is contained in 'string s' as a token, 
	 * in which case, it returns true. Else false. 
	 * @param s
	 * @param t
	 ****************************************************************/
	public static boolean containsToken(String s, String t)
	{
		Scanner searchToken = new Scanner (s);  	 // Convert string s to scanner
		String currentTokenInS = "";	
		
		while(searchToken.hasNext())				 // Checks if str t is in s
		{
			currentTokenInS = searchToken.next();
			
			if( (currentTokenInS.compareTo(t)) == 0) // If same, comapreTo() returns 0.
			{										 // Thus, if stat. evaluates to true.
				searchToken.close();				 // Close the scanner to avoid complications
				return true;
			}
		}
		
		searchToken.close();
		return false;
	}
	
	
	/****************************************************************
	 * This method finds and returns a line from s that contains t 
	 * as a token (if such a line exists) and returns null otherwise.
	 * @param s
	 * @param t
	 *****************************************************************/
	public static String findLineWithToken(Scanner s, String t)
	{
		String currentLine = "";
		
		while(s.hasNextLine())				   //Finds line in s containing t
		{
			currentLine = s.nextLine();
			if(containsToken(currentLine, t))  //Determines if token is embedded
			{
				return currentLine;
			}
		}
		
		return null;						   // t not embedded in s
	}
	
	
	/***************************************************
	 * This method determines if the passed string is 
	 * a palindrome (reads same forwards and backwards). 
	 * Returns true if it is, else returns false.
	 * @param s
	 ***************************************************/
	public static boolean isPalindrome(String s)
	{
		int index = s.length() - 1;		//Index of last char in s
		String reverse = "";
		
		while(index >= 0)				//Makes reverse of s
		{
			reverse += s.charAt(index);
			index--;
		}
		
		if(s.compareTo(reverse) == 0)	//Compares reverse to orig. str s
		{
			return true;
		}	
		
		return false;
		
	}
	
	
	/*********************************************************
	 * This method finds and returns the smallest factor of n 
	 * which is greater than 1.
	 * @param n
	 **********************************************************/
	public static int findSmallestFactor(int n)
	{
		int count = n;
		int smallestFactor = n;   //treat n as smallest and go down
		while(count > 1)		  //Finds other factors and determines smallest
		{
			if(n % count == 0)    //If n is divisible, then count is a factor
			{
				smallestFactor = count;
			}
			count--;
		}
		return smallestFactor;
	}
	
	
	/************************************************************
	 * This method finds the longest token which is a palindrome
	 * @param s
	 * @return if found, the longest token;
	 * else an empty string
	 *************************************************************/
	public static String findLongestPalindrome(Scanner s)
	{
		String currentToken = "";
		String longestPalindrome = "";
		
		while(s.hasNext())			     		//finds which token in s is a palindrome
		{
			currentToken = s.next();
			if( isPalindrome(currentToken) )	//Is current token a palindrome?
			{
				//Is it longer than the current longest palindrome?
				
				if(currentToken.length() > longestPalindrome.length() )	 
				{
					longestPalindrome = currentToken;
				}
			}
			
		}
		return longestPalindrome;
	}
	
	
	/**************************************************
	 * This method finds the longest line from s
	 * @param s
	 * @return If found, the longest line; else null
	 ***************************************************/
	public static String findLongestLine(Scanner s)
	{
		String currentLine = "";
		String longestLine = "";
		
		while(s.hasNextLine())								//Finds the longest line in s
		{
			currentLine = s.nextLine();
			if(currentLine.length() > longestLine.length())	//Is current line > longest one till now?
			{
				longestLine = currentLine;
			}
		}
		
		if(longestLine == "")						//If s is empty, longestLine will remain unchanged
		{
			return null;
		}
		
		return longestLine;
	}
	
	
	/*******************************************************
	 * This method finds the line with the most whitespaces
	 * @param s
	 * @return If found, line with most whitespaces;
	 * else null.
	 ********************************************************/
	public static String findMostWhitespace(Scanner s)
	{
		String currentLine = "";
		String mostWhiteSpaces = "";
		
		while(s.hasNextLine())				//Finds line with most whitespaces
		{
			currentLine = s.nextLine();
			
			//Is total no. of whitespaces in current line > the one with the most?
			
			if( countWhitespace(currentLine) > countWhitespace(mostWhiteSpaces) )
			{
				mostWhiteSpaces = currentLine;
			}
		}
		
		if(mostWhiteSpaces == "")			//passed scanner s is empty
		{
			return null;
		}
		
		return mostWhiteSpaces;
	}

	
}
