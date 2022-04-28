package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class FloydWarshall {

    public final int MAX_WEIGHT = 100;
    //public static int path[][];

    int[][] getDistance(Graph graph) {
        int matrix[][] = new int[graph.adjacency.length][graph.adjacency.length];
        int path[][] = new int[graph.adjacency.length][graph.adjacency.length];
        int i, j, k;

        for (i = 0; i < graph.adjacency.length; i++) {
            for (j = 0; j < graph.adjacency.length; j++) {
                matrix[i][j] = graph.adjacency[i][j];
                path[i][j] = j;
            }
        }

        // Adding vertices individually
        for (k = 0; k < graph.adjacency.length; k++) {
            for (i = 0; i < graph.adjacency.length; i++) {
                for (j = 0; j < graph.adjacency.length; j++) {

                    /*if (matrix[i][k] == 101 ||
                    matrix[k][j] == 101)
                    continue; */

                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) { 
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = path[i][k];
                    }
                    
                    //matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        printMatrix(matrix, path);

        return matrix;
    }
    static Vector<Integer> constructPath(int u,
                                    int v, int path[][])
{
 
    // If there's no path between
    // node u and v, simply return
    // an empty array
    if (path[u][v] == 101)
        return null;
 
    // Storing the path in a vector
    Vector<Integer> shortestpath = new Vector<Integer>();
    shortestpath.add(u);
     
    while (u != v)
    {
        u = path[u][v];
        shortestpath.add(u);
    }
    int n = shortestpath.size();
    for(int i = 0; i < n - 1; i++)
    System.out.print(shortestpath.get(i) + " -> ");
    System.out.print(shortestpath.get(n - 1) + "\n");
    return shortestpath;
}

    private void findPath(int[][] matrix, int[][] path, int start) {
        int start_save = start;
        System.out.println("start: " + start);
        for (int i = 0; i < path.length; i++) {
            start = start_save;
            //System.out.println(path[5][0]);
            if (matrix[start][i] == 101) {
                System.out.println("No path from " + start_save + " to " + i);
                continue;
            }
            if (start == i) {
                System.out.println(start);
                continue;
            }

            
            LinkedList<Integer> shortestPath = new LinkedList<Integer>();
            //shortestPath.add(start);
                
            while (start != i)
            {
                //System.out.println(start);
                //System.out.println(i+"\n");
                start = path[start][i];
                shortestPath.add(start);
            }
            //System.out.println(shortestPath);
            System.out.print(start_save + " -> " + shortestPath.remove());
            while (!shortestPath.isEmpty())
                System.out.print(" -> "+ shortestPath.remove());    
            System.out.println();
            //return shortestPath;
        }
    }

    private void printMatrix(int matrix[][], int path[][]) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (matrix[i][j] == MAX_WEIGHT+1)
                    System.out.print("INF ");
                else
                    System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            findPath(matrix, path, i);
            //constructPath(0, i, path);
            System.out.println();
        }
    }
        
}
