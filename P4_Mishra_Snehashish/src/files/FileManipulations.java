package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*****************************************************
 * This class manipulates the file obtained from users
 * in any 4 given ways
 * 
 * @author Snehashish Mishra
 *****************************************************/
public class FileManipulations 
{
	/***************************************************
	 * This main method asks for a file from the user
	 * and performs any of the 4 operations on it
	 * as chosen by the user
	 ***************************************************/
	public static void main(String[] args) 
	{
		String askForUserFile = JOptionPane.showInputDialog("Enter the path for a text file");
		
		//Try creating a new file via user provided file
		try(Scanner userFile = new Scanner(new File(askForUserFile));)	
		{
			String askForOperation = JOptionPane.showInputDialog("Press, \n"
					+ "1 - To find the longest line in the above file\n"
					+ "2 - To find the longest palindrome token in the file\n"
					+ "3 - To find the number of times a given token 't' appears in the file\n"
					+ "4 - To find all the lines containing exactly 'n' tokens");
			try
			{
				//try for valid integer input
				int choice = Integer.parseInt(askForOperation);
				
				//Perform actions based on user's choice
				switch(choice)
				{
					//Find and return the longest line from the input file
					case 1:
					{
						String longestLineFound = longestLineInFile(userFile);
						JOptionPane.showMessageDialog(null, "Longest Line found is: " + longestLineFound);
						break;
					}
					
					//Find and return the longest palindrome from the input file
					case 2:
					{
						String longestPalindrome = longestPalindromeInFile(userFile);
						JOptionPane.showMessageDialog(null, "Longest palindrome found is: " + longestPalindrome);
						break;
					}
					
					//Find and return the number of time a given token 
					//appears in the file
					case 3:
					{
						String t = JOptionPane.showInputDialog("Enter a token t to count: ");
						int totalCount = countOfToken(userFile, t);
						JOptionPane.showMessageDialog(null, "Total number of times " 
						    + t + " occurs in the file: " + totalCount);
						break;
					}
					
					//Find all the lines with n number of tokens
					//and save all of them in the user provided file
					case 4:
					{
						String nTokens = JOptionPane.showInputDialog("Enter the number of tokens you"
								+ " wish for in a single line");
						String outputPathname = JOptionPane.showInputDialog("Enter the output filepath: ");
						
						//Check if the user input is valid
						try
						{
							int n = Integer.parseInt(nTokens);
							
							//Try creating an output file from user provided
							//output file path
							try(PrintStream outputFile = new PrintStream(outputPathname);)
							{
								linesWithNTokens(userFile, outputFile, n);
								JOptionPane.showMessageDialog(null, "Output file has been created at: "
										+ outputPathname);
							}
							
							//If unable to create output file
							catch(FileNotFoundException p)
							{
								JOptionPane.showMessageDialog(null, "File not created. Please enter a "
										+ "valid filepath ending with file_name.txt");
							}
						}
						
						//If the input = 'number of lines n' is invalid
						catch(NumberFormatException num)
						{
							JOptionPane.showMessageDialog(null, "Invalid number of tokens. Please enter "
									+ "an integer");
						}
						break;
					}
				}
			}
			
			//If the main choice (of 4 actions) is invalid
			catch(NumberFormatException n)
			{
				JOptionPane.showMessageDialog(null, "Invalid Choice. Input should be "
						+ "an integer");
			}
			
		}
		
		//If unable to find the user provided input file
		catch(FileNotFoundException f)
		{
			JOptionPane.showMessageDialog(null, "File Not Found");
		}
	}
	
	/*********************************************************
	 * Prints all the lines in the input file having n num of
	 * tokens in them to the passed output file. If the file
	 * with the same name already exists, then the existing
	 * file is overwritten.
	 *********************************************************/
	public static void linesWithNTokens(Scanner inputFile,
			PrintStream output, int passedLength) 
	{
		String currentLine = "";

		while(inputFile.hasNextLine())	//Prints all the lines with n tokens in the output file
		{
			currentLine = inputFile.nextLine();
			
			//Is number of tokens equal to passed length?
			if(countTokens(new Scanner(currentLine)) == passedLength)	
			{
				output.println(currentLine);
			}
		}
		
	}
	
	/******************************************
	 * Counts and returns the total number
	 * of tokens in a line
	 ******************************************/
	public static int countTokens(Scanner line) 
	{
		int tokenCount = 0;
		
		while(line.hasNext())
		{
			line.next();
			tokenCount++;
		}
		
		return tokenCount;
	}

	/*****************************************************
	 * Counts the number of times token t (case-sensitive)
	 * occurs in the input file
	 *****************************************************/
	public static int countOfToken(Scanner inputFile, String t) 
	{
		int tokenCount = 0;
		String currentToken = "";
		
		while(inputFile.hasNext())
		{
			currentToken = inputFile.next();
			if( (currentToken.compareTo(t)) == 0)	//Is the token found?
			{
				tokenCount++;
			}
		}
		return tokenCount;
	}

	/*********************************************
	 * Finds and returns the longest token in the
	 * file which is also a palindrome
	 *********************************************/
	public static String longestPalindromeInFile(Scanner inputFile) 
	{
		String longestPalindrome = "";
		String currentToken = "";
		
		while(inputFile.hasNext())
		{
			currentToken = inputFile.next();
			
			//Is token a palindrome and greater than the current largest?
			if(isPalindrome(currentToken) && currentToken.length() > longestPalindrome.length())
			{
				longestPalindrome = currentToken;
			}
		}
		return longestPalindrome;
	}


	/******************************************
	 * Checks if a given string is a palindrome
	 * (reading same from both sides)
	 ******************************************/
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

	/***********************************************************
	 * Finds and returns the longest line 
	 * from the user provided file.
	 ***********************************************************/
	public static String longestLineInFile(Scanner inputFile) 
	{
		String currentLine = "";
		String longestLineFound = "";
		
		while(inputFile.hasNextLine())
		{
			currentLine = inputFile.nextLine();
			if( currentLine.length() > longestLineFound.length() )
			{
				longestLineFound = currentLine;
			}
		}
		
		return longestLineFound;
	}

}
