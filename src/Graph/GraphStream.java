import java.io.IOException;
import java.util.Iterator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.spriteManager.*;

import Graph.*;

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


        graph.addAttribute("ui.stylesheet", styleSheet);
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

        explore(graph.getNode("A"));
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


    protected String styleSheet =
            "node {" +
                    "	fill-color: black;" +
                    "}" +
                    "node.marked {" +
                    "	fill-color: red;" +
                    "}";
}