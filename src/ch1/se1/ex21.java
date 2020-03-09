package ch1.se1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex21 {
    public static void main(String[] args) {
        String name;
        int score1;
        int score2;
        String table = "";
        while (!StdIn.isEmpty()) {
            name = StdIn.readString();
            score1 = StdIn.readInt();
            score2 = StdIn.readInt();
            table += String.format("%s: %d %d %d\n", name, score1, score2 , score1 / score2);
        }
        StdOut.print(table);
    }
}
