package maxKVisitors.util;

import java.util.ArrayList;

public class MyArray implements ADTI
{
    private ArrayList<Integer> arraylist;

    public MyArray()
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ",MyLogger.DebugLevel.CONSTRUCTOR);
        arraylist = new ArrayList<Integer>();
    }

    /**
     * add an integer to arraylist
     * @param number
     */
    public void add(int number)
    {
        arraylist.add(number);
    }

    /**
     * @return array list
     */
    public ArrayList<Integer> getArraylist()
    {
        return arraylist;
    }

    /**
     * Set arraylist
     * @param list
     */
    public void setArraylist(ArrayList<Integer> list)
    {
        this.arraylist = list;
    }

    /**
     * Convert to string and return
     */
    public String toString()
    {
        String line = "";
        for ( Integer num : arraylist)
        {
            line = line + num + " ,";
        }
        return line;
    }
};