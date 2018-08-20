package maxKVisitors.util;

// Reference https://www.geeksforgeeks.org/bubble-sort/

public class ModifiedBubbleSortVisitor implements Visitor
{
    private int K;
    private Results result;

    /**
     * Constructor, set result and K parameter
     * @param size_K
     * @param res_a
     */
    public ModifiedBubbleSortVisitor(int size_K, Results res_a)
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        K = size_K;
        result = res_a;
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
     * Sort elements in an array
     * @param array
     */
    public void visit(MyArray array)
    {
        for ( int i = 0; i < array.getArraylist().size() - 1; i++ )
        {
            for ( int j = 0; j < array.getArraylist().size() - i - 1; j++ )
            {
                if ( array.getArraylist().get(j) < array.getArraylist().get(j + 1))
                {
                    int temp = array.getArraylist().get(j);
                    array.getArraylist().set(j,array.getArraylist().get(j+1));
                    array.getArraylist().set(j+1, temp);
                }
            }
        }

        if ( K > array.getArraylist().size() )
        {
            K = array.getArraylist().size();
        }

        String line = "";
        for ( int i = 0; i < K; i++ )
        {
            line = line + array.getArraylist().get(i) + ",";
        }

        this.result.storeNewResult(line);
    }

    /**
     * Sort elements in vectror
     * @param vector
     */
    public void visit(MyVector vector)
    {
        for ( int i = 0; i < vector.getVectorlist().size() - 1; i++ )
        {
            for ( int j = 0; j < vector.getVectorlist().size() - i - 1; j++ )
            {
                if ( vector.getVectorlist().get(j) < vector.getVectorlist().get(j + 1))
                {
                    int temp = vector.getVectorlist().get(j);
                    vector.getVectorlist().set(j,vector.getVectorlist().get(j+1));
                    vector.getVectorlist().set(j+1, temp);
                }
            }
        }
        if ( K > vector.getVectorlist().size() )
        {
            K = vector.getVectorlist().size();
        }

        String line = "";
        for ( int i = 0; i < K; i++ )
        {
            line = line + vector.getVectorlist().get(i) + ",";
        }

        this.result.storeNewResult(line);
    }
};