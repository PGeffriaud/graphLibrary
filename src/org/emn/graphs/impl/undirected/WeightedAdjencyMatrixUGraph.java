package org.emn.graphs.impl.undirected;

/**
 * Created by pierreg on 03/02/16.
 * Adjency undirected matrix representation with weighted edges
 * Negative weight implies no edge
 */
public class WeightedAdjencyMatrixUGraph extends  AdjencyMatrixUGraph {

    public WeightedAdjencyMatrixUGraph(int[][] matrix) {
        super(matrix);
    }

    @Override
    public boolean isEdge(int x, int y) {
        return adjencyMatrix[x][y] >=0 && adjencyMatrix[y][x] >= 0;
    }

    @Override
    public void removeEdge(int x, int y) {
        adjencyMatrix[x][y] = -1;
        adjencyMatrix[y][x] = -1;
    }

    /**
     * Add a weighted edge
     * @param x from node
     * @param y to node
     * @param weight positive or null edge weight
     */
    public void addEdge(int x, int y, int weight) {
        adjencyMatrix[x][y] = weight;
        adjencyMatrix[y][x] = weight;
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
