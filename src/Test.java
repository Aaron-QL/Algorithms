public class Test {
    public static void main(String[] args) {
        String res;

        System.out.println(args[2]);
        if (args[0] == args[1] && args[1] == args[2]) {
            res = "equal";
        } else {
            res = "not";
        }
        System.out.println(res);
    }
}
