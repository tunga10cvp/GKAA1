package Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;



public class DijkstraAlgorithm {

    public static long accessCounter = 0;

    /**
     *
     * @param graph mit dem wir arbeiten
     * @param source Startknote
     * @param target ZielKnote
     * @return List von Knoten, welche den kürzesten Weg zwischen Start und Zielknote zeigt
     */

    public static List<Node> shortestPathsWithDijkstra(Graph graph, Node source, Node target) {

        // Hashmap für Node und sein cost
        Map<Node, Double> cost = new HashMap<>();

        // Hashmap für Node und sein Vorgänger
        Map<Node, Node> prev = new HashMap<>();

        // Set visited
        Set<Node> visited = new HashSet<>();

        //check ob es StartKnote gibt
        if (source == null){
            throw new IllegalArgumentException("SourceNode darf nicht null sein");
        }

        if (target == null){
            throw new IllegalArgumentException("TargetNode darf nicht null sein");
        }

        NodeComparator codeComparator = new NodeComparator(cost);

        //Erstellen Sie eine Warteschlange, die nach den Kosten für den Startknoten geordnet ist
        PriorityQueue<Node> queue = new PriorityQueue<Node>(codeComparator);

        //Startwerte des Startknotens festlegen
        source.addAttribute("distance", 0.0);
        //Startkonte in prev hinzufügen
        prev.put(source, null);
        //cost für Startknote
        cost.put(source, 0.0);

        //add startknote in queue
        queue.add(source);

        //setz die anderen Knoten unendlichen Wert
        for (Node node : graph.getEachNode()) {
            if (node != source) {
                node.addAttribute("distance", Double.POSITIVE_INFINITY);
                cost.put(node, Double.POSITIVE_INFINITY);
            }
        }

        while (!queue.isEmpty()) {
            // nimmt erste Node in Warteschlange
            Node aktuell = queue.poll();

            //System.out.println(queue);
            //setz es visited
            visited.add(aktuell);

            // nicht besuchte Nachbarn
            Iterator<Node> neighbors = aktuell.getNeighborNodeIterator();


            while (neighbors.hasNext()) {
                Node neighbor = neighbors.next();

                Double distance;

                Double newDistance;
                // noch nicht besucht und hat Verbindung mit aktuellKnote
                if (!visited.contains(neighbor) && aktuell.hasEdgeToward(neighbor)) {

                    // Kosten zwischen dem aktuellen Knoten und seinem Nachbarn
                     distance = getShortestDistance(aktuell, neighbor);

                     if (distance < 0){
                         System.out.println("Kreis negativer Länge gefunden!");
                         return null;
                     }

                    // Kosten zwischen dem Start Knoten und seinem Nachbarn
                     newDistance = cost.get(aktuell) + distance;

                    // Gesamtkosten und vorherigen Knoten aktualisieren, wenn der Pfad besser ist
                    if (newDistance < cost.get(neighbor)) {
                        accessCounter++;

                        // löscht neightbor mit dem alten Cost, Vorgänger
                        if (queue.contains(neighbor)) {
                            queue.remove(neighbor);
                        }

                        neighbor.addAttribute("distance", newDistance);
                        queue.add(neighbor);
                        cost.put(neighbor, newDistance);

                        prev.put(neighbor, aktuell);
                    }
                }


            }

        }

        List<Node> path = new LinkedList<>();

        Node aktuellNode = target;
        while (aktuellNode!= null) {
            path.add(0, aktuellNode);
            aktuellNode = prev.get(aktuellNode);
        }

        if (path.isEmpty()){
            return null;
        }

        System.out.println("Anzahl der Zugriffe" + accessCounter);
        //System.out.println(path);
        uiForDijkstra(path);
        //cost.get(target).intValue();
        return path;


    }


    //Rechnen das geringeste Gewicht zwischen 2 Knoten in ein Graph
    private static double getShortestDistance(Node sourceNode, Node targetNode) {
        double minCost = Double.POSITIVE_INFINITY;
        for (Edge edge : sourceNode.getEachEdge()) {
            if (sourceNode.hasEdgeToward(targetNode) && sourceNode.getEdgeToward(targetNode).equals(edge)) {
                if (Double.valueOf(edge.getAttribute("ui.label").toString()) < minCost) {
                    minCost = Double.valueOf(edge.getAttribute("ui.label").toString());
                }
            }
        }
        return minCost;
    }

    public static void uiForDijkstra(List<Node> path){

        List<Double> distance = new ArrayList<>();

        if (!path.isEmpty()) {
            for (int i = 0; i < path.size(); i++) {
                path.get(i).addAttribute("ui.style", " fill-color: red;");
            }
            for (int i = 0; i < path.size() - 1; i++) {
                path.get(i).getEdgeToward(path.get(i + 1)).addAttribute("ui.style", " fill-color: red;");
            }

            System.out.println("Unser Weg: " + path.get(0) + " als Startknote und " + path.get(path.size() - 1) + " als Zielknote");
            System.out.println("----------------------------------------------------------");

            for (int i = 0; i < path.size() - 1; i++) {
                distance.add(path.get(i).getEdgeToward(path.get(i + 1)).getAttribute("ui.label"));
                System.out.println("Von " + path.get(i) + " nach " + path.get(i + 1) + " mit dem Abstand: " + path.get(i).getEdgeToward(path.get(i + 1)).getAttribute("ui.label"));
            }

            Double summe = 0.0;
            for (Double dis : distance) {
                summe += dis;
            }


            System.out.println("----------------------------------------------------------");
            System.out.println("Die Entfernung zwischen Startknote und ZielKnote: " + summe);
            System.out.println("----------------------------------------------------------");

        }
        else {
            System.out.println("Kein Path gefunden");
        }

    }


}
