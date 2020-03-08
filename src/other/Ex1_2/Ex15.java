package other.Ex1_2;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class Ex15 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(readInts(args[0])));
    }

    public static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(words[i]);
        }
        return ints;
    }
}
