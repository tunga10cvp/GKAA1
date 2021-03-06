package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import java.util.ArrayList;
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
        Graph graph = GraphGenerator.generateGraph("test5",20,50,10);

        Node source = graph.getNode(0);
        Node target = graph.getNode(19);

        List<Node> floyd = shortestPathsWithFloydWarshal(graph, source, target);

        List<Node> dijkstra = shortestPathsWithDijkstra(graph, source, target);


        //assertTrue(summeFloyd.equals(summeDijkstra));

        Double dijkstracost = dijkstra.get(dijkstra.size()-1).getAttribute("distance");
        Double floydCost = floyd.get(floyd.size()-1).getAttribute("distance");

        assertTrue(dijkstracost.equals(floydCost));

    }
}
