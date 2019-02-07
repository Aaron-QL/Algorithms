package Ex1_2;

import edu.princeton.cs.algs4.StdIn;

public class Ex6 {
    public static void main(String[] args) {
        System.out.println("s: ");
        String s = StdIn.readLine();
        System.out.println("t: ");
        String t = StdIn.readLine();

        if (s.length() == t.length() && s.concat(s).contains(t)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
