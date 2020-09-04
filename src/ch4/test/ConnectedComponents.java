package ch4.test;

public class ConnectedComponents implements ConnectedComponentsInterface {
    private int count = 0;
    private int[] id;
    private boolean[] marked;

    public ConnectedComponents(Graph graph) {
        this.id = new int[graph.vertices()];
        this.marked = new boolean[graph.vertices()];

        for (int i = 0; i < graph.vertices(); i++) {
            if (!this.marked[i]) {
                this.count++;
                this.dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int vertex) {
        this.marked[vertex] = true;
        this.id[vertex] = this.count;

        for (int neighbor : graph.adjacent(vertex)) {
            if (!this.marked[neighbor]) {
                this.dfs(graph, neighbor);
            }
        }
    }

    public boolean connected(int vertex1, int vertex2) {
        return id[vertex1] == id[vertex2];
    }

    public int id(int vertex) {
        return id[vertex];
    }

    public int count() {
        return count;
    }

}
