package GraphTest;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.io.IOException;

public class GraphStream {
	
	public static void main(String[] args) throws IOException {
		Graph graph = new SingleGraph("Tutorial 1");
		
		graph.addNode("A" );
		graph.addNode("B" );								//Parsing
		graph.addNode("C" );
		graph.addNode("D");
		graph.addEdge("Test", "A", "B" );
		graph.addEdge("BC", "B", "C" );
		graph.addEdge("CA", "C", "A" );
		graph.addEdge("CD", "C", "D");
		
		graph.display();

		Node A = graph.getNode("A");
		Edge AB = graph.getEdge("Test");
		System.out.println(AB.getId());

		System.out.println(AB.isDirected());
		System.out.println(AB.getId());

        GraphIO.saveGraph(graph, "test1.gka");


    }

}
