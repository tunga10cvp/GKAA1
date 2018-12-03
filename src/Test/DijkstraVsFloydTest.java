package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import java.util.List;

import static Graph.DijkstraAlgorithm.shortestPathsWithDijkstra;
import static Graph.FloydWarshalAlgorithm.shortestPathsWithFloydWarshal;
import Graph.GraphGenerator;
import static Graph.ReadFile.readFile;
import static org.junit.Assert.assertTrue;

public class DijkstraVsFloydTest {
    @Test
    public void graph03Test() {

        Graph graph = readFile("gka-Dateien/graph03.gka");

        Node source = graph.getNode("Hannover");
        Node target = graph.getNode("Hamburg");

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, source, target);

        List<Node> dijkstra = shortestPathsWithDijkstra(graph, source, target);

        assertTrue(floyd.equals(dijkstra));

    }

    @Test
    public void generateGraphTest() throws Exception{
        Graph graph = GraphGenerator.generateGraph("test5",100,3500,10);

        Node source = graph.getNode(0);
        Node target = graph.getNode(1);

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, source, target);

        List<Node> dijkstra = shortestPathsWithDijkstra(graph,source,target);

        assertTrue(floyd.equals(dijkstra));
    }
}
