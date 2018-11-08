package Graph;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphAlgorithms {

    public static void main(String[] args){
        Graph testGraph = new MultiGraph("testGraph");
        testGraph.addNode("A");
        testGraph.addNode("B");
        testGraph.addNode("C");
        testGraph.addNode("D");
        testGraph.addNode("E");
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

        List<Node> bfsReturn = traverseWithBFS(testGraph, testGraph.getNode("A"), testGraph.getNode("D"));

        System.out.println(bfsReturn);
    }

    /*public static List<Node> traverseWithBFS(Graph graph, Node sourceNode, Node targetNode){
        List<Node> nodeList = new LinkedList<>();
        boolean pathFound = false;
        Node currentNode;

        //Add sourceNode to the queue
        sourceNode.addAttribute("pathLength", 0);
        ((LinkedList<Node>) nodeList).addFirst(sourceNode);


        while(pathFound == false){
            //get next element from the queue
            currentNode = ((LinkedList<Node>) nodeList).removeFirst();

            //set currentNode to visited and save it's neighbors. Save the current node as their precursor
            for(Edge leavingEdge : currentNode.getEachLeavingEdge()){
                Node adjacentNode = leavingEdge.getNode1();
                int adjacentLength = ((int)currentNode.getAttribute("pathLength")) + 1;

                //Is adjacentNode already in queue and has longer path logged?
                //->path from currentNode is shorter
                //->Set adjacentNode's precursor to currentNode
                //->Set adjacentNode's pathLength to adjacentLength
                if(adjacentNode.hasAttribute("precursor")
                        && ((int)adjacentNode.getAttribute("pathLength")) > adjacentLength) {
                    adjacentNode.setAttribute("precursor", currentNode);
                    adjacentNode.setAttribute("pathLength", adjacentLength);
                }
                //if adjacentNode is unvisited (has no precursor) it gets added to queue
                else if (!adjacentNode.hasAttribute("precursor")){
                    adjacentNode.setAttribute("precursor", currentNode);
                    adjacentNode.setAttribute("pathLength", adjacentLength);
                    ((LinkedList<Node>) nodeList).addLast(adjacentNode);

                    //unvisited adjacentnode is targetNode menas shortest path found
                    if(adjacentNode.equals(targetNode))
                        pathFound = true;
                }
            }
        }

        //clear the nodeList for saving of shortest path
        nodeList.clear();

        for(currentNode = targetNode;!currentNode.equals(sourceNode); currentNode = currentNode.getAttribute("precursor")){
            ((LinkedList<Node>)nodeList).addFirst(currentNode);
        }
        ((LinkedList<Node>)nodeList).addFirst(currentNode);

        return nodeList;
    }*/

    public static List<Node> traverseWithBFS(Graph graph, Node sourceNode, Node targetNode){
        List<Node> nodeList = new LinkedList<>();
        Node currentNode;

        //Add sourceNode to the queue and mark as visited
        ((LinkedList<Node>) nodeList).addLast(sourceNode);
        sourceNode.addAttribute("visited", null);


        while(!nodeList.isEmpty()){
            //get next element from the queue
            currentNode = ((LinkedList<Node>) nodeList).removeFirst();

            //if targetNode is reached, a path is found
            if(currentNode == targetNode)
                return pathAsList(sourceNode, targetNode);

            //Save the current node as their precursor if adjacentNode was not visited before
            for(Edge leavingEdge : currentNode.getEachLeavingEdge()){
                Node adjacentNode = leavingEdge.getNode1();

                if(!adjacentNode.hasAttribute("visited")) {
                    adjacentNode.setAttribute("visited", currentNode);
                    ((LinkedList<Node>) nodeList).addLast(adjacentNode);
                }

            }
        }

        //if the list is empty, there is no path
        System.out.println("Kein Weg gefunden!");
        return null;
    }

    private static List<Node> pathAsList(Node sourceNode, Node targetNode){
        List<Node> path = new LinkedList<>();
        Node currentNode;

        //use the list for saving of the path, beginning from the targetNode
        for(currentNode = targetNode;!currentNode.equals(sourceNode); currentNode = currentNode.getAttribute("visited")){
            ((LinkedList<Node>)path).addFirst(currentNode);
        }
        ((LinkedList<Node>)path).addFirst(currentNode);

        return path;
    }
}
