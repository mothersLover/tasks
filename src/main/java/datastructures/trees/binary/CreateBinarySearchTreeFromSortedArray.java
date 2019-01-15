package test.datastructures.trees.binary;

import org.junit.Assert;
import org.junit.Test;

public class CreateBinarySearchTreeFromSortedArray {

    @Test
    public void test() {
        int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11};
        TreeNode root = createTreeFromSortedArray(arr, 0, arr.length - 1);
        Assert.assertEquals(6, root.value);
        Assert.assertEquals(3, root.left.value);
        Assert.assertEquals(9, root.right.value);
        Assert.assertEquals(1, root.left.left.value);
        Assert.assertEquals(4, root.left.right.value);
        Assert.assertNull(root.left.left.left);
        Assert.assertEquals(2, root.left.left.right.value);
        Assert.assertNull(root.left.right.left);
        Assert.assertEquals(5, root.left.right.right.value);
    }

    TreeNode createTreeFromSortedArray(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int middlePosition = (start + end) / 2;
        TreeNode currNode = new TreeNode(arr[middlePosition]);
        TreeNode leftNode = createTreeFromSortedArray(arr, start, middlePosition - 1);
        TreeNode rightNode = createTreeFromSortedArray(arr, middlePosition + 1, end);
        currNode.left = leftNode;
        currNode.right = rightNode;
        return currNode;
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
