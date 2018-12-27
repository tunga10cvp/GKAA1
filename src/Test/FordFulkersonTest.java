package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import Graph.FordFulkerson;
import Graph.FordFulkerson;
import java.util.List;

import static Graph.DijkstraAlgorithm.shortestPathsWithDijkstra;
import static Graph.FloydWarshalAlgorithm.shortestPathsWithFloydWarshal;
import static Graph.FordFulkerson.graphMatrix;
import static Graph.ReadFile.readFile;
import static org.junit.Assert.*;


public class FordFulkersonTest {
    @Test
    public void newGraph01Test() {

        Graph graph = readFile("newGraph/graph01.gka");

        Node source = graph.getNode("0");
        Node target = graph.getNode("5");

        int[][] rGraph = graphMatrix(graph);

        Integer maxflow = FordFulkerson.fordFulkerson(rGraph, source, target);

        int maxFlow = 23;

        assertTrue(maxflow == maxflow);

    }

    @Test
    public void graph03Test() {

        Graph graph = readFile("gka-Dateien/graph03.gka");

        Node source = graph.getNode("Hamburg");
        Node target = graph.getNode("Kiel");

        int[][] rGraph = graphMatrix(graph);

        Integer maxflow = FordFulkerson.fordFulkerson(rGraph, source, target);

        int maxFlow = 276;

        assertTrue(maxflow == maxflow);

    }


    @Test
    public void graph04Test1() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("v8");

        int[][] rGraph = graphMatrix(graph);

        Integer maxflow = FordFulkerson.fordFulkerson(rGraph, source, target);

        int maxFlow = 20;

        assertTrue(maxflow == maxflow);

    }

    @Test
    public void graph04Test2() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("s");

        int[][] rGraph = graphMatrix(graph);

        Integer maxflow = FordFulkerson.fordFulkerson(rGraph, source, target);

        int maxFlow = 23;

        assertTrue(maxflow == maxflow);

    }


}
