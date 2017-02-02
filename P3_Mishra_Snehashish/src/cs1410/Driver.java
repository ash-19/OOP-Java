
/*******************************************************
 *Did do all 4 before Joseph announced its cancellation, 
 *but didn't test them.  
 *******************************************************/


package cs1410;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Contains an interactive driver for some of the methods in the Loop class
 * @author Snehashish Mishra
 */
public class Driver
{
	
    /**
     * An interactive driver for some of the methods in the Loop class.
     */
    public static void main (String[] args)
    {
        // The drivers for countFactors and findSmallestFactor should
        // be called here.
        driveCountFactors();
        driveSmallestFactor();
        
        // The drivers for findLineWithToken and findLongestPalindrome
        // should be called here.
        driveFindLineWithToken();
        driveFindLongestPalindrome();
    }

    
    /**
     * Prompts the user for an integer and displays the result of invoking the 
     * Loops.countFactors method.  Deals gracefully with input errors.
     */
    public static void driveCountFactors ()
    {
        // If a NumberFormatException happens inside of the try block, control
        // transfers immediately to the catch block.
        try {
            // Prompt for input and parse to obtain an int.  If null is returned,
            // that means the user canceled the dialog.
            String input = JOptionPane.showInputDialog("Enter positive integer");
            if (input == null)
            {
                return;
            }
            int n = Integer.parseInt(input);
            
            // Display an error message when the input is non-positive
            if (n <= 0) 
            {
                JOptionPane.showMessageDialog(null, "Value entered is not positive");
            }
            
            // Display the number of factors when the input is positive
            else
            {
                int factors = Loops.countFactors(n);
                JOptionPane.showMessageDialog(null, "Number of factors = " + factors);
            }
        }
        catch (NumberFormatException e)
        {
            // Display an error message when the input doesn't parse
            JOptionPane.showMessageDialog(null, "Value entered is not an integer");
        }
    }

    
    /**
     * Prompts the user for a File and a String, and then displays the result of invoking
     * Loops.findLineWithToken.  Deals gracefully with input errors.
     */
    public static void driveFindLineWithToken ()
    {
        // This allows the user to select a file, returning quietly if
        // the user cancels out of the file dialog.
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File file = chooser.getSelectedFile();
        //System.out.println(file);
        // This will automatically close the Scanner when control leaves the
        // try block.  In addition, if a FileNotFoundException occurs in the try
        // block, control immediately transfers to the catch block.
        try (Scanner s = new Scanner(file))
        {
            // Prompt for a token and make sure it isn't null, which indicates
            // that the user canceled.
            String token = JOptionPane.showInputDialog("Enter token to search for");
            if (token == null) 
            {
                return;
            }
            
            // Invoke Loops.findLineWithToken
            String line = Loops.findLineWithToken(s, token);
            
            // Report that the token entered by the user wasn't found
            if (line == null)
            {
                JOptionPane.showMessageDialog(null, "Token wasn't found");
            }
            
            // Display the line that contained the token
            else
            {
                JOptionPane.showMessageDialog(null, "Token found in this line:\n" + line);
            }
        }
        catch (FileNotFoundException e)
        {
            // Report that the file didn't exist or couldn't be opened
            JOptionPane.showMessageDialog(null, "No such file: " + file);
        }
    }

    
    /**
     *  Prompts the user for an integer and displays the result of invoking the 
     * 	Loops.findSmallestFactor method. Deals gracefully with input errors.
     */
    public static void driveSmallestFactor ()
    {
        // If a NumberFormatException happens inside of the try block, control
        // transfers immediately to the catch block.
        try {
            // Prompt for input and parse to obtain an int.  If null is returned,
            // that means the user canceled the dialog.
            String input = JOptionPane.showInputDialog("Enter a positive integer");
            if (input == null)
            {
                return;
            }
            int n = Integer.parseInt(input);
            
            // Display an error message when the input is non-positive
            if (n <= 0) 
            {
                JOptionPane.showMessageDialog(null, "Value entered is not positive");
            }
            
            // Display the smallest factor when the input is positive
            else
            {
                int factors = Loops.findSmallestFactor(n);
                JOptionPane.showMessageDialog(null, "Smallest Factor = " + factors);
            }
        }
        catch (NumberFormatException e)
        {
            // Display an error message when the input doesn't parse
            JOptionPane.showMessageDialog(null, "Value entered is not an integer");
        }
    }


    /**
     * Prompts the user for a File, and then displays the result of invoking
     * Loops.findLongestPalindrome. Deals gracefully no file selected. 
     * Only user input required in this method is specifying the file, rest
     * is automated to get the result (longest palindrome or not).
     */
    
    public static void driveFindLongestPalindrome ()
    {
        // This allows the user to select a file, returning quietly if
        // the user cancels out of the file dialog.
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File file = chooser.getSelectedFile();
        
        // This will automatically close the Scanner when control leaves the
        // try block.  In addition, if a FileNotFoundException occurs in the try
        // block, control immediately transfers to the catch block.
        try (Scanner s = new Scanner(file))
        {
            // Prompt for a token and make sure it isn't null, which indicates
            // that the user canceled.
           
        	//String token = JOptionPane.showInputDialog("Enter token to search for");
            //if (token == null) 
            //{
            //    return;
            //}
            
            // Invoke Loops.findLongestPalindrome
            String line = Loops.findLongestPalindrome(s);
            
            // Report that the token entered by the user wasn't found
            if (line == "")
            {
                JOptionPane.showMessageDialog(null, "No longest Palindrome");
            }
            
            // Display the line that contained the token
            else
            {
                JOptionPane.showMessageDialog(null, "Longest palindrome found is:\n" + line);
            }
        }
        catch (FileNotFoundException e)
        {
            // Report that the file didn't exist or couldn't be opened
            JOptionPane.showMessageDialog(null, "No such file: " + file);
        }
    }

}
