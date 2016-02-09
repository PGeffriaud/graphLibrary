package org.emn.graphs.utils;

import org.emn.graphs.components.Arete;
import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.interfaces.IGraph;
import org.emn.graphs.interfaces.IUndirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierreg on 15/01/16.
 */
public class GraphUtils {

    /**
     * Naive method to get a matrix transpose
     * @param matrix the matrix
     * @return the matrix transpose
     */
    public static int[][] getTranspose(int[][] matrix) {
        int size = matrix.length;
        int[][] newMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static List<Arete> getListAretes(IGraph graph) {
        List<Arete> listeArete = new ArrayList<>();
        int[][] matrix = graph.getGraph();

        for (int s = 0; s < graph.getOrder(); s++) {
            List<Integer> neighbors = (graph.isDirected()) ?
                    ((IDirectedGraph)graph).getSuccessors(s) :
                    ((IUndirectedGraph)graph).getNeighbors(s);

            for (Integer n : neighbors) {
                listeArete.add(new Arete(s,n, matrix[s][n]));
            }
        }
        return listeArete;
    }
}
