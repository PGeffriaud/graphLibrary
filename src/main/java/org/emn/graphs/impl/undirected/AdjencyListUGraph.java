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
    int[] node;
    List<Integer> succ;

    public AdjencyListUGraph(int[][] adjencyMatrix){
        node = new int[adjencyMatrix.length +1];
        succ = new ArrayList<>();
        for(int i = 0; i < adjencyMatrix.length; i++){
            node[i] = succ.size();
            for(int j = 0; j < adjencyMatrix.length; j++){
                if(adjencyMatrix[i][j] == 1){
                    succ.add(j);
                }
            }
        }
        node[adjencyMatrix.length] = succ.size();
    }

    @Override
    public void removeEdge(int x, int y) {
        if(isEdge(x, y)){
            removeDirectedArc(x, y);
            removeDirectedArc(y, x);
        }
    }

    private void removeDirectedArc(int x, int y){
        boolean hasBeenRemoved = false;
        for(int i = node[x]; i < node[x+1]; i++){
            if(!hasBeenRemoved && succ.get(i) == y){
                succ.remove(i);
                hasBeenRemoved = true;
            }
        }
        if(hasBeenRemoved){
            for(int i = x+1; i < node.length; i++){
                node[i]--;
            }
        }
    }

    @Override
    public void addEdge(int x, int y) {
        if(x != y){
            addDirectedArc(x, y);
            addDirectedArc(y, x);
        }
    }

    private void addDirectedArc(int x, int y){
        if(!isEdge(x, y)){
            succ.add(node[x+1], y);
            for(int i = x+1; i < node.length; i++){
                node[i]++;
            }
        }
    }

    @Override
    public List<Integer> getNeighbors(int x) {
        int nb = node[x+1]-node[x];
        List<Integer> neighbors = new ArrayList<>();
        for(int i = 0; i < nb; i++){
            neighbors.add(succ.get(node[x]+i));
        }
        return neighbors;
    }

    @Override
    public int getOrder() {
        return node.length;
    }

    @Override
    public int addNode() {
        List<Integer> newNodes = new ArrayList<>();
        for (int aNode : node) {
            newNodes.add(aNode);
        }

        newNodes.add(succ.size());
        node = newNodes.stream().mapToInt(i->i).toArray();
        return node.length-2;
    }

}
