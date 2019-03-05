package Ex1_5;

public class PracticeUF {
    private int[] id;
    private int[] sz;
    private int count;

    PracticeUF(int n) {
        id = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    int getCount()
    {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (sz[qRoot] < sz[pRoot]) {
            id[qRoot] = id[pRoot];
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = id[qRoot];
            sz[qRoot] += sz[pRoot];
        }
        id[pRoot] = qRoot;
        count--;
    }
}
