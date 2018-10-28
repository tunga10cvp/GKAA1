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
    /**
     * Jede Zeilen von den Graphen(line) werden eingelesen
     * @param line sind die Zeile, die aus den Dateien eingelesen werden
     */

    public static  graphLine(String line){
        GraphLine graphLine = new GraphLine();

        Graph toLoad = new MultiGraph("newLoadedGraph");

        FileReader file = new FileReader("graph01.gka");
        BufferedReader br = new BufferedReader(file);

        line = br.readLine();

        String regex = "";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        //jede Zeile prüfen
        while (matcher.find()){

            //wenn die Zeile lerr ist
            if (graphLine.getNodeSource() == null){
                System.out.println("Zeile " + line + " hat keinen Graph");
            }

            //wenn die Zeile "->" hat, das heißt gerichter Graph
            if (line.contains("->")){
                //erste Knote wird in gruppe 1 gespeichert
                if(matcher.group(1) != null){
                    graphLine.setNodeSource(matcher.group(1));
                }
                // -> wird in gruppe 2 gespeichert
                if(matcher.group(2) != null){
                    graphLine.setDirected(matcher.group(2));
                }
                //zweite Konte wird in gruppe 3 gespeichert
                if(matcher.group(3) != null){
                    graphLine.setNodeTarget(matcher.group(3));
                }
                //edgegewicht wird in gruppe 4 gespeichert
                if(matcher.group(4) != null){
                    graphLine.setWeight(Integer.parseInt(matcher.group(4));
                }
                //name wird in gruppe 5 gespeichert
                if(matcher.group(5) != null){
                    graphLine.setName(matcher.group(5));
                }
                // adden die Zeile in den Graph
                toLoad.addEdge(matcher.group(1) +  matcher.group(3), matcher.group(1),matcher.group(3));
            }

            //wenn die Zeile "--" hat, das heißt ungerichter Graph
            else if (line.contains("--")){
                //erste Knote wird in gruppe 1 gespeichert
                if(matcher.group(1) != null){
                    graphLine.setNodeSource(matcher.group(1));
                }
                //zwite Knote wird in gruppe 3 gespeichert
                if(matcher.group(3) != null){
                    graphLine.setNodeTarget(matcher.group(3));
                }
                //Edgegewicht wird in gruppe 4 gespeichert
                if(matcher.group(4) != null){
                    graphLine.setWeight(Integer.parseInt(matcher.group(4));
                }
                //name wird in gruppe 5 gespeichert
                if(matcher.group(5) != null){
                    graphLine.setName(matcher.group(5));
                }
                // add die Zeile in den Graph
                toLoad.addEdge(matcher.group(1) +  matcher.group(3), matcher.group(1),matcher.group(3));

            }
            // wenn es einzelne Konte ist
            else {
                toLoad.addEdge(matcher.group(1), matcher.group(1), null);
            }

        }
        return graphLine;
        br.close();
    }



}