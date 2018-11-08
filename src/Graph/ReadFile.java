package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.*;

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
        String filename = "graph03.gka";
        readFile(filename);
    }

    /**

    **/
    private static void readFile(String filename) {
        String filePath = new String("gka-Dateien/" + filename);
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
                        "^([\\wÄäÖöÜüß]+)(?:\\s*(->|--)\\s*([\\wÄäÖöÜüß]+)\\s*(?:\\(\\s*([\\wÄäÖöÜüß]+)\\s*\\)\\s*)?(?::\\s*(\\d+)\\s*)?)?;";


                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                //Lese und zeichne Graphen
                showGraph(graph, matcher);
            }

            //zeichnet Graph
            graph.display();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void showGraph(Graph graph, Matcher matcher) {
        //suche alle Teile of Dateien
        while (matcher.find()) {
            //Auslesen der einzelnen Gruppen
            final String node1 = matcher.group(1);
            final String node2 = matcher.group(3);
            final String edge = matcher.group(2);
            final String weight = matcher.group(5);
            final String edgeName = matcher.group(4); // TODO!!!

            String idEdge = edgeID();



//            System.out.println("Node1: " + node1);
//            System.out.println("Edge: " + edge);
//            System.out.println("Node2: " + node2);
//            System.out.println("EdgeWeight: " + weight);
//            System.out.println("EdgeName: " + edgeName);

            // drück jede Zeile nach dem Sortieren aus

            System.out.println(graph.getEdgeCount());
            System.out.println("----------------------------");
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
            System.out.println(edgeID());
          //  System.out.println("Die ID-Kante: " + edgeID());
            //System.out.println(matcher.groupCount());
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
            // - die Kante nicht schon in dem Graphen ist
            // - die Kante nicht schon in dem Graphen ist, aber rückwärts gerichtet
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
                    graph.getEdge(idEdge).addAttribute("ui.label", weight);
                }

                if (edgeName != null &&
                        !"".equals(edgeName)) {
                    graph.getEdge(idEdge).addAttribute("ui.label", edgeName);
                }


            }


        }
        //Node Namen
        graph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));

        graph.addAttribute("ui.stylesheet", styleSheet);
        //graph.addAttribute("ui.stylesheet", "graph { fill-color: yellow; }");
      //  graph.addAttribute("ui.quality");
       // graph.addAttribute("ui.antialias");
       // graph.addAttribute("ui.screenshot", "heart.jpg");


    }

//    private static void showGroups(Matcher matcher) {
//
//        while (matcher.find()) {
//            System.out.println("----------------------------");
//            for (int i = 1; i < matcher.groupCount(); i++) {
//                System.out.println("Group " + i + ": " + matcher.group(i));
//            }
//        }
//    }

    //erstellt EdgeID
    public static String edgeID() {
        return UUID.randomUUID().toString();
    }



    protected static String styleSheet =
            "graph {" +
                    "   fill-color: black;"+
                    "}" +

            "node {" +
                    "text-color: red;" +
                    "   shape: line;" +
                    "   text-size: 30px;" +
                    "   shape: circle;" +
                    "   fill-color: green;" +
                    "   size: 20px;" +
                    "   text-alignment: center;" +
                    "   fill-mode: gradient-radial;" +
                    "   stroke-color: yellow;" +

                    "}" +
                    "node.marked {" +
                    "	fill-color: red;" +
                    "}" +

   " edge {" +
        "shape: angle;" +
       " stroke-mode: plain;"+
        "text-color: white;"+
        "text-size: 15;"+
        "fill-color: blue;"+
        "text-background-mode: rounded-box;"+
        "text-background-color: red;"+
    "}"+

    "edge.marked {"+
       " fill-color: red;"+
    "}";

//    protected static String styleSheet =
//            "node {" +
//                    "	fill-color: black;" +
//                    "text-size: 20px;" +
//                    "shape: circle;" +
//                    "fill-color: red;"+
//                    "size: 20px;" +
//
//                    "}" +
//                    "node.marked {" +
//                    "	text-size: 20px;" +
//                    "	text-alignment: along;" +
//                    "	text-background-mode: rounded-box;" +
//                    "	stroke-mode: plain;" +
//                    "}";

}
