package ch4.test;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        this.marked = new boolean[graph.vertices()];

        for (int i = 0; i < graph.vertices(); i++) {
            if (!this.marked[i]) {
                this.dfs(graph, i, i);
            }
        }
    }

    private void dfs(Graph graph, int vertex, int previous) {
        this.marked[vertex] = true;

        for (int neighbor : graph.adjacent(vertex)) {
            if (!this.marked[neighbor]) {
                this.dfs(graph, neighbor, vertex);
            } else if (neighbor != previous) {
                this.hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
