package ch1.se2;

public class ex6 {
    public static void main(String[] args) {
        isCircularShift(args[0], args[1]);
    }

    private static boolean isCircularShift(String s, String t)
    {
        return s.length() == t.length() && (s + s).contains(t);
    }
}
