package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Q6 {
    public static void main(String[] args) {
        Q6 q = new Q6();
        ArrayList<Integer> a = q.printListFromTailToHead2(q.construct());
        System.out.println(Arrays.toString(a.toArray()));
    }

    public Node construct() {
        Node x = new Node(1);
        x.next = new Node(2);
        x.next.next = new Node(3);
        x.next.next.next = new Node(4);
        x.next.next.next.next = new Node(5);
        return x;
    }

    private class Node {
        int val;
        Node next;

        Node(int x) {
            this.val = x;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(Node x) {
        Node head = new Node(-1);
        while (x != null) {
            Node t1 = x;
            Node t2 = head.next;
            x = x.next;
            head.next = t1;
            t1.next = t2;
        }

        ArrayList<Integer> ret = new ArrayList<>();
        head = head.next;
        while (head != null) {
            ret.add(head.val);
            head = head.next;
        }
        return ret;
    }

    public ArrayList<Integer> printListFromTailToHead2(Node x) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (x != null) {
            ret.addAll(printListFromTailToHead2(x.next));
            ret.add(x.val);
        }
        return ret;
    }


    public ArrayList<Integer> printListFromTailToHead3(Node x) {
        Stack<Integer> s = new Stack<>();
        while (x != null) {
            s.push(x.val);
            x = x.next;
        }

        ArrayList<Integer> ret = new ArrayList<>();
        while (!s.isEmpty()) {
            ret.add(s.pop());
        }
        return ret;
    }



}
