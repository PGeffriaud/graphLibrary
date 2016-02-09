package org.emn.graphs.utils;

import org.emn.graphs.exception.GraphFactoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pierreg on 30/01/16.
 * Class to create random and graph examples
 */
public class GraphFactory {

    public static int[][] createRandomMatrix(int order, int nbEdge, boolean isDirected) throws GraphFactoryException {
        int nbMaxEdges = (order*(order-1)); // nombre max d'arête d'un graphe orienté
        if(order > 0 && nbMaxEdges >= nbEdge){
            int[][] matrix = new int[order][order];

            // Liste de toutes les arrêtes possibles
            List<int[]> listAllEdges = new ArrayList<>();
            for(int i=0; i<order; i++){
                for(int j=0; j<order; j++){
                    if(i != j) {
                        int[] edge = {i,j};
                        listAllEdges.add(edge);
                    }
                }
            }

            // Récupération aléatoire de nbEdge arêtes
            Random random = new Random();
            int actualNbEdge = 0;
            while(actualNbEdge < nbEdge){
                int x = random.nextInt(listAllEdges.size());
                int[] edge = listAllEdges.remove(x);
                matrix[edge[0]][edge[1]] = 1;
                if(!isDirected)  matrix[edge[1]][edge[0]] = 1;
                actualNbEdge++;
            }

            return matrix;
        }
        else throw new GraphFactoryException("Paramètres de construction incorrects");
    }

    public static int[][] createExempleUndirectedMatrix(){
        return new int[][] {{0,1,1,0,1,0,0},
                            {1,0,0,1,0,1,0},
                            {1,0,0,0,0,0,1},
                            {0,1,0,0,0,0,0},
                            {1,0,0,0,0,1,0},
                            {0,1,0,0,1,0,0},
                            {0,0,1,0,0,0,0}};
    }

    public static int[][] createExempleDirectedMatrix(){
        return new int[][] {{0,1,1,0,1,0,0},
                            {0,0,0,1,0,1,0},
                            {0,0,0,0,0,0,1},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,1,0},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0}};
    }

    public static int[][] createExempleWeightedDirectedMatrix(){
        return new int[][] {{-1,1,-1,5,-1},
                            {-1,-1,3,-1,-1},
                            {-1,-1,-1,0,2},
                            {-1,1,-1,-1,1},
                            {-1,-1,-1,-1,-1}};
    }

    public static int[][] createExempleWeightedUndirectedMatrix(){
        return new int[][] {{-1,1,-1,5,-1},
                            {1,-1,3,1,-1},
                            {-1,3,-1,0,2},
                            {5,1,0,-1,1},
                            {-1,-1,2,1,-1}};
    }

}
