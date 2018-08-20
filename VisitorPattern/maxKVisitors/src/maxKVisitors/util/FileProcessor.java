package maxKVisitors.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import maxKVisitors.util.MyLogger.DebugLevel;

public class FileProcessor
{
    private String filename;
    private File inReader;
    private BufferedReader Breader;


    /**
     * 	Consutructor, opens the file
     *	@param filen - Name of the file
     */
    public FileProcessor(String filen)
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", DebugLevel.CONSTRUCTOR);

        filename = filen;

        try
        {
            // open the file which needs to be read
            inReader = new File(filename);
            Breader = new BufferedReader(new FileReader(filename));
        }
        catch ( IOException e)
        {
            MyLogger.writeMessage(this.getClass().getName() + e.toString(), DebugLevel.ERROR);
            System.exit(0);
        }

    }

    /**
     *	Reads a file , line by line
     *	@return  The line which was read from file.
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
            MyLogger.writeMessage(this.getClass().getName() + e.toString(), DebugLevel.ERROR);
            System.exit(0);
        }
        return null;
    }

    /**
     * Convert string to int
     * @return : Converted int
     */
    public Integer nextInt()
    {
        String line = this.readLine();
        Integer number = null;

        if ( line != null && this.isInteger(line) == true )
        {
            number = Integer.parseInt(line);
        }
        else
        {
            number = null;
        }
        return number;
    }

    /**
     * Checks if a string is int or not
     * @param str
     * @return True or false
     */
    private boolean isInteger( String str )
    {
        try
        {
            int i = Integer.parseInt(str);
            return true;
        }
        catch( NumberFormatException er)
        {
            return false;
        }
    }

    /**
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
            MyLogger.writeMessage(this.getClass().getName() + e.toString(), DebugLevel.ERROR);
        }
    }

    /**
     * Reset Buffer to start of file
     */
    public void reset()
    {
        try
        {
            Breader = null;
            Breader = new BufferedReader(new FileReader(this.filename));
        }
        catch (IOException e)
        {
            System.err.println(e.toString());
        }
    }

};
