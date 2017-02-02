package cs1410;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class GraphingMethodsTest 
{
	//Unequal columns exception
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph1() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 12.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		int operation = 1;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}
	
	//Empty Categories exception
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph2() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 12.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList(""));
		int operation = 1;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}
	
	//Non-positive column2 value exception: -7.0
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph3() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 12.0, -7.0));	//Neg. 7
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		int operation = 1;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}
	
	//Non-positive column2 value exception: 0.0
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph4() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 0.0, 7.0));	//Zero
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		int operation = 1;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}
	
	//Operation other than SUM, AVG, MAX, MIN exception
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph5() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 12.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		int operation = 5;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}

	//Operation other than SUM, AVG, MAX, MIN exception
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph6() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 12.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		int operation = -9;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}
	
	//Duplicate Categories exception
	@Test(expected = IllegalArgumentException.class)
	public void testPrepareGraph7() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","Boston","SLC","Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(5.0, 19.0, 20.0, 12.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","Boston","NYC","Portland"));
		int operation = 1;
		GraphingMethods.prepareGraph(column1, column2, categories, operation);
	}
	
	//Sum of all percentages' approximation to 1.0 test
	@Test
	public void testPrepareGraph8() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC","SLC","SLC","NYC"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(7.0, 19.0, 1.0, 3.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","SLC"));
		int operation = 0;
		
		//Computed percentage List
		ArrayList<Double> percent = GraphingMethods.prepareGraph(column1, column2, categories, operation);
		
		//Compute sum of all percentages in percentage List
		double sum = 0.0d;
    	for(int i=0; i<percent.size(); i++)
    	{
    		sum += percent.get(i);	
    	}
    	
    	//Check the total sum's tolerance/closeness to the approximation of 1.0
    	if(Math.abs(1.0d - sum) > 9.9999999999999995E-07D)
    		assert(true);
    	else
    		assert(false);
	}
	
	//Unexpected line pattern exception
	@Test(expected = IllegalArgumentException.class)
	public void testReadTable1() 
	{
		ArrayList<String> column1 = new ArrayList<>();
		ArrayList<Double> column2 = new ArrayList<>();
		
		//String "sneaky" instead of a double value
		GraphingMethods.readTable(new Scanner("NYC\tsneaky\n"),column1, column2);	
	}

	//Unexpected line pattern exception
	@Test(expected = IllegalArgumentException.class)
	public void testReadTable2() 
	{
		ArrayList<String> column1 = new ArrayList<>();
		ArrayList<Double> column2 = new ArrayList<>();
		
		// '\n' instead of '\t'
		GraphingMethods.readTable(new Scanner("NYC\n5.0\n"),column1, column2);	
	}
	
	//Item separation to column1 and column2 check: Positive column2 value
	@Test
	public void testReadTable3() 
	{
		ArrayList<String> column1 = new ArrayList<>();
		ArrayList<Double> column2 = new ArrayList<>();
		
		GraphingMethods.readTable(new Scanner("NYC\t5.0\n"),column1, column2);
		assertEquals("NYC", column1.get(0));
		assertEquals(5.0, column2.get(0), 1e-6);
	}

	//Item separation to column1 and column2 check: Neg. column2 value
	//NOTE: Neg. column2 value will be caught in prepareGraph method
	@Test
	public void testReadTable4() 
	{
		ArrayList<String> column1 = new ArrayList<>();
		ArrayList<Double> column2 = new ArrayList<>();
		
		GraphingMethods.readTable(new Scanner("NYC\t-7.0\n"),column1, column2);
		assertEquals("NYC", column1.get(0));
		assertEquals(-7.0, column2.get(0), -(1e-6));
	}
	
	//No values to perform operation on categories exception
	@Test(expected = IllegalArgumentException.class)
	public void testCreateTablesList1() 
	{
		//Items in column1 and categories do not match, so no operation on the values possible
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("Boston", "Portland"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(1.0, 19.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","SLC"));
		int operation = 0;
		GraphingMethods.createTablesList(column1, column2, categories, operation);
	}
	
	//SUM Table
	@Test
	public void testCreateTablesList2() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC", "SLC", "NYC", "NYC"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(1.0, 19.0, 5.0, 6.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","SLC"));
		int operation = 0;
		
		//Computed values
		ArrayList<Double> sum = GraphingMethods.createTablesList(column1, column2, categories, operation);
		//Intended values
		ArrayList<Double> answer = new ArrayList<>(Arrays.asList(12.0, 19.0));
		
		//Convert ArrayLists to array and compare (expected, actual)
		assertArrayEquals(answer.toArray(), sum.toArray());
	}
	
	//AVG Table
	@Test
	public void testCreateTablesList3() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC", "SLC", "SLC", "NYC"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(1.0, 10.0, 5.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","SLC"));
		int operation = 1;
		
		//Computed values
		ArrayList<Double> avg = GraphingMethods.createTablesList(column1, column2, categories, operation);
		//Intended values
		ArrayList<Double> answer = new ArrayList<>(Arrays.asList(4.0, 7.5));
		
		//Convert ArrayLists to array and compare (expected, actual)
		assertArrayEquals(answer.toArray(), avg.toArray());
	}
	
	//MAX Table
	@Test
	public void testCreateTablesList4() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC", "SLC", "SLC", "NYC"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(1.0, 10.0, 5.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","SLC"));
		int operation = 2;
		
		//Computed values
		ArrayList<Double> avg = GraphingMethods.createTablesList(column1, column2, categories, operation);
		//Intended values
		ArrayList<Double> answer = new ArrayList<>(Arrays.asList(7.0, 10.0));
		
		//Convert ArrayLists to array and compare (expected, actual)
		assertArrayEquals(answer.toArray(), avg.toArray());	
	}
	
	//MIN Table
	@Test
	public void testCreateTablesList5() 
	{
		ArrayList<String> column1 = new ArrayList<>(Arrays.asList("NYC", "SLC", "SLC", "NYC"));
		ArrayList<Double> column2 = new ArrayList<>(Arrays.asList(1.0, 10.0, 5.0, 7.0));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("NYC","SLC"));
		int operation = 3;
		
		//Computed values
		ArrayList<Double> avg = GraphingMethods.createTablesList(column1, column2, categories, operation);
		//Intended values
		ArrayList<Double> answer = new ArrayList<>(Arrays.asList(1.0, 5.0));
		
		//Convert ArrayLists to array and compare (expected, actual)
		assertArrayEquals(answer.toArray(), avg.toArray());	
	}
}
