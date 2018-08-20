package studentCoursesBackup.driver;

import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.other.Student;

import studentCoursesBackup.util.FileProcessor;
import java.io.File;

public class Driver
{
    public static void main(String[] args) {
        if (args.length != 6 || args[0].contains("{arg0}") || args[1].contains("{arg1}") || args[2].contains("{arg2}")) {
            System.err.println("Invalid arguments specified");
            System.err.println("Command : ant -buildfile build.xml run -Darg0=infile.txt -Darg1=outfile.txt -Darg2=DEBUG_LEVEL");
            System.err.println("DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - Constructor, 3 - Results, 4 - Error , 5 : Debug");
            System.exit(0);
        }

        /*
        String insertfile = "/home/kartik/projects/design-patterns/csx42-s18-assign-3-deshpandekartik/kartik_deshpande_assign_3/studentCoursesBackup/in.txt";
        String deletefile = "/home/kartik/projects/design-patterns/csx42-s18-assign-3-deshpandekartik/kartik_deshpande_assign_3/studentCoursesBackup/delete.txt";
        String out1 = "/home/kartik/projects/design-patterns/csx42-s18-assign-3-deshpandekartik/kartik_deshpande_assign_3/studentCoursesBackup/output1.txt";
        String out2 = "/home/kartik/projects/design-patterns/csx42-s18-assign-3-deshpandekartik/kartik_deshpande_assign_3/studentCoursesBackup/output2.txt";
        String out3 = "/home/kartik/projects/design-patterns/csx42-s18-assign-3-deshpandekartik/kartik_deshpande_assign_3/studentCoursesBackup/output3.txt";
        String DebugLevel = "4";
        */

        String insertfile = args[0];
        String deletefile = args[1];
        String out1 = args[2];
        String out2 = args[3];
        String out3 = args[4];
        String DebugLevel = args[5];
        int debug_level;

        if (!check_Fileexsist(insertfile))
        {
            System.err.println("Input file does not exist");
            System.exit(0);
        }

        if ( !check_Fileexsist(deletefile))
        {
            System.err.println("Delete file does not exist");
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

        String line;
        FileProcessor FileReader = new FileProcessor(insertfile);

        // will require 3 trees
        TreeBuilder Tree = new TreeBuilder();

        // insert courses
        while ( (line = FileReader.readLine() ) != null)
        {

            if ( ! line.contains(":"))
                continue;

            String bNumberStr = line.split(":")[0].trim();
            String course = line.split(":")[1].trim();

            if ( isInteger(bNumberStr) && course.matches("[A-K]"))
            {
                int bNumber = Integer.parseInt(bNumberStr);

                // check if node exist with this bNumber
                Node searched = Tree.gettreelist().get(0).SearchNode(bNumber);

                if ( searched != null )
                {
                    // node already exist

                    Student std = new Student(bNumber,course, Student.operation.INSERT);
                    // update orignal node
                    searched.update(std);
                }
                else
                {
                    // add new node
                    Node Node_orig = new Node(bNumber,course);
                    Node backup_Node_1 = Node_orig.clone();
                    Node backup_Node_2 = Node_orig.clone();

                    Node_orig.registerObserver(backup_Node_1);
                    Node_orig.registerObserver(backup_Node_2);

                    Tree.gettreelist().get(0).insert(Node_orig);
                    Tree.gettreelist().get(1).insert(backup_Node_1);
                    Tree.gettreelist().get(2).insert(backup_Node_2);

                }
            }
        }

        // delete courses
        FileReader = new FileProcessor(deletefile);
        while ( (line = FileReader.readLine() ) != null)
        {
            if ( ! line.contains(":"))
                continue;

            String bNumberStr = line.split(":")[0].trim();
            String course = line.split(":")[1].trim();

            if (isInteger(bNumberStr) && course.matches("[A-K]"))
            {
                int bNumber = Integer.parseInt(bNumberStr);

                // check if node exist with this bNumber
                Node searched = Tree.gettreelist().get(0).SearchNode(bNumber);

                if (searched != null)
                {
                    // node exist

                    Student std = new Student(bNumber, course, Student.operation.DELETE);
                    // update orignal node
                    searched.update(std);
                }
            }
        }

        Results results;

        results = new Results(out1);
        Tree.gettreelist().get(0).printNodes(results);
        results.ResulttoFile();

        results = new Results(out2);
        Tree.gettreelist().get(1).printNodes(results);
        results.ResulttoFile();

        results = new Results(out3);
        Tree.gettreelist().get(2).printNodes(results);
        results.ResulttoFile();

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
