package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphStream {
    public static void main(String args[]) {
        new GraphStream();
    }

    public GraphStream() {
        Graph graph = new MultiGraph("tutorial 1");

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

//        Graph newGraph = null;
//        try {
//           newGraph = GraphGenerator.generateGraph("test1", 8, 15, 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        newGraph.addAttribute("ui.stylesheet", styleSheet);
//        newGraph.display();
//        System.out.println(FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(newGraph, newGraph.getNode("0"),newGraph.getNode("1")));

        graph.addNode("x1");
        graph.addNode("x2");

        graph.addEdge("1", "x1", "x2", true);
        graph.getEdge("1").setAttribute("ui.label", 3);

        graph.addEdge("2", "x1", "x2", true);
        graph.getEdge("2").setAttribute("ui.label", 10);

        graph.addEdge("3", "x1", "x2", true);
        graph.getEdge("3").setAttribute("ui.label", 110);

        int V = graph.getNodeCount();
        int residualGraphMatrix[][] = new int[V][V];

        List<Node> nodes = new ArrayList<>();

        for (Node node : graph.getEachNode()) {
            nodes.add(node);
        }

        // Add up all edge's weights from nodes i to j in the matrix, if not connected -> 0
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                for (Edge leavingEdge : nodes.get(i).getEachLeavingEdge()) {
                    if (nodes.get(i).hasEdgeToward(nodes.get(j))) {
                        if (residualGraphMatrix[i][j] > 0)
                            residualGraphMatrix[i][j] += (int)leavingEdge.getAttribute("ui.label");
                        else
                            residualGraphMatrix[i][j] = (int)leavingEdge.getAttribute("ui.label");
                    }
                    else
                        residualGraphMatrix[i][j] = 0;
                }
            }
        }

        System.out.println(residualGraphMatrix[0][1]);
        System.out.println(residualGraphMatrix[1][0]);

        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.display();

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