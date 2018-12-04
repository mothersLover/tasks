package datastructures.trees.binary;

import java.util.*;

public class SwapNodesAlgo {

    public static void main(String... ats) {
        int[][] ar = new int[][] {{2,3},{4, -1}, {5, -1}, {6, -1}, {7, 8}, {-1, 9}, {-1, -1}, {10, 11}, {-1, -1}, {-1, -1}, {-1, -1}};
        int[] query = new int[] {2, 4};
//        swapNodes(ar, query);
        Node tree = createTree(ar);
        boolean b = checkBST(tree);
        System.out.println("b = " + b);
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        /*
         * Write your code here.
         */
        Node root = createTree(indexes);
        List<Integer> integers = new ArrayList<>();
        traverse(root, 1, queries[0]);
        printInOrder(root, integers);
        int[][] result = new int[queries.length][integers.size()];
        for (int j = 0; j < integers.size(); j++) {
            result[0][j] = integers.get(j);
        }
        integers.clear();

        for (int i = 1; i < queries.length; i++) {
            traverse(root, 1, queries[i]);
            printInOrder(root, integers);
            for (int j = 0; j < integers.size(); j++) {
                result[i][j] = integers.get(j);
            }
            integers.clear();
        }
        return result;
    }

    private static void printInOrder(Node root, List<Integer> integers) {
        if (root != null) {
            printInOrder(root.left, integers);
            integers.add(root.data);
            printInOrder(root.right, integers);
        }
    }

    private static void traverse(Node root, int curLevel, int level) {
        if (root != null) {
            if (curLevel % level == 0) {
                Node left = root.left;
                root.left = root.right;
                root.right = left;
            }
            int curLevel1 = ++curLevel;
            traverse(root.left, curLevel1, level);
            traverse(root.right, curLevel1, level);
        }
    }

    private static Node createTree(int[][] indexes) {
        ArrayDeque<Node> nodes = new ArrayDeque<>(indexes.length*2 + 1);
        Node root = new Node(1);
        nodes.offerLast(root);
        int step = 0;
        while (!nodes.isEmpty()) {
            Node curRoot = nodes.pollFirst();
            int left = indexes[step][0];
            int right = indexes[step][1];
            if (left != -1) {
                curRoot.left = new Node(left);
                nodes.offerLast(curRoot.left);
            }
            if (right != -1) {
                curRoot.right = new Node(right);
                nodes.offerLast(curRoot.right);
            }
            step++;
        }
        return root;
    }

    static boolean checkBST(Node root) {
        return checkIfBinaryTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean checkIfBinaryTree(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.data < min || root.data > max) {
            return false;
        }
        return checkIfBinaryTree(root.left, min, root.data - 1) && checkIfBinaryTree(root.right, root.data + 1, max);
    }
}
