package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static Graph.DijkstraAlgorithm.shortestPathsWithDijkstra;
import static Graph.GraphAlgorithms.traverseWithBFS;
import static Graph.ReadFile.readFile;

public class Main {

    public static void main(final String[] args) throws InterruptedException {
        String filename = "gka-Dateien/graph03.gka";
        Graph graph = readFile(filename);

        /**
         * BFS Algorithms aufrufen
         */

//        List<Node> bfsReturn = traverseWithBFS(graph, graph.getNode("Kiel"), graph.getNode("Celle"));
//
//        for (int i = 0; i < bfsReturn.size(); i++) {
//            bfsReturn.get(i).addAttribute("ui.style", " fill-color: red;");
//        }
//
//        for (int i = 0; i < bfsReturn.size() - 1; i++) {
//            bfsReturn.get(i).getEdgeToward(bfsReturn.get(i + 1)).addAttribute("ui.style", " fill-color: red;");
//        }
//        graph.display();
//
//        System.out.println(bfsReturn);

        /**
         * Graph Generator aufrufen
         */
//        Graph newGraph = null;
//        try {
//            newGraph = GraphGenerator.generateGraph("test1", 100, 3500, 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        newGraph.addAttribute("ui.stylesheet", styleSheet);
//        newGraph.display();
//
//        System.out.println(FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(newGraph, newGraph.getNode("0"),newGraph.getNode("1")));
//        System.out.println(DijkstraAlgorithm.shortestPathsWithDijkstra(newGraph, newGraph.getNode("0"), newGraph.getNode("1")));


        /**
         *  FloydWarshalAlgorithm aufrufen
         */
        Node source = graph.getNode("Kiel");
        Node target = graph.getNode("Lübeck");



        FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(graph, source, target);
//
//
//
//        graph.display();



        /**
         *  DijkstraAlgorithm aufrufen
         */

//        Node source = graph.getNode("Hamburg");
//        Node target = graph.getNode("Husum");
//
//        //DijkstraAlgorithm.shortestPathsWithDijkstra(graph, "Kiel", "Lübeck");
//
//        System.out.println(DijkstraAlgorithm.shortestPathsWithDijkstra(graph, source, target));

       // graph.display();


    }

    // Aussehen für die Graphen
    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;"+
                    "}" +

                    "node {" +
                    "   text-color: yellow;" +
                    "   shape: line;" +
                    "   text-size: 20px;" +
                    "   shape: circle;" +
                    "   fill-color: green;" +
                    "   size: 15px;" +
                    //  "   text-alignment: center;" +
                    //  "   fill-mode: gradient-radial;" +
                    // "   stroke-color: yellow;" +

                    "}" +
                    "node.marked {" +
                    "	fill-color: green;" +
                    "}" +

                    " edge {" +
                    "shape: angle;" +
                    " stroke-mode: plain;"+
                    "text-color: white;"+
                    "text-size: 15px;"+
                    "fill-color: blue;"+
                    "text-background-mode: rounded-box;"+
                    "text-background-color: red;"+
                    "}"+

                    "edge.marked {"+
                    " fill-color: red;"+
                    " text-color: red;"+
                    "}";

}