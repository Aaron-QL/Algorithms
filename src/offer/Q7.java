package offer;

import java.util.HashMap;
import java.util.Map;

public class Q7 {
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    private class Node {
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
        }
    }

    public Node reConstructBT(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }

        return reConstructBT(pre, 0, pre.length - 1, 0);
    }

    private Node reConstructBT(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }

        Node root = new Node(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftSize = inIndex - inL;
        root.left = reConstructBT(pre, preL + 1, preL + leftSize, inL);
        root.right = reConstructBT(pre, preL + leftSize + 1, preR, inL + leftSize + 1);
        return root;
    }

}
