package org.emn.graphs.algorithms;

import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.interfaces.IGraph;
import org.emn.graphs.interfaces.IUndirectedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by pierreg on 02/02/16.
 * Algorithm class to provide exploration algorithms
 */
public class GraphExplorers {

    /**
     * Exploration du graphe en profondeur
     * @param graph le graphe
     * @return liste des sommets dans l'ordre atteints
     */
    public static List<Integer> parcoursProfondeur(IGraph graph)
    {
        List<Integer> atteint = new ArrayList<>();
        for (int s = 0; s < graph.getOrder(); s++) {
            if(!atteint.contains(s)){
                explorerSommetProfondeur(graph, atteint, s);
            }
        }
        return atteint;
    }

    private static void explorerSommetProfondeur(IGraph graph, List<Integer> atteint, int s) {
        if(graph.isDirected()){
            explorerDirectedSommetProfondeur((IDirectedGraph) graph, s, atteint);
        } else {
            explorerUndirectedSommetProfondeur((IUndirectedGraph) graph, s, atteint);
        }
    }

    private static void explorerDirectedSommetProfondeur(IDirectedGraph graph, int s, List<Integer> atteint) {
        atteint.add(s);
        for (Integer t : graph.getSuccessors(s)) {
            if(! atteint.contains(t)){
                explorerDirectedSommetProfondeur(graph, t, atteint);
            }
        }
    }

    private static void explorerUndirectedSommetProfondeur(IUndirectedGraph graph, int s, List<Integer> atteint) {
        atteint.add(s);
        for (Integer t : graph.getNeighbors(s)) {
            if(! atteint.contains(t)){
                explorerUndirectedSommetProfondeur(graph, t, atteint);
            }
        }
    }



    /**
     * Exploration du graphe en largeur
     * @param graph le graphe
     * @param s identifiant su sommet de départ
     * @return liste des sommets dans l'ordre de parcours
     */
    public static List<Integer> parcoursLargeur(IGraph graph, int s) {
        List<Integer> atteint = new ArrayList<>();
        Queue<Integer> file = new ArrayDeque<>();
        file.add(s);
        atteint.add(s);
        while (!file.isEmpty()) {
            s = file.poll();

            // Récupération de la liste des voisins selon le type de graphe
            List<Integer> listNeighbors = (graph.isDirected()) ?
                    ((IDirectedGraph) graph).getSuccessors(s)
                    : ((IUndirectedGraph) graph).getNeighbors(s);

            listNeighbors.stream().filter(t -> !atteint.contains(t)).forEach(t -> {
                file.add(t);
                atteint.add(t);
            });
        }
        return atteint;
    }

    /**
     * Récupération des composantes connexes
     * @param graph le graphes
     * @return liste des composantes que composent le graphe
     */
    public static List<List<Integer>> getComposantesConnexe(IGraph graph){
        List<List<Integer>> listeCC = new ArrayList<>();

        List<Integer> atteintGlobal = new ArrayList<>();
        for (int i = 0; i < graph.getOrder(); i++) {
            if(! atteintGlobal.contains(i)){
                List<Integer> atteint = new ArrayList<>();

                explorerSommetProfondeur(graph, atteint, i);
                listeCC.add(atteint);
                atteintGlobal.addAll(atteint);
            }
        }
        return listeCC;
    }
}
