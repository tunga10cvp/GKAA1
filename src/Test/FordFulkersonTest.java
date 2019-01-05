package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import Graph.FordFulkerson;
import Graph.FordFulkerson;
import java.util.List;

import static Graph.DijkstraAlgorithm.shortestPathsWithDijkstra;
import static Graph.FloydWarshalAlgorithm.shortestPathsWithFloydWarshal;
import static Graph.ReadFile.readFile;
import static org.junit.Assert.*;


public class FordFulkersonTest {
    @Test
    public void newGraph01Test() {

        Graph graph = readFile("newGraph/graph01.gka");

        Node source = graph.getNode("0");
        Node target = graph.getNode("5");



        Integer maxflow = FordFulkerson.fordFulkerson(graph, source, target);

        int maxFlow = 23;

        assertTrue(maxflow == maxflow);

    }

    @Test
    public void graph03Test() {

        Graph graph = readFile("gka-Dateien/graph03.gka");

        Node source = graph.getNode("Hamburg");
        Node target = graph.getNode("Kiel");



        Integer maxflow = FordFulkerson.fordFulkerson(graph, source, target);

        int maxFlow = 276;

        assertTrue(maxflow == maxflow);

    }


    @Test
    public void graph04Test1() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("v8");



        Integer maxflow = FordFulkerson.fordFulkerson(graph, source, target);

        int maxFlow = 20;

        assertTrue(maxflow == maxFlow);

    }

    @Test
    public void graph04Test2() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("s");



        Integer ergebnis = FordFulkerson.fordFulkerson(graph, source, target);

        int maxFlow = 23;

        assertTrue(ergebnis == maxFlow);

    }

    @Test
    public void zweiKnotenTest() {

        Graph graph = readFile("newGraph/graph02.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("v2");



        Integer ergebnis = FordFulkerson.fordFulkerson(graph, source, target);

        int maxFlow = 3;

        assertTrue(ergebnis == maxFlow);

    }

    @Test
    public void einKnotenTest() {

        Graph graph = readFile("newGraph/einKnote.gka");

        Node source = graph.getNode("v1");


        Integer ergebnis = FordFulkerson.fordFulkerson(graph, source, source);

        int maxFlow = 0;

        assertTrue(ergebnis == maxFlow);

    }

}
