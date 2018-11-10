package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class GraphAlgorithmsTest {



    @Test
    public void traverseWithBFS() {
        Graph testGraph = new MultiGraph("testGraph");

        testGraph.addNode("A");
        traverseWithBFS();

    }
}
