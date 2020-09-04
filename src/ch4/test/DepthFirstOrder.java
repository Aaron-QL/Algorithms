package ch4.test;

import edu.princeton.cs.algs4.*;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> preOrder;
    private Queue<Integer> postOrder;
    private Stack<Integer> reversePostOrder;

    public DepthFirstOrder(Digraph digraph, int source) {
        this.marked = new boolean[digraph.vertices()];
        this.preOrder = new Queue<>();
        this.postOrder = new Queue<>();
        this.reversePostOrder = new Stack<>();
        this.dfs(digraph, source);
    }

    public DepthFirstOrder(DigraphInterface digraph) {
        preOrder = new Queue<>();
        postOrder = new Queue<>();
        reversePostOrder = new Stack<>();
        marked = new boolean[digraph.vertices()];

        for(int vertex = 0; vertex < digraph.vertices(); vertex++) {
            if (!marked[vertex]) {
                dfs(digraph, vertex);
            }
        }
    }

    private void dfs(DigraphInterface digraph, int vertex) {
        this.preOrder.enqueue(vertex);

        this.marked[vertex] = true;
        for (int neighbor : digraph.adjacent(vertex)) {
            if (!this.marked[neighbor]) {
                this.dfs(digraph, neighbor);
            }
        }

        this.postOrder.enqueue(vertex);
        this.reversePostOrder.push(vertex);
    }

    public boolean marked(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> preOrder() {
        return this.preOrder;
    }

    public Iterable<Integer> postOrder() {
        return this.postOrder;
    }

    public Iterable<Integer> reversePostOrder() {
        return this.reversePostOrder;
    }
}
