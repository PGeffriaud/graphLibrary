package org.emn.graphs.algorithms;

import org.emn.graphs.components.Arete;
import org.emn.graphs.interfaces.IGraph;
import org.emn.graphs.utils.GraphUtils;

import java.util.*;


/**
 * Created by pierreg on 03/02/16.
 * Algorithms to transform a graph to its minimum weight representation
 */
public class MinimumWeightTree {

    /**
     * Kruskal algorithm
     * @param n graph's order
     * @param graph the graph to transform
     * @return Edges list of the weighted tree
     */
    public static List<Arete> kruskal(int n, IGraph graph)
    {
        int cout = 0;
        List<Arete> aretesT = GraphUtils.getListAretes(graph);
        Collections.sort(aretesT);
        List<Arete> couverture = new ArrayList<>();
        Set<Integer> atteint = new HashSet<>();

        while(couverture.size() < n-1){
            Arete a = aretesT.remove(0); // liste triée donc minimum en première position
            while(atteint.contains(a.from) && atteint.contains(a.to)){
                a = aretesT.remove(0);
            }
            atteint.add(a.from);
            atteint.add(a.to);
            couverture.add(a);
            cout += a.weight;
        }
        return couverture;
    }

    public static int getCoutListArete(List<Arete> liste){
        return liste.stream().mapToInt(a->a.weight).sum();
    }

    /**
     * Prim Algorithm
     * @param n graph's order
     * @param graph the graph to transform
     * @return Edges list of the weighted tree
     */
    public static List<Arete> prim(int n, IGraph graph)
    {
        // Not yet implemented
        return null;
    }

}
