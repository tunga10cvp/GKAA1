package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Iterator;

public class GraphStream {
    public static void main(String args[]) {
        new GraphStream();
    }

    public GraphStream() {
        Graph graph = new SingleGraph("tutorial 1");

//        SpriteManager sman = new SpriteManager(graph);
//
//        Sprite s = sman.addSprite("S1");
//
//        s.setPosition(2,1,0);
//        s.attachToNode("A");
//        s.attachToEdge("AB");
//        s.setPosition(0.5);
//        s.detach();


/*        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addEdge("AB", "A", "B").addAttribute("ui.label", 10);
        graph.addEdge("BC", "B", "C").addAttribute("name", "tung");
        graph.addEdge("CA", "C", "A").addAttribute("ui.label", 20);
        graph.addEdge("AD", "A", "D");
        graph.addEdge("DE", "D", "E");
        graph.addEdge("DF", "D", "F");
        graph.addEdge("EF", "E", "F");
        graph.addNode("G");

        try {
            GraphIOSave.saveGraph(graph, "speichern1.gka");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        explore(graph.getNode("A"));*/

        Graph newGraph = null;
        try {
           newGraph = GraphGenerator.generateGraph("test1", 8, 15, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newGraph.addAttribute("ui.stylesheet", styleSheet);
        newGraph.display();
        System.out.println(FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(newGraph, newGraph.getNode("0"),newGraph.getNode("1")));
    }

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }


    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
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