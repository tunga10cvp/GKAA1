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
    public void graph03Test() {

        Graph graph = readFile("newGraph/graph03.gka");

        Node source = graph.getNode("0");
        Node target = graph.getNode("5");




        int[][] rGraph = graphMatrix(graph);




        Integer maxflow = FordFulkerson.fordFulkerson(rGraph, source, target);


        assertTrue(maxflow == 23);

    }

}
