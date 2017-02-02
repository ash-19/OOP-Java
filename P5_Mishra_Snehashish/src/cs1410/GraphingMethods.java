package cs1410;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/******************************************************
 * This class takes in tabular data and computes
 * any one of the four operations (SUM, AVG, MAX, MIN)
 * on it to plot a pie chart or a bar graph
 * @author Snehashish Mishra
 ******************************************************/
public class GraphingMethods
{
    /**
     * Constant used to request a sum operation
     */
    public final static int SUM = 0;

    /**
     * Constant used to request an average operation
     */
    public final static int AVG = 1;

    /**
     * Constant used to request a max operation
     */
    public final static int MAX = 2;

    /**
     * Constant used to requset a min operation
     */
    public final static int MIN = 3;

    /****************************************************************************
     * If column1 and column2 have different lengths, throws an
     * IllegalArgumentException.
     * 
     * If column2 contains any non-positive numbers, throws an
     * IllegalAgumentException.
     * 
     * If categories is of length zero, throws an IllegalArgumentException.
     * 
     * If categories contains any duplicates, throws an
     * IllegalArgumentException.
     * 
     * If operation is anything other than SUM, AVG, MAX, or MIN, throws an
     * IllegalArgumentException.
     * 
     * Put side-by-side, the lists "column1" and "column2" represent a table of
     * values, where each row contains a string and a number. For example, here
     * is the table of values that might give the number of people named
     * Zebediah in different parts of four states.
     * 
     * <pre>
     *  Utah          5
     *  Nevada        6
     *  California   12
     *  Oregon        8
     *  Utah          9
     *  California   10
     *  Nevada        4
     *  Nevada        4
     *  Oregon       17
     *  California    6
     * </pre>
     * 
     * We can make a table of totals by pairing each string c from the list
     * "categories" with either the sum, average, maximum, or minimum of the
     * numbers paired with c in the table above. For example, if "categories" is
     * 
     * <pre>
     *  Utah
     *  Nevada
     *  California
     * </pre>
     * 
     * then, assuming we are summing, the table of totals would be
     *
     * <pre>
     *  Utah       14 
     *  Nevada     14 
     *  California 28
     * </pre>
     * 
     * We can convert this into a table of percentages by dividing each value by
     * the sum of the numbers in the second column:
     * 
     * <pre>
     *  Utah       0.25
     *  Nevada     0.25
     *  California 0.50
     * </pre>
     * 
     * The method should calculate and return, as an ArrayList<Double>, the
     * second column of the table of percentages. It should use the value of the
     * "operation" parameter to determine whether the values are combined by
     * summing, averaging, max-ing, or min-ing. (If, for any category, there are
     * no values to sum, average, min, or max, the method should throw an
     * IllegalArgumentException.
     * 
     * The order of the percentages in the returned ArrayList<Double> should
     * correspond to the order of the categories in the "categories" list.
     ****************************************************************************/
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Double> prepareGraph (ArrayList<String> column1,
            	  ArrayList<Double> column2, ArrayList<String> categories,
            	  int operation)
    {
    	ArrayList<Double> percentages = new ArrayList<Double>();
    	double totalListSum = 0.0d;
    	
    	//Exceptions
    	if(column1.size() != column2.size())		//Unequal columns
    		throw new IllegalArgumentException("Mismatching number of rows!");
    	
    	if(categories.size() == 0)					//Empty Categories
    		throw new IllegalArgumentException("Empty categories");
    	
    	if(operation != 0 && operation != 1 && operation != 2 && operation != 3 && operation != 4)
    		throw new IllegalArgumentException("Unidentified operation");
    	
    	for(int i = 0; i < column2.size(); i++)		//Non-positive column2 value
    	{
    		if(column2.get(i) <= 0.0d)
    			throw new IllegalArgumentException("Non-positive value detected");
    	}
    	
    	if(categories.size() != (new HashSet(categories)).size())	//Duplicate Categories
            throw new IllegalArgumentException();
    	
    	percentages = createTablesList(column1, column2, categories, operation);
    	for(int i = 0; i < percentages.size(); i++)	  //Sum of all the values in totals list
    		totalListSum += percentages.get(i);
    	
    	for(int i = 0; i < percentages.size(); i++)	  //Computes individual percentage value
    	{
    		double percentValue = percentages.get(i) / totalListSum;
    		percentages.set(i, percentValue);
    	}
    	return percentages;
    }

