package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class GraphGenerator {

    /**
     * Erstellt eine allgemeine Konstruktion eines gerichteten Graphen für eine vorgegebene Anzahl von Knoten und
     * Kanten mit beliebigen, aber unterschiedlichen, nicht-negativen Kantenbewertungen.
     *
     * @param nodeNum     Knotenanzahl
     * @param edgeNum     Kantenanzahl
     * @param weightLimit Limit bis wohin die Kantengewichte gehen
     * @return Graph mit Vorgaben
     */
    public static Graph generateGraph(String name, int nodeNum, int edgeNum, int weightLimit) throws Exception {

        Graph generatedGraph = new MultiGraph(name);

        // negative Anzahl
        if (nodeNum < 0 || edgeNum < 0) {
            throw new IllegalArgumentException("Keine negative Anzahl wurde akzeptiert");
        }

        //keine Knoten aber trotzdem Kanten eingeben
        else if (nodeNum <= 0 && edgeNum > 0) {

            throw new IllegalArgumentException("Anzahl der Knoten muss größer als Anzahl der Kanten");
        } else {
            //generiere nodeNum Knoten
            for (int i = 0; i < nodeNum; i++) {
                generatedGraph.addNode(String.valueOf(i));

            }

            //generiere edgeNum Kanten
            //Start- und Zielknoten werden dabei zufällig bestimmt.
            for (int i = 0; i < edgeNum; i++) {

                String edgeId = String.valueOf(i);
                generatedGraph.addEdge(edgeId, String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), true);

                //add Random Weight
                generatedGraph.getEdge(edgeId).setAttribute("ui.label", Math.floor(Math.random() * weightLimit));

                // nodeNum als Name der Knote setzen
                generatedGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));
            }
        }
        return generatedGraph;
    }

    /**
     * generiert ein zusammenhängendes Netzwerk mit Quelle und Senke. Quelle und Senke haben entsprechende Attribute "Quelle" und "Senke".
     *
     * @param nodeNum     Knotenanzahl
     * @param edgeNum     Kantenanzahl
     * @param weightLimit Limit bis wohin die Kantengewichte gehen
     * @return Graph mit Vorgaben
     */
    public static Graph generateBigNet(String name, int nodeNum, int edgeNum, int weightLimit) throws Exception {

        Graph generatedGraph = new MultiGraph(name);

        // negative Anzahl Knoten
        if (nodeNum < 0 || edgeNum < 0) {
            throw new IllegalArgumentException("Keine negative Anzahl Knoten wird akzeptiert");
        }
        //keine Knoten aber trotzdem Kanten eingegeben
        if (nodeNum <= 0 && edgeNum > 0) {
            throw new IllegalArgumentException("Anzahl der Knoten muss größer als Anzahl der Kanten sein");
        }
        //Kantenanzahl nicht größer als Knotenanzahl
        if (nodeNum >= edgeNum) {
            throw new IllegalArgumentException("Kann kein Netzwerk generieren, wenn Kantenanzahl kleiner gleich Knotenanzahl");
        }
        //Nicht mindestens 2 Knoten für Quelle und Senke
        if (nodeNum < 2) {
            throw new IllegalArgumentException("Muss mindestens 2 Knoten besitzen für ein Netzwerk (Quelle und Senke)");
        }

        //generiere nodeNum Knoten und verbinde sie zu einem Netzwerk
        for (int i = 0; i < nodeNum; i++) {
            generatedGraph.addNode(String.valueOf(i));
            //System.out.println(generatedGraph.getNodeCount());

            //Verbinde mit bestehenden Knoten, um Zusammenhang zu gewährleisten
            if (generatedGraph.getNodeCount() > 1) {
                String newEdgeId = String.valueOf(i);
                String newNodeId = String.valueOf(i);
                String randomNodeInGraphId = String.valueOf(generatedGraph.getNode(String.valueOf(Math.round(Math.floor(Math.random() * generatedGraph.getNodeCount())))));
                generatedGraph.addEdge(newEdgeId, randomNodeInGraphId, newNodeId);
            }

            //System.out.println(generatedGraph.getEdgeCount());
        }

        //Bisher wurde pro Knoten (ausser der Quelle) eine Kante zur Sicherung des Zusammenhangs generiert
        int numberOfAlreadyGeneratedEdges = nodeNum - 1;

        //Füge restliche Kanten hinzu, falls zu generierende Kanten mehr als bisher generierte Kanten.
        //Start- und Zielknoten werden dabei zufällig bestimmt.
        if (edgeNum > numberOfAlreadyGeneratedEdges) {
            for (int i = numberOfAlreadyGeneratedEdges; i < edgeNum - numberOfAlreadyGeneratedEdges; i++) {
                String edgeId = String.valueOf(i);
                generatedGraph.addEdge(edgeId, String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), String.valueOf(Math.round(Math.floor(Math.random() * nodeNum))), true);
            }
        }

        //Alle Kanten mit zufälligem Gewicht versehen
        for (Edge currentEdge : generatedGraph.getEachEdge()) {
            currentEdge.setAttribute("ui.label", Math.floor(Math.random() * weightLimit));
        }
        //Quelle und Senke kennzeichnen
        generatedGraph.getNode("0").setAttribute("Quelle", true);
        generatedGraph.getNode(String.valueOf(generatedGraph.getNodeCount() - 1)).setAttribute("Senke", true);

        // nodeId als UI-Label der Knoten setzen
        generatedGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

        return generatedGraph;
    }
}
