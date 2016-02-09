package org.emn.graphs.impl.undirected;

import org.emn.graphs.interfaces.IUndirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierreg on 07/01/16.
 * Graphe non orienté par liste d'adjacence
 */
public class AdjencyListUGraph implements IUndirectedGraph {

    /** Structure correspondant à la liste d'adjacence du graphe */
    private List<Integer> node, succ;

    public AdjencyListUGraph(int[][] adjencyMatrix){
        IUndirectedGraph adjencyGraph = new AdjencyMatrixUGraph(adjencyMatrix);

        this.node = new ArrayList<>();
        this.succ = new ArrayList<>();

        int newIndex = 0;
        for (int i = 0; i < adjencyMatrix.length; i++) {
            List<Integer> listeVoisins = adjencyGraph.getNeighbors(i);
            node.add(newIndex);
            succ.addAll(listeVoisins);
            newIndex += listeVoisins.size();
        }
    }

    @Override
    public void removeEdge(int x, int y) {
        int indexToRemove = node.get(x) + getNeighbors(x).indexOf(y);
        succ.remove(indexToRemove);
        for (int i = x+1; i < node.size(); i++) {
            node.set(i,node.get(i)-1);
        }
        removeEdge(y,x);
    }

    @Override
    public void addEdge(int x, int y) {
        succ.add(node.get(x), y);
        for (int i = x+1; i < node.size(); i++) {
            node.set(i, node.get(i)+1);
        }
        addEdge(y,x);
    }

    @Override
    public List<Integer> getNeighbors(int x) {
        int indexBegin = node.get(x);
        int indexEnd = (node.size() > x+1) ? node.get(x+1) : succ.size();
        return succ.subList(indexBegin, indexEnd);
    }

    @Override
    public int getOrder() {
        return node.size();
    }

    @Override
    public int addNode() {
        node.add(succ.size());
        return getOrder()-1;
    }
}
