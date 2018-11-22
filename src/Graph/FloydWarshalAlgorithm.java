package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FloydWarshalAlgorithm {

    public static List<Node> shortestPathsWithFloydWarshal(Graph graph, Node sourceNode, Node targetNode) {
        int numberOfNodes = graph.getNodeCount();
        List<Node> nodes = new ArrayList<>();
        List<Node> path = new LinkedList<>();
        double[][] distanceMatrix = new double[numberOfNodes][numberOfNodes];
        int[][] transitMatrix = new int[numberOfNodes][numberOfNodes];

        //get all Nodes in a list
        for (Node node : graph.getEachNode()) {
            nodes.add(node);
        }

        //fill distance matrix initially
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (nodes.get(i).equals(nodes.get(j)))
                    distanceMatrix[i][j] = 0.0;
                else if (nodes.get(i).hasEdgeToward(nodes.get(j)))
                    distanceMatrix[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("weight");
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
                    if (i != j && k != j) {
                        if (distanceMatrix[i][j] + distanceMatrix[j][k] < distanceMatrix[i][k]) {
                            distanceMatrix[i][k] = Math.min(distanceMatrix[i][k], distanceMatrix[i][j] + distanceMatrix[j][k]);
                            transitMatrix[i][k] = j;
                        }
                    }
                }
            }
        }

        //calculate the path from source to target
        for (Integer indexInNodes : getPathFromTransit(nodes, transitMatrix, sourceNode, targetNode)) {
            path.add(nodes.get(indexInNodes));
        }
        if (path.size() > 1)
            path.add(targetNode);

        return path;
    }

    private static List<Integer> getPathFromTransit(List<Node> nodes, int[][] transitMatrix, Node source, Node target) {
        List<Integer> path = new LinkedList<>();
        if (transitMatrix[nodes.indexOf(source)][nodes.indexOf(target)] == -1) {
            if(source == target || source.hasEdgeToward(target))
                ((LinkedList<Integer>) path).add(nodes.indexOf(source));
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
