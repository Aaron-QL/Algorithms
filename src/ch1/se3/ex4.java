package ch1.se3;

import edu.princeton.cs.algs4.StdOut;

public class ex4 {
    public static void main(String[] args) {
        ex4 exercise4 = new ex4();

        StdOut.println("Is balanced [()]{}{[()()]()}: " + exercise4.isBalanced("[()]{}{[()()]()}"));
        StdOut.println("Is balanced [(]): " + exercise4.isBalanced("[(])"));
        StdOut.println("Is balanced (((((: " + exercise4.isBalanced("((((("));
    }

    private boolean isBalanced(String input) {

        char[] charArr = input.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : charArr) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                    char prevChar = stack.pop();
                    if (c == ')' && prevChar != '('
                            || c == ']' && prevChar != '['
                            || c == '}' && prevChar != '{') {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
