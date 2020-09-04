package ch4.test;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
    private int[] edgeTo;
    private int[] distTo;
    private boolean[] marked;
    private final int source;

    public BreadthFirstPaths(Graph graph, int source) {
        if (source < 0 || source >= graph.vertices()) {
            throw new IllegalArgumentException();
        }
        this.source = source;
        this.edgeTo = new int[graph.vertices()];
        this.distTo = new int[graph.vertices()];
        this.marked = new boolean[graph.vertices()];

        this.bfs(graph, source);
    }

    public void bfs(Graph graph, int sourceVertex) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(sourceVertex);
        this.marked[sourceVertex] = true;
        this.edgeTo[sourceVertex] = sourceVertex;

        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();

            for (int w : graph.adjacent(currentVertex)) {
                if (!this.marked[w]) {
                    this.marked[w] = true;
                    this.edgeTo[w] = currentVertex;
                    this.distTo[w] = this.distTo[currentVertex] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }


    //O(1)
    public int distTo(int vertex) {
        return distTo[vertex];
    }

    //O(1)
    public int edgeTo(int vertex) {
        return edgeTo[vertex];
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();

        for(int currentVertex = vertex; currentVertex != source; currentVertex = edgeTo[currentVertex]) {
            path.push(currentVertex);
        }

        path.push(source);
        return path;
    }
}
