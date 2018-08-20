package fourWayStreetLights.driver;

import fourWayStreetLights.util.Logger;
import fourWayStreetLights.util.Results;
import fourWayStreetLights.service.StretLights;
import java.io.File;

public class Driver
{
    public static void main(String[] args) {
        if (args.length != 3 || args[0].contains("{arg0}") || args[1].contains("{arg1}") || args[2].contains("{arg2}")) {
            System.err.println("Invalid arguments specified");
            System.err.println("Command : ant -buildfile build.xml run -Darg0=infile.txt -Darg1=outfile.txt -Darg2=DEBUG_LEVEL");
            System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - COnstructor, 3 - Results, 4 - Error ");
            System.exit(0);
        }

        String infile = args[0];
        String outfile = args[1];
        String DebugLevel = args[2];
        int debug_level;
        if (!check_Fileexsist(infile))
        {
            System.err.println("Input file does not exist");
            System.exit(0);
        }

        try
        {
            debug_level = Integer.parseInt(DebugLevel);
            if ( debug_level < 0 || debug_level > 4 )
            {
                System.err.println("Debug level should be between 0 and 3");
                System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - COnstructor, 3 - Results, 4 - Error ");
                System.exit(0);
            }
            Logger.setDebugValue(debug_level);
        }
        catch (NumberFormatException er)
        {
            System.err.println("Debug level should be a number");
            System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - COnstructor, 3 - Results, 4 - Error ");
            System.exit(0);
        }

	    Results res = new Results(outfile);
	    StretLights st = new StretLights(infile,outfile,res);
	    res.ResulttoFile();
    }

    /**
     * check if file exsit
     * @param filename
     * @return
     */
    private static boolean check_Fileexsist(String filename)
    {
        File f = new File(filename);
        if ( f.exists() && !f.isDirectory())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
};
