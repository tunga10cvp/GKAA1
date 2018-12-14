package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.*;

public class FordFulkerson {

    public static double fordFulkerson(Graph graph, Node startNode, Node zielNode){

        int nummberOfNodes = graph.getNodeCount();
        List<Node> nodes = new ArrayList<>();

        double[][] distance = new double[nummberOfNodes][nummberOfNodes];

        for (Node node : graph.getEachNode()){
            nodes.add(node);
        }

        for (int i = 0; i < nummberOfNodes; i++){
            for (int j = 0; j < nummberOfNodes; j++){
                if (nodes.get(i).equals(nodes.get(j))){
                    distance[i][j] = 0.0;
                }
                else if (nodes.get(i).hasEdgeToward(nodes.get(j))){
                    distance[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");
                }
                else distance[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        Map<Node, Node> prev = new HashMap<>();
        List<List<Node>> augmentedPaths = new ArrayList<>();

        double maxFlow = 0.0;

        while (bfs(graph, prev, startNode, zielNode)){
            List<Node> augmentedPath = new ArrayList<>();

            Double flow = Double.POSITIVE_INFINITY;

            Node v = zielNode;
            while (v != startNode){
                augmentedPath.add(v);
                Node u = prev.get(v);

                if (flow > Double.valueOf(u.getEdgeToward(v).getAttribute("ui.label"))){
                    flow = Double.valueOf(u.getEdgeToward(v).getAttribute("ui.label"));
                }

                v = u;
            }

            augmentedPath.add(startNode);
            Collections.reverse(augmentedPath);
            augmentedPaths.add(augmentedPath);

            maxFlow += flow;

            v = zielNode;

            while (v != startNode){
                Node u = prev.get(v);
                Double.valueOf(u.getEdgeToward(v).getAttribute("ui.label")) -= flow;
                Double.valueOf(v.getEdgeToward(u).getAttribute("ui.label")) += flow;
                v = u;
            }
        }
        return maxFlow;
    }

    public static boolean bfs(Graph graph, Map<Node, Node> prev, Node startNode, Node zielNode){
        Set<Node> visited = new HashSet<>();

        Queue<Node> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        boolean found = false;

        while (!queue.isEmpty()){

            Node aktuell = queue.poll();

            for (Edge leavingEdge : aktuell.getEachLeavingEdge()){
                Node neighbor = leavingEdge.getNode1();

                if (!visited.contains(neighbor) && aktuell.hasEdgeToward(neighbor)){
                    prev.put(neighbor, aktuell);

                    visited.add(neighbor);

                    queue.add(neighbor);

                    if ( neighbor == zielNode){
                        found = true;
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

        return found;

    }

    public static void main(String[] args){
        Graph testGraph = new MultiGraph("testGraph");
        testGraph.addNode("A");
        testGraph.addNode("B");
        testGraph.addNode("C");
        testGraph.addNode("D");
        // testGraph.addNode("E");
        testGraph.addNode("F");
        testGraph.addNode("G");

        testGraph.addEdge("AB","A", "B", true);
        testGraph.getEdge("AB").setAttribute("weight", 1);

        testGraph.addEdge("AC","A", "C", true);
        testGraph.getEdge("AC").setAttribute("weight", 2);

        testGraph.addEdge("CD","B", "D", true);
        testGraph.getEdge("CD").setAttribute("weight", 1);

        testGraph.addEdge("BD","C", "D", true);
        testGraph.getEdge("BD").setAttribute("weight", 1);

        testGraph.addEdge("AD","A", "D", true);
        testGraph.getEdge("AD").setAttribute("weight", 1);

        testGraph.addEdge("AF","A", "F", true);
        testGraph.getEdge("AF").setAttribute("weight", 1);

        testGraph.addEdge("FG","F", "G", true);
        testGraph.getEdge("FG").setAttribute("weight", 1);

        testGraph.addEdge("GD","G", "D", true);
        testGraph.getEdge("GD").setAttribute("weight", 1);

        testGraph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

      // Boolean bfsReturn = bfs(testGraph,testGraph.getNode("A"), testGraph.getNode("G"));

        FordFulkerson ff = new FordFulkerson();
        //System.out.println(bfsReturn);
        System.out.println(ff.fordFulkerson(testGraph, testGraph.getNode("A"), testGraph.getNode("G")));
        testGraph.display();
    }


}
