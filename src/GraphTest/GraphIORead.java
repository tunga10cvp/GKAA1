package Graphtest;

import java.io.*;

public class GraphIORead {
    public static void main(String[] args) throws IOException
    {
        FileReader file = new FileReader("graph01.gka");
        BufferedReader br = new BufferedReader(file);

        String zeile;


        while( (zeile = br.readLine()) != null )
        {
            String graph = new String(zeile.getBytes(),"UTF-8");
            System.out.println(graph);
        }

        br.close();
    }




}