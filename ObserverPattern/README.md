
Name : Kartik Deshpande
Bmail : kdeshpa3

-----------------------------------------------------------------------
Introduction:
Every node will have two backup nodes as observers . Once a course is 
updated / deleted for a node. The node will notify its observers about 
the operation and perform the update.

-----------------------------------------------------------------------

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run 
ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=delete.txt - Darg2=output1.txt -Darg3=output2.txt -Darg4=output3.txt -Darg5=DEBUG_LEVEL

DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - COnstructor, 3 - Results, 4 - Error , 5 - Debug

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
[Date:  7/10/2018 ]

-----------------------------------------------------------------------

#Data Structures Used and complexity :	
Binary search tree
Array list

Used BST for tree, as it's easy to add and search for nodes, the complexity is o(logn)

-----------------------------------------------------------------------

# References:
https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html	( FILES )
https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/ ( BST )
