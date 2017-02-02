package files;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/****************************************************************
 * Testing for file attributes in FolderExplorer class
 * @author Snehashish Mishra
 ****************************************************************/
public class FolderExplorerTest 
{

	/***********************************************************************
	 * Test method for Largest File Details without directories size method
	 ***********************************************************************/
	@Test
	public void testLargestFileDetailsWithoutDirectories() 
	{
		FolderExplorer x = new FolderExplorer();
		
		// Main testing directory
		String[] s = x.largestFileDetailsWithoutDirectories(new File("/Users/snehashish19/Desktop/Test/"));
		
		assertEquals("277885", s[0]);
		assertEquals("PHYS 2210.pdf", s[1]);
		
		// Empty sub-directory
		String[] t = x.largestFileDetailsWithoutDirectories(new File("/Users/snehashish19/Desktop/Test/untitled folder/"));
		assertEquals("0", t[0]);
		assertEquals("Empty Directory", t[1]);
	}

	/***************************************************************
	 * Test method for last modified file or directory
	 ***************************************************************/
	@Test
	public void testLatestModified() 
	{
		FolderExplorer x = new FolderExplorer();
		
		// Main testing directory
		String[] s = x.latestModified(new File("/Users/snehashish19/Desktop/Test/"));
		assertEquals("1428637120000", s[0]);												// Date
		assertEquals(".DS_Store", s[1]);													// Name
		
		// Empty sub-directory
		String[] t = x.latestModified(new File("/Users/snehashish19/Desktop/Test/untitled folder/"));
		assertEquals("0", t[0]);
		assertEquals("Empty Directory", t[1]);
	}

	/***************************************************************
	 * Test method for couting all files and directories 
	 * including the passed one
	 ***************************************************************/
	@Test
	public void testTotalFiles() 
	{
		FolderExplorer x = new FolderExplorer();
		
		long count1 = x.totalFiles(new File("/Users/snehashish19/Desktop/Test/"));
		long count2 = x.totalFiles(new File("/Users/snehashish19/Desktop/Test/untitled folder/"));	//Empty Directory
		
		assertEquals(Long.parseLong("15"), count1);
		assertEquals(Long.parseLong("1"), count2);		// Empty Directory : 1
	}

	/***************************************************************
	 * Test method for largest file size including
	 * directory file sizes
	 ***************************************************************/
	@Test
	public void testLargestFileSize() 
	{
		FolderExplorer x = new FolderExplorer();
		
		long size1 = x.largestFileSize(new File("/Users/snehashish19/Desktop/Test/"));
		long size2 = x.largestFileSize(new File("/Users/snehashish19/Desktop/Test/untitled folder"));
		
		assertEquals(Long.parseLong("652147"), size1);
		assertEquals(Long.parseLong("0"), size2);		// Empty Directory : 1
	}

	/***************************************************************
	 * Test method for largest file's name including
	 * directories
	 ***************************************************************/
	@Test
	public void testLargestFileName() 
	{
		FolderExplorer x = new FolderExplorer();
		
		String s1 = x.largestFileName(new File("/Users/snehashish19/Desktop/Test/"));
		String s2 = x.largestFileName(new File("/Users/snehashish19/Desktop/Test/Test1"));
		String s3 = x.largestFilePath(new File("/Users/snehashish19/Desktop/Test/untitled folder"));
		
		assertEquals("Test1", s1);
		assertEquals("PHYS 2210.pdf", s2);
		assertEquals("Empty Directory", s3);
	}

	/***************************************************************
	 * Test method for the absolute path of the
	 * largest file or directory
	 ***************************************************************/
	@Test
	public void testLargestFilePath() 
	{
		FolderExplorer x = new FolderExplorer();
		
		String s1 = x.largestFilePath(new File("/Users/snehashish19/Desktop/Test/"));
		String s2 = x.largestFilePath(new File("/Users/snehashish19/Desktop/Test/untitled folder"));
		
		assertEquals("/Users/snehashish19/Desktop/Test/Test1", s1);
		assertEquals("Empty Directory", s2);
	}

	/***************************************************************
	 * Test method for total number of files in
	 * 10 KB to 25 KB range
	 ***************************************************************/
	@Test
	public void testTotalFilesInRange() 
	{
		FolderExplorer x = new FolderExplorer();
		
		long total = x.totalFilesInRange(new File("/Users/snehashish19/Desktop/Test/"));
		assertEquals(Long.parseLong("2"), total);
	}

}
