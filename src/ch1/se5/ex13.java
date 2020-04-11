package ch1.se5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex13 {
    private int[] id;
    private int[] size;
    private int count;

    public ex13(int n) {
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    // validate that p is a valid index
    private void validate(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (id.length - 1));
        }
    }

    public int find(int p)
    {
        validate(p);
        if (p == id[p]) {
            return p;
        }
        return id[p] = find(id[p]);
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    public void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            size[qRoot] = pRoot;
            size[pRoot] = qRoot;
        }
        count--;
    }

    public static void main(String[] args) {
        //Sequence of input pairs to produce a tree of height 4:
        //0 1
        //0 2
        //0 3
        //6 7
        //8 9
        //6 8
        //0 6
        //10 11
        //10 12
        //10 13
        //10 14
        //10 15
        //10 16
        //10 17
        //10 18
        //0 10

        //Path of height 4: 9 -> 8 -> 6 -> 0 -> 10

        ex13 weightedQuickUnionPathCompression = new ex13(19);
        weightedQuickUnionPathCompression.union(0, 1);
        weightedQuickUnionPathCompression.union(0, 2);
        weightedQuickUnionPathCompression.union(0, 3);
        weightedQuickUnionPathCompression.union(6, 7);
        weightedQuickUnionPathCompression.union(8, 9);
        weightedQuickUnionPathCompression.union(6, 8);
        weightedQuickUnionPathCompression.union(0, 6);
        weightedQuickUnionPathCompression.union(10, 11);
        weightedQuickUnionPathCompression.union(10, 12);
        weightedQuickUnionPathCompression.union(10, 13);
        weightedQuickUnionPathCompression.union(10, 14);
        weightedQuickUnionPathCompression.union(10, 15);
        weightedQuickUnionPathCompression.union(10, 16);
        weightedQuickUnionPathCompression.union(10, 17);
        weightedQuickUnionPathCompression.union(10, 18);
        weightedQuickUnionPathCompression.union(0, 10);

        StdOut.println("Components: " + weightedQuickUnionPathCompression.count() + " Expected: 3");
    }
}
