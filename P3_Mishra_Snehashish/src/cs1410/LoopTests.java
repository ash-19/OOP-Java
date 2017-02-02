package cs1410;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class LoopTests 
{
	@Test
	public void testCountFactors ()
	{
		assertEquals(1, Loops.countFactors(1));
		assertEquals(4, Loops.countFactors(6));
		assertEquals(2, Loops.countFactors(11));
		assertEquals(8, Loops.countFactors(30));
	}

	@Test
	public void testCountWhitespace ()
	{
		assertEquals(0, Loops.countWhitespace(""));
		assertEquals(0, Loops.countWhitespace("xyz"));
		assertEquals(10,
				Loops.countWhitespace("one two  three   four    "));
		assertEquals(8,
				Loops.countWhitespace("one\ttwo\n\nthree   four\n\t"));
	}

	@Test
	public void testAverageTokenLength ()
	{
		assertEquals(0, Loops.averageTokenLength(new Scanner("")), 1e-6);
		assertEquals(3.66666666666667,
				Loops.averageTokenLength(new Scanner("one two three")),
				1e-6);
	}

	@Test
	public void testContainsToken ()
	{
		assertFalse(Loops.containsToken("", "xyz"));
		assertTrue(Loops.containsToken("xyz", "xyz"));
		assertTrue(Loops.containsToken("xyx xyy xyz xya", "xyz"));
		assertFalse(Loops.containsToken("xyx xyy xyZ xya", "xyz"));
	}

	@Test
	public void testFindLineWithToken ()
	{
		assertEquals(null,
				Loops.findLineWithToken(new Scanner(""), "hello"));
		assertEquals("hello world", Loops.findLineWithToken(new Scanner(
				"hello world"), "world"));
		assertEquals(null, Loops.findLineWithToken(new Scanner(
				"hello world"), "abc"));
		assertEquals("hello world", Loops.findLineWithToken(new Scanner(
				"hello world"), "world"));
		assertEquals("this is a test", Loops.findLineWithToken(new Scanner(
				"hello world\nthis is a test\nthis is another test"), "a"));
	}
	
    @Test
    public void testIsPalindrome ()
    {
        assertTrue(Loops.isPalindrome(""));
        assertTrue(Loops.isPalindrome("z"));
        assertFalse(Loops.isPalindrome("xy"));
        assertTrue(Loops.isPalindrome("abcddcba"));
        assertFalse(Loops.isPalindrome("abcddbba"));
        assertTrue(Loops.isPalindrome("abcdedcba"));
        assertFalse(Loops.isPalindrome("abcdedbba"));
    }
    
    @Test
    public void testFindFactor ()
    {
        assertEquals(17, Loops.findSmallestFactor(17));
        assertEquals(2, Loops.findSmallestFactor(2));
        assertEquals(13, Loops.findSmallestFactor(169));
    }

	@Test
	public void testFindLongestPalindrome ()
	{
		assertEquals("", Loops.findLongestPalindrome(new Scanner("")));
		assertEquals("did", Loops.findLongestPalindrome(new Scanner("I did something wrong")));
		assertEquals("", Loops.findLongestPalindrome(new Scanner("This is an apple")));
		assertEquals("peep", Loops.findLongestPalindrome(new Scanner("a bb xyz\nI heard a peep sis")));
	}
	
	@Test
	public void testFindLongestLine ()
	{
		assertEquals(null, Loops.findLongestLine(new Scanner("")));
		assertEquals("hello", Loops.findLongestLine(new Scanner("hello")));
		assertEquals("This is a test", Loops.findLongestLine(new Scanner("Hello\nThis is a test\nGoodbye")));
	}

	@Test
	public void testFindMostWhitespace ()
	{
		assertEquals(null, Loops.findMostWhitespace(new Scanner("")));
		assertEquals("a b c", Loops.findMostWhitespace(new Scanner("a b c")));
		assertEquals("a bb\t\t", Loops.findMostWhitespace(new Scanner("a bb\na bb\t\t\nxyz")));
	}
}
