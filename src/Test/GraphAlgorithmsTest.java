package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import org.junit.Before;
import org.junit.Test;
import Graph.*;

import org.graphstream.graph.implementations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


import static Graph.GraphAlgorithms.traverseWithBFS;
import static org.junit.Assert.*;


public class GraphAlgorithmsTest {

    @Test
    public void traverseWithBFS_SingleNodeTest() {
        Graph testGraph = new MultiGraph("testGraph");
        List<Node> expectedPath = new LinkedList<>();

        Node testNode1 = testGraph.addNode("A");
        expectedPath.add(testNode1);

        List<Node> bfs = traverseWithBFS(testGraph, testNode1, testNode1);

        assertTrue(bfs.equals(expectedPath));
    }

    @Test
    public void traverseWithBFS_MultiNodeTest() {
        Graph testGraph = new MultiGraph("testGraph");
        List<Node> expectedPath = new LinkedList<>();

        Node testNode1 = testGraph.addNode("A");
        Node testNode2 = testGraph.addNode("B");
        Node testNode3 = testGraph.addNode("C");
        testGraph.addEdge("1", testNode1, testNode2);
        testGraph.addEdge("2", testNode2, testNode3);

        expectedPath.add(testNode1);
        expectedPath.add(testNode2);
        expectedPath.add(testNode3);

        List<Node> bfs = traverseWithBFS(testGraph, testNode1, testNode3);

        assertTrue(bfs.equals(expectedPath));
    }

    @Test
    public void traverseWithBFS_UnconnectedNodeTest() {
        Graph testGraph = new MultiGraph("testGraph");
        List<Node> expectedPath = new LinkedList<>();

        Node testNode1 = testGraph.addNode("A");
        Node testNode2 = testGraph.addNode("B");
        Node testNode3 = testGraph.addNode("C");
        testGraph.addEdge("1", testNode1, testNode2);

        List<Node> bfs = traverseWithBFS(testGraph, testNode1, testNode3);

        assertTrue(bfs == null);
    }
}
