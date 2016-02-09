package org.emn.graphs.impl.undirected;

import org.emn.graphs.utils.GraphFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pierreg on 09/02/16.
 */
public class WeightedAdjencyMatrixUGraphTest {

    private WeightedAdjencyMatrixUGraph graph;
    private int[][] matrix = GraphFactory.createExempleWeightedUndirectedMatrix();

    @Before
    public void setUp() throws Exception {
        graph = new WeightedAdjencyMatrixUGraph(matrix);
    }

    @Test
    public void testIsEdge() throws Exception {
        assertThat(graph.isEdge(0,3), is(true));
        assertThat(graph.isEdge(1,4), is(false));
    }

    @Test
    public void testRemoveEdge() throws Exception {
        assertThat(graph.isEdge(0,3), is(true));
        graph.removeEdge(0,3);
        assertThat(graph.isEdge(0,3), is(false));
    }

    @Test
    public void testAddEdge() throws Exception {
        assertThat(graph.isEdge(1,4), is(false));
        graph.addEdge(1,4,2);
        assertThat(graph.isEdge(1,4), is(true));
    }

    @Test
    public void testAddNode() throws Exception {
        int order = graph.getOrder();
        int node = graph.addNode();
        assertThat(node, is(order));
        assertThat(graph.getOrder(), is(order+1));
    }
}