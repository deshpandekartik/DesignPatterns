package fourWayStreetLights.util;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
	private ArrayList<String> results;
	private String filename;
	private File inWriter;
	private BufferedWriter Bwriter;

	/**
	 *	Constructor,
	 *	@param outfilename - Name of the file where output is to be written
	 */
	public Results( String outfilename)
	{
		Logger.writeMessage(this.getClass().getName() + " Constructor is called ", Logger.DebugLevel.CONSTRUCTOR);
		results = new ArrayList<String>();

		filename = outfilename;

		try
		{
			// open the file which needs to be written
			inWriter = new File(filename);

			// Create file
			inWriter.createNewFile();

			Bwriter = new BufferedWriter(new FileWriter(filename));
		}
		catch ( IOException e)
		{
            Logger.writeMessage( this.getClass().getName() + e.toString() ,  Logger.DebugLevel.ERROR);
			System.exit(0);
		}

	}

	/**
	 *	Print output to screen
	 *	@param s - String that needs to be printed
	 */
	public void writeToStdout( String s)
	{
		System.out.println(s);
	}


	/**
	 *	Add a result to String array list
	 *	@param input - Add this string to result array
	 */
	public void storeNewResult( String input)
	{
		results.add(input);
	}


	/**
	 *	Print the entire array of result
	 */
	public void printResult()
	{
		for ( String res : results)
		{
			System.out.println(res);
		}
	}

	/**
	 *       Write to file
	 *       @param - s - String which is to be written to the file
	 */
	public void writeToFile(String s)
	{
		if ( s == null )
		{
			return;
		}
		try
		{
			s = s + "\n";
			Bwriter.write(s);
		}
		catch( IOException e)
		{
            Logger.writeMessage( this.getClass().getName() + e.toString() ,  Logger.DebugLevel.ERROR);
		}
	}


	/**
	 *       Close the file , after it was finished writing
	 */
	public void close()
	{
		try
		{
			Bwriter.close();
		}
		catch ( IOException e)
		{
            Logger.writeMessage( this.getClass().getName() + e.toString() ,  Logger.DebugLevel.ERROR);
		}
	}

	/**
	 *	Write all values in result to file
	 */
	public void ResulttoFile()
	{
		for ( String res : results)
		{
			this.writeToFile(res);
		}
		this.close();
	}

	/**
	 *	All values in result
	 *	@return all values in result
	 */
	public String toString()
	{
		String ret = "Contents of Result array : ";
		for ( String res : results)
		{
			ret = ret + "\n" + res;
		}
		return ret;
	}

};
