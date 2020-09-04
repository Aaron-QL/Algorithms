package ch4.test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {
    private ST<String, Integer> map;
    private String[] keys;
    private Graph graph;

    public SymbolGraph(String stream, String sp) {
        if (stream == null || sp == null) {
            throw new IllegalArgumentException();
        }

        In in = new In(stream);
        this.map = new ST<>();

        while (in.hasNextLine()) {
            String[] arr = in.readLine().split(sp);
            for (int i = 0; i < arr.length; i++) {
                if (!map.contains(arr[i])) {
                    map.put(arr[i], map.size());
                }
            }
        }

        this.keys = new String[map.size()];
        for (String name : map.keys()) {
            this.keys[map.get(name)] = name;
        }

        in = new In(stream);
        this.graph = new Graph(this.keys.length);
        while (in.hasNextLine()) {
            String[] arr = in.readLine().split(sp);
            int v = map.get(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                this.graph.addEdge(v, map.get(arr[i]));
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

    public Graph graph() {
        return graph;
    }
}
