package ch1.se1;

public class ex19 {

    public static void main(String[] args) {
        System.out.print(fibonacci(46));
    }

    public static long fibonacci(int n)
    {
        int[] buf = new int[n + 1];
        buf[0] = 0;
        buf[1] = 1;
        for (int i = 2; i <= n; i++) {
            buf[i] = buf[i - 2] + buf[i - 1];
        }
        return buf[n];
    }
}
