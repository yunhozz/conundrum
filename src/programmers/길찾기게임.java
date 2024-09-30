package programmers;

import java.util.Arrays;

public class 길찾기게임 {

    private int[][] result;
    private int index;

    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }

        Arrays.sort(nodes, (n1, n2) -> {
            if (n1.y == n2.y) return n1.x - n2.x;
            else return n2.y - n1.y;
        });

        Node rootNode = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            locateNode(rootNode, nodes[i]);
        }

        result = new int[2][nodeinfo.length];
        index = 0;
        preOrder(rootNode);
        index = 0;
        postOrder(rootNode);

        return result;
    }

    private void locateNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else locateNode(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else locateNode(parent.right, child);
        }
    }

    private void preOrder(Node node) {
        if (node != null) {
            result[0][index++] = node.value;
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            result[1][index++] = node.value;
        }
    }

    private static class Node {
        int x;
        int y;
        int value;
        Node left;
        Node right;

        Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}