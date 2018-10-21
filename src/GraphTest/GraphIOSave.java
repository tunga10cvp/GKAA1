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

            for (Edge currentEdge : graphToSave.getEachEdge()) {
                String edgeString = "";

                //add the source-node to the string
                edgeString += currentEdge.getNode0();

                if(currentEdge.isDirected()) {
                    //add the target-node only if it's a different node
                    if(currentEdge.getNode1() != currentEdge.getNode0()){
                        edgeString += " -> " + currentEdge.getNode1();
                    }
                }
                else{
                    //add the target-node only if it's a different node
                    if(currentEdge.getNode1() != currentEdge.getNode0()){
                        edgeString += " -- " + currentEdge.getNode1();
                    }
                }
                //there is always a name for each edge in GraphStream, so add that
                edgeString += " (" + currentEdge.getId() + ")";

                //weight is an extra attribute in Graphstream, so check for that and add it
                if(currentEdge.hasAttribute("weight"))
                    edgeString += ": " + currentEdge.getAttribute("weight");

                edgeString += ";\n";
                writer.write(edgeString);
            }
        }

    }
}
