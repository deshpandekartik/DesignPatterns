package myArrayList.driver;

import myArrayList.MyArrayList;
import myArrayList.util.Results;
import myArrayList.test.MyArrayListTest;

public class Driver 
{

	public static void main(String[] args) 
	{
		if ( args.length != 3 || args[0].contains("{arg0}") || args[1].contains("{arg1}"))
		{
			System.out.println("Invalid arguments specified");
			System.out.println("Command : ant -buildfile build.xml run -Darg0=infile.txt -Darg1=outfile.txt");
			System.exit(0);
		}
		String infile = args[0];
		String outfile = args[1];

		MyArrayList array = new MyArrayList();		
		Results result = new Results( outfile );
		MyArrayListTest test = new MyArrayListTest(infile);
		
		test.testMe(array,result);
		
		//System.out.println(result.toString());
		result.ResulttoFile();
	}
};
