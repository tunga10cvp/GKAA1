package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class EdmondsKarpAlgorithm {

    /**
     * Finds the maximum flow in a given graph with given source and sink via the
     * Edmonds Karp implementation (BFS) of the Ford Fulkerson Method.
     * <p>
     * The algorithm works not directly on the graph, but on a
     * mathematical representation of the flow diagram (residual graph matrix).
     *
     * @param graph
     * @param sourceNode
     * @param sinkNode
     * @return max flow between source and sink
     */
    public static int edmondsKarp(Graph graph, Node sourceNode, Node sinkNode) {

        int maxFlow = 0;

        // no flow if source = sink
        if (sourceNode == sinkNode) {
            return maxFlow;
        }

        // initialize residual graph matrix
        int[][] residualGraphMatrix = createResidualGraphMatrix(graph);

        /**
         * ????????
         */

        int source = sourceNode.getIndex();
        int sink = sinkNode.getIndex();
        int i, j;

        // array to store path for augmentingPathBFS
        int prev[] = new int[residualGraphMatrix.length];


        // find all augmenting paths via BFS
        while (augmentingPathBFS(residualGraphMatrix, source, sink, prev)) {

            int currentFlow = Integer.MAX_VALUE;

            // find maximum possible additional flow over augmenting path
            for (j = sink; j != source; j = prev[j]) {
                i = prev[j];
                currentFlow = Math.min(currentFlow, residualGraphMatrix[i][j]);
            }
            // update residual graph matrix
            for (j = sink; j != source; j = prev[j]) {
                i = prev[j];

                // forward -> reduce flow potential
                residualGraphMatrix[i][j] -= currentFlow;
                // backward -> increase flow potential
                residualGraphMatrix[j][i] += currentFlow;
            }

            // add flow of current augmenting Path to max flow
            maxFlow += currentFlow;
        }

        return maxFlow;
    }

    /**
     * generates a residual graph from a graphstream-graph
     *
     * @param graph
     * @return      residual graph as 2 dimensional int-array
     */
    private static int[][] createResidualGraphMatrix(Graph graph) {

        int V = graph.getNodeCount();
        int residualGraphMatrix[][] = new int[V][V];
        List<Node> nodes = new ArrayList<>();

        /**
         * ????????
         */
        for (Node node : graph.getEachNode()) {
            nodes.add(node);
        }
        // Add up all edge's weights from nodes i to j in the matrix, if not connected -> 0
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                for (Edge leavingEdge : nodes.get(i).getEachLeavingEdge()) {
                    if (leavingEdge.getNode1().equals(nodes.get(j))) {
                        if (residualGraphMatrix[i][j] > 0)
                            residualGraphMatrix[i][j] += (int)leavingEdge.getAttribute("ui.label");
                        else
                            residualGraphMatrix[i][j] = (int)leavingEdge.getAttribute("ui.label");
                    }

                }
            }
        }
        return residualGraphMatrix;
    }

    /**
     * finds out if there is still a possible augmenting path via a residual graph as a matrix.
     * the path is saved via prev array
     * Runtime: E²
     *
     * @param residualGraphMatrix contains flow potential between all nodes
     * @param source
     * @param sink
     * @param prev                array; index of current node -> index of previous node
     * @return whether path to sink was found
     */
    private static boolean augmentingPathBFS(int residualGraphMatrix[][], int source, int sink, int prev[]) {

        boolean visited[] = new boolean[residualGraphMatrix.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // marks all nodes as not visited
        for (int i = 0; i < residualGraphMatrix.length; ++i) {
            visited[i] = false;
        }

        // add source to queue as visited and no prev (-1)
        queue.add(source);
        visited[source] = true;
        prev[source] = -1;

        // search while sink was not reached and still nodes in queue
        while (queue.size() > 0 && !visited[sink]) {

            int current = queue.poll();

            // adds all unvisited neighbors with remaining potential to queue and sets them as visited with current as prev
            for (int potentialNeighbor = 0; potentialNeighbor < residualGraphMatrix.length; potentialNeighbor++) {

                if (!visited[potentialNeighbor] && residualGraphMatrix[current][potentialNeighbor] > 0) {
                    queue.add(potentialNeighbor);
                    prev[potentialNeighbor] = current;
                    visited[potentialNeighbor] = true;
                }
            }
        }

        // returns whether sink was visited via augmenting path
        return (visited[sink]);
    }

    public static void main (String[] args) throws java.lang.Exception {

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


        // Let us create a graph shown in the above example
        //int graph[][] = residualCapacityMatrix;

        EdmondsKarpAlgorithm edmondsKarpAlgorithm = new EdmondsKarpAlgorithm();

        System.out.println("The maximum possible flow is " +
                edmondsKarpAlgorithm.edmondsKarp(residualGraph, residualGraph.getNode("x1"), residualGraph.getNode("x7")));

        residualGraph.display();

    }
}

