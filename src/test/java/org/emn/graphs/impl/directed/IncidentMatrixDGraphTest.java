package org.emn.graphs.impl.directed;

import org.emn.graphs.exception.GraphFactoryException;
import org.emn.graphs.interfaces.IDirectedGraph;
import org.emn.graphs.utils.GraphFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by pierreg on 09/02/16.
 * Classe de test pour IncidentMatrixDGraph
 */
public class IncidentMatrixDGraphTest {

    private IDirectedGraph graph;
    private int[][] matrix = {{0, 0, 1, 1, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0}};

    @Before
    public void setUp(){
        graph = new IncidentMatrixDGraph(matrix);
    }

    @Test
    public void testAddVertex() throws Exception {
        int val = graph.addNode();
        assertEquals(val, 5);
    }

    @Test
    public void testisArc() throws Exception {
        assertTrue(graph.isArc(0,2));
        assertFalse(graph.isArc(4,4));
    }

    @Test
    public void testGetSuccessors() throws Exception {
        List<Integer> resultList = graph.getSuccessors(3);
        Integer[] succ = {1,2,4};
        List<Integer> expectedList = Arrays.asList(succ);
        assertEquals(expectedList, resultList);
    }

    @Test
    public void testGetPredecessors() throws Exception {
        List<Integer> resultList = graph.getPredecessors(3);
        Integer[] pred = {0,1,2};
        List<Integer> expectedList = Arrays.asList(pred);
        assertEquals(expectedList, resultList);
    }

    @Test
    public void testRemoveEdge() throws Exception {
        graph.removeArc(0,2);
        assertFalse(graph.isArc(0,2));
    }

    @Test
    public void testAddEdge() throws Exception {
        graph.addArc(4,3);
        assertTrue(graph.isArc(4,3));
    }

    @Test
    public void testInverse(){
        int[][] resultMatrix = graph.inverse().getGraph();
        int[][] expectedMatrix = {{0,1,1,0,1},
                {0,0,1,1,0},
                {1,1,0,1,1},
                {1,1,1,0,0},
                {1,0,1,1,0}};
        assertThat(resultMatrix, is(expectedMatrix));
    }

    @Test
    public void testGetGraph(){
        int[][] resultMatrix = graph.getGraph();
        assertThat(resultMatrix, is(matrix));
    }

    @Test
    public void testRandom() throws GraphFactoryException {
        Random random = new Random();
        int order = random.nextInt(40)+1;
        int maxEdge = order * (order - 1);
        int nbEdge = (maxEdge > 0) ? random.nextInt(maxEdge) : 0;
        int poidsMax = random.nextInt(10)+1;
        graph = new IncidentMatrixDGraph(GraphFactory.createRandomMatrix(order, nbEdge, true));

        assertThat(graph.addNode(), is(order));
        int randomVertex = random.nextInt(order);
        for (Integer succ : graph.getSuccessors(randomVertex)) {
            assertThat(graph.getPredecessors(succ), hasItem(randomVertex));
        }
        assertThat(graph.inverse().inverse().getGraph(), is(graph.getGraph()));
    }
}