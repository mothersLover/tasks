package test.datastructures.trees.binary;

import org.junit.Assert;
import org.junit.Test;

public class DeleteNodeFromBinarySearchTree {

    @Test
    public void fillingTreeTest() {
        int[] ints = new int[] {6,4,2,5,1,3,8,7,9,10};
        TreeNode root = fillTree(ints);
        Assert.assertEquals(6, root.value);

        Assert.assertEquals(4, root.left.value);
        Assert.assertEquals(2, root.left.left.value);
        Assert.assertEquals(5, root.left.right.value);
        Assert.assertEquals(1, root.left.left.left.value);
        Assert.assertEquals(3, root.left.left.right.value);

        Assert.assertEquals(8, root.right.value);
        Assert.assertEquals(7, root.right.left.value);
        Assert.assertEquals(9, root.right.right.value);
        Assert.assertEquals(10, root.right.right.right.value);
        Assert.assertNull(root.right.right.left);
    }

    @Test
    public void findMinValueInTreeMethodTest() {
        int[] ints = new int[] {6,4,2,5,1,3,8,7,9,10};
        TreeNode root = fillTree(ints);
        int minValue = findMinInBinarySearchTree(root);
        Assert.assertEquals(1, minValue);
    }

    @Test
    public void deleteRootNodeTest() {
        int[] ints = new int[] {6,4,2,5,1,3,8,7,9,10};
        TreeNode root = fillTree(ints);
        root = deleteNode(root, 6);
        Assert.assertEquals(7, root.value);

        Assert.assertEquals(4, root.left.value);
        Assert.assertEquals(2, root.left.left.value);
        Assert.assertEquals(5, root.left.right.value);
        Assert.assertEquals(1, root.left.left.left.value);
        Assert.assertEquals(3, root.left.left.right.value);

        Assert.assertEquals(8, root.right.value);
        Assert.assertNull(root.right.left);
        Assert.assertEquals(9, root.right.right.value);
        Assert.assertEquals(10, root.right.right.right.value);
        Assert.assertNull(root.right.right.left);
    }

    @Test
    public void deleteLeafNodeTest() {
        int[] ints = new int[] {6,4,2,5,1,3,8,7,9,10};
        TreeNode root = fillTree(ints);
        root = deleteNode(root, 7);
        Assert.assertEquals(6, root.value);

        Assert.assertEquals(4, root.left.value);
        Assert.assertEquals(2, root.left.left.value);
        Assert.assertEquals(5, root.left.right.value);
        Assert.assertEquals(1, root.left.left.left.value);
        Assert.assertEquals(3, root.left.left.right.value);

        Assert.assertEquals(8, root.right.value);
        Assert.assertNull(root.right.left);
        Assert.assertEquals(9, root.right.right.value);
        Assert.assertEquals(10, root.right.right.right.value);
        Assert.assertNull(root.right.right.left);
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        TreeNode(int value) {
            this.value = value;
        }

        TreeNode(TreeNode left, TreeNode right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    TreeNode deleteNode(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        TreeNode tempNode;
        if (root.value == value) {
            if (root.right == null && root.left == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            int minValue = findMinInBinarySearchTree(root.right);
            root.value = minValue;
            root.right = deleteNode(root.right, minValue);
        } else {
            if (value < root.value) {
                tempNode = deleteNode(root.left, value);
                root.left = tempNode;
            } else {
                tempNode = deleteNode(root.right, value);
                root.right = tempNode;
            }
        }
        return root;
    }

    private int findMinInBinarySearchTree(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftMin = findMinInBinarySearchTree(root.left);
        int rightMin = findMinInBinarySearchTree(root.right);
        return Math.min(Math.min(leftMin, rightMin), root.value);
    }

    TreeNode fillTree(int[] arr) {
        TreeNode root = null;
        for (int value : arr) {
             root = insert(root, value);
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        TreeNode nodeToInsert;
        if (value < root.value) {
            nodeToInsert = insert(root.left, value);
            root.left = nodeToInsert;
        }
        if (value > root.value) {
            nodeToInsert = insert(root.right, value);
            root.right = nodeToInsert;
        }
        return root;
    }
}
