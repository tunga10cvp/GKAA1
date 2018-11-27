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

        Node source = graph.getNode("Kiel");
        Node target = graph.getNode("Lübeck");


        FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(graph, source, target);

        graph.display();



    }

}
