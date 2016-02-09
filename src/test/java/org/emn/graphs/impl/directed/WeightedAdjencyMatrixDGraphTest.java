package org.emn.graphs.impl.directed;

import org.emn.graphs.utils.GraphFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pierreg on 09/02/16.
 * Classe de test pour WeightedAdjencyMatrixDGraph
 */
public class WeightedAdjencyMatrixDGraphTest {

    private WeightedAdjencyMatrixDGraph graph;
    private int[][] matrix = GraphFactory.createExempleWeightedDirectedMatrix();

    @Before
    public void setUp() throws Exception {
        graph = new WeightedAdjencyMatrixDGraph(matrix);
    }

    @Test
    public void testIsArc() throws Exception {
        assertThat(graph.isArc(0,3), is(true));
        assertThat(graph.isArc(1,4), is(false));
    }

    @Test
    public void testRemoveEdge() throws Exception {
        assertThat(graph.isArc(0,3), is(true));
        graph.removeArc(0,3);
        assertThat(graph.isArc(0,3), is(false));
    }

    @Test
    public void testAddEdge() throws Exception {
        assertThat(graph.isArc(1,4), is(false));
        graph.addArc(1,4,2);
        assertThat(graph.isArc(1,4), is(true));
    }

    @Test
    public void testAddNode() throws Exception {
        int order = graph.getOrder();
        int node = graph.addNode();
        assertThat(node, is(order));
        assertThat(graph.getOrder(), is(order+1));
    }
}