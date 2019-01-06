package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import static Graph.ReadFile.readFile;
import static org.junit.Assert.assertTrue;

import Graph.EdmondsKarpAlgorithm;


public class EdmondsKarpAlgorithmTest {

    @Test
    public void newGraph01Test() {

        Graph graph = readFile("newGraph/graph01.gka");

        Node source = graph.getNode("0");
        Node target = graph.getNode("5");



        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);

        int maxflow = 23;

        assertTrue(ek == maxflow);

    }

    @Test
    public void graph04Test1() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("v8");



        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);

        int maxflow = 8;

        assertTrue(ek == maxflow);

    }

    @Test
    public void graph04Test2() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("s");



        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);

        int maxFlow = 12;

        assertTrue(ek == maxFlow);

    }

    @Test
    public void zweiKnotenTest() {

        Graph graph = readFile("newGraph/graph02.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("v2");



        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);

        int maxFlow = 3;

        assertTrue(ek == maxFlow);

    }

    @Test
    public void einKnotenTest() {

        Graph graph = readFile("newGraph/einKnote.gka");

        Node source = graph.getNode("v1");


        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, source);

        int maxFlow = 0;

        assertTrue(ek == maxFlow);

    }

    @Test
    public void mehrfachkanteTest(){
        Graph graph = readFile("newGraph/mehrfachkante.gka");

        Node source = graph.getNode("x1");
        Node target = graph.getNode("x7");

        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);

        int maxFlow = 16;

        assertTrue(ek == maxFlow);
    }
}
