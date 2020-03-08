package ch1.se1;

public class ex14 {
    public static void main(String[] args) {

    }

    public static int solution(int n)
    {
        int c = 0;
        while (n != 0) {
            n >>= 1;
            c++;
        }

        return --c;
    }
}
