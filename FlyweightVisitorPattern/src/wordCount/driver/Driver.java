package wordCount.driver;

import java.io.File;

import wordCount.treeOperations.treeInfoOperations.TreeOperationsCountVisitor;
import wordCount.treeOperations.treeInsertOperations.PopulateMyTreeVisitor;
import wordCount.treeOperations.treeInsertOperations.Visitor;
import wordCount.treesForStrings.MyTreeImpl;
import wordCount.util.FileDisplayInterface;
import wordCount.util.MyLogger;
import wordCount.util.MyLogger.DebugLevel;
import wordCount.util.Results;

public class Driver
{

	
	/**
	 * @param args
	 * @return void
	 */
	public static void main(String args[]) {
		try {
			if(args.length!=4 || args[0]==null || args[0].equalsIgnoreCase("${arg0}") ||
					args[1]==null || args[1].equalsIgnoreCase("${arg1}") || 
					args[2]==null || args[2].equalsIgnoreCase("${arg2}") || 
					args[3]==null || args[3].equalsIgnoreCase("${arg3}")) {
				MyLogger.writeMessage("Incorrect arguments passed, Expected <input.txt> <output.txt> <NUM_ITTERATIONS> <Debug Value>. \n Exiting the program. \n", DebugLevel.ERROR);
				System.err.println("Incorrect arguments passed, Expected <input.txt> <output.txt> <NUM_ITTERATIONS> <Debug Value>. \\n Exiting the program. \\n");
				System.exit(0);
			}
			
	        if (!check_Fileexsist(args[0]))
	        {
				MyLogger.writeMessage("Input file does not exist", DebugLevel.ERROR);
				MyLogger.writeMessage("Input file does not exist", DebugLevel.DEBUG);
	            System.err.println("Input file does not exist");
	            System.exit(0);
	        }
			int debugValue = Integer.parseInt(args[3]);
			if(!(debugValue > 0 && debugValue < 7)) {
				MyLogger.writeMessage("Debug value is outside of the expected range (1-6) per code \n", DebugLevel.ERROR);
				System.err.println("Debug value is outside of the expected range (1-6) per code.");
				System.err.println("Exiting the program. \n");
				System.exit(0);				
			}
			MyLogger.setDebugValue(debugValue);

			int NUM_ITTERATIONS = 0;
            try {
                NUM_ITTERATIONS = Integer.parseInt(args[2]);
                if (NUM_ITTERATIONS < 1) {
                    MyLogger.writeMessage("NUM_ITTERATION value is outside of the expected range (1-INF)  \n", DebugLevel.ERROR);
                    System.err.println("NUM_ITTERATION value is outside of the expected range (1-INF).");
                    System.err.println("Exiting the program. \n");
                    System.exit(0);
                }
            }
            catch (NumberFormatException e)
            {
                MyLogger.writeMessage("Exception occured as NUM_ITTERATION argument is not a number \n", DebugLevel.ERROR);
                System.err.println("Exception occured as NUM_ITTERATION argument is not a number. \n");
                System.err.println("Exiting the program. \n");
                System.exit(0);
            }
            // Picking the idea of FlyWeight pattern by picking the
            // below objects before the loop iterations
            Visitor popVisitor = new PopulateMyTreeVisitor();
            Visitor treeOpsCountVisitor = new TreeOperationsCountVisitor();
            Results resultsObj = new Results(args[1]);
            MyTreeImpl myTree = null; 
            
            long startTime = System.currentTimeMillis();
            for ( int i = 0; i < NUM_ITTERATIONS; i ++ ) {
                myTree = new MyTreeImpl(args[0]);                
                myTree.accept(popVisitor);                
                myTree.accept(treeOpsCountVisitor);
            }
			resultsObj.storeNewResult("Number of words : " + myTree.getNo_words());
			resultsObj.storeNewResult("Number of distinct words : " + myTree.getNo_distinct_words());
			resultsObj.storeNewResult("Number of characters : " + myTree.getNo_characters());
            
            long finishTime = System.currentTimeMillis();
            System.out.println("Total time : " +  (float) (finishTime-startTime) / NUM_ITTERATIONS );
            FileDisplayInterface fileDispInterface = resultsObj;
            for (String result : resultsObj.getResultsList()) {
                fileDispInterface.writeToFile(result);
            }
            resultsObj.closeBufferWriter();

		}catch(NumberFormatException e) {
			MyLogger.writeMessage("Exception occured as debug argument is not a number \n", DebugLevel.ERROR);
			System.err.println("Exception occured as debug argument is not a number. \n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}
		catch (Exception e) {
			MyLogger.writeMessage("Exception occured : "+ e.getMessage()+"\n", DebugLevel.ERROR);
			System.err.println("Exception occured : "+ e.getMessage()+"\n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}finally {
			//nothing implemented
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString(){
		return "";
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
}