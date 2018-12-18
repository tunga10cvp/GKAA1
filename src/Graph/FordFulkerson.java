package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class FordFulkerson {

//    public static double fordFulkerson(Graph graph, Node startNode, Node zielNode){
//
//        int nummberOfNodes = graph.getNodeCount();
//        List<Node> nodes = new ArrayList<>();
//
//        double[][] distance = new double[nummberOfNodes][nummberOfNodes];
//
//        for (Node node : graph.getEachNode()){
//            nodes.add(node);
//        }
//
//        for (int i = 0; i < nummberOfNodes; i++){
//            for (int j = 0; j < nummberOfNodes; j++){
//                if (nodes.get(i).equals(nodes.get(j))){
//                    distance[i][j] = 0.0;
//                }
//
//                else if (nodes.get(i).hasEdgeToward(nodes.get(j))){
//                    distance[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");
//                }
//            }
//        }
//
//        Map<Node, Node> prev = new HashMap<>();
//
//        List<List<Node>> augmentedPaths = new ArrayList<>();
//
//        double maxFlow = 0.0;
//
//        while (bfs(graph, prev, startNode, zielNode)){
//            List<Node> augmentedPath = new ArrayList<>();
//
//            Double flow = Double.POSITIVE_INFINITY;
//
//            Node v = zielNode;
//            while (v != startNode){
//                augmentedPath.add(v);
//                Node u = prev.get(v);
//
//                if (flow > Double.valueOf(u.getEdgeToward(v).getAttribute("ui.label"))){
//                    flow = Double.valueOf(u.getEdgeToward(v).getAttribute("ui.label"));
//                }
//
//                v = u;
//            }
//
//            augmentedPath.add(startNode);
//            Collections.reverse(augmentedPath);
//            augmentedPaths.add(augmentedPath);
//
//            maxFlow += flow;
//
//            v = zielNode;
//
//            while (v != startNode){
//                Node u = prev.get(v);
//                Double.valueOf(u.getEdgeToward(v).getAttribute("ui.label")) -= flow;
//                Double.valueOf(v.getEdgeToward(u).getAttribute("ui.label")) += flow;
//                v = u;
//            }
//        }
//        return maxFlow;
//    }

    public static boolean bfs(Graph graph,Map<Node, Node> prev,  Node startNode, Node zielNode){
        Set<Node> visited = new HashSet<>();

       // Map<Node, Node> prev = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        // AugmentedPath : duong tang luong
        boolean augmentedPath = false;

        while (!queue.isEmpty()){

            Node aktuell = queue.poll();

            for (Edge leavingEdge : aktuell.getEachLeavingEdge()){
                Node neighbor = leavingEdge.getNode1();

                if (!visited.contains(neighbor) && aktuell.hasEdgeToward(neighbor)){
                    prev.put(neighbor, aktuell);

                    visited.add(neighbor);

                    queue.add(neighbor);

                    if ( neighbor == zielNode){
                        augmentedPath = true;
                        break;

                    }
                }
            }
        }
        List<Node> path = new LinkedList<>();
        Node aktuell = zielNode;
        while (aktuell!= null){
            path.add(0, aktuell);
            aktuell = prev.get(aktuell);
        }

        return augmentedPath;

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


        residualGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

        residualGraph.addAttribute("ui.stylesheet", styleSheet);

        //Boolean bfsReturn = bfs(residualGraph,residualGraph.getNode("x1"), residualGraph.getNode("x7"));

        FordFulkerson ff = new FordFulkerson();
        //System.out.println(bfsReturn);
       // System.out.println(ff.fordFulkerson(residualGraph, residualGraph.getNode("A"), residualGraph.getNode("G")));
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
