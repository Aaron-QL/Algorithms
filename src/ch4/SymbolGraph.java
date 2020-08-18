package ch4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Graph graph;

    public SymbolGraph(String stream, String sp) {
        this.st = new ST<String, Integer>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] row = in.readLine().split(sp);
            for (int i = 0; i < row.length; i++) {
                if (!this.st.contains(row[i])) {
                    this.st.put(row[i], this.st.size());
                }
            }
        }

        this.keys = new String[this.st.size()];
        this.graph = new Graph(this.st.size());

        for (String name: this.st.keys()) {
            this.keys[this.st.get(name)] = name;
        }
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] row = in.readLine().split(sp);
            int v = this.st.get(row[0]);
            for (int i = 1; i < row.length; i++) {
                this.graph.addEdge(v, this.st.get(row[i]));
            }
        }
    }

    public boolean contains(String s) {
        return this.st.contains(s);
    }

    public int index(String s) {
        return this.st.get(s);
    }

    public String name(int v) {
        return this.keys[v];
    }

    public Graph G() {
        return this.graph;
    }
}
