package maxKVisitors.driver;

import maxKVisitors.util.*;

import java.io.File;

public class Driver
{
    public static void main(String[] args) {
        if (args.length != 3 || args[0].contains("{arg0}") || args[1].contains("{arg1}") || args[2].contains("{arg2}")) {
            System.err.println("Invalid arguments specified");
            System.err.println("Command : ant -buildfile build.xml run -Darg0=infile.txt -Darg1=KVALUE -Darg2=DEBUG_LEVEL");
            System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - Constructor, 3 - Results, 4 - Error , 5 : Debug");
            System.exit(0);
        }

        String inputfile = args[0];
        String KValue = args[1];
        String DebugLevel = args[2];
        int debug_level = 0, K = 0;

        if (!check_Fileexsist(inputfile))
        {
            System.err.println("Input file does not exist");
            System.exit(0);
        }

        if ( isInteger(KValue))
        {
            K = Integer.parseInt(KValue);
            if ( K <= 0 )
            {
                System.out.println("Value of K should be a positive integer");
                System.exit(0);
            }
        }
        else
        {
            System.err.println("Value of K should be a integer, " + KValue);
            System.exit(0);
        }


        if ( isInteger(DebugLevel))
        {
            debug_level = Integer.parseInt(DebugLevel);
            if ( debug_level < 0 || debug_level > 5 )
            {
                System.err.println("Debug level should be between 0 and 3");
                System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - Constructor, 3 - Results, 4 - Error , 5 - Debug");
                System.exit(0);
            }
            MyLogger.setDebugValue(debug_level);
        }
        else
        {
            System.err.println("Debug level should be a number");
            System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - Constructor, 3 - Results, 4 - Error, 5 - Debug ");
            System.exit(0);
        }

        MyVector vector1 = new MyVector();
        MyArray array1 = new MyArray();

        MyVector vector2 = new MyVector();
        MyArray array2 = new MyArray();

        FileProcessor inFile = new FileProcessor(inputfile);

        Visitor visitor = new PopulateVisitor(inFile);

        visitor.accept(array1);
        visitor.accept(vector1);

        visitor.accept(array2);
        visitor.accept(vector2);

        Results res = new Results();
        Visitor heapsort = new MaxHeapVisitor(K,res);
        Visitor bubblesort = new ModifiedBubbleSortVisitor(K,res);

        heapsort.accept(array1);
        heapsort.accept(vector1);

        bubblesort.accept(array2);
        bubblesort.accept(vector2);

        res.printResult();

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

    /**
     * Checks if a string is int or not
     * @param str
     * @return True or false
     */
    private static boolean isInteger( String str )
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
};
