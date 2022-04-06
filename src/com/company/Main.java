package com.company;

public class Main {

    public static void main(String[] args) {
        Graph testGraph = new Graph(
                new int[][]{
                        {0, 4, -1, -1, -1},
                        {-1, 0, 3, 3, -1},
                        {2, -1, 0, -1, 2},
                        {-1, -1, -1, 0, -1},
                        {-1, 5, -1, 2, 0},
                }
        );

        Dijkstras dijkstras = new Dijkstras(testGraph, 0);
        dijkstras.findCheapestPaths();
        dijkstras.prettyPrintTable();
    }
}
