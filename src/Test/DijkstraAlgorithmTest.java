package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import java.util.List;

import static Graph.DijkstraAlgorithm.shortestPathsWithDijkstra;
import static Graph.FloydWarshalAlgorithm.shortestPathsWithFloydWarshal;
import static Graph.ReadFile.readFile;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DijkstraAlgorithmTest {

    @Test
    public void keinStatKnoten() {

        try {
            Graph graph = readFile("gka-Dateien/leerGraph.gka");

            Node source = graph.getNode(0);
            Node target = graph.getNode(graph.getNodeCount());

            List<Node> dijkstra = shortestPathsWithDijkstra(graph, source, target);

        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("SourceNode darf nicht null sein"));
        }
    }

    //Ein Knoten, gleichzeitig Source und Target
    @Test
    public void startKnoteIstZielKnoten() {

        Graph graph = readFile("gka-Dateien/graph03.gka");


        Node node1 = graph.getNode("Hamburg");


        List<Node> dijkstra = shortestPathsWithDijkstra(graph, graph.getNode("Hamburg"), graph.getNode("Hamburg"));

        assertTrue(dijkstra.size() == 1);
        assertTrue(dijkstra.get(0).equals(node1));

    }


    @Test
    public void graph03Test() {

        Graph graph = readFile("gka-Dateien/graph03.gka");

        Node node1 = graph.getNode("Hannover");
        Node node2 = graph.getNode("Celle");
        Node node3 = graph.getNode("Soltau");
        Node node4 = graph.getNode("Buxtehude");
        Node node5 = graph.getNode("Hamburg");

        List<Node> dijkstra = shortestPathsWithDijkstra(graph, graph.getNode("Hannover"), graph.getNode("Hamburg"));

        assertTrue(dijkstra.size() == 5);
        assertTrue(dijkstra.get(0).equals(node1));
        assertTrue(dijkstra.get(1).equals(node2));
        assertTrue(dijkstra.get(2).equals(node3));
        assertTrue(dijkstra.get(3).equals(node4));
        assertTrue(dijkstra.get(4).equals(node5));
    }


    @Test
    public void kreisMitNegativerLänger(){
        Graph graph = readFile("gka-Dateien/negativeGraph03.gka");

        List<Node> dijkstra = shortestPathsWithDijkstra(graph, graph.getNode("Kiel"), graph.getNode("Lübeck"));

        assertTrue(dijkstra == null);


    }

}
