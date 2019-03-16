package offer;

import java.util.HashMap;
import java.util.Map;

public class Q7_2 {
    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 6, 7, 8, 3, 5};
        int[] mid = new int[]{4, 7, 6, 8, 2, 1, 3, 5};
        int[] post = new int[]{7, 8, 6, 4, 2, 5, 3, 1};
        Q7_2 q = new Q7_2();
        Node re = q.reConstructByPost(post, mid);
        q.preorderTraversal(re);
        System.out.print("\n");
        q.midorderTraversal(re);
        System.out.print("\n");
        q.postorderTraversal(re);
    }

    private class Node {
        int val;
        Node left, right;

        Node(int x) {
            this.val = x;
        }
    }

    private Map<Integer, Integer> index = new HashMap<>();

    public Node reConstructByPre(int[] pre, int[] mid) {
        for (int i = 0; i < mid.length; i++) {
            index.put(mid[i], i);
        }

        return reConstructByPre(pre, 0, pre.length - 1, 0);
    }

    private Node reConstructByPre(int[] pre, int preL, int preR, int midL) {
        if (preL > preR) {
            return null;
        }

        Node root = new Node(pre[preL]);
        int leftSize = index.get(root.val) - midL;
        root.left = reConstructByPre(pre, preL + 1, preL + leftSize, midL);
        root.right = reConstructByPre(pre, preL + 1 + leftSize, preR, midL + leftSize + 1);
        return root;
    }

    public Node reConstructByPost(int[] post, int[] mid) {
        for (int i = 0; i < mid.length; i++) {
            index.put(mid[i], i);
        }

        return reConstructByPost(post, 0, post.length - 1, mid.length - 1);
    }

    private Node reConstructByPost(int[] post, int postL, int postR, int midR) {
        if (postL > postR) {
            return null;
        }

        Node root = new Node(post[postR]);
        int rightSize = midR - index.get(root.val);
        root.left = reConstructByPost(post, postL, postR - 1 - rightSize, midR - rightSize - 1);
        root.right = reConstructByPost(post, rightSize, postR - 1, midR);

        return root;
    }


    public void preorderTraversal(Node x) {
        if (x != null) {
            System.out.print(x.val + " ");
            preorderTraversal(x.left);
            preorderTraversal(x.right);
        }
    }

    public void midorderTraversal(Node x) {
        if (x != null) {
            midorderTraversal(x.left);
            System.out.print(x.val + " ");
            midorderTraversal(x.right);
        }
    }

    public void postorderTraversal(Node x) {
        if (x != null) {
            postorderTraversal(x.left);
            postorderTraversal(x.right);
            System.out.print(x.val + " ");
        }
    }
}
