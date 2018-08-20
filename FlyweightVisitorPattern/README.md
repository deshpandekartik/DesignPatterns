
Name : Kartik Deshpande
Bmail : kdeshpa3

Name : Mohit Joshi
Bmail : mjoshi7

-----------------------------------------------------------------------
Assuming you are already in the directory containing this README.md, 

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run 
ant -buildfile src/build.xml run -Darg0=<Absolute path of input file> -Darg1=<Absolute path of output file> -Darg2=<NUM_ITERATIONS value> -Darg3=<Debug value from 1-5>

DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - Constructor, 3 - Results, 4 - Error , 5 - Debug, 6- Info
K_VALUE : Positive Integers only

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

Kartik Deshpande
Mohit Joshi
[Date:  8/03/2018 ]

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).
File Reading from and Writing to code in 2 classes, concept taken from :
https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
1. FileProcessor.java : to read the file
2. Results.java : to write results to file

https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/

-----------------------------------------------------------------------
Cases for which command line validation has been provided 
1. number of passed command line arguments.
2. input.txt not found
3. Debug value not between 1 and 5; which are not defined in enums
4. Passing String which is not a number to the debug value
5. Passing String which is not a number to the NUM_ITERATIONS value

-----------------------------------------------------------------------

#Complexity :
Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

We choose BST(Binary Search Tree) for the insert, search which take
time complexity of order O(log n) where n is the number of the nodes in the tree at a given point in time.
The insert and search operations are of O(log n) complexity as we keep on reducing our scope to half of previous 
with each new level.

-----------------------------------------------------------------------

#Patterns Used::

We have used the "Visitor pattern" as our main pattern as if the logic of operation changes, then we need to make change only in the visitor implementation rather than doing it in other tree implementation classes, and then kept the "Flyweight pattern" behavior as well as we are not creating the Visitor objects, Result objects again and again for the number of iterations, which will increase our performance as it will reduce the number of objects created. The tree that gets built N number of times ( NUM_IITERATIONS ) will use the same visitor object to populate and perform count operations over.

-----------------------------------------------------------------------

#Design implementation :

Each node in BST acts as a word which holds number of occurances of the word in the input file, 
Before inserting a node into BST we first check if a word prexist in the tree. If found we increment count of same node and not just creating the new node.
Finding number of words, number of characters and number of distinct words we traverse through tree only once.

