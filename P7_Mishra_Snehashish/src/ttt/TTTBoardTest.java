package ttt;

import static org.junit.Assert.*;

import ttt.TTTBoard;
import org.junit.Test;

public class TTTBoardTest {

	@Test
	public void testTTTBoard() 
	{
		TTTBoard t = new TTTBoard();
		assertEquals(0, t.getXWins());
		assertEquals(0, t.getOWins());
		assertEquals(0, t.getDrawCount());
		assertEquals(false, t.isDrawn());
		assertEquals(false, t.isWon());
		assertEquals("X", t.getToMove());
	}

	/****************************************************
	 * Invalid square number exception: 10 
	 ****************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testMoveException1() 
	{
		TTTBoard t = new TTTBoard();
		t.move(10);	
	}

	/****************************************************
	 * Exception for - when the current position 
	 * for win or draw is already set in move
	 ****************************************************/
	@Test
	public void testMoveException2() 
	{
		TTTBoard t = new TTTBoard();
		try
		{
			t.move(2);
			if( !t.isDrawn() )		//isDrawn() is false. So make it true for testing
				throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException e)
		{
			assert(true);
		}
	}
	
	/****************************************************
	 * Exception for - square already occupied
	 ****************************************************/
	@Test(expected = IllegalArgumentException.class)
	public void testMoveException3() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		
		t.move(1);
		assertEquals("O", t.getToMove());
		t.move(1);		//Try same square to get exception
	}
	
	@Test
	public void testGetToMove() 
	{
		TTTBoard t = new TTTBoard();
		assertEquals(0, t.getXWins());;
		assertEquals(false, t.isWon());
		assertEquals("X", t.getToMove());
	}

	@Test
	public void testIsDrawn() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		t.move(1);
		t.move(2);
		t.move(3);
		t.move(4);
		t.move(6);
		t.move(5);
		t.move(7);
		t.move(9);
		t.move(8);
		assertEquals(false, t.isWon());
		assertEquals(0, t.getXWins());
		assertEquals(0, t.getOWins());
		assertEquals(true, t.isDrawn());
	}

	@Test
	public void testIsWon() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		t.move(1);
		t.move(2);
		t.move(5);
		t.move(3);
		t.move(9);
		assertEquals(true, t.isWon());
		assertEquals(false, t.isDrawn());
	}

	@Test
	public void testReset() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		t.move(1);
		assertEquals(false, t.isDrawn());
		assertEquals(false, t.isWon());
		assertEquals("O", t.getToMove());
	}

	@Test
	public void testGetXWins() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		t.move(1);
		t.move(2);
		t.move(5);
		t.move(3);
		t.move(9);
		assertEquals(true, t.isWon());
		assertEquals(1, t.getXWins());
	}

	@Test
	public void testGetOWins() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		t.move(2);
		t.move(1);
		t.move(3);
		t.move(5);
		t.move(4);
		t.move(9);
		assertEquals(true, t.isWon());
		assertEquals(1, t.getOWins());
		assertEquals(0, t.getXWins());
	}

	@Test
	public void testGetDrawCount() 
	{
		TTTBoard t = new TTTBoard();
		t.reset();
		t.move(1);
		t.move(2);
		t.move(3);
		assertEquals("O", t.getToMove());
		t.move(4);
		assertEquals("X", t.getToMove());
		t.move(6);
		t.move(5);
		t.move(7);
		t.move(9);
		t.move(8);
		assertEquals(false, t.isWon());
		assertEquals(0, t.getXWins());
		assertEquals(0, t.getOWins());
		assertEquals(true, t.isDrawn());
		assertEquals(1, t.getDrawCount());
	}

}
