package org.emn.graphs.impl.undirected;

import org.emn.graphs.interfaces.IUndirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pierreg on 12/01/16.
 */
public class IncidentMatrixUGraph implements IUndirectedGraph {

    private List<int[]> incidentMatrix;

    public IncidentMatrixUGraph(int[][] adjencyMatrix){
        int order = getOrder();
        incidentMatrix = new ArrayList<>();
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < i; j++) {
                if(adjencyMatrix[i][j] != 0){
                    int[] col = new int[order];
                    col[i] = 1;
                    col[j] = 1;
                    incidentMatrix.add(col);
                }
            }
        }
    }

    @Override
    public void removeEdge(int x, int y) {
        List<int[]> possibleList = incidentMatrix.stream().filter(ints -> ints[x] == 1 && ints[y] == 1).collect(Collectors.toList());
        incidentMatrix.remove(possibleList.get(0));
    }

    @Override
    public void addEdge(int x, int y) {
        int[] ints = new int[getOrder()];
        ints[x] = 1;
        ints[y] = 1;
        incidentMatrix.add(ints);
    }

    @Override
    public List<Integer> getNeighbors(int x) {
        List<Integer> listNeighbors = new ArrayList<>();
        List<int[]> incidentList = incidentMatrix.stream().filter(ints -> ints[x] == 1).collect(Collectors.toList());
        for (int[] ints : incidentList) {
            for (int i = 0; i < ints.length; i++) {
                if(i != x && ints[i] == 1) listNeighbors.add(i);
            }
        }
        return listNeighbors;
    }

    @Override
    public int getOrder() {
        return incidentMatrix.size();
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
