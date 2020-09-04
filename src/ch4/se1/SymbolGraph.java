package ch4.se1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @return the name of the vertex associated with the integer {@code v}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }


    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public Graph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    /**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
