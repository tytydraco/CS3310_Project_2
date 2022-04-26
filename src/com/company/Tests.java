package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Tests {
    public final int MAX_WEIGHT = 100;
    public final int RUNS = 100;

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
        if (isConnected(adjacency, 4, MAX_WEIGHT+1))
            return new Graph(adjacency);
        else {
            System.out.println("NOT CONNECTED");
            return generateGraph(nodes);
        }
    }

    private Graph generateGraph_Floyd(int nodes) {
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

                boolean isNeg = random.nextBoolean();
                if (isNeg)
                    randomWeight *= -1;

                adjacency[i][j] = randomWeight;
            }
        }

        // TODO: make sure generated graph is valid
        if (isConnected(adjacency, 4, MAX_WEIGHT+1))
            return new Graph(adjacency);
        else {
            System.out.println("NOT CONNECTED");
            return generateGraph(nodes);
        }
    }

    private static boolean isConnected(int adjacency_matrix[][], int number_of_nodes, int inf) {
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

    public void runTests() {
        for (int i = 0; i < RUNS; i++) {
            Graph g = generateGraph(4);
            //System.out.println(isConnected(g, 4));
            System.out.println(Arrays.deepToString(g.adjacency));
            Dijkstras dijkstras = new Dijkstras(g);
            dijkstras.findAllCheapestPaths();
        }
    }

    public void runTests_Floyd() {
        for (int i = 0; i < RUNS; i++) {
            Graph g = generateGraph_Floyd(4);
            //System.out.println(isConnected(g, 4));
            System.out.println(Arrays.deepToString(g.adjacency));
            FloydWarshall floyd = new FloydWarshall();
            floyd.getDistance(g);
        }
    }
}
