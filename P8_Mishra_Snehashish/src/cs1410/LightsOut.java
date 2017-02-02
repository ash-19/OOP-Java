package cs1410;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/*******************************************************************
 * This class provides the interface of the Lights Out board 
 * and includes the actual gameplay implementation.
 * The goal of the game is to turn off all the lights on the board.
 * 
 * The Lights Out board consists of 25 squares 
 * (in either true or false state) as follows:
 * 
 *  <pre>
 *    |   |   |   |  
 * ---+---+---+---+--- 
 *    |   |   |   |
 * ---+---+---+---+--- 
 *    |   |   |   |
 * ---+---+---+---+---
 *    |   |   |   |
 * ---+---+---+---+---
 *    |   |   |   |
 * </pre>
 * 
 * Once clicked, each square will switch between either OFF or ON color.
 * 
 * The board can either be set up in a random order or the player can
 * set it itself in manual mode.
 * 
 * @author Snehashish Mishra
 *********************************************************************/
@SuppressWarnings("serial")
public class LightsOut extends JFrame
    implements ActionListener
{
	
	private static final Color LIGHT_ON = new Color(255, 193, 47);	//255, 97, 85// Lights ON color 255, 193, 47, 116, 64, 173
    private static final Color LIGHT_OFF = new Color(200, 182, 161);		// Lights OFF color
    
    private JButton buttons[][];			// 2-D Buttons matrix 
    private boolean board[][];				// 2-D board-state matrix (either ON(true) or OFF(false) state)
    private JButton modeButton;				// Manual setup button
    private boolean manualMode;				// Manual mode ON or OFF tracker
    private int click; 						// 0: Lights ON sound, 1: Lights off sound
    private int winCounter;
    private JLabel score;					// Where the score is displayed
    private JLabel achievement;				// Where the current achievement is displayed
    
    /*******************************************
     * Launches a game of Lights Out!
     *******************************************/
    public static void main(String args[])
    {
        LightsOut lightsout = new LightsOut();
        lightsout.setLocationRelativeTo(null);
        lightsout.setVisible(true);
    }

    /********************************************************************
     * This class creates the GUI of the Lights Out game board.
     * 
     * It also creates a 5x5 game button grid and sets its 
     * ON, OFF state.
     * 
     * It randomizes the game board every time the app is run or 
     * "New Game" button is clicked.
     *********************************************************************/
    public LightsOut()
    {
    	// Universal look across different platforms
    	try
    	{
    	    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	}
    	catch(Exception e)
    	{
    	}
    	
        setDefaultCloseOperation(3);	//Exit the app when 'X' is clicked
        setTitle("Lights Out!   (Are we really that excited to use an exclamation mark?)");
        
        // Create a new 5x5 board of buttons along with its state
        board = new boolean[5][5];
        buttons = new JButton[5][5];
        
        // Make the main window, set its layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Make the game grid and set its layout to a 5x5 grid
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(5, 5));
        
        // Add buttons to each grid cell
        for(int i = 0; i < 5; i++)						// Loop through each row
        {
            for(int j = 0; j < 5; j++)					// Loop through all the columns of current row
            {
                buttons[i][j] = new JButton();			// Create a new button for each cell on the board
               
                buttons[i][j].setName((new StringBuilder()).append(i).append(j).toString());	// Set a name to it
                top.add(buttons[i][j]);															// Add this button in the grid
                buttons[i][j].addActionListener(this);		// Add an ActionListerner to this button
                buttons[i][j].setBackground(LIGHT_OFF);		// Set it to OFF color
            }
        }

        // Add the grid game board to the center of the mainPanel
        mainPanel.add(top, "Center");
        
        // Add two buttons in 1x2 Grid layout to a new bottom JPanel
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        
        // Create a new button "New Game"
        JButton rButton = new JButton("New Game");		// Button's Face name
        rButton.setName("newgame");						// Button's internal name
        bottom.add(rButton);							// Add this button to bottom panel
        rButton.addActionListener(this);				// Add an ActionListerner to this button
        
        modeButton = new JButton("Enter Manual Setup");	// Button's Face name
        modeButton.setName("mode");						// Button's internal name
        bottom.add(modeButton);							// Add this button to bottom panel
        modeButton.addActionListener(this);				// Add an ActionListerner to this button
        backgroundMusic();        
        
        bottom.setVisible(true);						// Make the bottom JPanel of 2 buttons visible 
        
        // Make the score display
        winCounter = 0;									// Reset total number of wins
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        north.add(score = new JLabel("Total Wins: " + winCounter + "\t\t\t\t\t\t\t\t\t\t"));
        north.add(achievement = new JLabel("Current Achievement: "));
        mainPanel.add(north, "North");
        
        mainPanel.add(bottom, "South");					// Add this bottom panel at the bottom of mainPanel
        setContentPane(mainPanel);
        setSize(600, 600);								// Set the mainPanel's size
        
        randomize();									// Randomize the board every time the app is run
    }

    /********************************************************************
     * This method listens to the player action (of pressing button)
     * and performs the required work once an ActionEvent is registered. 
     ********************************************************************/
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();				// The object on which the event occurred
        JButton b = (JButton)o;					// Cast the event object as a JButton
        
        if(b.getName().equals("mode"))			// If the button pressed is manual setup button
            toggleMode();						// Activate manual setup configuration
        
        else if(b.getName().equals("newgame"))	//If the button pressed is new game button
        {
            randomize();						// Call randomize to arrange the board in random order
        } 
        
        else
        {
            if(manualMode)				// If its manual setup mode, toggle only the selected button
                toggleButton(b);
            else						// Else toggle the surrounding buttons as well
                normalMove(b);
            
            if(gameWon())				// Check if the move turns OFF all the lights on the board
            {
            	// If so, increment win counter and update the score board
            	winCounter++;
            	score.setText("Total Wins: " + winCounter + "\t\t\t\t\t\t\t\t\t\t");
            	winFX();				// Win sound effect
            	winMessage();			// Alert about the win
            }
        }
    }

	/********************************************************************
	 * This method plays the background music repeatedly
	 ********************************************************************/
    private void backgroundMusic()
    {
    	try 
    	{
    		// Open an audio input stream.
    		AudioInputStream backMusic = AudioSystem.getAudioInputStream(new File("ambientLoop84.wav"));
    		
    		Clip clip = AudioSystem.getClip();		// Get a sound clip resource.		
    		clip.open(backMusic);					// Open audio clip and play it repeatedly
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    	} 
    	catch (UnsupportedAudioFileException e) { e.printStackTrace(); } 
    	catch (IOException e) 					{ e.printStackTrace(); } 
    	catch (LineUnavailableException e) 		{ e.printStackTrace(); }
	}
    
    /********************************************************************
     * This method creates a random game board.
     * That is, it turns the lights ON or OFF randomly. 
     ********************************************************************/
    private void randomize()
    {
        for(int i = 0; i < 5; i++)		// Go through each row
        {
            for(int j = 0; j < 5; j++)	// Go through each column of the current row
            {
                Random random = new Random();			// Create a new Random object
                if(board[i][j] = random.nextBoolean())	// Set random lights on
                    buttons[i][j].setBackground(LIGHT_ON);	
                else									// Set random lights off
                    buttons[i][j].setBackground(LIGHT_OFF);
            }
        }
    }

	/********************************************************************
     * Toggles the manual setup button
     * <li> If the player is entering manual setup
     * 		of the game board, changes the button text to
     * 		"Exit Manual Mode" once the button is clicked.
     * 
     * <li> If the player is done with manual setup,
     * 		changes the button back to "Enter Manual Setup"
     * 		once the button is clicked.
     ********************************************************************/
    private void toggleMode()
    {
        if(manualMode)
        {
            modeButton.setText("Enter Manual Setup"); 	// Set button text to activate manual mode
            manualMode = false;
        } 
        else
        {
            modeButton.setText("Exit Manual Setup");	// Exit manual setup when done
            manualMode = true;
        }
    }
    
    /********************************************************************
     * This method toggles the selected button ON or OFF,
     * that is, ON -> OFF;  OFF -> ON
     * 
     * It takes in the currently clicked button object.
     ********************************************************************/
    private void toggleButton(JButton b)
    {
    	// Get the current button and identify its index in the matrix
        int bName = Integer.parseInt(b.getName());
        int x = bName / 10;
        int y = bName % 10;
        
        if(board[x][y])					// If the button is ON, turn if OFF
        {
            board[x][y] = false;		// Set the button's state
            b.setBackground(LIGHT_OFF);	// Change its color
            click = 1;					// Lights off sound effect
            clickSound();
        }  
        else							// If the button is OFF, turn it ON
        {
            board[x][y] = true;			// Set the button's state
            b.setBackground(LIGHT_ON);	// Change its color
            click = 0;					// Lights on sound effect
            clickSound();
        }
    }
    
    /********************************************************************
     * This method plays the lights on and off sound effects
     ********************************************************************/
    private void clickSound() 
    {
    	try 
    	{
    		AudioInputStream lightsSoundFX = null;
    		if(click == 0)							// Lights On
    		{
    			// Open an audio input stream.
    			lightsSoundFX = AudioSystem.getAudioInputStream(new File("button-16.wav"));
    		}
    		if(click == 1)							// Lights Off
    		{
    			// Open an audio input stream.
    			lightsSoundFX = AudioSystem.getAudioInputStream(new File("button-17.wav"));
    		}
    		// Get a sound clip resource.
    		Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
					
			clip.open(lightsSoundFX);
			clip.start();
    	} 
    	catch (UnsupportedAudioFileException e) { e.printStackTrace(); } 
    	catch (IOException e) 					{ e.printStackTrace(); } 
    	catch (LineUnavailableException e) 		{ e.printStackTrace(); }
	}

	/********************************************************************
     * This method toggles all the buttons (top, bottom, left and right)
     * surrounding the selected button, that is, ON -> OFF;  OFF -> ON
     * 
     * It takes in the currently clicked button object.
     ********************************************************************/
    private void normalMove(JButton b)
    {
        toggleButton(b);		// Toggle the selected button. (ON if OFF, OFF if ON)
        
        // Find the buttons surrounding the pressed button (top, bottom, left, right)
        int up = Integer.parseInt(b.getName()) - 10;
        int down = Integer.parseInt(b.getName()) + 10;
        int left = Integer.parseInt(b.getName()) - 1;
        int right = Integer.parseInt(b.getName()) + 1;
        
        // Toggle the button on top of the selected button
        if(up >= 0)
        {
            int x = up / 10;
            int y = up % 10;
            toggleButton(buttons[x][y]);
        }
        
        // Toggle the button below the selected button
        if(down <= 44)
        {
            int x = down / 10;
            int y = down % 10;
            toggleButton(buttons[x][y]);
        }
        
        // Toggle the button to the left of the selected button
        if(left % 10 >= 0 && left % 10 <= 4)
        {
            int x = left / 10;
            int y = left % 10;
            toggleButton(buttons[x][y]);
        }
        
        // Toggle the button to the right of the selected button
        if(right % 10 <= 4)
        {
            int x = right / 10;
            int y = right % 10;
            toggleButton(buttons[x][y]);
        }
    }

    /********************************************************************
     * Determines if the player has won the game, that is,
     * has all the lights on the game board turned OFF.
     * 
     * It returns true if all lights are OFF, else false.
     ********************************************************************/
    private boolean gameWon()
    {
    	// Set initial count of ON lights to zero
        int lightsOn = 0;
        
        // Loop through each square to determine its state (either ON or OFF)
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
                if(board[i][j])			// If the current square is ON, increment the lightsOn counter
                    lightsOn++;
        }
        
        // If no lights are ON, returns true (won). Else returns false (hasn't won yet).
        return lightsOn <= 0;
    }
    
    /********************************************************************
     * Sarcastic reply method for when the player wins, along with
     * achievement updates
     ********************************************************************/
    private void winMessage() 
    {
    	JFrame frame = new JFrame("Message Box");
    	
    	if(winCounter == 1)
    	{
    		JOptionPane.showMessageDialog(frame, "Congratulations, you won!!!");
    		achievement.setText("Current Achievement: \t Breaking the ground (1st Win)");
    	}
    	else if(winCounter == 2)
    	{
    		JOptionPane.showMessageDialog(frame, "Hmm... pretty smart. You won...");
    		achievement.setText("Current Achievement: \t I'm twice as smart");
    	}
    	else if(winCounter == 3)
    	{
    		JOptionPane.showMessageDialog(frame, "Don't get cocky! Let's see how well you do next time.");
    		achievement.setText("Current Achievement: \t Three in a row");
    	}
    	else if(winCounter == 4)
    	{
    		JOptionPane.showMessageDialog(frame, "Tsch. Looks like a nerd's in town...");
    		achievement.setText("Current Achievement: \t A nerd's in town");
    	}
    	else if(winCounter >= 5 && winCounter <= 9)
    	{
    		JOptionPane.showMessageDialog(frame, "You won! Yippy!! Rejoice!!! \n"
    				+ "You just rid the whole world of all its misfortunes");
    		achievement.setText("Current Achievement: \t A savior has arrived (5 in a row)");
    	}
    	else if(winCounter >= 10 && winCounter < 25)
    	{
    		JOptionPane.showMessageDialog(frame, "Still playing?? Oh, the HUMANITY... (sob)");
    		achievement.setText("Current Achievement: \t And the journey begins... (10 in a row)");
    	}
    	else if(winCounter >= 25 && winCounter < 50)
    	{
    		JOptionPane.showMessageDialog(frame, "Of all the things to do, why still play this pitiful game.");
    		achievement.setText("Current Achievement: \t Breaking Spirit (25 in a row)");
    	}
    	else if(winCounter >= 50 && winCounter < 100)
    	{
    		JOptionPane.showMessageDialog(frame, "Wait, that's enough. I give up, you win. Please STOP!");
    		achievement.setText("Current Achievement: \t Closing Insanity... (50 in a row)");
    	}
    	else if(winCounter >= 100)
    	{
    		JOptionPane.showMessageDialog(frame, "That's it. I withdraw. You can't lose.");
    		achievement.setText("Current Achievement: \t Forever Lights Out... (100 in a row)");
    	}
	}
    
    /********************************************************************
     * This method plays the winning pop-up dialog's 
     * sound effect
     ********************************************************************/
	private void winFX() 
	{
		try 
    	{
    		// Open an audio input stream.
    		AudioInputStream winFX = AudioSystem.getAudioInputStream(new File("button-37.wav"));
    		
    		Clip clip = AudioSystem.getClip();	// Get a sound clip resource.			
    		clip.open(winFX);	    			// Open audio clip and play it repeatedly
    		clip.start();
    	} 
    	catch (UnsupportedAudioFileException e) { e.printStackTrace(); } 
    	catch (IOException e) 					{ e.printStackTrace(); } 
    	catch (LineUnavailableException e) 		{ e.printStackTrace(); }
	}
}