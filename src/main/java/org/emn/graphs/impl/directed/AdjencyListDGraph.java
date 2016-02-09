package org.emn.graphs.impl.directed;

import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierreg on 13/01/16.
 * Directed graph with adjency list representation
 */
public class AdjencyListDGraph implements IDirectedGraph {

    private List<Integer> node, succ;

    public AdjencyListDGraph(int[][] adjencyMatrix){
        IDirectedGraph adjencyGraph = new AdjencyMatrixDGraph(adjencyMatrix);

        this.node = new ArrayList<>();
        this.succ = new ArrayList<>();

        int newIndex = 0;
        for (int i = 0; i < adjencyMatrix.length; i++) {
            List<Integer> listeVoisins = adjencyGraph.getSuccessors(i);
            node.add(newIndex);
            succ.addAll(listeVoisins);
            newIndex += listeVoisins.size();
        }
    }

    @Override
    public void removeArc(int from, int to) {
        int indexToRemove = node.get(from) + getSuccessors(from).indexOf(to);
        succ.remove(indexToRemove);
        for (int i = from+1; i < node.size(); i++) {
            node.set(i,node.get(i)-1);
        }
    }

    @Override
    public void addArc(int from, int to) {
        succ.add(node.get(from), to);
        for (int i = from+1; i < node.size(); i++) {
            node.set(i, node.get(i)+1);
        }
    }

    @Override
    public List<Integer> getSuccessors(int x) {
        int indexBegin = node.get(x);
        int indexEnd = (node.size() > x+1) ? node.get(x+1) : succ.size();
        return succ.subList(indexBegin, indexEnd);
    }

    @Override
    public List<Integer> getPredecessors(int x) {
        List<Integer> listPredecessors = new ArrayList<>();
        for (int i = 0; i < getOrder(); i++) {
            if(i != x && getSuccessors(i).contains(x)){
                listPredecessors.add(i);
            }
        }
        return listPredecessors;
    }

    @Override
    public IDirectedGraph inverse() {
        return new AdjencyListDGraph(GraphUtils.getTranspose(getGraph()));
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
