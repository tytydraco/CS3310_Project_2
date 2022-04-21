package com.company;

public class Main {

    public static void main(String[] args) {
        Graph testGraph = new Graph(
                new int[][]{
                        {0, 6, -1, 1, -1, -1, -1},
                        {-1, 0, -1, -1, 1, -1, -1},
                        {2, -1, 0, 3, -1, -1, -1},
                        {-1, -1, -1, 0, 4, -1, -1},
                        {-1, -1, -1, -1, 0, -1, -1},
                        {-1, -1, -1, -1, 2, 0, -1},
                        {3, -1, 2, -1, -1, 6, 0}
                }
        );

        Graph testGraph2 = new Graph(
            new int[][]{ 
                { 0, 3, 999, 5 }, { 2, 0, 999, 4 }, { 999, 1, 0, 999 }, { 999, 999, 2, 0 } 
            }
        );
        
        Graph testGraph3 = new Graph(
            new int[][]{ 
                {0, -18, -95, -84}, {99, 0, -76, -85}, {101, 101, 0, 101}, {-54, 101, 39, 0}
            }
        );

        Graph testGraph4 = new Graph(
                new int[][]{
                        {0, 6, 101, 1, 101, 101, 101},
                        {101, 0, 101, 101, 1, 101, 101},
                        {2, 101, 0, 3, 101, 101, 101},
                        {101, 101, 101, 0, 4, 101, 101},
                        {101, 101, 101, 101, 0, 101, 101},
                        {101, 101, 101, 101, 2, 0, 101},
                        {3, 101, 2, 101, 101, 6, 0}
                }
        );

        Graph testGraph5 = new Graph(
            new int[][] { { 0, 3, 101, 7 },
            { 8, 0, 2, 101 },
            { 5, 101, 0, 1 },
            { 2, 101, 101, 0 } }
        );
        /*
        Dijkstras dijkstras = new Dijkstras(testGraph);
        dijkstras.findCheapestPathsFrom(0);
        dijkstras.findAllCheapestPaths();
        //dijkstras.prettyPrintTable();
        FloydWarshall floyd = new FloydWarshall();
        floyd.getDistance(testGraph2);
        */

        //Tests tests = new Tests();
       // tests.runTests_Floyd(); 
        Dijkstras dijkstras = new Dijkstras(testGraph);
        dijkstras.findAllCheapestPaths();
        FloydWarshall floyd = new FloydWarshall();
        floyd.getDistance(testGraph5);

        //Tests tests = new Tests();
        //tests.runTests(); 
    }
}
