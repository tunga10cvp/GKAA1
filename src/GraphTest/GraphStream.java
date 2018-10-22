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
		graph.addNode("E");
		graph.addEdge("Test", "A", "B" );
		graph.addEdge("BC", "B", "C" );
		graph.addEdge("CA", "C", "A" );
		graph.addEdge("CD", "C", "D");
		graph.addEdge("DA", "D", "A");
		graph.getEdge("BC").setAttribute("weight", 1.2);
	//	graph.addEdge("BC", "C", "B");

		//graph.display();

		Node A = graph.getNode("A");
		Edge AB = graph.getEdge("Test");
		Edge DA = graph.getEdge("DA");
		System.out.println(AB.getId());

		System.out.println(DA.isDirected());
		System.out.println(AB.isDirected());
		System.out.println(AB.getId());

        GraphIOSave.saveGraph(graph, "gka-Dateien/graph01.gka");
		// graph.display();

    }

}
