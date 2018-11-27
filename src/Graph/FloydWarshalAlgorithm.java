package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FloydWarshalAlgorithm {

    public static long accessCounter = 0;

    /**
     * Calculates the shortest path from one node to another on a given graph using the Floyd Warshal algorithm and a transit-matrix
     * for backtracking of the respective path
     *
     * @param graph         graph to find path on
     * @param sourceNode    node to start a path from
     * @param targetNode    target node
     * @return              a path as a list of nodes, OR null if path is circle with negative length
     */
    public static List<Node> shortestPathsWithFloydWarshal(Graph graph, Node sourceNode, Node targetNode) {
        int numberOfNodes = graph.getNodeCount();
        List<Node> nodes = new ArrayList<>();
        List<Node> path = new LinkedList<>();
        double[][] distanceMatrix = new double[numberOfNodes][numberOfNodes];
        int[][] transitMatrix = new int[numberOfNodes][numberOfNodes];

        // source = target
        if (sourceNode == targetNode){
            path.add(sourceNode);
        }

        //get all Nodes in a list
        for (Node node : graph.getEachNode()) {
            nodes.add(node);
            accessCounter++;
        }

        //fill distance matrix initially
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                accessCounter++;
                if (nodes.get(i).equals(nodes.get(j)))

                    distanceMatrix[i][j] = 0.0;

                else if (nodes.get(i).hasEdgeToward(nodes.get(j)))
                    distanceMatrix[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");
                else
                    distanceMatrix[i][j] = Double.POSITIVE_INFINITY;

                //set transit matrix entry to -1 as transit end point
                transitMatrix[i][j] = -1;
            }
        }

        //calculate distances and transits
        for (int j = 0; j < numberOfNodes; j++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int k = 0; k < numberOfNodes; k++) {
                    accessCounter++;
                    if (i != j && k != j) {
                        if (distanceMatrix[i][j] + distanceMatrix[j][k] < distanceMatrix[i][k]) {
                            distanceMatrix[i][k] = Math.min(distanceMatrix[i][k], distanceMatrix[i][j] + distanceMatrix[j][k]);
                            transitMatrix[i][k] = j;
                        }
                    }
                    if(i == k && distanceMatrix[i][k] < 0) {
                        System.out.println("Kreis negativer Länge gefunden!");
                        return null;
                    }
                }
            }
        }

        //calculate the path from sourceNode to node before targetNode
        for (Integer indexInNodes : getPathFromTransit(nodes, transitMatrix, sourceNode, targetNode)) {
            path.add(nodes.get(indexInNodes));
        }
        //target node needs to be added manually because of recursion, but only if sourceNode != targetNode,
        //then getPathFromTransit returns only that node
        if (path.size() > 0 && sourceNode != targetNode)
            path.add(targetNode);

        System.out.println();
        return path;
    }

    /**
     * helper method for recursive calculation of the path
     *
     * @param nodes         all nodes in the graph
     * @param transitMatrix the calculated transit matrix from shortestPathsWithFloydWarshal
     * @param source        current source node
     * @param target        current target node
     * @return              path as list of nodes minus the target node
     */
    private static List<Integer> getPathFromTransit(List<Node> nodes, int[][] transitMatrix, Node source, Node target) {
        List<Integer> path = new LinkedList<>();
        if (transitMatrix[nodes.indexOf(source)][nodes.indexOf(target)] == -1) {
            if(source.hasEdgeToward(target))
                ((LinkedList<Integer>) path).add(nodes.indexOf(source));
            else if(source == target);
            else
                System.out.println("Kein Weg gefunden!");
        }
        else {
            ((LinkedList<Integer>) path).addAll(getPathFromTransit(nodes, transitMatrix, source, nodes.get(transitMatrix[nodes.indexOf(source)][nodes.indexOf(target)])));
            ((LinkedList<Integer>) path).addAll(getPathFromTransit(nodes, transitMatrix, nodes.get(transitMatrix[nodes.indexOf(source)][nodes.indexOf(target)]), target));
        }

        return path;
    }



/*
            //TEST:
        // AB HIER ALLES FRAGLICH!!!!!
        for(int i = 0;i < transitMatrix[0].length; i++) {
            System.out.println("");
            for (int j = 0; j < transitMatrix[0].length; j++)
                System.out.print(transitMatrix[i][j] + " ");
        }
        //-1 in transit matrix means no path or direct path between the two nodes
        if(transitMatrix[nodes.indexOf(sourceNode)][nodes.indexOf(targetNode)] == -1) {
            if(sourceNode.hasEdgeToward(targetNode)){
                ((LinkedList<Node>) path).addFirst(targetNode);
                ((LinkedList<Node>) path).addFirst(sourceNode);
            }
            else
                System.out.println("Kein Weg gefunden!");
        }
        else {
            *//*Node currentNode = targetNode;
            while (transitMatrix[nodes.indexOf(sourceNode)][nodes.indexOf(currentNode)] >= 0) {
                ((LinkedList<Node>) path).addFirst(currentNode);
                if (transitMatrix[nodes.indexOf(sourceNode)][nodes.indexOf(currentNode)] >= 0)
                    currentNode = nodes.get(transitMatrix[nodes.indexOf(sourceNode)][nodes.indexOf(targetNode)]);
            }
            ((LinkedList<Node>) path).addFirst(sourceNode);*//*

            //add source and target nodes
            ((LinkedList<Node>) path).addFirst(sourceNode);
            ((LinkedList<Node>) path).addLast(targetNode);


        }
    }*/
}
