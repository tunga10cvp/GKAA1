package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FordFulkerson2 {
       //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    public static boolean hasAugmentingPath(Graph graph, Node startNode, Node zielNode, int parent[]) {

        int s = startNode.getIndex();
        int t = zielNode.getIndex();
        int V = graph.getNodeCount();
        //int parent[] = new int[V];

        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; ++i) {
            visited[i] = false;
        }

        List<Node> nodes = new ArrayList<>();

        int[][] rGraph = new int[V][V];

        for (Node node : graph.getEachNode()){
            nodes.add(node);
        }


        for (int u = 0; u < V; u++){
            for (int v = 0; v < V; v++){
                if (nodes.get(u).hasEdgeToward(nodes.get(v))){
                    rGraph[u][v] = nodes.get(u).getEdgeToward(nodes.get(v)).getAttribute("ui.label");
                }
            }
        }

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<V; v++)
            {
                //rGraph[u][v] = nodes.get(u).getEdgeToward(nodes.get(v)).getAttribute("ui.label");
                if (visited[v] == false && rGraph[u][v] > 0)
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
    public static int fordFulkerson(Graph graph, Node startNode, Node zielNode) {

        int s = startNode.getIndex();
        int t = zielNode.getIndex();
        int V = graph.getNodeCount();

        List<Node> nodes = new ArrayList<>();

        int[][] rGraph = new int[V][V];

        for (Node node : graph.getEachNode()){
            nodes.add(node);
        }


        int u,v ;

        for (u = 0; u < V; u++){
            for (v = 0; v < V; v++){
                if (nodes.get(u).hasEdgeToward(nodes.get(v))){
                    rGraph[u][v] = nodes.get(u).getEdgeToward(nodes.get(v)).getAttribute("ui.label");
                    //System.out.println(rGraph[u][v]);
                }
            }
        }


        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (hasAugmentingPath(graph, startNode, zielNode, parent))
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
            //System.out.println(max_flow);
        }

        // Return the overall flow
        return max_flow;
    }

    public static void main(String[] args){
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

//        List<Node> listNode = new ArrayList<>();
//        listNode.add(residualGraph.getNode("x1"));
//        listNode.add(residualGraph.getNode("x2"));
//        listNode.add(residualGraph.getNode("x3"));
//        listNode.add(residualGraph.getNode("x4"));
//        listNode.add(residualGraph.getNode("x5"));
//
//        Collections.reverse(listNode);
//
//        System.out.println(listNode);

//        int startNode = Integer.valueOf(residualGraph.getNode("x1").getId());
        System.out.println(residualGraph.getNode("x1").getIndex());
        System.out.println(residualGraph.getNodeCount());

        List<Node> nodes = new ArrayList<>();
        for (Node node : residualGraph.getEachNode()){
            nodes.add(node);
        }

        System.out.println(nodes);
        System.out.println(residualGraph.getEdgeCount());


        residualGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

        residualGraph.addAttribute("ui.stylesheet", styleSheet);

        //Boolean bfsReturn = hasAugmentingPath(residualGraph,residualGraph.getNode("x1"), residualGraph.getNode("x7"));

         FordFulkerson2 ff = new FordFulkerson2();
        //System.out.println(bfsReturn);
         System.out.println(ff.fordFulkerson(residualGraph, residualGraph.getNode("x1"), residualGraph.getNode("x7")));
        residualGraph.display();

    }

    // Aussehen für die Graphen
    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;"+
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
                    " stroke-mode: plain;"+
                    "text-color: white;"+
                    "text-size: 20px;"+
                    "fill-color: blue;"+
                    "text-background-mode: rounded-box;"+
                    "text-background-color: red;"+
                    "}"+

                    "edge.marked {"+
                    " fill-color: red;"+
                    " text-color: red;"+
                    "}";


}
