package maxKVisitors.util;

public class PopulateVisitor implements Visitor
{
    private FileProcessor Reader;

    /**
     * Constructor
     * @param in
     */
    public PopulateVisitor( FileProcessor in)
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ",MyLogger.DebugLevel.CONSTRUCTOR);
        Reader = in;
    }

    /**
     * Call visit for array
     * @param array
     */
    public void accept(MyArray array)
    {
        this.visit(array);
    }

    /**
     * Call visit for vector
     * @param vector
     */
    public void accept(MyVector vector)
    {
        this.visit(vector);
    }

    /**
     * Add elements to array
     * @param array
     */
    public void visit( MyArray array)
    {
        Integer Intline = 0;
        try
        {
            while ((Intline = Reader.nextInt()) != null)
            {
                array.add(Intline);
            }
            Reader.reset();
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
            return;
        }

    }

    /**
     * add elements to vector
     * @param vector
     */
    public void visit( MyVector vector)
    {

        Integer Intline = null;
        try
        {
            while ((Intline = Reader.nextInt()) != null)
            {
                vector.add(Intline);
            }
            Reader.reset();
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
            return;
        }
    }
};