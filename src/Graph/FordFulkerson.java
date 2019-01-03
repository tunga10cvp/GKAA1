package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class FordFulkerson {

    /**
     * return true wenn es einen Pfad von startNode zu zielNode gibt
     *
     * @param adjazenzMatrix   Eine Adjazenzmatrix eines Graph
     * @param startNode startNode
     * @param zielNode  zielNode
     * @param prev      Vorgänger
     * @return
     */
//    public static boolean bfs(int adjazenzMatrix[][], Node startNode, Node zielNode, int prev[]) {
//
//        //index für StartNode und ZielNode
//        int source = startNode.getIndex();
//        int target = zielNode.getIndex();
//
//        // erstellt ein Visited Array und markiert alle Knoten als nicht visited
//        boolean visited[] = new boolean[adjazenzMatrix.length];
//
//        for (int i = 0; i < adjazenzMatrix.length; ++i) {
//            visited[i] = false;
//        }
//
//        // erstellt eine Liste, fügt startNode hinzu und markiert als visited
//        LinkedList<Integer> queue = new LinkedList<Integer>();
//        queue.add(source);
//        visited[source] = true;
//        prev[source] = -1;
//
//        // Wenn die Liste nicht leer ist
//
//        while (!queue.isEmpty()) {
//
//            // aktuellKnote aus der Queue
//            int aktuell = queue.poll();
//
//            // gucke alle Nachbar
//            for (int neighbor = 0; neighbor < adjazenzMatrix.length; neighbor++) {
//
//                // Wenn der noch nicht besucht wurde und mit dem aktuellen Knote verkfnüpfen ist
//                // füg den Nachbar in queue hinzu und markiert als visited
//                if (visited[neighbor] == false && adjazenzMatrix[aktuell][neighbor] > 0) {
//                    queue.add(neighbor);
//                    prev[neighbor] = aktuell;
//                    visited[neighbor] = true;
//                }
//            }
//        }
//
//        // return true, wenn wir den ZielKnote erreichen
//        return (visited[target] == true);
//    }

    /**
     * return true wenn es einen Pfad von startNode zu zielNode gibt
     *
     * @param adjazenzMatrix   Eine Adjazenzmatrix eines Graph
     * @param startNode startNode
     * @param zielNode  zielNode
     * @param prev      Vorgänger
     * @return
     */

    public static boolean augmentingPathDFS(int adjazenzMatrix[][], Node startNode, Node zielNode, int prev[]) {

        //index für StartNode und ZielNode
        int source = startNode.getIndex();
        int target = zielNode.getIndex();

        // erstellt ein Visited Array und markiert alle Knoten als nicht visited
        boolean visited[] = new boolean[adjazenzMatrix.length];

        for (int i = 0; i < adjazenzMatrix.length; ++i) {
            visited[i] = false;
        }

        // use Stack zu speichern von startNode zu zielNode
        Stack<Integer> stack =new  Stack<Integer>();

        // Starten mit StartNode
        stack.push(source);

        // Wenn Stack nicht leer ist
        while (!stack.isEmpty()) {

            int neighbor;

            // get AktuellNode
            Integer aktuell = stack.pop();

            // wenn noch nicht besucht, markiert als besucht
            if (!visited[aktuell]){
                visited[aktuell] = true;
                prev[source] = -1;

                //guck alle Nachbar von AktuellNode die noch nicht besucht wurden
                for (neighbor = 0; neighbor < adjazenzMatrix.length; neighbor++){
                    if (adjazenzMatrix[aktuell][neighbor] > 0 && !visited[neighbor]){
                        //in Stack reinpacken
                        // Node mit niedriger Id liegt oben
                        stack.push(neighbor);
                        //set Vorgänger
                        prev[neighbor] = aktuell;
                    }
                }
            }
        }
        return (visited[target] == true);
    }

    /**
     *
     * @param adjazenzMatrix   Eine Adjazenzmatrix eines Graph
     * @param startNode startNode
     * @param zielNode  zielNode

     * @return maximal fluss zwischen startknote und zielKnote
     */
    public static int fordFulkerson(int adjazenzMatrix[][], Node startNode, Node zielNode) {

        // setze den Wert für Fluss ist 0
        int maximalflow = 0;  //

        //wenn startknote auch zielKnote ist
        if (startNode == zielNode) {
            maximalflow = 0;
        } else {

            // index für Start und ZielKnote
            int source = startNode.getIndex();
            int target = zielNode.getIndex();

            // erstellt eine Matrix eines Graph und tragen die KanteGewichte in diese Matrix ein
            // wenn es kein Gewicht ziwschen 2 Knoten gibt, wird residualGraph[i][j] auf 0 gesetzt
            int residualGraph[][] = new int[adjazenzMatrix.length][adjazenzMatrix.length];

            //
            int i,j;

            // setzt die Werte für residualGraph aus der adjazenzMatrix
            for (i = 0; i < residualGraph.length; i++) {
                for (j = 0; j < residualGraph.length; j++) {
                    residualGraph[i][j] = adjazenzMatrix[i][j];
                    //System.out.println(rGraph[u][v]);
                }

            }
            // die Vorgänger
            int prev[] = new int[residualGraph.length];


            // wenn es den Pfad von startKonte zu ZielKnote gibt
            while (augmentingPathDFS(residualGraph, startNode, zielNode, prev)) {

                // der Wert für den FLuss wird am Anfang unendlich gesezt
                int aktuelleflow = Integer.MAX_VALUE;


                // Wenn wir den ZielKnote erreichen, # StartKnote
                // Nehmen wir den kleinesten Wert für den Fluss raus
                for (j = target; j != source; j = prev[j]) {
                    i = prev[j];
                    aktuelleflow = Math.min(aktuelleflow, residualGraph[i][j]);
                }

                // update den neuen Wert für jeden Fluss des Graphes
                for (j = target; j != source; j = prev[j]) {
                    i = prev[j];

                    //Eine forward  Kante
                    residualGraph[i][j] = residualGraph[i][j] - aktuelleflow;
                    //Eine backward Kante
                    residualGraph[j][i] = residualGraph[j][i] + aktuelleflow;
                }

                // Fügen Sie den Pfadfluss zum Gesamtfluss hinzu
                maximalflow += aktuelleflow;
            }
        }

        // Return den Gesamtfluss
        return maximalflow;
    }




    /**
     * Wandeln den Graph sowie jede Datei *.gka in eine adjazenzMatrix um
     * @param graph unser Graph
     * @return eine adjazenzMatrix
     */
    public static int[][] graphMatrix(Graph graph) {
        // Anzahl der Knoten
        int V = graph.getNodeCount();

        //Liste aller Knote
        List<Node> nodes = new ArrayList<>();

        // in Liste reinpacken
        for (Node node : graph.getEachNode()) {
            nodes.add(node);
        }

        // eine adjazenzMatrix
        int graphMatrix[][] = new int[V][V];

        // sammeln alle Kantegewichte ein, wenn nicht verbunden -> auf 0 setzen
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (nodes.get(i).hasEdgeToward(nodes.get(j))) {
                    graphMatrix[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");

                } else graphMatrix[i][j] = 0;
            }
        }

        return graphMatrix;
    }

