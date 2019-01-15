package test.datastructures.trees.binary;

import org.junit.Assert;
import org.junit.Test;

public class TrimNodesOutOfRange {

    @Test
    public void trimOutOfRangeTest() {
        int[] ints = new int[] {6,4,2,5,1,3,8,7,9,10};
        TreeNode root = fillTree(ints);
        root = trimOutOfRange(root, 4, 8);
        Assert.assertEquals(6, root.value);

        Assert.assertEquals(4, root.left.value);
        Assert.assertEquals(5, root.left.right.value);

        Assert.assertEquals(8, root.right.value);
        Assert.assertEquals(7, root.right.left.value);
        Assert.assertNull(root.right.right);
        Assert.assertNull(root.left.left);
    }

    TreeNode trimOutOfRange(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        root.left = trimOutOfRange(root.left, min, max);
        root.right = trimOutOfRange(root.right, min, max);

        if (root.value < min) {
            return root.right;
        }
        if (root.value > max) {
            return root.left;
        }
        return root;
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

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        TreeNode(int value) {
            this.value = value;
        }

        TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
