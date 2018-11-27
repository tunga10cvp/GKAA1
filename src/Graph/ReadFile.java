package Graph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Graph.FloydWarshalAlgorithm.accessCounter;

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

    public static void main(final String[] args) throws InterruptedException {
        String filename = "gka-Dateien/graph03.gka";
        Graph graph = readFile(filename);


//        List<Node> bfsReturn = traverseWithBFS(graph, graph.getNode("Kiel"), graph.getNode("Husum"));
//
//        for (Node node : bfsReturn){
//            node.addAttribute("ui.style", " fill-color: red;");
//            //testGraph.addAttribute("ui.stylesheet", "edge { fill-color: red; }");
//        }
//
//        for(int i = 0; i < bfsReturn.size() - 1; i++){
//            // System.out.println(bfsReturn.get(i)+bfsReturn.get(i+1));
//            String node1 = String.valueOf(bfsReturn.get(i));
//            String node2 = String.valueOf(bfsReturn.get(i+1));
//            String edgeId = String.valueOf(i);
//
//            System.out.println(node1);
//            System.out.println(node2);
//
//
//        }
//
//        System.out.println(bfsReturn);

        Node source = graph.getNode("Kiel");
        Node target = graph.getNode("Lübeck");
        List<Double> distance = new ArrayList<>();

        List<Node> floyd = FloydWarshalAlgorithm.shortestPathsWithFloydWarshal(graph, source, target);

        //Knoten und Kanten des Path zeigen
        for (int i = 0; i < floyd.size() - 1; i++) {
        //    floyd.get(i).addAttribute("ui.style", " fill-color: red;");
            floyd.get(i).getEdgeToward(floyd.get(i + 1)).addAttribute("ui.style", " fill-color: red;");
        }

        for (int i = 0; i < floyd.size() ; i++){
            floyd.get(i).addAttribute("ui.style", " fill-color: red;");

        }

//        List<Node> nodes = new ArrayList<>();
//        for (Node node : graph.getEachNode()) {
//            nodes.add(node);
//        }
//
//        int count = graph.getNodeCount();
//        double[][] distanceMatrix = new double[count][count];
//        for (int i = 0; i < count; i++) {
//            for (int j = 0; j < count; j++) {
//                distanceMatrix[i][j] = nodes.get(i).getEdgeToward(nodes.get(j)).getAttribute("ui.label");
//                System.out.println(distanceMatrix[i][j]);
//            }
//        }

        System.out.println("Unser Weg: " + source + " als Startknote und " + target + " als Zielknote");
        System.out.println("----------------------------------------------------------");


        for (int i = 0; i < floyd.size() - 1; i++) {
            distance.add(floyd.get(i).getEdgeToward(floyd.get(i + 1)).getAttribute("ui.label"));
            System.out.println("Von " + floyd.get(i) + " nach " + floyd.get(i + 1) + " mit dem Abstand: " + floyd.get(i).getEdgeToward(floyd.get(i + 1)).getAttribute("ui.label"));
        }

        Double summe = 0.0;
        for (Double dis : distance){
            summe += dis;
        }


        System.out.println("----------------------------------------------------------");
        System.out.println("Die Entfernung zwischen Startknote und ZielKnote: " + summe);
        System.out.println("----------------------------------------------------------");
        System.out.println("Anzahl der Zugriffe auf den Graphen: " + accessCounter);

        graph.display();

//        for (int i = 0; i < floyd.size(); i++){
//            Node node = floyd.get(0);
//            node.addAttribute("ui.style","fill-color: red");
//            sleep();
//        }


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
                        "^([\\wÄäÖöÜüß]+)(?:\\s*(->|--)\\s*([\\wÄäÖöÜüß]+)\\s*(?::\\s*(\\d+)\\s*)?)?\\s*(?:\\(\\s*([\\wÄäÖöÜüß]+)\\s*\\)\\s*)?;";
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
