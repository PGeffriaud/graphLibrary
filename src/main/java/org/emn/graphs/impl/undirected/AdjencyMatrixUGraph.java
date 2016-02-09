package org.emn.graphs.impl.undirected;

import org.emn.graphs.interfaces.IUndirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierreg on 07/01/16.
 * Graphe non orienté par matrice d'adjacence
 */
public class AdjencyMatrixUGraph implements IUndirectedGraph {

    protected int[][] adjencyMatrix;

    public AdjencyMatrixUGraph(int[][] matrix){
        this.adjencyMatrix = matrix;
    }

    @Override
    public boolean isEdge(int x, int y) {
        return adjencyMatrix[x][y] == 1 && adjencyMatrix[y][x] != 1;
    }

    @Override
    public void removeEdge(int x, int y) {
        adjencyMatrix[x][y] = 0;
        adjencyMatrix[y][x] = 0;
    }

    @Override
    public void addEdge(int x, int y) {
        adjencyMatrix[x][y] = 1;
        adjencyMatrix[y][x] = 1;
    }

    @Override
    public List<Integer> getNeighbors(int x) {
        List<Integer> listNeighbors = new ArrayList<>();
        for (int i = 0; i < getOrder(); i++) {
            if(isEdge(x,i)) listNeighbors.add(i);
        }
        return listNeighbors;
    }

    @Override
    public int getOrder() {
        return adjencyMatrix.length;
    }

    @Override
    public int[][] getGraph() {
        return adjencyMatrix;
    }

    @Override
    public int addNode() {
        int order = getOrder();
        int[][] newMatrix = new int[order+1][order+1];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                newMatrix[i][j] = adjencyMatrix[i][j];
            }
        }
        adjencyMatrix = newMatrix;
        return order;
    }
}
