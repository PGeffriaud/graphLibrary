package org.emn.graphs.interfaces;

import java.util.List;

/**
 * Created by pierreg on 30/01/16.
 * Interface for undirected graphs
 */
public interface IUndirectedGraph extends IGraph{

    /**
     * Check the presence of an edge between two nodes.
     *
     * @param x the source node
     * @param y the destination node
     * @return true if there is an edge between x and y
     */
    default boolean isEdge(int x, int y) {
        return getNeighbors(x).contains(y);
    }

    /**
     * Remove the edge between two nodes if exist.
     * @param x the source node
     * @param y the destination node
     */
    void removeEdge(int x, int y);

    /**
     * Add an edge between two nodes, if not already present.
     * The two nodes must be distincts.
     * @param x the source node
     * @param y the destination node
     */
    void addEdge(int x, int y);

    /**
     * Get the neighboors of a node
     * @param x the node
     * @return a new int array representing the neighbors
     */
    List<Integer> getNeighbors(int x);

    @Override
    default int[][] getGraph() {
        int order = getOrder();
        int[][] adjencyMatrix = new int[order][order];
        for (int i = 0; i < order; i++) {
            List<Integer> succ = getNeighbors(i);
            for (Integer s  : succ) {
                adjencyMatrix[i][s] = 1;
            }
        }
        return adjencyMatrix;
    }

    @Override
    default boolean isDirected(){
        return false;
    }
}
