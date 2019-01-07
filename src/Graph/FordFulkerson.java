package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class FordFulkerson {


    /**
     * return true wenn es einen Pfad von startNode zu zielNode gibt
     *
     * @param residualGraphMatrix Eine Matrix  eines Graph
     * @param startNode           startNode
     * @param zielNode            zielNode
     * @param prev                Vorgänger
     * @return
     */

    public static boolean augmentingPathDFS(int residualGraphMatrix[][], Node startNode, Node zielNode, int prev[]) {

        //index für StartNode und ZielNode
        int source = startNode.getIndex();
        int target = zielNode.getIndex();

        // erstellt ein Visited Array und markiert alle Knoten als nicht visited
        boolean visited[] = new boolean[residualGraphMatrix.length];

        for (int i = 0; i < residualGraphMatrix.length; ++i) {
            visited[i] = false;
        }

        // mit Hilfe eines Stack um die Knote "zum Besuchen" zu speichern
        Stack<Integer> stack = new Stack<>();

        // Starten mit StartNode
        stack.add(source);
        prev[source] = -1;

        // Wenn Stack nicht leer ist
        while (!stack.isEmpty()) {

            int neighbor;

            // get AktuellNode
            Integer aktuell = stack.pop();

            // wenn noch nicht besucht, markiert als besucht
            if (!visited[aktuell]) {
                visited[aktuell] = true;
                //System.out.println("aktuelNode " + aktuell);

                List<Integer> neightborList = new ArrayList<>();

                for (neighbor = 0; neighbor < residualGraphMatrix.length; neighbor++) {
                    if (residualGraphMatrix[aktuell][neighbor] > 0 && !visited[neighbor]) {
                        neightborList.add(neighbor);

                    }
                }

                // die Nachbarliste wurde zufällig sortiert
                Collections.shuffle(neightborList);
                //System.out.println("random " + neightborList);

                
                for (int i = 0; i < neightborList.size(); i++) {
                    stack.push(neightborList.get(i));
                    prev[neightborList.get(i)] = aktuell;
                }

            }

        }

        return (visited[target] == true);
    }

    /**
     * @param graph     Graph
     * @param startNode startNode
     * @param zielNode  zielNode
     * @return maximal fluss zwischen startknote und zielKnote
     */
    public static int fordFulkerson(Graph graph, Node startNode, Node zielNode) {

        // setze den Wert für Fluss ist 0
        int maximalflow = 0;  //

        int nodeCount = graph.getNodeCount();

        //Liste aller Knoten
        List<Node> nodes = new ArrayList<>();

        // in Liste reinpacken
        for (Node node : graph.getEachNode()) {
            nodes.add(node);
        }

        // erstellt eine residualGraphMatrix und wandeln in einen residual Graph aus Graphstream Graph
        int residualGraphMatrix[][] = new int[nodeCount][nodeCount];

        int i, j;

        // pack alle Kantegewichte in residualGraphMatrix rein, wenn nicht verbunden -> auf 0 setzen
        for (i = 0; i < nodeCount; i++) {
            for (j = 0; j < nodeCount; j++) {
                for (Edge leavingEdge : nodes.get(i).getEachLeavingEdge()) {
                    if (leavingEdge.getNode1().equals(nodes.get(j))) {
                        if (residualGraphMatrix[i][j] > 0)
                            residualGraphMatrix[i][j] += (int) leavingEdge.getAttribute("ui.label");
                        else
                            residualGraphMatrix[i][j] = (int) leavingEdge.getAttribute("ui.label");
                    } else if (!nodes.get(i).hasEdgeToward(nodes.get(j))) {
                        residualGraphMatrix[i][j] = 0;
                    }
                }
            }
        }

        //wenn startknote auch zielKnote ist
        if (startNode == zielNode) {
            maximalflow = 0;
        } else {

            // index für Start und ZielKnote
            int source = startNode.getIndex();
            int target = zielNode.getIndex();

            // die Vorgänger
            int prev[] = new int[residualGraphMatrix.length];


            // wenn es den Pfad von startKonte zu ZielKnote gibt
            while (augmentingPathDFS(residualGraphMatrix, startNode, zielNode, prev)) {

                // der Wert für den FLuss wird am Anfang unendlich gesezt
                int aktuelleflow = Integer.MAX_VALUE;


                // Wenn wir den ZielKnote erreichen, # StartKnote
                // Nehmen wir den kleinesten Wert für den Fluss raus
                for (j = target; j != source; j = prev[j]) {
                    i = prev[j];
                    aktuelleflow = Math.min(aktuelleflow, residualGraphMatrix[i][j]);
                }

                // update den neuen Wert für jeden Fluss des Graphes
                for (j = target; j != source; j = prev[j]) {
                    i = prev[j];

                    //Eine forward  Kante
                    residualGraphMatrix[i][j] = residualGraphMatrix[i][j] - aktuelleflow;
                    //Eine backward Kante
                    residualGraphMatrix[j][i] = residualGraphMatrix[j][i] + aktuelleflow;
                }

                // Fügen Sie den Pfadfluss zum Gesamtfluss hinzu
                maximalflow += aktuelleflow;
            }
        }

        // Return den Gesamtfluss
        return maximalflow;
    }


    public static void main(String[] args) throws java.lang.Exception {

        Graph residualGraph = new MultiGraph("Residual Graph");

        residualGraph.addNode("x1");
        residualGraph.addNode("x2");
        residualGraph.addNode("x3");
        residualGraph.addNode("x4");
        residualGraph.addNode("x5");
        residualGraph.addNode("x6");
        residualGraph.addNode("x7");

        residualGraph.addEdge("x1x2", "x1", "x2", true);
        residualGraph.getEdge("x1x2").setAttribute("ui.label", 9);

        residualGraph.addEdge("x2x6", "x2", "x6", true);
        residualGraph.getEdge("x2x6").setAttribute("ui.label", 3);

        residualGraph.addEdge("x6x7", "x6", "x7", true);
        residualGraph.getEdge("x6x7").setAttribute("ui.label", 6);

        residualGraph.addEdge("x1x3", "x1", "x3", true);
        residualGraph.getEdge("x1x3").setAttribute("ui.label", 4);

        residualGraph.addEdge("x3x7", "x3", "x7", true);
        residualGraph.getEdge("x3x7").setAttribute("ui.label", 7);

//            residualGraph.addEdge("1", "x3", "x7", true);
//            residualGraph.getEdge("1").setAttribute("ui.label", 10);

        residualGraph.addEdge("x2x3", "x2", "x3", true);
        residualGraph.getEdge("x2x3").setAttribute("ui.label", 4);

        residualGraph.addEdge("x1x4", "x1", "x4", true);
        residualGraph.getEdge("x1x4").setAttribute("ui.label", 8);

        residualGraph.addEdge("x4x5", "x4", "x5", true);
        residualGraph.getEdge("x4x5").setAttribute("ui.label", 5);

        residualGraph.addEdge("x5x7", "x5", "x7", true);
        residualGraph.getEdge("x5x7").setAttribute("ui.label", 2);

        residualGraph.addEdge("x5x3", "x5", "x3", true);
        residualGraph.getEdge("x5x3").setAttribute("ui.label", 3);

        Node source = residualGraph.getNode("x1");
        source.addAttribute("ui.style", "fill-color: red;");

        Node target = residualGraph.getNode("x7");
        target.addAttribute("ui.style", "fill-color: blue;");

        residualGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

        residualGraph.addAttribute("ui.stylesheet", styleSheet);


        // Let us create a graph shown in the above example
        //int graph[][] = residualCapacityMatrix;


        System.out.println("Maximal Flow: " +
                FordFulkerson.fordFulkerson(residualGraph, residualGraph.getNode("x1"), residualGraph.getNode("x7")));

        //residualGraph.display();

    }

    // Aussehen für die Graphen
    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;" +
                    "}" +

                    "node {" +
                    "   text-color: yellow;" +
                    "   shape: line;" +
                    "   text-size: 20px;" +
                    "   shape: circle;" +
                    "   fill-color: green;" +
                    "   size: 15px;" +
                    //  "   text-alignment: center;" +
                    //  "   fill-mode: gradient-radial;" +
                    // "   stroke-color: yellow;" +

                    "}" +
                    "node.marked {" +
                    "	fill-color: green;" +
                    "}" +

                    " edge {" +
                    "shape: angle;" +
                    " stroke-mode: plain;" +
                    "text-color: white;" +
                    "text-size: 15px;" +
                    "fill-color: blue;" +
                    "text-background-mode: rounded-box;" +
                    "text-background-color: red;" +
                    "}" +

                    "edge.marked {" +
                    " fill-color: red;" +
                    " text-color: red;" +
                    "}";

}
