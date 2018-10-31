package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static void main(String[] args) throws IOException
    {

        fileParse();

    }

    public static void fileParse() throws IOException {

        //
        Graph graph = new MultiGraph("newLoadedGraph");

        FileReader file = new FileReader("gka-Dateien/graph03.gka");
        BufferedReader br = new BufferedReader(file);

        String line;
        //jede Zeile einlesen
        while(( line = br.readLine()) != null) {

            // löscht ; für alle Zeilen
            line = line.replace(";","");

            // prüf ob das -> gibt
            if (line.contains("->")) {

                // split ->
                String[] nodes = line.split(" -> ");

                // prüf ob der Knote1 vorhanden ist
                if (graph.getNode(nodes[0]) == null) {
                    graph.addNode(nodes[0]);
                }

                // prüf ob der Knote2 vorhanden ist
                if (graph.getNode(nodes[1]) == null) {
                    if (nodes[1].contains(":")) {
                        String[] nodes1 = nodes[1].split(" : ");
                        if (graph.getNode(nodes1[0]) == null) {
                            // System.out.println(nodes1[0]);
                            graph.addNode(nodes1[0]);
                            graph.addEdge(nodes[0] + nodes1[0], nodes[0], nodes1[0], true).setAttribute("weight", nodes1[1]);
                        }
                    }
                    else {
                        graph.addNode(nodes[1]);
                        graph.addEdge(nodes[0] + nodes[1],nodes[0],nodes[1],true);
                    }
                }
                // add to Graph
                graph.addEdge(nodes[0] + nodes[1],nodes[0],nodes[1],true);
            }

            if (line.contains("--")) {
                String[] nodes = line.split(" -- ");

                if (graph.getNode(nodes[0]) == null) {

                    graph.addNode(nodes[0]);
                }
                if (graph.getNode(nodes[1]) == null) {
                    if (nodes[1].contains(":")) {
                        String[] nodes1 = nodes[1].split(" : ");
                        if (graph.getNode(nodes1[0]) == null) {
                            // System.out.println(nodes1[0]);
                            graph.addNode(nodes1[0]);
                            graph.addEdge(nodes[0] + nodes1[0], nodes[0], nodes1[0], false).setAttribute("weight", nodes1[1]);
                        }
                    }
                    else {
                        graph.addNode(nodes[1]);
                        graph.addEdge(nodes[0] + nodes[1],nodes[0],nodes[1],false);
                    }
                }


            }

            // einzelner Knote
            if (line.length() == 1){
                graph.addNode(line);
            }

            System.out.println(line);

        }
        graph.display();
    }




}