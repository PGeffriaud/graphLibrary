package org.emn.graphs.impl.directed;

import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierreg on 12/01/16.
 * Directed graph with adjency matrix representation
 */
public class AdjencyMatrixDGraph implements IDirectedGraph {

    protected int[][] adjencyMatrix;

    public AdjencyMatrixDGraph(int[][] matrix){
        this.adjencyMatrix = matrix;
    }

    @Override
    public boolean isArc(int from, int to) {
        return adjencyMatrix[from][to] == 1;
    }

    @Override
    public void removeArc(int from, int to) {
        adjencyMatrix[from][to] = 0;
    }

    @Override
    public void addArc(int from, int to) {
        adjencyMatrix[from][to] = 1;
    }

    @Override
    public List<Integer> getSuccessors(int x) {
        List<Integer> listSucc = new ArrayList<>();
        for (int i = 0; i < getOrder(); i++) {
            if(isArc(x,i)) listSucc.add(i);
        }
        return listSucc;
    }

    @Override
    public List<Integer> getPredecessors(int x) {
        List<Integer> listPred = new ArrayList<>();
        for (int i = 0; i < getOrder(); i++) {
            if(isArc(i,x)) listPred.add(i);
        }
        return listPred;
    }

    @Override
    public IDirectedGraph inverse() {
        return new AdjencyMatrixDGraph(GraphUtils.getTranspose(adjencyMatrix));
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
