//This declares that GasMileage.java is in the cs1410 package

package cs1410;

//This gives the full name of the JOptionPane class so that it can be found
import javax.swing.JOptionPane;

/**
 * This class provides a main method that calculates 
 * the gas mileage of a car taking in 3 inputs along with
 * the brand of the car and displays the result
 * 
 * @author Snehashish Mishra
 */


public class GasMileage 
{
	
	/**
	 * This main method asks for 4 user inputs, 
	 * calculates mileage, cost per gallon of gas, gas cost
	 * and displays the result in a single window
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) 
	{
		// Initial User Prompt Variables
		String carBrand;
		double pricePerGallon, amountOfFuelInTank;
		int milesSinceFillup;
		
		//Asks for Brand of car and stores it in carBrand string variable
		carBrand = JOptionPane.showInputDialog("Enter the brand of your car: ");
		
		//Asks user for no. of miles driven since fill up & stores in to temp string variable, converts to interger
		String inputPrompt2 = JOptionPane.showInputDialog("Enter the number of miles driven since the last time the tank was filled: ");
		milesSinceFillup = Integer.parseInt(inputPrompt2);
		
		//Asks user for price of gas and stores in temp string variable, converts to double
		String inputPrompt3 = JOptionPane.showInputDialog("Enter the current price in dollars for a gallon of gasoline: ");
		pricePerGallon = Double.parseDouble(inputPrompt3);
		
		//Asks user for gas in tank and stores in temp string variable, converts to double
		String inputPrompt4 = JOptionPane.showInputDialog("Enter the number of gallons used to fill the tank: ");
		amountOfFuelInTank = Double.parseDouble(inputPrompt4);
		
		//Final variable declarations for end results
		double totalCostOfGas, milesPerGallon, gasCostPerMile;
		
		//Calculates Total Cost of Gas
		totalCostOfGas = pricePerGallon * amountOfFuelInTank;
		
		//Calculates Miles per gallon
		milesPerGallon = milesSinceFillup / amountOfFuelInTank;
		
		//Calculates cost of gas per mile driven
		gasCostPerMile = totalCostOfGas / milesSinceFillup;
		
		//Final results displayed to the user in one window
		JOptionPane.showMessageDialog(
				null, carBrand + "\n" + "Gas Cost             : $" + String.format("%.2f",totalCostOfGas) + 
				"\n" + "Miles per gallon  : " + String.format("%.2f", milesPerGallon) + 
				"\n" + "Gas cost per mile: $" + String.format("%.2f", gasCostPerMile)
				);
		
	}
}
