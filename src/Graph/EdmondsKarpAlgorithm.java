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

        for (Node node : graph.getEachNode()) {
            nodes.add(node);
        }

        // Add up all edge's weights from nodes i to j in the matrix, if not connected -> 0
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                for (Edge leavingEdge : nodes.get(i).getEachLeavingEdge()) {
                    if (nodes.get(i).hasEdgeToward(nodes.get(j))) {
                        if (residualGraphMatrix[i][j] > 0)
                            residualGraphMatrix[i][j] += (int)leavingEdge.getAttribute("ui.label");
                        else
                            residualGraphMatrix[i][j] = (int)leavingEdge.getAttribute("ui.label");
                    }
                    else
                        residualGraphMatrix[i][j] = 0;
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

                if (!visited[potentialNeighbor]) {
                    int forwardPotential = residualGraphMatrix[current][potentialNeighbor];
                    int backwardPotential = residualGraphMatrix[potentialNeighbor][current];

                    if (forwardPotential > 0 || backwardPotential > 0) {
                        queue.add(potentialNeighbor);
                        prev[potentialNeighbor] = current;
                        visited[potentialNeighbor] = true;
                    }
                }
            }
        }

        // returns whether sink was visited via augmenting path
        return (visited[sink]);
    }
}

