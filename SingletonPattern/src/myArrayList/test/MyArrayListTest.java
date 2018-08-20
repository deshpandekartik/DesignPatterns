package myArrayList.test;

import myArrayList.util.Results;
import myArrayList.MyArrayList;
import myArrayList.util.FileProcessor;

public class MyArrayListTest 
{
	private String inFilename ;

	/*
	*	Constructor, initialize private members
	*	@param - inputFilename name of the input file
	*/
	public MyArrayListTest( String inputFilename)
	{
		inFilename = inputFilename;
	}

	/*
	*	testMe - Function that calls 
	*	Private function to insert data from file to array
	*	Call's 10 different test to verify the result
	*	@param myArrayList - Object of type MyArrayList, results of type Results
	*/
	public void testMe( MyArrayList myArrayList, Results results)
	{
		myArrayList = this.resetmyArrayList();
		this.FileinsertArray(myArrayList,inFilename);
		results.storeNewResult("The sum of all the values in the array list is: " + myArrayList.sum());

 		myArrayList = this.resetmyArrayList();
		results.storeNewResult(this.TestInsertion(myArrayList));	
		myArrayList = this.resetmyArrayList();
		results.storeNewResult(this.TestRemove(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestMultipleRemove(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestAllRemove(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestInvalidInput(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestAddZero(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestRemoveZero(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestOneLevelResize(myArrayList));
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestTwoLevelResize(myArrayList));	
		myArrayList = this.resetmyArrayList();
                results.storeNewResult(this.TestNullList(myArrayList));
	}
	
	/*
	* 	1. TestInsertion : checks if numbers are sorted , sum and size of list validate
	*	Required Output : 
	*	Size : 3, Sum : 80, list : 10,20,50
	*	@param list - MyArrayList
	*	@return	- Success / Failure message
	*/
	public String TestInsertion( MyArrayList list)
	{
		list.insertSorted(50);
		list.insertSorted(10);
		list.insertSorted(20);

		if ( ! this.isSize(list,3) )
		{
			return "TestInsertion failed, Size mismatch. size of MyArrayList is " + list.size() ;
		}
		
		if ( ! this.isSum(list,80) )
		{
			return "TestInsertion failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
		}	
	
		if ( ! this.isIndex(list,10,0) || ! this.isIndex(list,20,1) || ! this.isIndex(list,50,2) )
		{
			return "TestInsertion failed, Elements not sorted";
		}

		return "TestInsertion passsed";	
	}


	/*
        *       2. TestRemove :
	*	checks for a removal of element
        *       sum and size of list validation, and order of sorted list
        *       Required Output : 
        *       Size : 2, Sum : 60, list : 10,50
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestRemove( MyArrayList list)
        {
                list.insertSorted(50);
                list.insertSorted(10);
		list.insertSorted(20);

		list.removeValue(20);

		if ( ! this.isIndex(list,20,-1) )
		{
			return "TestRemove failed, Could'nt remove a value from list";
		}

                if ( ! this.isSize(list,2) )
                {
                        return "TestRemove failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list,60) )
                {
                        return "TestRemove failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                if ( ! this.isIndex(list,10,0) || ! this.isIndex(list,50,1) )
                {
                        return "TestRemove failed, Elements not sorted";
                }

                return "TestRemove passsed";
        }

        /*
        *       3. TestMultipleRemove : 
        *       checks for removal of multiple elements
	*	sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 1, Sum : 50, list : 50
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestMultipleRemove( MyArrayList list)
        {
                list.insertSorted(50);
                list.insertSorted(10);
                list.insertSorted(20);
                list.insertSorted(20);

                list.removeValue(20);
		list.removeValue(10);


                if ( ! this.isIndex(list,10,-1) || ! this.isIndex(list,20,-1))
                {
                        return "TestMultipleRemove failed, Could'nt remove a value from list";
                }

                if ( ! this.isSize(list,1) )
                {
                        return "TestMultipleRemove failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list,50) )
                {
                        return "TestMultipleRemove failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                if ( ! this.isIndex(list,50,0) )
                {
                        return "TestMultipleRemove failed, Elements not sorted";
                }

                return "TestMultipleRemove passsed";
        }
	
        /*
        *       4. TestAllRemove : 
        *       checks for removal of all elements
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 0, Sum : 0, list : [ empty ]
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestAllRemove( MyArrayList list)
        {
                list.insertSorted(50);
                list.insertSorted(10);
                list.insertSorted(20);
                list.insertSorted(20);

                list.removeValue(20);
                list.removeValue(10);
		list.removeValue(50);

                if ( ! this.isIndex(list,10,-1) || ! this.isIndex(list,20,-1) || ! this.isIndex(list,50,-1) )
                {
                        return "TestAllRemove failed, Could'nt remove a value from list";
                }

                if ( ! this.isSize(list,0) )
                {
                        return "TestAllRemove failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list,0) )
                {
                        return "TestAllRemove failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                return "TestAllRemove passsed";
        }


        /*
        *       5. TestInvalidInput :
        *       checks if invalid number can be inserted into list
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 3, Sum : 80, list : 10,20,50
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestInvalidInput( MyArrayList list)
        {
                list.insertSorted(50);
                list.insertSorted(10);
                list.insertSorted(20);
                list.insertSorted(-12);

                if ( ! this.isSize(list,3) )
                {
                        return "TestInvalidInput failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list,80) )
                {
                        return "TestInvalidInput failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                if ( ! this.isIndex(list,10,0) || ! this.isIndex(list,20,1) || ! this.isIndex(list,50,2) )
                {
                        return "TestInvalidInput failed, Elements not sorted";
                }

                return "TestInvalidInput passsed";
        }


        /*
        *       6. TestAddZero :
        *       checks what happens when zero is inserted 
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 3, Sum : 60, list : 0,10,50
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestAddZero( MyArrayList list)
        {
                list.insertSorted(50);
                list.insertSorted(10);
                list.insertSorted(0);

                if ( ! this.isSize(list,3) )
                {
                        return "TestAddZero failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list,60) )
                {
                        return "TestAddZero failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                if ( ! this.isIndex(list,0,0) || ! this.isIndex(list,10,1) || ! this.isIndex(list,50,2) )
                {
                        return "TestAddZero failed, Elements not sorted";
                }

                return "TestAddZero passsed";
        }

        /*
        *       7. TestRemoveZero : 
        *       checks what happens when zero is inserted and then removed
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 2, Sum : 60, list : 10,50
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestRemoveZero( MyArrayList list)
        {
                list.insertSorted(50);
                list.insertSorted(10);
                list.insertSorted(0);

		list.removeValue(0);

                if ( ! this.isIndex(list,0,-1) )
                {
                        return "TestRemoveZero failed, Could'nt remove a value from list";
                }


                if ( ! this.isSize(list,2) )
                {
                        return "TestRemoveZero failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list,60) )
                {
                        return "TestRemoveZero failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                if ( ! this.isIndex(list,10,0) || ! this.isIndex(list,50,1) )
                {
                        return "TestRemoveZero failed, Elements not sorted";
                }

                return "TestRemoveZero passsed";
        }

        /*
        *       8. TestOneLevelResize : 
        *       Insertion of 51 elements
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 51, Sum : 1326, list : 1 to 51
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestOneLevelResize( MyArrayList list)
        {

		int max_num = 51;

		for ( int i = 0 ; i <= max_num - 1; i++ )
		{
			list.insertSorted(i);
		}


                if ( ! this.isSize(list,max_num) )
                {
                        return "TestOneLevelResize failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list, sumoffirstn(max_num)) )
                {
                        return "TestOneLevelResize failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

		for ( int i = 0; i <= max_num - 1; i ++ )
		{
			if ( ! this.isIndex(list,i,i) )
			{
				return "TestOneLevelResize failed, Elements not sorted";
			}
		}
		
                return "TestOneLevelResize passsed";
        }


        /*
        *       9. TestTwoLevelResize : 
        *       Insertion of 76 elements
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 76, Sum : 2926, list : 1 to 76
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestTwoLevelResize( MyArrayList list)
        {
		int max_num = 76;

                for ( int i = 0 ; i <= max_num - 1; i++ )
                {
                        list.insertSorted(i);
                }



                if ( ! this.isSize(list,max_num) )
                {
                        return "TestTwoLevelResize failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list, sumoffirstn(max_num)) )
                {
                        return "TestTwoLevelResize failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                for ( int i = 0; i <= max_num - 1; i ++ )
                {
                        if ( ! this.isIndex(list,i,i) )
                        {
                                return "TestTwoLevelResize failed, Elements not sorted";
                        }
                }

                return "TestTwoLevelResize passsed";
        }

        /*
        *       10. TestNullList :
        *       Insertion of 0 elements
        *       sum and size of list validation, and order of sorted list
        *       Required Output :
        *       Size : 0, Sum : 0, list : []
        *       @param list - MyArrayList
        *       @return - Success / Failure message
        */
        public String TestNullList( MyArrayList list)
        {

                if ( ! this.isSize(list,0) )
                {
                        return "TestNullList failed, Size mismatch. size of MyArrayList is " + list.size() ;
                }

                if ( ! this.isSum(list, 0) )
                {
                        return "TestNullList failed, Sum mismatch. sum of MyArrayList is " + list.sum() ;
                }

                return "TestNullList passsed";
        }
	


        /*
        *       Insert numbers from file to myarraylist
        */
        private void FileinsertArray( MyArrayList myArrayList, String filename)
        {
                String line;
		FileProcessor FileReader = new FileProcessor(filename);
                while ( (line = FileReader.readLine() ) != null)
                {
                        if ( !isInteger(line) )
                        {
                                //System.err.println("Non integer value found in input file : " + line);
                                //System.exit(0);
                        }
                        else
                        {
                                myArrayList.insertSorted(Integer.parseInt(line));
                        }
                }
                //this.removeValue(20);
                //System.out.println(this.toString());
        }

        /*
        *       Checks if a string is a Integer or not
        *       @param s - the string which needs to be tested
        *       @return - True if passed string is a integer , False otherwise
        */
        private boolean isInteger(String s)
        {
                try
                {
                        int i = Integer.parseInt(s);
                        return true;
                }
                catch( NumberFormatException er)
                {
                        return false;
                }
        }


	/*
	*	Check if size of arraylist matches with required size
	*	@return : True if matched False otherwise
	*/
	private boolean isSize( MyArrayList list, int required_size)
	{
		if ( list.size() != required_size )
		{
			return false;
		}
		return true;
	}
	

	/*
        *       Check if sum of arraylist matches with required sum
        *       @return : True if matched False otherwise
        */
        private boolean isSum( MyArrayList list, int required_sum)
        {
                if ( list.sum() != required_sum )
                {
                        return false;
                }
                return true;
        }


	/*
        *       Check if index of a number matches with required index
        *       @return : True if matched False otherwise
        */
        private boolean isIndex( MyArrayList list, int val , int required_index)
        {
                if ( list.indexOf(val) != required_index )
                {
                        return false;
                }
                return true;
        }

	/*
	*	Finds the sum of first n numbers
	*	The number upto which sum needs to be calculated
	*	@param num - number upto which sum needs to be calculated
	*	@return sum of first n numbers
	*/
	private int sumoffirstn( int num)
	{
		int sum = 0;
		for ( int i = 0; i <= num - 1; i++ )
		{
			sum = sum + i;
		}
		return sum;
	}

	/*
	*	Create's new arrray list to overide exsising one
	*	@return new array list
	*/
	private MyArrayList resetmyArrayList()
	{
		MyArrayList tempArray = new MyArrayList();	
		return tempArray;
	}
};
