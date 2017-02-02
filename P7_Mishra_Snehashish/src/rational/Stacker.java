package rational;

import rational.Rat;

/**************************************************
 * This class provides method to compute
 * the number of objects that can be stacked on
 * one another without falling.
 * @author Snehashish Mishra
 ***************************************************/
public class Stacker
{
	/***************************************************
	 * This method finds the sum of the sequences
	 * 1/2 + 1/4 + 1/6 + ... until a certain extension,
	 * and returns the total number of individual
	 * terms in the sequence (l) or the stack size.
	 ****************************************************/
	public static long stackSize(int extension)
	{
		Rat sum = new Rat(0, 1);		// Sum = 0
		Rat e = new Rat(extension, 1);	// Extension
		long l = 0;						// Stack Size
		
		for(int i = 2; sum.compareTo(e) <= 0; i += 2 )	// Start at 1/2 and increment by 1/2 everytime
		{
			sum = sum.add(new Rat (1, i));		// Add the next terms in the sequence
			l++;								// Count stack size
		}
		return l;								//Return Stack Size
	}
	
	/****************************************************
	 * This main method prints the stack size for each 
	 * extension from 1 to 6.
	 * @param args
	 ****************************************************/
    public static void main (String[] args)
    {
    	//Print the value for testing
    	System.out.println(stackSize(1));	//extension = 1
    	System.out.println(stackSize(2));	//extension = 2
    	System.out.println(stackSize(3));	//extension = 3
        System.out.println(stackSize(4));	//extension = 4
        System.out.println(stackSize(5));	//extension = 5
        
        //Takes too long
        System.out.println(stackSize(6));	//extension = 6
        
    }
}
