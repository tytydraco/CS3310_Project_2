package com.company;

import java.util.Arrays;
import java.util.HashSet;

public class Dijkstras {
    Graph graph;

    private int[][] costPrevTable;
    private HashSet<Integer> visited = new HashSet<>();

    public Dijkstras(Graph graph) {
        this.graph = graph;
        costPrevTable = new int[2][graph.adjacency.length];
    }

    /**
     * @return -1 if node is unreachable, 0 if node is itself
     */
    private int getDistance(int from, int to) {
        return graph.adjacency[from][to];
    }

    /**
     * Initialize from scratch
     * @param start The node to use as the start node
     */
    private void initialize(int start) {
        visited.clear();
        visited.add(start);

        /* Set all prev nodes to start */
        for (int i = 0; i < graph.adjacency.length; i++) {
            int cost = getDistance(start, i);
            setCostOf(i, cost);
            setPrevOf(i, start);
        }
    }

    private void updateFor(int i) {
        /* Cost of reaching node i in the first place */
        int selfCost = getCostOf(i);

        /* Check if node i has any cheaper distances to node j */
        for (int j = 0; j < graph.adjacency.length; j++) {
            int cost = getDistance(i, j);
            int currentCost = getCostOf(j);

            /* Don't touch visited nodes */
            if (alreadyVisited(j))
                continue;

            /* Inaccessible node */
            if (cost == -1)
                continue;

            /* Cheaper path identified! */
            if (cost + selfCost < currentCost || currentCost == -1) {
                setCostOf(j, cost + selfCost);
                setPrevOf(j, i);
            }
        }
    }

    private int getCostOf(int i) { return costPrevTable[0][i]; }
    private int getPrevOf(int i) { return costPrevTable[1][i]; }
    private void setCostOf(int i, int val) { costPrevTable[0][i] = val; }
    private void setPrevOf(int i, int val) { costPrevTable[1][i] = val; }
    private boolean alreadyVisited(int i) { return visited.contains(i); }

    private int cheapestNonVisited() {
        int cheapestIdx = -1;
        int cheapestCost = -1;

        for (int i = 0; i < graph.adjacency.length; i++) {
            if (alreadyVisited(i))
                continue;

            int cost = getCostOf(i);

            /* Inaccessible */
            if (cost == -1)
                continue;

            /* Set new best option if cheaper OR if we haven't chosen one yet */
            if (cost < cheapestCost || cheapestIdx == -1) {
                cheapestIdx = i;
                cheapestCost = cost;
            }
        }

        return cheapestIdx;
    }

    public void findCheapestPathsFrom(int start) {
        initialize(start);

        while (true) {
            //prettyPrintTable();

            int cheapest = cheapestNonVisited();

            /* There is no other non-visited node */
            if (cheapest == -1)
                break;

            /* Add node to the list of visited nodes */
            visited.add(cheapest);

            /* Update table to see if there are any new connections we can make */
            updateFor(cheapest);

            //System.out.println(cheapest);
        }
    }

    public void findAllCheapestPaths() {
        for (int i = 0; i < graph.adjacency.length; i++) {
            System.out.println("START: " + i);
            findCheapestPathsFrom(i);
            prettyPrintTable();
            System.out.println();
        }
    }

    public void prettyPrintTable() {
        System.out.println("COST: " + Arrays.toString(costPrevTable[0]));
        System.out.println("PREV: " + Arrays.toString(costPrevTable[1]));
        System.out.println("VISI: " + Arrays.toString(visited.toArray()));
    }
}
