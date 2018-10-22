package GraphTest;

import org.graphstream.graph.Edge;

import java.util.*;

public class VertexGraph {

    public String      name;          // Name des Knoten               (fix)
    public List<Edge>  edges ;        // Nachbarn als Kantenliste      (fix)


    public Vertex ( String s ) {      // Konstruktor fuer Knoten
        name = s;                       // initialisiere Name des Knoten
        edges = new LinkedList<Edge>(); // initialisiere Nachbarschaftsliste
    }


}