package offer;

public class Q10_2 {
    public static void main(String[] args) {
        Q10_2 q = new Q10_2();
        System.out.print(q.fibonacci3(39));
    }

    public int fibonacci1(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci1(n - 2) + fibonacci1(n - 1);
    }

    public int fibonacci2(int n) {
        if (n <= 1) {
            return n;
        }

        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 2] + f[i - 1];
        }
        return f[n];
    }

    public int fibonacci3(int n) {
        if (n <= 1) {
            return n;
        }

        int pre2 = 0, pre1 = 1, fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = pre2 + pre1;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }
}
