package org.emn.graphs.impl.undirected;

import org.emn.graphs.exception.GraphFactoryException;
import org.emn.graphs.interfaces.IUndirectedGraph;
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
 */
public class IncidentMatrixUGraphTest {

    private IUndirectedGraph graph;
    private int[][] matrix = {{0, 0, 1, 1, 1},
            {0, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 0, 1},
            {1, 1, 0, 1, 0}};

    @Before
    public void setUp() throws Exception {
        graph = new AdjencyMatrixUGraph(matrix);
    }

    @Test
    public void testGetNeighbors() throws Exception {
        List<Integer> resultList = graph.getNeighbors(1);
        Integer[] neighbors = {2,3};
        List<Integer> expectedList = Arrays.asList(neighbors);
        assertEquals(expectedList,resultList);
    }

    @Test
    public void testAddVertex() throws Exception {
        int val = graph.addNode();
        assertEquals(val, 5);
    }

    @Test
    public void testRemoveEdge() throws Exception {
        graph.removeEdge(0,2);
        assertFalse(graph.isEdge(0,2));
        assertFalse(graph.isEdge(2,0));
    }

    @Test
    public void testAddEdge() throws Exception {
        graph.addEdge(0,1);
        assertTrue(graph.isEdge(0,1));
        assertTrue(graph.isEdge(1,0));
    }

    @Test
    public void testIsEdge() throws Exception {
        assertTrue(graph.isEdge(3,0));
        assertFalse(graph.isEdge(2,4));
    }

    @Test
    public void testRandom() throws GraphFactoryException {
        Random random = new Random();
        int order = random.nextInt(40)+1;
        int maxEdge = order * (order - 1) / 2;
        int nbEdge = (maxEdge > 0) ? random.nextInt(maxEdge) : 0;
        graph = new AdjencyListUGraph(GraphFactory.createRandomMatrix(order, nbEdge, false));

        assertThat(graph.addNode(), is(order));
        int randomVertex = random.nextInt(order);
        for (Integer succ : graph.getNeighbors(randomVertex)) {
            assertThat(graph.getNeighbors(succ), hasItem(randomVertex));
        }
    }
}