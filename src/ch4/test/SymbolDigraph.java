package ch4.test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolDigraph {

    private ST<String, Integer> map;
    private String[] keys;
    private Digraph digraph;

    public SymbolDigraph(String stream, String separator) {
        map = new ST<>();

        //First pass
        In in = new In(stream);

        while (in.hasNextLine()) {
            String[] vertices = in.readLine().split(separator);

            for(int i = 0; i < vertices.length; i++) {
                if (!map.contains(vertices[i])) {
                    map.put(vertices[i], map.size());
                }
            }
        }

        keys = new String[map.size()];

        for(String vertexName : map.keys()) {
            keys[map.get(vertexName)] = vertexName;
        }

        digraph = new Digraph(map.size());
        //Seconds pass
        in = new In(stream);

        while (in.hasNextLine()) {
            String[] vertices = in.readLine().split(separator);

            int vertex = map.get(vertices[0]);
            for(int i = 1; i < vertices.length; i++) {
                digraph.addEdge(vertex, map.get(vertices[i]));
            }
        }
    }

    public boolean contains(String vertexName) {
        return map.contains(vertexName);
    }

    public int index(String vertexName) {
        return map.get(vertexName);
    }

    public String name(int vertexId) {
        return keys[vertexId];
    }

    public Digraph digraph() {
        return digraph;
    }

}