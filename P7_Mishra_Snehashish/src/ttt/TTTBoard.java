package ttt;

/**********************************************************************************
 * Represents the state of a tic-tac-toe board. This specification assumes that
 * you know how to play tic-tac-toe. Consult Wikipedia if you don't.
 * 
 * A tic-tac-toe board consists of nine squares, numbered like this:
 * 
 * <pre>
 *  1 | 2 | 3 
 * ---+---+--- 
 *  4 | 5 | 6
 * ---+---+--- 
 *  7 | 8 | 9
 * </pre>
 * 
 * Each square can be unoccupied, contain an X, or contain an O.
 * 
 * Beginning from an empty board, the players take turns moving until X wins, O
 * wins, or there is a draw. X always moves first.
 * 
 * In addition, a tic-tac-toe board knows how many times X has won, how many
 * times O has won, and how many times there has been a draw.
 * 
 * @author Snehashish Mishra
 ************************************************************************************/
public class TTTBoard
{
	/****************************************************
	 * Physical structure of String[] board 
	 * <pre> 
	 * 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8
	 * --+---+---+---+---+---+---+---+--- 
	 * </pre>
	 * 
	 * Logical Structure of String[] board
     * <pre>
     *  0 | 1 | 2 
     * ---+---+--- 
     *  3 | 4 | 5
     * ---+---+--- 
     *  6 | 7 | 8
     * </pre>
	 ***************************************************/
	private String[] board;		//Contains player choices: either "X" or "O"
	private int totalXWins;		//Total number of wins by "X" player
	private int totalOWins;		//Total number of wins by "O" player
	private int totalDraws;		//Total number of draws
	private int turn;			//Tracks current turn: 1(X's turn), 0(O's turn)
	private int currentState;	//Current state of the game: 0: X wins, 1: O wins, 2: draw

    /*****************************************************************
     * Constructs an empty board in which,
     * <li> X has won zero times, 
     * <li> O has won zero times, 
     * <li> there have been no draws,
     * <li> the current state of the game is neither win or draw, and
     * <li> X plays first
     ******************************************************************/
    public TTTBoard ()
    {
    	board = new String[9]; 	// 9 squares
    	turn = 1;				// X plays first
    	currentState = -1;		// Neither win or draw game state
    	totalXWins = 0;	
    	totalOWins = 0;
    	totalDraws = 0;
    }

    /******************************************************************************
     * If the current position is a win for X, a win for O, or a draw, throws an
     * IllegalArgumentException.
     * 
     * If the specified square is already occupied, throws an
     * IllegalArgumentException.
     * 
     * If square is invalid (less than 1 or greater than 9), throws an
     * IllegalArgumentException.
     * 
     * Otherwise, if it is X's turn to move, records an X move to the specified
     * square and returns "X". If it is O's turn to move, records an O move to
     * the specified square and returns "O". If the move makes the position a
     * win for X, the "wins for X" count is incremented. If the move makes the
     * position a win for O, the "wins for O" count is incremented. If the move
     * makes the position a draw, the "draws" count is incremented.
     *********************************************************************************/
    public String move (int square)
    {
    	if(square < 1 || square > 9)	// Invalid square value exception
    		throw new IllegalArgumentException();
    	
    	if(isWon() || isDrawn())		// First exception of win or draw state
    		throw new IllegalArgumentException();
    	
    	if(board[square-1] != "")		// Square already occupied exception
    		throw new IllegalArgumentException();
    	
    	if(turn == 1)					// If X's turn
    	{
    		board[square-1] = "X";		// Record X's in corresponding square
    		if( checkWin() == 0 )		// Has X won?
    		{
    			totalXWins++;			// If so, add to its total wins
    			currentState = 0;		// Set win state to X
    			return "X";
    		}
    		if( checkWin() == 2)		// If game's a draw...
    		{
    			totalDraws++;			// Add to total draws till now
    			currentState = 2;		// Set draw state
    			return "X";				// Since, it was X's turn
    		}
    		turn = 0;			//If game does not end, O's turn, return "X"
			return "X";
    	}
    	else							// If O's turn
    	{
    		board[square-1] = "O";		// Record O's in corresponding square
    		if( checkWin() == 1 )		// Has O won?
    		{
    			totalOWins++;			// If so, add to its total wins
    			currentState = 1;		// Set win state to O
    			return "O";
    		}
    		if( checkWin() == 2)		// If game's a draw...
    		{
    			totalDraws++;			// Add to total draws till now
    			currentState = 2;		// Set draw state
    			return "O";				// Since, it was O's turn
    		}
    		turn = 1;			//If game does not end, X's turn, return "O"
			return "O";
    	}
    }

