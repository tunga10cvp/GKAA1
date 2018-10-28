package GraphTest;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphIORead2 {


    public static  graphLine(String line){
        GraphLine graphLine = new GraphLine();

        Graph toLoad = new MultiGraph("newLoadedGraph");

        FileReader file = new FileReader("graph01.gka");
        BufferedReader br = new BufferedReader(file);

        line = br.readLine();

        String regex = "";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()){
            if (graphLine.getNodeSource() == null){
                System.out.println("Zeile " + line + " hat keinen Graph");
            }
            if (line.contains("->")){
                if(matcher.group(1) != null){
                    graphLine.setNodeSource(matcher.group(1));
                }
                if(matcher.group(2) != null){
                    graphLine.setDirected(matcher.group(2));
                }
                if(matcher.group(3) != null){
                    graphLine.setNodeTarget(matcher.group(3));
                }
                if(matcher.group(4) != null){
                    graphLine.setWeight(Integer.parseInt(matcher.group(4));
                }
                if(matcher.group(5) != null){
                    graphLine.setName(matcher.group(5));
                }
            toLoad.addEdge(matcher.group(1) +  matcher.group(3), matcher.group(1),matcher.group(3));
            }

            else if (line.contains("--")){
                if(matcher.group(1) != null){
                    graphLine.setNodeSource(matcher.group(1));
                }
                if(matcher.group(3) != null){
                    graphLine.setNodeTarget(matcher.group(3));
                }
                if(matcher.group(4) != null){
                    graphLine.setWeight(Integer.parseInt(matcher.group(4));
                }
                if(matcher.group(5) != null){
                    graphLine.setName(matcher.group(5));
                }
                toLoad.addEdge(matcher.group(1) +  matcher.group(3), matcher.group(1),matcher.group(3));

            }

        }
        return graphLine;
    }

   // public Graph<String, Edge> addtoGraph(List≤GraphLine>  ){


}