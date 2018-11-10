package Test;

import org.graphstream.graph.Graph;

import org.junit.Test;


import Graph.*;
import java.io.IOException;

import static org.junit.Assert.*;


public class GraphIOSaveTest {


    @Test
    public void saveTestGka01() throws IOException {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph01.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph1.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph1.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 10);
        assertTrue(saveGraph.getEdgeCount() == 23);
        assertTrue(saveGraph.getNode("v3") != null);

    }

    @Test
    public void saveTestGka02() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph02.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph2.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph2.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 11);
        assertTrue(saveGraph.getEdgeCount() == 38);
        assertTrue(saveGraph.getNode("a") != null);

    }

    @Test
    public void saveTestGka03() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph03.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph3.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph3.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 22);
        assertTrue(saveGraph.getEdgeCount() == 40);
        assertTrue(saveGraph.getNode("Hamburg") != null);

    }

    @Test
    public void saveTestGka04() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph04.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph4.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph4.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 10);
        assertTrue(saveGraph.getEdgeCount() == 23);
        assertTrue(saveGraph.getNode("q") != null);

    }

    @Test
    public void saveTestGka05() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph05.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph5.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph5.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 7);
        assertTrue(saveGraph.getEdgeCount() == 20);
        assertTrue(saveGraph.getNode("v1") != null);

    }

    @Test
    public void saveTestGka06() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph06.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph6.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph6.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 11);
        assertTrue(saveGraph.getEdgeCount() == 15);
        assertTrue(saveGraph.getNode("1") != null);

    }
    @Test
    public void saveTestGka07() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph07.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph7.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph7.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 10);
        assertTrue(saveGraph.getEdgeCount() == 23);
        assertTrue(saveGraph.getNode("v1") != null);

    }

    @Test
    public void saveTestGka08() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph08.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph8.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph8.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 16);
        assertTrue(saveGraph.getEdgeCount() == 15);
        assertTrue(saveGraph.getNode("v8") != null);

    }

    @Test
    public void saveTestGka09() throws IOException {
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph09.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph9.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph9.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 12);
        assertTrue(saveGraph.getEdgeCount() == 36);
        assertTrue(saveGraph.getNode("a") != null);

    }

    @Test
    public void saveTestGka10() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph10.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph10.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph10.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 12);
        assertTrue(saveGraph.getEdgeCount() == 26);
        assertTrue(saveGraph.getNode("s") != null);


    }

    @Test
    public void saveTestGka11() throws IOException{
        //Aufrufen deiner eigenen Methoden/Klassen
        Graph graph = ReadFile.readFile("gka-Dateien/graph11.gka");
        GraphIOSave.saveGraph(graph, "save-Dateien/savegraph11.gka");
        Graph saveGraph = ReadFile.readFile("save-Dateien/savegraph11.gka");

        assertFalse(saveGraph == null);
        assertTrue(saveGraph.getNodeCount() == 10);
        assertTrue(saveGraph.getEdgeCount() == 23);
        assertTrue(saveGraph.getNode("v4") != null);

    }

}
