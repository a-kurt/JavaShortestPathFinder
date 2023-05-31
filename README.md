# JavaShortestPathFinder
JavaShortestPathFinder is a Java-based project that implements Dijkstra's and BFS (Breadth-First Search) algorithms for efficient shortest path finding in a graph.

Features
    Implementation of Dijkstra's algorithm for weighted graphs.
    Implementation of BFS algorithm for unweighted graphs.
    Determine the shortest path between two nodes in 2D grid.
    Well-documented code with clear examples and usage instructions.

Usage
    Clone the repository: git clone https://github.com/a-kurt/JavaShortestPathFinder.git
    Navigate to the project directory: cd JavaShortestPathFinder/src
    Compile the Java source files: javac *.java
    Run the program: java main

Examples

Here's a simple example demonstrating how to use the JavaShortestPathFinder:

java

/* <br />
  &emsp;No need to specify int X_SIZE, int Y_SIZE. It is dynamic.<br />
  &emsp;File:<br />
    &emsp;First Line:   (y,x) of starting coordinate.<br />
    &emsp;Second Line:  (y,x) of end coordinate.<br />
    &emsp;Rest is grid which consists 0's and 1's. Program can travel in 0's. 1's are blocks.<br />
*/<br />
public TestCases(String FileName, int X_SIZE, int Y_SIZE) {<br />
        &emsp;this.FileName = FileName;<br />
        &emsp;this.X_SIZE = X_SIZE;<br />
    	&emsp;this.Y_SIZE = Y_SIZE;<br />
}<br />


@ Atakan Kurt
