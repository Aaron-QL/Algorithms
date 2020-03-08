package ch1.se1;

public class ex10 {
    public static void main(String[] args) {
        boolean[][] tab = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            System.out.printf("\t%d", i + 1);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.printf("\t%s", "*");
            }
            System.out.println();
        }
    }
}
