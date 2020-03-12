package ch1.se2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class Cat {
    public static void main(String[] args) {
        Out out = new Out(args[args.length - 1]);
        for (int i = 0; i < args.length - 1; i++) {
            In in = new In(args[i]);
            String content = in.readAll();
            out.println(content);
            in.close();
        }
        out.close();
    }
}
