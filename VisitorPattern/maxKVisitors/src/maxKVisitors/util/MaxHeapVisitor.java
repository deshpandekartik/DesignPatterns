package maxKVisitors.util;

import java.util.ArrayList;
import java.util.Vector;

// Reference : https://www.geeksforgeeks.org/heap-sort/

public class MaxHeapVisitor implements Visitor
{
    private int K;
    private Results results;
    private ArrayList<Integer> list;
    private String resline = null;
    private int ressize = 0;
    /**
     * Constructor set K and result
     * @param k_size
     * @param res_arg
     */
    public MaxHeapVisitor( int k_size, Results res_arg)
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        K = k_size;
        results = res_arg;
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
        resline = "";
        ressize = 0;
        list = array.getArraylist();
        this.sort(list);
        array.setArraylist(list);
        results.storeNewResult(resline);
    }

    /**
     * Sort elements in vectror
     * @param vector
     */
    public void visit( MyVector vector)
    {
        resline = "";
        ressize = 0;
        list = new ArrayList<Integer>(vector.getVectorlist());
        this.sort(list);
        Vector<Integer> newL = new Vector<Integer>(list);
        vector.setVectorlist(newL);
        results.storeNewResult(resline);
    }


    /**
     * Sort a array
     * @param arr
     */
    public void sort(ArrayList<Integer> arr)
    {
        int n = arr.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr.get(0);
            arr.set(0,arr.get(i));
            arr.set(i,temp);

            ressize = ressize + 1;
            resline = resline + temp + ",";

            if ( ressize >= K )
            {
                return;
            }

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    /**
     * To heapify a subtree rooted with node i which is
     * an index in arr[]. n is size of heap
     * @param arr
     * @param n
     * @param i
     */
    void heapify(ArrayList<Integer> arr, int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr.get(l) > arr.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr.get(r) > arr.get(largest))
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr.get(i);
            arr.set(i,arr.get(largest));
            arr.set(largest,swap);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}