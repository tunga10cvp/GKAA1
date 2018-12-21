package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class FordFulkerson {

    public static int fordFulkerson(Graph graph, Node startNode, Node zielNode){

        int nummberOfNodes = graph.getNodeCount();
        List<Node> nodes = new ArrayList<>();

        int source = startNode.getIndex();

        int target = zielNode.getIndex();

        int[][] rGraph = new int[nummberOfNodes][nummberOfNodes];

        for (Node node : graph.getEachNode()){
            nodes.add(node);
        }

        for (int i = 0; i < nummberOfNodes; i++){
            for (int j = 0; j < nummberOfNodes; j++){
                if (nodes.get(i).hasEdgeToward(nodes.get(j))){
                    rGraph[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");
                }
            }
        }

        Map<Integer, Integer> parent = new HashMap<>();

        List<List<Integer>> augmentedPaths = new ArrayList<>();

        int maxFlow = 0;

        while (hasAugmentingPath(graph, parent, startNode, zielNode)){
            List<Integer> augmentedPath = new ArrayList<>();

            int flow = Integer.MAX_VALUE;

            int v = target;
            while (v != source){
                augmentedPath.add(v);
                int u = parent.get(v);

                if (flow > rGraph[u][v]){
                    flow = rGraph[u][v];
                }

                v = u;


            }

            augmentedPath.add(source);

            // reserve die augmentedPath
            Collections.reverse(augmentedPath);

            //add   to augmentedPaths
            augmentedPaths.add(augmentedPath);

            maxFlow += flow;

            v = target;

            while (v != source){
                int u = parent.get(v);
               // u.getEdgeToward(v).setAttribute("ui.label", Integer.valueOf(u.getEdgeToward(v).getAttribute("ui.label")) - flow);
               // v.getEdgeToward(u).setAttribute("ui.label", Integer.valueOf(u.getEdgeToward(v).getAttribute("ui.label")) + flow);
                rGraph[u][v] -= flow;
                rGraph[u][v] += flow;
                v = u;
            }

        }

        printAugmentedPaths(augmentedPaths);
        return maxFlow;
    }

    private static void printAugmentedPaths(List<List<Integer>> augmentedPaths) {
        System.out.println("Augmented paths");
        augmentedPaths.forEach(path -> {
            path.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

    public static boolean hasAugmentingPath(Graph graph,Map<Integer, Integer> parent, Node startNode, Node zielNode){

        int source = startNode.getIndex();

        int target = zielNode.getIndex();

        Set<Integer> visited = new HashSet<>();

       // Map<Node, Node> prev = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        int V = graph.getNodeCount();

        int nummberOfNodes = graph.getNodeCount();
        int[][] rGraph = new int[nummberOfNodes][nummberOfNodes];

        queue.add(source);
        visited.add(source);

        // AugmentedPath : duong tang luong
        boolean augmentedPath = false;

        //Gucken, ob wir einen erweiterten Pfad von der StartKnote zur ZielKnote finden können
        while (!queue.isEmpty()){
            int u = queue.poll();

            for (int v = 0; v < V; v++ ){

                if (!visited.contains(v) && rGraph[u][v] > 0){
                    parent.put(v, u);

                    visited.add(v);

                    queue.add(v);

                    if ( v == target){
                        augmentedPath = true;
                        break;

                    }
                }
            }
        }
//        List<Integer> path = new ArrayList<>();
//        Integer aktuell = zielNode.getIndex();
//        while (aktuell!= null){
//            path.add(0, aktuell);
//            aktuell = parent.get(aktuell);
//        }

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

        residualGraph.addEdge("x1x2", "x1", "x2", false);
        residualGraph.getEdge("x1x2").setAttribute("ui.label", 9);

        residualGraph.addEdge("x2x6", "x2", "x6", false);
        residualGraph.getEdge("x2x6").setAttribute("ui.label", 3);

        residualGraph.addEdge("x6x7", "x6", "x7", false);
        residualGraph.getEdge("x6x7").setAttribute("ui.label", 6);

        residualGraph.addEdge("x1x3", "x1", "x3", false);
        residualGraph.getEdge("x1x3").setAttribute("ui.label", 4);

        residualGraph.addEdge("x3x7", "x3", "x7", false);
        residualGraph.getEdge("x3x7").setAttribute("ui.label", 7);

        residualGraph.addEdge("x2x3", "x2", "x3", false);
        residualGraph.getEdge("x2x3").setAttribute("ui.label", 4);

        residualGraph.addEdge("x1x4", "x1", "x4", false);
        residualGraph.getEdge("x1x4").setAttribute("ui.label", 8);

        residualGraph.addEdge("x4x5", "x4", "x5", false);
        residualGraph.getEdge("x4x5").setAttribute("ui.label", 5);

        residualGraph.addEdge("x5x7", "x5", "x7", false);
        residualGraph.getEdge("x5x7").setAttribute("ui.label", 2);

        residualGraph.addEdge("x5x3", "x5", "x3", false);
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


        residualGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

        residualGraph.addAttribute("ui.stylesheet", styleSheet);

        //Boolean bfsReturn = hasAugmentingPath(residualGraph,residualGraph.getNode("x1"), residualGraph.getNode("x7"));

        FordFulkerson ff = new FordFulkerson();
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
