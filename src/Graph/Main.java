package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.List;

import static Graph.ReadFile.readFile;

public class Main {

    public static void main(final String[] args) throws InterruptedException {
        String filename = "gka-Dateien/graph03.gka";
        Graph graph = readFile(filename);

        /**
         * BFS Algorithms aufrufen
         */

//        List<Node> bfsReturn = traverseWithBFS(graph, graph.getNode("Kiel"), graph.getNode("Husum"));
//
//        for (Node node : bfsReturn){
//            node.addAttribute("ui.style", " fill-color: red;");
//            //testGraph.addAttribute("ui.stylesheet", "edge { fill-color: red; }");
//        }
//
//        for(int i = 0; i < bfsReturn.size() - 1; i++){
//            // System.out.println(bfsReturn.get(i)+bfsReturn.get(i+1));
//            String node1 = String.valueOf(bfsReturn.get(i));
//            String node2 = String.valueOf(bfsReturn.get(i+1));
//            String edgeId = String.valueOf(i);
//
//            System.out.println(node1);
//            System.out.println(node2);
//
//
//        }
//
//        System.out.println(bfsReturn);

        /**
         * Graph Generator aufrufen
         */
        Graph newGraph = null;
        try {
            newGraph = GraphGenerator.generateGraph("test1", 20, 50, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newGraph.addAttribute("ui.stylesheet", styleSheet);
        newGraph.display();
        System.out.println(FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(newGraph, newGraph.getNode("0"),newGraph.getNode("1")));


        /**
         *  FloydWarshalAlgorithm aufrufen
         */
//        Node source = graph.getNode("Kiel");
//        Node target = graph.getNode("Lübeck");
//
//
//        FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(graph, source, target);
//
//        graph.display();

    }

    // Aussehen für die Graphen
    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;"+
                    "}" +

                    "node {" +
                    "   text-color: yellow;" +
                    "   shape: line;" +
                    "   text-size: 25px;" +
                    "   shape: circle;" +
                    "   fill-color: green;" +
                    "   size: 20px;" +
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
