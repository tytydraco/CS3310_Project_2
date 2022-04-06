package com.company;

import java.util.Arrays;
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
                    adjacency[i][j] = -1;
                    continue;
                }

                int randomWeight = random.nextInt(MAX_WEIGHT);
                adjacency[i][j] = randomWeight;
            }
        }

        // TODO: make sure generated graph is valid

        return new Graph(adjacency);
    }

    public void runTests() {
        for (int i = 0; i < RUNS; i++) {
            Graph g = generateGraph(4);
            System.out.println(Arrays.deepToString(g.adjacency));
            Dijkstras dijkstras = new Dijkstras(g);
            dijkstras.findAllCheapestPaths();
        }
    }
}
