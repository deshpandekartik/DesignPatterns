package maxKVisitors.util;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
	private ArrayList<String> results;
	private String filename ;
	private File inWriter;
	private BufferedWriter Bwriter;


    /**
     * Constructor, Used when only stdout required
     */
    public Results()
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        filename = null;
        Bwriter = null;
        results = new ArrayList<String>();
    }

	/**
	 *	Constructor, Used when we have to write to a file
	 *	@param outfilename - Name of the file where output is to be written
	 */
	public Results( String outfilename)
	{
		MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
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
            MyLogger.writeMessage( this.getClass().getName() + e.toString() ,  MyLogger.DebugLevel.ERROR);
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
            MyLogger.writeMessage( this.getClass().getName() + e.toString() ,  MyLogger.DebugLevel.ERROR);
		}
	}


	/**
	 *       Close the file , after it was finished writing
	 */
	public void close()
	{
	    if ( filename == null )
        {
            return;
        }
		try
		{
			Bwriter.close();
		}
		catch ( IOException e)
		{
            MyLogger.writeMessage( this.getClass().getName() + e.toString() ,  MyLogger.DebugLevel.ERROR);
		}
	}

	/**
	 *	Write all values in result to file
	 */
	public void ResulttoFile()
	{
        if ( filename == null )
        {
            System.err.println("Results instance invoked without file level support");
            return;
        }

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
