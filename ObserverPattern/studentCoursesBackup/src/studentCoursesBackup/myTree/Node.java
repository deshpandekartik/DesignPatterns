package studentCoursesBackup.myTree;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.other.Student;

import java.util.ArrayList;

public class Node implements ObserverI, SubjectI
{
    private int bNumber;
    private ArrayList<String> courses = new ArrayList<String>();
    private Node left, right;
    private String added_course;
    private ArrayList<Node> listeners = new ArrayList<Node>();
    private Boolean orignal_node;

    /**
     * Empty constructor
     */
    public Node()
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        this.left = null;
        this.right = null;
        this.added_course = null;
    }

    /**
     * Constructor
     * @param bNo
     * @param a_course
     */
    public Node( int bNo , String a_course)
    {
        this.bNumber = bNo;
        this.courses.add(a_course);
        this.left = null;
        this.right = null;
        this.added_course = a_course;
        this.orignal_node = true;
    }

    /**
     * @return Bnumber of node
     */
    public int getbNumber()
    {
        return this.bNumber;
    }
    /**
     * @return Left node of current
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * @return Right node of current
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * @return Array list of courses
     */
    public ArrayList<String> getAllCourses()
    {
        return this.courses;
    }

    /**
     * set Bnumber
     * @param No
     */
    public void setbNumber(int No)
    {
        this.bNumber = No;
    }

    /**
     * set all courses at once
     * @param a_courses
     */
    public void setAllCourses( ArrayList<String> a_courses)
    {
        this.courses = a_courses;
    }

    /**
     * set left node
     * @param aleft
     */
    public void setLeft(Node aleft)
    {
        this.left = aleft;
    }

    /**
     * set right node
     * @param aright
     */
    public void setRight(Node aright)
    {
        this.right = aright;
    }

    /**
     * @return The most recently added course
     */
    public String getAdded_course()
    {
        return this.added_course;
    }

    /**
     * Add a course
     */
    public void update(Student student)
    {
        String a_course = student.getCourse();

        // add the course
        if ( student.getStatus() == Student.operation.INSERT && ! courses.contains(a_course) )
        {
            this.courses.add(a_course);
        }

        // remove the course
        if ( student.getStatus() == Student.operation.DELETE && courses.contains(a_course))
        {
            this.courses.remove(a_course);
        }

        // update backup nodes
        if ( this.orignal_node == true )
        {
            this.notifyAll(student);
        }
    }

    /**
     * Add a listner
     * @param a_listner
     */
    public void registerObserver(Node a_listner)
    {
        listeners.add(a_listner);
    }

    /**
     * remove a listner from obsever list
     * @param observer
     */
    public void removeObserver(Node observer)
    {
        if ( listeners.contains(observer))
        {
            listeners.remove(observer);
        }
    }


    /**
     * Notify all backup nodes about changes
     * @param student
     */
    public void notifyAll(Student student)
    {
        for ( Node Backupnode : listeners)
        {
            Backupnode.update(student);
        }
    }

    /**
     * Clone the exsisiting object and return
     * @return cloned instance
     */
    public Node clone()
    {
         Node cloned =  new Node(bNumber,added_course);
         cloned.orignal_node = false;
         return cloned;
    }
};
