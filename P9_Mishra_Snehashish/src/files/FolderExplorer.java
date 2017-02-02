package files;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.*;

/**************************************************************
 * Collection of methods that examine the user selected
 * file directory through a GUI
 * @author Snehashish Mishra
 **************************************************************/
@SuppressWarnings("serial")
public class FolderExplorer extends JFrame
		implements ActionListener
{

	private JFileChooser chooser;		// To display a pop-up file chooser
	private File selectedDirectory;		// To store the path of user selected directory
	private JTextArea directory;		// To display selected directory label
	private JTextArea files;			// To display total files label
	private JTextArea largestFileWO;	// To display largest without dir label
	private JTextArea largestFile;		// To display largest with dir label
	private JTextArea totalRange;		// To display files in range label
	private JTextArea modifiedFile;		// To display last modified label
	
	private static final Font LABEL_FONT = new Font("Helvetica", Font.BOLD, 14);
	private static final Font MONO_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 14);
	
	/****************************************************************
	 * Calls or starts the Folder Attributes Viewer 
	 ****************************************************************/
	public static void main(String[] args) 
	{
		FolderExplorer x = new FolderExplorer();
		x.setLocationRelativeTo(null);
	}
	
	/****************************************************************
	 * Creates a FolderExplorer GUI
	 ****************************************************************/
	public FolderExplorer()
	{
		// Create Main Window and set some attributes
		JFrame frame = new JFrame();
		frame.setTitle("File Attributes Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700,500));
		
		// Create main Content Panel and set some attributes
		JPanel mainPanel = new JPanel();
		frame.setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());

		// Create a panel to add all the Folder attributes
		JPanel attributesPanel = new JPanel();
		attributesPanel.setLayout(new GridLayout(12, 1, 2, 2));
		
		// Add selected directory label and the read-only textArea to show the result
		attributesPanel.add(new JLabel("  Selected Directory: ")).setFont(LABEL_FONT);
		directory = new JTextArea(50,1);												// Directory Result
		directory.setEditable(false);				// Read - only
		directory.setFont(MONO_FONT);				// Mono font
		attributesPanel.add(directory);				// add to attributes panel

		// Add "Total # files" label and the read-only textArea to show the result
		attributesPanel.add(new JLabel("  Total # of Files: ")).setFont(LABEL_FONT);
		files = new JTextArea(100,1);
		files.setEditable(false);
		files.setFont(MONO_FONT);
		attributesPanel.add(files);
		
		// Add "Largest File without dir" label and the read-only textArea to show the result
		attributesPanel.add(new JLabel("  Largest File excluding Directories: ")).setFont(LABEL_FONT);
		largestFileWO = new JTextArea(100,2);
		largestFileWO.setEditable(false);
		largestFileWO.setFont(MONO_FONT);
		attributesPanel.add(largestFileWO);

		// Add "Largest including dir" label and the read-only textArea to show the result
		attributesPanel.add(new JLabel("  Largest File including Directories: ")).setFont(LABEL_FONT);
		largestFile = new JTextArea(100,2);
		largestFile.setEditable(false);
		largestFile.setFont(MONO_FONT);
		attributesPanel.add(largestFile);

		// Add "# in Range" label and the read-only textArea to show the result
		attributesPanel.add(new JLabel("  Total # of files ranging between 10 KB - 25 KB: ")).setFont(LABEL_FONT);
		totalRange = new JTextArea(100,1);
		totalRange.setEditable(false);
		totalRange.setFont(MONO_FONT);
		attributesPanel.add(totalRange);

		// Add "Last Modified" label and the read-only textArea to show the result
		attributesPanel.add(new JLabel("  Latest Modified File or Directory: ")).setFont(LABEL_FONT);
		modifiedFile = new JTextArea(100,2);
		modifiedFile.setEditable(false);
		modifiedFile.setFont(MONO_FONT);
		attributesPanel.add(modifiedFile);
		
		// Add attributesPanel to the main content Panel
		mainPanel.add(attributesPanel, "Center");
		
		// Create a bottom panel for button
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 1));
		
		// Create a file select Jbutton
		JButton fileChooser = new JButton("Open a file or Directory");	// Button's Face name
		fileChooser.setName("fileChoose");								// Button's internal name
        bottom.add(fileChooser);
        bottom.setVisible(true);
        fileChooser.addActionListener(this);		// Add an actionListerner to it
        
        mainPanel.add(bottom, "South");				// Add the button panel to the bottom of main pane
        
        frame.pack();
        frame.setVisible(true);
	}
	
	/****************************************************************
	 * This method is called when the user clicks the folder
	 * select button
	 ****************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object o = e.getSource();				// The object on which the event occurred
        JButton b = (JButton)o;					// Cast the event object as a JButton
        
        chooser = new JFileChooser();											// Create a new JFileChooser
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	// Set its default pop-up location to project user directory
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);			// Allow only directories to be selected
        
        chooser.setDialogTitle("Select a Folder");		// Title Window's Text 
        int result = chooser.showOpenDialog(null);		// Open the File Select window and store the user action
        
        if (result == JFileChooser.CANCEL_OPTION)		// If the user cancels, do nothing
        {						
        }
        
        // If an error occurs, quit the app
        if (result != JFileChooser.APPROVE_OPTION && result != JFileChooser.CANCEL_OPTION)
        {
        	System.exit(-1);
        }
        
        selectedDirectory = chooser.getSelectedFile();	// Store the user selected directory
        
        // Set the calculated attributes to their resp. panels
        try
        {
        	directory.setText(" " + selectedDirectory.getAbsolutePath()); 		// Set directory text
        }
        catch(NullPointerException n) { }
        
        files.setText(" " + totalFiles(selectedDirectory) + "");				// Set total files text
        
        // Calculate "Largest File without dir" details and and set it 
        String[] l = largestFileDetailsWithoutDirectories(selectedDirectory);
        largestFileWO.setText(" Name: " + l[1] +"\n Size: " + l[0] + " bytes");
        
        //Calculate "Largest File with dir" details and and set it
        largestFile.setText(" Name: " + largestFileName(selectedDirectory) 
        		+"\n Size: " + largestFileSize(selectedDirectory) + " bytes");
        
        //Calculate "# files within range" and and set it
        totalRange.setText(" " + totalFilesInRange(selectedDirectory) + "");
        
        //Calculate "Last Modified" details and and set it
        String[] m = latestModified(selectedDirectory);
        if(Long.parseLong(m[0]) == 0)												// If empty dir, report empty
        	modifiedFile.setText(" Name: " + m[1] + "\n Date: Empty Directory");
        else																		// Else, set details
        {
        	Date d = new Date(Long.parseLong(m[0]));
        	modifiedFile.setText(" Name: " + m[1] + "\n Date: " + d);
        }
	}
	
	/**************************************************************************
	 * Finds the largest file in the given directory and returns
	 * an array of Strings containing its size as the first element,
	 * and its name as its second element:
	 * 
	 * <pre>
	 * a[0] = File_Size
	 * a[1] = File_Name
	 * </pre>
	 * 
	 * If the passed directory is empty, notifies the user and
	 * sets the size to 0.
	 * 
	 * <li>NOTE: In Discussions, Joe said that he later realized that length()
	 * doesn't work on directories, so just use it as it is. However, by 
	 * the time I read that, I had already implemented one which calculates
	 * directory sizes to compute the largestFile. I included this method too,
	 * to avoid problems ;)
	 ***************************************************************************/
	public String[] largestFileDetailsWithoutDirectories(File f) 
	{
		String[] largestFileDetails = {"0", "Empty Directory"};			// Set largest file size and name to 0
		
		if(f == null)										// If directory is null, exit
			return largestFileDetails;
		
		for(File currentFile : f.listFiles())				// Go through each File in the passed file-path.
		{
			if(currentFile.isFile())						// If its a file,
			{
				if(currentFile.length() >= Long.parseLong(largestFileDetails[0]))	// Check if its size is >= the largest one yet
				{
					largestFileDetails[0] = currentFile.length() + "";				// If true, update largest size yet
					largestFileDetails[1] = currentFile.getName();					// Update its file's path
				}
			}
			
			else if(currentFile.isDirectory())										// If its a directory,
			{
				if(currentFile.length() >= Long.parseLong(largestFileDetails[0]))	// Compare the size of directory with the largest file so far
				{
					largestFileDetails[0] = currentFile.length() + "";				// If true, update largest size yet
					largestFileDetails[1] = currentFile.getName();					// Update its file's path
				}
				
				String[] v = largestFileDetailsWithoutDirectories(currentFile);		// Find recursively the largest inside the current directory
				
				if(Long.parseLong(v[0]) >= Long.parseLong(largestFileDetails[0]))	// If the size inside directory is larger,
				{
					largestFileDetails[0] = Long.parseLong(v[0]) + "";				// Update the largestFileDetails
					largestFileDetails[1] = v[1];
				}
			}
		}
			
		return largestFileDetails;													// Else, return the array of LargestFileDetails
	}

	/*****************************************************************************
	 * Finds the last modified file or directory in the given directory
	 * and returns an array of String containing its date as the first element,
	 * and its name as its second element:
	 * 
	 * <pre>
	 * a[0] = Modification_Date
	 * a[1] = File_Name
	 * </pre>
	 * 
	 * If the passed directory is empty, notifies the user and
	 * sets the date to 0.
	 *****************************************************************************/
	public String[] latestModified(File f)
	{
		String[] latestModifiedDetails = {"0", "Empty Directory"};		// Set initial last modified File's details

		if(f == null)										// If directory is null, exit
			return latestModifiedDetails;
		
		for(File currentFile : f.listFiles())				// Go through each "File" in the passed file-path. If its null, will exit loop
		{
			if(currentFile.isFile())						// If its a file,
			{
				if(currentFile.lastModified() >= Long.parseLong(latestModifiedDetails[0]))	// Check if it was recently modified
				{
					latestModifiedDetails[0] = currentFile.lastModified() + "";				// If it was, update modification date
					latestModifiedDetails[1] = currentFile.getName();						// Update the file's name
				}
			}
			
			else if(currentFile.isDirectory())												// If its a directory,
			{
				if(currentFile.lastModified() >= Long.parseLong(latestModifiedDetails[0]))	// Check if it was recently modified,
				{
					latestModifiedDetails[0] = currentFile.lastModified() + "";				// If it was, update modification date
					latestModifiedDetails[1] = currentFile.getName();						// Update the file's name
				}
				
				// Find recursively the latest modified "File" inside the current directory
				String[] v = latestModified(currentFile);
				
				if(Long.parseLong(v[0]) >= Long.parseLong(latestModifiedDetails[0]))		// If there's a latest modification inside the directory
				{
					latestModifiedDetails[0] = Long.parseLong(v[0]) + "";					// Update the latest modification date
					latestModifiedDetails[1] = v[1];										// Update the file's name
				}
			}
		}

		if(latestModifiedDetails[0].equals("0") && latestModifiedDetails[1].equals("0"))	// If the passed directory is empty
			latestModifiedDetails[1] = "Empty Directory!";									// Set notification message

		return latestModifiedDetails;						// Else, return the array containing details of latestModifiedFile
	}
	
	/**********************************************************************
	 * Calculates recursively and returns the total number of files 
	 * and directories that are contained within the passed directory
	 * -- including sub-directories and the original passed directory, 
	 * (since directories are self-contained and because Joe said so ;) ).
	 **********************************************************************/
	public long totalFiles (File f)						
	{
		long totalFiles = 1;							// Since, a folder contains itself
		
		if(f == null)										// If directory is null, return 0
			return 0;
		
	    for(File currentFile : f.listFiles()) 			// Gets the next File/Directory from the passed file-path
	    {
	    	if(currentFile.isFile())					// If its a file, count it
	    		totalFiles++;
	    	
	    	else if(currentFile.isDirectory()) 			// If its a directory, 
	        {											
	            totalFiles += totalFiles(currentFile);	// Recursively count all the "Files" in it
	        }
	    }
	    return totalFiles;								// Return the total no. of files and directories
	}
	
	/******************************************************************
	 * Calculates the size of a directory (including the size of 
	 * all the sub-directories). If the directory is empty, returns 0.
	 ******************************************************************/
	public long directorySize(File f)
	{		
		long size = 0;								// Sets the total size to 0
	    for(File currentFile : f.listFiles()) 		// Go through each File in the passed file-path. If its null, will exit loop
	    {
	    	if(currentFile.isFile())				// If its a file,		
	    	{
	    		size += currentFile.length();		// Add it to the total size of the passed directory's size
	    	}
	    	
	    	else if(currentFile.isDirectory()) 		// If its a sub-directory,
	        {	    		
	    		size += directorySize(currentFile);	// Find its total size recursively and add it 
	        }										// to the total size of the passed directory.
	    }
	    return size;								// Return total size. 0 if empty directory.
	}
	
	/****************************************************************
	 * Returns the size of the largest file / directory 
	 * in the passed file-path.
	 * If the passed directory is empty, returns 0
	 ****************************************************************/
	public long largestFileSize(File f)
	{
		if(f == null)										// If directory is null, return 0
			return 0;
		
		// Find the pathname of the largest file/directory in the passed File f and
		// create a new file pointing to that file
		File largestFile = new File(largestFilePath(f));
		
		if(largestFile.isFile())				// If the largestFile is a file, 
			return largestFile.length();		// return its size
		
		else if(largestFile.isDirectory())		// If the largestFile is a directory,
			return directorySize(largestFile);	//  return its size by calling directorySize()
		
		return 0;
	}
	
	/****************************************************************
	 * Returns the name of the largest File in the
	 * passed file-path. If the directory is empty,
	 * notifies with a message.
	 ****************************************************************/
	public String largestFileName(File f)
	{
		if(f == null)										// If directory is null, exit
			return "Empty Directory";
		
		File largestFile = new File(largestFilePath(f));	// Get the largest file's path name
		
		// Return its file name. If it's empty, message displayed through largestFilePath() method.
		return largestFile.getName();
	}
	
	/****************************************************************
	 * It finds and returns the absolute file-path of the 
	 * largest file or directory in passed directory.
	 ****************************************************************/
	public String largestFilePath(File f)
	{
		long largestFileSize = 0;							// Set largest file size to zero
		String largestFilePath = "";						// Set largest file path name to empty
		
		if(f == null)										// If directory is null, exit
			return null;
		
		for(File currentFile : f.listFiles())				// Go through each File in the passed file-path. If its null, will exit loop
		{
			if(currentFile.isFile())						// If its a file,
			{
				if(currentFile.length() >= largestFileSize)	// Check if its size is >= the largest one yet
				{
					largestFileSize = currentFile.length();				// If true, update largest size yet
					largestFilePath = currentFile.getAbsolutePath();	// Update the largest file's path
				}
			}
			else if(currentFile.isDirectory())						// If its a directory,
			{
				long directorySize = directorySize(currentFile);	// Store its size in a variable
				if(directorySize >= largestFileSize)				// Compare the size of the directory with the largest file so far
				{
					largestFileSize = directorySize;				// If true, update largest size yet
					largestFilePath = currentFile.getAbsolutePath();// Update the largest size directory's path
				}
			}
		}
		
		if(largestFilePath == "")								// If empty directory, then notify
			return "Empty Directory";
		
		return largestFilePath;		// Else, return the largest "File's" (including directory) path.
	}
	
	
	
	/****************************************************************
	 * Counts and returns the total no. of files in the passed 
	 * directory whose size ranges from 10000 bytes - 25000 bytes.
	 * 
	 * Does not count the size of sub-directories, only
	 * files (directly or indirectly).
	 *****************************************************************/
	public long totalFilesInRange(File f)
	{
		if(f == null)										// If directory is null, return 0
			return 0;
		
		// If - else if : Base case
		if (f.isFile() && f.length() >= 10000 && f.length() <= 25000)		// If the file is in range, count
        {
            return 1;
        }
		else if( f.isFile() && f.length() <= 10000 || f.length() >= 25000)	// If the file is not in range, don't count
			return 0;
        
		else									// If its a directory,
        {
            int count = 0;
            for (File f1: f.listFiles())		// Go through every file in it and
            {
                count += totalFilesInRange(f1);	// Count the number of files within 10 KB - 25 KB range
            }
            return count;						// Return the total files meeting the criteria
        }
	}	
}
	
