package org.emn.graphs.appli;

import org.emn.graphs.algorithms.GraphExplorers;
import org.emn.graphs.algorithms.PathFinding;
import org.emn.graphs.impl.directed.AdjencyMatrixDGraph;
import org.emn.graphs.impl.directed.WeightedAdjencyMatrixDGraph;
import org.emn.graphs.impl.undirected.AdjencyMatrixUGraph;
import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.interfaces.IUndirectedGraph;
import org.emn.graphs.utils.GraphFactory;

import java.util.Arrays;

/**
 * Created by pierreg on 02/02/16.
 * Main application in order to test graph library
 */
public class Main {

    public static void main(String[] args) {
        int[][] undirectedMatrix = GraphFactory.createExempleUndirectedMatrix();
        IUndirectedGraph graph = new AdjencyMatrixUGraph(undirectedMatrix);
        System.out.println(GraphExplorers.parcoursProfondeur(graph));

        System.out.println("-------------");

        int[][] directedMatrix = GraphFactory.createExempleDirectedMatrix();
        IDirectedGraph graph2 = new AdjencyMatrixDGraph(directedMatrix);
        System.out.println(GraphExplorers.parcoursProfondeur(graph2));
        System.out.println(GraphExplorers.parcoursLargeur(graph2,0));
        graph2.addNode();

        System.out.println("-------------");


        int[][] weightedDirectedMatrix = {{-1,1,-1,5,-1},
                                        {-1,-1,3,-1,-1},
                                        {-1,-1,-1,0,2},
                                        {-1,1,-1,-1,1},
                                        {-1,-1,-1,-1,-1}};// GraphFactory.createExempleWeightedDirectedMatrix();
        IDirectedGraph graph3 = new WeightedAdjencyMatrixDGraph(weightedDirectedMatrix);

        int[][] couts = PathFinding.floyd(graph3.getGraph());
        for (int i = 0; i < couts.length; i++) {
            System.out.println(Arrays.toString(couts[i]));
        }

        boolean[][] pathsExist = PathFinding.floydExistPath(GraphFactory.createExempleUndirectedMatrix());
        for (int i = 0; i < pathsExist.length; i++) {
            System.out.println(Arrays.toString(pathsExist[i]));
        }

        int[] c = PathFinding.bellmann(0, graph3);
        System.out.println(Arrays.toString(c));

    }
}
