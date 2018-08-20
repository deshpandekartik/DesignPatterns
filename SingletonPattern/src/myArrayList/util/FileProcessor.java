package myArrayList.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor
{
	private String filename;
	private File inReader;
	private BufferedReader Breader;


	/*
 	* 	Consutructor, opens the file
	*	@param filen - Name of the file
 	*/	
	public FileProcessor(String filen)
	{
		filename = filen;

		try
		{
			// open the file which needs to be read
	                inReader = new File(filename);
			Breader = new BufferedReader(new FileReader(filename));
		}
		catch ( IOException e)
		{
			System.err.println("Cannot open file");
			System.err.println(e.toString());
			System.exit(0);
		}
		
	}

	/*
	*	Reads a file , line by line
	*	@return - The line which was read from file.
	*/
	public String readLine()
	{
		try
		{
			String inline = Breader.readLine();
			return inline;
		}
		catch ( IOException e)
          	{
			System.err.println("Error while closing file");
             		System.err.println(e.toString());
                  	System.exit(0);
          	}
		return null;
	}

	/*
	*	Close the file , after it was finished reading
	*/
	public void close()
	{
		try
		{
			Breader.close();
		}
		catch (IOException e)
		{
			System.err.println("Cannot close file");
			System.err.println(e.toString());
		}
	}
		
};
