package cs1410;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class CacheListTest 
{
	//select() indirectly tested in all the testMethods below

	/****************************************************
	 * It tests the IllgelArgumentException thrown when
	 * the input to the cache object is not in the 
	 * correct format.
	 ****************************************************/
	@Test(expected = IllegalArgumentException.class) 
	public void testCacheList() throws IOException
	{
		//Inappropriate scanner string for cache constructor: No Owner
		String cache1 = "GCRQWK\tA\\t3.5\t3\tN40 45.850\tW111 48.045\n";
		CacheList caches = new CacheList(new Scanner(cache1));
	}
	
	/**************************************************
	 * Tests if IOException is thrown which propagates 
	 * from the scanner
	 **************************************************/
	@Test 
	public void testCacheList1()
	{
		try 
		{
			CacheList caches = new CacheList(new Scanner(""));
		} 
		catch (IOException e) 
		{
			assert(true);
		}
	}
	
	/********************************************************
	 * Tests setTitleConstraint() alongwith select()
	 * and methods invoked on Cache object items in CacheList 
	 ********************************************************/
	@Test
	public void testSetTitleConstraint() throws IOException
	{
		String s1 = "GC2X09\tA\tgeocadet1\t3.5\t3\tN40 45.850\tW111 48.045\n";
		String s2 = "GC2X00\tB\tgeocadet2\t3.5\t3\tN40 45.850\tW111 48.045\n";
		String s3 = "GC1200\tA\tgeocadet3\t3.5\t3\tN40 45.850\tW111 48.045\n";
		
		CacheList caches = new CacheList(new Scanner(s1 + s2 + s3));
		caches.setTitleConstraint("A");
		ArrayList<Cache> result = caches.select();
		
		assertEquals(2, result.size());
		assertEquals("geocadet1", result.get(0).getOwner());
		assertEquals("geocadet3", result.get(1).getOwner());
		assertEquals("GC2X09", result.get(0).getGcCode());
		assertEquals("GC1200", result.get(1).getGcCode());
	}

	/********************************************************
	 * Tests setOwnerConstraint() alongwith select()
	 * and methods invoked on Cache object items in CacheList 
	 ********************************************************/
	@Test
	public void testSetOwnerConstraint() throws IOException 
	{
		String s1 = "GCRQWK\tA\tgeocadet1\t3.5\t3\tN40 45.850\tW111 48.045\n";
		String s2 = "GCRQWK\tB\tgeocadet2\t3.5\t3\tN40 45.850\tW111 48.045\n";
		String s3 = "GCRQWK\tC\tgeocadet1\t3.5\t3\tN40 45.850\tW111 48.045\n";
		
		CacheList caches = new CacheList(new Scanner(s1 + s2 + s3));
		caches.setOwnerConstraint("geocadet1");
		ArrayList<Cache> result = caches.select();
		
		assertEquals(2, result.size());
		assertEquals("A", result.get(0).getTitle());
		assertEquals("C", result.get(1). getTitle());
	}

	/********************************************************
	 * Tests setDifficultyConstraint() alongwith select()
	 * and methods invoked on Cache object items in CacheList 
	 ********************************************************/
	@Test
	public void testSetDifficultyConstraints() throws IOException 
	{
		String s1 = "GC1111\tA\tPikachu\t2.0\t4\tN40 45.850\tW111 48.045\n";
		String s2 = "GC1999\tB\tSquirtel\t1.0\t4\tN40 45.850\tW111 48.045\n";
		String s3 = "GC1Z00\tC\tCharmandar\t4.5\t3.5\tN40 45.850\tW111 48.045\n";
		String s4 = "GC1XV0\tD\tOnix\t5.0\t4\tN40 45.850\tW111 48.045\n";
		String s5 = "GC13N0\tE\tZubat\t3.5\t3.0\tN40 45.850\tW111 48.045\n";
		
		CacheList c = new CacheList(new Scanner(s1 + s2 + s3 + s4 + s5));
		c.setDifficultyConstraints(2, 4.5);
		ArrayList<Cache> result = c.select();
		
		assertEquals(3, result.size());
		
		assertEquals("GC1111", result.get(0).getGcCode());
		assertEquals("Pikachu", result.get(0).getOwner());
		assertEquals("GC13N0", result.get(2).getGcCode());
		
		assertEquals(4.5, result.get(1).getDifficulty(), 1e-6);
		assertEquals(4, result.get(0).getTerrain(), 1e-6);
		assertEquals(3, result.get(2).getTerrain(), 1e-6);
	}

	/********************************************************
	 * Tests setTerrainConstraint() alongwith select()
	 * and methods invoked on Cache object items in CacheList 
	 ********************************************************/
	@Test
	public void testSetTerrainConstraints() throws IOException 
	{
		String s1 = "GC1111\tA\tPikachu\t2.0\t1.5\tN40 45.850\tW111 48.045\n";
		String s2 = "GC1999\tB\tSquirtel\t1.0\t4\tN40 45.850\tW111 48.045\n";
		String s3 = "GC1Z00\tC\tCharmandar\t4.5\t3.5\tN40 45.850\tW111 48.045\n";
		String s4 = "GC1XV0\tD\tOnix\t5.0\t3.5\tN40 45.850\tW111 48.049\n";
		String s5 = "GC13N0\tE\tZubat\t3.5\t3.0\tN40 45.850\tW111 48.045\n";
		
		CacheList c = new CacheList(new Scanner(s1 + s2 + s3 + s4 + s5));
		c.setTerrainConstraints(1.5, 4.0);
		ArrayList<Cache> result = c.select();
		
		assertEquals(5, result.size());
		
		assertEquals("GC1999", result.get(1).getGcCode());
		assertEquals("Squirtel", result.get(1).getOwner());
		assertEquals("N40 45.850", result.get(4).getLatitude());
		assertEquals("W111 48.049", result.get(3).getLongitude());
		
		assertEquals(1.0, result.get(1).getDifficulty(), 1e-6);
		assertEquals(3.0, result.get(4).getTerrain(), 1e-6);
		assertEquals(1.5, result.get(0).getTerrain(), 1e-6);
	}
}
