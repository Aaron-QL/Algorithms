package other.Ex1_5;

public class QuickFindUF {
    private int[] id;
    private int count;

    QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = id[p];
        int qID = id[q];

        if (pID == qID) {
            return;
        }

        for (int i = 0; i < count; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }
}
