package ch1.se1;

public class ex9 {
    public static void main(String[] args) {
        System.out.println(intToBinStr(11111));
    }

    public static String intToBinStr(int n)
    {
        String s = "";
        while (n > 0) {
            s += n % 2;
            n /= 2;
        }

        return s;
    }
}
