package ch1.se2;

import edu.princeton.cs.algs4.StdOut;

public class ex4 {
    public static void main(String[] args) {
        String string1 = "hello";
        String string2 = string1;
        string1 = "world";
        StdOut.println(string1 + " Expected: world");
        StdOut.println(string2 + " Expected: hello");
    }
}
