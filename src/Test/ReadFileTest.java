package Test;


import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;

import org.junit.Test;


import Graph.*;
import java.io.IOException;


import static org.junit.Assert.*;

public class ReadFileTest{



    @Test
    public void readTestGka01() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph01.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 10);
        assertTrue(graph.getEdgeCount() == 23);
        assertTrue(graph.getNode("v3") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka02() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph02.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 11);
        assertTrue(graph.getEdgeCount() == 38);
        assertTrue(graph.getNode("a") != null);

        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka03() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph03.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 22);
        assertTrue(graph.getEdgeCount() == 40);
        assertTrue(graph.getNode("Hamburg") != null);


        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka04() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph04.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 10);
        assertTrue(graph.getEdgeCount() == 23);
        assertTrue(graph.getNode("q") != null);


        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka05() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph05.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 7);
        assertTrue(graph.getEdgeCount() == 20);
        assertTrue(graph.getNode("v1") != null);

        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka06() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph06.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 11);
        assertTrue(graph.getEdgeCount() == 15);
        assertTrue(graph.getNode("1") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }
    @Test
    public void readTestGka07() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph07.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 10);
        assertTrue(graph.getEdgeCount() == 23);
        assertTrue(graph.getNode("v1") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka08() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph08.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 16);
        assertTrue(graph.getEdgeCount() == 15);
        assertTrue(graph.getNode("v8") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka09() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph09.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 12);
        assertTrue(graph.getEdgeCount() == 36);
        assertTrue(graph.getNode("a") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka10() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph10.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 12);
        assertTrue(graph.getEdgeCount() == 26);
        assertTrue(graph.getNode("v1") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }

    @Test
    public void readTestGka11() {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph11.gka");

        assertFalse(graph == null);
        assertTrue(graph.getNodeCount() == 10);
        assertTrue(graph.getEdgeCount() == 23);
        assertTrue(graph.getNode("v4") != null);



        //Überprüfung der Ergebnisse auf Komplettheit/Korrektheit

    }
}
