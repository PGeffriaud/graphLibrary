package org.emn.graphs.interfaces;

/**
 * Created by pierreg on 30/01/16.
 * Main interface for Graphs
 */
public interface IGraph {

    /**
     * Get the order of the graph
     * @return the number of nodes
     */
    int getOrder();

    /**
     * Convert the graph to its adjacency matrix representation
     * @return the obtained matrix
     */
    int[][] getGraph();

    /**
     * Add a node to the graph
     * @return the node id
     */
    int addNode();

    /**
     * Say if the graph is directed or not
     * @return true if the graph is directed
     */
    boolean isDirected();
}
