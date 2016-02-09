package org.emn.graphs.algorithms;

import org.emn.graphs.components.Arete;
import org.emn.graphs.interfaces.IGraph;
import org.emn.graphs.utils.GraphUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by pierreg on 03/02/16.
 * Path finding algorithms
 */
public class PathFinding {

    /**
     * Dijkstra algorithm
     * @param n order of the graph
     * @param cout weigth matrix (-1 mean edge does not exist)
     * @return valuation for each node
     */
    public static int[] dijkstra(int n, int[][] cout){
        // Initialisation
        int[] p = new int[n]; // matrice des prédécesseurs
        int[] v = new int[n]; // matrice de valuation
        Arrays.fill(v, 1, n, Integer.MAX_VALUE);
        boolean[] b = new boolean[n]; // matrice des noeud atteints

        // Recherche du sommet x non atteint de valuation minimale
        int x = 0;
        while (IntStream.range(0, b.length).anyMatch(i -> !b[i])){
            int min = Integer.MAX_VALUE;
            for (int y = 0; y < n; y++) {
                if(!b[y] && v[y] < min) {
                    x = y;
                    min = v[y];
                }
            }

            // Mise à jour des successeurs de x
            b[x] = true;
            for (int y = 0; y < n; y++) {
                if(!b[y] && cout[x][y] >= 0 && v[x]+cout[x][y] < v[y]){
                    v[y] = v[x]+cout[x][y];
                    p[y] = x;
                }
            }
        }
        return v;
    }

    /**
     * Floyd marshall algorithm
     * @param C weight matrix (-1 implies edge does not exist)
     * @return path weight for each pair of node (-1 means infinity)
     */
    public static int[][] floyd(int[][] C){
        // initialisation
        int n = C.length;
        int[][] W = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                W[i][j] = (i==j) ? 0 : C[i][j];
            }
        }

        // recherche des plus courts chemins
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(W[i][k] >= 0 && W[k][j] >= 0 &&
                            (W[i][j] < 0 || W[i][j] > W[i][k] + W[k][j])) {
                       W[i][j] = W[i][k] + W[k][j];
                    }
                }
            }
        }

        return W;
    }

    /**
     * Get a matrix indicates if a path exists for each (x,y)
     * @param C weight matrix (-1 implies edge does not exist)
     * @return boolean matrix indicates if a path exist for each (x,y)
     */
    public static boolean[][] floydExistPath(int[][] C) {
        int n = C.length;
        boolean[][] W = new boolean[n][n];
        for (int i = 0; i < W.length; i++) {
            for (int j = 0; j < W[i].length; j++) {
                W[i][j] = (i == j) || C[i][j] >= 0;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    W[i][j] = C[i][j]>=0 || (C[i][k] >=0  && C[j][k] >= 0);
                }
            }
        }
        return W;
    }

    /**
     * Find path weight for each node
     * @param s from node
     * @param graph the graph
     * @return minimum path weigth for each other node
     */
    public static int[] bellmann(int s, IGraph graph) {
        // Initialisation
        int n = graph.getOrder();
        int[] dist = new int[n];

        for (int v = 0; v < n; v++) {
            dist[v] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        // find distances
        List<Arete> listAretes = GraphUtils.getListAretes(graph);
        for (int i = 0; i < n; i++) {
            listAretes.stream()
                    .filter(arete -> dist[arete.from] < Integer.MAX_VALUE && dist[arete.from] + arete.weight < dist[arete.to])
                    .forEach(arete -> {
                            dist[arete.to] = dist[arete.from] + arete.weight;
                    });
        }

        return dist;
    }

}
