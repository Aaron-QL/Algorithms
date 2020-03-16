package ch1.se3;

import edu.princeton.cs.algs4.StdOut;

public class ex6 {
    private static void invertQueue(Queue<String> queue)
    {
        Stack<String> stack = new Stack<>();

        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
    }

    public static void main(String[] args)
    {
        Queue<String> queue = new Queue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");

        invertQueue(queue);

        StdOut.print("Inverted queue: ");
        for (String string : queue) {
            StdOut.print(string + " ");
        }
        StdOut.println("\nExpected: 3 2 1");
    }
}
