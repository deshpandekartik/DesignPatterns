
Name : Kartik Deshpande
Bmail : kdeshpa3

-----------------------------------------------------------------------
# Intro

The following assumptions have been made,

1.Only one signal can be green at a time, when one signal is green other signal are red.
2. When a signal is green, cars from that signal can pass to any direction. Ex if north is green,
cars from north can pass to east/ south / west.

-----------------------------------------------------------------------
# Input format

- To add cars,

N-4 //adds 4 cars to North side
W-4 // adds 4 cars to west side

- To make a signal green.
N@Green // Changes north side signal to green
W@Green // Changes west side signal to green

- To make a signal Red
N@RED
E@RED

-----------------------------------------------------------------------

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run 
ant -buildfile src/build.xml run -Darg0=in.txt -Darg1=outFile.txt -Darg2=DEBUG_LEVEL

DEBUG_LEVEL : 0 - None, 1 - File Porcessor, 2 - COnstructor, 3 - Results, 4 - Error

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
[Date: 06/28/2018 ]

-----------------------------------------------------------------------

#Data Structures Used :

Complexity : n - ( No of lines in file )

-----------------------------------------------------------------------

# References:
https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
