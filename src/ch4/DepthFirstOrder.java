package ch4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        this.marked = new boolean[G.V()];
        this.pre = new Queue<Integer>();
        this.post = new Queue<Integer>();
        this.reversePost = new Stack<Integer>();

        for (int v = 0; v < G.V(); v++) {
            if (!this.marked[v]) {
                this.dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        this.marked[v] = true;
        this.pre.enqueue(v);

        for (int w : G.adj(v)) {
            if (!this.marked[w]) {
                this.dfs(G, w);
            }
        }

        this.post.enqueue(v);
        this.reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return this.post;
    }

    public Iterable<Integer> reversePost() {
        return this.reversePost;
    }
}
