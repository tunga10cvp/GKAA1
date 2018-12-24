package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class FordFulkerson {


    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */

    /**
     *  return true wenn es ein path von startNode nach zielNode gibt
     *
     * @param rGraph ein Graph in Matrix
     * @param startNode startNode
     * @param zielNode zielNode
     * @param parent Vorgänger
     * @return
     */
    boolean augumentPath(int rGraph[][], Node startNode, Node zielNode, int parent[])
    {

        int s = startNode.getIndex();
        int t = zielNode.getIndex();

        // erstellt ein Visited Array und markiert alle Knoten als nicht visited
        boolean visited[] = new boolean[rGraph.length];
        for(int i=0; i< rGraph.length; ++i)
            visited[i]=false;


        // erstellt eine Liste, fügt startNode hinzu und markiert als visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Wenn die Liste nicht leer ist

        while (queue.size()!=0) {

            int u = queue.poll();

            for (int v=0; v < rGraph.length; v++) {

                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], Node startNode, Node zielNode)
    {

        int u, v;

        int s = startNode.getIndex();
        int t = zielNode.getIndex();
        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[graph.length][graph.length];

        for (u = 0; u < rGraph.length; u++) {
            for (v = 0; v < rGraph.length; v++){
                rGraph[u][v] = graph[u][v];
                //System.out.println(rGraph[u][v]);
            }

        }
        // This array is filled by BFS and to store path
        int parent[] = new int[rGraph.length];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (augumentPath(rGraph, startNode, zielNode, parent))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }


    public static int[][] graphMatrix(Graph graph){
        int V = graph.getNodeCount();

        List<Node> nodes = new ArrayList<>();

        for (Node node : graph.getEachNode()){
            nodes.add(node);
        }

        int graphMatrix[][] = new int[V][V];

        for (int i = 0; i < V; i++){
            for (int j = 0; j < V; j++){
                if (nodes.get(i).hasEdgeToward(nodes.get(j))){
                    graphMatrix[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");

                }
                else graphMatrix[i][j] = 0;
            }
        }

        return graphMatrix;
    }

    public static void main (String[] args) throws java.lang.Exception
    {

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



        int[][] arr = graphMatrix(residualGraph);

        // Let us create a graph shown in the above example
        //int graph[][] = residualCapacityMatrix;

        FordFulkerson m = new FordFulkerson();

        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(arr, residualGraph.getNode("x1"), residualGraph.getNode("x7")));

        residualGraph.display();

    }
}
