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
    public static Graph generateGraph(String name, int nodeNum, int edgeNum, int weightLimit){

        Graph generatedGraph = new MultiGraph(name);

        //generiere nodeNum Knoten
        for(int i = 0;i<nodeNum;i++){
            generatedGraph.addNode(String.valueOf(i));
        }

        //generiere edgeNum Kanten
        //Start- und Zielknoten werden dabei zufällig bestimmt.
        for(int i = 0;i<edgeNum;i++){
            String edgeId = String.valueOf(i);
            generatedGraph.addEdge(edgeId, String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), true);
            generatedGraph.getEdge(edgeId).setAttribute("weight", Math.random() * weightLimit);
        }

        return generatedGraph;
    }
}
