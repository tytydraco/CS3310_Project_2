package com.company;

import java.util.ArrayList;
import java.rmi.MarshalException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Tests {
    public final int MAX_WEIGHT = 100;

    private static void panic(String message) {
        System.out.println(message);
        System.exit(1);
    }

    ArrayList<long[]> times = new ArrayList<>();

    /**
     * For Dijkstras we just compare costPrev tables
     * For Floyd we compare matrices
     */
    public void assertSanityChecks() {
        Graph testGraph = new Graph(
            new int[][] {
                {0, MAX_WEIGHT+1, 11, 70},
                {MAX_WEIGHT+1, 0, MAX_WEIGHT+1, 45},
                {MAX_WEIGHT+1, 41, 0, MAX_WEIGHT+1},
                {MAX_WEIGHT+1, 86, MAX_WEIGHT+1, 0},
            }
        );

        int[][] testGraphCostPrev0 = {
            {0, 52, 11, 70},
            {0, 2, 0, 0}
        };

        int[][] testGraphCostPrev1 = {
            {MAX_WEIGHT+1, 0, MAX_WEIGHT+1, 45},
            {1, 1, 1, 1}
        };

        int[][] testGraphCostPrev2 = {
            {MAX_WEIGHT+1, 41, 0, 86},
            {2, 2, 2, 1}
        };

        int[][] testGraphCostPrev3 = {
            {MAX_WEIGHT+1, 86, MAX_WEIGHT+1, 0},
            {3, 3, 3, 3}
        };

        Dijkstras dijkstras = new Dijkstras(testGraph);

        dijkstras.findCheapestPathsFrom(0);
        if (!Arrays.deepEquals(dijkstras.costPrevTable, testGraphCostPrev0)) panic("Sanity failed!");

        dijkstras.findCheapestPathsFrom(1);
        if (!Arrays.deepEquals(dijkstras.costPrevTable, testGraphCostPrev1)) panic("Sanity failed!");

        dijkstras.findCheapestPathsFrom(2);
        if (!Arrays.deepEquals(dijkstras.costPrevTable, testGraphCostPrev2)) panic("Sanity failed!");

        dijkstras.findCheapestPathsFrom(3);
        if (!Arrays.deepEquals(dijkstras.costPrevTable, testGraphCostPrev3)) panic("Sanity failed!");

        int[][] floydMatrix = {
            {0, 52, 11, 70},
            {MAX_WEIGHT+1, 0, MAX_WEIGHT+1, 45},
            {MAX_WEIGHT+1, 41, 0, 86},
            {MAX_WEIGHT+1, 86, MAX_WEIGHT+1, 0},
        };

        FloydWarshall floyd = new FloydWarshall();
        int[][] matrix = floyd.getDistance(testGraph);

        if (!Arrays.deepEquals(matrix, floydMatrix)) panic("Sanity failed!");

        System.out.println("SANITY CHECKS PASSED");
    }

    private Graph generateGraph(int nodes) {
        Random random = new Random(System.currentTimeMillis());
        int[][] adjacency = new int[nodes][nodes];

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (i == j) {
                    adjacency[i][j] = 0;
                    continue;
                }

                boolean randomConnected = random.nextBoolean();
                if (!randomConnected) {
                    adjacency[i][j] = MAX_WEIGHT+1;
                    continue;
                }

                int randomWeight = random.nextInt(MAX_WEIGHT);
                adjacency[i][j] = randomWeight;
            }
        }
        int randomNode = random.nextInt(nodes);
        for (int i = 0; i < nodes; i++) {   //Ensures graph is all connected
            if (i == randomNode)
                continue;
            if (adjacency[randomNode][i] < MAX_WEIGHT+1)
                continue;
            int randomWeight = random.nextInt(MAX_WEIGHT);
            adjacency[randomNode][i] = randomWeight;  
        }

        //if (isConnected(adjacency, nodes, MAX_WEIGHT+1))
        return new Graph(adjacency);
        //else {
          //  System.out.println("NOT CONNECTED");
            //return generateGraph(nodes);
        //}
    }

    private static boolean isConnected(int[][] adjacency_matrix, int number_of_nodes, int inf) {
        HashSet<Integer> connectedNodes = new HashSet<Integer>();
        for (int i = 0; i < number_of_nodes; i++) {
            for (int j = 0; j < number_of_nodes; j++) {
                if (adjacency_matrix[i][j] != inf) {
                    connectedNodes.add(i);
                    connectedNodes.add(j);
                }
            }
        }
        return connectedNodes.size() == number_of_nodes;
    }

    private long timeIt(Runnable r) {
        long s = System.currentTimeMillis();
        r.run();
        long e = System.currentTimeMillis();
        return e - s;
    }

    public void runTests(int runs, int nodeTwoPow) {
        for (int j = 1; j < nodeTwoPow + 1; j++) {
            int nodes = (int) Math.round(Math.pow(2, j));

            long timesDijkstra = 0;
            long timesFloyd = 0;

            for (int i = 1; i < runs + 1; i++) {
                System.out.println();
                System.out.println("RUN #" + i + " WITH " + nodes + " NODES");

                Graph g = generateGraph(nodes);
                System.out.println(Arrays.deepToString(g.adjacency));

                Dijkstras dijkstras = new Dijkstras(g);
                FloydWarshall floyd = new FloydWarshall();

                timesDijkstra += timeIt(dijkstras::findAllCheapestPaths);
                timesFloyd += timeIt(() -> floyd.getDistance(g));
            }

            timesDijkstra /= runs;
            timesFloyd /= runs;

            times.add(new long[] {timesDijkstra, timesFloyd});

            System.out.println("DIJKSTRA AVG TIME FOR " + nodes + " NODES: " + timesDijkstra + " MS");
            System.out.println("FLOYD AVG TIME FOR " + nodes + " NODES: " + timesFloyd + " MS");
        }

        System.out.println(Arrays.deepToString(times.toArray()));
    }
}