	/**************************************************************
     * Returns "X" (if it is X's turn to move) or "O" (otherwise).  
     * This method does not change whose turn it is or 
     * anything else about the board.
     ***************************************************************/
    public String getToMove ()
    {
    	if(turn == 1)		// If X's turn
    		return "X";
    	else				// If O's turn
    		return "O";
    }

    /****************************************************
     * Reports whether or not the board has a drawn 
     * position (all squares filled in, neither X 
     * nor O has three in a row). This method does not
     * change anything about the board.
     ****************************************************/
    public boolean isDrawn ()
    {
        if(currentState == 2)		// If game is a draw
        	return true;
        else
        	return false;
    }

    /****************************************************
     * Reports whether or not the board has a won 
     * position (either X or O has three in a row).  
     * This method does not change anything about
     * the board.
     ****************************************************/
    public boolean isWon ()
    {
        if(currentState == 0 || currentState == 1)	// If either X or O wins
        	return true;
        else
        	return false;
    }

    /*******************************************************
     * Resets the board so that a fresh game can be played. 
     * Does not modify the scoring records.
     *******************************************************/
    public void reset ()
    {
    	board = new String[9];		// Empty the board
    	for(int i = 0; i <= 8; i++)
    		board[i] = "";
    	
    	turn = 1;					// Reset to X's first turn
    	currentState = -1;			// Reset current state of the game (neither win nor draw)
    }

    /*******************************************************
     * Returns the number of games that X has won.  
     * This method does not change anything about the board.
     *******************************************************/
    public int getXWins ()
    {
        return totalXWins;
    }

    /********************************************************
     * Returns the number of games that O has won.  
     * This method does not change anything about the board.
     ********************************************************/
    public int getOWins ()
    {
        return totalOWins;
    }

    /********************************************************
     * Returns the number of games that have been drawn.  
     * This method does not change anything about the board.
     ********************************************************/
    public int getDrawCount ()
    {
        return totalDraws;
    }
    
    /****************************************************
     * Checks if a win condition has been met, i.e, 
     * three in a row for X or O. Also, checks the 
     * draw condition (whole board is filled).
     * 
     * Reference board
     * <pre>
     *  Original	   String Array Representation
     *  1 | 2 | 3  			 0 | 1 | 2 
     * ---+---+--- 			---+---+--- 
     *  4 | 5 | 6  			 3 | 4 | 5
     * ---+---+--- 			---+---+--- 
     *  7 | 8 | 9  			 6 | 7 | 8
     * </pre>
     * 
     * @return 
     * <li> -1 if neither win / draw 
     * <li> 0 if X wins
     * <li> 1 if O wins
     * <li> 2 if its a draw
     *****************************************************/
    private int checkWin() 
    {
    	int status = -1; 		// Reset win / draw condition for every call
    	int filledBoard = 0;	// Counter to check if all squares are filled
    	
    	//Check three in a row for 
    	status = checkThreeInARow(0, 8, 4);		// Diagonal 1: 1,5,9 
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(2, 6, 2);		// Diagonal 2: 3,5,7
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(0, 2, 1);		// Row 1: 1,2,3
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(3, 5, 1);		// Row 2: 3,4,5
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(6, 8, 1);		// Row 3: 7,8,9
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(0, 6, 3);		// Column 1: 1,4,7
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(1, 7, 3);		// Column 2: 2,5,8
    	if(status != -1)
    		return status;
    	
    	status = checkThreeInARow(2, 8, 3);		// Column 3: 3,6,9
    	if(status != -1)
    		return status;
    	
    	for(int i = 0; i <= 8; i++)				// Check if all squares are filled
    	{
    		if(board[i] != "")
    			filledBoard++;
    	}
    	
    	if(filledBoard == 9)					// If all squares are filled
    		return 2;							// Say Draw
    	
		return -1;								// Else neither win nor draw
	}

    /****************************************************
     * Checks for a three in a row of either X's or O's
     * via passed initial value, condition value, and 
     * increment value.
     * Returns 0 if three X's in a row, 1 if three O's
     * in a row, or -1 if none.
     *****************************************************/
	private int checkThreeInARow(int ini, int cond, int incre) 
	{
		int xThreeInARow = 0;		// Number of X's in a row
		int oThreeInARow = 0;		// Number of O's in a row
		
		for(int i = ini; i <= cond; i += incre)		// Loop through each square of passed row
		{
			if(board[i] == "X")						// If square is X
				xThreeInARow++;
			if(board[i] == "O")						// If square is O
				oThreeInARow++;
		}
		
		if(xThreeInARow == 3)		// If all 3 squares are X's
			return 0;
		if(oThreeInARow == 3)		// If all 3 squares are O's
			return 1;
		
		return -1;					// If neither three X's nor O's in a row
	}
}
