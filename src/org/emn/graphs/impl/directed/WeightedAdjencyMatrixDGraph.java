package org.emn.graphs.impl.directed;

/**
 * Created by pierreg on 03/02/16.
 * Adjency directed matrix representation with weighted edges
 * Negative weight implies no edge
 */
public class WeightedAdjencyMatrixDGraph extends AdjencyMatrixDGraph {

    public WeightedAdjencyMatrixDGraph(int[][] matrix) {
        super(matrix);
    }

    @Override
    public boolean isArc(int from, int to) {
        return adjencyMatrix[from][to] >= 0;
    }

    /**
     * Add a weighted edge
     * @param from from node
     * @param to to node
     * @param weight positive or null edge weight
     */
    public void addArc(int from, int to, int weight) {
        if(weight >= 0){
            adjencyMatrix[from][to] = weight;
        }
    }

    @Override
    public void removeArc(int from, int to) {
        adjencyMatrix[from][to] = -1;
    }

    @Override
    public int addNode() {
        int newIndex = super.addNode();
        for (int i = 0; i < adjencyMatrix[newIndex].length; i++) {
            adjencyMatrix[newIndex][i] = -1;
            adjencyMatrix[i][newIndex] = -1;
        }
        return newIndex;
    }
}
