package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.LinkedList;
import java.util.List;


public class GraphAlgorithms {

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

        List<Node> bfsReturn = traverseWithBFS(testGraph, testGraph.getNode("A"), testGraph.getNode("G"));
        for (Node node : bfsReturn){
            node.addAttribute("ui.style", " fill-color: red;");
            //testGraph.addAttribute("ui.stylesheet", "edge { fill-color: red; }");


        }

        for(int i = 0; i < bfsReturn.size() - 1; i++){
            // System.out.println(bfsReturn.get(i)+bfsReturn.get(i+1));
            String node1 = String.valueOf(bfsReturn.get(i));
            String node2 = String.valueOf(bfsReturn.get(i+1));
            String edgeId = String.valueOf(i);

            testGraph.getEdge(node1 + node2).addAttribute("ui.style", " fill-color: red;");

        }
        //testGraph.getEdge().addAttribute("ui.style", " fill-color: red;");

        testGraph.display();

        //System.out.println(bfsReturn);
        //System.out.println(bfsReturn.get(0));
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

    /**
     * Method to traverse a graph and find the shortest path between a
     * first node and a second. If a Path is found, it is returned.
     * If not, null is returned with a message over console.
     *
     * @param graph         the graph to traverse
     * @param sourceNode    node to start searching from
     * @param targetNode    node to find path to
     * @return              returns a LinkedList<Node> with the path.
     *                      if no path found, returns null plus message
     */
    public static List<Node> traverseWithBFS(Graph graph, Node sourceNode, Node targetNode){
        List<Node> nodeList = new LinkedList<>();
        Node currentNode;

        //Add sourceNode to the queue and mark as visited
        ((LinkedList<Node>) nodeList).addLast(sourceNode);
        sourceNode.addAttribute("visited", sourceNode);


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
        System.out.println("no path found!");
        return null;
    }

    /**
     * Helper method to return a list with a path
     *
     * @param sourceNode    node to get back to
     * @param targetNode    node from which to iterate back over precursora
     * @return              a List with the path
     */
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
