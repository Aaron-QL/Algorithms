package ch1.se5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex17 {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);

        int numberOfConnections = erdosRenyi(number);

        StdOut.println();
        StdOut.println("Number of connections generated: " + numberOfConnections);
    }

    public static int erdosRenyi(int number)
    {
        int connectionCount = 0;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(number);

        while (uf.count() != 1) {
            int p = StdRandom.uniform(number);
            int q = StdRandom.uniform(number);

            StdOut.println("Connection generated: " + p + " - " + q);

            connectionCount++;
            uf.union(p, q);
        }

        return connectionCount;
    }
}
