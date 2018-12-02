package Graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasse ReadFile fürs Einlesen einer Datei und zeichne den Graphen
 * Die ReadFile liest jeder Zeile der Dateien und sortiert in die Liste mit Hilfe der Regulär Ausdrücke
 *
 *
 * DIRECTED
 * <name node1>[ -> <name node2>][(edge name)][: <edgeweight>];
 * UNDIRECTED
 * <name node1>[ -- <name node2>][(edge name)][: <edgeweight>];
 *
 * @author Ngo Thanh Tung PC
 *
 */
public class ReadFile {

    public static void main(final String[] args) {
        String filename = "gka-Dateien/graph03.gka";
        Graph graph = readFile(filename);
        graph.display();

    }

    /**
     Liest jeder Zeile der Datei mit Hilfe der Regulär Ausdrücke
     **/
    public static Graph readFile(String filename) {
        String filePath = new String(filename);
        File file = new File(filePath);

        String line;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            //erstellt ein Graph
            Graph graph = new MultiGraph("GraphStream");

            //liest jeder Zeile ein
            while ((line = br.readLine()) != null) {

                //regex formatiert jeder Zeile
                String regex =
                        "^([\\wÄäÖöÜüß]+)(?:\\s*(->|--)\\s*([\\wÄäÖöÜüß]+)\\s*(?::\\s*((?:-)*\\d+)\\s*)?)?\\s*(?:\\(\\s*([\\wÄäÖöÜüß]+)\\s*\\)\\s*)?;";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                //Lese und zeichne Graphen
                showGraph(graph, matcher);
            }

           return graph;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param graph
     * @param matcher
     */
    private static void showGraph(Graph graph, Matcher matcher) {
        //suche alle Teile of Dateien


        while (matcher.find()) {
            //Auslesen der einzelnen Gruppen
            final String node1 = matcher.group(1);
            final String edge = matcher.group(2);
            final String node2 = matcher.group(3);
            final String edgeName = matcher.group(5);
            final String weight = matcher.group(4);

            // ID für jede Kante mit Random in String
            String idEdge = edgeID();
            // drück jede Zeile in verschiedenen Gruppen nach dem Sortieren aus
//            System.out.println("Zeile Nr: " + graph.getEdgeCount());
//            System.out.println("----------------------------");
//            for (int i = 0; i <= matcher.groupCount(); i++) {
//                System.out.println("Group " + i + ": " + matcher.group(i));
//            }

            //System.out.println(edgeID());

             // System.out.println("Die ID-Kante: " + edgeID());

            // Knoten 1 hinzufügen, wenn:
            // - es nicht null ist
            // - es kein Leerstring ist
            // - der Knoten nicht schon in dem Graphen ist
            if (node1 != null &&
                    !"".equals(node1) &&
                    graph.getNode(node1) == null) {
                graph.addNode(node1);
            }

            // Knoten 2 hinzufügen, wenn:
            // - es nicht null ist
            // - es kein Leerstring ist
            // - der Knoten nicht schon in dem Graphen ist
            if (node2 != null &&
                    !"".equals(node2) &&
                    graph.getNode(node2) == null) {
                graph.addNode(node2);


            }
            // Kante hinzufügen, wenn:
            // - es nicht null ist
            // - es kein Leerstring ist
            // - die EdgeID nicht vorhanden ist
            // - der zweite Knoten nicht null ist
            if (edge != null &&
                    !"".equals(edge) &&
//                    graph.getEdge(node1 + node2) == null &&
//                    graph.getEdge(node2 + node1) == null  &&
                    graph.getEdge(idEdge) == null &&
                    graph.getNode(node2) != null) {


                graph.addEdge(
                        idEdge,
                        node1,
                        node2,
                        edge.equals("->")
                );

                // Gewicht hinzufügen, wenn:
                // - es nicht null ist
                // - es kein Leerstring ist
                if (weight != null &&
                        !"".equals(weight)) {
                    graph.getEdge(idEdge).addAttribute("ui.label", Double.valueOf(weight));
                    //graph.addAttribute("ui.label", graph.getEdge(idEdge));
                }

                // Name hinzufügen, wenn:
                // - es nicht null ist
                // - es kein Leerstring ist
                if (edgeName != null &&
                        !"".equals(edgeName)) {
                    graph.getEdge(idEdge).addAttribute("name", edgeName);
                }


            }

            //Anzahl der Knoten
          //  System.out.println("Anzahl der Knoten: " + graph.getNodeCount());

            // Anzahl der Kanaten
           //  System.out.println("Anzahl der Kanten: " + graph.getEdgeCount());


        }
        //Node Namen
        graph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

//        for (Edge edge : graph.getEachEdge()){
//            System.out.println(edge.getSourceNode() + edge.getAttribute("ui.label").toString());
//        }



        // Viewer für die Graphen
        graph.addAttribute("ui.stylesheet", styleSheet);
        //graph.addAttribute("ui.stylesheet", "graph { fill-color: yellow; }");
        graph.addAttribute("ui.quality");

        graph.addAttribute("ui.antialias");
       // graph.addAttribute("weight", "edge.marked { text-color: yellow; }" );
        // graph.addAttribute("ui.screenshot", "heart.jpg");


    }

    //erstellt EdgeID
    public static String edgeID() {
        return UUID.randomUUID().toString();
    }

    public static void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep(100);
        }
    }

    protected static void sleep(int i) {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }


    // Aussehen für die Graphen
    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;"+
                    "}" +

                    "node {" +
                    "   text-color: yellow;" +
                    "   shape: line;" +
                    "   text-size: 25px;" +
                    "   shape: circle;" +
                    "   fill-color: green;" +
                    "   size: 20px;" +
                  //  "   text-alignment: center;" +
                  //  "   fill-mode: gradient-radial;" +
                   // "   stroke-color: yellow;" +

                    "}" +
                    "node.marked {" +
                    "	fill-color: green;" +
                    "}" +

                    " edge {" +
                    "shape: angle;" +
                    " stroke-mode: plain;"+
                    "text-color: white;"+
                    "text-size: 15px;"+
                    "fill-color: blue;"+
                    "text-background-mode: rounded-box;"+
                    "text-background-color: red;"+
                    "}"+

                    "edge.marked {"+
                    " fill-color: red;"+
                    " text-color: red;"+
                    "}";


}
