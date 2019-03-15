package offer;

public class Q8 {
    public static void main(String[] args) {
        Q8 q = new Q8();
        q.postorderTraversal(q.construct());
    }

    public Node construct() {
        Node x = new Node(7);
        x.left = new Node(3);
        x.right = new Node(10);

        return x;
    }

    private class Node {
        int val;
        Node left, right, parent;

        Node(int x) {
            this.val = x;
        }
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

    public Node getNext(Node x) {
        if (x.right != null) {
            Node t = x.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        }

        while (x.parent != null) {
            Node p = x.parent;
            if (p.left == x) {
                return p;
            }
            x = x.parent;
        }

        return null;
    }
}
