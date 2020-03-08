package other.Ex1_1;

public class Ex14 {
    public static int solution(int n) {
        int count = 0;
        while (n != 0) {
            n >>= 1;
            count++;
        }
        return --count;
    }

    public static void main(String[] args) {
        System.out.println(solution(8));
    }
}
