package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Calendar;

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


        String filename1 = "gka-Dateien/graph04.gka";
        String filename2 = "newGraph/graph03.gka";
        Graph graph = readFile(filename2);

        /**
         * BFS Algorithms aufrufen
         */

//        List<Node> bfsReturn = traverseWithBFS(graph, graph.getNode("Kiel"), graph.getNode("Celle"));
//
//        for (int i = 0; i < bfsReturn.size(); i++) {
//            bfsReturn.get(i).addAttribute("uistyle", " fill-color: red;");
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
         * Graph BigNet aufrufen
         */
//        Graph newGraph = null;
//        try {
//            newGraph = GraphGenerator.generateBigNet("test1", 50, 800, 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        newGraph.addAttribute("ui.stylesheet", styleSheet);
//        newGraph.display();
//
//        System.out.println(newGraph.getNodeCount());
//        System.out.println(newGraph.getEdgeCount());

        //System.out.println(FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(newGraph, newGraph.getNode("0"),newGraph.getNode("1")));
        //System.out.println(DijkstraAlgorithm.shortestPathsWithDijkstra(newGraph, newGraph.getNode("0"), newGraph.getNode("1")));


        /**
         *  FloydWarshalAlgorithm aufrufen
         */
//        Node source = graph.getNode("Kiel");
//        Node target = graph.getNode("Lübeck");
//
//
//
//        FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(graph, source, target);
////
////
////
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
//
//        graph.display();

        /**
         *  FordFulkerson und EdmondsKarp aufrufen
         */

        Node source = graph.getNode("s");
        Node target = graph.getNode("t");



        //DijkstraAlgorithm.shortestPathsWithDijkstra(graph, "Kiel", "Lübeck");

        long begin = System.nanoTime();


        System.out.println("The maximum possible flow by Ford Fulkerson is " + FordFulkerson.fordFulkerson(graph, source, target));



        long end = System.nanoTime();

        long begin1 = System.nanoTime();

        System.out.println("The maximum possible flow by EdmonsKarpAlgorithm is " + EdmondsKarpAlgorithm.edmondsKarp(graph, source, target));

        long end1 = System.nanoTime();


        System.out.println("Executed Time: " + (end - begin) + " ns");

        System.out.println("Executed Time: " + (end1 - begin1) + " ns");

        graph.display();

        /**
         *  FordFulkerson und Edmonds Karp auf Bignet
         */

//        Graph bigNet = null;
//        int anzahl = 100; // die eingegebene Anzahl, im Beispiel 2
//
//        try {
//            bigNet = GraphGenerator.generateBigNet("test1", 800, 300000, 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        bigNet.addAttribute("ui.stylesheet", styleSheet);
//        // bigNet.display();
//
//        Node source = bigNet.getNode(0);
//        Node target = bigNet.getNode(20);
//
//        long begin = System.nanoTime();
//
//        System.out.println("Maximum Flow FordFulkerson: " + FordFulkerson.fordFulkerson(bigNet, source, target));
//
//
//
//        long end = System.nanoTime();
//        System.out.println("Executed Time: " + (end - begin));
//        System.out.println("--------------------------------------------");
//
//        long begin1 = System.nanoTime();
//
//
//
//        System.out.println("Maximum Flow Edmonds Karp: " + EdmondsKarpAlgorithm.edmondsKarp(bigNet, source, target));
//
//        long end1 = System.nanoTime();
//        System.out.println("Executed Time: " + (end1 - begin1));

    }

    // Aussehen für die Graphen
    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;" +
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
                    " stroke-mode: plain;" +
                    "text-color: white;" +
                    "text-size: 15px;" +
                    "fill-color: blue;" +
                    "text-background-mode: rounded-box;" +
                    "text-background-color: red;" +
                    "}" +

                    "edge.marked {" +
                    " fill-color: red;" +
                    " text-color: red;" +
                    "}";


}
