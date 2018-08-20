package maxKVisitors.util;

import java.util.Vector;

public class MyVector implements ADTI
{
    private Vector<Integer> vectorlist;

    public MyVector()
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ",MyLogger.DebugLevel.CONSTRUCTOR);
        vectorlist = new Vector<Integer>();
    }

    /**
     * add an integer to vectorlist
     * @param number
     */
    public void add(int number)
    {
        vectorlist.add(number);
    }

    /**
     * @return vector list
     */
    public Vector<Integer> getVectorlist()
    {
        return vectorlist;
    }

    /**
     * set vector list
     * @param list
     */
    public void setVectorlist(Vector<Integer> list)
    {
        this.vectorlist = list;
    }

    /**
     * Convert to string and return
     */
    public String toString()
    {
        String line = "";
        for ( Integer num : vectorlist)
        {
            line = line + num + " ,";
        }

        return line;
    }
};