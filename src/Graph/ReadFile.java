import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {

    final static File file = new File("gka-Dateien/graph06.gka");

    public static void main(final String[] args) {
        readFile();
        //graphStream();

    }

    private static void readFile() {
        String line;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            Graph graph = new SingleGraph("Graph01");

            while ((line = br.readLine()) != null) {
                String ertsePattern =
                        "^(\\w+)(?:\\s*(->|--)\\s*(\\w+)\\s*(?:\\(\\s*(\\w+)\\s*\\)\\s*)?(?::\\s*(\\d+)\\s*)?)?;";
                String zweitePattern = "^\\s*(\\w+\\d*)\\s*([\\-\\-\\>]*)\\s*(\\w+\\d*)*\\s*[:]*\\s*(\\d*)";

                Pattern pattern = Pattern.compile(ertsePattern);
                Matcher matcher = pattern.matcher(line);

                //Lese und zeichne Graphen
                showGraph(graph, matcher);

            }
            graph.display();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void showGraph(Graph graph, Matcher matcher) {
        while (matcher.find()) {
            //Auslesen der einzelnen Gruppen
            final String node1 = matcher.group(1);
            final String node2 = matcher.group(3);
            final String edge = matcher.group(2);
            final String weight = matcher.group(5);
            final String edgeName = matcher.group(4); // TODO!!!

            System.out.println("Node1: " + node1);
            System.out.println("Edge: " + edge);
            System.out.println("Node2: " + node2);
            System.out.println("EdgeWeight: " + weight);
            System.out.println("EdgeName: " + edgeName);

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
                    graph.getEdge(node1 + node2) == null &&
                    graph.getEdge(node2 + node1) == null &&
                    graph.getNode(node2) != null) {

                graph.addEdge(
                        node1 + node2,
                        node1,
                        node2,
                        edge.equals("->")
                );

                // Gewicht hinzufügen, wenn:
                // - es nicht null ist
                // - es kein Leerstring ist
                if (weight != null &&
                        !"".equals(weight)) {
                    graph.getEdge(node1 + node2).addAttribute("ui.label", weight);
                }
            }

        }
        //Node Namen
        graph.getEachNode().forEach(node -> node.addAttribute("ui.label", node.getId()));
    }

    private static void showGroups(Matcher matcher) {

        while (matcher.find()) {
            System.out.println("----------------------------");
            for (int i = 1; i < matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }

}
