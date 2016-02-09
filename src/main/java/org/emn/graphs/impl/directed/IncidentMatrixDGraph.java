package org.emn.graphs.impl.directed;

import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pierreg on 14/01/16.
 * Oriented graph with incident matrix representation
 */
public class IncidentMatrixDGraph implements IDirectedGraph {

    private List<int[]> incidentMatrix;

    public IncidentMatrixDGraph(int[][] matrix){
        incidentMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] != 0){
                    int[] col = new int[matrix.length];
                    col[i] = -1*matrix[i][j];
                    col[j] = matrix[i][j];
                    incidentMatrix.add(col);
                }
            }
        }
    }

    @Override
    public void removeArc(int from, int to) {
        List<int[]> possibleList = incidentMatrix.stream()
                .filter(ints -> ints[from] == -1 && ints[to] == 1)
                .collect(Collectors.toList());
        incidentMatrix.remove(possibleList.get(0));
    }

    @Override
    public void addArc(int from, int to) {
        int[] ints = new int[getOrder()];
        ints[from] = -1;
        ints[to] = 1;
        incidentMatrix.add(ints);
    }

    @Override
    public List<Integer> getSuccessors(int x) {
        List<Integer> listNeighbors = new ArrayList<>();
        List<int[]> incidentList = incidentMatrix.stream().filter(ints -> ints[x] == -1).collect(Collectors.toList());
        for (int[] ints : incidentList) {
            for (int i = 0; i < ints.length; i++) {
                if(i != x && ints[i] == 1) listNeighbors.add(i);
            }
        }
        return listNeighbors;
    }

    @Override
    public List<Integer> getPredecessors(int x) {
        List<Integer> listPred = new ArrayList<>();
        List<int[]> incidentList = incidentMatrix.stream().filter(ints -> ints[x] == 1).collect(Collectors.toList());
        for (int[] ints : incidentList) {
            for (int i = 0; i < ints.length; i++) {
                if(i != x && ints[i] == -1) listPred.add(i);
            }
        }
        return listPred;
    }

    @Override
    public IDirectedGraph inverse() {
        return new IncidentMatrixDGraph(GraphUtils.getTranspose(getGraph()));
    }

    @Override
    public int getOrder() {
        return incidentMatrix.get(0).length;
    }

    @Override
    public int addNode() {
        for (int i = 0; i < incidentMatrix.size(); i++) {
            int[] ints = incidentMatrix.get(i);
            List<Integer> newListe = new ArrayList<>();
            for (int x : ints) {
                newListe.add(x);
            }
            newListe.add(0);
            incidentMatrix.set(i, newListe.stream().mapToInt(x -> x).toArray());
        }
        return getOrder()-1;
    }
}
