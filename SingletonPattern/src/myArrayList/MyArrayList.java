package myArrayList;

public class MyArrayList
{
	private int[] myIntArray;
	private float increase_percent;
	private int max_array_size;
	private int cur_array_size;
	private int myIntArray_sum;

        /*
        *       Consutructor, sets the private data members to default values
        */
	public MyArrayList()
        {
		max_array_size = 50;
		cur_array_size = 0;	// currenlty there are 0 elements in the array
		increase_percent = 50.0f;	// increase by 50% of current array size every time array gets full
		myIntArray_sum = 0;

		myIntArray = new int[max_array_size];
        }

	/*
	*	Insert's nmbers into array while keeping the array sorted
	*	@param newValue - The value to be inserted
	*/
	public void insertSorted( int newValue)
	{
		if ( newValue < 0 || newValue > 10000 )
		{
			//throw new IllegalArgumentException("number must be greater than zero and less than 10000:" + newValue);
		}
		else
		{
			if ( cur_array_size >= max_array_size )
			{
				// Array full , resize array
				this.IncreaseArray();
			}

			int temp_index = 0;
			while ( temp_index < cur_array_size && newValue > myIntArray[temp_index] )
			{
				temp_index ++;
			}
			for ( int i = max_array_size - 1; i > temp_index; i -- )
			{
				myIntArray[i] = myIntArray[i-1];
			}
			myIntArray[temp_index] = newValue;
			myIntArray_sum = myIntArray_sum + newValue;	
			cur_array_size ++;
		}	
	
	}

	/*
	*	Remove's all occurances of a value from array
	*	@param value - the value which needs to be removed
	*/
	public void removeValue(int value)
	{
	
		while ( this.indexOf(value) != -1 )
		{
			for ( int i = this.indexOf(value); i < cur_array_size ; i++ )
                   	{
                    		myIntArray[i] = myIntArray[i+1];
                   	}
			myIntArray_sum = myIntArray_sum - value;
			cur_array_size --;
		}	
	}


	/*
	*	Find's the index of first occurance of a value in the array
	*	@param value - the value whose index is to be found
	*	@return - Index of the found value, -1 if not present
	*/
	public int indexOf( int value)
	{
		for ( int i = 0; i < cur_array_size; i ++ )
		{
			if ( myIntArray[i] == value )
			{
				return i;
			}
		}

		return -1;
	}

	/*
	*	Size of the array, number of elements in array
	*	@return - Size of array
	*
	*/
	public int size()
	{
		return cur_array_size ;
	}

	/*
	*	Sum of elements inside the array
	*	@return - sum of elements of array
	*/	
	public int sum()
	{
		return myIntArray_sum;
	}
	

        /*
        *       Returns all values in result
        */
        public String toString()
        {

                String ret;

                ret = "Max size " + myIntArray.length + " Size : " + this.size() + "\nSum : " + this.sum() + " \nContents of myIntArray : \n";

                for ( int i = 0; i < cur_array_size; i ++ )
                {
                        ret = ret + " , " + myIntArray[i];
                }

                return ret;

        }

	/*
	*	Increases size of array by 50% of its current size
	*/
	private void IncreaseArray()
	{
		int temp_copy_array_size = max_array_size + ( int ) ( ( increase_percent / 100.0f ) * (float) max_array_size )  ;
		int[] temp_copy_array = new int[temp_copy_array_size];

		// move content from main array to temp array	
		for ( int i = 0; i < myIntArray.length; i ++ )
		{
			temp_copy_array[i] = myIntArray[i];
		}
		
		myIntArray = temp_copy_array;
		max_array_size = temp_copy_array_size;
	}
};