    /****************************************************************************
     * If categories, percentages, or colors is empty, throws an
     * IllegalArgumentException.
     * 
     * If categories, percentages, and colors don't have the same number of
     * elements, throws an IllegalArgumentException.
     * 
     * If the numbers in percentages don't add to (close to) 1.0, throws an
     * IllegalArgumentException.
     * 
     * Otherwise, views the three lists as a table with a String, a double, and
     * a Color in each column and displays the data with either a pie chart (if
     * usePieChart is true) or a bar graph (otherwise). The area of each slice
     * (of a pie chart) and the length of each bar (in a bar graph) should be
     * proportional to the number being graphed. In the example above, in a pie
     * chart Utah and Nevada should each have a quarter of the pie and
     * California should have half. In a bar graph, California's line should be
     * twice as long as Utah's and Nevada's.
     * 
     * In both the pie chart and the bar graph, the color used for categories[i]
     * should be colors[i].
     * 
     * The method should display the pie chart or bar graph by drawing on the g
     * parameter.
     ****************************************************************************/    
    public static void drawGraph (Graphics g, ArrayList<String> categories,
    		ArrayList<Double> percentages, ArrayList<Color> colors,
    		boolean usePieChart)
    {
    	//Exceptions
    	if(categories.size() == 0 || percentages.size() == 0 || colors.size() == 0) //Empty exception
    		throw new IllegalArgumentException("Empty ArrayLists");

    	if(categories.size() != percentages.size() || percentages.size() != colors.size())	//Unequal Exception
    		throw new IllegalArgumentException("Unequal ArrayLists");

    	//Add all the percentages
    	double sum = 0.0d;
    	for(int i = 0; i < percentages.size(); i++)
    	{
    		sum += percentages.get(i);	
    	}

    	if(Math.abs(1.0d - sum) > 9.9999999999999995E-07D)	//Over sum tolerance of 1.0 exception
    		throw new IllegalArgumentException();

    	if(usePieChart)		//Draw Pie Chart
    	{
    		int arcAngle;
    		int startAngle = 0;
    		int xLegend = 330;	//x-coord of legend
    		int yLegend = 30;	//y-coord of legend

    		//To draw items from each category
    		for(int i = 0; i < categories.size(); i++)
    		{
    			g.setColor(colors.get(i));

    			//Computes each category's angle
    			int angle = (int)Math.rint(percentages.get(i) * 360d);

    			if(i == 0 )			//First item in categories
    				arcAngle = 360;
    			else
    				arcAngle = angle;

    			g.fillArc(10, 60, 300, 300, startAngle, arcAngle);	//Draw respective arc
    			startAngle += angle;
    			g.fillRect(xLegend, yLegend, 10, 10);				//Draw Legend box

    			//Draw Legend String
    			g.setColor(Color.BLACK);
    			g.drawString(categories.get(i), xLegend+18, yLegend+10);

    			yLegend += 25;
    		}
    	}
    	else				//Draw Bar Graph
    	{
    		int yCoor = 30;

    		//To draw items from each category
    		for(int i = 0; i < categories.size(); i++)
    		{
    			g.setColor(colors.get(i));

    			//Computes each category's bar length
    			int barLength = (int)Math.rint(percentages.get(i) * 300d);

    			//Draw respective Bar
    			g.fillRect(90, yCoor, barLength, 30);

    			//Draw Legend String
    			g.setColor(Color.black);
    			g.drawString(categories.get(i), 10, yCoor+18);
    			yCoor += 40;
    		}
    	}
    }

    /***********************************************************************************
     * The dataSource should consist of zero or more lines.  Each line should consist of 
     * some text, followed by a tab character, followed by a double literal, followed by 
     * a newline.  
     * 
     * If any lines are encountered that don't meet this criteria, the method throws
     * an IllegalArgumentException whose message explains what is wrong.
     * 
     * Otherwise, for each line, the text before the tab is added to column1, and the
     * parsed double literal is added to column2.
     ************************************************************************************/
    public static void readTable (Scanner dataSource,
    		ArrayList<String> column1, ArrayList<Double> column2)
    {
    	try
    	{
    		String currentLine = "";

    		while(dataSource.hasNextLine())
    		{
    			String[] tokens;

    			currentLine = dataSource.nextLine();
    			tokens = currentLine.split("\t");	//split line at tabs

    			//Check the required pattern of tokens
    			if(tokens.length != 2)
    				throw new IllegalArgumentException("One of the lines does not contain 2 tokens");

    			column1.add(tokens[0]);										//Add first token to column1
    			column2.add(Double.valueOf(Double.parseDouble(tokens[1])));	//Add second token to column2

    		}
    	}
    	catch(NumberFormatException f)
    	{
    		throw new IllegalArgumentException("Invalid double value. Please check the file");
    	}
    }
    
    /***************************************************************************
     * Calculates and returns a double ArrayList of "totals" table based on the 
     * categories and their respective values obtained by applying the passed 
     * operation (SUM, AVG, MAX, MIN).
     * Throws IllegalArgumentException if no values are available to perform 
     * the operation.
     ***************************************************************************/
    public static ArrayList<Double> createTablesList(ArrayList<String> column1,
    		ArrayList<Double> column2, ArrayList<String> categories,
    		int operation)
    {  	
    	ArrayList<Double> totalsTable = new ArrayList<Double>();

    	double total;
    	int count = 0;
    	
    	//Go through one category at a time
    	for(int catIndex = 0; catIndex < categories.size(); catIndex++)
    	{
    		//Reset count and total for every category item
    		total = 0.0d;		
    		count = 0;

    		//For each category, compare with every item in column1
    		for(int i = 0; i < column1.size(); i++)
    		{
    			String categoryItem = categories.get(catIndex);
    			
    			//If item matches a category, perform specified operation
    			if(categoryItem.equals(column1.get(i)))		    
    			{
    				double categoryValue = column2.get(i);;
    				count++;

    				if(operation == 2)						    //Maximum 
    					total = Math.max(total, categoryValue);
    				else if(operation == 3)					    //Minimum 
    					if(total != 0.0d)
    						total = Math.min(total, categoryValue);
    					else
    						total = categoryValue;
    				else
    					total += categoryValue;	//Addition, since used for both sum and avg.
    			}
    		}

    		//When items in column1 and categories are different
    		if(count == 0)
    			throw new IllegalArgumentException("No values to perform operation for specified categories");

    		if(operation == 1)		//Average
    			total /= count;		

    		totalsTable.add(Double.valueOf(total));
    	}
    	return totalsTable;
    }
}
