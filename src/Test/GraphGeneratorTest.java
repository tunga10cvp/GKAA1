package Test;

import Graph.GraphGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import java.util.List;

import static Graph.FloydWarshalAlgorithm.shortestPathsWithFloydWarshal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

        try {
            Graph graph = GraphGenerator.generateGraph("test3",-10,-10,0);

            fail("This should have thrown an exception");

        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Keine negative Anzahl wurde akzeptiert"));
        }


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

    @Test
    public void floydWarhsalTest() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test5",1000,35000,10);

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, graph.getNode(0), graph.getNode(50));

        assertTrue(graph != null);
        assertTrue(graph.getEdgeCount() == 35000);
        assertTrue(graph.getNodeCount() == 1000);
    }
}

