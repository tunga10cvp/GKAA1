package GraphTest;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.*;

public class GraphIORead {
    public static void main(String[] args) throws IOException
    {
        FileReader file = new FileReader("graph01.gka");
        BufferedReader br = new BufferedReader(file);

        Graph toLoad = new MultiGraph("newLoadedGraph");
        String zeile;


        while( (zeile = br.readLine()) != null )
        {
            //String graph = new String(zeile.getBytes(),"UTF-8");
            String[] currentComponents = zeile.split(";");

            for(String component : currentComponents){
                if(component.contains("--")) {
                    String[] extractedNodes = component.split("--");
                    toLoad.addEdge(extractedNodes[0] + extractedNodes[1], extractedNodes[0], extractedNodes[1]);


                }
                else if(component.contains("->")){
                    String[] extractedNodes = component.split("->");
                    toLoad.addEdge(extractedNodes[0] + extractedNodes[1], extractedNodes[0], extractedNodes[1]);

                }
            }
            System.out.println(zeile);
        }

        br.close();
    }




}