//    public static void main (String[] args) throws java.lang.Exception {
//
//        Graph residualGraph = new MultiGraph("Residual Graph");
//
//        residualGraph.addNode("x1");
//        residualGraph.addNode("x2");
//        residualGraph.addNode("x3");
//        residualGraph.addNode("x4");
//        residualGraph.addNode("x5");
//        residualGraph.addNode("x6");
//        residualGraph.addNode("x7");
//
//        residualGraph.addEdge("x1x2", "x1", "x2", true);
//        residualGraph.getEdge("x1x2").setAttribute("ui.label", 9);
//
//        residualGraph.addEdge("x2x6", "x2", "x6", true);
//        residualGraph.getEdge("x2x6").setAttribute("ui.label", 3);
//
//        residualGraph.addEdge("x6x7", "x6", "x7", true);
//        residualGraph.getEdge("x6x7").setAttribute("ui.label", 6);
//
//        residualGraph.addEdge("x1x3", "x1", "x3", true);
//        residualGraph.getEdge("x1x3").setAttribute("ui.label", 4);
//
//        residualGraph.addEdge("x3x7", "x3", "x7", true);
//        residualGraph.getEdge("x3x7").setAttribute("ui.label", 7);
//
//        residualGraph.addEdge("x2x3", "x2", "x3", true);
//        residualGraph.getEdge("x2x3").setAttribute("ui.label", 4);
//
//        residualGraph.addEdge("x1x4", "x1", "x4", true);
//        residualGraph.getEdge("x1x4").setAttribute("ui.label", 8);
//
//        residualGraph.addEdge("x4x5", "x4", "x5", true);
//        residualGraph.getEdge("x4x5").setAttribute("ui.label", 5);
//
//        residualGraph.addEdge("x5x7", "x5", "x7", true);
//        residualGraph.getEdge("x5x7").setAttribute("ui.label", 2);
//
//        residualGraph.addEdge("x5x3", "x5", "x3", true);
//        residualGraph.getEdge("x5x3").setAttribute("ui.label", 3);
//
//        Node source = residualGraph.getNode("x1");
//        source.addAttribute("ui.style", "fill-color: red;");
//
//        Node target = residualGraph.getNode("x7");
//        target.addAttribute("ui.style", "fill-color: blue;");
//
//        residualGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));
//
//
//
//        int[][] arr = graphMatrix(residualGraph);
//
//        // Let us create a graph shown in the above example
//        //int graph[][] = residualCapacityMatrix;
//
//        FordFulkerson fordFulkerson = new FordFulkerson();
//
//        System.out.println("The maximum possible flow is " +
//                fordFulkerson.fordFulkerson(arr, residualGraph.getNode("x1"), residualGraph.getNode("x7")));
//
//        residualGraph.display();
//
//    }
}
