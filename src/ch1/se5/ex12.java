package ch1.se5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex12 {
    private int[] id;
    private int count;

    public ex12(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
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
        if (connected(p, q)) {
            return;
        }
        id[p] = q;
        count--;
    }

    public static void main(String[] args) {
        ex12 quickUnionPathCompression = new ex12(10);
        quickUnionPathCompression.union(0, 1);
        quickUnionPathCompression.union(2, 3);
        quickUnionPathCompression.union(3, 4);
        quickUnionPathCompression.union(2, 4);

        StdOut.println("Components: " + quickUnionPathCompression.count + " Expected: 7");

        //Sequence of input pairs to produce a path of length 4:
        //0 1
        //2 3
        //4 5
        //6 7
        //6 4
        //4 2
        //4 0

        //Path: 6 -> 7 -> 5 -> 3 -> 1

        ex12 quickUnionPathCompression2 = new ex12(10);
        quickUnionPathCompression2.union(0, 1);
        quickUnionPathCompression2.union(2, 3);
        quickUnionPathCompression2.union(4, 5);
        quickUnionPathCompression2.union(6, 7);
        quickUnionPathCompression2.union(6, 4);
        quickUnionPathCompression2.union(4, 2);
        quickUnionPathCompression2.union(4, 0);

        StdOut.println("Components: " + quickUnionPathCompression2.count + " Expected: 3");
    }
}
