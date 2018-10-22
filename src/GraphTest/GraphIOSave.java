package GraphTest;

import java.io.*;
import java.io.IOException;

import org.graphstream.graph.*;

public class GraphIOSave {

    /**
     * makes a new file and formats a Graph-object to the following form:
     *
     * DIRECTED
     * <name node1>[ -> <name node2>][(edge name)][: <edgeweight>];
     *
     * UNDIRECTED
     * <name node1>[ -- <name node2>][(edge name)][: <edgeweight>];
     *
     * @param graphToSave   The graph to be saved in a file
     * @param filename      The filename for the output file to be saved in the project path
     * @throws IOException  When something goes wrong in the file access etc.
     */
    public static void saveGraph(Graph graphToSave, String filename) throws IOException {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {

            String componentString;

            for (Edge currentEdge : graphToSave.getEachEdge()) {

                //reset the componentString
                componentString = "";

                //add the source-node to the string
                componentString += currentEdge.getNode0();

                if(currentEdge.isDirected()) {
                        componentString += " -> " + currentEdge.getNode1();
                }
                else{
                        componentString += " -- " + currentEdge.getNode1();
                }
                //there is always a name for each edge in GraphStream, so add that
                componentString += " (" + currentEdge.getId() + ")";

                //weight is an extra attribute in Graphstream, so check for that and add it
                if(currentEdge.hasAttribute("weight"))
                    componentString += ": " + currentEdge.getAttribute("weight");

                componentString += ";\n";
                writer.write(componentString);
            }

            for(Node currentNode: graphToSave.getEachNode()){

                //reset the componentString
                componentString = "";

                if(currentNode.getDegree() == 0){
                    componentString += currentNode.getId();
                    componentString += "(" + currentNode.getId() + ")";
                    componentString += ";\n";
                }

                writer.write(componentString);
            }
        }

    }
}
