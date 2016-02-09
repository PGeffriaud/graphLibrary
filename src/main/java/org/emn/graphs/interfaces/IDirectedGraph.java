package org.emn.graphs.interfaces;

import java.util.List;

/**
 * Created by pierreg on 30/01/16.
 * Interface for directed graphs
 */
 public interface IDirectedGraph extends IGraph{

    /**
     * Check the presence of an arc between two nodes.
     * @param from the source node
     * @param to the destination node
     * @return true if there is an arc from 'from' to 'to'
     */
     default boolean isArc(int from, int to){
         return getSuccessors(from).contains(to);
     }

    /**
     * Remove the arc between two nodes if exist.
     * @param from the source node
     * @param to the destination node
     */
     void removeArc(int from, int to);

    /**
     * Add an arc between two nodes, if not already present.
     * The two nodes must be distincts.
     * @param from the source node
     * @param to the destination node
     */
     void addArc(int from, int to);

    /**
     * Get the successors of a node
     * @param x the node
     * @return a new int array representing the successors
     */
     List<Integer> getSuccessors(int x);

    /**
     * Get the predecessors of a node
     * @param x the node
     * @return a new int array representing the predecessors
     */
     List<Integer> getPredecessors(int x);

    /**
     * Compute the inverse graph
     * @return the inverse graph
     */
     IDirectedGraph inverse();

    @Override
    default int[][] getGraph() {
        int order = getOrder();
        int[][] adjencyMatrix = new int[order][order];
        for (int i = 0; i < order; i++) {
            List<Integer> succ = getSuccessors(i);
            for (Integer s  : succ) {
                adjencyMatrix[i][s] = 1;
            }
        }
        return adjencyMatrix;
    }

    @Override
    default  boolean isDirected(){
        return true;
    }
}
