package ch1.se1;

public class ex18 {
    public static void main(String[] args) {
        System.out.print(mystery(2, 25));
    }

    public static int mystery(int a, int b)
    {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return mystery(a * a, b/2);
        }
        return mystery(a * a, b/2) * a;
    }
}
