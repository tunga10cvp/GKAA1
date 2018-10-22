package GraphTest;

import org.graphstream.graph.Edge;

import java.util.*;

/** Klasse zur Implementation eines Graphen basierend auf Vertex und Edge     */

/*  Der Graph wird implementiert als HashMap <String, Vertex>, d.h. als eine  */
/*  Hashtabelle mit Keys vom Typ String und Values vom Typ Knoten             */


public class Graph1 {

    private Map <String, Vertex> graph;          // Datenstruktur fuer Graph

    public Graph() {                             // leerer Graph wird angelegt
        graph = new HashMap <String, Vertex> ();   // als HashMap von String,Vertex
    }

    public boolean isEmpty(){                    // liefert true, falls Graph leer
        return graph.isEmpty();                    // mit isEmpty() von HashMap
    }

    public int size(){                           // liefert die Anzahl der Knoten
        return graph.size();                       // mit size() von HashMap
    }

    public Collection <Vertex> vertices(){       // liefert Knoten als Collection
        return graph.values();                     // mit values() von HashMap
    }

    public Vertex getVertex(String s){  // liefere Knoten zu String
        Vertex v = graph.get(s);          // besorge Knoten zu Knotennamen
        if (v==null) {                    // falls nicht gefunden
            v = new Vertex(s);              // lege neuen Knoten an
            graph.put(s, v);                // fuege Namen und Knoten in HashMap ein
        }
        return v;                         // liefere gefundenen oder neuen Knoten
    }

    public void addEdge(String source,  // fuege Kante ein von Knotennamen source
                        String dest,    // zu Knotennamen dest
                        double cost) {  // mit Kosten cost
        Vertex v = getVertex(source);     // finde Knoten v zum Startnamen
        Vertex w = getVertex(dest);       // finde Knoten w zum Zielnamen
        v.edges.add(new Edge(w, cost));   // fuege Kante (v,w) mit Kosten cost ein
    }

}