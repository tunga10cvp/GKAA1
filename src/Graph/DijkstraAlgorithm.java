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

    public static List<Node> shortestPathsWithDijkstra(Graph graph, Node source, Node target) {

        // Hashmap für Node und sein cost
        Map<Node, Double> costs = new HashMap<>();

        // Hashmap zwischen Node und Vorgänger
        Map<Node, Node> prev = new HashMap<>();

        Set<Node> visited = new HashSet<>();

        //check ob es StartKnote gibt
        if (source == null){
            throw new IllegalArgumentException("SourceNode darf nicht null sein");
        }
        //Todo Comparator für die Queue
        NodeComparator codeComparator = new NodeComparator(costs);

        //Erstellen Sie eine Warteschlange mit minimaler Priorität, die nach den Kosten für den Startknoten geordnet ist
        PriorityQueue<Node> queue = new PriorityQueue<Node>(codeComparator);

        //Startwerte des Startknotens festlegen
        source.addAttribute("distance", 0.0);
        prev.put(source, null);
        costs.put(source, 0.0);

        queue.add(source);

        //setz die anderen Knoten unendlichen Wert
        for (Node node : graph.getEachNode()) {
            if (node != source) {
                node.addAttribute("distance", Double.POSITIVE_INFINITY);
                costs.put(node, Double.POSITIVE_INFINITY);
            }
        }

        while (!queue.isEmpty()) {
            Node aktuell = queue.poll();

            //System.out.println(queue);
            //setz es visited
            visited.add(aktuell);

            // nicht besuchte Nachbarn
            Iterator<Node> neighbors = aktuell.getNeighborNodeIterator();

            while (neighbors.hasNext()) {
                Node node = neighbors.next();

                Double distance;

                Double newCost;

                if (!visited.contains(node) && aktuell.hasEdgeToward(node)) {

                    // Kosten zwischen dem aktuellen Knoten und seinem Nachbarn
                     distance = getShortestDist(aktuell, node);

                     if (distance < 0){
                         System.out.println("Kreis negativer Länge gefunden!");
                         return null;
                     }

                    // Kosten zwischen dem Start Knoten und seinem Nachbarn
                     newCost = costs.get(aktuell) + distance;

                    // Gesamtkosten und vorherigen Knoten aktualisieren, wenn der Pfad besser ist
                    if (newCost < costs.get(node)) {
                        accessCounter++;
                        if (queue.contains(node)) {
                            queue.remove(node);
                        }

                        node.addAttribute("distance", newCost);
                        queue.add(node);
                        costs.put(node, newCost);

                        prev.put(node, aktuell);
                    }
                }


            }

        }
//        System.out.println(prev);

        // trace back the path with HashMap
        List<Node> path = new LinkedList<>();

        Node curr = target;
        while (curr != null) {
            path.add(0, curr);
            curr = prev.get(curr);

        }
        System.out.println(accessCounter);
        uiForDijkstra(path);
        return path;


    }


    private static double getShortestDist(Node sourceNode, Node targetNode) {
        double min = Double.POSITIVE_INFINITY;
        for (Edge edge : sourceNode.getEachEdge()) {
            if (sourceNode.hasEdgeToward(targetNode) && sourceNode.getEdgeToward(targetNode).equals(edge)) {
                if (Double.valueOf(edge.getAttribute("ui.label").toString()) < min) {
                    min = Double.valueOf(edge.getAttribute("ui.label").toString());
                }
            }
        }
        return min;
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
