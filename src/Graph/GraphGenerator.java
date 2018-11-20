package Graph;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {

    /**
     * Erstellt eine allgemeine Konstruktion eines gerichteten Graphen für eine vorgegebene Anzahl von Knoten und
     * Kanten mit beliebigen, aber unterschiedlichen, nicht-negativen Kantenbewertungen.
     * @param nodeNum       Knotenanzahl
     * @param edgeNum       Kantenanzahl
     * @param weightLimit   Limit bis wohin die Kantengewichte gehen
     * @return              Graph mit Vorgaben
     */
    public static Graph generateGraph(String name, int nodeNum, int edgeNum, int weightLimit) throws Exception {

        Graph generatedGraph = new MultiGraph(name);

        // negative Anzahl
        if (nodeNum < 0 || edgeNum < 0){
            System.out.println("Kein Graph!!!");
        }

        //keine Knoten aber trotzdem Kanten eingeben
        else if(nodeNum <= 0 && edgeNum > 0){

            throw new IllegalArgumentException( "Anzahl der Knoten muss größer als Anzahl der Kanten");
        }

        else {
            //generiere nodeNum Knoten
            for (int i = 0; i < nodeNum; i++) {
                generatedGraph.addNode(String.valueOf(i));

            }
            System.out.println(generatedGraph.getNode(1).getId());
            //generiere edgeNum Kanten
            //Start- und Zielknoten werden dabei zufällig bestimmt.
            for (int i = 0; i < edgeNum; i++) {
                String edgeId = String.valueOf(i);
                generatedGraph.addEdge(edgeId, String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), true);
                generatedGraph.getEdge(edgeId).setAttribute("weight", Math.floor(Math.random() * 100) * weightLimit);
                // nodeNum als Name der Knote setzen
                generatedGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));
            }
        }
        return generatedGraph;
    }
}
