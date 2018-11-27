package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import java.util.List;

import static Graph.FloydWarshalAlgorithm.shortestPathsWithFloydWarshal;
import static Graph.ReadFile.readFile;
import static org.junit.Assert.assertTrue;

public class FloydWarshalAlgorithmTest {

    //leer Graph
    @Test
    public void leerGraph() {

        Graph graph = readFile("gka-Dateien/leerGraph.gka");

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, graph.getNode(0), graph.getNode(graph.getNodeCount()-1));

        assertTrue(floyd.isEmpty());
    }

    //Ein Knoten, gleichzeitig Source und Target
    @Test
    public void startKnoteIstZielKnoten() {

        Graph graph = readFile("gka-Dateien/graph03.gka");


        Node node1 = graph.getNode("Kiel");


        List<Node> floyd = shortestPathsWithFloydWarshal(graph, graph.getNode("Kiel"), graph.getNode("Kiel"));

        assertTrue(floyd.size() == 1);
        assertTrue(floyd.get(0).equals(node1));

    }


    @Test
    public void graph03Test() {

        Graph graph = readFile("gka-Dateien/graph03.gka");


        Node node1 = graph.getNode("Hannover");
        Node node2 = graph.getNode("Celle");
        Node node3 = graph.getNode("Soltau");
        Node node4 = graph.getNode("Buxtehude");
        Node node5 = graph.getNode("Hamburg");

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, graph.getNode("Hannover"), graph.getNode("Hamburg"));

        assertTrue(floyd.size() == 5);
        assertTrue(floyd.get(0).equals(node1));
        assertTrue(floyd.get(1).equals(node2));
        assertTrue(floyd.get(2).equals(node3));
        assertTrue(floyd.get(3).equals(node4));
        assertTrue(floyd.get(4).equals(node5));
    }


    @Test
    public void kreisMitNegativerLänger(){
        Graph graph = readFile("gka-Dateien/negativeGraph03.gka");

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, graph.getNode("Kiel"), graph.getNode("Lübeck"));

        assertTrue(floyd == null);


    }

}
