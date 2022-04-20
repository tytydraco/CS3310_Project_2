package com.company;

public class FloydWarshall {

    public final int MAX_WEIGHT = 100;

    void getDistance(Graph graph) {
        int matrix[][] = new int[graph.adjacency.length][graph.adjacency.length];
        int i, j, k;

        for (i = 0; i < graph.adjacency.length; i++)
            for (j = 0; j < graph.adjacency.length; j++)
                matrix[i][j] = graph.adjacency[i][j];

        // Adding vertices individually
        for (k = 0; k < graph.adjacency.length; k++) {
            for (i = 0; i < graph.adjacency.length; i++) {
                for (j = 0; j < graph.adjacency.length; j++) {

                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) 
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    
                    //matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        printMatrix(matrix);
    }

    private void printMatrix(int matrix[][]) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (matrix[i][j] == MAX_WEIGHT+1)
                    System.out.print("INF ");
                else
                    System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
            }
        }
}
