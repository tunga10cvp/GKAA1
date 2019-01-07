package Test;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import static Graph.ReadFile.readFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Graph.EdmondsKarpAlgorithm;

import Graph.FordFulkerson;

import Graph.GraphGenerator;

import javax.imageio.metadata.IIOMetadataNode;
import java.util.Calendar;

public class FordFulkersonVsEdmondsKarpTest {

    @Test
    public void graph04Test1() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("v8");



        long begin1 = System.nanoTime();
        Integer ff = FordFulkerson.fordFulkerson(graph, source, target);
        long end1 = System.nanoTime();

        System.out.println("Laufzeit Fordfulkerson: " + (end1 - begin1));

        long begin2 = System.nanoTime();
        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);
        long end2 = System.nanoTime()
                ;

        System.out.println("Laufzeit Edmondskarp: " + (end2 - begin2));

        assertTrue(ff == ek);

    }

    @Test
    public void graph04Test2() {

        Graph graph = readFile("gka-Dateien/graph04.gka");

        Node source = graph.getNode("v1");
        Node target = graph.getNode("s");


        long begin1 = System.nanoTime();
        Integer ff = FordFulkerson.fordFulkerson(graph, source, target);
        long end1 = System.nanoTime();

        System.out.println("Laufzeit Fordfulkerson: " + (end1 - begin1));

        long begin2 = System.nanoTime();
        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(graph, source, target);
        long end2 = System.nanoTime();

        System.out.println("Laufzeit Edmondskarp: " + (end2 - begin2));

        assertTrue(ff == ek);

    }

    @Test
    public void bigNetTest800Knoten(){

        Graph bigNet = null;

        try {
            bigNet = GraphGenerator.generateBigNet("test1", 800, 300000, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Node source = bigNet.getNode(0);
        Node target = bigNet.getNode(20);

        long begin1 = System.nanoTime();
        Integer ff = FordFulkerson.fordFulkerson(bigNet, source, target);
        long end1 = System.nanoTime();

        System.out.println("Laufzeit Fordfulkerson: " + (end1 - begin1) + "ns" + " ---- " + (end1 - begin1)/1000000000 + "s");

        long begin2 = System.nanoTime();
        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(bigNet, source, target);
        long end2 = System.nanoTime();

        System.out.println("Laufzeit Fordfulkerson: " + (end2 - begin2) + "ns" + " ---- " + (end2 - begin2)/1000000000 + "s");

        assertEquals(ff, ek);

    }

    @Test
    public void bigNetTest3500Knoten(){

        Graph bigNet = null;

        try {
            bigNet = GraphGenerator.generateBigNet("test1", 3500, 2000000, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Node source = bigNet.getNode(0);
        Node target = bigNet.getNode(20);

        long begin1 = System.nanoTime();
        Integer ff = FordFulkerson.fordFulkerson(bigNet, source, target);
        long end1 = System.nanoTime();

        System.out.println("Laufzeit Fordfulkerson: " + (end1 - begin1) + " ns" + " ---- " + (end1 - begin1)/1000000000 + "s");

        long begin2 = System.nanoTime();
        Integer ek = EdmondsKarpAlgorithm.edmondsKarp(bigNet, source, target);
        long end2 = System.nanoTime();

        System.out.println("Laufzeit Fordfulkerson: " + (end2 - begin2) + " ns" + " ---- " + (end2 - begin2)/1000000000 + "s");
        assertEquals(ff, ek);

    }

}
