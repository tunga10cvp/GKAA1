package GraphTest;

/*************************** Edge.java * **************************************/
/** Klasse zur Repraesentation einer Kante                                    */


public class EdgeGraph {

    public VertexGraph end;                 // Zielknoten, zu dem die Kante fuehrt
    public double weight;                 // Kosten dieser Kante

    public Edge (VertexGraph e, double w) {  // Konstruktor fuer Kante
        end = e;                         // initialisiere Zielknoten
        weight = w;                         // initialisiere Kantenkosten
    }
}
