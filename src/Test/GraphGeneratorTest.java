package Test;

import Graph.GraphGenerator;
import org.graphstream.graph.Graph;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GraphGeneratorTest {

    @Test
    public void emptyGraph() throws Exception {
        Graph graph = GraphGenerator.generateGraph("test1",0,0,0);

        assertTrue(graph.getAttributeCount() == 0);
        assertTrue(graph.getEdgeCount() == 0);
        assertTrue(graph.getNodeCount() == 0);
    }

    @Test
    public void singleGraph() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test2",1,0,0);

        assertTrue(graph != null);
        assertTrue(graph.getNodeCount() == 1);
        assertTrue(graph.getEdgeCount() == 0);
    }

    @Test
    public void twoNodesGraph() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test2",2,0,0);

        assertTrue(graph != null);
        assertTrue(graph.getNodeCount() == 2);
        assertTrue(graph.getEdgeCount() == 0);
    }


    @Test
    public void negativeGraph() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test3",-10,-10,0);


        assertTrue(graph.getAttributeCount() == 0);
        assertTrue(graph.getEdgeCount() == 0);
        assertTrue(graph.getNodeCount() == 0);
    }

    @Test
    public void bigGraph1() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test4",100,3500,10);

        assertTrue(graph != null);
        assertTrue(graph.getEdgeCount() == 3500);
        assertTrue(graph.getNodeCount() == 100);
    }

    @Test
    public void bigGraph2() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test5",1000,35000,10);

        assertTrue(graph != null);
        assertTrue(graph.getEdgeCount() == 35000);
        assertTrue(graph.getNodeCount() == 1000);
    }
}

